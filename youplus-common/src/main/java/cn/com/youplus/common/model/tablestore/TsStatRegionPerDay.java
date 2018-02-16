package cn.com.youplus.common.model.tablestore;

import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.constant.PrimaryKeyTypeObject;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
@TableEntity(name = "ts_stat_region_per_day")
public class TsStatRegionPerDay extends TsBaseData {

    @TableKey(type = PrimaryKeyTypeObject.INTEGER)
    private Long questionnaireId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 1)
    private Long regionId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 2)
    private Long days;

    public TsStatRegionPerDay() {
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
