package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>雷达图</strong></p>
 * <p>雷达图主要用于表现多变量的数据，例如球员的各个属性分析。依赖 <a href="#radar">radar</a> 组件。</p>
 * <p>下面是 AQI 数据用雷达图表现的示例。</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=radar-aqi&edit=1&reset=1" width="600" height="500" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesRadar implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    private Boolean silent;

    private String animationEasing;

    private List data;

    private Object animationDelay;

    private Object symbolSize;

    private Object animationDelayUpdate;

    private EChartSeriesRadarTooltip tooltip;

    private ItemStyle itemStyle;

    private EChartSeriesRadarLabel label;

    private String type;

    private Boolean animation;

    private Number animationDuration;

    private Number animationThreshold;

    private String animationEasingUpdate;

    private Number symbolRotate;

    private AreaStyle areaStyle;

    private LineStyle lineStyle;

    private List symbolOffset;

    private String name;

    private Number radarIndex;

    private Number zlevel;

    private Number z;

    private Object animationDurationUpdate;


    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesRadar setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesRadar setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesRadar setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesRadar setData(List data) {
        this.data = data;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesRadar setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesRadar setSymbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesRadar setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public EChartSeriesRadarTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesRadar setTooltip(EChartSeriesRadarTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesRadar setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesRadarLabel getLabel(){
        return label;
    }

    public EChartSeriesRadar setLabel(EChartSeriesRadarLabel label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesRadar setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesRadar setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesRadar setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesRadar setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesRadar setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesRadar setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public AreaStyle getAreaStyle(){
        return areaStyle;
    }

    public EChartSeriesRadar setAreaStyle(AreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesRadar setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesRadar setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesRadar setName(String name) {
        this.name = name;
        return this;
    }

    public Number getRadarIndex(){
        return radarIndex;
    }

    public EChartSeriesRadar setRadarIndex(Number radarIndex) {
        this.radarIndex = radarIndex;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesRadar setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesRadar setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesRadar setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
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
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (radarIndex != null)  {
            map.put("radarIndex", radarIndex);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
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
