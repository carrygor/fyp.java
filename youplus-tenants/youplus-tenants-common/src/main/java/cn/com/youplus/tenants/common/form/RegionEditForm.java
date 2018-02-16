package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class RegionEditForm extends RegionAddForm implements Serializable{

    @Check(minNum = 1)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
