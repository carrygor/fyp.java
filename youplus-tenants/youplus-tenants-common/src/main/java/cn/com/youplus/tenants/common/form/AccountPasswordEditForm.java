package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class AccountPasswordEditForm extends BaseForm implements Serializable{

    private String oldPassword;

    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
