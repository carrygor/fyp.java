package cn.com.youplus.apps.server.controller;

import cn.com.youplus.apps.api.auto.UpAppsQuestionItemService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireThemeService;
import cn.com.youplus.apps.common.form.RedirectForm;
import cn.com.youplus.apps.common.form.WeixinOauthForm;
import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.*;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apps/v1/weixin")
public class WeixinController extends SuperController{

    Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Autowired
    private WeixinProperties weixinProperties;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    @GetMapping("/oauth")
    public void WeixinOauth(@Valid WeixinOauthForm form) throws Exception {
        String scope = form.getScope();
        String router = form.getRouter();
        String questionnaireId = form.getQuestionnaireId();
        Long regionId = form.getRegionId();
        if (ValueHelper.isNone(scope)) {
            scope = "base";
        }

        UpWeixinAccount weixinAccount = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.TENANTS_COMPANY_ID, form.getCompanyId())
                        .eq(UpWeixinAccount.IS_AUTH, 1)
        );

        if (RequestUtil.isWeixinBrowser(request)) {
            String url;
//            String baseUrl = systemConfig.getAppsApiUrlWithVersion() + "/weixin/redirect?router=" + router;
            String baseUrl = "http://thirdparty.shenmiren.com.cn/apps/v1/weixin/redirect?router=" + router;
            baseUrl += "&questionnaireId=" + questionnaireId;
            baseUrl += "&regionId=" + regionId;

            if (scope.equals("userInfo")) {
                url = String.format(WeixinConstants.WEIXIN_OAUTH_USERINFO_URL,
                        weixinAccount.getAuthorizerAppid(), URLEncoder.encode(baseUrl, "utf-8"), router, weixinProperties.getAppid()
                );
            } else {
                url = String.format(WeixinConstants.WEIXIN_OAUTH_BASE_URL,
                        weixinAccount.getAuthorizerAppid(), URLEncoder.encode(baseUrl, "utf-8"), router, weixinProperties.getAppid()
                );
            }

            response.sendRedirect(url);
        } else {
            throw new AlertException("请在微信浏览器上浏览");
        }

    }

    @GetMapping("/redirect")
    public void WeixinRedirect(RedirectForm form) throws Exception {

        logger.info("get oauth redirect");
        String code = form.getCode();
        String router = form.getRouter();
        String appid = form.getAppid();
        String questionnaireId = form.getQuestionnaireId();
        Long companyId = form.getCompanyId();
        Long regionId = form.getRegionId();

        String accessTokenUrl = String.format(WeixinConstants.WEIXIN_CODE_TO_ACCESS_TOKEN,
                appid, code, weixinProperties.getAppid(), systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN)
        );
        logger.info(accessTokenUrl);
        String tokenResult = HttpConnect.getInstance().doGet(accessTokenUrl, null);
        JSONObject tokenJson = JSONObject.parseObject(tokenResult);
        logger.info(tokenResult);
        String access_token = tokenJson.getString("access_token");
        String openid = tokenJson.getString("openid");
        //先只处理静默授权
        router = router.replace("_", "/");
        CookieUtil.setCookie(response, "openid", openid, Constants.COOKIE_AGE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questionnaireId", questionnaireId);
        jsonObject.put("regionId", regionId);
        jsonObject.put("companyId", companyId);
        jsonObject.put("openid", openid);
        String url = systemConfig.getAppUrl() + "/#/welcome/" + jsonObject.toJSONString();
        response.sendRedirect(url);
    }

}
