package cn.com.youplus.base.api;

/**
 * Created by 严汉羽 on 2017/10/27.
 */
public interface SystemConfigService {

    //region TENANTS_LEY
    String TENANTS_WELCOME_KEY = "TENANTS_WELCOME_KEY";

    String TENANTS_DEFAULT_PASSWORD = "TENANTS_DEFAULT_PASSWORD";
    //endregion

    //region THIRDPARTY key

    String THIRDPARTY_WEIXIN_COMPONENT_VERIFY_TICKET = "THIRDPARTY_WEIXIN_COMPONENT_VERIFY_TICKET";
    String THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN = "THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN";

    //微信公众号
    String THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_ACCESS_TOKEN = "THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_ACCESS_TOKEN";
    String THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_JSDK_TICKET = "THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_JSDK_TICKET";
    String THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_EXPIRE_TIME = "THIRDPARTY_WEIXIN_OFFICIAL_ACCOUNT_EXPIRE_TIME";

    //endregion

    String getCmsParam(String key) throws Exception;

    String getAppsParam(String key) throws Exception;

    String getTenantsParam(String key) throws Exception;

    String getThirdpartyParam(String key) throws Exception;
}
