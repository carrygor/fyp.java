package cn.com.youplus.apps.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class WeixinOauthForm extends BaseForm implements Serializable {

    private String router;

    private String scope;

    private Long companyId;

    private Long regionId;

    private String questionnaireId;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
