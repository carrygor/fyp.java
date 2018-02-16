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
@TableName("up_apps_answer_sheet_detail_view")
public class UpAppsAnswerSheetDetailView extends Model<UpAppsAnswerSheetDetailView> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 答题人用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 归属的企业id
     */
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
    /**
     * 网点id
     */
	@TableField("tenants_region_id")
	private Long tenantsRegionId;
    /**
     * 问卷id
     */
	@TableField("questionnaire_id")
	private Long questionnaireId;
    /**
     * 是否自动提交
     */
	@TableField("is_auto_submit")
	private Integer isAutoSubmit;
	@TableField("start_time")
	private Date startTime;
    /**
     * 是否完成
     */
	@TableField("is_finished")
	private Integer isFinished;
	@TableField("finish_time")
	private Date finishTime;
    /**
     * 完成题目数量
     */
	@TableField("finish_question_num")
	private Integer finishQuestionNum;
    /**
     * 完成必填题目数量
     */
	@TableField("finish_required_question_num")
	private Integer finishRequiredQuestionNum;
    /**
     * 完成百分比
     */
	@TableField("finish_precentage")
	private Float finishPrecentage;
    /**
     * 是否需要跟进服务
     */
	@TableField("is_need_service")
	private Integer isNeedService;
    /**
     * 跟进记录
     */
	@TableField("service_record_json")
	private String serviceRecordJson;
    /**
     * 答题记录
     */
	@TableField("answer_sheet_json")
	private String answerSheetJson;
    /**
     * 答题人的来源
     */
	@TableField("user_source")
	private String userSource;
    /**
     * 快速索引
     */
	@TableField("quick_tag")
	private String quickTag;
    /**
     * 是否存在危险词
     */
	@TableField("is_dangerous")
	private Integer isDangerous;
    /**
     * 服务，或者危险是否解除
     */
	@TableField("is_sloved")
	private Integer isSloved;
    /**
     * 是否评论
     */
	@TableField("is_commented")
	private Integer isCommented;
    /**
     * 服务方式
     */
	@TableField("service_type")
	private String serviceType;
    /**
     * 如果是微信提交过来的,需要通过openid来判定用户
     */
	private String openid;
    /**
     * 对于支付行为产生的问卷需要通过orderSn来确定问卷的有效性
     */
	@TableField("order_sn")
	private String orderSn;
    /**
     * 提交答卷对应的cookies
     */
	private String cookies;
    /**
     * 记录ip,不一定能获取到
     */
	private String ip;
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
     * 电话号码
     */
	@TableField("phone_num")
	private String phoneNum;
    /**
     * 来源
     */
	private String entrance;
    /**
     * 浏览器属性
     */
	private String broswer;
    /**
     * 网点用户id
     */
	@TableField("questionnaire_title")
	private String questionnaireTitle;
    /**
     * 名称
     */
	@TableField("region_name")
	private String regionName;


	public Long getId() {
		return id;
	}

	public UpAppsAnswerSheetDetailView setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public UpAppsAnswerSheetDetailView setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpAppsAnswerSheetDetailView setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public Long getTenantsRegionId() {
		return tenantsRegionId;
	}

	public UpAppsAnswerSheetDetailView setTenantsRegionId(Long tenantsRegionId) {
		this.tenantsRegionId = tenantsRegionId;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpAppsAnswerSheetDetailView setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public Integer getIsAutoSubmit() {
		return isAutoSubmit;
	}

	public UpAppsAnswerSheetDetailView setIsAutoSubmit(Integer isAutoSubmit) {
		this.isAutoSubmit = isAutoSubmit;
		return this;
	}

	public Date getStartTime() {
		return startTime;
	}

	public UpAppsAnswerSheetDetailView setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	public Integer getIsFinished() {
		return isFinished;
	}

	public UpAppsAnswerSheetDetailView setIsFinished(Integer isFinished) {
		this.isFinished = isFinished;
		return this;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public UpAppsAnswerSheetDetailView setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
		return this;
	}

	public Integer getFinishQuestionNum() {
		return finishQuestionNum;
	}

	public UpAppsAnswerSheetDetailView setFinishQuestionNum(Integer finishQuestionNum) {
		this.finishQuestionNum = finishQuestionNum;
		return this;
	}

	public Integer getFinishRequiredQuestionNum() {
		return finishRequiredQuestionNum;
	}

	public UpAppsAnswerSheetDetailView setFinishRequiredQuestionNum(Integer finishRequiredQuestionNum) {
		this.finishRequiredQuestionNum = finishRequiredQuestionNum;
		return this;
	}

	public Float getFinishPrecentage() {
		return finishPrecentage;
	}

	public UpAppsAnswerSheetDetailView setFinishPrecentage(Float finishPrecentage) {
		this.finishPrecentage = finishPrecentage;
		return this;
	}

	public Integer getIsNeedService() {
		return isNeedService;
	}

	public UpAppsAnswerSheetDetailView setIsNeedService(Integer isNeedService) {
		this.isNeedService = isNeedService;
		return this;
	}

	public String getServiceRecordJson() {
		return serviceRecordJson;
	}

	public UpAppsAnswerSheetDetailView setServiceRecordJson(String serviceRecordJson) {
		this.serviceRecordJson = serviceRecordJson;
		return this;
	}

	public String getAnswerSheetJson() {
		return answerSheetJson;
	}

	public UpAppsAnswerSheetDetailView setAnswerSheetJson(String answerSheetJson) {
		this.answerSheetJson = answerSheetJson;
		return this;
	}

	public String getUserSource() {
		return userSource;
	}

	public UpAppsAnswerSheetDetailView setUserSource(String userSource) {
		this.userSource = userSource;
		return this;
	}

	public String getQuickTag() {
		return quickTag;
	}

	public UpAppsAnswerSheetDetailView setQuickTag(String quickTag) {
		this.quickTag = quickTag;
		return this;
	}

	public Integer getIsDangerous() {
		return isDangerous;
	}

	public UpAppsAnswerSheetDetailView setIsDangerous(Integer isDangerous) {
		this.isDangerous = isDangerous;
		return this;
	}

	public Integer getIsSloved() {
		return isSloved;
	}

	public UpAppsAnswerSheetDetailView setIsSloved(Integer isSloved) {
		this.isSloved = isSloved;
		return this;
	}

	public Integer getIsCommented() {
		return isCommented;
	}

	public UpAppsAnswerSheetDetailView setIsCommented(Integer isCommented) {
		this.isCommented = isCommented;
		return this;
	}

	public String getServiceType() {
		return serviceType;
	}

	public UpAppsAnswerSheetDetailView setServiceType(String serviceType) {
		this.serviceType = serviceType;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public UpAppsAnswerSheetDetailView setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public UpAppsAnswerSheetDetailView setOrderSn(String orderSn) {
		this.orderSn = orderSn;
		return this;
	}

	public String getCookies() {
		return cookies;
	}

	public UpAppsAnswerSheetDetailView setCookies(String cookies) {
		this.cookies = cookies;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public UpAppsAnswerSheetDetailView setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsAnswerSheetDetailView setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsAnswerSheetDetailView setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsAnswerSheetDetailView setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsAnswerSheetDetailView setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public UpAppsAnswerSheetDetailView setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}

	public String getEntrance() {
		return entrance;
	}

	public UpAppsAnswerSheetDetailView setEntrance(String entrance) {
		this.entrance = entrance;
		return this;
	}

	public String getBroswer() {
		return broswer;
	}

	public UpAppsAnswerSheetDetailView setBroswer(String broswer) {
		this.broswer = broswer;
		return this;
	}

	public String getQuestionnaireTitle() {
		return questionnaireTitle;
	}

	public UpAppsAnswerSheetDetailView setQuestionnaireTitle(String questionnaireTitle) {
		this.questionnaireTitle = questionnaireTitle;
		return this;
	}

	public String getRegionName() {
		return regionName;
	}

	public UpAppsAnswerSheetDetailView setRegionName(String regionName) {
		this.regionName = regionName;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String TENANTS_REGION_ID = "tenants_region_id";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String IS_AUTO_SUBMIT = "is_auto_submit";

	public static final String START_TIME = "start_time";

	public static final String IS_FINISHED = "is_finished";

	public static final String FINISH_TIME = "finish_time";

	public static final String FINISH_QUESTION_NUM = "finish_question_num";

	public static final String FINISH_REQUIRED_QUESTION_NUM = "finish_required_question_num";

	public static final String FINISH_PRECENTAGE = "finish_precentage";

	public static final String IS_NEED_SERVICE = "is_need_service";

	public static final String SERVICE_RECORD_JSON = "service_record_json";

	public static final String ANSWER_SHEET_JSON = "answer_sheet_json";

	public static final String USER_SOURCE = "user_source";

	public static final String QUICK_TAG = "quick_tag";

	public static final String IS_DANGEROUS = "is_dangerous";

	public static final String IS_SLOVED = "is_sloved";

	public static final String IS_COMMENTED = "is_commented";

	public static final String SERVICE_TYPE = "service_type";

	public static final String OPENID = "openid";

	public static final String ORDER_SN = "order_sn";

	public static final String COOKIES = "cookies";

	public static final String IP = "ip";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String PHONE_NUM = "phone_num";

	public static final String ENTRANCE = "entrance";

	public static final String BROSWER = "broswer";

	public static final String QUESTIONNAIRE_TITLE = "questionnaire_title";

	public static final String REGION_NAME = "region_name";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsAnswerSheetDetailView{" +
			"id=" + id +
			", userId=" + userId +
			", tenantsCompanyId=" + tenantsCompanyId +
			", tenantsRegionId=" + tenantsRegionId +
			", questionnaireId=" + questionnaireId +
			", isAutoSubmit=" + isAutoSubmit +
			", startTime=" + startTime +
			", isFinished=" + isFinished +
			", finishTime=" + finishTime +
			", finishQuestionNum=" + finishQuestionNum +
			", finishRequiredQuestionNum=" + finishRequiredQuestionNum +
			", finishPrecentage=" + finishPrecentage +
			", isNeedService=" + isNeedService +
			", serviceRecordJson=" + serviceRecordJson +
			", answerSheetJson=" + answerSheetJson +
			", userSource=" + userSource +
			", quickTag=" + quickTag +
			", isDangerous=" + isDangerous +
			", isSloved=" + isSloved +
			", isCommented=" + isCommented +
			", serviceType=" + serviceType +
			", openid=" + openid +
			", orderSn=" + orderSn +
			", cookies=" + cookies +
			", ip=" + ip +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", phoneNum=" + phoneNum +
			", entrance=" + entrance +
			", broswer=" + broswer +
			", questionnaireTitle=" + questionnaireTitle +
			", regionName=" + regionName +
			"}";
	}
}
