package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>滑动条型数据区域缩放组件（dataZoomSlider）</strong></p>
 * <p>（参考<a href="#dataZoom">数据区域缩放组件（dataZoom）的介绍</a>）</p>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartDataZoomSlider implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number maxSpan;

    private Number throttle;

    private Color borderColor;

    private Object endValue;

    private Boolean show;

    private List angleAxisIndex;

    private String type;

    private Color fillerColor;

    private String filterMode;

    private Object maxValueSpan;

    private Object top;

    private Number minSpan;

    private Number end;

    private Number zlevel;

    private Object startValue;

    private EChartDataZoomSliderDataBackground dataBackground;

    private List radiusAxisIndex;

    private Color backgroundColor;

    private Boolean realtime;

    private Object minValueSpan;

    private String orient;

    private Object bottom;

    private List xAxisIndex;

    private Number start;

    private Object right;

    private Object labelFormatter;

    private Boolean showDetail;

    private Boolean zoomLock;

    private String handleIcon;

    private List rangeMode;

    private Number labelPrecision;

    private EChartDataZoomSliderHandleStyle handleStyle;

    private Object left;

    private String showDataShadow;

    private Number z;

    private TextStyle textStyle;

    private Number handleSize;

    private List yAxisIndex;


    public Number getMaxSpan(){
        return maxSpan;
    }

    public EChartDataZoomSlider setMaxSpan(Number maxSpan) {
        this.maxSpan = maxSpan;
        return this;
    }

    public Number getThrottle(){
        return throttle;
    }

    public EChartDataZoomSlider setThrottle(Number throttle) {
        this.throttle = throttle;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartDataZoomSlider setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Object getEndValue(){
        return endValue;
    }

    public EChartDataZoomSlider setEndValue(Object endValue) {
        this.endValue = endValue;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartDataZoomSlider setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public List getAngleAxisIndex(){
        return angleAxisIndex;
    }

    public EChartDataZoomSlider setAngleAxisIndex(List angleAxisIndex) {
        this.angleAxisIndex = angleAxisIndex;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartDataZoomSlider setType(String type) {
        this.type = type;
        return this;
    }

    public Color getFillerColor(){
        return fillerColor;
    }

    public EChartDataZoomSlider setFillerColor(Color fillerColor) {
        this.fillerColor = fillerColor;
        return this;
    }

    public String getFilterMode(){
        return filterMode;
    }

    public EChartDataZoomSlider setFilterMode(String filterMode) {
        this.filterMode = filterMode;
        return this;
    }

    public Object getMaxValueSpan(){
        return maxValueSpan;
    }

    public EChartDataZoomSlider setMaxValueSpan(Object maxValueSpan) {
        this.maxValueSpan = maxValueSpan;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartDataZoomSlider setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getMinSpan(){
        return minSpan;
    }

    public EChartDataZoomSlider setMinSpan(Number minSpan) {
        this.minSpan = minSpan;
        return this;
    }

    public Number getEnd(){
        return end;
    }

    public EChartDataZoomSlider setEnd(Number end) {
        this.end = end;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartDataZoomSlider setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Object getStartValue(){
        return startValue;
    }

    public EChartDataZoomSlider setStartValue(Object startValue) {
        this.startValue = startValue;
        return this;
    }

    public EChartDataZoomSliderDataBackground getDataBackground(){
        return dataBackground;
    }

    public EChartDataZoomSlider setDataBackground(EChartDataZoomSliderDataBackground dataBackground) {
        this.dataBackground = dataBackground;
        return this;
    }

    public List getRadiusAxisIndex(){
        return radiusAxisIndex;
    }

    public EChartDataZoomSlider setRadiusAxisIndex(List radiusAxisIndex) {
        this.radiusAxisIndex = radiusAxisIndex;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartDataZoomSlider setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Boolean getRealtime(){
        return realtime;
    }

    public EChartDataZoomSlider setRealtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    public Object getMinValueSpan(){
        return minValueSpan;
    }

    public EChartDataZoomSlider setMinValueSpan(Object minValueSpan) {
        this.minValueSpan = minValueSpan;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartDataZoomSlider setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartDataZoomSlider setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public List getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartDataZoomSlider setXAxisIndex(List xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Number getStart(){
        return start;
    }

    public EChartDataZoomSlider setStart(Number start) {
        this.start = start;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartDataZoomSlider setRight(Object right) {
        this.right = right;
        return this;
    }

    public Object getLabelFormatter(){
        return labelFormatter;
    }

    public EChartDataZoomSlider setLabelFormatter(Object labelFormatter) {
        this.labelFormatter = labelFormatter;
        return this;
    }

    public Boolean getShowDetail(){
        return showDetail;
    }

    public EChartDataZoomSlider setShowDetail(Boolean showDetail) {
        this.showDetail = showDetail;
        return this;
    }

    public Boolean getZoomLock(){
        return zoomLock;
    }

    public EChartDataZoomSlider setZoomLock(Boolean zoomLock) {
        this.zoomLock = zoomLock;
        return this;
    }

    public String getHandleIcon(){
        return handleIcon;
    }

    public EChartDataZoomSlider setHandleIcon(String handleIcon) {
        this.handleIcon = handleIcon;
        return this;
    }

    public List getRangeMode(){
        return rangeMode;
    }

    public EChartDataZoomSlider setRangeMode(List rangeMode) {
        this.rangeMode = rangeMode;
        return this;
    }

    public Number getLabelPrecision(){
        return labelPrecision;
    }

    public EChartDataZoomSlider setLabelPrecision(Number labelPrecision) {
        this.labelPrecision = labelPrecision;
        return this;
    }

    public EChartDataZoomSliderHandleStyle getHandleStyle(){
        return handleStyle;
    }

    public EChartDataZoomSlider setHandleStyle(EChartDataZoomSliderHandleStyle handleStyle) {
        this.handleStyle = handleStyle;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartDataZoomSlider setLeft(Object left) {
        this.left = left;
        return this;
    }

    public String getShowDataShadow(){
        return showDataShadow;
    }

    public EChartDataZoomSlider setShowDataShadow(String showDataShadow) {
        this.showDataShadow = showDataShadow;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartDataZoomSlider setZ(Number z) {
        this.z = z;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartDataZoomSlider setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Number getHandleSize(){
        return handleSize;
    }

    public EChartDataZoomSlider setHandleSize(Number handleSize) {
        this.handleSize = handleSize;
        return this;
    }

    public List getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartDataZoomSlider setYAxisIndex(List yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (maxSpan != null)  {
            map.put("maxSpan", maxSpan);
        }
        if (throttle != null)  {
            map.put("throttle", throttle);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (endValue != null)  {
            map.put("endValue", endValue);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (angleAxisIndex != null)  {
            map.put("angleAxisIndex", angleAxisIndex);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (fillerColor != null)  {
            map.put("fillerColor", fillerColor);
        }
        if (filterMode != null)  {
            map.put("filterMode", filterMode);
        }
        if (maxValueSpan != null)  {
            map.put("maxValueSpan", maxValueSpan);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (minSpan != null)  {
            map.put("minSpan", minSpan);
        }
        if (end != null)  {
            map.put("end", end);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (startValue != null)  {
            map.put("startValue", startValue);
        }
        if (dataBackground != null)  {
            map.put("dataBackground", dataBackground.toMap());
        }
        if (radiusAxisIndex != null)  {
            map.put("radiusAxisIndex", radiusAxisIndex);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (realtime != null)  {
            map.put("realtime", realtime);
        }
        if (minValueSpan != null)  {
            map.put("minValueSpan", minValueSpan);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (start != null)  {
            map.put("start", start);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (labelFormatter != null)  {
            map.put("labelFormatter", labelFormatter);
        }
        if (showDetail != null)  {
            map.put("showDetail", showDetail);
        }
        if (zoomLock != null)  {
            map.put("zoomLock", zoomLock);
        }
        if (handleIcon != null)  {
            map.put("handleIcon", handleIcon);
        }
        if (rangeMode != null)  {
            map.put("rangeMode", rangeMode);
        }
        if (labelPrecision != null)  {
            map.put("labelPrecision", labelPrecision);
        }
        if (handleStyle != null)  {
            map.put("handleStyle", handleStyle.toMap());
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (showDataShadow != null)  {
            map.put("showDataShadow", showDataShadow);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        if (handleSize != null)  {
            map.put("handleSize", handleSize);
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
