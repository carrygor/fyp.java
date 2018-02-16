package cn.com.xiaohanyu.report4j.enums;

/**
 * Created by 严汉羽 on 2017/11/21.
 */
public enum DataSourceStatusEnum {
    ERROR(-1),
    NULL(0),
    INITIAL(1),
    LOADED(2),
    FETCHED(3);

    int code;

    DataSourceStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public DataSourceStatusEnum setCode(int code) {
        this.code = code;
        return this;
    }
}
