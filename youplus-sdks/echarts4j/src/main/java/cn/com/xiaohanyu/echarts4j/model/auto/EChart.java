package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ECharts的主结构类
 * </p>
 *
 * @author 小汉语
 */
public class EChart implements Serializable {
    private static final long serialVersionUID = 1L;

    private EChartXAxis xAxis;

    private Color color;

    private EChartParallelAxis parallelAxis;

    private EChartLegend legend;

    private EChartTooltip tooltip;

    private Number hoverLayerThreshold;

    private EChartTitle title;

    private EChartGeo geo;

    private Number animationThreshold;

    private EChartParallel parallel;

    private EChartGraphic graphic;

    private EChartAngleAxis angleAxis;

    private EChartPolar polar;

    private EChartBrush brush;

    private EChartCalendar calendar;

    private Color backgroundColor;

    private String animationEasing;

    private Object animationDelay;

    private EChartSingleAxis singleAxis;

    private Object animationDelayUpdate;

    private Boolean animation;

    private Number animationDuration;

    private EChartYAxis yAxis;

    private EChartRadiusAxis radiusAxis;

    private EChartRadar radar;

    private String animationEasingUpdate;

    private String blendMode;

    private EChartGrid grid;

    private List series;

    private Boolean useUTC;

    private List dataZoom;

    private EChartToolbox toolbox;

    private EChartAxisPointer axisPointer;

    private EChartTimeline timeline;

    private Number progressive;

    private TextStyle textStyle;

    private Object animationDurationUpdate;

    private Number progressiveThreshold;

    private List visualMap;


    public EChartXAxis getXAxis(){
        return xAxis;
    }

