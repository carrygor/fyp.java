package cn.com.carry.common.model.resources;

/**
 * Created by 严汉羽 on 2017/10/19.
 */
public class SystemConfig {

    private String version;
    private String majorVersion;
    private String profile;
    private String apiUrl;
    private String webUrl;
    private String debug;
    private String bypassSecurityCheck;

    private String appsApi;
    private String tenantsApi;
    private String cmsApi;
    private String thirdpartyApi;
    private String appUrl;

    /**
     * 获取到版本号的API地址
     * @return
     */
    public String getApiUrlWithVersion() {
        return getApiUrl() + "/" + getMajorVersion();
    }

    public String getAppsApiUrlWithVersion() {
        return getAppsApi() + "/" + getMajorVersion();
    }

    public String getWebUrlWithVersion() {
        return getWebUrl() + "/" + getMajorVersion();
    }

    public String getTenantsApiUrlWithVersion() {
        return getTenantsApi() + "/tenants/" + getMajorVersion();
    }

    public String getCmsApiUrlWithVersion() {
        return getCmsApi() + "/" + getMajorVersion();
    }

    public String getThirdpartyApiUrlWithVersion() {
        return getTenantsApi() + "/" + getMajorVersion();
    }


    public String getAppUrl() {
        return appUrl;
    }

    public String getAppUrlWithVersion() {
        return appUrl + "/apps/" + getMajorVersion();
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppsApi() {
        return appsApi;
    }

    public void setAppsApi(String appsApi) {
        this.appsApi = appsApi;
    }

    public String getTenantsApi() {
        return tenantsApi;
    }

    public void setTenantsApi(String tenantsApi) {
        this.tenantsApi = tenantsApi;
    }

    public String getCmsApi() {
        return cmsApi;
    }

    public void setCmsApi(String cmsApi) {
        this.cmsApi = cmsApi;
    }

    public String getThirdpartyApi() {
        return thirdpartyApi;
    }

    public void setThirdpartyApi(String thirdpartyApi) {
        this.thirdpartyApi = thirdpartyApi;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getBypassSecurityCheck() {
        return bypassSecurityCheck;
    }

    public void setBypassSecurityCheck(String bypassSecurityCheck) {
        this.bypassSecurityCheck = bypassSecurityCheck;
    }
}
