package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseForm;
import cn.com.carry.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class ChangePhoneForm extends BaseForm implements Serializable{

    @Check(notNull = true)
    private String phoneNum;

    @Check(notNull = true)
    private String code;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
