package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>极坐标系的径向轴。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartRadiusAxis implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private Boolean scale;

    private String nameLocation;

    private String type;

    private EChartRadiusAxisAxisLabel axisLabel;

    private Object min;

    private EChartRadiusAxisAxisLine axisLine;

    private EChartRadiusAxisSplitArea splitArea;

    private Number zlevel;

    private NameTextStyle nameTextStyle;

    private Number logBase;

    private Boolean inverse;

    private Boolean silent;

    private Object max;

    private Number nameGap;

    private Number splitNumber;

    private Number maxInterval;

    private List boundaryGap;

    private Number minInterval;

    private Number nameRotate;

    private String name;

    private EChartRadiusAxisAxisTick axisTick;

    private EChartRadiusAxisSplitLine splitLine;

    private EChartRadiusAxisAxisPointer axisPointer;

    private Number interval;

    private Boolean triggerEvent;

    private Number z;

    private Number polarIndex;


    public List getData(){
        return data;
    }

    public EChartRadiusAxis setData(List data) {
        this.data = data;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartRadiusAxis setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public String getNameLocation(){
        return nameLocation;
    }

    public EChartRadiusAxis setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartRadiusAxis setType(String type) {
        this.type = type;
        return this;
    }

    public EChartRadiusAxisAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartRadiusAxis setAxisLabel(EChartRadiusAxisAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public Object getMin(){
        return min;
    }

    public EChartRadiusAxis setMin(Object min) {
        this.min = min;
        return this;
    }

    public EChartRadiusAxisAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartRadiusAxis setAxisLine(EChartRadiusAxisAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public EChartRadiusAxisSplitArea getSplitArea(){
        return splitArea;
    }

    public EChartRadiusAxis setSplitArea(EChartRadiusAxisSplitArea splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartRadiusAxis setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public NameTextStyle getNameTextStyle(){
        return nameTextStyle;
    }

    public EChartRadiusAxis setNameTextStyle(NameTextStyle nameTextStyle) {
        this.nameTextStyle = nameTextStyle;
        return this;
    }

    public Number getLogBase(){
        return logBase;
    }

    public EChartRadiusAxis setLogBase(Number logBase) {
        this.logBase = logBase;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartRadiusAxis setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartRadiusAxis setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Object getMax(){
        return max;
    }

    public EChartRadiusAxis setMax(Object max) {
        this.max = max;
        return this;
    }

    public Number getNameGap(){
        return nameGap;
    }

    public EChartRadiusAxis setNameGap(Number nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartRadiusAxis setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Number getMaxInterval(){
        return maxInterval;
    }

    public EChartRadiusAxis setMaxInterval(Number maxInterval) {
        this.maxInterval = maxInterval;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartRadiusAxis setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public Number getMinInterval(){
        return minInterval;
    }

    public EChartRadiusAxis setMinInterval(Number minInterval) {
        this.minInterval = minInterval;
        return this;
    }

    public Number getNameRotate(){
        return nameRotate;
    }

    public EChartRadiusAxis setNameRotate(Number nameRotate) {
        this.nameRotate = nameRotate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartRadiusAxis setName(String name) {
        this.name = name;
        return this;
    }

    public EChartRadiusAxisAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartRadiusAxis setAxisTick(EChartRadiusAxisAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public EChartRadiusAxisSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartRadiusAxis setSplitLine(EChartRadiusAxisSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public EChartRadiusAxisAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChartRadiusAxis setAxisPointer(EChartRadiusAxisAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public Number getInterval(){
        return interval;
    }

    public EChartRadiusAxis setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartRadiusAxis setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartRadiusAxis setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartRadiusAxis setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (scale != null)  {
            map.put("scale", scale);
        }
        if (nameLocation != null)  {
            map.put("nameLocation", nameLocation);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (axisLabel != null)  {
            map.put("axisLabel", axisLabel.toMap());
        }
        if (min != null)  {
            map.put("min", min);
        }
        if (axisLine != null)  {
            map.put("axisLine", axisLine.toMap());
        }
        if (splitArea != null)  {
            map.put("splitArea", splitArea.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (nameTextStyle != null)  {
            map.put("nameTextStyle", nameTextStyle.toMap());
        }
        if (logBase != null)  {
            map.put("logBase", logBase);
        }
        if (inverse != null)  {
            map.put("inverse", inverse);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (max != null)  {
            map.put("max", max);
        }
        if (nameGap != null)  {
            map.put("nameGap", nameGap);
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
        }
        if (maxInterval != null)  {
            map.put("maxInterval", maxInterval);
        }
        if (boundaryGap != null)  {
            map.put("boundaryGap", boundaryGap);
        }
        if (minInterval != null)  {
            map.put("minInterval", minInterval);
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
