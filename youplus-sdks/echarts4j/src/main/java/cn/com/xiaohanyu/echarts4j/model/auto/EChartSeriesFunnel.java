package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>漏斗图</strong></p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=funnel&reset=1&edit=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesFunnel implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private EChartSeriesFunnelTooltip tooltip;

    private String maxSize;

    private EChartSeriesFunnelLabelLine labelLine;

    private String type;

    private EChartSeriesFunnelMarkLine markLine;

    private Boolean legendHoverLink;

    private EChartSeriesFunnelMarkArea markArea;

    private Number animationThreshold;

    private Number min;

    private Number gap;

    private String minSize;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Number max;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private String sort;

    private EChartSeriesFunnelLabel label;

    private EChartSeriesFunnelMarkPoint markPoint;

    private String funnelAlign;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private String name;

    private Object animationDurationUpdate;


    public List getData(){
        return data;
    }

    public EChartSeriesFunnel setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesFunnelTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesFunnel setTooltip(EChartSeriesFunnelTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getMaxSize(){
        return maxSize;
    }

    public EChartSeriesFunnel setMaxSize(String maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public EChartSeriesFunnelLabelLine getLabelLine(){
        return labelLine;
    }

    public EChartSeriesFunnel setLabelLine(EChartSeriesFunnelLabelLine labelLine) {
        this.labelLine = labelLine;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesFunnel setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesFunnelMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesFunnel setMarkLine(EChartSeriesFunnelMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesFunnel setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public EChartSeriesFunnelMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesFunnel setMarkArea(EChartSeriesFunnelMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesFunnel setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getMin(){
        return min;
    }

    public EChartSeriesFunnel setMin(Number min) {
        this.min = min;
        return this;
    }

    public Number getGap(){
        return gap;
    }

    public EChartSeriesFunnel setGap(Number gap) {
        this.gap = gap;
        return this;
    }

    public String getMinSize(){
        return minSize;
    }

    public EChartSeriesFunnel setMinSize(String minSize) {
        this.minSize = minSize;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesFunnel setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesFunnel setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesFunnel setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Number getMax(){
        return max;
    }

    public EChartSeriesFunnel setMax(Number max) {
        this.max = max;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesFunnel setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesFunnel setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public String getSort(){
        return sort;
    }

    public EChartSeriesFunnel setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public EChartSeriesFunnelLabel getLabel(){
        return label;
    }

    public EChartSeriesFunnel setLabel(EChartSeriesFunnelLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesFunnelMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesFunnel setMarkPoint(EChartSeriesFunnelMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public String getFunnelAlign(){
        return funnelAlign;
    }

    public EChartSeriesFunnel setFunnelAlign(String funnelAlign) {
        this.funnelAlign = funnelAlign;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesFunnel setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesFunnel setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesFunnel setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesFunnel setName(String name) {
        this.name = name;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesFunnel setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (maxSize != null)  {
            map.put("maxSize", maxSize);
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
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
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
        if (gap != null)  {
            map.put("gap", gap);
        }
        if (minSize != null)  {
            map.put("minSize", minSize);
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
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (sort != null)  {
            map.put("sort", sort);
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (funnelAlign != null)  {
            map.put("funnelAlign", funnelAlign);
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
