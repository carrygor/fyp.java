package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>雷达图坐标系组件，只适用于<a href="#series-radar">雷达图</a>。该组件等同 ECharts 2 中的 polar 组件。因为 3 中的 polar 被重构为标准的极坐标组件，为避免混淆，雷达图使用 radar 组件作为其坐标系。</p>
 * <p>雷达图坐标系与极坐标系不同的是它的每一个轴（indicator 指示器）都是一个单独的维度，可以通过 <a href="#radar.name">name</a>、<a href="#radar.axisLine">axisLine</a>、<a href="#radar.axisTick">axisTick</a>、<a href="#radar.axisLabel">axisLabel</a>、<a href="#radar.splitLine">splitLine</a>、 <a href="#radar.splitArea">splitArea</a> 几个配置项配置指示器坐标轴线的样式。</p>
 * <p>下面是一个 radar 组件的一个自定义例子。</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/radar&edit=1&reset=1" width="400" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartRadar implements Serializable {
    private static final long serialVersionUID = 1L;

    private List indicator;

    private Boolean silent;

    private Number startAngle;

    private String shape;

    private List center;

    private Number nameGap;

    private Boolean scale;

    private Number splitNumber;

    private EChartRadarAxisLabel axisLabel;

    private EChartRadarAxisLine axisLine;

    private EChartRadarSplitArea splitArea;

    private EChartRadarName name;

    private EChartRadarAxisTick axisTick;

    private EChartRadarSplitLine splitLine;

    private Number zlevel;

    private Number z;

    private Boolean triggerEvent;

    private Object radius;


    public List getIndicator(){
        return indicator;
    }

    public EChartRadar setIndicator(List indicator) {
        this.indicator = indicator;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartRadar setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Number getStartAngle(){
        return startAngle;
    }

    public EChartRadar setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public String getShape(){
        return shape;
    }

    public EChartRadar setShape(String shape) {
        this.shape = shape;
        return this;
    }

    public List getCenter(){
        return center;
    }

    public EChartRadar setCenter(List center) {
        this.center = center;
        return this;
    }

    public Number getNameGap(){
        return nameGap;
    }

    public EChartRadar setNameGap(Number nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartRadar setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartRadar setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public EChartRadarAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartRadar setAxisLabel(EChartRadarAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public EChartRadarAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartRadar setAxisLine(EChartRadarAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public EChartRadarSplitArea getSplitArea(){
        return splitArea;
    }

    public EChartRadar setSplitArea(EChartRadarSplitArea splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    public EChartRadarName getName(){
        return name;
    }

    public EChartRadar setName(EChartRadarName name) {
        this.name = name;
        return this;
    }

    public EChartRadarAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartRadar setAxisTick(EChartRadarAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public EChartRadarSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartRadar setSplitLine(EChartRadarSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartRadar setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartRadar setZ(Number z) {
        this.z = z;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartRadar setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public Object getRadius(){
        return radius;
    }

    public EChartRadar setRadius(Object radius) {
        this.radius = radius;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (indicator != null)  {
            map.put("indicator", indicator);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (startAngle != null)  {
            map.put("startAngle", startAngle);
        }
        if (shape != null)  {
            map.put("shape", shape);
        }
        if (center != null)  {
            map.put("center", center);
        }
        if (nameGap != null)  {
            map.put("nameGap", nameGap);
        }
        if (scale != null)  {
            map.put("scale", scale);
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
        }
        if (axisLabel != null)  {
            map.put("axisLabel", axisLabel.toMap());
        }
        if (axisLine != null)  {
            map.put("axisLine", axisLine.toMap());
        }
        if (splitArea != null)  {
            map.put("splitArea", splitArea.toMap());
        }
        if (name != null)  {
            map.put("name", name.toMap());
        }
        if (axisTick != null)  {
            map.put("axisTick", axisTick.toMap());
        }
        if (splitLine != null)  {
            map.put("splitLine", splitLine.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (triggerEvent != null)  {
            map.put("triggerEvent", triggerEvent);
        }
        if (radius != null)  {
            map.put("radius", radius);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
