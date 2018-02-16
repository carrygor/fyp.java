package cn.com.youplus.common.model.resources;

public class WeixinProperties {
    private String token;
    private String appid;
    private String appsecret;
    private String encodingAesKey;

    private String webappId;
    private String webappSecret;

    private String officialAccountsAppId;
    private String officialAccountsOriginId;
    private String officialAccountsAppSecret;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public String getWebappId() {
        return webappId;
    }

    public WeixinProperties setWebappId(String webappId) {
        this.webappId = webappId;
        return this;
    }

    public String getWebappSecret() {
        return webappSecret;
    }

    public WeixinProperties setWebappSecret(String webappSecret) {
        this.webappSecret = webappSecret;
        return this;
    }

    public String getOfficialAccountsAppId() {
        return officialAccountsAppId;
    }

    public WeixinProperties setOfficialAccountsAppId(String officialAccountsAppId) {
        this.officialAccountsAppId = officialAccountsAppId;
        return this;
    }

    public String getOfficialAccountsAppSecret() {
        return officialAccountsAppSecret;
    }

    public WeixinProperties setOfficialAccountsAppSecret(String officialAccountsAppSecret) {
        this.officialAccountsAppSecret = officialAccountsAppSecret;
        return this;
    }

    public String getOfficialAccountsOriginId() {
        return officialAccountsOriginId;
    }

    public WeixinProperties setOfficialAccountsOriginId(String officialAccountsOriginId) {
        this.officialAccountsOriginId = officialAccountsOriginId;
        return this;
    }
}
