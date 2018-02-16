package cn.com.youplus.tenants.common.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum InfoTypeEnum {
    unauthorized(0,"取消授权"),
    updateauthorized(1,"更新授权"),
    authorized(1,"授权成功"),
    component_verify_ticket(1,"component_verify_ticket"),

    ;

    private int code;
    private String type;

    InfoTypeEnum(int code, String type) {
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
