package cn.com.youplus.model.auto.entity.notification;

import java.io.Serializable;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("up_notification_request_log")
public class UpNotificationRequestLog extends Model<UpNotificationRequestLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 客户id
     */
	private String cid;
    /**
     * ip地址
     */
	private String ip;
    /**
     * 请求uri
     */
	@TableField("request_uri")
	private String requestUri;
    /**
     * 模块id
     */
	@TableField("module_name")
	private String moduleName;
    /**
     * 处理时长
     */
	@TableField("time_consume")
	private Integer timeConsume;
    /**
     * 处理结果
     */
	private String result;
    /**
     * 如果超长会非截断
     */
	@TableField("request_parameter")
	private String requestParameter;
    /**
     * 如果超长会给截断
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

	public UpNotificationRequestLog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCid() {
		return cid;
	}

	public UpNotificationRequestLog setCid(String cid) {
		this.cid = cid;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public UpNotificationRequestLog setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public UpNotificationRequestLog setRequestUri(String requestUri) {
		this.requestUri = requestUri;
		return this;
	}

	public String getModuleName() {
		return moduleName;
	}

	public UpNotificationRequestLog setModuleName(String moduleName) {
		this.moduleName = moduleName;
		return this;
	}

	public Integer getTimeConsume() {
		return timeConsume;
	}

	public UpNotificationRequestLog setTimeConsume(Integer timeConsume) {
		this.timeConsume = timeConsume;
		return this;
	}

	public String getResult() {
		return result;
	}

	public UpNotificationRequestLog setResult(String result) {
		this.result = result;
		return this;
	}

	public String getRequestParameter() {
		return requestParameter;
	}

	public UpNotificationRequestLog setRequestParameter(String requestParameter) {
		this.requestParameter = requestParameter;
		return this;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public UpNotificationRequestLog setResponseContent(String responseContent) {
		this.responseContent = responseContent;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpNotificationRequestLog setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpNotificationRequestLog setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String CID = "cid";

	public static final String IP = "ip";

	public static final String REQUEST_URI = "request_uri";

	public static final String MODULE_NAME = "module_name";

	public static final String TIME_CONSUME = "time_consume";

	public static final String RESULT = "result";

	public static final String REQUEST_PARAMETER = "request_parameter";

	public static final String RESPONSE_CONTENT = "response_content";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpNotificationRequestLog{" +
			"id=" + id +
			", cid=" + cid +
			", ip=" + ip +
			", requestUri=" + requestUri +
			", moduleName=" + moduleName +
			", timeConsume=" + timeConsume +
			", result=" + result +
			", requestParameter=" + requestParameter +
			", responseContent=" + responseContent +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
