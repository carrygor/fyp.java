package cn.com.youplus.common.model.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Function implements Serializable{

    final static String ID_KEY = "id";
    final static String CHILDREN_KEY = "children";
    final static String NAME_KEY = "name";

    private int id;

    private String name;

    private FunctionList children;

    public Function() {

    }

    /**
     * 客户功能模块
     * @param json
     */
    public Function(JSONObject json) {
        this.name = json.getString(NAME_KEY);
        this.id = json.getInteger(ID_KEY);

        JSONArray childrenJson = json.getJSONArray(CHILDREN_KEY);
        if (childrenJson != null) {
            this.children = new FunctionList();
            for (Object child : childrenJson) {
                this.children.add(new Function((JSONObject) child));
            }
        }
    }

    public List<Integer> takeCheckedIds() {
        List<Integer> ids = new ArrayList<>();
        if (children != null) {
            ids.addAll(children.takeCheckedIds());
        } else {
            ids.add(id);
        }
        return ids;
    }

    public List<StringBuilder> toStringPermission(String prefix) {
        List<StringBuilder> stringList = new ArrayList<>();
        if (this.children != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(prefix)
                    .append(":");
            for (Function function: this.children) {
                if (function.getChildren() != null) {
                    stringList.addAll(function.toStringPermission(prefix + ":" + function.getName()));
                } else {
                    stringBuilder.append(function.getName())
                            .append(",");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringList.add(stringBuilder);
        } else {
            stringList.add(new StringBuilder(prefix));
        }

        return stringList;
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

    public FunctionList getChildren() {
        return children;
    }

    public void setChildren(FunctionList children) {
        this.children = children;
    }
}
