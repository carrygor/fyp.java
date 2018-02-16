package cn.com.youplus.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum WeixinMessageTypeEnum  implements Serializable {
    NEWS("NEWS"),
    TEXT("TEXT"),
    IMAGE("IMAGE"),
    VOICE("VOICE")
    ;

    private String type;

    WeixinMessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
