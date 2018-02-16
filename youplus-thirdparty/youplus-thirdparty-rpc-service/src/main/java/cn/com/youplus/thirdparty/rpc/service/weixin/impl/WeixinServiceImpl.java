package cn.com.youplus.thirdparty.rpc.service.weixin.impl;

import cn.com.youplus.apps.common.model.enums.EntranceEnum;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.WeixinOauthBaseInfo;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.AESUtil;
import cn.com.youplus.common.util.HttpConnect;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinUser;
import cn.com.youplus.thirdparty.api.auto.UpWeixinUserService;
import cn.com.youplus.thirdparty.api.weixin.WeixinService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.weixin4j.WeixinConstants;

import java.net.URLDecoder;
import java.util.Date;


/**
 * Created by 严汉羽 on 2017/12/21.
 */
@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {

    private static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);
    @Autowired
    private UpWeixinUserService upWeixinUserService;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private TaskExecutor taskExecutor;

    public class WeixinUserInfoGetJob implements Runnable {

        private UpWeixinUserService upWeixinUserService;
        private UpWeixinUser weixinUser;
        private EntranceEnum entranceEnumr;

        private WeixinUserInfoGetJob() {
        }

        public WeixinUserInfoGetJob(UpWeixinUserService upWeixinUserService, UpWeixinUser weixinUser, EntranceEnum entranceEnum) {
            this.upWeixinUserService = upWeixinUserService;
            this.weixinUser = weixinUser;
            this.entranceEnumr = entranceEnum;
        }

        @Override
        public void run() {
            logger.debug("KINGYHY: 开始执行任务");
            try {
                //抓取用户信息
                switch (entranceEnumr) {
                    case WEIXIN_INFO_OAUTH:
                    case LINK_OAUTH:
                        String oauthUrl = String.format(WeixinConstants.WEIXIN_公众号网页版获取用户详细信息_ACCESS_TOKEN_OPENID,
                                entranceEnumr == EntranceEnum.LINK_OAUTH ? weixinUser.getWebappAccessToken() : weixinUser.getYouplusAccessToken(),
                                entranceEnumr == EntranceEnum.LINK_OAUTH ? weixinUser.getWebappOpenid() : weixinUser.getYouplusOpenid());

                        logger.debug("oauthUrl:[" + oauthUrl + "]");
                        String responeStr = HttpConnect.getInstance().doGet(oauthUrl,null);
                        logger.debug("respone:[" + responeStr + "]");
                        JSONObject json = JSON.parseObject(responeStr);
                        boolean needUpdated = false;

                        int sex = json.getIntValue("sex");
                        if (weixinUser.getSex() ==  null || sex != weixinUser.getSex()) {
                            weixinUser.setSex(sex);
                            needUpdated = true;
                        }

                        String nickname = json.getString("nickname");
                        if (nickname !=null && !nickname.equals(weixinUser.getNickname())) {
                            weixinUser.setNickname(nickname);
                            needUpdated = true;
                        }

                        String province = json.getString("province");
                        if (province !=null && !province.equals(weixinUser.getProfileProvince())) {
                            weixinUser.setProfileProvince(province);
                            needUpdated = true;
                        }

                        String city = json.getString("city");
                        if (city !=null && !city.equals(weixinUser.getProfileCity())) {
                            weixinUser.setProfileCity(city);
                            needUpdated = true;
                        }

                        String country = json.getString("country");
                        if (country !=null && !country.equals(weixinUser.getProfileCountry())) {
                            weixinUser.setProfileCountry(country);
                            needUpdated = true;
                        }

                        String headimgurl = json.getString("headimgurl");
                        if (headimgurl !=null && !headimgurl.equals(weixinUser.getHeadImg())) {
                            weixinUser.setHeadImg(headimgurl);
                            needUpdated = true;
                        }

                        if (needUpdated) {
                            upWeixinUserService.updateById(weixinUser);
                        }
                        break;
                }
            } catch (Exception e) {
                logger.debug("KINGYHY: 任务异常", e);
            }
        }
    }

    @Override
    public UpWeixinUser getWeixinUserByCode(String code, String cookie, EntranceEnum entranceEnum) throws Exception {
        UpWeixinUser upWeixinUser = getWeixinUserByRefreshToken(cookie, entranceEnum);
        if (upWeixinUser != null) {
            return upWeixinUser;
        }

        if (ValueHelper.isNone(code)) {
            return null;
        }

        switch (entranceEnum) {
            case WEIXIN_INFO_OAUTH:
            case WEIXIN_BASE_OAUTH:
            case LINK_OAUTH:  //获取信息的链接和流程是一样的
                String oauthUrl = String.format(WeixinConstants.WEIXIN_公众号网页版获取信息_APPID_SECRET_CODE,
                        entranceEnum == EntranceEnum.LINK_OAUTH ? weixinProperties.getWebappId() : weixinProperties.getOfficialAccountsAppId(),
                        entranceEnum == EntranceEnum.LINK_OAUTH ? weixinProperties.getWebappSecret() : weixinProperties.getOfficialAccountsAppSecret(),
                        code);

                logger.debug("oauthUrl:[" + oauthUrl + "]");
                String responeStr = HttpConnect.getInstance().doGet(oauthUrl,null);
                logger.debug("respone:[" + responeStr + "]");
                JSONObject json = JSON.parseObject(responeStr);

                WeixinOauthBaseInfo info = new WeixinOauthBaseInfo(json);
                if(ValueHelper.isNone(info.getUnionid())) {
                    return null;
                }

                UpWeixinUser weixinUser = upWeixinUserService.selectOne(new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.UNIONID, info.getUnionid()));

                if (weixinUser == null) {
                    weixinUser = new UpWeixinUser();
                    weixinUser.setUnionid(info.getUnionid())
                            .setAddTime(new Date())
                            .setUpdateTime(new Date());

                    if(entranceEnum == EntranceEnum.LINK_OAUTH) {
                        weixinUser.setWebappOpenid(info.getOpenid())
                                .setWebappAccessToken(info.getAccessToken());
                    } else {
                        weixinUser.setYouplusOpenid(info.getOpenid())
                                .setYouplusAccessToken(info.getAccessToken());
                    }

                    weixinUser = upWeixinUserService.mInsert(weixinUser);
                } else {
                    weixinUser.setUnionid(info.getUnionid())
                            .setUpdateTime(new Date());

                    if(entranceEnum == EntranceEnum.LINK_OAUTH) {
                        weixinUser.setWebappOpenid(info.getOpenid())
                                .setWebappAccessToken(info.getAccessToken());
                    } else {
                        weixinUser.setYouplusOpenid(info.getOpenid())
                                .setYouplusAccessToken(info.getAccessToken());
                    }

                    upWeixinUserService.updateById(weixinUser);
                }

                //如果是信息授权scope

                logger.debug("KINGYHY:" + JSONObject.toJSONString(info));

                if ("snsapi_userinfo".equals(info.getScope())) {
                    logger.debug("KINGYHY: 开始启动任务");
                    taskExecutor.execute(new WeixinUserInfoGetJob(upWeixinUserService, weixinUser, entranceEnum));
                }

                return weixinUser;

            case WEIXIN_COMPHONENT_BASE_OAUTH:
            case WEIXIN_COMPHONENT_INFO_OAUTH:
                throw new Exception("赞不支持！");

            default:
                return null;
        }
    }

    @Override
    public UpWeixinUser getWeixinUserByRefreshToken(String cookie, EntranceEnum entranceEnum) throws Exception {
        if (ValueHelper.isNone(cookie)) {
            return null;
        }

        logger.debug("KINGYHY:cookie" + cookie);
        String str = URLDecoder.decode(cookie,"utf-8");
        logger.debug("KINGYHY:decode" + str);
        String accessToken = AESUtil.AESDecodeForWeb(str); //从cookies里面得到accesstoken
        logger.debug("KINGYHY:cookie" + accessToken);
        if (ValueHelper.isNone(accessToken)) {
            return null;
        }

        Wrapper<UpWeixinUser> wrapper;
        switch (entranceEnum) {
            case WEIXIN_BASE_OAUTH:
            case WEIXIN_INFO_OAUTH:
                wrapper = new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.YOUPLUS_ACCESS_TOKEN, accessToken);
                break;
            case WEIXIN_COMPHONENT_INFO_OAUTH:
            case WEIXIN_COMPHONENT_BASE_OAUTH:
                wrapper = new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.THIRDPARTY_ACCESS_TOKEN, accessToken);
                break;
            case LINK:
            case LINK_OAUTH:
                wrapper = new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.WEBAPP_ACCESS_TOKEN, accessToken);
                break;

            default:
                throw new AlertException("不支持的授权类型！");
        }
        return upWeixinUserService.selectOne(wrapper);
    }
}
