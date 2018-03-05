package cn.com.carry.common.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PermissionItem implements Serializable{

    private String permissionName;

    private String parentName;

    public PermissionItem(String permissionName, String parentName) {
        this.permissionName = permissionName;
        this.parentName = parentName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
