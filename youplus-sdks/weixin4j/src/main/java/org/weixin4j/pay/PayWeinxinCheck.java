package org.weixin4j.pay;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class PayWeinxinCheck implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    private String appid;
    private String mch_id;
    private String out_trade_no;
    private String nonce_str;
    private String sign;
    private String sign_type = "MD5";

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("nonce_str", nonce_str);
        map.put("out_trade_no", out_trade_no);
        return map;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(appid).append("]]></appid>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]></mch_id>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]></nonce_str>");
        sb.append("<out_trade_no><![CDATA[").append(out_trade_no).append("]]></out_trade_no>");
        sb.append("<sign><![CDATA[").append(sign).append("]]></sign>");
        sb.append("</xml>");
        return sb.toString();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
