package cn.com.youplus.model.auto.entity.notification;

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
@TableName("up_notification_weixin_template_record")
public class UpNotificationWeixinTemplateRecord extends Model<UpNotificationWeixinTemplateRecord> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * appid
     */
	private String appid;
    /**
     * 用户微信id
     */
	@TableField("to_user")
	private String toUser;
    /**
     * content_json
     */
	@TableField("content_json")
	private String contentJson;
    /**
     * 模板id
     */
	@TableField("template_id")
	private String templateId;
    /**
     * result_code
     */
	@TableField("result_code")
	private String resultCode;
    /**
     * result_json
     */
	@TableField("result_json")
	private String resultJson;
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

	public UpNotificationWeixinTemplateRecord setId(Long id) {
		this.id = id;
		return this;
	}

	public String getAppid() {
		return appid;
	}

	public UpNotificationWeixinTemplateRecord setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getToUser() {
		return toUser;
	}

	public UpNotificationWeixinTemplateRecord setToUser(String toUser) {
		this.toUser = toUser;
		return this;
	}

	public String getContentJson() {
		return contentJson;
	}

	public UpNotificationWeixinTemplateRecord setContentJson(String contentJson) {
		this.contentJson = contentJson;
		return this;
	}

	public String getTemplateId() {
		return templateId;
	}

	public UpNotificationWeixinTemplateRecord setTemplateId(String templateId) {
		this.templateId = templateId;
		return this;
	}

	public String getResultCode() {
		return resultCode;
	}

	public UpNotificationWeixinTemplateRecord setResultCode(String resultCode) {
		this.resultCode = resultCode;
		return this;
	}

	public String getResultJson() {
		return resultJson;
	}

	public UpNotificationWeixinTemplateRecord setResultJson(String resultJson) {
		this.resultJson = resultJson;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpNotificationWeixinTemplateRecord setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpNotificationWeixinTemplateRecord setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpNotificationWeixinTemplateRecord setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpNotificationWeixinTemplateRecord setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String APPID = "appid";

	public static final String TO_USER = "to_user";

	public static final String CONTENT_JSON = "content_json";

	public static final String TEMPLATE_ID = "template_id";

	public static final String RESULT_CODE = "result_code";

	public static final String RESULT_JSON = "result_json";

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
		return "UpNotificationWeixinTemplateRecord{" +
			"id=" + id +
			", appid=" + appid +
			", toUser=" + toUser +
			", contentJson=" + contentJson +
			", templateId=" + templateId +
			", resultCode=" + resultCode +
			", resultJson=" + resultJson +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
