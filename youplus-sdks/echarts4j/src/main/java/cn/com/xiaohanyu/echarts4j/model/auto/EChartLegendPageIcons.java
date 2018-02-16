package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><a href="#legend.type">legend.type</a> 为 <code>&#39;scroll&#39;</code> 时有效。</p>
 * <p>图例控制块的图标。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartLegendPageIcons implements Serializable {
    private static final long serialVersionUID = 1L;

    private List horizontal;

    private List vertical;


    public List getHorizontal(){
        return horizontal;
    }

    public EChartLegendPageIcons setHorizontal(List horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public List getVertical(){
        return vertical;
    }

    public EChartLegendPageIcons setVertical(List vertical) {
        this.vertical = vertical;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (horizontal != null)  {
            map.put("horizontal", horizontal);
        }
        if (vertical != null)  {
            map.put("vertical", vertical);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
