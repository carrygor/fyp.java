package cn.com.youplus.apps.common.model.enums;

public enum EntranceEnum {

    LINK("LINK"),
    LINK_OAUTH("LINK_OAUTH"),
    WEIXIN("WEIXIN"),
    WEIXIN_BASE_OAUTH("WEIXIN_BASE_OAUTH"),
    WEIXIN_INFO_OAUTH("WEIXIN_INFO_OAUTH"),
    WEIXIN_COMPHONENT_BASE_OAUTH("WEIXIN_COMPHONENT_BASE_OAUTH"),
    WEIXIN_COMPHONENT_INFO_OAUTH("WEIXIN_COMPHONENT_INFO_OAUTH"),
    ;
    private String entrance;

    EntranceEnum(String entrance) {
        this.entrance = entrance;
    }

    public String getEntrance() {
        return entrance;
    }
}
