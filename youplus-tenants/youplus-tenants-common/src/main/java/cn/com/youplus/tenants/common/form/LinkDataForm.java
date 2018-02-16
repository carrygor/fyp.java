package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class LinkDataForm extends BaseForm implements Serializable{

    private Long regionId;

    private Long questionnaireId;

    public String getQueryString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?")
                .append("q=").append(questionnaireId)
                .append("&r=").append(regionId);
        return stringBuilder.toString();
    }

    public Long getRegionId() {
        return regionId;
    }

    public LinkDataForm setRegionId(Long regionId) {
        this.regionId = regionId;
        return this;
    }

    public Long getQuestionnaireId() {

        return questionnaireId;
    }

    public LinkDataForm setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }
}
