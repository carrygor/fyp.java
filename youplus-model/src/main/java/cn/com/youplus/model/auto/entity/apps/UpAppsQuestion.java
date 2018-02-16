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
@TableName("up_apps_question")
public class UpAppsQuestion extends Model<UpAppsQuestion> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 问卷id
     */
	@TableField("questionnaire_id")
	private Long questionnaireId;
    /**
     * 标题
     */
	private String title;
    /**
     * 子标题
     */
	@TableField("sub_title")
	private String subTitle;
    /**
     * description
     */
	private String description;
    /**
     * 题目类型
     */
	@TableField("question_type")
	private String questionType;
    /**
     * 是否必答
     */
	@TableField("is_required")
	private Integer isRequired;
    /**
     * 子选项总体数目
     */
	@TableField("options_num")
	private Integer optionsNum;
    /**
     * 选项的json包
     */
	@TableField("question_json")
	private String questionJson;
    /**
     * 题目标号
     */
	@TableField("question_order")
	private Integer questionOrder;
    /**
     * 是否显示
     */
	@TableField("is_visible")
	private Integer isVisible;
    /**
     * 区域的快速索引
     */
	@TableField("qucik_tag")
	private String qucikTag;
    /**
     * 优秀的得分
     */
	@TableField("good_score")
	private Integer goodScore;
    /**
     * 不好的得分
     */
	@TableField("bad_score")
	private Integer badScore;
    /**
     * 是否为NPS题目
     */
	@TableField("is_nps")
	private Integer isNps;
    /**
     * NPS维度的名称
     */
	@TableField("score_dimenssion")
	private String scoreDimenssion;
    /**
     * 是否满意度题目
     */
	@TableField("is_score")
	private Integer isScore;
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
     * 显示逻辑
     */
	@TableField("display_rule")
	private String displayRule;
    /**
     * 记录前端使用的unique
     */
	@TableField("unique_key")
	private String uniqueKey;
    /**
     * 是否随机排序题目
     */
	@TableField("is_random_sort")
	private Integer isRandomSort;


	public Long getId() {
		return id;
	}

	public UpAppsQuestion setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpAppsQuestion setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public UpAppsQuestion setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public UpAppsQuestion setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpAppsQuestion setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getQuestionType() {
		return questionType;
	}

	public UpAppsQuestion setQuestionType(String questionType) {
		this.questionType = questionType;
		return this;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public UpAppsQuestion setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
		return this;
	}

	public Integer getOptionsNum() {
		return optionsNum;
	}

	public UpAppsQuestion setOptionsNum(Integer optionsNum) {
		this.optionsNum = optionsNum;
		return this;
	}

	public String getQuestionJson() {
		return questionJson;
	}

	public UpAppsQuestion setQuestionJson(String questionJson) {
		this.questionJson = questionJson;
		return this;
	}

	public Integer getQuestionOrder() {
		return questionOrder;
	}

	public UpAppsQuestion setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
		return this;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public UpAppsQuestion setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public String getQucikTag() {
		return qucikTag;
	}

	public UpAppsQuestion setQucikTag(String qucikTag) {
		this.qucikTag = qucikTag;
		return this;
	}

	public Integer getGoodScore() {
		return goodScore;
	}

	public UpAppsQuestion setGoodScore(Integer goodScore) {
		this.goodScore = goodScore;
		return this;
	}

	public Integer getBadScore() {
		return badScore;
	}

	public UpAppsQuestion setBadScore(Integer badScore) {
		this.badScore = badScore;
		return this;
	}

	public Integer getIsNps() {
		return isNps;
	}

	public UpAppsQuestion setIsNps(Integer isNps) {
		this.isNps = isNps;
		return this;
	}

	public String getScoreDimenssion() {
		return scoreDimenssion;
	}

	public UpAppsQuestion setScoreDimenssion(String scoreDimenssion) {
		this.scoreDimenssion = scoreDimenssion;
		return this;
	}

	public Integer getIsScore() {
		return isScore;
	}

	public UpAppsQuestion setIsScore(Integer isScore) {
		this.isScore = isScore;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsQuestion setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsQuestion setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsQuestion setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsQuestion setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getDisplayRule() {
		return displayRule;
	}

	public UpAppsQuestion setDisplayRule(String displayRule) {
		this.displayRule = displayRule;
		return this;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public UpAppsQuestion setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
		return this;
	}

	public Integer getIsRandomSort() {
		return isRandomSort;
	}

	public UpAppsQuestion setIsRandomSort(Integer isRandomSort) {
		this.isRandomSort = isRandomSort;
		return this;
	}

	public static final String ID = "id";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String TITLE = "title";

	public static final String SUB_TITLE = "sub_title";

	public static final String DESCRIPTION = "description";

	public static final String QUESTION_TYPE = "question_type";

	public static final String IS_REQUIRED = "is_required";

	public static final String OPTIONS_NUM = "options_num";

	public static final String QUESTION_JSON = "question_json";

	public static final String QUESTION_ORDER = "question_order";

	public static final String IS_VISIBLE = "is_visible";

	public static final String QUCIK_TAG = "qucik_tag";

	public static final String GOOD_SCORE = "good_score";

	public static final String BAD_SCORE = "bad_score";

	public static final String IS_NPS = "is_nps";

	public static final String SCORE_DIMENSSION = "score_dimenssion";

	public static final String IS_SCORE = "is_score";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String DISPLAY_RULE = "display_rule";

	public static final String UNIQUE_KEY = "unique_key";

	public static final String IS_RANDOM_SORT = "is_random_sort";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsQuestion{" +
			"id=" + id +
			", questionnaireId=" + questionnaireId +
			", title=" + title +
			", subTitle=" + subTitle +
			", description=" + description +
			", questionType=" + questionType +
			", isRequired=" + isRequired +
			", optionsNum=" + optionsNum +
			", questionJson=" + questionJson +
			", questionOrder=" + questionOrder +
			", isVisible=" + isVisible +
			", qucikTag=" + qucikTag +
			", goodScore=" + goodScore +
			", badScore=" + badScore +
			", isNps=" + isNps +
			", scoreDimenssion=" + scoreDimenssion +
			", isScore=" + isScore +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", displayRule=" + displayRule +
			", uniqueKey=" + uniqueKey +
			", isRandomSort=" + isRandomSort +
			"}";
	}
}
