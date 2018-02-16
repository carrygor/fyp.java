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
@TableEntity(name = "ts_answer_sheet_index")
public class TsAnswerSheetIndex extends TableStoreBaseModel {

    @TableKey(type = PrimaryKeyTypeObject.INTEGER)
    private Long questionnaireId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 1)
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

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}
