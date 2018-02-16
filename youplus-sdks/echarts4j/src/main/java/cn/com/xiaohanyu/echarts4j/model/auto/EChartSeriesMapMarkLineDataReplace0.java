package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>起点的数据。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesMapMarkLineDataReplace0 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number valueIndex;

    private String symbol;

    private List coord;

    private Number symbolRotate;

    private LineStyle lineStyle;

    private String valueDim;

    private List symbolSize;

    private List symbolOffset;

    private Number x;

    private Number y;

    private EChartSeriesMapMarkLineDataReplace0Label label;

    private Number value;


    public Number getValueIndex(){
        return valueIndex;
    }

    public EChartSeriesMapMarkLineDataReplace0 setValueIndex(Number valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }

    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesMapMarkLineDataReplace0 setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public List getCoord(){
        return coord;
    }

    public EChartSeriesMapMarkLineDataReplace0 setCoord(List coord) {
        this.coord = coord;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesMapMarkLineDataReplace0 setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesMapMarkLineDataReplace0 setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public String getValueDim(){
        return valueDim;
    }

    public EChartSeriesMapMarkLineDataReplace0 setValueDim(String valueDim) {
        this.valueDim = valueDim;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesMapMarkLineDataReplace0 setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesMapMarkLineDataReplace0 setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public Number getX(){
        return x;
    }

    public EChartSeriesMapMarkLineDataReplace0 setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartSeriesMapMarkLineDataReplace0 setY(Number y) {
        this.y = y;
        return this;
    }

    public EChartSeriesMapMarkLineDataReplace0Label getLabel(){
        return label;
    }

    public EChartSeriesMapMarkLineDataReplace0 setLabel(EChartSeriesMapMarkLineDataReplace0Label label) {
        this.label = label;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesMapMarkLineDataReplace0 setValue(Number value) {
        this.value = value;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (valueIndex != null)  {
            map.put("valueIndex", valueIndex);
        }
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (coord != null)  {
            map.put("coord", coord);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (valueDim != null)  {
            map.put("valueDim", valueDim);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (x != null)  {
            map.put("x", x);
        }
        if (y != null)  {
            map.put("y", y);
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
