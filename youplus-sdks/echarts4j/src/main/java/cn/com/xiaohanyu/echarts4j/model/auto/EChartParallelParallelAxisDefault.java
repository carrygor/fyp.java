package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>配置多个 <a href="#parallelAxis">parallelAxis</a> 时，有些值一样的属性，如果书写多遍则比较繁琐，那么可以放置在 <a href="#parallel.parallelAxisDefault">parallel.parallelAxisDefault</a> 里。在坐标轴初始化前，<a href="#parallel.parallelAxisDefault">parallel.parallelAxisDefault</a> 里的配置项，会分别融合进 <a href="#parallelAxis">parallelAxis</a>，形成最终的坐标轴的配置。</p>
 * <p><a href="http://echarts.baidu.com/gallery/editor.html?c=doc-example/parallel-all&amp;edit=1&amp;reset=1" target="_blank">参见示例</a></p>
 * <p><br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartParallelParallelAxisDefault implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number logBase;

    private Boolean inverse;

    private Boolean silent;

    private List data;

    private Object max;

    private Number nameGap;

    private Boolean scale;

    private String nameLocation;

    private Number splitNumber;

    private Number maxInterval;

    private String type;

    private List boundaryGap;

    private EChartParallelParallelAxisDefaultAxisLabel axisLabel;

    private Object min;

    private Number minInterval;

    private EChartParallelParallelAxisDefaultAxisLine axisLine;

    private Number nameRotate;

    private String name;

    private EChartParallelParallelAxisDefaultAxisTick axisTick;

    private Number interval;

    private Boolean triggerEvent;

    private NameTextStyle nameTextStyle;


    public Number getLogBase(){
        return logBase;
    }

    public EChartParallelParallelAxisDefault setLogBase(Number logBase) {
        this.logBase = logBase;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartParallelParallelAxisDefault setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartParallelParallelAxisDefault setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartParallelParallelAxisDefault setData(List data) {
        this.data = data;
        return this;
    }

    public Object getMax(){
        return max;
    }

    public EChartParallelParallelAxisDefault setMax(Object max) {
        this.max = max;
        return this;
    }

    public Number getNameGap(){
        return nameGap;
    }

    public EChartParallelParallelAxisDefault setNameGap(Number nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartParallelParallelAxisDefault setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public String getNameLocation(){
        return nameLocation;
    }

    public EChartParallelParallelAxisDefault setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartParallelParallelAxisDefault setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Number getMaxInterval(){
        return maxInterval;
    }

    public EChartParallelParallelAxisDefault setMaxInterval(Number maxInterval) {
        this.maxInterval = maxInterval;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartParallelParallelAxisDefault setType(String type) {
        this.type = type;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartParallelParallelAxisDefault setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public EChartParallelParallelAxisDefaultAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartParallelParallelAxisDefault setAxisLabel(EChartParallelParallelAxisDefaultAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public Object getMin(){
        return min;
    }

    public EChartParallelParallelAxisDefault setMin(Object min) {
        this.min = min;
        return this;
    }

    public Number getMinInterval(){
        return minInterval;
    }

    public EChartParallelParallelAxisDefault setMinInterval(Number minInterval) {
        this.minInterval = minInterval;
        return this;
    }

    public EChartParallelParallelAxisDefaultAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartParallelParallelAxisDefault setAxisLine(EChartParallelParallelAxisDefaultAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public Number getNameRotate(){
        return nameRotate;
    }

    public EChartParallelParallelAxisDefault setNameRotate(Number nameRotate) {
        this.nameRotate = nameRotate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartParallelParallelAxisDefault setName(String name) {
        this.name = name;
        return this;
    }

    public EChartParallelParallelAxisDefaultAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartParallelParallelAxisDefault setAxisTick(EChartParallelParallelAxisDefaultAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public Number getInterval(){
        return interval;
    }

    public EChartParallelParallelAxisDefault setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartParallelParallelAxisDefault setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public NameTextStyle getNameTextStyle(){
        return nameTextStyle;
    }

    public EChartParallelParallelAxisDefault setNameTextStyle(NameTextStyle nameTextStyle) {
        this.nameTextStyle = nameTextStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (logBase != null)  {
            map.put("logBase", logBase);
        }
        if (inverse != null)  {
            map.put("inverse", inverse);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (max != null)  {
            map.put("max", max);
        }
        if (nameGap != null)  {
            map.put("nameGap", nameGap);
        }
        if (scale != null)  {
            map.put("scale", scale);
        }
        if (nameLocation != null)  {
            map.put("nameLocation", nameLocation);
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
        }
        if (maxInterval != null)  {
            map.put("maxInterval", maxInterval);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (boundaryGap != null)  {
            map.put("boundaryGap", boundaryGap);
        }
        if (axisLabel != null)  {
            map.put("axisLabel", axisLabel.toMap());
        }
        if (min != null)  {
            map.put("min", min);
        }
        if (minInterval != null)  {
            map.put("minInterval", minInterval);
        }
        if (axisLine != null)  {
            map.put("axisLine", axisLine.toMap());
        }
        if (nameRotate != null)  {
            map.put("nameRotate", nameRotate);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (axisTick != null)  {
            map.put("axisTick", axisTick.toMap());
        }
        if (interval != null)  {
            map.put("interval", interval);
        }
        if (triggerEvent != null)  {
            map.put("triggerEvent", triggerEvent);
        }
        if (nameTextStyle != null)  {
            map.put("nameTextStyle", nameTextStyle.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
