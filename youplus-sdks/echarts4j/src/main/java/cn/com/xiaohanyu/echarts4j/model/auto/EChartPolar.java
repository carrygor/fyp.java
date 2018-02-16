package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>极坐标系，可以用于散点图和折线图。每个极坐标系拥有一个<a href="#angleAxis">角度轴</a>和一个<a href="#radiusAxis">半径轴</a>。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-polar-punchCard&edit=1&reset=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartPolar implements Serializable {
    private static final long serialVersionUID = 1L;

    private List center;

    private EChartPolarTooltip tooltip;

    private Number zlevel;

    private Number z;

    private List radius;


    public List getCenter(){
        return center;
    }

    public EChartPolar setCenter(List center) {
        this.center = center;
        return this;
    }

    public EChartPolarTooltip getTooltip(){
        return tooltip;
    }

    public EChartPolar setTooltip(EChartPolarTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartPolar setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartPolar setZ(Number z) {
        this.z = z;
        return this;
    }

    public List getRadius(){
        return radius;
    }

    public EChartPolar setRadius(List radius) {
        this.radius = radius;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (center != null)  {
            map.put("center", center);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (radius != null)  {
            map.put("radius", radius);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
