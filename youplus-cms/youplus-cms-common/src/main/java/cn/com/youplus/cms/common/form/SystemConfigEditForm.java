package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.model.enums.SystemConfigTypeEnum;
import cn.com.youplus.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class SystemConfigEditForm extends SystemConfigAddForm {

    @Check(minNum = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
