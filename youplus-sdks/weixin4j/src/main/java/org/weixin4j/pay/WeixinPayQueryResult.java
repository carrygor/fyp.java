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
public class WeixinPayQueryResult implements Serializable{

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

    //业务结果
    private String result_code;
    //错误代码
    private String err_code;
    //错误代码描述
    private String err_code_des;
    //商户订单号
    private String partnet_trade_no;
    //商户号
    private String mch_id;
    //付款单号
    private String detail_id;
    //转账状态
    private String status;
    //失败原因
    private String reason;
    //收款用户openid
    private String openid;
    //收款用户姓名
    private String transfer_name;
    //付款金额
    private int payment_amount;
    //转账时间
    private String transfer_time;
    //付款描述
    private String desc;


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("return_code", return_code);
        map.put("return_msg", return_msg);
        map.put("result_code", result_code);
        map.put("err_code", err_code);
        map.put("err_code_des", err_code_des);
        map.put("partnet_trade_no", partnet_trade_no);
        map.put("mchid", mch_id);
        map.put("detail_id", detail_id);
        map.put("status", status);
        map.put("reason", reason);
        map.put("openid", openid);
        map.put("transfer_name", transfer_name);
        map.put("payment_amount", payment_amount + "");
        map.put("transfer_time", transfer_time);
        map.put("desc", desc);
        return map;
    }

    public boolean isSendSucc() {
        return "SUCCESS".equals(return_code);
    }

    public boolean isPaySucc() {
        return "SUCCESS".equals(result_code);
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

    public String getPartnet_trade_no() {
        return partnet_trade_no;
    }

    @XmlElement(name = "partnet_trade_no")
    public void setPartnet_trade_no(String partnet_trade_no) {
        this.partnet_trade_no = partnet_trade_no;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getMch_id() {
        return mch_id;
    }

    @XmlElement(name = "mch_id")
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDetail_id() {
        return detail_id;
    }

    @XmlElement(name = "detail_id")
    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement(name = "status")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    @XmlElement(name = "reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenid() {
        return openid;
    }

    @XmlElement(name = "openid")
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTransfer_name() {
        return transfer_name;
    }

    @XmlElement(name = "transfer_name")
    public void setTransfer_name(String transfer_name) {
        this.transfer_name = transfer_name;
    }

    public int getPayment_amount() {
        return payment_amount;
    }

    @XmlElement(name = "payment_amount")
    public void setPayment_amount(int payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getTransfer_time() {
        return transfer_time;
    }

    @XmlElement(name = "transfer_time")
    public void setTransfer_time(String transfer_time) {
        this.transfer_time = transfer_time;
    }

    public String getDesc() {
        return desc;
    }

    @XmlElement(name = "desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
