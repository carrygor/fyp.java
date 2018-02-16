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
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_weixin_request_log")
public class UpWeixinRequestLog extends Model<UpWeixinRequestLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 微信账户的appid
     */
	private String appid;
    /**
     * 微信用户的openid
     */
	private String openid;
    /**
     * 请求类型
     */
	@TableField("request_type")
	private String requestType;
    /**
     * 请求事件的key
     */
	@TableField("enevnt_key")
	private String enevntKey;
    /**
     * 请求整体
     */
	@TableField("request_content")
	private String requestContent;
    /**
     * 回复类型
     */
	@TableField("response_type")
	private String responseType;
    /**
     * 回复内容
     */
	@TableField("response_content")
	private String responseContent;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public UpWeixinRequestLog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getAppid() {
		return appid;
	}

	public UpWeixinRequestLog setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public UpWeixinRequestLog setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getRequestType() {
		return requestType;
	}

	public UpWeixinRequestLog setRequestType(String requestType) {
		this.requestType = requestType;
		return this;
	}

	public String getEnevntKey() {
		return enevntKey;
	}

	public UpWeixinRequestLog setEnevntKey(String enevntKey) {
		this.enevntKey = enevntKey;
		return this;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public UpWeixinRequestLog setRequestContent(String requestContent) {
		this.requestContent = requestContent;
		return this;
	}

	public String getResponseType() {
		return responseType;
	}

	public UpWeixinRequestLog setResponseType(String responseType) {
		this.responseType = responseType;
		return this;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public UpWeixinRequestLog setResponseContent(String responseContent) {
		this.responseContent = responseContent;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpWeixinRequestLog setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpWeixinRequestLog setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String APPID = "appid";

	public static final String OPENID = "openid";

	public static final String REQUEST_TYPE = "request_type";

	public static final String ENEVNT_KEY = "enevnt_key";

	public static final String REQUEST_CONTENT = "request_content";

	public static final String RESPONSE_TYPE = "response_type";

	public static final String RESPONSE_CONTENT = "response_content";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpWeixinRequestLog{" +
			"id=" + id +
			", appid=" + appid +
			", openid=" + openid +
			", requestType=" + requestType +
			", enevntKey=" + enevntKey +
			", requestContent=" + requestContent +
			", responseType=" + responseType +
			", responseContent=" + responseContent +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
