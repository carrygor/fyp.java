package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class WeixinOauthForm extends BaseForm implements Serializable {

    private String router;

    private String scope;

    private Long companyId;

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
