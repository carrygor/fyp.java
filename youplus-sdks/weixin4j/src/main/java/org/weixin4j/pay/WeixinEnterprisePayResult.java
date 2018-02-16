package org.weixin4j.pay;

import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付结果检查
 *
 * @author qsyang
 */
@XmlRootElement(name = "xml")
public class WeixinEnterprisePayResult implements Serializable{

    /**
     * SUCCESS/FAIL
     *
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;     //返回状态码
    /**
     * 返回信息，如非空，为错误原因
     *
     * 签名失败
     *
     * 参数格式校验错误
     */
    private String return_msg;      //返回信息

    //商户appid
    private String mch_appid;
    //商户号
    private String mchid;
    //设备号
    private String device_info;
    //随机字符串
    private String nonce_str;
    //业务结果
    private String result_code;
    //错误代码
    private String err_code;
    //错误代码描述
    private String err_code_des;
    //商户订单号
    private String partnet_trade_no;
    //微信订单号
    private String payment_no;
    //微信支付成功时间
    private String payment_time;

    /**
     * 生成的订单号
     */
    private String genOrderNo;

    /**
     * 生成的body
     */
    private String genBody;

    /**
     * 插入的支付记录id
     */
    private Integer genPayRecordId;


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("return_code", return_code);
        map.put("return_msg", return_msg);
        map.put("mch_appid", mch_appid);
        map.put("mchid", mchid);
        map.put("device_info", device_info);
        map.put("nonce_str", nonce_str);
        map.put("result_code", result_code);
        map.put("err_code", err_code);
        map.put("err_code_des", err_code_des);
        map.put("partnet_trade_no", partnet_trade_no);
        map.put("payment_no", payment_no);
        map.put("payment_time", payment_time);
        return map;
    }

    public Integer getGenPayRecordId() {
        return genPayRecordId;
    }

    public void setGenPayRecordId(Integer genPayRecordId) {
        this.genPayRecordId = genPayRecordId;
    }

    public boolean isSendSucc() {
        return "SUCCESS".equals(return_code);
    }

    public boolean isPaySucc() {
        return "SUCCESS".equals(result_code);
    }

    public String getGenOrderNo() {
        return genOrderNo;
    }

    public void setGenOrderNo(String genOrderNo) {
        this.genOrderNo = genOrderNo;
    }

    public String getGenBody() {
        return genBody;
    }

    public void setGenBody(String genBody) {
        this.genBody = genBody;
    }

    public String getReturn_code() {
        return return_code;
    }

    @XmlElement(name = "return_code")
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    @XmlElement(name = "return_msg")
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getDevice_info() {
        return device_info;
    }

    @XmlElement(name = "device_info")
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    @XmlElement(name = "nonce_str")
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getResult_code() {
        return result_code;
    }

    @XmlElement(name = "result_code")
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    @XmlElement(name = "err_code")
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    @XmlElement(name = "err_code_des")
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getMch_appid() {
        return mch_appid;
    }

    @XmlElement(name = "mch_appid")
    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    @XmlElement(name = "mchid")
    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getPartnet_trade_no() {
        return partnet_trade_no;
    }

    @XmlElement(name = "partnet_trade_no")
    public void setPartnet_trade_no(String partnet_trade_no) {
        this.partnet_trade_no = partnet_trade_no;
    }

    public String getPayment_no() {
        return payment_no;
    }

    @XmlElement(name = "payment_no")
    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getPayment_time() {
        return payment_time;
    }

    @XmlElement(name = "payment_time")
    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
