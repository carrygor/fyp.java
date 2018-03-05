package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class JSONForm extends BaseForm implements Serializable{

    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
