package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>标域右下角的数据</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesHeatmapMarkAreaDataReplace1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number x;

    private Number y;

    private ItemStyle itemStyle;

    private EChartSeriesHeatmapMarkAreaDataReplace1Label label;

    private Number value;


    public Number getX(){
        return x;
    }

    public EChartSeriesHeatmapMarkAreaDataReplace1 setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartSeriesHeatmapMarkAreaDataReplace1 setY(Number y) {
        this.y = y;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesHeatmapMarkAreaDataReplace1 setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesHeatmapMarkAreaDataReplace1Label getLabel(){
        return label;
    }

    public EChartSeriesHeatmapMarkAreaDataReplace1 setLabel(EChartSeriesHeatmapMarkAreaDataReplace1Label label) {
        this.label = label;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesHeatmapMarkAreaDataReplace1 setValue(Number value) {
        this.value = value;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (x != null)  {
            map.put("x", x);
        }
        if (y != null)  {
            map.put("y", y);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (value != null)  {
            map.put("value", value);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
