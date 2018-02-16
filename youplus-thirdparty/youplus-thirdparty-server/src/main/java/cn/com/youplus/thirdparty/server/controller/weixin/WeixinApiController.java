package cn.com.youplus.thirdparty.server.controller.weixin;

import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.base.api.auto.UpBaseSystemConfigService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.annotation.NoApiLog;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.enums.MessageQueueTagEnum;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.common.model.enums.SystemConfigTypeEnum;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.RequestUtil;
import cn.com.youplus.model.auto.entity.base.UpBaseSystemConfig;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinMessageLog;
import cn.com.youplus.tenants.common.enums.InfoTypeEnum;
import cn.com.youplus.tenants.common.form.EncryptForm;
import cn.com.youplus.tenants.common.weixin.aes.WXBizMsgCrypt;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import cn.com.youplus.thirdparty.api.auto.UpWeixinMessageLogService;
import cn.com.youplus.thirdparty.api.weixin.WeixinApiService;
import cn.com.youplus.thirdparty.server.controller.SuperController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/6/27.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("thirdparty/v1/weixin")
public class WeixinApiController extends SuperController {

    private static Logger logger = Logger.getLogger(WeixinApiController.class);

    @Autowired
    private WeixinApiService weixinApiService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Autowired
    private UpBaseSystemConfigService upBaseSystemConfigService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private UpWeixinMessageLogService upWeixinMessageLogService;

    @ExceptionHandler(value = Throwable.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return exceptionRealHandler(request, response, exception);
    }

    /**
     * 微信定时推送ComponentVerifyTicket
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping("/message")
    public String weixinMessage(EncryptForm form) throws Exception {

        WXBizMsgCrypt pc = new WXBizMsgCrypt(weixinProperties.getToken(), weixinProperties.getEncodingAesKey(), weixinProperties.getAppid());

        logger.info(form);
        String msgSignature = form.getMsg_signature();
        String timestamp = form.getTimestamp();
        String nonce = form.getNonce();
        String fromXML = RequestUtil.getRequestPostStr(request);
        logger.info("我是拿到的log");
        logger.info(fromXML);

        String XMLStr = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);


        Document document = DocumentHelper.parseText(XMLStr);
        Element root = document.getRootElement();

        UpWeixinMessageLog weixinMessage = new UpWeixinMessageLog();
        weixinMessage.setComponentVerifyTicket(root.elementText("ComponentVerifyTicket"));
        weixinMessage.setAppid(root.elementText("AppId"));
        weixinMessage.setInfoType(root.elementText("InfoType"));
        weixinMessage.setAuthorizerAppid(root.elementText("AuthorizerAppid"));
        weixinMessage.setAuthorizationCode(root.elementText("AuthorizationCode"));
        weixinMessage.setAuthorizationCodeExpiredTime(root.elementText("AuthorizationCodeExpiredTime"));
        weixinMessage.setPreAuthCode(root.elementText("PreAuthCode"));
        weixinMessage.setAddTime(new Date());
        weixinMessage.setUpdateTime(new Date());
        upWeixinMessageLogService.insert(weixinMessage);

        InfoTypeEnum infoTypeEnum = InfoTypeEnum.valueOf(root.element("InfoType").getStringValue());
        switch (infoTypeEnum) {
            case authorized:
                break;
            case unauthorized:
                if (weixinMessage.getAuthorizerAppid() != null) {
                    UpWeixinAccount weixinAccount = upWeixinAccountService.selectOne(
                            new EntityWrapper<UpWeixinAccount>()
                                    .eq(UpWeixinAccount.AUTHORIZER_APPID, weixinMessage.getAppid())
                    );
                    weixinAccount.setIsAuth(0);
                    weixinAccount.setUpdateTime(new Date());
                    upWeixinAccountService.updateById(weixinAccount);
                }
                break;
            case updateauthorized:
                weixinApiService.updateWeixinAccountAuthorization(weixinMessage.getAuthorizationCode());
                break;
            case component_verify_ticket:
                Element element = root.element("ComponentVerifyTicket");
                String str = element.getStringValue();

                UpBaseSystemConfig config = upBaseSystemConfigService.selectOne(
                        new EntityWrapper<UpBaseSystemConfig>()
                                .eq(UpBaseSystemConfig.PARAMETER_TYPE, SystemConfigParamsTypeEnum.第三方参数.getType())
                                .eq(UpBaseSystemConfig.ATTRIBUTE_KEY, SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_VERIFY_TICKET)
                                .orderBy(UpBaseSystemConfig.ADD_TIME, false)
                );

                if (config == null) {
                    upBaseSystemConfigService.insert(new UpBaseSystemConfig()
                            .setParameterType(SystemConfigParamsTypeEnum.第三方参数.getType())
                            .setAttributeKey(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_VERIFY_TICKET)
                            .setUpdateTime(new Date())
                            .setAddTime(new Date())
                            .setAttributeValue(str)
                    );
                } else {
                    upBaseSystemConfigService.updateById(config.setAttributeValue(str).setUpdateTime(new Date()));
                }

                messageQueueService.sendMq(new MessageQueueParams()
                        .setTag(MessageQueueTagEnum.BROADCAST_SYSTEM_CONFIG.getType())
                        .setBody(SystemConfigTypeEnum.THIRDPARTY_SYSTEM_CONFIG.getType())
                );
                break;
            default:

        }

        logger.info(XMLStr);

        return "success";
    }

    /**
     * 微信开发后台接口
     * @throws IOException
     */
    @NoApiLog
    @RequestMapping("/callback/{appid}")
    public void weixinDidizuliApi(@PathVariable String appid, EncryptForm form) throws Exception {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        WXBizMsgCrypt pc = new WXBizMsgCrypt(weixinProperties.getToken(), weixinProperties.getEncodingAesKey(), weixinProperties.getAppid());
        logger.info(form);
        String msgSignature = form.getMsg_signature();
        String timestamp = form.getTimestamp();
        String nonce = form.getNonce();
        String openid = form.getOpenid();
        String fromXML = RequestUtil.getRequestPostStr(request);
        logger.info("我是拿到的log");
        logger.info(fromXML);

        String XMLStr = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
        Document document = DocumentHelper.parseText(XMLStr);
        Element root = document.getRootElement();
        handleRequest(out, root, appid, openid);
    }


    private void handleRequest(PrintWriter out, Element xmlElement, String appid, String openid) throws Exception {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("appid", appid);
        dataMap.put("openid", openid);

        for (Object element :xmlElement.elements()) {
            if (element instanceof Element) {
                Element elementNode = (Element)element;
                String key = elementNode.getName().toLowerCase();
                String value = elementNode.getText();
                dataMap.put(key, value);
            }
        }

        String xml= weixinApiService.handleInputMessage(dataMap);
        logger.debug("POST输出消息:[" + xml + "]");

        out.write(xml);
        out.close();
    }


}
