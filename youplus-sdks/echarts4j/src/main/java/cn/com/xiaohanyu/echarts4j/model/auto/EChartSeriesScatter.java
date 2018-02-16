package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>散点（气泡）图。<a href="#grid">直角坐标系</a>上的散点图可以用来展现数据的 <code>x</code>，<code>y</code> 之间的关系，如果数据项有多个维度，其它维度的值可以通过不同大小的 <a href="#series-scatter.symbol">symbol</a> 展现成气泡图，也可以用颜色来表现。这些可以配合 <a href="#visualMap">visualMap</a> 组件完成。</p>
 * <p>可以应用在<a href="#grid">直角坐标系</a>，<a href="#polar">极坐标系</a>，<a href="#geo">地理坐标系</a>上。</p>
 * <p><strong>Tip:</strong> ECharts 2.x 中在地图上通过 markPoint 标记大量数据点方式在 ECharts 3 中建议通过<a href="#geo">地理坐标系</a>上的 scatter 实现。下面示例就是在中国地图上用散点图展现了空气质量的分布。并且用 <a href="#visualMap">visualMap</a> 组件将 PM2.5 映射到了颜色。</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-map&edit=1&reset=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesScatter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Object encode;

    private String symbol;

    private List data;

    private EChartSeriesScatterTooltip tooltip;

    private String type;

    private EChartSeriesScatterMarkLine markLine;

    private Boolean legendHoverLink;

    private Number geoIndex;

    private EChartSeriesScatterMarkArea markArea;

    private Number animationThreshold;

    private Number largeThreshold;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean silent;

    private String animationEasing;

    private Boolean large;

    private Object animationDelay;

    private Object symbolSize;

    private Number xAxisIndex;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesScatterLabel label;

    private EChartSeriesScatterMarkPoint markPoint;

    private Boolean animation;

    private Boolean hoverAnimation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private Number symbolRotate;

    private List symbolOffset;

    private String name;

    private Number z;

    private Number polarIndex;

    private Number calendarIndex;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private List dimensions;


    public String getCursor(){
        return cursor;
    }

    public EChartSeriesScatter setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getEncode(){
        return encode;
    }

    public EChartSeriesScatter setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesScatter setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesScatter setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesScatterTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesScatter setTooltip(EChartSeriesScatterTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesScatter setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesScatterMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesScatter setMarkLine(EChartSeriesScatterMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesScatter setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesScatter setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public EChartSeriesScatterMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesScatter setMarkArea(EChartSeriesScatterMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesScatter setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getLargeThreshold(){
        return largeThreshold;
    }

    public EChartSeriesScatter setLargeThreshold(Number largeThreshold) {
        this.largeThreshold = largeThreshold;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesScatter setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesScatter setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesScatter setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesScatter setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Boolean getLarge(){
        return large;
    }

    public EChartSeriesScatter setLarge(Boolean large) {
        this.large = large;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesScatter setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesScatter setSymbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesScatter setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesScatter setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesScatter setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesScatterLabel getLabel(){
        return label;
    }

    public EChartSeriesScatter setLabel(EChartSeriesScatterLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesScatterMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesScatter setMarkPoint(EChartSeriesScatterMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesScatter setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesScatter setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesScatter setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesScatter setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesScatter setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesScatter setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesScatter setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesScatter setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartSeriesScatter setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Number getCalendarIndex(){
        return calendarIndex;
    }

    public EChartSeriesScatter setCalendarIndex(Number calendarIndex) {
        this.calendarIndex = calendarIndex;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesScatter setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesScatter setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesScatter setDimensions(List dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cursor != null)  {
            map.put("cursor", cursor);
        }
        if (encode != null)  {
            map.put("encode", encode);
        }
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (data != null)  {
            map.put("data", data);
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
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
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
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
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
        if (hoverAnimation != null)  {
            map.put("hoverAnimation", hoverAnimation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (polarIndex != null)  {
            map.put("polarIndex", polarIndex);
        }
        if (calendarIndex != null)  {
            map.put("calendarIndex", calendarIndex);
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        if (dimensions != null)  {
            map.put("dimensions", dimensions);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
