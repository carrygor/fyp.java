package cn.com.carry.common.model.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunctionList extends ArrayList<Function> implements Serializable{

    public FunctionList() {

    }

    public FunctionList(String jsonStr) {
        JSONArray jsonArray = JSONObject.parseArray(jsonStr);
        for (Object object : jsonArray) {
            this.add(new Function((JSONObject)object));
        }
    }

    public List<Integer> takeCheckedIds() {
        List<Integer> ids = new ArrayList<>();
        for (Function function: this) {
            ids.addAll(function.takeCheckedIds());
        }

        return ids;
    }

    public List<StringBuilder> toStringPermission() {
        List<StringBuilder> stringList = new ArrayList<>();
        for (Function function: this) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(function.getName());
            if (function.getChildren() != null) {
                stringList.addAll(function.toStringPermission(function.getName()));
            } else {
                stringList.add(stringBuilder);
            }
        }

        return stringList;
    }

}
