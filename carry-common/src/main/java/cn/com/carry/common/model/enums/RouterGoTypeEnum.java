package cn.com.carry.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum RouterGoTypeEnum implements Serializable {
    GO("go"),
    REPLACE("replace")
    ;

    private String type;

    RouterGoTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
