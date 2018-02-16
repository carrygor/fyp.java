package cn.com.youplus.common.model.tablestore;

import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.constant.PrimaryKeyTypeObject;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
@TableEntity(name = "ts_total_data")
public class TsTotalData extends TsBaseData {

    @TableKey(type = PrimaryKeyTypeObject.INTEGER)
    private Long questionnaireId;

    public TsTotalData() {
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public TsTotalData setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

}
