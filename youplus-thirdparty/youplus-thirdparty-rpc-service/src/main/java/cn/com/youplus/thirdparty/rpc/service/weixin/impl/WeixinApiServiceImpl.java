package cn.com.youplus.thirdparty.rpc.service.weixin.impl;


import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.HttpConnect;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinResponseRule;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinUser;
import cn.com.youplus.thirdparty.api.auto.UpWeixinUserService;
import cn.com.youplus.thirdparty.api.weixin.WeixinApiService;
import cn.com.youplus.thirdparty.rpc.service.weixin.abst.WeixinMessageServiceAbst;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weixin4j.WeixinConstants;
import org.weixin4j.WeixinException;

import java.util.Date;
import java.util.Map;

/**
 * Created by lijian on 2016/9/28.
 */
@Service("weixinApiService")
public class WeixinApiServiceImpl extends WeixinMessageServiceAbst implements WeixinApiService {

    private static Logger logger = Logger.getLogger(WeixinApiServiceImpl.class);

    @Autowired
    private UpWeixinUserService upWeixinUserService;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private SystemConfigService systemConfigService;

    @Override
    public void preHandle(Map<String, String> dataMap) throws Exception {
        UpWeixinUser user = upWeixinUserService.selectOne(
                new EntityWrapper<UpWeixinUser>()
                        .eq(UpWeixinUser.THIRDPARTY_APPID, dataMap.get(appidKey))
                        .eq(UpWeixinUser.THIRDPARTY_OPENID, dataMap.get(openidKey))
        );

        if (user == null) {
            getUserInfo(dataMap,0L);
        } else {
            if (ValueHelper.isNone(user.getHeadImg()) ||
                    ValueHelper.isNone(user.getNickname()) ||
                    user.getIsSubscribe() == 0) {  //需要更新信息
                getUserInfo(dataMap, user.getId());
            }
        }
    }

    /**
     * 获取用户对象
     * 通过公众号，返回用户对象，进行用户相关操作</p>
     * @return 用户对象
     * @throws WeixinException
     */
    private UpWeixinUser  getUserInfo(Map<String, String> dataMap, Long id) throws Exception {
        //拼接参数

        UpWeixinAccount account = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>().eq(UpWeixinAccount.AUTHORIZER_APPID, dataMap.get(appidKey))
        );

        if (account == null) {
            return null;
        } else {
            dataMap.put(accesstokenKey, account.getAuthorizerAccessToken());
            dataMap.put(companyIdKey, account.getTenantsCompanyId().toString());
        }

        String param = "?access_token=" + dataMap.get(accesstokenKey);
        param += "&openid=" + dataMap.get(openidKey);
        logger.debug(param);


        //创建请求对象
        HttpConnect httpConnect = HttpConnect.getInstance();
        String jsonStr = httpConnect.doGet("https://api.weixin.qq.com/cgi-bin/user/info" + param,null);
        logger.debug("获取到的json:" + jsonStr);
        JSONObject json = JSONObject.parseObject(jsonStr);

        UpWeixinUser user = new UpWeixinUser();

        user.setIsSubscribe(json.getInteger("subscribe"));
        user.setUpdateTime(new Date());
        user.setNickname(json.getString("nickname"));
        user.setSex(json.getIntValue("sex"));
        user.setThirdpartyOpenid(json.getString("openid"));
        user.setHeadImg(json.getString("headimgurl"));

        if (ValueHelper.isNone(user.getLastCountry())) {
            user.setLastCountry(json.getString("country"));
        }
        user.setProfileCountry(json.getString("country"));
        user.setProfileProvince(json.getString("province"));
        user.setProfileCity(json.getString("city"));

        //如果没有地址，就更新地址
        if (ValueHelper.isNone(user.getLastCountry())) {
            user.setLastCountry(json.getString("country"));
        }
        if (ValueHelper.isNone(user.getLastProvince())) {
            user.setLastProvince(json.getString("province"));
        }
        if (ValueHelper.isNone(user.getLastCity())) {
            user.setLastCity(json.getString("city"));
        }

