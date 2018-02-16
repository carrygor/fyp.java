package cn.com.youplus.model.auto.entity.apps;

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
@TableName("up_apps_phone_list")
public class UpAppsPhoneList extends Model<UpAppsPhoneList> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 电话号码
     */
	@TableField("phone_num")
	private String phoneNum;
    /**
     * 名单类型
     */
	private String type;
    /**
     * 问卷id
     */
	@TableField("questionnaire_id")
	private Long questionnaireId;
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
	@Version
	private Integer version;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public UpAppsPhoneList setId(Long id) {
		this.id = id;
		return this;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public UpAppsPhoneList setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}

	public String getType() {
		return type;
	}

	public UpAppsPhoneList setType(String type) {
		this.type = type;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpAppsPhoneList setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsPhoneList setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsPhoneList setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsPhoneList setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsPhoneList setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String PHONE_NUM = "phone_num";

	public static final String TYPE = "type";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String VERSION = "version";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsPhoneList{" +
			"id=" + id +
			", phoneNum=" + phoneNum +
			", type=" + type +
			", questionnaireId=" + questionnaireId +
			", logicDelete=" + logicDelete +
			", version=" + version +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
