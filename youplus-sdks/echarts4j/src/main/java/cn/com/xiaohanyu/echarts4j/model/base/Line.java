package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>轴线</p>
 * </p>
 *
 * @author 小汉语
 */
public class Line implements Serializable {
    private static final long serialVersionUID = 1L;

    private List symbol;

    private LineStyle lineStyle;

    private Boolean onZero;

    private List symbolSize;

    private Boolean show;

    private Number onZeroAxisIndex;


    public List getSymbol(){
        return symbol;
    }

    public Line setSymbol(List symbol) {
        this.symbol = symbol;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public Line setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getOnZero(){
        return onZero;
    }

    public Line setOnZero(Boolean onZero) {
        this.onZero = onZero;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public Line setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public Line setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Number getOnZeroAxisIndex(){
        return onZeroAxisIndex;
    }

    public Line setOnZeroAxisIndex(Number onZeroAxisIndex) {
        this.onZeroAxisIndex = onZeroAxisIndex;
        return this;
    }


    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (onZero != null)  {
            map.put("onZero", onZero);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (onZeroAxisIndex != null)  {
            map.put("onZeroAxisIndex", onZeroAxisIndex);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
