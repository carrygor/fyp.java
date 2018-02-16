package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>坐标轴在 <a href="#grid">grid</a> 区域中的分隔区域，默认不显示。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartRadiusAxisSplitArea implements Serializable {
    private static final long serialVersionUID = 1L;

    private AreaStyle areaStyle;

    private Boolean show;

    private Object interval;


    public AreaStyle getAreaStyle(){
        return areaStyle;
    }

    public EChartRadiusAxisSplitArea setAreaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartRadiusAxisSplitArea setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getInterval(){
        return interval;
    }

    public EChartRadiusAxisSplitArea setInterval(Object interval) {
        this.interval = interval;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (areaStyle != null)  {
            map.put("areaStyle", areaStyle.toMap());
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (interval != null)  {
            map.put("interval", interval);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
