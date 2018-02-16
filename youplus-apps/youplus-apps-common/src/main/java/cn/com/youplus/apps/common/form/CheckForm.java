package cn.com.youplus.apps.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class CheckForm extends BaseForm implements Serializable {

    private String openid;

    private String orderSn;

    private Long r;

    private Long q;

    private Long c;

    private String entrance;

    private String roleCode;

    private String roleName;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public Long getRegionId() {
        return r;
    }

    public void setR(Long regionId) {
        this.r = regionId;
    }

    public Long getCompanyId() {
        return c;
    }

    public void setC(Long companyId) {
        this.c = companyId;
    }

    public Long getQuestionnaireId() {
        return q;
    }

    public void setQ(Long questionnaireId) {
        this.q = questionnaireId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
}
