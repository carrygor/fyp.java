package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>标域左上角的数据</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesPieMarkAreaDataReplace0 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number x;

    private Number y;

    private ItemStyle itemStyle;

    private EChartSeriesPieMarkAreaDataReplace0Label label;

    private Number value;


    public Number getX(){
        return x;
    }

    public EChartSeriesPieMarkAreaDataReplace0 setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartSeriesPieMarkAreaDataReplace0 setY(Number y) {
        this.y = y;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesPieMarkAreaDataReplace0 setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesPieMarkAreaDataReplace0Label getLabel(){
        return label;
    }

    public EChartSeriesPieMarkAreaDataReplace0 setLabel(EChartSeriesPieMarkAreaDataReplace0Label label) {
        this.label = label;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesPieMarkAreaDataReplace0 setValue(Number value) {
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