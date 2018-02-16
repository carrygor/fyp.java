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
public class EChartSeriesEffectScatterMarkAreaDataReplace1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number valueIndex;

    private List coord;

    private String valueDim;

    private Number x;

    private Number y;

    private ItemStyle itemStyle;

    private EChartSeriesEffectScatterMarkAreaDataReplace1Label label;

    private String type;

    private Number value;


    public Number getValueIndex(){
        return valueIndex;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setValueIndex(Number valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }

    public List getCoord(){
        return coord;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setCoord(List coord) {
        this.coord = coord;
        return this;
    }

    public String getValueDim(){
        return valueDim;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setValueDim(String valueDim) {
        this.valueDim = valueDim;
        return this;
    }

    public Number getX(){
        return x;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setY(Number y) {
        this.y = y;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1Label getLabel(){
        return label;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setLabel(EChartSeriesEffectScatterMarkAreaDataReplace1Label label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setType(String type) {
        this.type = type;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesEffectScatterMarkAreaDataReplace1 setValue(Number value) {
        this.value = value;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (valueIndex != null)  {
            map.put("valueIndex", valueIndex);
        }
        if (coord != null)  {
            map.put("coord", coord);
        }
        if (valueDim != null)  {
            map.put("valueDim", valueDim);
        }
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
        if (type != null)  {
            map.put("type", type);
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
