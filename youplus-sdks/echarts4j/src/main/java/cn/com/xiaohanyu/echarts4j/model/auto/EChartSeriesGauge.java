package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>仪表盘</strong></p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=gauge-car" width="600" height="500" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGauge implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number startAngle;

    private Number endAngle;

    private EChartSeriesGaugeTooltip tooltip;

    private String type;

    private EChartSeriesGaugeTitle title;

    private EChartSeriesGaugeMarkLine markLine;

    private EChartSeriesGaugeAxisLabel axisLabel;

    private EChartSeriesGaugeMarkArea markArea;

    private Number animationThreshold;

    private Number min;

    private EChartSeriesGaugeAxisLine axisLine;

    private Object radius;

    private EChartSeriesGaugePointer pointer;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Number max;

    private Boolean clockwise;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private Number splitNumber;

    private EChartSeriesGaugeMarkPoint markPoint;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private String name;

    private EChartSeriesGaugeSplitLine splitLine;

    private EChartSeriesGaugeAxisTick axisTick;

    private EChartSeriesGaugeDetail detail;

    private Object animationDurationUpdate;


    public Number getStartAngle(){
        return startAngle;
    }

    public EChartSeriesGauge setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public Number getEndAngle(){
        return endAngle;
    }

    public EChartSeriesGauge setEndAngle(Number endAngle) {
        this.endAngle = endAngle;
        return this;
    }

    public EChartSeriesGaugeTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesGauge setTooltip(EChartSeriesGaugeTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesGauge setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesGaugeTitle getTitle(){
        return title;
    }

    public EChartSeriesGauge setTitle(EChartSeriesGaugeTitle title) {
        this.title = title;
        return this;
    }

    public EChartSeriesGaugeMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesGauge setMarkLine(EChartSeriesGaugeMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public EChartSeriesGaugeAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartSeriesGauge setAxisLabel(EChartSeriesGaugeAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public EChartSeriesGaugeMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesGauge setMarkArea(EChartSeriesGaugeMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesGauge setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getMin(){
        return min;
    }

    public EChartSeriesGauge setMin(Number min) {
        this.min = min;
        return this;
    }

    public EChartSeriesGaugeAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartSeriesGauge setAxisLine(EChartSeriesGaugeAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public Object getRadius(){
        return radius;
    }

    public EChartSeriesGauge setRadius(Object radius) {
        this.radius = radius;
        return this;
    }

    public EChartSeriesGaugePointer getPointer(){
        return pointer;
    }

    public EChartSeriesGauge setPointer(EChartSeriesGaugePointer pointer) {
        this.pointer = pointer;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesGauge setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesGauge setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesGauge setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Number getMax(){
        return max;
    }

    public EChartSeriesGauge setMax(Number max) {
        this.max = max;
        return this;
    }

    public Boolean getClockwise(){
        return clockwise;
    }

    public EChartSeriesGauge setClockwise(Boolean clockwise) {
        this.clockwise = clockwise;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesGauge setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesGauge setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartSeriesGauge setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public EChartSeriesGaugeMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesGauge setMarkPoint(EChartSeriesGaugeMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesGauge setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesGauge setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesGauge setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesGauge setName(String name) {
        this.name = name;
        return this;
    }

    public EChartSeriesGaugeSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartSeriesGauge setSplitLine(EChartSeriesGaugeSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public EChartSeriesGaugeAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartSeriesGauge setAxisTick(EChartSeriesGaugeAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public EChartSeriesGaugeDetail getDetail(){
        return detail;
    }

    public EChartSeriesGauge setDetail(EChartSeriesGaugeDetail detail) {
        this.detail = detail;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesGauge setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (startAngle != null)  {
            map.put("startAngle", startAngle);
        }
        if (endAngle != null)  {
            map.put("endAngle", endAngle);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (title != null)  {
            map.put("title", title.toMap());
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (axisLabel != null)  {
            map.put("axisLabel", axisLabel.toMap());
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (min != null)  {
            map.put("min", min);
        }
        if (axisLine != null)  {
            map.put("axisLine", axisLine.toMap());
        }
        if (radius != null)  {
            map.put("radius", radius);
        }
        if (pointer != null)  {
            map.put("pointer", pointer.toMap());
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
        if (max != null)  {
            map.put("max", max);
        }
        if (clockwise != null)  {
            map.put("clockwise", clockwise);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
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
        if (splitLine != null)  {
            map.put("splitLine", splitLine.toMap());
        }
        if (axisTick != null)  {
            map.put("axisTick", axisTick.toMap());
        }
        if (detail != null)  {
            map.put("detail", detail.toMap());
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
