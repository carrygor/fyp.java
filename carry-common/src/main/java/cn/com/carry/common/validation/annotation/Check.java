package cn.com.carry.common.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 严汉羽 on 2017/7/6.
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
    /**
     * 是否非空
     */
    public boolean notNull() default false;

    /**
     * 判定手机号码
     */
    public boolean phone() default false;

    /**
     * 判定token
     */
    public boolean token() default false;

    /**
     * 判定module
     */
    public boolean module() default false;

    /**
     * 判定身份证号码
     */
    public boolean idcard() default false;

    /**
     * 是否为数值
     */
    public boolean numeric() default false;

    /**
     * 最大长度
     */
    public int maxLen() default -1;

    /**
     * 最小长度
     */
    public int minLen() default -1;

    /**
     * 最小数值
     */
    public long minNum() default -999999;

    /**
     * 最大数值
     */
    public long maxNum() default -1;

    /**
     * 检查是否为数值，0 - 99999.99
     */
    public boolean isDecimal() default false;

    /**
     * 检查日期： 1900-01-01
     */
    public boolean date() default false;

    /**
     * 检查日期： 1900-01-01 00:00:00
     */
    public boolean datetime() default false;

    /**
     * 检查日期： 1900-01-01 00:00:00
     */
    public boolean timestamp() default false;

}
