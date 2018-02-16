package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>线图</strong></p>
 * <p>用于带有起点和终点信息的线数据的绘制，主要用于地图上的航线，路线的可视化。</p>
 * <p>ECharts 2.x 里会用地图上的 <code>markLine</code> 去绘制迁徙效果，在 ECharts 3 里建议使用单独的 <code>lines</code> 类型图表。</p>
 * <p><strong>迁徙示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=geo-lines&edit=1&reset=1" width="700" height="500" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesLines implements Serializable {
    private static final long serialVersionUID = 1L;

    private List symbol;

    private List data;

    private String type;

    private EChartSeriesLinesMarkLine markLine;

    private Number geoIndex;

    private EChartSeriesLinesMarkArea markArea;

    private Number animationThreshold;

    private Number largeThreshold;

    private LineStyle lineStyle;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean silent;

    private String animationEasing;

    private Boolean large;

    private Object animationDelay;

    private List symbolSize;

    private Number xAxisIndex;

    private Object animationDelayUpdate;

    private EChartSeriesLinesLabel label;

    private EChartSeriesLinesMarkPoint markPoint;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private EChartSeriesLinesEffect effect;

    private String name;

    private Number z;

    private Boolean polyline;

    private Object animationDurationUpdate;

    private Number yAxisIndex;


    public List getSymbol(){
        return symbol;
    }

    public EChartSeriesLines setSymbol(List symbol) {
        this.symbol = symbol;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesLines setData(List data) {
        this.data = data;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesLines setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesLinesMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesLines setMarkLine(EChartSeriesLinesMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesLines setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public EChartSeriesLinesMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesLines setMarkArea(EChartSeriesLinesMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesLines setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getLargeThreshold(){
        return largeThreshold;
    }

    public EChartSeriesLines setLargeThreshold(Number largeThreshold) {
        this.largeThreshold = largeThreshold;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesLines setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesLines setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesLines setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesLines setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesLines setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Boolean getLarge(){
        return large;
    }

    public EChartSeriesLines setLarge(Boolean large) {
        this.large = large;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesLines setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesLines setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesLines setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesLines setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public EChartSeriesLinesLabel getLabel(){
        return label;
    }

    public EChartSeriesLines setLabel(EChartSeriesLinesLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesLinesMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesLines setMarkPoint(EChartSeriesLinesMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesLines setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesLines setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesLines setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public EChartSeriesLinesEffect getEffect(){
        return effect;
    }

    public EChartSeriesLines setEffect(EChartSeriesLinesEffect effect) {
        this.effect = effect;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesLines setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesLines setZ(Number z) {
        this.z = z;
        return this;
    }

    public Boolean getPolyline(){
        return polyline;
    }

    public EChartSeriesLines setPolyline(Boolean polyline) {
        this.polyline = polyline;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesLines setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesLines setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (geoIndex != null)  {
            map.put("geoIndex", geoIndex);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (largeThreshold != null)  {
            map.put("largeThreshold", largeThreshold);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (large != null)  {
            map.put("large", large);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (effect != null)  {
            map.put("effect", effect.toMap());
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (polyline != null)  {
            map.put("polyline", polyline);
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
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
