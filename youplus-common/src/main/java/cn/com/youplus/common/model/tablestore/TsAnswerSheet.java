package cn.com.youplus.common.model.tablestore;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;
import com.hannea.annotation.TableColumn;
import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.constant.ColumnTypeObject;
import com.hannea.constant.PrimaryKeyTypeObject;

import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
@TableEntity(name = "ts_answer_sheet")
public class TsAnswerSheet extends TableStoreBaseModel {

    @TableKey(type = PrimaryKeyTypeObject.INTEGER)
    private Long answerSheetId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 1)
    private Long questionnaireId;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    /**
     * 答题人用户id
     */
    @TableColumn(name = "accessLogId", type = ColumnTypeObject.LONG)
    private Long accessLogId;

    /**
     * 如果是微信提交过来的,需要通过openid来判定用户
     */
    @TableColumn(name = "openid", type = ColumnTypeObject.STRING)
    private String openid;

    /**
     * 网点id
     */
    @TableColumn(name = "tenantsRegionId", type = ColumnTypeObject.LONG)
    private Long tenantsRegionId;

    /**
     * 是否自动提交
     */
    @TableColumn(name = "isAutoSubmit", type = ColumnTypeObject.BOOLEAN)
    private Boolean isAutoSubmit = false;

    /**
     * 填写开始时间
     */
    @TableColumn(name = "startTime", type = ColumnTypeObject.DATE)
    private Date startTime;

    /**
     * 是否完成
     */
    @TableColumn(name = "isFinished", type = ColumnTypeObject.BOOLEAN)
    private Boolean isFinished;


    @TableColumn(name = "finishTime", type = ColumnTypeObject.DATE)
    private Date finishTime;

    /**
     * 完成题目数量
     */
    @TableColumn(name = "finishQuestionNum", type = ColumnTypeObject.INTEGER)
    private Integer finishQuestionNum;

    /**
     * 完成必填题目数量
     */
    @TableColumn(name = "finishRequiredQuestionNum", type = ColumnTypeObject.INTEGER)
    private Integer finishRequiredQuestionNum;

    /**
     * 完成百分比
     */
    @TableColumn(name = "finishPrecentage", type = ColumnTypeObject.FLOAT)
    private Float finishPrecentage;

    /**
     * 快速索引
     */
    @TableColumn(name = "quickTag", type = ColumnTypeObject.STRING)
    private String quickTag;

    /**
     * 对于支付行为产生的问卷需要通过orderSn来确定问卷的有效性
     */
    @TableColumn(name = "orderSn", type = ColumnTypeObject.STRING)
    private String orderSn;

    /**
     * 提交答卷对应的cookies
     */
    @TableColumn(name = "cookies", type = ColumnTypeObject.STRING)
    private String cookies;

    /**
     * 记录ip,不一定能获取到
     */
    @TableColumn(name = "ip", type = ColumnTypeObject.STRING)
    private String ip;

    @TableColumn(name = "addTime", type = ColumnTypeObject.DATE)
    private Date addTime;

    /**
     * 自动根据Version计算的
     */
    private Date updateTime;

    /**
     * 电话号码
     */
    @TableColumn(name = "phoneNum", type = ColumnTypeObject.STRING)
    private String phoneNum;

    /**
     * 来源
     */
    @TableColumn(name = "entrance", type = ColumnTypeObject.STRING)
    private String entrance;

    /**
     * 浏览器属性
     */
    @TableColumn(name = "broswer", type = ColumnTypeObject.STRING)
    private String broswer;

    /**
     * 浏览器属性
     */
    @TableColumn(name = "system", type = ColumnTypeObject.STRING)
    private String system;


    /**
     * 浏览器属性
     */
    @TableColumn(name = "broswerType", type = ColumnTypeObject.STRING)
    private String broswerType;

    @TableColumn(name = "itemList", type = ColumnTypeObject.ARRAY)
    private List<UpAppsAnswerSheetItem> itemList;

    public Long getAccessLogId() {
        return accessLogId;
    }

    public void setAccessLogId(Long accessLogId) {
        this.accessLogId = accessLogId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getTenantsRegionId() {
        return tenantsRegionId;
    }

    public void setTenantsRegionId(Long tenantsRegionId) {
        this.tenantsRegionId = tenantsRegionId;
    }

    public Boolean getAutoSubmit() {
        return isAutoSubmit;
    }

    public void setAutoSubmit(Boolean autoSubmit) {
        this.isAutoSubmit = autoSubmit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public void isIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getFinishQuestionNum() {
        return finishQuestionNum;
    }

    public void setFinishQuestionNum(Integer finishQuestionNum) {
        this.finishQuestionNum = finishQuestionNum;
    }

    public Integer getFinishRequiredQuestionNum() {
        return finishRequiredQuestionNum;
    }

    public void setFinishRequiredQuestionNum(Integer finishRequiredQuestionNum) {
        this.finishRequiredQuestionNum = finishRequiredQuestionNum;
    }

    public Float getFinishPrecentage() {
        return finishPrecentage;
    }

    public void setFinishPrecentage(Float finishPrecentage) {
        this.finishPrecentage = finishPrecentage;
    }

    public String getQuickTag() {
        return quickTag;
    }

    public void setQuickTag(String quickTag) {
        this.quickTag = quickTag;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getBroswer() {
        return broswer;
    }

    public void setBroswer(String broswer) {
        this.broswer = broswer;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getBroswerType() {
        return broswerType;
    }

    public void setBroswerType(String broswerType) {
        this.broswerType = broswerType;
    }

    public List<UpAppsAnswerSheetItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<UpAppsAnswerSheetItem> itemList) {
        this.itemList = itemList;
    }
}
