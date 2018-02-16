package cn.com.youplus.apps.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class SmsCodeForm extends BaseForm implements Serializable {

    @Check(notNull = true)
    private String phoneNum;

    private String entrance;

    @Check(minNum = 1)
    private Long questionnaireId;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }
}
