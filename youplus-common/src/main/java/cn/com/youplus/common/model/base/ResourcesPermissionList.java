package cn.com.youplus.common.model.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResourcesPermissionList extends ArrayList<ResourcesPermission> implements Serializable{

    public ResourcesPermissionList() {

    }

    public ResourcesPermissionList(String jsonStr) {
        JSONArray jsonArray = JSONObject.parseArray(jsonStr);
        for (Object object : jsonArray) {
            this.add(new ResourcesPermission((JSONObject)object));
        }
    }

    public List<StringBuilder> toStringPermission() {
        List<StringBuilder> StringList = new ArrayList<>();
        for (ResourcesPermission resourcesPermission: this) {
            StringBuilder StringPermission = new StringBuilder();
            StringPermission.append(resourcesPermission.getName());
            StringPermission.append(":");
            for (String action: resourcesPermission.getActions()) {
                StringPermission.append(action);
                StringPermission.append(",");
            }
            if (resourcesPermission.getChildren() != null) {
                String prefix1 = resourcesPermission.getName();
                StringList.addAll(ResourcesPermissionList.toStringPermission(resourcesPermission.getChildren(), prefix1));
            } else {
                StringPermission.deleteCharAt(StringPermission.length()-1);
                StringList.add(StringPermission);
            }
        }

        return StringList;
    }

    private static List<StringBuilder> toStringPermission(List<ResourcesPermission> resourcesPermissionList, String prefix) {
        List<StringBuilder> StringList = new ArrayList<>();
        for (ResourcesPermission resourcesPermission: resourcesPermissionList) {
            StringBuilder StringPermission = new StringBuilder();
            StringPermission.append(prefix);
            StringPermission.append(":");
            StringPermission.append(resourcesPermission.getName());
            StringPermission.append(":");
            for (String action: resourcesPermission.getActions()) {
                StringPermission.append(action);
                StringPermission.append(",");
            }
            if (resourcesPermission.getChildren() != null) {
                String prefix1 = prefix + ":" + resourcesPermission.getName() + ":";
                StringList.addAll(ResourcesPermissionList.toStringPermission(resourcesPermission.getChildren(), prefix1));
            } else {
                StringPermission.deleteCharAt(StringPermission.length()-1);
                StringList.add(StringPermission);
            }
        }
        return StringList;
    }
}
