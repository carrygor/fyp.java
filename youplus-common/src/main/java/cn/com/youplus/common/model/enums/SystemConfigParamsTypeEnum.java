package cn.com.youplus.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum SystemConfigParamsTypeEnum implements Serializable {
    系统参数("系统参数"),
    网点参数("网点参数"),
    用户参数("用户参数"),
    第三方参数("第三方参数")
    ;

    private String type;

    SystemConfigParamsTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
