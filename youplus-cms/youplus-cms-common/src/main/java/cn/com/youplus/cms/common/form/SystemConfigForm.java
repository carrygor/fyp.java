package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.common.validation.annotation.Check;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class SystemConfigForm extends BaseForm {

    @Check(notNull = true)
    private String parameterType;

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public void validType() throws AlertException {
        try {
            SystemConfigParamsTypeEnum.valueOf(this.getParameterType());
        } catch (Exception e) {
            throw new AlertException("配置类型错误：[" + this.getParameterType() + "]");
        }
    }
}
