package cn.com.youplus.thirdparty.rpc.service.weixin.abst;


import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.common.exception.UnsupportWeixinEnentException;
import cn.com.youplus.common.model.enums.WeixinApiTypeEnum;
import cn.com.youplus.common.model.enums.WeixinMessageTypeEnum;
import cn.com.youplus.common.model.enums.WeixinResopnseMatchTypeEnum;
import cn.com.youplus.common.model.enums.WeixinResopnseTypeEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinRequestLog;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinResponseRule;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinUser;
import cn.com.youplus.notification.api.LocationBaseService;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import cn.com.youplus.thirdparty.api.auto.UpWeixinRequestLogService;
import cn.com.youplus.thirdparty.api.auto.UpWeixinResponseRuleService;
import cn.com.youplus.thirdparty.api.auto.UpWeixinUserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.weixin4j.Configuration;
import org.weixin4j.message.*;
import org.weixin4j.message.output.ImageOutputMessage;
import org.weixin4j.message.output.NewsOutputMessage;
import org.weixin4j.message.output.TextOutputMessage;
import org.weixin4j.message.output.VoiceOutputMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lijian on 2016/9/28.
 */
public abstract class WeixinMessageServiceAbst {

    private static Logger logger = Logger.getLogger(WeixinMessageServiceAbst.class);

    static protected final String openidKey = "fromusername";
    static protected final String unionidKey = "unionid";
    static protected final String originalKey = "tousername";
    static protected final String appidKey = "appid";
    static protected final String accesstokenKey = "accesstoken";
    static protected final String companyIdKey = "companyid";

    //国家地区语言版本 zh_CN 简体，zh_TW 繁体，en 英语
    public final static String lang = "zh_CN";

    @Autowired
    protected SystemConfigService systemConfigService;

    @Autowired
    protected UpWeixinAccountService upWeixinAccountService;

    @Autowired
    protected UpWeixinRequestLogService upWeixinRequestLogService;

    @Autowired
    protected UpWeixinUserService upWeixinUserService;

    @Autowired
    protected UpWeixinResponseRuleService upWeixinResponseRuleService;

    @Autowired
    protected LocationBaseService locationBaseService;

    @Autowired
    protected AliyunProperties aliyunProperties;

    /**
     * 预处理,不同的公众号，处理不一样
     */
    public abstract void preHandle(Map<String, String> dataMap) throws Exception;

    /**
     * 扫码待key
     * @param eventKey
     * @return
     */
    public abstract UpWeixinResponseRule handleQrsceneScan(Map<String, String> dataMap, String eventKey) throws Exception;

    public abstract UpWeixinResponseRule handleSpecialText(Map<String, String> dataMap, String text);



    /**
     * 处理微信服务器发来的消息
     *
     * @param dataMap
     * @return
     */
    protected String handleInputMessage(Map<String, String> dataMap) {
        String msg = "";
        UpWeixinRequestLog weixinLog = new UpWeixinRequestLog();
        weixinLog.setUpdateTime(new Date());
        weixinLog.setAddTime(new Date());
        weixinLog.setRequestType(dataMap.get("msgtype"));
        weixinLog.setEnevntKey(dataMap.get("eventkey"));
        weixinLog.setRequestContent(JSONObject.toJSONString(dataMap));

        //微信新出的事件，不chuli
        String event = dataMap.get("event");
        if (event != null && event.toUpperCase().equals("TEMPLATESENDJOBFINISH")) {
            return "";
        }

        try {
            //第三层
            OutputMessage outputMessage = inputMessageHandller(dataMap);
            if (outputMessage != null) {
                msg = outputMessage.toXML();
                weixinLog.setResponseType(outputMessage.getMsgType());
                weixinLog.setResponseContent(JSONObject.toJSONString(outputMessage));

            } else {
                weixinLog.setResponseType("");
                weixinLog.setResponseContent("");
            }
        } catch (Exception e) {
            logger.info("", e);
            weixinLog.setResponseType("EXCEPTION");
            weixinLog.setResponseContent(e.getMessage());
        }
        weixinLog.setAppid(dataMap.get(appidKey));
        try {
            upWeixinRequestLogService.insert(weixinLog);
        } catch (Exception e) {
            logger.info("", e);
        }
        return msg;
    }

