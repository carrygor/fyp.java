package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseIdForm;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class RoleEditForm extends BaseIdForm {

    private String name;

    private String roleCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
