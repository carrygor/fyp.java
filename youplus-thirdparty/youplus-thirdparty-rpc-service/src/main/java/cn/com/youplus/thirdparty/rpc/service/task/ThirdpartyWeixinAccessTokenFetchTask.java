package cn.com.youplus.thirdparty.rpc.service.task;

import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.base.api.auto.UpBaseSystemConfigService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.enums.MessageQueueTagEnum;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.common.model.enums.SystemConfigTypeEnum;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.HttpConnect;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.base.UpBaseSystemConfig;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;
import org.weixin4j.WeixinConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/6/27.
 */

@Component
@ActiveProfiles("master")
public class ThirdpartyWeixinAccessTokenFetchTask {
    private static Logger log = Logger.getLogger(ThirdpartyWeixinAccessTokenFetchTask.class);

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private UpBaseSystemConfigService upBaseSystemConfigService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    //region 三方平台
    @Scheduled(cron="0 0 0/1 * * ? ")
    public void fetchAccessTokenTask() throws Exception {
        fetchAccessToken(0);
    }

    public void fetchAccessToken(int retryCnt) throws Exception {
        log.info("优加+Thirdparty RPC Server服务器---开始抓取开放平台AccessToken。。。");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("component_appid", weixinProperties.getAppid());
        jsonObject.put("component_appsecret", weixinProperties.getAppsecret());
        jsonObject.put("component_verify_ticket", systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_VERIFY_TICKET));
        HttpConnect httpConnect = HttpConnect.getInstance();
        JSONObject result = httpConnect.doPost(WeixinConstants.WEIXIN_FETCH_ACCESS_TOKEN_URL, jsonObject);
        String component_access_token = result.getString("component_access_token");

