package cn.com.youplus.common.model.tablestore;

import com.hannea.annotation.TableColumn;
import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.constant.ColumnTypeObject;
import com.hannea.constant.PrimaryKeyTypeObject;

import java.util.Date;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
@TableEntity(name = "ts_answer_sheet_access_log")
public class TsAnswerSheetAccessLog extends TableStoreBaseModel{

    @TableKey(type = PrimaryKeyTypeObject.INTEGER)
    private Long accessLogId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 1)
    private Long questionnaireId;

    @TableColumn(type= ColumnTypeObject.LONG)
    private Long answerSheetId;

    @TableColumn(type= ColumnTypeObject.LONG)
    private Long regionId;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String system;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String browser;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String cookies;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String openid;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String ip;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String phone;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String uri;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String log;

    @TableColumn(type= ColumnTypeObject.STRING)
    private String entrance;

    @TableColumn(type= ColumnTypeObject.DATE)
    private Date startTime;

    /**
     * 最后更新日期，自动计算
     */
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
