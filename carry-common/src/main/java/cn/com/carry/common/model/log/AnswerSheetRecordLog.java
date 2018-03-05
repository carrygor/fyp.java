package cn.com.carry.common.model.log;

import cn.com.carry.common.model.log.annotation.UpLogItem;
import cn.com.carry.common.model.log.annotation.UpLogStore;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@UpLogStore(name = "answer-sheet-record-log")
public class AnswerSheetRecordLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ip地址
     */
    @UpLogItem(name="ip")
	private String ip;

    @UpLogItem(name="addTime")
    private Date addTime;

    /**
     * 浏览器cookies
     */
    @UpLogItem(name="cookies")
    private String cookies;

    @UpLogItem(name="broswer")
    private String broswer;

    @UpLogItem(name="system")
    private String system;

    @UpLogItem(name = "answerSheetId")
    private Long answerSheetId;

    @UpLogItem(name = "accessLogId")
    private Long accessLogId;

    @UpLogItem(name = "questionnaireId")
    private Long questionnaireId;

    @UpLogItem(name = "quickTag")
    private String quickTag;

    @UpLogItem(name = "regionId")
    private Long regionId;

    @UpLogItem(name = "answerTime")
    private Double answerTime;

    @UpLogItem(name = "answerItems")
    private String answerItems;

    @UpLogItem(name = "openid")
    private String openid;

    @UpLogItem(name = "finishQuestionNum")
    private Integer finishQuestionNum;

    public Integer getFinishQuestionNum() {
        return finishQuestionNum;
    }

    public void setFinishQuestionNum(Integer finishQuestionNum) {
        this.finishQuestionNum = finishQuestionNum;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public Double getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Double answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(String answerItems) {
        this.answerItems = answerItems;
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

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
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

    public Long getAccessLogId() {
        return accessLogId;
    }

    public void setAccessLogId(Long accessLogId) {
        this.accessLogId = accessLogId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuickTag() {
        return quickTag;
    }

    public void setQuickTag(String quickTag) {
        this.quickTag = quickTag;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
