package org.weixin4j.pay;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class WeixinEnterprisePay implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    //商户账号appid
    private String mch_appid;
    //商户号
    private String mchid;
    //随机字符串
    private String nonce_str;
    //签名
    private String sign;
    //商户订单号
    private String partner_trade_no;
    //用户openid
    private String openid;
    //校验用户姓名选项
    private String check_name;
    //收款用户姓名
    private String re_user_name;
    //金额
    private int amount;
    //企业付款描述信息
    private String desc;
    //Ip地址
    private String spbill_create_ip;


    private String sign_type = "MD5";

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mch_appid", mch_appid);
        map.put("mchid", mchid);
        map.put("nonce_str", nonce_str);
        map.put("partner_trade_no", partner_trade_no);
        map.put("openid", openid);
        map.put("check_name", check_name);
        map.put("re_user_name", re_user_name);
        map.put("amount", amount + "");
        map.put("desc", desc);
        map.put("spbill_create_ip", spbill_create_ip);
        return map;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<mch_appid>").append(mch_appid).append("</mch_appid>");
        sb.append("<mchid>").append(mchid).append("</mchid>");
        sb.append("<nonce_str>").append(nonce_str).append("</nonce_str>");
        sb.append("<sign>").append(sign).append("</sign>");
        sb.append("<partner_trade_no>").append(partner_trade_no).append("</partner_trade_no>");
        sb.append("<openid>").append(openid).append("</openid>");
        sb.append("<check_name>").append(check_name).append("</check_name>");
        sb.append("<re_user_name>").append(re_user_name).append("</re_user_name>");
        sb.append("<amount>").append(amount).append("</amount>");
        sb.append("<desc>").append(desc).append("</desc>");
        sb.append("<spbill_create_ip>").append(spbill_create_ip).append("</spbill_create_ip>");
        sb.append("</xml>");
        return sb.toString();
    }

    public String getMch_appid() {
        return mch_appid;
    }

    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
