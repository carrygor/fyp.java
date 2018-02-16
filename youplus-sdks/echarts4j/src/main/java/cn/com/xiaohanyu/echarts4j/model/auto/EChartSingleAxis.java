package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>单轴。可以被应用到散点图中展现一维数据，如下示例</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-single-axis&edit=1&reset=1" width="700" height="500" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSingleAxis implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private EChartSingleAxisTooltip tooltip;

    private Boolean scale;

    private String nameLocation;

    private String type;

    private EChartSingleAxisAxisLabel axisLabel;

    private Object min;

    private Object top;

    private EChartSingleAxisAxisLine axisLine;

    private EChartSingleAxisSplitArea splitArea;

    private Number zlevel;

    private NameTextStyle nameTextStyle;

    private Object height;

    private Number logBase;

    private Boolean inverse;

    private Boolean silent;

    private String orient;

    private Object max;

    private Object bottom;

    private Number nameGap;

    private Object right;

    private Number splitNumber;

    private Number maxInterval;

    private List boundaryGap;

    private Object left;

    private Number minInterval;

    private Number nameRotate;

    private Object width;

    private String name;

    private EChartSingleAxisAxisTick axisTick;

    private EChartSingleAxisSplitLine splitLine;

    private EChartSingleAxisAxisPointer axisPointer;

    private Number z;

    private Number interval;

    private Boolean triggerEvent;


    public List getData(){
        return data;
    }

    public EChartSingleAxis setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSingleAxisTooltip getTooltip(){
        return tooltip;
    }

    public EChartSingleAxis setTooltip(EChartSingleAxisTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartSingleAxis setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public String getNameLocation(){
        return nameLocation;
    }

    public EChartSingleAxis setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSingleAxis setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSingleAxisAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartSingleAxis setAxisLabel(EChartSingleAxisAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public Object getMin(){
        return min;
    }

    public EChartSingleAxis setMin(Object min) {
        this.min = min;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSingleAxis setTop(Object top) {
        this.top = top;
        return this;
    }

    public EChartSingleAxisAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartSingleAxis setAxisLine(EChartSingleAxisAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public EChartSingleAxisSplitArea getSplitArea(){
        return splitArea;
    }

    public EChartSingleAxis setSplitArea(EChartSingleAxisSplitArea splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSingleAxis setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public NameTextStyle getNameTextStyle(){
        return nameTextStyle;
    }

    public EChartSingleAxis setNameTextStyle(NameTextStyle nameTextStyle) {
        this.nameTextStyle = nameTextStyle;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartSingleAxis setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Number getLogBase(){
        return logBase;
    }

    public EChartSingleAxis setLogBase(Number logBase) {
        this.logBase = logBase;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartSingleAxis setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSingleAxis setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartSingleAxis setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public Object getMax(){
        return max;
    }

    public EChartSingleAxis setMax(Object max) {
        this.max = max;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSingleAxis setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Number getNameGap(){
        return nameGap;
    }

    public EChartSingleAxis setNameGap(Number nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSingleAxis setRight(Object right) {
        this.right = right;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartSingleAxis setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Number getMaxInterval(){
        return maxInterval;
    }

    public EChartSingleAxis setMaxInterval(Number maxInterval) {
        this.maxInterval = maxInterval;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartSingleAxis setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSingleAxis setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Number getMinInterval(){
        return minInterval;
    }

    public EChartSingleAxis setMinInterval(Number minInterval) {
        this.minInterval = minInterval;
        return this;
    }

    public Number getNameRotate(){
        return nameRotate;
    }

    public EChartSingleAxis setNameRotate(Number nameRotate) {
        this.nameRotate = nameRotate;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartSingleAxis setWidth(Object width) {
        this.width = width;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSingleAxis setName(String name) {
        this.name = name;
        return this;
    }

    public EChartSingleAxisAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartSingleAxis setAxisTick(EChartSingleAxisAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public EChartSingleAxisSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartSingleAxis setSplitLine(EChartSingleAxisSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public EChartSingleAxisAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChartSingleAxis setAxisPointer(EChartSingleAxisAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSingleAxis setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getInterval(){
        return interval;
    }

    public EChartSingleAxis setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartSingleAxis setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
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
        if (top != null)  {
            map.put("top", top);
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
        if (height != null)  {
            map.put("height", height);
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
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (max != null)  {
            map.put("max", max);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (nameGap != null)  {
            map.put("nameGap", nameGap);
        }
        if (right != null)  {
            map.put("right", right);
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
        if (left != null)  {
            map.put("left", left);
        }
        if (minInterval != null)  {
            map.put("minInterval", minInterval);
        }
        if (nameRotate != null)  {
            map.put("nameRotate", nameRotate);
        }
        if (width != null)  {
            map.put("width", width);
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
        if (z != null)  {
            map.put("z", z);
        }
        if (interval != null)  {
            map.put("interval", interval);
        }
        if (triggerEvent != null)  {
            map.put("triggerEvent", triggerEvent);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