    public EChart setXAxis(EChartXAxis xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChart setColor(Color color) {
        this.color = color;
        return this;
    }

    public EChartParallelAxis getParallelAxis(){
        return parallelAxis;
    }

    public EChart setParallelAxis(EChartParallelAxis parallelAxis) {
        this.parallelAxis = parallelAxis;
        return this;
    }

    public EChartLegend getLegend(){
        return legend;
    }

    public EChart setLegend(EChartLegend legend) {
        this.legend = legend;
        return this;
    }

    public EChartTooltip getTooltip(){
        return tooltip;
    }

    public EChart setTooltip(EChartTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Number getHoverLayerThreshold(){
        return hoverLayerThreshold;
    }

    public EChart setHoverLayerThreshold(Number hoverLayerThreshold) {
        this.hoverLayerThreshold = hoverLayerThreshold;
        return this;
    }

    public EChartTitle getTitle(){
        return title;
    }

    public EChart setTitle(EChartTitle title) {
        this.title = title;
        return this;
    }

    public EChartGeo getGeo(){
        return geo;
    }

    public EChart setGeo(EChartGeo geo) {
        this.geo = geo;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChart setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public EChartParallel getParallel(){
        return parallel;
    }

    public EChart setParallel(EChartParallel parallel) {
        this.parallel = parallel;
        return this;
    }

    public EChartGraphic getGraphic(){
        return graphic;
    }

    public EChart setGraphic(EChartGraphic graphic) {
        this.graphic = graphic;
        return this;
    }

    public EChartAngleAxis getAngleAxis(){
        return angleAxis;
    }

    public EChart setAngleAxis(EChartAngleAxis angleAxis) {
        this.angleAxis = angleAxis;
        return this;
    }

    public EChartPolar getPolar(){
        return polar;
    }

    public EChart setPolar(EChartPolar polar) {
        this.polar = polar;
        return this;
    }

    public EChartBrush getBrush(){
        return brush;
    }

    public EChart setBrush(EChartBrush brush) {
        this.brush = brush;
        return this;
    }

    public EChartCalendar getCalendar(){
        return calendar;
    }

    public EChart setCalendar(EChartCalendar calendar) {
        this.calendar = calendar;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChart setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChart setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChart setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public EChartSingleAxis getSingleAxis(){
        return singleAxis;
    }

    public EChart setSingleAxis(EChartSingleAxis singleAxis) {
        this.singleAxis = singleAxis;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChart setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChart setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChart setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public EChartYAxis getYAxis(){
        return yAxis;
    }

    public EChart setYAxis(EChartYAxis yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    public EChartRadiusAxis getRadiusAxis(){
        return radiusAxis;
    }

    public EChart setRadiusAxis(EChartRadiusAxis radiusAxis) {
        this.radiusAxis = radiusAxis;
        return this;
    }

    public EChartRadar getRadar(){
        return radar;
    }

    public EChart setRadar(EChartRadar radar) {
        this.radar = radar;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChart setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getBlendMode(){
        return blendMode;
    }

    public EChart setBlendMode(String blendMode) {
        this.blendMode = blendMode;
        return this;
    }

    public EChartGrid getGrid(){
        return grid;
    }

    public EChart setGrid(EChartGrid grid) {
        this.grid = grid;
        return this;
    }

    public List getSeries(){
        return series;
    }

    public EChart setSeries(List series) {
        this.series = series;
        return this;
    }

    public Boolean getUseUTC(){
        return useUTC;
    }

    public EChart setUseUTC(Boolean useUTC) {
        this.useUTC = useUTC;
        return this;
    }

    public List getDataZoom(){
        return dataZoom;
    }

    public EChart setDataZoom(List dataZoom) {
        this.dataZoom = dataZoom;
        return this;
    }

    public EChartToolbox getToolbox(){
        return toolbox;
    }

    public EChart setToolbox(EChartToolbox toolbox) {
        this.toolbox = toolbox;
        return this;
    }

    public EChartAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChart setAxisPointer(EChartAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public EChartTimeline getTimeline(){
        return timeline;
    }

    public EChart setTimeline(EChartTimeline timeline) {
        this.timeline = timeline;
        return this;
    }

    public Number getProgressive(){
        return progressive;
    }

    public EChart setProgressive(Number progressive) {
        this.progressive = progressive;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChart setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChart setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getProgressiveThreshold(){
        return progressiveThreshold;
    }

    public EChart setProgressiveThreshold(Number progressiveThreshold) {
        this.progressiveThreshold = progressiveThreshold;
        return this;
    }

    public List getVisualMap(){
        return visualMap;
    }

    public EChart setVisualMap(List visualMap) {
        this.visualMap = visualMap;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (xAxis != null)  {
            map.put("xAxis", xAxis.toMap());
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (parallelAxis != null)  {
            map.put("parallelAxis", parallelAxis.toMap());
        }
        if (legend != null)  {
            map.put("legend", legend.toMap());
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (hoverLayerThreshold != null)  {
            map.put("hoverLayerThreshold", hoverLayerThreshold);
        }
        if (title != null)  {
            map.put("title", title.toMap());
        }
        if (geo != null)  {
            map.put("geo", geo.toMap());
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (parallel != null)  {
            map.put("parallel", parallel.toMap());
        }
        if (graphic != null)  {
            map.put("graphic", graphic.toMap());
        }
        if (angleAxis != null)  {
            map.put("angleAxis", angleAxis.toMap());
        }
        if (polar != null)  {
            map.put("polar", polar.toMap());
        }
        if (brush != null)  {
            map.put("brush", brush.toMap());
        }
        if (calendar != null)  {
            map.put("calendar", calendar.toMap());
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (singleAxis != null)  {
            map.put("singleAxis", singleAxis.toMap());
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (yAxis != null)  {
            map.put("yAxis", yAxis.toMap());
        }
        if (radiusAxis != null)  {
            map.put("radiusAxis", radiusAxis.toMap());
        }
        if (radar != null)  {
            map.put("radar", radar.toMap());
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (blendMode != null)  {
            map.put("blendMode", blendMode);
        }
        if (grid != null)  {
            map.put("grid", grid.toMap());
        }
        if (series != null)  {
            map.put("series", series);
        }
        if (useUTC != null)  {
            map.put("useUTC", useUTC);
        }
        if (dataZoom != null)  {
            map.put("dataZoom", dataZoom);
        }
        if (toolbox != null)  {
            map.put("toolbox", toolbox.toMap());
        }
        if (axisPointer != null)  {
            map.put("axisPointer", axisPointer.toMap());
        }
        if (timeline != null)  {
            map.put("timeline", timeline.toMap());
        }
        if (progressive != null)  {
            map.put("progressive", progressive);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        if (progressiveThreshold != null)  {
            map.put("progressiveThreshold", progressiveThreshold);
        }
        if (visualMap != null)  {
            map.put("visualMap", visualMap);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
