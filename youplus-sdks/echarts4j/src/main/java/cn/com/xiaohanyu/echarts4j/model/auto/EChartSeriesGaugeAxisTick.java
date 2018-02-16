package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>刻度样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGaugeAxisTick implements Serializable {
    private static final long serialVersionUID = 1L;

    private LineStyle lineStyle;

    private Boolean show;

    private Object length;

    private Number splitNumber;


    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesGaugeAxisTick setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartSeriesGaugeAxisTick setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getLength(){
        return length;
    }

    public EChartSeriesGaugeAxisTick setLength(Object length) {
        this.length = length;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartSeriesGaugeAxisTick setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (length != null)  {
            map.put("length", length);
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
