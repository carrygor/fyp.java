package cn.com.youplus.model.auto.entity.thirdparty;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 微信时间推送
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_weixin_message_log")
public class UpWeixinMessageLog extends Model<UpWeixinMessageLog> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 第三方平台appid
     */
	private String appid;
    /**
     * 发送时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * unauthorized是取消授权，updateauthorized是更新授权，authorized是授权成功通知
     */
	@TableField("info_type")
	private String infoType;
    /**
     * 公众号或小程序appid
     */
	@TableField("authorizer_appid")
	private String authorizerAppid;
    /**
     * 授权码
     */
	@TableField("authorization_code")
	private String authorizationCode;
    /**
     * 授权码过期时间
     */
	@TableField("authorization_code_expired_time")
	private String authorizationCodeExpiredTime;
    /**
     * 预授权码
     */
	@TableField("pre_auth_code")
	private String preAuthCode;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
    /**
     * Ticket内容

     */
	@TableField("component_verify_ticket")
	private String componentVerifyTicket;


	public Long getId() {
		return id;
	}

	public UpWeixinMessageLog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getAppid() {
		return appid;
	}

	public UpWeixinMessageLog setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public UpWeixinMessageLog setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getInfoType() {
		return infoType;
	}

	public UpWeixinMessageLog setInfoType(String infoType) {
		this.infoType = infoType;
		return this;
	}

	public String getAuthorizerAppid() {
		return authorizerAppid;
	}

	public UpWeixinMessageLog setAuthorizerAppid(String authorizerAppid) {
		this.authorizerAppid = authorizerAppid;
		return this;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public UpWeixinMessageLog setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
		return this;
	}

	public String getAuthorizationCodeExpiredTime() {
		return authorizationCodeExpiredTime;
	}

	public UpWeixinMessageLog setAuthorizationCodeExpiredTime(String authorizationCodeExpiredTime) {
		this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
		return this;
	}

	public String getPreAuthCode() {
		return preAuthCode;
	}

	public UpWeixinMessageLog setPreAuthCode(String preAuthCode) {
		this.preAuthCode = preAuthCode;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpWeixinMessageLog setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpWeixinMessageLog setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getComponentVerifyTicket() {
		return componentVerifyTicket;
	}

	public UpWeixinMessageLog setComponentVerifyTicket(String componentVerifyTicket) {
		this.componentVerifyTicket = componentVerifyTicket;
		return this;
	}

	public static final String ID = "id";

	public static final String APPID = "appid";

	public static final String CREATE_TIME = "create_time";

	public static final String INFO_TYPE = "info_type";

	public static final String AUTHORIZER_APPID = "authorizer_appid";

	public static final String AUTHORIZATION_CODE = "authorization_code";

	public static final String AUTHORIZATION_CODE_EXPIRED_TIME = "authorization_code_expired_time";

	public static final String PRE_AUTH_CODE = "pre_auth_code";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String COMPONENT_VERIFY_TICKET = "component_verify_ticket";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpWeixinMessageLog{" +
			"id=" + id +
			", appid=" + appid +
			", createTime=" + createTime +
			", infoType=" + infoType +
			", authorizerAppid=" + authorizerAppid +
			", authorizationCode=" + authorizationCode +
			", authorizationCodeExpiredTime=" + authorizationCodeExpiredTime +
			", preAuthCode=" + preAuthCode +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", componentVerifyTicket=" + componentVerifyTicket +
			"}";
	}
}
