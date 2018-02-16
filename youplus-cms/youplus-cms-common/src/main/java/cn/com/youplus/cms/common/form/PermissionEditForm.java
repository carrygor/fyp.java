package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class PermissionEditForm extends BaseForm {

    @Check(minNum = 1)
    private long roleId;

    private String permissionStr;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getPermissionStr() {
        return permissionStr;
    }

    public void setPermissionStr(String permissionStr) {
        this.permissionStr = permissionStr;
    }
}
