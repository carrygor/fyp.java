package cn.com.youplus.model.auto.entity.cms;

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
@TableName("up_cms_request_log")
public class UpCmsRequestLog extends Model<UpCmsRequestLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * cid
     */
	private Long cid;
    /**
     * ip
     */
	private String ip;
    /**
     * request_uri
     */
	@TableField("request_uri")
	private String requestUri;
    /**
     * permission_name
     */
	@TableField("permission_name")
	private String permissionName;
    /**
     * time_consume
     */
	@TableField("time_consume")
	private Integer timeConsume;
    /**
     * result
     */
	private String result;
    /**
     * request_parameter
     */
	@TableField("request_parameter")
	private String requestParameter;
    /**
     * response_content
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

	public UpCmsRequestLog setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getCid() {
		return cid;
	}

	public UpCmsRequestLog setCid(Long cid) {
		this.cid = cid;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public UpCmsRequestLog setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public UpCmsRequestLog setRequestUri(String requestUri) {
		this.requestUri = requestUri;
		return this;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public UpCmsRequestLog setPermissionName(String permissionName) {
		this.permissionName = permissionName;
		return this;
	}

	public Integer getTimeConsume() {
		return timeConsume;
	}

	public UpCmsRequestLog setTimeConsume(Integer timeConsume) {
		this.timeConsume = timeConsume;
		return this;
	}

	public String getResult() {
		return result;
	}

	public UpCmsRequestLog setResult(String result) {
		this.result = result;
		return this;
	}

	public String getRequestParameter() {
		return requestParameter;
	}

	public UpCmsRequestLog setRequestParameter(String requestParameter) {
		this.requestParameter = requestParameter;
		return this;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public UpCmsRequestLog setResponseContent(String responseContent) {
		this.responseContent = responseContent;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpCmsRequestLog setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpCmsRequestLog setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String CID = "cid";

	public static final String IP = "ip";

	public static final String REQUEST_URI = "request_uri";

	public static final String PERMISSION_NAME = "permission_name";

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
		return "UpCmsRequestLog{" +
			"id=" + id +
			", cid=" + cid +
			", ip=" + ip +
			", requestUri=" + requestUri +
			", permissionName=" + permissionName +
			", timeConsume=" + timeConsume +
			", result=" + result +
			", requestParameter=" + requestParameter +
			", responseContent=" + responseContent +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
