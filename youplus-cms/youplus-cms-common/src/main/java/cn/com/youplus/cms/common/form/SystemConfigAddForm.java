package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;
import cn.com.youplus.common.validation.annotation.Valid;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class SystemConfigAddForm extends SystemConfigForm implements Serializable{

    /**
     * 参数的key
     */
    @Check(notNull = true, minLen = 1)
    private String attributeKey;
    /**
     * 参数的值
     */
    @Check(notNull = true, minLen = 1)
    private String attributeValue;
    /**
     * 说明
     */
    @Check(notNull = true, minLen = 1)
    private String description;
    /**
     * 排序
     */
    @Check(minNum = 1)
    private Integer sort;

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
