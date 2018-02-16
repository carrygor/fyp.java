package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <p>还原 icon 样式设置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class ItemStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Normal normal;

    private Emphasis emphasis;


    public Normal getNormal(){
        return normal;
    }

    public ItemStyle setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public ItemStyle setEmphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
        return this;
    }


    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (normal != null)  {
            map.put("normal", normal.toMap());
        }
        if (emphasis != null)  {
            map.put("emphasis", emphasis.toMap());
        }

        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
