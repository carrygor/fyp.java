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
@TableEntity(name = "ts_answer_sheet_phone")
public class TsAnswerSheetPhone  extends TableStoreBaseModel {

    @TableKey(type = PrimaryKeyTypeObject.STRING)
    private String questionnaireIdPhone;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 1)
    private Long regionId;

    @TableColumn(type= ColumnTypeObject.LONG)
    private Long answerSheetId;

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

    public String getQuestionnaireIdPhone() {
        return questionnaireIdPhone;
    }

    public void setQuestionnaireIdPhone(String questionnaireIdPhone) {
        this.questionnaireIdPhone = questionnaireIdPhone;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

}
