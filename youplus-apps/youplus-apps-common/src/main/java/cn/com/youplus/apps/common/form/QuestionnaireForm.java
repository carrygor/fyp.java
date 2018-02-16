package cn.com.youplus.apps.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class QuestionnaireForm extends BaseForm implements Serializable {

    //@Check(minNum = 1)  考虑到预览的时候没有asid，去掉限制
    private Long answerSheetId;

    @Check
    private Long questionnaireId;

    @Check(notNull = true)
    private String entrance;

    @Check(minNum = 1)
    private Long regionId;

    private String phoneNum;

    private Long weixinUserId;

    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public QuestionnaireForm setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public QuestionnaireForm setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

    public Long getRegionId() {
        return regionId;
    }

    public QuestionnaireForm setRegionId(Long regionId) {
        this.regionId = regionId;
        return this;
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

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public Long getWeixinUserId() {
        return weixinUserId;
    }

    public QuestionnaireForm setWeixinUserId(Long weixinUserId) {
        this.weixinUserId = weixinUserId;
        return this;
    }
}
