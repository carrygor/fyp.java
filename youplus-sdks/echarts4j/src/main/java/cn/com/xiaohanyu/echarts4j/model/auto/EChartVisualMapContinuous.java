package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>连续型视觉映射组件（visualMapContinuous）</strong></p>
 * <p>（参考<a href="#visualMap">视觉映射组件（visualMap）的介绍</a>）</p>
 * <p>展现形式如下图：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/map-visualMap-continuous&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * <p><code>visualMapContinuous</code>中，可以通过 <a href="#visualMap.calculable">visualMap.calculable</a> 来显示或隐藏手柄（手柄能拖拽改变值域）。</p>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartVisualMapContinuous implements Serializable {
    private static final long serialVersionUID = 1L;

    private Color borderColor;

    private Boolean hoverLink;

    private Color color;

    private Number precision;

    private Number itemHeight;

    private Boolean show;

    private List range;

    private Object outOfRange;

    private String type;

    private String align;

    private Object inRange;

    private List seriesIndex;

    private Number min;

    private List textGap;

    private Object top;

    private Number borderWidth;

    private Number itemWidth;

    private Number zlevel;

    private List text;

    private Number dimension;

    private Boolean inverse;

    private List padding;

    private Boolean realtime;

    private EChartVisualMapContinuousController controller;

    private Color backgroundColor;

    private String orient;

    private Number max;

    private Boolean calculable;

    private Object bottom;

    private Object right;

    private Object formatter;

    private Object left;

    private Number z;

    private TextStyle textStyle;


    public Color getBorderColor(){
        return borderColor;
    }

    public EChartVisualMapContinuous setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Boolean getHoverLink(){
        return hoverLink;
    }

    public EChartVisualMapContinuous setHoverLink(Boolean hoverLink) {
        this.hoverLink = hoverLink;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartVisualMapContinuous setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getPrecision(){
        return precision;
    }

    public EChartVisualMapContinuous setPrecision(Number precision) {
        this.precision = precision;
        return this;
    }

    public Number getItemHeight(){
        return itemHeight;
    }

    public EChartVisualMapContinuous setItemHeight(Number itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartVisualMapContinuous setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public List getRange(){
        return range;
    }

    public EChartVisualMapContinuous setRange(List range) {
        this.range = range;
        return this;
    }

    public Object getOutOfRange(){
        return outOfRange;
    }

    public EChartVisualMapContinuous setOutOfRange(Object outOfRange) {
        this.outOfRange = outOfRange;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartVisualMapContinuous setType(String type) {
        this.type = type;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public EChartVisualMapContinuous setAlign(String align) {
        this.align = align;
        return this;
    }

    public Object getInRange(){
        return inRange;
    }

    public EChartVisualMapContinuous setInRange(Object inRange) {
        this.inRange = inRange;
        return this;
    }

    public List getSeriesIndex(){
        return seriesIndex;
    }

    public EChartVisualMapContinuous setSeriesIndex(List seriesIndex) {
        this.seriesIndex = seriesIndex;
        return this;
    }

    public Number getMin(){
        return min;
    }

    public EChartVisualMapContinuous setMin(Number min) {
        this.min = min;
        return this;
    }

    public List getTextGap(){
        return textGap;
    }

    public EChartVisualMapContinuous setTextGap(List textGap) {
        this.textGap = textGap;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartVisualMapContinuous setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartVisualMapContinuous setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getItemWidth(){
        return itemWidth;
    }

    public EChartVisualMapContinuous setItemWidth(Number itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartVisualMapContinuous setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public List getText(){
        return text;
    }

    public EChartVisualMapContinuous setText(List text) {
        this.text = text;
        return this;
    }

    public Number getDimension(){
        return dimension;
    }

    public EChartVisualMapContinuous setDimension(Number dimension) {
        this.dimension = dimension;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartVisualMapContinuous setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public EChartVisualMapContinuous setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Boolean getRealtime(){
        return realtime;
    }

    public EChartVisualMapContinuous setRealtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    public EChartVisualMapContinuousController getController(){
        return controller;
    }

    public EChartVisualMapContinuous setController(EChartVisualMapContinuousController controller) {
        this.controller = controller;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartVisualMapContinuous setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartVisualMapContinuous setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public Number getMax(){
        return max;
    }

    public EChartVisualMapContinuous setMax(Number max) {
        this.max = max;
        return this;
    }

    public Boolean getCalculable(){
        return calculable;
    }

    public EChartVisualMapContinuous setCalculable(Boolean calculable) {
        this.calculable = calculable;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartVisualMapContinuous setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartVisualMapContinuous setRight(Object right) {
        this.right = right;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartVisualMapContinuous setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartVisualMapContinuous setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartVisualMapContinuous setZ(Number z) {
        this.z = z;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartVisualMapContinuous setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (hoverLink != null)  {
            map.put("hoverLink", hoverLink);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (precision != null)  {
            map.put("precision", precision);
        }
        if (itemHeight != null)  {
            map.put("itemHeight", itemHeight);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (range != null)  {
            map.put("range", range);
        }
        if (outOfRange != null)  {
            map.put("outOfRange", outOfRange);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (align != null)  {
            map.put("align", align);
        }
        if (inRange != null)  {
            map.put("inRange", inRange);
        }
        if (seriesIndex != null)  {
            map.put("seriesIndex", seriesIndex);
        }
        if (min != null)  {
            map.put("min", min);
        }
        if (textGap != null)  {
            map.put("textGap", textGap);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (itemWidth != null)  {
            map.put("itemWidth", itemWidth);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (text != null)  {
            map.put("text", text);
        }
        if (dimension != null)  {
            map.put("dimension", dimension);
        }
        if (inverse != null)  {
            map.put("inverse", inverse);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (realtime != null)  {
            map.put("realtime", realtime);
        }
        if (controller != null)  {
            map.put("controller", controller.toMap());
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (max != null)  {
            map.put("max", max);
        }
        if (calculable != null)  {
            map.put("calculable", calculable);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (formatter != null)  {
            map.put("formatter", formatter);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
