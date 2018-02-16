package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class LoginForm extends BaseForm {

    @Check(notNull = true)
    private String username;

    @Check(notNull = true)
    private String password;

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
