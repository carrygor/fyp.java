package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>热力图</strong></p>
 * <p>热力图主要通过颜色去表现数值的大小，必须要配合 <a href="#visualMap">visualMap</a> 组件使用。</p>
 * <p>可以应用在<a href="#grid">直角坐标系</a>以及<a href="#geo">地理坐标系</a>上，这两个坐标系上的表现形式相差很大，直角坐标系上必须要使用两个类目轴。</p>
 * <p>下面分别是直角坐标系和地理坐标系上应用的例子：</p>
 * <p><strong>直角坐标系：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=heatmap-cartesian&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * <p><strong>地理坐标系：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=heatmap-map&edit=1&reset=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesHeatmap implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number blurSize;

    private Boolean silent;

    private List data;

    private Number xAxisIndex;

    private EChartSeriesHeatmapTooltip tooltip;

    private String type;

    private EChartSeriesHeatmapMarkLine markLine;

    private EChartSeriesHeatmapMarkPoint markPoint;

    private Number geoIndex;

    private Number minOpacity;

    private EChartSeriesHeatmapMarkArea markArea;

    private Number maxOpacity;

    private String name;

    private Number zlevel;

    private Number z;

    private String coordinateSystem;

    private Number calendarIndex;

    private Number yAxisIndex;


    public Number getBlurSize(){
        return blurSize;
    }

    public EChartSeriesHeatmap setBlurSize(Number blurSize) {
        this.blurSize = blurSize;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesHeatmap setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesHeatmap setData(List data) {
        this.data = data;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesHeatmap setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public EChartSeriesHeatmapTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesHeatmap setTooltip(EChartSeriesHeatmapTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesHeatmap setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesHeatmapMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesHeatmap setMarkLine(EChartSeriesHeatmapMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public EChartSeriesHeatmapMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesHeatmap setMarkPoint(EChartSeriesHeatmapMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesHeatmap setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public Number getMinOpacity(){
        return minOpacity;
    }

    public EChartSeriesHeatmap setMinOpacity(Number minOpacity) {
        this.minOpacity = minOpacity;
        return this;
    }

    public EChartSeriesHeatmapMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesHeatmap setMarkArea(EChartSeriesHeatmapMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getMaxOpacity(){
        return maxOpacity;
    }

    public EChartSeriesHeatmap setMaxOpacity(Number maxOpacity) {
        this.maxOpacity = maxOpacity;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesHeatmap setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesHeatmap setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesHeatmap setZ(Number z) {
        this.z = z;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesHeatmap setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Number getCalendarIndex(){
        return calendarIndex;
    }

    public EChartSeriesHeatmap setCalendarIndex(Number calendarIndex) {
        this.calendarIndex = calendarIndex;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesHeatmap setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (blurSize != null)  {
            map.put("blurSize", blurSize);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (geoIndex != null)  {
            map.put("geoIndex", geoIndex);
        }
        if (minOpacity != null)  {
            map.put("minOpacity", minOpacity);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (maxOpacity != null)  {
            map.put("maxOpacity", maxOpacity);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (calendarIndex != null)  {
            map.put("calendarIndex", calendarIndex);
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
