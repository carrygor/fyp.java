package cn.com.xiaohanyu.report4j.model;

import cn.com.xiaohanyu.report4j.enums.CriteriaTypeEnum;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/22.
 */
public class Column implements Serializable{

    /**
     * 字段参数
     */
    String field;

    /**
     * 别名,可以不设置
     */
    String as;

    public String getField() {
        return field;
    }

    public Column setField(String field) {
        this.field = field;
        return this;
    }

    public String getAs() {
        return as == null ? this.field : as;
    }

    public Column setAs(String as) {
        this.as = as;
        return this;
    }
}
