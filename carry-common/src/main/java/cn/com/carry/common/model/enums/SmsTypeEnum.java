package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum SmsTypeEnum {

    VERIFY_CODE(1,"VERIFY_CODE"),
    NOTIFY(2,"NOTIFY")
    ;

    private int code;
    private String type;

    SmsTypeEnum(int code, String type) {
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
