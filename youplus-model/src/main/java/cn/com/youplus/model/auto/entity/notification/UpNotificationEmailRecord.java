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
@TableName("up_notification_email_record")
public class UpNotificationEmailRecord extends Model<UpNotificationEmailRecord> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 邮件类型
     */
	@TableField("mail_type")
	private String mailType;
    /**
     * 接受者
     */
	@TableField("to_user")
	private String toUser;
    /**
     * 邮件标题
     */
	private String title;
    /**
     * 邮件内容
     */
	private String content;
    /**
     * 发送结果
     */
	private String result;
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

	public UpNotificationEmailRecord setId(Long id) {
		this.id = id;
		return this;
	}

	public String getMailType() {
		return mailType;
	}

	public UpNotificationEmailRecord setMailType(String mailType) {
		this.mailType = mailType;
		return this;
	}

	public String getToUser() {
		return toUser;
	}

	public UpNotificationEmailRecord setToUser(String toUser) {
		this.toUser = toUser;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public UpNotificationEmailRecord setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public UpNotificationEmailRecord setContent(String content) {
		this.content = content;
		return this;
	}

	public String getResult() {
		return result;
	}

	public UpNotificationEmailRecord setResult(String result) {
		this.result = result;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpNotificationEmailRecord setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpNotificationEmailRecord setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpNotificationEmailRecord setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpNotificationEmailRecord setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String MAIL_TYPE = "mail_type";

	public static final String TO_USER = "to_user";

	public static final String TITLE = "title";

	public static final String CONTENT = "content";

	public static final String RESULT = "result";

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
		return "UpNotificationEmailRecord{" +
			"id=" + id +
			", mailType=" + mailType +
			", toUser=" + toUser +
			", title=" + title +
			", content=" + content +
			", result=" + result +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
