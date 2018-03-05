package cn.com.carry.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum WeixinResopnseMatchTypeEnum implements Serializable {
    完全匹配("完全匹配"),
    包含匹配("包含匹配")
    ;
    //'默认回复','默认关注回复','扫码关注回复','关键字回复','菜单栏回复','其他回复'

    private String type;

    WeixinResopnseMatchTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
