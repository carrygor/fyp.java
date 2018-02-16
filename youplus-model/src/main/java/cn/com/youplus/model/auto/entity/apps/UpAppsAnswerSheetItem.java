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
@TableName("up_apps_answer_sheet_item")
public class UpAppsAnswerSheetItem extends Model<UpAppsAnswerSheetItem> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 问卷id
     */
	@TableField("answer_sheet_id")
	private Long answerSheetId;
    /**
     * 题目id
     */
	@TableField("question_id")
	private Long questionId;
    /**
     * 选项id
     */
	@TableField("question_item_id")
	private Long questionItemId;
	private String value;
    /**
     * 输入的内容
     */
	@TableField("input_content")
	private String inputContent;
    /**
     * 区域的快速索引
     */
	@TableField("qucik_tag")
	private String qucikTag;
    /**
     * 打分题目对应的得分
     */
	private Integer score;
    /**
     * 是否存在危险词
     */
	@TableField("is_dangerous")
	private Integer isDangerous;
    /**
     * 本题的最高分
     */
	@TableField("max_score")
	private Integer maxScore;
    /**
     * 企业的id
     */
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
    /**
     * 题目的类型
     */
	@TableField("question_type")
	private String questionType;
    /**
     * NPS维度名称
     */
	@TableField("nps_dimenssion")
	private String npsDimenssion;
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

	public UpAppsAnswerSheetItem setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getAnswerSheetId() {
		return answerSheetId;
	}

	public UpAppsAnswerSheetItem setAnswerSheetId(Long answerSheetId) {
		this.answerSheetId = answerSheetId;
		return this;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public UpAppsAnswerSheetItem setQuestionId(Long questionId) {
		this.questionId = questionId;
		return this;
	}

	public Long getQuestionItemId() {
		return questionItemId;
	}

	public UpAppsAnswerSheetItem setQuestionItemId(Long questionItemId) {
		this.questionItemId = questionItemId;
		return this;
	}

	public String getValue() {
		return value;
	}

	public UpAppsAnswerSheetItem setValue(String value) {
		this.value = value;
		return this;
	}

	public String getInputContent() {
		return inputContent;
	}

	public UpAppsAnswerSheetItem setInputContent(String inputContent) {
		this.inputContent = inputContent;
		return this;
	}

	public String getQucikTag() {
		return qucikTag;
	}

	public UpAppsAnswerSheetItem setQucikTag(String qucikTag) {
		this.qucikTag = qucikTag;
		return this;
	}

	public Integer getScore() {
		return score;
	}

	public UpAppsAnswerSheetItem setScore(Integer score) {
		this.score = score;
		return this;
	}

	public Integer getIsDangerous() {
		return isDangerous;
	}

	public UpAppsAnswerSheetItem setIsDangerous(Integer isDangerous) {
		this.isDangerous = isDangerous;
		return this;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public UpAppsAnswerSheetItem setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpAppsAnswerSheetItem setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getQuestionType() {
		return questionType;
	}

	public UpAppsAnswerSheetItem setQuestionType(String questionType) {
		this.questionType = questionType;
		return this;
	}

	public String getNpsDimenssion() {
		return npsDimenssion;
	}

	public UpAppsAnswerSheetItem setNpsDimenssion(String npsDimenssion) {
		this.npsDimenssion = npsDimenssion;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsAnswerSheetItem setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsAnswerSheetItem setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsAnswerSheetItem setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsAnswerSheetItem setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String ANSWER_SHEET_ID = "answer_sheet_id";

	public static final String QUESTION_ID = "question_id";

	public static final String QUESTION_ITEM_ID = "question_item_id";

	public static final String VALUE = "value";

	public static final String INPUT_CONTENT = "input_content";

	public static final String QUCIK_TAG = "qucik_tag";

	public static final String SCORE = "score";

	public static final String IS_DANGEROUS = "is_dangerous";

	public static final String MAX_SCORE = "max_score";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String QUESTION_TYPE = "question_type";

	public static final String NPS_DIMENSSION = "nps_dimenssion";

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
		return "UpAppsAnswerSheetItem{" +
			"id=" + id +
			", answerSheetId=" + answerSheetId +
			", questionId=" + questionId +
			", questionItemId=" + questionItemId +
			", value=" + value +
			", inputContent=" + inputContent +
			", qucikTag=" + qucikTag +
			", score=" + score +
			", isDangerous=" + isDangerous +
			", maxScore=" + maxScore +
			", tenantsCompanyId=" + tenantsCompanyId +
			", questionType=" + questionType +
			", npsDimenssion=" + npsDimenssion +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
