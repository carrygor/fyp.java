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
@TableName("up_apps_answer_sheet_item_view")
public class UpAppsAnswerSheetItemView extends Model<UpAppsAnswerSheetItemView> implements Serializable {

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
    /**
     * 输入的内容
     */
	@TableField("input_content")
	private String inputContent;
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
     * 省份
     */
	private String province;
    /**
     * 城市
     */
	private String city;
    /**
     * 地区
     */
	private String district;
    /**
     * 区域代码
     */
	@TableField("region_code")
	private String regionCode;


	public Long getId() {
		return id;
	}

	public UpAppsAnswerSheetItemView setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getAnswerSheetId() {
		return answerSheetId;
	}

	public UpAppsAnswerSheetItemView setAnswerSheetId(Long answerSheetId) {
		this.answerSheetId = answerSheetId;
		return this;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public UpAppsAnswerSheetItemView setQuestionId(Long questionId) {
		this.questionId = questionId;
		return this;
	}

	public Long getQuestionItemId() {
		return questionItemId;
	}

	public UpAppsAnswerSheetItemView setQuestionItemId(Long questionItemId) {
		this.questionItemId = questionItemId;
		return this;
	}

	public String getInputContent() {
		return inputContent;
	}

	public UpAppsAnswerSheetItemView setInputContent(String inputContent) {
		this.inputContent = inputContent;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsAnswerSheetItemView setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsAnswerSheetItemView setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsAnswerSheetItemView setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsAnswerSheetItemView setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getQucikTag() {
		return qucikTag;
	}

	public UpAppsAnswerSheetItemView setQucikTag(String qucikTag) {
		this.qucikTag = qucikTag;
		return this;
	}

	public Integer getScore() {
		return score;
	}

	public UpAppsAnswerSheetItemView setScore(Integer score) {
		this.score = score;
		return this;
	}

	public Integer getIsDangerous() {
		return isDangerous;
	}

	public UpAppsAnswerSheetItemView setIsDangerous(Integer isDangerous) {
		this.isDangerous = isDangerous;
		return this;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public UpAppsAnswerSheetItemView setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpAppsAnswerSheetItemView setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getQuestionType() {
		return questionType;
	}

	public UpAppsAnswerSheetItemView setQuestionType(String questionType) {
		this.questionType = questionType;
		return this;
	}

	public String getNpsDimenssion() {
		return npsDimenssion;
	}

	public UpAppsAnswerSheetItemView setNpsDimenssion(String npsDimenssion) {
		this.npsDimenssion = npsDimenssion;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public UpAppsAnswerSheetItemView setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UpAppsAnswerSheetItemView setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public UpAppsAnswerSheetItemView setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public UpAppsAnswerSheetItemView setRegionCode(String regionCode) {
		this.regionCode = regionCode;
		return this;
	}

	public static final String ID = "id";

	public static final String ANSWER_SHEET_ID = "answer_sheet_id";

	public static final String QUESTION_ID = "question_id";

	public static final String QUESTION_ITEM_ID = "question_item_id";

	public static final String INPUT_CONTENT = "input_content";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String QUCIK_TAG = "qucik_tag";

	public static final String SCORE = "score";

	public static final String IS_DANGEROUS = "is_dangerous";

	public static final String MAX_SCORE = "max_score";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String QUESTION_TYPE = "question_type";

	public static final String NPS_DIMENSSION = "nps_dimenssion";

	public static final String PROVINCE = "province";

	public static final String CITY = "city";

	public static final String DISTRICT = "district";

	public static final String REGION_CODE = "region_code";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsAnswerSheetItemView{" +
			"id=" + id +
			", answerSheetId=" + answerSheetId +
			", questionId=" + questionId +
			", questionItemId=" + questionItemId +
			", inputContent=" + inputContent +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", qucikTag=" + qucikTag +
			", score=" + score +
			", isDangerous=" + isDangerous +
			", maxScore=" + maxScore +
			", tenantsCompanyId=" + tenantsCompanyId +
			", questionType=" + questionType +
			", npsDimenssion=" + npsDimenssion +
			", province=" + province +
			", city=" + city +
			", district=" + district +
			", regionCode=" + regionCode +
			"}";
	}
}
