package cn.com.youplus.common.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PermissionTree implements Serializable{

    private int id;

    private String name;

    private List<PermissionTree> children;

    private transient int count;

    public PermissionTree(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PermissionTree(ResourcesPermission resourcesPermission) {
        new PermissionTree(resourcesPermission, 0);
    }

    public PermissionTree(ResourcesPermission resourcesPermission, int count) {
        this.count = count + 1;
        this.id = this.count;
        this.name = resourcesPermission.getName();
        if (resourcesPermission.getChildren() == null) {
            this.children = new ArrayList<>();
            List<String> actions = resourcesPermission.getActions();
            for (String action: actions) {
                this.count++;
                this.children.add(new PermissionTree(this.count, action));
            }
        } else {
            this.children = new ArrayList<>();
            for (ResourcesPermission resourcesPermission1: resourcesPermission.getChildren()) {
                PermissionTree permissionTree = new PermissionTree(resourcesPermission1, this.count);
                this.children.add(permissionTree);

                this.count = permissionTree.getCount();
            }

        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTree> children) {
        this.children = children;
    }
}
