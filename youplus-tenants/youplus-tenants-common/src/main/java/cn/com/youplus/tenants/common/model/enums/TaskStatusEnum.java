package cn.com.youplus.tenants.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum TaskStatusEnum {
    进行中(1,"进行中"),
    已完成(2,"已完成"),
    意外终止(3,"意外终止")

    //ENUM('进行中', '已完成', '意外终止')
    ;

    private int code;
    private String type;

    TaskStatusEnum(int code, String type) {
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
