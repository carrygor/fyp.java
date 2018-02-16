package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>坐标轴轴线相关设置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartXAxisAxisLine implements Serializable {
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

    public EChartXAxisAxisLine setSymbol(List symbol) {
        this.symbol = symbol;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartXAxisAxisLine setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getOnZero(){
        return onZero;
    }

    public EChartXAxisAxisLine setOnZero(Boolean onZero) {
        this.onZero = onZero;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartXAxisAxisLine setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartXAxisAxisLine setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Number getOnZeroAxisIndex(){
        return onZeroAxisIndex;
    }

    public EChartXAxisAxisLine setOnZeroAxisIndex(Number onZeroAxisIndex) {
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
