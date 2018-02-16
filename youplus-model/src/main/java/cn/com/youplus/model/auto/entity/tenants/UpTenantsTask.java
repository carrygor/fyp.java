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
 * 任务进度管理
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_tenants_task")
public class UpTenantsTask extends Model<UpTenantsTask> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
    /**
     * 任务类型
     */
	@TableField("task_type")
	private String taskType;
	private String status;
    /**
     * 总进度数
     */
	@TableField("total_num")
	private Integer totalNum;
    /**
     * 当前完成数量
     */
	@TableField("current_completed_num")
	private Integer currentCompletedNum;
    /**
     * 最后消息
     */
	@TableField("last_message")
	private String lastMessage;
    /**
     * 消息列表
     */
	@TableField("messages_json")
	private String messagesJson;
    /**
     * 如果有要下载的附件，则存储在这里。
     */
	private String download;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
    /**
     * 问卷id
     */
	@TableField("questionnaire_id")
	private Long questionnaireId;
    /**
     * 快速索引
     */
	@TableField("quick_tag")
	private String quickTag;
    /**
     * 网点索引
     */
	@TableField("region_filter")
	private String regionFilter;
	@Version
	private Integer version;
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;


	public Long getId() {
		return id;
	}

	public UpTenantsTask setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpTenantsTask setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getTaskType() {
		return taskType;
	}

	public UpTenantsTask setTaskType(String taskType) {
		this.taskType = taskType;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public UpTenantsTask setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public UpTenantsTask setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
		return this;
	}

	public Integer getCurrentCompletedNum() {
		return currentCompletedNum;
	}

	public UpTenantsTask setCurrentCompletedNum(Integer currentCompletedNum) {
		this.currentCompletedNum = currentCompletedNum;
		return this;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public UpTenantsTask setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
		return this;
	}

	public String getMessagesJson() {
		return messagesJson;
	}

	public UpTenantsTask setMessagesJson(String messagesJson) {
		this.messagesJson = messagesJson;
		return this;
	}

	public String getDownload() {
		return download;
	}

	public UpTenantsTask setDownload(String download) {
		this.download = download;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsTask setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsTask setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpTenantsTask setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public String getQuickTag() {
		return quickTag;
	}

	public UpTenantsTask setQuickTag(String quickTag) {
		this.quickTag = quickTag;
		return this;
	}

	public String getRegionFilter() {
		return regionFilter;
	}

	public UpTenantsTask setRegionFilter(String regionFilter) {
		this.regionFilter = regionFilter;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsTask setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsTask setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String TASK_TYPE = "task_type";

	public static final String STATUS = "status";

	public static final String TOTAL_NUM = "total_num";

	public static final String CURRENT_COMPLETED_NUM = "current_completed_num";

	public static final String LAST_MESSAGE = "last_message";

	public static final String MESSAGES_JSON = "messages_json";

	public static final String DOWNLOAD = "download";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String QUICK_TAG = "quick_tag";

	public static final String REGION_FILTER = "region_filter";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsTask{" +
			"id=" + id +
			", tenantsCompanyId=" + tenantsCompanyId +
			", taskType=" + taskType +
			", status=" + status +
			", totalNum=" + totalNum +
			", currentCompletedNum=" + currentCompletedNum +
			", lastMessage=" + lastMessage +
			", messagesJson=" + messagesJson +
			", download=" + download +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", questionnaireId=" + questionnaireId +
			", quickTag=" + quickTag +
			", regionFilter=" + regionFilter +
			", version=" + version +
			", logicDelete=" + logicDelete +
			"}";
	}
}
