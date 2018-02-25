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
public class EChartSeriesPictorialBarMarkAreaDataReplace0 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number valueIndex;

    private List coord;

    private String valueDim;

    private Number x;

    private Number y;

    private ItemStyle itemStyle;

    private EChartSeriesPictorialBarMarkAreaDataReplace0Label label;

    private String type;

    private Number value;


    public Number getValueIndex(){
        return valueIndex;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setValueIndex(Number valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }

    public List getCoord(){
        return coord;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setCoord(List coord) {
        this.coord = coord;
        return this;
    }

    public String getValueDim(){
        return valueDim;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setValueDim(String valueDim) {
        this.valueDim = valueDim;
        return this;
    }

    public Number getX(){
        return x;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setY(Number y) {
        this.y = y;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0Label getLabel(){
        return label;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setLabel(EChartSeriesPictorialBarMarkAreaDataReplace0Label label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setType(String type) {
        this.type = type;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesPictorialBarMarkAreaDataReplace0 setValue(Number value) {
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