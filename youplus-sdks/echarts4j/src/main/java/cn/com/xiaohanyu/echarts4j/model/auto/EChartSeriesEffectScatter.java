package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>带有涟漪特效动画的散点（气泡）图。利用动画特效可以将某些想要突出的数据进行视觉突出。</p>
 * <p><strong>Tip:</strong> ECharts 2.x 中在地图上通过 markPoint 实现地图特效在 ECharts 3 中建议通过地理坐标系上的 effectScatter 实现。</p>
 * <p><strong>如下示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=effectScatter-map&edit=1&reset=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesEffectScatter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Object encode;

    private String symbol;

    private List data;

    private EChartSeriesEffectScatterTooltip tooltip;

    private String type;

    private EChartSeriesEffectScatterMarkLine markLine;

    private Boolean legendHoverLink;

    private Number geoIndex;

    private EChartSeriesEffectScatterMarkArea markArea;

    private Number animationThreshold;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private List symbolSize;

    private Number xAxisIndex;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesEffectScatterLabel label;

    private String effectType;

    private EChartSeriesEffectScatterMarkPoint markPoint;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private Number symbolRotate;

    private List symbolOffset;

    private String name;

    private Number z;

    private String showEffectOn;

    private EChartSeriesEffectScatterRippleEffect rippleEffect;

    private Number polarIndex;

    private Number calendarIndex;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private List dimensions;


    public String getCursor(){
        return cursor;
    }

    public EChartSeriesEffectScatter setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getEncode(){
        return encode;
    }

    public EChartSeriesEffectScatter setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesEffectScatter setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesEffectScatter setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesEffectScatterTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesEffectScatter setTooltip(EChartSeriesEffectScatterTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesEffectScatter setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesEffectScatterMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesEffectScatter setMarkLine(EChartSeriesEffectScatterMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesEffectScatter setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesEffectScatter setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public EChartSeriesEffectScatterMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesEffectScatter setMarkArea(EChartSeriesEffectScatterMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesEffectScatter setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesEffectScatter setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesEffectScatter setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesEffectScatter setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesEffectScatter setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesEffectScatter setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesEffectScatter setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesEffectScatter setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesEffectScatter setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesEffectScatter setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesEffectScatterLabel getLabel(){
        return label;
    }

    public EChartSeriesEffectScatter setLabel(EChartSeriesEffectScatterLabel label) {
        this.label = label;
        return this;
    }

    public String getEffectType(){
        return effectType;
    }

    public EChartSeriesEffectScatter setEffectType(String effectType) {
        this.effectType = effectType;
        return this;
    }

    public EChartSeriesEffectScatterMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesEffectScatter setMarkPoint(EChartSeriesEffectScatterMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesEffectScatter setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesEffectScatter setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesEffectScatter setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesEffectScatter setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesEffectScatter setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesEffectScatter setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesEffectScatter setZ(Number z) {
        this.z = z;
        return this;
    }

    public String getShowEffectOn(){
        return showEffectOn;
    }

    public EChartSeriesEffectScatter setShowEffectOn(String showEffectOn) {
        this.showEffectOn = showEffectOn;
        return this;
    }

    public EChartSeriesEffectScatterRippleEffect getRippleEffect(){
        return rippleEffect;
    }

    public EChartSeriesEffectScatter setRippleEffect(EChartSeriesEffectScatterRippleEffect rippleEffect) {
        this.rippleEffect = rippleEffect;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartSeriesEffectScatter setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Number getCalendarIndex(){
        return calendarIndex;
    }

    public EChartSeriesEffectScatter setCalendarIndex(Number calendarIndex) {
        this.calendarIndex = calendarIndex;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesEffectScatter setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesEffectScatter setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesEffectScatter setDimensions(List dimensions) {
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
        if (effectType != null)  {
            map.put("effectType", effectType);
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
        if (showEffectOn != null)  {
            map.put("showEffectOn", showEffectOn);
        }
        if (rippleEffect != null)  {
            map.put("rippleEffect", rippleEffect.toMap());
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
