package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseForm;
import cn.com.carry.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class PhoneForm extends BaseForm implements Serializable{

    @Check(notNull = true)
    private String phoneNum;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
