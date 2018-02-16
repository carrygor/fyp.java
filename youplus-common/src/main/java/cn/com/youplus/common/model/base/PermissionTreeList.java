package cn.com.youplus.common.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PermissionTreeList extends ArrayList<PermissionTree> implements Serializable{

    private transient int count = 0;

    public PermissionTreeList(ResourcesPermissionList resourcesPermissions) {
        for (ResourcesPermission resourcesPermission: resourcesPermissions) {
            PermissionTree permissionTree = new PermissionTree(resourcesPermission, count);
            this.count = permissionTree.getCount();
            this.add(permissionTree);
        }
    }

    public List<Integer> getCheckedIds(PermissionTreeList permissionTreeList) {
        List<Integer> checkedIds = new ArrayList<>();
        List<PermissionItem> permissionNameList = new ArrayList<>();
        for (PermissionTree permissionTree: permissionTreeList){
            permissionNameList.addAll(getPermissionNameList(permissionTree, ""));
        }
        for (PermissionItem permissionItem: permissionNameList)  {
            checkedIds.add(getPermissionNameId(permissionItem));
        }

        return checkedIds;

    }

    private int getPermissionNameId(PermissionItem permissionItem) {
        for (PermissionTree permissionTree: this) {
            int id = 0;
            if (permissionTree.getName().equals(permissionItem.getPermissionName()) && permissionTree.getChildren() == null) {
                id = permissionTree.getId();
            } else if (permissionTree.getChildren() != null){
                id = getPermissionNameIdByList(permissionTree.getChildren(), permissionItem, permissionTree.getName());
            }
            if (id > 0) {
                return id;
            }
        }
        return 0;
    }

    private int getPermissionNameIdByList(List<PermissionTree> permissionTreeList, PermissionItem permissionItem, String parentName) {
        for (PermissionTree permissionTree: permissionTreeList) {
            int id = 0;
            if (permissionTree.getName().equals(permissionItem.getPermissionName()) && permissionTree.getChildren() == null) {
                id = permissionTree.getId();
                if (id > 0 && !parentName.equals(permissionItem.getParentName())) {
                    id = 0;
                }
            } else if (permissionTree.getChildren() != null){
                id = getPermissionNameIdByList(permissionTree.getChildren(), permissionItem, permissionTree.getName());
            }
            if (id > 0) {
                return id;
            }
        }
        return 0;
    }

    private static List<PermissionItem> getPermissionNameList(PermissionTree permissionTree, String parentName) {
        List<PermissionItem> list = new ArrayList<>();
        PermissionItem permissionItem = new PermissionItem(permissionTree.getName(), parentName);
        list.add(permissionItem);
        if (permissionTree.getChildren() != null) {
            for (PermissionTree permissionTree1: permissionTree.getChildren()) {
                list.addAll(getPermissionNameList(permissionTree1, permissionTree.getName()));
            }
        }

        return list;
    }

}
