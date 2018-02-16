package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>分隔线样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGaugeSplitLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private LineStyle lineStyle;

    private Boolean show;

    private Object length;


    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesGaugeSplitLine setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartSeriesGaugeSplitLine setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getLength(){
        return length;
    }

    public EChartSeriesGaugeSplitLine setLength(Object length) {
        this.length = length;
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
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
