package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class CopyProjectForm extends BaseIdForm {

    @Check(notNull = true)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
