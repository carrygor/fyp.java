package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class LayerEditForm extends BaseForm implements Serializable{

    private int level;

    private String name;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
