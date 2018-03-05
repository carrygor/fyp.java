package cn.com.carry.common.model.base;

import cn.com.carry.common.model.enums.SmsTypeEnum;
import cn.com.carry.common.model.enums.UserTypeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public class SmsParams implements Serializable {

    /**
     * 短信的模板号
     */
    private String templateId;

    /**
     * 签名，默认为 阿里云短信测试专用
     */
    private  String sign = "阿里云短信测试专用";

    /**
     * 要发送的手机号
     */
    private  String phone;

    private UserTypeEnum userType;
    private SmsTypeEnum type;

    private Long userId;

    private String cookie;

    private String openid;

    private String ip;

    private Long companyId;

    private Long questionnaireId;

    private String day;

    public String getCookie() {
        return cookie;
    }

    public SmsParams setCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public SmsParams setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public SmsParams setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public SmsParams setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public SmsParams setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

    public String getDay() {
        return day;
    }

    public SmsParams setDay(String day) {
        this.day = day;
        return this;
    }

    public SmsTypeEnum getType() {
        return type;
    }

    public SmsParams setType(SmsTypeEnum type) {
        this.type = type;
        return this;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public SmsParams setUserType(UserTypeEnum userType) {
        this.userType = userType;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SmsParams setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 详细参数，直接使用，无需初始化
     */
    private Map<String, String> data = new HashMap<>();

    public String getTemplateId() {
        return templateId;
    }

    public SmsParams setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public SmsParams setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SmsParams setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Map<String, String> getData() {
        return data;
    }

    public SmsParams setData(Map<String, String> data) {
        this.data = data;
        return this;
    }

    public Map<String, String> appendData(String key, String value) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put(key, value);
        return this.data;
    }
}
