package cn.com.youplus.common.model.enums;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum TenantsUserCodeEnum implements Serializable {

    /**
     * 系统配置
     */
    默认用户(0, "默认用户"),
    管理员(100, "管理员"),
    区域负责人(200, "区域负责人"),
    区域用户(300, "区域用户"),
    网点负责人(400, "网点负责人"),
    网点用户(500, "网点用户");

    private int code;

    private String roleName;

    TenantsUserCodeEnum(int code, String roleName) {
        this.code = code;
        this.roleName = roleName;
    }

    public int getCode() {
        return code;
    }

    public String getRoleName() {
        return roleName;
    }
}
