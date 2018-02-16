package cn.com.xiaohanyu.report4j.enums;

/**
 * Created by 严汉羽 on 2017/11/21.
 */
public enum CriteriaTypeEnum {
    EQ,
    GT,
    GE,
    LT,
    LE,
    IN,
    NotIN,
    Like,
    Llike,
    Rlike,
    NotLike,
    NotLlike,
    NotRlike,
    Or,
    OrNew,
    And,
    AndNew,

    IsNull,
    IsNotNull,
    Exists,
    NotExists,

    Between,
    NotBetween,

    OrderAsc,
    OrderDesc,
    Having,
    GroupBy,
    Where
}
