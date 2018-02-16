package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum TenantsCompanyTypeEnum {

    企业(0,"企业"),
    个人(1,"个人")
    ;

    private int code;
    private String type;

    TenantsCompanyTypeEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
