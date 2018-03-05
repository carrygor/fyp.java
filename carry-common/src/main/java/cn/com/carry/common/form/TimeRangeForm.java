package cn.com.carry.common.form;

import cn.com.carry.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/2.
 */
public class TimeRangeForm extends BaseForm implements Serializable {


    /**
     * 问卷id
     */
    @Check( minNum = 1)
    private Long questionnaireId;

    /**
     * 开始时间
     */
    @Check( minNum = 1)
    private Long startDate;

    /**
     * 结束日期
     */
    @Check( minNum = 1)
    private Long endDate;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }
}
