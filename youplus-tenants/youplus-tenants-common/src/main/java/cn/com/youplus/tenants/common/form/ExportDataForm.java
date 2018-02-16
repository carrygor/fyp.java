package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class ExportDataForm extends BaseForm implements Serializable{

    private Long questionnaireId;
    private Long start;
    private Long end;
    private String quickTag;
    private String regionFilter;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getQuickTag() {
        return quickTag;
    }

    public void setQuickTag(String quickTag) {
        this.quickTag = quickTag;
    }

    public String getRegionFilter() {
        return regionFilter;
    }

    public ExportDataForm setRegionFilter(String regionFilter) {
        this.regionFilter = regionFilter;
        return this;
    }
}
