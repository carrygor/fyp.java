package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class UpEncryptForm extends BaseForm implements Serializable {

    @Check(notNull = true)
    private String signature;

    @Check(notNull = true)
    private String timestamp;

    @Check(notNull = true)
    private String api_key;

    @Check(notNull = true)
    private String openid;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
