package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>内置型数据区域缩放组件（dataZoomInside）</strong></p>
 * <p>（参考<a href="#dataZoom">数据区域缩放组件（dataZoom）的介绍</a>）</p>
 * <p>所谓『内置』，即内置在坐标系中。</p>
 * <ul>
 * <li>平移：在坐标系中滑动拖拽进行数据区域平移。</li>
 * <li>缩放：<ul>
 * <li>PC端：鼠标在坐标系范围内滚轮滚动（MAC触控板类同）</li>
 * <li>移动端：在移动端触屏上，支持两指滑动缩放。</li>
 * </ul>
 * </li>
 * </ul>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartDataZoomInside implements Serializable {
    private static final long serialVersionUID = 1L;

    private List radiusAxisIndex;

    private Number maxSpan;

    private Number throttle;

    private Object endValue;

    private Object minValueSpan;

    private String orient;

    private List xAxisIndex;

    private Number start;

    private List angleAxisIndex;

    private String type;

    private Boolean zoomLock;

    private List rangeMode;

    private String filterMode;

    private Object maxValueSpan;

    private Boolean moveOnMouseMove;

    private Number minSpan;

    private Boolean preventDefaultMouseMove;

    private Boolean zoomOnMouseWheel;

    private Boolean disabled;

    private Number end;

    private Object startValue;

    private List yAxisIndex;


    public List getRadiusAxisIndex(){
        return radiusAxisIndex;
    }

    public EChartDataZoomInside setRadiusAxisIndex(List radiusAxisIndex) {
        this.radiusAxisIndex = radiusAxisIndex;
        return this;
    }

    public Number getMaxSpan(){
        return maxSpan;
    }

    public EChartDataZoomInside setMaxSpan(Number maxSpan) {
        this.maxSpan = maxSpan;
        return this;
    }

    public Number getThrottle(){
        return throttle;
    }

    public EChartDataZoomInside setThrottle(Number throttle) {
        this.throttle = throttle;
        return this;
    }

    public Object getEndValue(){
        return endValue;
    }

    public EChartDataZoomInside setEndValue(Object endValue) {
        this.endValue = endValue;
        return this;
    }

    public Object getMinValueSpan(){
        return minValueSpan;
    }

    public EChartDataZoomInside setMinValueSpan(Object minValueSpan) {
        this.minValueSpan = minValueSpan;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartDataZoomInside setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public List getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartDataZoomInside setXAxisIndex(List xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Number getStart(){
        return start;
    }

    public EChartDataZoomInside setStart(Number start) {
        this.start = start;
        return this;
    }

    public List getAngleAxisIndex(){
        return angleAxisIndex;
    }

    public EChartDataZoomInside setAngleAxisIndex(List angleAxisIndex) {
        this.angleAxisIndex = angleAxisIndex;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartDataZoomInside setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getZoomLock(){
        return zoomLock;
    }

    public EChartDataZoomInside setZoomLock(Boolean zoomLock) {
        this.zoomLock = zoomLock;
        return this;
    }

    public List getRangeMode(){
        return rangeMode;
    }

    public EChartDataZoomInside setRangeMode(List rangeMode) {
        this.rangeMode = rangeMode;
        return this;
    }

    public String getFilterMode(){
        return filterMode;
    }

    public EChartDataZoomInside setFilterMode(String filterMode) {
        this.filterMode = filterMode;
        return this;
    }

    public Object getMaxValueSpan(){
        return maxValueSpan;
    }

    public EChartDataZoomInside setMaxValueSpan(Object maxValueSpan) {
        this.maxValueSpan = maxValueSpan;
        return this;
    }

    public Boolean getMoveOnMouseMove(){
        return moveOnMouseMove;
    }

    public EChartDataZoomInside setMoveOnMouseMove(Boolean moveOnMouseMove) {
        this.moveOnMouseMove = moveOnMouseMove;
        return this;
    }

    public Number getMinSpan(){
        return minSpan;
    }

    public EChartDataZoomInside setMinSpan(Number minSpan) {
        this.minSpan = minSpan;
        return this;
    }

    public Boolean getPreventDefaultMouseMove(){
        return preventDefaultMouseMove;
    }

    public EChartDataZoomInside setPreventDefaultMouseMove(Boolean preventDefaultMouseMove) {
        this.preventDefaultMouseMove = preventDefaultMouseMove;
        return this;
    }

    public Boolean getZoomOnMouseWheel(){
        return zoomOnMouseWheel;
    }

    public EChartDataZoomInside setZoomOnMouseWheel(Boolean zoomOnMouseWheel) {
        this.zoomOnMouseWheel = zoomOnMouseWheel;
        return this;
    }

    public Boolean getDisabled(){
        return disabled;
    }

    public EChartDataZoomInside setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public Number getEnd(){
        return end;
    }

    public EChartDataZoomInside setEnd(Number end) {
        this.end = end;
        return this;
    }

    public Object getStartValue(){
        return startValue;
    }

    public EChartDataZoomInside setStartValue(Object startValue) {
        this.startValue = startValue;
        return this;
    }

    public List getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartDataZoomInside setYAxisIndex(List yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (radiusAxisIndex != null)  {
            map.put("radiusAxisIndex", radiusAxisIndex);
        }
        if (maxSpan != null)  {
            map.put("maxSpan", maxSpan);
        }
        if (throttle != null)  {
            map.put("throttle", throttle);
        }
        if (endValue != null)  {
            map.put("endValue", endValue);
        }
        if (minValueSpan != null)  {
            map.put("minValueSpan", minValueSpan);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (start != null)  {
            map.put("start", start);
        }
        if (angleAxisIndex != null)  {
            map.put("angleAxisIndex", angleAxisIndex);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (zoomLock != null)  {
            map.put("zoomLock", zoomLock);
        }
        if (rangeMode != null)  {
            map.put("rangeMode", rangeMode);
        }
        if (filterMode != null)  {
            map.put("filterMode", filterMode);
        }
        if (maxValueSpan != null)  {
            map.put("maxValueSpan", maxValueSpan);
        }
        if (moveOnMouseMove != null)  {
            map.put("moveOnMouseMove", moveOnMouseMove);
        }
        if (minSpan != null)  {
            map.put("minSpan", minSpan);
        }
        if (preventDefaultMouseMove != null)  {
            map.put("preventDefaultMouseMove", preventDefaultMouseMove);
        }
        if (zoomOnMouseWheel != null)  {
            map.put("zoomOnMouseWheel", zoomOnMouseWheel);
        }
        if (disabled != null)  {
            map.put("disabled", disabled);
        }
        if (end != null)  {
            map.put("end", end);
        }
        if (startValue != null)  {
            map.put("startValue", startValue);
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
