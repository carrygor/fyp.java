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
public class EChartSeriesPieMarkLineDataReplace0 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    private Number symbolRotate;

    private LineStyle lineStyle;

    private List symbolSize;

    private List symbolOffset;

    private Number x;

    private Number y;

    private EChartSeriesPieMarkLineDataReplace0Label label;

    private Number value;


    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesPieMarkLineDataReplace0 setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesPieMarkLineDataReplace0 setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesPieMarkLineDataReplace0 setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesPieMarkLineDataReplace0 setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesPieMarkLineDataReplace0 setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public Number getX(){
        return x;
    }

    public EChartSeriesPieMarkLineDataReplace0 setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartSeriesPieMarkLineDataReplace0 setY(Number y) {
        this.y = y;
        return this;
    }

    public EChartSeriesPieMarkLineDataReplace0Label getLabel(){
        return label;
    }

    public EChartSeriesPieMarkLineDataReplace0 setLabel(EChartSeriesPieMarkLineDataReplace0Label label) {
        this.label = label;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesPieMarkLineDataReplace0 setValue(Number value) {
        this.value = value;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
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
