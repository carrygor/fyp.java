package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>折线/面积图</strong></p>
 * <p>折线图是用折线将各个数据点<a href="#series-line.symbol">标志</a>连接起来的图表，用于展现数据的变化趋势。可用于<a href="#grid">直角坐标系</a>和<a href="#polar">极坐标系</a>上。</p>
 * <p><strong>Tip:</strong> 设置 <a href="#series-line.areaStyle">areaStyle</a> 后可以绘制面积图。</p>
 * <p><strong>Tip:</strong> 配合分段型 <a href="#visualMap-piecewise">visualMap</a> 组件可以将折线/面积图通过不同颜色分区间。如下示例</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=line-aqi&edit=1&reset=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Object encode;

    private String symbol;

    private String stack;

    private Boolean showSymbol;

    private List data;

    private EChartSeriesLineTooltip tooltip;

    private String type;

    private EChartSeriesLineMarkLine markLine;

    private Boolean showAllSymbol;

    private Boolean legendHoverLink;

    private EChartSeriesLineMarkArea markArea;

    private Number animationThreshold;

    private LineStyle lineStyle;

    private Boolean clipOverflow;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean smooth;

    private Boolean silent;

    private String animationEasing;

    private Boolean connectNulls;

    private Object animationDelay;

    private Object symbolSize;

    private String smoothMonotone;

    private Number xAxisIndex;

    private String sampling;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesLineLabel label;

    private EChartSeriesLineMarkPoint markPoint;

    private Boolean animation;

    private Boolean hoverAnimation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private Number symbolRotate;

    private AreaStyle areaStyle;

    private List symbolOffset;

    private String name;

    private Object step;

    private Number z;

    private Number polarIndex;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private List dimensions;


    public String getCursor(){
        return cursor;
    }

    public EChartSeriesLine setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getEncode(){
        return encode;
    }

    public EChartSeriesLine setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesLine setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getStack(){
        return stack;
    }

    public EChartSeriesLine setStack(String stack) {
        this.stack = stack;
        return this;
    }

    public Boolean getShowSymbol(){
        return showSymbol;
    }

    public EChartSeriesLine setShowSymbol(Boolean showSymbol) {
        this.showSymbol = showSymbol;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesLine setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesLineTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesLine setTooltip(EChartSeriesLineTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesLine setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesLineMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesLine setMarkLine(EChartSeriesLineMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getShowAllSymbol(){
        return showAllSymbol;
    }

    public EChartSeriesLine setShowAllSymbol(Boolean showAllSymbol) {
        this.showAllSymbol = showAllSymbol;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesLine setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public EChartSeriesLineMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesLine setMarkArea(EChartSeriesLineMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesLine setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesLine setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getClipOverflow(){
        return clipOverflow;
    }

    public EChartSeriesLine setClipOverflow(Boolean clipOverflow) {
        this.clipOverflow = clipOverflow;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesLine setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesLine setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSmooth(){
        return smooth;
    }

    public EChartSeriesLine setSmooth(Boolean smooth) {
        this.smooth = smooth;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesLine setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesLine setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Boolean getConnectNulls(){
        return connectNulls;
    }

    public EChartSeriesLine setConnectNulls(Boolean connectNulls) {
        this.connectNulls = connectNulls;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesLine setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesLine setSymbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public String getSmoothMonotone(){
        return smoothMonotone;
    }

    public EChartSeriesLine setSmoothMonotone(String smoothMonotone) {
        this.smoothMonotone = smoothMonotone;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesLine setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public String getSampling(){
        return sampling;
    }

    public EChartSeriesLine setSampling(String sampling) {
        this.sampling = sampling;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesLine setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesLine setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesLineLabel getLabel(){
        return label;
    }

    public EChartSeriesLine setLabel(EChartSeriesLineLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesLineMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesLine setMarkPoint(EChartSeriesLineMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesLine setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesLine setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesLine setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesLine setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesLine setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public AreaStyle getAreaStyle(){
        return areaStyle;
    }

    public EChartSeriesLine setAreaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesLine setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesLine setName(String name) {
        this.name = name;
        return this;
    }

    public Object getStep(){
        return step;
    }

    public EChartSeriesLine setStep(Object step) {
        this.step = step;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesLine setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartSeriesLine setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesLine setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesLine setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesLine setDimensions(List dimensions) {
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
        if (stack != null)  {
            map.put("stack", stack);
        }
        if (showSymbol != null)  {
            map.put("showSymbol", showSymbol);
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
        if (showAllSymbol != null)  {
            map.put("showAllSymbol", showAllSymbol);
        }
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (clipOverflow != null)  {
            map.put("clipOverflow", clipOverflow);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (smooth != null)  {
            map.put("smooth", smooth);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (connectNulls != null)  {
            map.put("connectNulls", connectNulls);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (smoothMonotone != null)  {
            map.put("smoothMonotone", smoothMonotone);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (sampling != null)  {
            map.put("sampling", sampling);
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
        if (areaStyle != null)  {
            map.put("areaStyle", areaStyle.toMap());
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (step != null)  {
            map.put("step", step);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (polarIndex != null)  {
            map.put("polarIndex", polarIndex);
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
