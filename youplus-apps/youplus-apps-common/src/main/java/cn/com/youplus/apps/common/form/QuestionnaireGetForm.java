package cn.com.youplus.apps.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.form.BaseIdForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class QuestionnaireGetForm extends BaseIdForm implements Serializable {

    private Long accessLogId;

    public Long getAccessLogId() {
        return accessLogId;
    }

    public void setAccessLogId(Long accessLogId) {
        this.accessLogId = accessLogId;
    }
}
