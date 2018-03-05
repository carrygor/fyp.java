package cn.com.carry.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum SystemConfigTypeEnum implements Serializable {
    APPS_SYSTEM_CONFIG("APPS_SYSTEM_CONFIG"),
    TENANTS_SYSTEM_CONFIG("TENANTS_SYSTEM_CONFIG"),
    CMS_SYSTEM_CONFIG("CMS_SYSTEM_CONFIG"),
    THIRDPARTY_SYSTEM_CONFIG("THIRDPARTY_SYSTEM_CONFIG")
    ;

    private String type;

    SystemConfigTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
