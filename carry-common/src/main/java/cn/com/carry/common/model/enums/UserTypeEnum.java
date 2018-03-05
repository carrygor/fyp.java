package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum UserTypeEnum {

    APPS(1,"APPS"),
    TENANTS(2,"TENANTS"),
    THIRDPARTY(3,"THIRDPARTY"),
    CMS(4,"CMS")
    ;

    private int code;
    private String type;

    UserTypeEnum(int code, String type) {
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
