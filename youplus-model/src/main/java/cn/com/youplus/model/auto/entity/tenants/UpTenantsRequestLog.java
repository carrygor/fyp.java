package cn.com.youplus.model.auto.entity.tenants;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_tenants_request_log")
public class UpTenantsRequestLog extends Model<UpTenantsRequestLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 租户id
     */
	@TableField("tenants_id")
	private Long tenantsId;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
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
     * 结果
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
    /**
     * 乐观锁
     */
	@Version
	private Integer version;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public UpTenantsRequestLog setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getTenantsId() {
		return tenantsId;
	}

	public UpTenantsRequestLog setTenantsId(Long tenantsId) {
		this.tenantsId = tenantsId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public UpTenantsRequestLog setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public UpTenantsRequestLog setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public UpTenantsRequestLog setRequestUri(String requestUri) {
		this.requestUri = requestUri;
		return this;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public UpTenantsRequestLog setPermissionName(String permissionName) {
		this.permissionName = permissionName;
		return this;
	}

	public Integer getTimeConsume() {
		return timeConsume;
	}

	public UpTenantsRequestLog setTimeConsume(Integer timeConsume) {
		this.timeConsume = timeConsume;
		return this;
	}

	public String getResult() {
		return result;
	}

	public UpTenantsRequestLog setResult(String result) {
		this.result = result;
		return this;
	}

	public String getRequestParameter() {
		return requestParameter;
	}

	public UpTenantsRequestLog setRequestParameter(String requestParameter) {
		this.requestParameter = requestParameter;
		return this;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public UpTenantsRequestLog setResponseContent(String responseContent) {
		this.responseContent = responseContent;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsRequestLog setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsRequestLog setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsRequestLog setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsRequestLog setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_ID = "tenants_id";

	public static final String USER_ID = "user_id";

	public static final String IP = "ip";

	public static final String REQUEST_URI = "request_uri";

	public static final String PERMISSION_NAME = "permission_name";

	public static final String TIME_CONSUME = "time_consume";

	public static final String RESULT = "result";

	public static final String REQUEST_PARAMETER = "request_parameter";

	public static final String RESPONSE_CONTENT = "response_content";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsRequestLog{" +
			"id=" + id +
			", tenantsId=" + tenantsId +
			", userId=" + userId +
			", ip=" + ip +
			", requestUri=" + requestUri +
			", permissionName=" + permissionName +
			", timeConsume=" + timeConsume +
			", result=" + result +
			", requestParameter=" + requestParameter +
			", responseContent=" + responseContent +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
