package cn.com.youplus.apps.common.model.enums;

public enum PhoneListTypeEnum {

    whiteList("whiteList"),
    blackList("blackList"),

    ;
    private String type;

    PhoneListTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
