package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>柱状/条形图</strong></p>
 * <p>柱状/条形图 通过 柱形的高度/条形的宽度 来表现数据的大小，用于有至少一个类目轴或时间轴的<a href="#grid">直角坐标系</a>上。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesBar implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Object barWidth;

    private Object encode;

    private String stack;

    private List data;

    private String barGap;

    private EChartSeriesBarTooltip tooltip;

    private String type;

    private EChartSeriesBarMarkLine markLine;

    private Boolean legendHoverLink;

    private EChartSeriesBarMarkArea markArea;

    private Number animationThreshold;

    private Number zlevel;

    private String coordinateSystem;

    private Number barMinHeight;

    private String barCategoryGap;

    private Boolean silent;

    private String animationEasing;

    private Object barMaxWidth;

    private Object animationDelay;

    private Number xAxisIndex;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesBarLabel label;

    private EChartSeriesBarMarkPoint markPoint;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private String name;

    private Number z;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private List dimensions;


    public String getCursor(){
        return cursor;
    }

    public EChartSeriesBar setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getBarWidth(){
        return barWidth;
    }

    public EChartSeriesBar setBarWidth(Object barWidth) {
        this.barWidth = barWidth;
        return this;
    }

    public Object getEncode(){
        return encode;
    }

    public EChartSeriesBar setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public String getStack(){
        return stack;
    }

    public EChartSeriesBar setStack(String stack) {
        this.stack = stack;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesBar setData(List data) {
        this.data = data;
        return this;
    }

    public String getBarGap(){
        return barGap;
    }

    public EChartSeriesBar setBarGap(String barGap) {
        this.barGap = barGap;
        return this;
    }

    public EChartSeriesBarTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesBar setTooltip(EChartSeriesBarTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesBar setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesBarMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesBar setMarkLine(EChartSeriesBarMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesBar setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public EChartSeriesBarMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesBar setMarkArea(EChartSeriesBarMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesBar setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesBar setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesBar setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Number getBarMinHeight(){
        return barMinHeight;
    }

    public EChartSeriesBar setBarMinHeight(Number barMinHeight) {
        this.barMinHeight = barMinHeight;
        return this;
    }

    public String getBarCategoryGap(){
        return barCategoryGap;
    }

    public EChartSeriesBar setBarCategoryGap(String barCategoryGap) {
        this.barCategoryGap = barCategoryGap;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesBar setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesBar setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getBarMaxWidth(){
        return barMaxWidth;
    }

    public EChartSeriesBar setBarMaxWidth(Object barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesBar setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesBar setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesBar setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesBar setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesBarLabel getLabel(){
        return label;
    }

    public EChartSeriesBar setLabel(EChartSeriesBarLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesBarMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesBar setMarkPoint(EChartSeriesBarMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesBar setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesBar setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesBar setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesBar setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesBar setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesBar setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesBar setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesBar setDimensions(List dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cursor != null)  {
            map.put("cursor", cursor);
        }
        if (barWidth != null)  {
            map.put("barWidth", barWidth);
        }
        if (encode != null)  {
            map.put("encode", encode);
        }
        if (stack != null)  {
            map.put("stack", stack);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (barGap != null)  {
            map.put("barGap", barGap);
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
        if (barMinHeight != null)  {
            map.put("barMinHeight", barMinHeight);
        }
        if (barCategoryGap != null)  {
            map.put("barCategoryGap", barCategoryGap);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (barMaxWidth != null)  {
            map.put("barMaxWidth", barMaxWidth);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
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
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (z != null)  {
            map.put("z", z);
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
