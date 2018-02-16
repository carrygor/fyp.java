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
@TableName("up_apps_questionnaire")
public class UpAppsQuestionnaire extends Model<UpAppsQuestionnaire> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 网点用户id
     */
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
    /**
     * 项目名称
     */
	@TableField("project_name")
	private String projectName;
    /**
     * 网点用户id
     */
	private String title;
    /**
     * 子标题
     */
	@TableField("sub_title")
	private String subTitle;
    /**
     * 问卷说明
     */
	private String description;
    /**
     * 问卷类型
     */
	@TableField("questionnaire_type")
	private String questionnaireType;
    /**
     * 题目总数
     */
	@TableField("question_num")
	private Integer questionNum;
    /**
     * 题目总数
     */
	@TableField("required_option_num")
	private Integer requiredOptionNum;
    /**
     * 主题id
     */
	@TableField("theme_id")
	private Long themeId;
    /**
     * 排序，越小拍越前面
     */
	private Integer sort;
    /**
     * 前端传回来的json
     */
	@TableField("questionnaire_json")
	private String questionnaireJson;
    /**
     * 区域的快速索引
     */
	@TableField("quick_tag")
	private String quickTag;
    /**
     * 网点的名称，比如麦当劳叫餐厅，中石油叫加油站
     */
	@TableField("site_name")
	private String siteName;
    /**
     * 启用时间
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 过期时间
     */
	@TableField("expired_time")
	private Date expiredTime;
	private String status;
    /**
     * 是否默认的问卷
     */
	@TableField("is_default")
	private Integer isDefault;
    /**
     * 时间段
     */
	@TableField("period_json")
	private String periodJson;
    /**
     * nps分项的json
     */
	@TableField("nps_json")
	private String npsJson;
    /**
     * 服务类型选项
     */
	@TableField("service_type_json")
	private String serviceTypeJson;
    /**
     * 问卷的链接常规设置
     */
	@TableField("link_setting_code")
	private String linkSettingCode;
    /**
     * 问卷的微信常规设置
     */
	@TableField("weixin_setting_code")
	private String weixinSettingCode;
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
	@TableField("update_time")
	private Date updateTime;
	@TableField("add_time")
	private Date addTime;
    /**
     * 在微信上的有效时间(天)
     */
	@TableField("day_limit")
	private Integer dayLimit;
    /**
     * 问卷删除时间
     */
	@TableField("delete_time")
	private Date deleteTime;


	public Long getId() {
		return id;
	}

	public UpAppsQuestionnaire setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpAppsQuestionnaire setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public UpAppsQuestionnaire setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public UpAppsQuestionnaire setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public UpAppsQuestionnaire setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpAppsQuestionnaire setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getQuestionnaireType() {
		return questionnaireType;
	}

	public UpAppsQuestionnaire setQuestionnaireType(String questionnaireType) {
		this.questionnaireType = questionnaireType;
		return this;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public UpAppsQuestionnaire setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
		return this;
	}

	public Integer getRequiredOptionNum() {
		return requiredOptionNum;
	}

	public UpAppsQuestionnaire setRequiredOptionNum(Integer requiredOptionNum) {
		this.requiredOptionNum = requiredOptionNum;
		return this;
	}

	public Long getThemeId() {
		return themeId;
	}

	public UpAppsQuestionnaire setThemeId(Long themeId) {
		this.themeId = themeId;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public UpAppsQuestionnaire setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public String getQuestionnaireJson() {
		return questionnaireJson;
	}

	public UpAppsQuestionnaire setQuestionnaireJson(String questionnaireJson) {
		this.questionnaireJson = questionnaireJson;
		return this;
	}

	public String getQuickTag() {
		return quickTag;
	}

	public UpAppsQuestionnaire setQuickTag(String quickTag) {
		this.quickTag = quickTag;
		return this;
	}

	public String getSiteName() {
		return siteName;
	}

	public UpAppsQuestionnaire setSiteName(String siteName) {
		this.siteName = siteName;
		return this;
	}

	public Date getStartTime() {
		return startTime;
	}

	public UpAppsQuestionnaire setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public UpAppsQuestionnaire setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public UpAppsQuestionnaire setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public UpAppsQuestionnaire setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
		return this;
	}

	public String getPeriodJson() {
		return periodJson;
	}

	public UpAppsQuestionnaire setPeriodJson(String periodJson) {
		this.periodJson = periodJson;
		return this;
	}

	public String getNpsJson() {
		return npsJson;
	}

	public UpAppsQuestionnaire setNpsJson(String npsJson) {
		this.npsJson = npsJson;
		return this;
	}

	public String getServiceTypeJson() {
		return serviceTypeJson;
	}

	public UpAppsQuestionnaire setServiceTypeJson(String serviceTypeJson) {
		this.serviceTypeJson = serviceTypeJson;
		return this;
	}

	public String getLinkSettingCode() {
		return linkSettingCode;
	}

	public UpAppsQuestionnaire setLinkSettingCode(String linkSettingCode) {
		this.linkSettingCode = linkSettingCode;
		return this;
	}

	public String getWeixinSettingCode() {
		return weixinSettingCode;
	}

	public UpAppsQuestionnaire setWeixinSettingCode(String weixinSettingCode) {
		this.weixinSettingCode = weixinSettingCode;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsQuestionnaire setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsQuestionnaire setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsQuestionnaire setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsQuestionnaire setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Integer getDayLimit() {
		return dayLimit;
	}

	public UpAppsQuestionnaire setDayLimit(Integer dayLimit) {
		this.dayLimit = dayLimit;
		return this;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public UpAppsQuestionnaire setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String PROJECT_NAME = "project_name";

	public static final String TITLE = "title";

	public static final String SUB_TITLE = "sub_title";

	public static final String DESCRIPTION = "description";

	public static final String QUESTIONNAIRE_TYPE = "questionnaire_type";

	public static final String QUESTION_NUM = "question_num";

	public static final String REQUIRED_OPTION_NUM = "required_option_num";

	public static final String THEME_ID = "theme_id";

	public static final String SORT = "sort";

	public static final String QUESTIONNAIRE_JSON = "questionnaire_json";

	public static final String QUICK_TAG = "quick_tag";

	public static final String SITE_NAME = "site_name";

	public static final String START_TIME = "start_time";

	public static final String EXPIRED_TIME = "expired_time";

	public static final String STATUS = "status";

	public static final String IS_DEFAULT = "is_default";

	public static final String PERIOD_JSON = "period_json";

	public static final String NPS_JSON = "nps_json";

	public static final String SERVICE_TYPE_JSON = "service_type_json";

	public static final String LINK_SETTING_CODE = "link_setting_code";

	public static final String WEIXIN_SETTING_CODE = "weixin_setting_code";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String UPDATE_TIME = "update_time";

	public static final String ADD_TIME = "add_time";

	public static final String DAY_LIMIT = "day_limit";

	public static final String DELETE_TIME = "delete_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsQuestionnaire{" +
			"id=" + id +
			", tenantsCompanyId=" + tenantsCompanyId +
			", projectName=" + projectName +
			", title=" + title +
			", subTitle=" + subTitle +
			", description=" + description +
			", questionnaireType=" + questionnaireType +
			", questionNum=" + questionNum +
			", requiredOptionNum=" + requiredOptionNum +
			", themeId=" + themeId +
			", sort=" + sort +
			", questionnaireJson=" + questionnaireJson +
			", quickTag=" + quickTag +
			", siteName=" + siteName +
			", startTime=" + startTime +
			", expiredTime=" + expiredTime +
			", status=" + status +
			", isDefault=" + isDefault +
			", periodJson=" + periodJson +
			", npsJson=" + npsJson +
			", serviceTypeJson=" + serviceTypeJson +
			", linkSettingCode=" + linkSettingCode +
			", weixinSettingCode=" + weixinSettingCode +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", updateTime=" + updateTime +
			", addTime=" + addTime +
			", dayLimit=" + dayLimit +
			", deleteTime=" + deleteTime +
			"}";
	}
}
