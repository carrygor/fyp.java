package org.weixin4j.pay;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class WeixinPayQuery implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    //商户账号appid
    private String appid;
    //商户号
    private String mch_id;
    //随机字符串
    private String nonce_str;
    //签名
    private String sign;
    //商户订单号
    private String partner_trade_no;


    private String sign_type = "MD5";

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("nonce_str", nonce_str);
        map.put("partner_trade_no", partner_trade_no);
        return map;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<sign><![CDATA[").append(sign).append("]]></sign>");
        sb.append("<partner_trade_no><![CDATA[").append(partner_trade_no).append("]]></partner_trade_no>");
        sb.append("<mch_id><![CDATA[").append(mch_id).append("]]></mch_id>");
        sb.append("<appid><![CDATA[").append(appid).append("]]></appid>");
        sb.append("<nonce_str><![CDATA[").append(nonce_str).append("]]></nonce_str>");
        sb.append("</xml>");
        return sb.toString();
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

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
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
