package cn.com.carry.common.form;

import cn.com.carry.common.validation.annotation.Check;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BaseIdAndQidForm extends BaseIdForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Check(numeric = true, minNum = 1)
    private Long questionnaireId;

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
