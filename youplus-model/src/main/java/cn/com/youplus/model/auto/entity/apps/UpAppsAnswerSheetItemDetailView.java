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
 * VIEW
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_apps_answer_sheet_item_detail_view")
public class UpAppsAnswerSheetItemDetailView extends Model<UpAppsAnswerSheetItemDetailView> implements Serializable {

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
    /**
     * 子标题
     */
	@TableField("sub_title")
	private String subTitle;
    /**
     * 标题
     */
	private String title;
    /**
     * 标号
     */
	private String key;
    /**
     * 答案显示到前端的内容
     */
	private String display;
    /**
     * 排序，默认1
     */
	private Integer sort;
    /**
     * 题目标号
     */
	@TableField("question_order")
	private Integer questionOrder;


	public Long getId() {
		return id;
	}

	public UpAppsAnswerSheetItemDetailView setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getAnswerSheetId() {
		return answerSheetId;
	}

	public UpAppsAnswerSheetItemDetailView setAnswerSheetId(Long answerSheetId) {
		this.answerSheetId = answerSheetId;
		return this;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public UpAppsAnswerSheetItemDetailView setQuestionId(Long questionId) {
		this.questionId = questionId;
		return this;
	}

	public Long getQuestionItemId() {
		return questionItemId;
	}

	public UpAppsAnswerSheetItemDetailView setQuestionItemId(Long questionItemId) {
		this.questionItemId = questionItemId;
		return this;
	}

	public String getValue() {
		return value;
	}

	public UpAppsAnswerSheetItemDetailView setValue(String value) {
		this.value = value;
		return this;
	}

	public String getInputContent() {
		return inputContent;
	}

	public UpAppsAnswerSheetItemDetailView setInputContent(String inputContent) {
		this.inputContent = inputContent;
		return this;
	}

	public String getQucikTag() {
		return qucikTag;
	}

	public UpAppsAnswerSheetItemDetailView setQucikTag(String qucikTag) {
		this.qucikTag = qucikTag;
		return this;
	}

	public Integer getScore() {
		return score;
	}

	public UpAppsAnswerSheetItemDetailView setScore(Integer score) {
		this.score = score;
		return this;
	}

	public Integer getIsDangerous() {
		return isDangerous;
	}

	public UpAppsAnswerSheetItemDetailView setIsDangerous(Integer isDangerous) {
		this.isDangerous = isDangerous;
		return this;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public UpAppsAnswerSheetItemDetailView setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpAppsAnswerSheetItemDetailView setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getQuestionType() {
		return questionType;
	}

	public UpAppsAnswerSheetItemDetailView setQuestionType(String questionType) {
		this.questionType = questionType;
		return this;
	}

	public String getNpsDimenssion() {
		return npsDimenssion;
	}

	public UpAppsAnswerSheetItemDetailView setNpsDimenssion(String npsDimenssion) {
		this.npsDimenssion = npsDimenssion;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsAnswerSheetItemDetailView setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsAnswerSheetItemDetailView setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsAnswerSheetItemDetailView setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsAnswerSheetItemDetailView setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public UpAppsAnswerSheetItemDetailView setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public UpAppsAnswerSheetItemDetailView setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getKey() {
		return key;
	}

	public UpAppsAnswerSheetItemDetailView setKey(String key) {
		this.key = key;
		return this;
	}

	public String getDisplay() {
		return display;
	}

	public UpAppsAnswerSheetItemDetailView setDisplay(String display) {
		this.display = display;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public UpAppsAnswerSheetItemDetailView setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Integer getQuestionOrder() {
		return questionOrder;
	}

	public UpAppsAnswerSheetItemDetailView setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
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

	public static final String SUB_TITLE = "sub_title";

	public static final String TITLE = "title";

	public static final String KEY = "key";

	public static final String DISPLAY = "display";

	public static final String SORT = "sort";

	public static final String QUESTION_ORDER = "question_order";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsAnswerSheetItemDetailView{" +
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
			", subTitle=" + subTitle +
			", title=" + title +
			", key=" + key +
			", display=" + display +
			", sort=" + sort +
			", questionOrder=" + questionOrder +
			"}";
	}
}
