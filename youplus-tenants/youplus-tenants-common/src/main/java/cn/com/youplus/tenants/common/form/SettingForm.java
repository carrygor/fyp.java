package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class SettingForm extends BaseForm implements Serializable{

    private Long questionnaireId;

    private String randomQuestion;

    private String settingNames;

    private String limitSetting;

    public String getLimitSetting() {
        return limitSetting;
    }

    public void setLimitSetting(String limitSetting) {
        this.limitSetting = limitSetting;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public SettingForm setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

    public String getRandomQuestion() {
        return randomQuestion;
    }

    public SettingForm setRandomQuestion(String randomQuestion) {
        this.randomQuestion = randomQuestion;
        return this;
    }

    public String getSettingNames() {
        return settingNames;
    }

    public SettingForm setSettingNames(String settingNames) {
        this.settingNames = settingNames;
        return this;
    }
}
