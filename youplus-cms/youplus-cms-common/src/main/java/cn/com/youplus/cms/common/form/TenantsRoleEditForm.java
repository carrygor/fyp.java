package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.validation.annotation.Check;

import java.io.Serializable;

public class TenantsRoleEditForm extends TenantsRoleAddForm implements Serializable{

    @Check(minNum = 1)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
