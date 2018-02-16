package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>直角坐标系 grid 中的 y 轴，一般情况下单个 grid 组件最多只能放左右两个 y 轴，多于两个 y 轴需要通过配置 <a href="#yAxis.offset">offset</a> 属性防止同个位置多个 Y 轴的重叠。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartYAxis implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private Boolean show;

    private Boolean scale;

    private String nameLocation;

    private String type;

    private EChartYAxisAxisLabel axisLabel;

    private Object min;

    private EChartYAxisAxisLine axisLine;

    private EChartYAxisSplitArea splitArea;

    private Number zlevel;

    private NameTextStyle nameTextStyle;

    private Number logBase;

    private Boolean inverse;

    private Boolean silent;

    private Number offset;

    private Object max;

    private Number nameGap;

    private Number splitNumber;

    private Number maxInterval;

    private List boundaryGap;

    private Number gridIndex;

    private Number minInterval;

    private Number nameRotate;

    private String name;

    private EChartYAxisAxisTick axisTick;

    private EChartYAxisSplitLine splitLine;

    private EChartYAxisAxisPointer axisPointer;

    private Number interval;

    private Boolean triggerEvent;

    private Number z;

    private String position;


    public List getData(){
        return data;
    }

    public EChartYAxis setData(List data) {
        this.data = data;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartYAxis setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartYAxis setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public String getNameLocation(){
        return nameLocation;
    }

    public EChartYAxis setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartYAxis setType(String type) {
        this.type = type;
        return this;
    }

    public EChartYAxisAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartYAxis setAxisLabel(EChartYAxisAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public Object getMin(){
        return min;
    }

    public EChartYAxis setMin(Object min) {
        this.min = min;
        return this;
    }

    public EChartYAxisAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartYAxis setAxisLine(EChartYAxisAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public EChartYAxisSplitArea getSplitArea(){
        return splitArea;
    }

    public EChartYAxis setSplitArea(EChartYAxisSplitArea splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartYAxis setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public NameTextStyle getNameTextStyle(){
        return nameTextStyle;
    }

    public EChartYAxis setNameTextStyle(NameTextStyle nameTextStyle) {
        this.nameTextStyle = nameTextStyle;
        return this;
    }

    public Number getLogBase(){
        return logBase;
    }

    public EChartYAxis setLogBase(Number logBase) {
        this.logBase = logBase;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartYAxis setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartYAxis setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Number getOffset(){
        return offset;
    }

    public EChartYAxis setOffset(Number offset) {
        this.offset = offset;
        return this;
    }

    public Object getMax(){
        return max;
    }

    public EChartYAxis setMax(Object max) {
        this.max = max;
        return this;
    }

    public Number getNameGap(){
        return nameGap;
    }

    public EChartYAxis setNameGap(Number nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartYAxis setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Number getMaxInterval(){
        return maxInterval;
    }

    public EChartYAxis setMaxInterval(Number maxInterval) {
        this.maxInterval = maxInterval;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartYAxis setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public Number getGridIndex(){
        return gridIndex;
    }

    public EChartYAxis setGridIndex(Number gridIndex) {
        this.gridIndex = gridIndex;
        return this;
    }

    public Number getMinInterval(){
        return minInterval;
    }

    public EChartYAxis setMinInterval(Number minInterval) {
        this.minInterval = minInterval;
        return this;
    }

    public Number getNameRotate(){
        return nameRotate;
    }

    public EChartYAxis setNameRotate(Number nameRotate) {
        this.nameRotate = nameRotate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartYAxis setName(String name) {
        this.name = name;
        return this;
    }

    public EChartYAxisAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartYAxis setAxisTick(EChartYAxisAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public EChartYAxisSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartYAxis setSplitLine(EChartYAxisSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public EChartYAxisAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChartYAxis setAxisPointer(EChartYAxisAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public Number getInterval(){
        return interval;
    }

    public EChartYAxis setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartYAxis setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartYAxis setZ(Number z) {
        this.z = z;
        return this;
    }

    public String getPosition(){
        return position;
    }

    public EChartYAxis setPosition(String position) {
        this.position = position;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (show != null)  {
            map.put("show", show);
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
        if (offset != null)  {
            map.put("offset", offset);
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
        if (gridIndex != null)  {
            map.put("gridIndex", gridIndex);
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
        if (position != null)  {
            map.put("position", position);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
