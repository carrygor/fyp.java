package cn.com.youplus.model.auto.entity.notification;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_notification_sms_record")
public class UpNotificationSmsRecord extends Model<UpNotificationSmsRecord> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * 调用的用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 用户类型
     */
	@TableField("user_type")
	private String userType;
    /**
     * 消息的模板id
     */
	@TableField("template_id")
	private String templateId;
    /**
     * 发送的验证码
     */
	private String code;
    /**
     * 短信的类型
     */
	@TableField("sms_type")
	private String smsType;
    /**
     * 发送的参数
     */
	@TableField("send_json")
	private String sendJson;
    /**
     * 发送的结果
     */
	private String result;
    /**
     * 消费结果，当消息为验证码的时候使用。
     */
	@TableField("is_consume")
	private Integer isConsume;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
    /**
     * 阿里云的任务id，用于反馈消费结果
     */
	@TableField("job_id")
	private String jobId;
    /**
     * 需要发的手机号码
     */
	private String phone;
	private String cookie;
	private String openid;
	private String ip;
	@TableField("company_id")
	private Long companyId;
	@TableField("questionnaire_id")
	private Long questionnaireId;
    /**
     * 日期
     */
	private String day;


	public Long getId() {
		return id;
	}

	public UpNotificationSmsRecord setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public UpNotificationSmsRecord setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getUserType() {
		return userType;
	}

	public UpNotificationSmsRecord setUserType(String userType) {
		this.userType = userType;
		return this;
	}

	public String getTemplateId() {
		return templateId;
	}

	public UpNotificationSmsRecord setTemplateId(String templateId) {
		this.templateId = templateId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public UpNotificationSmsRecord setCode(String code) {
		this.code = code;
		return this;
	}

	public String getSmsType() {
		return smsType;
	}

	public UpNotificationSmsRecord setSmsType(String smsType) {
		this.smsType = smsType;
		return this;
	}

	public String getSendJson() {
		return sendJson;
	}

	public UpNotificationSmsRecord setSendJson(String sendJson) {
		this.sendJson = sendJson;
		return this;
	}

	public String getResult() {
		return result;
	}

	public UpNotificationSmsRecord setResult(String result) {
		this.result = result;
		return this;
	}

	public Integer getIsConsume() {
		return isConsume;
	}

	public UpNotificationSmsRecord setIsConsume(Integer isConsume) {
		this.isConsume = isConsume;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpNotificationSmsRecord setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpNotificationSmsRecord setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getJobId() {
		return jobId;
	}

	public UpNotificationSmsRecord setJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UpNotificationSmsRecord setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getCookie() {
		return cookie;
	}

	public UpNotificationSmsRecord setCookie(String cookie) {
		this.cookie = cookie;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public UpNotificationSmsRecord setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public UpNotificationSmsRecord setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public UpNotificationSmsRecord setCompanyId(Long companyId) {
		this.companyId = companyId;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpNotificationSmsRecord setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public String getDay() {
		return day;
	}

	public UpNotificationSmsRecord setDay(String day) {
		this.day = day;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String USER_TYPE = "user_type";

	public static final String TEMPLATE_ID = "template_id";

	public static final String CODE = "code";

	public static final String SMS_TYPE = "sms_type";

	public static final String SEND_JSON = "send_json";

	public static final String RESULT = "result";

	public static final String IS_CONSUME = "is_consume";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String JOB_ID = "job_id";

	public static final String PHONE = "phone";

	public static final String COOKIE = "cookie";

	public static final String OPENID = "openid";

	public static final String IP = "ip";

	public static final String COMPANY_ID = "company_id";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String DAY = "day";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpNotificationSmsRecord{" +
			"id=" + id +
			", userId=" + userId +
			", userType=" + userType +
			", templateId=" + templateId +
			", code=" + code +
			", smsType=" + smsType +
			", sendJson=" + sendJson +
			", result=" + result +
			", isConsume=" + isConsume +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", jobId=" + jobId +
			", phone=" + phone +
			", cookie=" + cookie +
			", openid=" + openid +
			", ip=" + ip +
			", companyId=" + companyId +
			", questionnaireId=" + questionnaireId +
			", day=" + day +
			"}";
	}
}