        if (component_access_token == null) {
            log.info("get component access token fail");
            log.info(result.toJSONString());
            retryCnt++;
            if (retryCnt < 10) {
                Thread.sleep(10 * 1000);
                fetchAccessToken(retryCnt);
            }
        } else {
            UpBaseSystemConfig config = upBaseSystemConfigService.selectOne(
                    new EntityWrapper<UpBaseSystemConfig>()
                            .eq(UpBaseSystemConfig.PARAMETER_TYPE, SystemConfigParamsTypeEnum.第三方参数.getType())
                            .eq(UpBaseSystemConfig.ATTRIBUTE_KEY, SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN)
                            .orderBy(UpBaseSystemConfig.ADD_TIME, false)
            );

            if (config == null) {
                upBaseSystemConfigService.insert(new UpBaseSystemConfig()
                        .setParameterType(SystemConfigParamsTypeEnum.第三方参数.getType())
                        .setAttributeKey(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN)
                        .setUpdateTime(new Date())
                        .setAddTime(new Date())
                        .setAttributeValue(component_access_token)
                );
            } else {
                upBaseSystemConfigService.updateById(config.setAttributeValue(component_access_token).setUpdateTime(new Date()));
            }

            messageQueueService.sendMq(new MessageQueueParams()
                    .setTag(MessageQueueTagEnum.BROADCAST_SYSTEM_CONFIG.getType())
                    .setBody(SystemConfigTypeEnum.THIRDPARTY_SYSTEM_CONFIG.getType())
            );
        }
    }

    //endregion

    //region 公众号
    @Scheduled(cron="0 0 0/1 * * ? ")
    public void fetch服务号AccessTokenTask() throws Exception {
        int retryCnt = 0;
        while(!fetch服务号AccessToken()) {
            Thread.sleep(10 * 1000);
            retryCnt++;
            if (retryCnt > 10) {
                break;
            }
            log.info("优加+Thirdparty RPC Server服务器---开始抓取公众号AC失败，正在进行第" + retryCnt + "次重试...");
        }
    }

    public boolean fetch服务号AccessToken() throws Exception {
        log.info("优加+Thirdparty RPC Server服务器---开始抓取公众号平台AccessToken。。。");

        String appId = weixinProperties.getOfficialAccountsAppId();
        String secretKey = weixinProperties.getOfficialAccountsAppSecret();

        if (appId == null || secretKey == null) {
            log.info("优加+Thirdparty RPC Server服务器---抓取参数错误");
            return true;
        }
        Date date = new Date();
        String url = String.format(WeixinConstants.WEIXIN_刷新公众号的_Accesstoken_URL, appId, secretKey);
        try {
            log.debug("Get Request to:[" + url + "]");
            String respone = HttpConnect.getInstance().doGet(url,null);
            log.debug("Get Respone from:[" + respone + "]");
            JSONObject json = JSON.parseObject(respone);
            String access_token = json.getString("access_token");
            String expires_in_str = json.getString("expires_in");

            if (ValueHelper.isNone(access_token)) {
                return false;
            }
            String jsdkTicket = refresh服务号JsdkTicket(access_token);

            if (ValueHelper.isNone(jsdkTicket)) {
                return false;
            }
            int expires_in = 0;
            try{
                expires_in = Integer.parseInt(expires_in_str);
            } catch (Exception e) {
            }

            if (StringUtils.isEmpty(access_token) ||
                    StringUtils.isEmpty(expires_in)) {
                return false;
            }

            List<String> atts = new ArrayList<>();
            atts.add(SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_ACCESS_TOKEN);
            atts.add(SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_JSDK_TICKET);
            atts.add(SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_EXPIRE_TIME);

            List<UpBaseSystemConfig> configs = upBaseSystemConfigService.selectList(
                    new EntityWrapper<UpBaseSystemConfig>()
                            .eq(UpBaseSystemConfig.PARAMETER_TYPE, SystemConfigParamsTypeEnum.第三方参数.getType())
                            .in(UpBaseSystemConfig.ATTRIBUTE_KEY, atts)
                            .orderBy(UpBaseSystemConfig.ADD_TIME, false)
                            .groupBy(UpBaseSystemConfig.ATTRIBUTE_KEY)
            );

            //THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_ACCESS_TOKEN
            boolean updated = false;
            for (UpBaseSystemConfig config : configs) {
                if (SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_ACCESS_TOKEN.equals(config.getAttributeKey())) {
                    config.setAttributeValue(jsdkTicket);
                    config.setUpdateTime(new Date());

                    upBaseSystemConfigService.updateById(config);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                UpBaseSystemConfig newConfig = new UpBaseSystemConfig();
                newConfig.setAttributeKey(SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_ACCESS_TOKEN)
                        .setAttributeValue(expires_in_str)
                        .setParameterType(SystemConfigParamsTypeEnum.第三方参数.getType())
                        .setAddTime(new Date())
                        .setUpdateTime(new Date());

                upBaseSystemConfigService.insert(newConfig);
            }

            //THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_JSDK_TICKET
            updated = false;
            for (UpBaseSystemConfig config : configs) {
                if (SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_JSDK_TICKET.equals(config.getAttributeKey())) {
                    config.setAttributeValue(access_token);
                    config.setUpdateTime(new Date());

                    upBaseSystemConfigService.updateById(config);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                UpBaseSystemConfig newConfig = new UpBaseSystemConfig();
                newConfig.setAttributeKey(SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_JSDK_TICKET)
                        .setAttributeValue(access_token)
                        .setParameterType(SystemConfigParamsTypeEnum.第三方参数.getType())
                        .setAddTime(new Date())
                        .setUpdateTime(new Date());

                upBaseSystemConfigService.insert(newConfig);
            }

            //THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_EXPIRE_TIME
            updated = false;
            for (UpBaseSystemConfig config : configs) {
                if (SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_EXPIRE_TIME.equals(config.getAttributeKey())) {
                    config.setAttributeValue((date.getTime() + expires_in * 1000) + "");
                    config.setUpdateTime(new Date());

                    upBaseSystemConfigService.updateById(config);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                UpBaseSystemConfig newConfig = new UpBaseSystemConfig();
                newConfig.setAttributeKey(SystemConfigService.THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_EXPIRE_TIME)
                        .setAttributeValue(access_token)
                        .setParameterType(SystemConfigParamsTypeEnum.第三方参数.getType())
                        .setAddTime(new Date())
                        .setUpdateTime(new Date());

                upBaseSystemConfigService.insert(newConfig);
            }

            messageQueueService.sendMq(new MessageQueueParams()
                    .setTag(MessageQueueTagEnum.BROADCAST_SYSTEM_CONFIG.getType())
                    .setBody(SystemConfigTypeEnum.THIRDPARTY_SYSTEM_CONFIG.getType())
            );
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }

    protected String refresh服务号JsdkTicket(String accessToken) {
        if (accessToken == null) {
            return "";
        }
        String url = String.format(WeixinConstants.WEIXIN_刷新公众号的_JSDK_URL, accessToken);
        try {
            log.debug("获取JSDK ticket url:[:" + url + "]");
            String respone = HttpConnect.getInstance().doGet(url,null);
            log.debug("获取JSDK ticket respone:[:" + respone + "]");
            JSONObject json = JSON.parseObject(respone);
            String ticket = json.getString("ticket");
            if (StringUtils.isEmpty(ticket)) {
                return "";
            }
            return ticket;

        } catch (Exception e) {
            log.error("exception",e);
            return null;
        }
    }

    //endregion

    //region 第三方平台
    @Scheduled(cron="0 0 0/1 * * ? ")
    public void fetchAuthorizerToken() throws Exception {
        log.info("优加+Thirdparty RPC Server服务器---开始抓取授权公众号Token。。。");

        List<UpWeixinAccount> weixinAccountList = upWeixinAccountService.selectList(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.IS_AUTH, 1)
        );
        String url = WeixinConstants.WEIXIN_REFRESH_AUTHORIZER_TOKEN + "?component_access_token=" + systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN);
        for (UpWeixinAccount weixinAccount: weixinAccountList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("component_appid", weixinProperties.getAppid());
            jsonObject.put("authorizer_appid", weixinAccount.getAuthorizerAppid());
            jsonObject.put("authorizer_refresh_token", weixinAccount.getAuthorizerRefreshToken());
            int retryCount = 0;
            while (retryCount < 10) {
                HttpConnect httpConnect = HttpConnect.getInstance();
                JSONObject result = httpConnect.doPost(url, jsonObject);
                String authorizer_access_token = result.getString("authorizer_access_token");
                String authorizer_refresh_token = result.getString("authorizer_refresh_token");
                if (authorizer_access_token == null) {
                    retryCount++;
                    log.info("refresh authorizer token fail");
                    log.info("weixinAccount id: " + weixinAccount.getId());
                    log.info(result.toJSONString());
                    Thread.sleep(10 * 1000);
                } else {
                    weixinAccount.setAuthorizerAccessToken(authorizer_access_token);
                    weixinAccount.setAuthorizerRefreshToken(authorizer_refresh_token);
                    weixinAccount.setUpdateTime(new Date());
                    upWeixinAccountService.updateById(weixinAccount);
                    break;
                }
            }

        }

    }

    //endregion
}

