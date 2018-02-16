package org.weixin4j;

public class WeixinConstants {
    public static String WEIXIN_FETCH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    public static String WEIXIN_FETCH_PRE_AUTH_CODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";
    public static String WEIXIN_QUERY_AUTH = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth";
    public static String WEIXIN_GET_AUTHORIZER_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info";
    public static String WEIXIN_REFRESH_AUTHORIZER_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token";
    public static String WEIXIN_TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send";
    public static String WEIXIN_OAUTH_USERINFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s&component_appid=%s#wechat_redirect";
    public static String WEIXIN_OAUTH_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s&component_appid=%s#wechat_redirect";
    public static String WEIXIN_CODE_TO_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s&component_access_token=%s";
    public static String WEIXIN_OAUTH2_CODE_TO_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    public static String WEIXIN_THIRD_PARTY_AUTH = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
    public static String WEIXIN_WEB_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

    /**
     * WEIXIN_刷新公众号的Accesstoken
     */
    static public final String WEIXIN_刷新公众号的_Accesstoken_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 获取微信的JSDK
     */
    static public final String WEIXIN_刷新公众号的_JSDK_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=%s";

    /**
     * 获取公众号AUTH2的授权
     */
    static public final String WEIXIN_OAUTH2授权_URL =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_%s&state=%s";


    /**
     * 公众号网页auth2 换取unionid
     */
    static public final String WEIXIN_公众号网页版获取信息_APPID_SECRET_CODE =  "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    static public final String WEIXIN_公众号网页版获取用户详细信息_ACCESS_TOKEN_OPENID =  "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";


    /**
     * 微信网页应用OAUTH
     * @param s1: APPID
     * @param s2: REDIRECT_URI
     * @param s3: SCOPE
     * @param s4: STATE
     */
    public static String WEIXIN_网页应用_OAUTH_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

}
