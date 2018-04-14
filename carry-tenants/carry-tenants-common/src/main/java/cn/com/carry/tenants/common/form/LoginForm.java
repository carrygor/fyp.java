package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseIdForm;
import cn.com.carry.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class LoginForm extends BaseIdForm {

    @Check(notNull = true)
    private String username;

    @Check(notNull = true)
    private String password;

    private Long userRoleId;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
