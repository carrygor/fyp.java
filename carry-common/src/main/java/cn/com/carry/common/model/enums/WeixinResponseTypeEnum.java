package cn.com.carry.common.model.enums;

/**
 * Created by pan on 2016/8/29.
 */
public enum WeixinResponseTypeEnum {
    默认回复(0,"默认回复"),
    默认关注回复(1,"默认关注回复"),
    扫码关注回复(2,"扫码关注回复"),
    关键字回复(3,"关键字回复"),
    菜单栏回复(4,"菜单栏回复")
    ;

    private int code;
    private String name;

    WeixinResponseTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
