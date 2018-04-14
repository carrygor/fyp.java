package cn.com.carry.tenants.common.form;

import cn.com.carry.common.form.BaseIdForm;
import cn.com.carry.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class KeywordForm extends BaseIdForm {

    @Check(notNull = true)
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
