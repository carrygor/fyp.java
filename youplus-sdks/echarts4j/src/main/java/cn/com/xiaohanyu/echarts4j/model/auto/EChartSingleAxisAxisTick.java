package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>坐标轴刻度相关设置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSingleAxisAxisTick implements Serializable {
    private static final long serialVersionUID = 1L;

    private LineStyle lineStyle;

    private Boolean show;

    private Number length;

    private Object interval;

    private Boolean inside;

    private Boolean alignWithLabel;


    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSingleAxisAxisTick setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartSingleAxisAxisTick setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Number getLength(){
        return length;
    }

    public EChartSingleAxisAxisTick setLength(Number length) {
        this.length = length;
        return this;
    }

    public Object getInterval(){
        return interval;
    }

    public EChartSingleAxisAxisTick setInterval(Object interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getInside(){
        return inside;
    }

    public EChartSingleAxisAxisTick setInside(Boolean inside) {
        this.inside = inside;
        return this;
    }

    public Boolean getAlignWithLabel(){
        return alignWithLabel;
    }

    public EChartSingleAxisAxisTick setAlignWithLabel(Boolean alignWithLabel) {
        this.alignWithLabel = alignWithLabel;
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
        if (interval != null)  {
            map.put("interval", interval);
        }
        if (inside != null)  {
            map.put("inside", inside);
        }
        if (alignWithLabel != null)  {
            map.put("alignWithLabel", alignWithLabel);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