        user.setThirdpartyAppid(account.getAuthorizerAppid());
        if (id > 0) {
            user.setId(id);
            upWeixinUserService.updateById(user);
        } else {
            upWeixinUserService.insert(user);
        }
        return user;
    }

    @Override
    public UpWeixinResponseRule handleQrsceneScan(Map<String, String> dataMap, String eventKey) throws Exception {
        return null;
    }

    @Override
    public UpWeixinResponseRule handleSpecialText(Map<String, String> dataMap,String text) {
        if (text.toUpperCase().equals(Constants.WEIXIN_SPECIAL_TD)) {
            String openid = dataMap.get(openidKey);
            if (ValueHelper.isNone(openid)) {
                return null;
            }
            UpWeixinUser kmWeixinUser = new UpWeixinUser();
            kmWeixinUser.setIsTd(1);
            kmWeixinUser.setUpdateTime(new Date());
            try {
                upWeixinUserService.update(kmWeixinUser,
                        new EntityWrapper<UpWeixinUser>()
                                .eq(UpWeixinUser.IS_TD, 0)
                                .eq(UpWeixinUser.THIRDPARTY_OPENID, dataMap.get(openidKey))
                );
                logger.info("成功退订用户");
            } catch (Exception e) {
                logger.info("", e);
            }
        } else if (text.toUpperCase().equals(Constants.WEIXIN_SPECIAL_DY)) {
            String openid = dataMap.get(openidKey);
            if (ValueHelper.isNone(openid)) {
                return null;
            }
            UpWeixinUser kmWeixinUser = new UpWeixinUser();
            kmWeixinUser.setIsTd(0);
            kmWeixinUser.setUpdateTime(new Date());
            try {
                upWeixinUserService.update(kmWeixinUser,
                        new EntityWrapper<UpWeixinUser>()
                                .eq(UpWeixinUser.IS_TD, 0)
                                .eq(UpWeixinUser.THIRDPARTY_OPENID, dataMap.get(openidKey))
                );
            } catch (Exception e) {
                logger.info("", e);
            }
        }
        return null;
    }

    @Override
    public String handleInputMessage(Map<String, String> dataMap) {
        return super.handleInputMessage(dataMap);
    }

    @Override
    public void updateWeixinAccountAuthorization(String auth_code) {
        UpWeixinAccount weixinAccount = new UpWeixinAccount();
        weixinAccount.setAuthorizationCode(auth_code);

        //获取凭证
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("component_appid", weixinProperties.getAppid());
        jsonObject.put("authorization_code", auth_code);
        HttpConnect httpConnect = HttpConnect.getInstance();
        String url = null;
        try {
            url = WeixinConstants.WEIXIN_QUERY_AUTH + "?component_access_token=" + systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        JSONObject result = httpConnect.doPost(url, jsonObject);
        JSONObject authorization_info = result.getJSONObject("authorization_info");
        String appid = "";
        if (authorization_info != null) {
            appid = authorization_info.getString("authorizer_appid");
            String authorizer_access_token = authorization_info.getString("authorizer_access_token");
            String authorizer_refresh_token = authorization_info.getString("authorizer_refresh_token");
            String func_info = authorization_info.getJSONArray("func_info").toJSONString();

            weixinAccount.setAuthorizerAppid(appid);
            weixinAccount.setIsAuth(1);
            weixinAccount.setAuthorizerAccessToken(authorizer_access_token);
            weixinAccount.setAuthorizerRefreshToken(authorizer_refresh_token);
            weixinAccount.setFuncInfo(func_info);

        } else {
            weixinAccount.setDescription("获取authorizer_info失败: " + result.toJSONString());
        }


        //获取授权者信息
        JSONObject infoObject = new JSONObject();
        infoObject.put("component_appid", weixinProperties.getAppid());
        infoObject.put("authorizer_appid", appid);
        try {
            url = WeixinConstants.WEIXIN_GET_AUTHORIZER_INFO + "?component_access_token=" + systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject info = httpConnect.doPost(url, infoObject);
        JSONObject authorizer_info = info.getJSONObject("authorizer_info");
        if (authorizer_info != null) {
            String nick_name = authorizer_info.getString("nick_name");
            String head_img = authorizer_info.getString("head_img");
            String service_type_info = authorizer_info.getJSONObject("service_type_info").toJSONString();
            String verify_type_info = authorizer_info.getJSONObject("verify_type_info").toJSONString();
            String user_name = authorizer_info.getString("user_name");
            String principal_name = authorizer_info.getString("principal_name");
            String business_info = authorizer_info.getJSONObject("business_info").toJSONString();
            String alias = authorizer_info.getString("alias");
            String qrcode_url = authorizer_info.getString("qrcode_url");

            weixinAccount.setNickName(nick_name);
            weixinAccount.setHeadImg(head_img);
            weixinAccount.setServiceTypeInfo(service_type_info);
            weixinAccount.setVerifyTypeInfo(verify_type_info);
            weixinAccount.setUserName(user_name);
            weixinAccount.setPrincipalName(principal_name);
            weixinAccount.setBusinessInfo(business_info);
            weixinAccount.setAlias(alias);
            weixinAccount.setQrcodeUrl(qrcode_url);
        } else {
            weixinAccount.setDescription(weixinAccount.getDescription() + ";  获取authorizer_info失败: " + info.toJSONString());
        }


        weixinAccount.setAddTime(new Date());
        weixinAccount.setUpdateTime(new Date());

        UpWeixinAccount checkAccount = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.AUTHORIZER_APPID, appid)
                        .orderBy(UpWeixinAccount.ADD_TIME, false)
        );
        if (checkAccount == null) {
            logger.info("找不到原有的weixinAccount");
            upWeixinAccountService.insert(weixinAccount);
        } else {
            weixinAccount.setId(checkAccount.getId());
            upWeixinAccountService.updateById(weixinAccount);
        }
    }








}
