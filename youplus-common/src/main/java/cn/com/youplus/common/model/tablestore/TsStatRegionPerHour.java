package cn.com.youplus.common.model.tablestore;

import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.constant.PrimaryKeyTypeObject;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
@TableEntity(name = "ts_stat_region_per_hour")
public class TsStatRegionPerHour extends TsBaseData {

    @TableKey(type = PrimaryKeyTypeObject.INTEGER)
    private Long questionnaireId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 1)
    private Long regionId;

    @TableKey(type = PrimaryKeyTypeObject.INTEGER, sort = 2)
    private Long hours;

    public TsStatRegionPerHour() {
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

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }
}
