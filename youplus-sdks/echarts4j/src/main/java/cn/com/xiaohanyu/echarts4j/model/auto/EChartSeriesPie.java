package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>饼图</strong></p>
 * <p>饼图主要用于表现不同类目的数据在总和中的占比。每个的弧度表示数据数量的比例。</p>
 * <p><strong>Tip:</strong> 饼图更适合表现数据相对于总数的百分比等关系。如果只是表示不同类目数据间的大小，建议使用 <a href="bar" target="_blank">柱状图</a>，人们对于微小的弧度差别相比于微小的长度差别更不敏感，或者也可以通过配置 <a href="#series-pie.roseType">roseType</a> 显示成南丁格尔图，通过半径大小区分数据的大小。</p>
 * <p><strong>下面是自定义南丁格尔图的示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=pie-custom&edit=1&reset=1" width="500" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesPie implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Number startAngle;

    private Number selectedOffset;

    private List data;

    private Boolean stillShowZeroSum;

    private EChartSeriesPieTooltip tooltip;

    private EChartSeriesPieLabelLine labelLine;

    private String type;

    private EChartSeriesPieMarkLine markLine;

    private String animationType;

    private Boolean legendHoverLink;

    private EChartSeriesPieMarkArea markArea;

    private Number animationThreshold;

    private Boolean avoidLabelOverlap;

    private Number zlevel;

    private List radius;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Boolean clockwise;

    private Object roseType;

    private List center;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private Number hoverOffset;

    private Number minAngle;

    private EChartSeriesPieLabel label;

    private EChartSeriesPieMarkPoint markPoint;

    private Boolean animation;

    private Boolean hoverAnimation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private Object selectedMode;

    private String name;

    private Number z;

    private Object animationDurationUpdate;


    public String getCursor(){
        return cursor;
    }

    public EChartSeriesPie setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Number getStartAngle(){
        return startAngle;
    }

    public EChartSeriesPie setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public Number getSelectedOffset(){
        return selectedOffset;
    }

    public EChartSeriesPie setSelectedOffset(Number selectedOffset) {
        this.selectedOffset = selectedOffset;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesPie setData(List data) {
        this.data = data;
        return this;
    }

    public Boolean getStillShowZeroSum(){
        return stillShowZeroSum;
    }

    public EChartSeriesPie setStillShowZeroSum(Boolean stillShowZeroSum) {
        this.stillShowZeroSum = stillShowZeroSum;
        return this;
    }

    public EChartSeriesPieTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesPie setTooltip(EChartSeriesPieTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public EChartSeriesPieLabelLine getLabelLine(){
        return labelLine;
    }

    public EChartSeriesPie setLabelLine(EChartSeriesPieLabelLine labelLine) {
        this.labelLine = labelLine;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesPie setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesPieMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesPie setMarkLine(EChartSeriesPieMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public String getAnimationType(){
        return animationType;
    }

    public EChartSeriesPie setAnimationType(String animationType) {
        this.animationType = animationType;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesPie setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public EChartSeriesPieMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesPie setMarkArea(EChartSeriesPieMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesPie setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Boolean getAvoidLabelOverlap(){
        return avoidLabelOverlap;
    }

    public EChartSeriesPie setAvoidLabelOverlap(Boolean avoidLabelOverlap) {
        this.avoidLabelOverlap = avoidLabelOverlap;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesPie setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public List getRadius(){
        return radius;
    }

    public EChartSeriesPie setRadius(List radius) {
        this.radius = radius;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesPie setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesPie setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesPie setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Boolean getClockwise(){
        return clockwise;
    }

    public EChartSeriesPie setClockwise(Boolean clockwise) {
        this.clockwise = clockwise;
        return this;
    }

    public Object getRoseType(){
        return roseType;
    }

    public EChartSeriesPie setRoseType(Object roseType) {
        this.roseType = roseType;
        return this;
    }

    public List getCenter(){
        return center;
    }

    public EChartSeriesPie setCenter(List center) {
        this.center = center;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesPie setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesPie setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Number getHoverOffset(){
        return hoverOffset;
    }

    public EChartSeriesPie setHoverOffset(Number hoverOffset) {
        this.hoverOffset = hoverOffset;
        return this;
    }

    public Number getMinAngle(){
        return minAngle;
    }

    public EChartSeriesPie setMinAngle(Number minAngle) {
        this.minAngle = minAngle;
        return this;
    }

    public EChartSeriesPieLabel getLabel(){
        return label;
    }

    public EChartSeriesPie setLabel(EChartSeriesPieLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesPieMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesPie setMarkPoint(EChartSeriesPieMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesPie setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesPie setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesPie setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesPie setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Object getSelectedMode(){
        return selectedMode;
    }

    public EChartSeriesPie setSelectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesPie setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesPie setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesPie setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cursor != null)  {
            map.put("cursor", cursor);
        }
        if (startAngle != null)  {
            map.put("startAngle", startAngle);
        }
        if (selectedOffset != null)  {
            map.put("selectedOffset", selectedOffset);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (stillShowZeroSum != null)  {
            map.put("stillShowZeroSum", stillShowZeroSum);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (labelLine != null)  {
            map.put("labelLine", labelLine.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (animationType != null)  {
            map.put("animationType", animationType);
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
        if (avoidLabelOverlap != null)  {
            map.put("avoidLabelOverlap", avoidLabelOverlap);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (radius != null)  {
            map.put("radius", radius);
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
        if (clockwise != null)  {
            map.put("clockwise", clockwise);
        }
        if (roseType != null)  {
            map.put("roseType", roseType);
        }
        if (center != null)  {
            map.put("center", center);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (hoverOffset != null)  {
            map.put("hoverOffset", hoverOffset);
        }
        if (minAngle != null)  {
            map.put("minAngle", minAngle);
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
        if (selectedMode != null)  {
            map.put("selectedMode", selectedMode);
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
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
