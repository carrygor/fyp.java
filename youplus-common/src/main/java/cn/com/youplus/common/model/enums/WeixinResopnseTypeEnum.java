package cn.com.youplus.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum WeixinResopnseTypeEnum implements Serializable {
    默认回复("默认回复"),
    默认关注回复("默认关注回复"),
    扫码关注回复("扫码关注回复"),
    关键字回复("关键字回复"),
    菜单栏回复("菜单栏回复"),
    其他回复("其他回复")
    ;
    //'默认回复','默认关注回复','扫码关注回复','关键字回复','菜单栏回复','其他回复'

    private String type;

    WeixinResopnseTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
