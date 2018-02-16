package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>设置日历坐标分隔线的样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartCalendarSplitLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private LineStyle lineStyle;

    private Boolean show;


    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartCalendarSplitLine setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartCalendarSplitLine setShow(Boolean show) {
        this.show = show;
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
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
