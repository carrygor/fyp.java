package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>极坐标系的角度轴。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartAngleAxis implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number logBase;

    private Boolean silent;

    private Number startAngle;

    private List data;

    private Object max;

    private Boolean clockwise;

    private Boolean scale;

    private Number splitNumber;

    private Number maxInterval;

    private String type;

    private List boundaryGap;

    private EChartAngleAxisAxisLabel axisLabel;

    private Object min;

    private Number minInterval;

    private EChartAngleAxisAxisLine axisLine;

    private EChartAngleAxisSplitArea splitArea;

    private EChartAngleAxisAxisTick axisTick;

    private EChartAngleAxisSplitLine splitLine;

    private EChartAngleAxisAxisPointer axisPointer;

    private Number interval;

    private Boolean triggerEvent;

    private Number zlevel;

    private Number z;

    private Number polarIndex;


    public Number getLogBase(){
        return logBase;
    }

    public EChartAngleAxis setLogBase(Number logBase) {
        this.logBase = logBase;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartAngleAxis setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Number getStartAngle(){
        return startAngle;
    }

    public EChartAngleAxis setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartAngleAxis setData(List data) {
        this.data = data;
        return this;
    }

    public Object getMax(){
        return max;
    }

    public EChartAngleAxis setMax(Object max) {
        this.max = max;
        return this;
    }

    public Boolean getClockwise(){
        return clockwise;
    }

    public EChartAngleAxis setClockwise(Boolean clockwise) {
        this.clockwise = clockwise;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartAngleAxis setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartAngleAxis setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Number getMaxInterval(){
        return maxInterval;
    }

    public EChartAngleAxis setMaxInterval(Number maxInterval) {
        this.maxInterval = maxInterval;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartAngleAxis setType(String type) {
        this.type = type;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartAngleAxis setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public EChartAngleAxisAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartAngleAxis setAxisLabel(EChartAngleAxisAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public Object getMin(){
        return min;
    }

    public EChartAngleAxis setMin(Object min) {
        this.min = min;
        return this;
    }

    public Number getMinInterval(){
        return minInterval;
    }

    public EChartAngleAxis setMinInterval(Number minInterval) {
        this.minInterval = minInterval;
        return this;
    }

    public EChartAngleAxisAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartAngleAxis setAxisLine(EChartAngleAxisAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public EChartAngleAxisSplitArea getSplitArea(){
        return splitArea;
    }

    public EChartAngleAxis setSplitArea(EChartAngleAxisSplitArea splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    public EChartAngleAxisAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartAngleAxis setAxisTick(EChartAngleAxisAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public EChartAngleAxisSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartAngleAxis setSplitLine(EChartAngleAxisSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public EChartAngleAxisAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChartAngleAxis setAxisPointer(EChartAngleAxisAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public Number getInterval(){
        return interval;
    }

    public EChartAngleAxis setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartAngleAxis setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartAngleAxis setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartAngleAxis setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartAngleAxis setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (logBase != null)  {
            map.put("logBase", logBase);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (startAngle != null)  {
            map.put("startAngle", startAngle);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (max != null)  {
            map.put("max", max);
        }
        if (clockwise != null)  {
            map.put("clockwise", clockwise);
        }
        if (scale != null)  {
            map.put("scale", scale);
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
        if (splitArea != null)  {
            map.put("splitArea", splitArea.toMap());
        }
        if (axisTick != null)  {
            map.put("axisTick", axisTick.toMap());
        }
        if (splitLine != null)  {
            map.put("splitLine", splitLine.toMap());
        }
        if (axisPointer != null)  {
            map.put("axisPointer", axisPointer.toMap());
        }
        if (interval != null)  {
            map.put("interval", interval);
        }
        if (triggerEvent != null)  {
            map.put("triggerEvent", triggerEvent);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (polarIndex != null)  {
            map.put("polarIndex", polarIndex);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
