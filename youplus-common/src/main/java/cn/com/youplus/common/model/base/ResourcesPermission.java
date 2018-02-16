package cn.com.youplus.common.model.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResourcesPermission implements Serializable{

    final static String ACTIONS_KEY = "actions";
    final static String CHILDREN_KEY = "children";
    final static String NAME_KEY = "name";

    private String name;

    private List<String> actions;

    public List<ResourcesPermission> getChildren() {
        return children;
    }

    public void setChildren(List<ResourcesPermission> children) {
        this.children = children;
    }

    private List<ResourcesPermission> children;

    public ResourcesPermission() {

    }

    /**
     * 后台shiro权限所需数据
     * @param json
     */
    public ResourcesPermission(JSONObject json) {
        this.actions = new ArrayList<>();
        for (Object object :json.getJSONArray(ACTIONS_KEY)) {
            this.actions.add((String)object);
        }
        this.name = json.getString(NAME_KEY);

        JSONArray childrenJson = json.getJSONArray(CHILDREN_KEY);
        if (childrenJson != null) {
            this.children = new ArrayList<>();
            for (Object child : childrenJson) {
                this.children.add(new ResourcesPermission((JSONObject) child));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

}
