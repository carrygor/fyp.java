package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class PhoneListForm extends BaseForm {

    private String type;

    private Long questionnaireId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}
