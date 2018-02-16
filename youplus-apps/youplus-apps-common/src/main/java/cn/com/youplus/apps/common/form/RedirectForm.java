package cn.com.youplus.apps.common.form;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class RedirectForm extends WeixinOauthForm implements Serializable {

    private String code;

    private String state;

    private String appid;

    public String getCode() {
    return code;
    }

    public void setCode(String code) {
    this.code = code;
    }

    public String getState() {
    return state;
    }

    public void setState(String state) {
    this.state = state;
    }

    public String getAppid() {
    return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
