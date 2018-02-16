package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class TitleForm extends BaseForm {

    @Check(notNull = true)
    private String title;

    public String getTitle() {
        return title;
    }

    public TitleForm setTitle(String title) {
        this.title = title;
        return this;
    }
}
