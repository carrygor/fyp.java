package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class EncryptForm extends BaseForm implements Serializable {

    private String signature;

    private String timestamp;

    private String nonce;

    private String encrypt_type;

    private String msg_signature;

    private String openid;

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

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEncrypt_type() {
        return encrypt_type;
    }

    public void setEncrypt_type(String encrypt_type) {
        this.encrypt_type = encrypt_type;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
