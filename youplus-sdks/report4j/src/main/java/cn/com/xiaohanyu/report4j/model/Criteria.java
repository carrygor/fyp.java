package cn.com.xiaohanyu.report4j.model;

import cn.com.xiaohanyu.report4j.enums.CriteriaTypeEnum;

import java.io.Serializable;
/**
 * Created by 严汉羽 on 2017/11/22.
 */
public class Criteria implements Serializable{

    /**
     * 条件类型
     */
    CriteriaTypeEnum criteriaTypeEnum;

    /**
     * 字段参数
     */
    String field;

    /**
     * 参数1
     */
    String paramOne;

    /**
     * 参数2
     */
    String paramTwo;

    public CriteriaTypeEnum getCriteriaTypeEnum() {
        return criteriaTypeEnum;
    }

    public Criteria setCriteriaTypeEnum(CriteriaTypeEnum criteriaTypeEnum) {
        this.criteriaTypeEnum = criteriaTypeEnum;
        return this;
    }

    public String getField() {
        return field;
    }

    public Criteria setField(String field) {
        this.field = field;
        return this;
    }

    public String getParamOne() {
        return paramOne;
    }

    public Criteria setParamOne(String paramOne) {
        this.paramOne = paramOne;
        return this;
    }

    public String getParamTwo() {
        return paramTwo;
    }

    public Criteria setParamTwo(String paramTwo) {
        this.paramTwo = paramTwo;
        return this;
    }
}