    private OutputMessage inputMessageHandller(Map<String, String> dataMap) throws Exception {
        UpWeixinAccount account = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.AUTHORIZER_APPID, dataMap.get(appidKey))
        );

        UpWeixinResponseRule outputRule = null;
        String openid = dataMap.get(openidKey);
        if(ValueHelper.isNone(openid))
        {
            throw new Exception("openid 不存在");
        }
        //非取消关注事件
        if(!(dataMap.get("msgtype").equals(WeixinApiTypeEnum.event.getType())&&
                dataMap.get("event").toUpperCase().equals(EventUppercaseType.UNSUBSCRIBE.toString()))){
            preHandle(dataMap); //进行预处理
        }

        // 取得消息类型
        String msgType = dataMap.get("msgtype");
        if (Configuration.isDebug()) {
            logger.debug("POST的消息类型:[" + msgType + "]");
        }
        switch (WeixinApiTypeEnum.valueOf(msgType)) {
            case text://处理文本消息
                String Content = dataMap.get("content");
                UpWeixinResponseRule kmWeixinResponseRule = handleSpecialText(dataMap, Content);
                //如果是空的再去匹配
                if (kmWeixinResponseRule == null) {
                    outputRule = getKeywordRule(dataMap.get(appidKey), Content.trim());
                }
                break;
            case image://处理图片消息
            case voice://处理语音消息
            case video://处理视频消息
            case shortvideo://处理小视屏消息
            case link://处理链接消息
            case location://处理发送过来的位置消息
                logger.info("微信接收到：[" + msgType + "] 采用默认回复");
                break;
            case event://处理事件消息
                logger.debug("收到事件消息" + WeixinApiTypeEnum.event.getType());
                try {
                    //第四层
                    outputRule = handleEvent(dataMap);
                } catch (UnsupportWeixinEnentException e) {
                    return null;
                }
                break;
            default:
                break;
        }
        if (outputRule != null) {
            OutputMessage outputMessage = getOutputMessageByRule(dataMap, outputRule);
            // 把发送发送对象转换为xml输出
            return outputMessage;
        } else {
            return getOutputMessageByRule(dataMap, getDefauleRule(dataMap));
        }
    }

    private OutputMessage getOutputMessageByRule(Map<String, String> dataMap, UpWeixinResponseRule rule){
        if (rule == null) {
            return null;
        }
        OutputMessage message = null;
        switch (WeixinMessageTypeEnum.valueOf(rule.getMessageType())) {
            case NEWS:
                NewsOutputMessage newsMessage = new NewsOutputMessage();
                String content = rule.getContent();
                JSONArray jsonObjects = JSONObject.parseArray(content);
                List<Articles> list = new ArrayList<>();
                for (Object json : jsonObjects) {
                    JSONObject object = (JSONObject)json;
                    String title = object.getString("title");
                    String description = object.getString("description");
                    String imgUrl = object.getString("imgUrl");
                    String link = object.getString("link");
                    Articles articles = new Articles();
                    articles.setTitle(title);
                    articles.setDescription(description);
                    articles.setUrl(link);
                    articles.setPicUrl(aliyunProperties.getOssImgUrl(imgUrl));
                    list.add(articles);
                }
                newsMessage.setArticles(list);
                message = newsMessage;
                break;
            case TEXT:
                TextOutputMessage textMessage = new TextOutputMessage();
                textMessage.setContent(rule.getContent());
                message = textMessage;
                break;
            case IMAGE:
                ImageOutputMessage imageMessage = new ImageOutputMessage();
                Image image = new Image();
                image.setMediaId(rule.getMediaId());
                imageMessage.setImage(image);
                message = imageMessage;
                break;
            case VOICE:
                VoiceOutputMessage voiceMessage = new VoiceOutputMessage();
                Voice voice = new Voice();
                voice.setMediaId(rule.getMediaId());
                voiceMessage.setVoice(voice);
                message = voiceMessage;
                break;
        }

        message.setToUserName(dataMap.get(openidKey));
        message.setFromUserName(dataMap.get(originalKey));
        message.setCreateTime(new Date().getTime());
        return message;
    }

    /**
     * 处理事件消息
     * @param dataMap
     * @return
     */
     public UpWeixinResponseRule handleEvent(Map<String,String> dataMap) throws Exception {
         UpWeixinResponseRule outputMsg = null;
        //获取事件类型
        String event = dataMap.get("event").toLowerCase();
        String eventKey = dataMap.get("eventkey");
        logger.debug("事件eventkey："+eventKey);
         logger.debug("事件类型："+event);
        //自定义菜单事件
         EventUppercaseType type = EventUppercaseType.valueOf(event.toUpperCase());
         switch (type) {
             case SCAN:
             case SUBSCRIBE:
                 logger.debug("未关注用户扫描带参数二维码事件");
                 eventKey = eventKey.replace("qrscene_", "");

                 if (ValueHelper.isNone(eventKey.trim())) { //如果没有参数，则使用默认回复
                     outputMsg = getTypeRule(dataMap, WeixinResopnseTypeEnum.默认关注回复);
                 } else { //有参数，需要处理
                     outputMsg = handleQrsceneScan(dataMap, eventKey);
                     if (outputMsg == null) {
                         outputMsg = getTypeRule(dataMap, WeixinResopnseTypeEnum.扫码关注回复);
                     }
                 }
                 break;
             case CLICK:
                 outputMsg = getClickRule(dataMap.get(appidKey), eventKey);
                 break;

             case UNSUBSCRIBE:
                 handleUnsubscrbe(dataMap);
                 throw new UnsupportWeixinEnentException("Unsubscribe");

             case LOCATION:
                 //第五层
                 handleLocation(dataMap);  //先处理location
                 throw new UnsupportWeixinEnentException("Unsubscribe");
             case VIEW:  //这个事件不用回复
             case PIC_WEIXIN:
             case SCANCODE_PUSH:
             case PIC_SYSPHOTO:
             case LOCATION_SELECT:
             case SCANCODE_WAITMSG:
             case PIC_PHOTO_OR_ALBUM:
             default:
                 logger.info("不支持的类型");
                 throw new UnsupportWeixinEnentException(event);
         }
         return outputMsg;
    }

    public void handleUnsubscrbe(Map<String, String> dataMap) throws Exception {
        UpWeixinUser user = upWeixinUserService.selectOne(
                new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.THIRDPARTY_OPENID, dataMap.get(openidKey))
        );
        if (user != null) {
            UpWeixinUser newUser = new UpWeixinUser();
            newUser.setUpdateTime(new Date());
            newUser.setIsSubscribe(0);
            newUser.setId(user.getId());
            upWeixinUserService.updateById(newUser);
        } else {
            logger.info("地理时间，用户不存在");
        }
    }

    public void handleLocation(Map<String, String> dataMap) throws Exception {
        String Latitude = dataMap.get("latitude").toString();
        String Longitude = dataMap.get("longitude").toString();
        float lat = Float.parseFloat(Latitude);
        float lng = Float.parseFloat(Longitude);

        UpWeixinUser user = upWeixinUserService.selectOne(
                new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.THIRDPARTY_OPENID, dataMap.get(openidKey))
        );
        if (user != null) {
            locationBaseService.getLocationByLocation(user.getId(),lat, lng);
        } else {
            logger.info("地理时间，用户不存在");
        }
    }

    UpWeixinResponseRule getDefauleRule(Map<String, String> dataMap) throws Exception {
        return upWeixinResponseRuleService.selectOne(
                new EntityWrapper<UpWeixinResponseRule>()
                        .eq(UpWeixinResponseRule.APPID, dataMap.get(appidKey))
                        .eq(UpWeixinResponseRule.RESPONSE_TYPE, WeixinResopnseTypeEnum.默认回复.getType())
                        .orderBy(UpWeixinResponseRule.ADD_TIME, false)
        );
    }

    UpWeixinResponseRule getTypeRule(Map<String, String> dataMap, WeixinResopnseTypeEnum typeEnum) throws Exception {
        return upWeixinResponseRuleService.selectOne(
                new EntityWrapper<UpWeixinResponseRule>()
                        .eq(UpWeixinResponseRule.APPID, dataMap.get(appidKey))
                        .eq(UpWeixinResponseRule.RESPONSE_TYPE, typeEnum.getType())
                        .orderBy(UpWeixinResponseRule.ADD_TIME, false)
        );
    }

    UpWeixinResponseRule getKeywordRule(String appid, String key) throws Exception {
        return upWeixinResponseRuleService.selectOne(
                new EntityWrapper<UpWeixinResponseRule>()
                        .eq(UpWeixinResponseRule.APPID, appid)
                        .eq(UpWeixinResponseRule.RESPONSE_TYPE, WeixinResopnseTypeEnum.关键字回复.getType())
                        .andNew()
                        .eq(UpWeixinResponseRule.MATCH_TYPE, WeixinResopnseMatchTypeEnum.完全匹配.getType())
                        .eq(UpWeixinResponseRule.KEYWORD, key)
                        .orNew()
                        .eq(UpWeixinResponseRule.MATCH_TYPE, WeixinResopnseMatchTypeEnum.包含匹配.getType())
                        .like(UpWeixinResponseRule.KEYWORD, "|" +key+"|")
                        .orNew()
                        .eq(UpWeixinResponseRule.MATCH_TYPE, WeixinResopnseMatchTypeEnum.包含匹配.getType())
                        .like(UpWeixinResponseRule.KEYWORD, key+"|")
                        .orNew()
                        .eq(UpWeixinResponseRule.MATCH_TYPE, WeixinResopnseMatchTypeEnum.包含匹配.getType())
                        .like(UpWeixinResponseRule.KEYWORD, "|" +key)
                        .orderBy(UpWeixinResponseRule.ADD_TIME, false)
        );
    }

    UpWeixinResponseRule getClickRule(String appid, String key) throws Exception {
        return upWeixinResponseRuleService.selectOne(
                new EntityWrapper<UpWeixinResponseRule>()
                        .eq(UpWeixinResponseRule.APPID, appid)
                        .eq(UpWeixinResponseRule.KEYWORD, key)
                        .eq(UpWeixinResponseRule.RESPONSE_TYPE, WeixinResopnseTypeEnum.菜单栏回复.getType())
                        .eq(UpWeixinResponseRule.MATCH_TYPE, WeixinResopnseMatchTypeEnum.完全匹配.getType())
                        .orderBy(UpWeixinResponseRule.ADD_TIME, false)
        );
    }
}
