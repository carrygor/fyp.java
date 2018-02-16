package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>数据区域缩放。目前只支持直角坐标系的缩放。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureDataZoom implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object xAxisIndex;

    private Boolean show;

    private EChartToolboxFeatureDataZoomIcon icon;

    private EChartToolboxFeatureDataZoomTitle title;

    private IconStyle iconStyle;

    private Object yAxisIndex;


    public Object getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartToolboxFeatureDataZoom setXAxisIndex(Object xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartToolboxFeatureDataZoom setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public EChartToolboxFeatureDataZoomIcon getIcon(){
        return icon;
    }

    public EChartToolboxFeatureDataZoom setIcon(EChartToolboxFeatureDataZoomIcon icon) {
        this.icon = icon;
        return this;
    }

    public EChartToolboxFeatureDataZoomTitle getTitle(){
        return title;
    }

    public EChartToolboxFeatureDataZoom setTitle(EChartToolboxFeatureDataZoomTitle title) {
        this.title = title;
        return this;
    }

    public IconStyle getIconStyle(){
        return iconStyle;
    }

    public EChartToolboxFeatureDataZoom setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    public Object getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartToolboxFeatureDataZoom setYAxisIndex(Object yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (icon != null)  {
            map.put("icon", icon.toMap());
        }
        if (title != null)  {
            map.put("title", title.toMap());
        }
        if (iconStyle != null)  {
            map.put("iconStyle", iconStyle.toMap());
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
