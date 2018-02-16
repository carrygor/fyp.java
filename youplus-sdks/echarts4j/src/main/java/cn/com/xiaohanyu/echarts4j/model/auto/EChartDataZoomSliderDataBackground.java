package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>数据阴影的样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartDataZoomSliderDataBackground implements Serializable {
    private static final long serialVersionUID = 1L;

    private AreaStyle areaStyle;

    private LineStyle lineStyle;


    public AreaStyle getAreaStyle(){
        return areaStyle;
    }

    public EChartDataZoomSliderDataBackground setAreaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartDataZoomSliderDataBackground setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (areaStyle != null)  {
            map.put("areaStyle", areaStyle.toMap());
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
