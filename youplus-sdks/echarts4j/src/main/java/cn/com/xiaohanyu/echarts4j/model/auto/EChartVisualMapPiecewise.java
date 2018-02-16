package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>分段型视觉映射组件（visualMapPiecewise）</strong></p>
 * <p>（参考<a href="#visualMap">视觉映射组件（visualMap）的介绍</a>）</p>
 * <p>展现形式如下图：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/scatter-visualMap-piecewise&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * 
 * <p>分段型视觉映射组件，有三种模式：</p>
 * <ul>
 * <li><strong>连续型数据平均分段</strong>: 依据 <a href="#visualMap-piecewise.splitNumber">visualMap-piecewise.splitNumber</a> 来自动平均分割成若干块。</li>
 * <li><strong>连续型数据自定义分段</strong>: 依据 <a href="#visualMap-piecewise.pieces">visualMap-piecewise.pieces</a> 来定义每块范围。</li>
 * <li><strong>离散数据根据类别分段</strong>: 类别定义在 <a href="#visualMap-piecewise.categories">visualMap-piecewise.categories</a> 中。</li>
 * </ul>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartVisualMapPiecewise implements Serializable {
    private static final long serialVersionUID = 1L;

    private Color borderColor;

    private Boolean maxOpen;

    private Boolean hoverLink;

    private Color color;

    private Number precision;

    private Number itemHeight;

    private Boolean show;

    private Object outOfRange;

    private String type;

    private String align;

    private Object inRange;

    private Boolean showLabel;

    private List seriesIndex;

    private List pieces;

    private Number min;

    private List textGap;

    private Object top;

    private Number borderWidth;

    private Number itemWidth;

    private Number zlevel;

    private List categories;

    private Boolean minOpen;

    private List text;

    private Number dimension;

    private Boolean inverse;

    private Number itemGap;

    private List padding;

    private EChartVisualMapPiecewiseController controller;

    private Color backgroundColor;

    private String orient;

    private Number max;

    private Object bottom;

    private Number splitNumber;

    private Object right;

    private Object formatter;

    private String itemSymbol;

    private Object left;

    private String selectedMode;

    private Number z;

    private TextStyle textStyle;


    public Color getBorderColor(){
        return borderColor;
    }

    public EChartVisualMapPiecewise setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Boolean getMaxOpen(){
        return maxOpen;
    }

    public EChartVisualMapPiecewise setMaxOpen(Boolean maxOpen) {
        this.maxOpen = maxOpen;
        return this;
    }

    public Boolean getHoverLink(){
        return hoverLink;
    }

    public EChartVisualMapPiecewise setHoverLink(Boolean hoverLink) {
        this.hoverLink = hoverLink;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartVisualMapPiecewise setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getPrecision(){
        return precision;
    }

    public EChartVisualMapPiecewise setPrecision(Number precision) {
        this.precision = precision;
        return this;
    }

    public Number getItemHeight(){
        return itemHeight;
    }

    public EChartVisualMapPiecewise setItemHeight(Number itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartVisualMapPiecewise setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getOutOfRange(){
        return outOfRange;
    }

    public EChartVisualMapPiecewise setOutOfRange(Object outOfRange) {
        this.outOfRange = outOfRange;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartVisualMapPiecewise setType(String type) {
        this.type = type;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public EChartVisualMapPiecewise setAlign(String align) {
        this.align = align;
        return this;
    }

    public Object getInRange(){
        return inRange;
    }

    public EChartVisualMapPiecewise setInRange(Object inRange) {
        this.inRange = inRange;
        return this;
    }

    public Boolean getShowLabel(){
        return showLabel;
    }

    public EChartVisualMapPiecewise setShowLabel(Boolean showLabel) {
        this.showLabel = showLabel;
        return this;
    }

    public List getSeriesIndex(){
        return seriesIndex;
    }

    public EChartVisualMapPiecewise setSeriesIndex(List seriesIndex) {
        this.seriesIndex = seriesIndex;
        return this;
    }

    public List getPieces(){
        return pieces;
    }

    public EChartVisualMapPiecewise setPieces(List pieces) {
        this.pieces = pieces;
        return this;
    }

    public Number getMin(){
        return min;
    }

    public EChartVisualMapPiecewise setMin(Number min) {
        this.min = min;
        return this;
    }

    public List getTextGap(){
        return textGap;
    }

    public EChartVisualMapPiecewise setTextGap(List textGap) {
        this.textGap = textGap;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartVisualMapPiecewise setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartVisualMapPiecewise setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getItemWidth(){
        return itemWidth;
    }

    public EChartVisualMapPiecewise setItemWidth(Number itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartVisualMapPiecewise setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public List getCategories(){
        return categories;
    }

    public EChartVisualMapPiecewise setCategories(List categories) {
        this.categories = categories;
        return this;
    }

    public Boolean getMinOpen(){
        return minOpen;
    }

    public EChartVisualMapPiecewise setMinOpen(Boolean minOpen) {
        this.minOpen = minOpen;
        return this;
    }

    public List getText(){
        return text;
    }

    public EChartVisualMapPiecewise setText(List text) {
        this.text = text;
        return this;
    }

    public Number getDimension(){
        return dimension;
    }

    public EChartVisualMapPiecewise setDimension(Number dimension) {
        this.dimension = dimension;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartVisualMapPiecewise setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Number getItemGap(){
        return itemGap;
    }

    public EChartVisualMapPiecewise setItemGap(Number itemGap) {
        this.itemGap = itemGap;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public EChartVisualMapPiecewise setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public EChartVisualMapPiecewiseController getController(){
        return controller;
    }

    public EChartVisualMapPiecewise setController(EChartVisualMapPiecewiseController controller) {
        this.controller = controller;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartVisualMapPiecewise setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartVisualMapPiecewise setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public Number getMax(){
        return max;
    }

    public EChartVisualMapPiecewise setMax(Number max) {
        this.max = max;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartVisualMapPiecewise setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartVisualMapPiecewise setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartVisualMapPiecewise setRight(Object right) {
        this.right = right;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartVisualMapPiecewise setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public String getItemSymbol(){
        return itemSymbol;
    }

    public EChartVisualMapPiecewise setItemSymbol(String itemSymbol) {
        this.itemSymbol = itemSymbol;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartVisualMapPiecewise setLeft(Object left) {
        this.left = left;
        return this;
    }

    public String getSelectedMode(){
        return selectedMode;
    }

    public EChartVisualMapPiecewise setSelectedMode(String selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartVisualMapPiecewise setZ(Number z) {
        this.z = z;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartVisualMapPiecewise setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (maxOpen != null)  {
            map.put("maxOpen", maxOpen);
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
        if (showLabel != null)  {
            map.put("showLabel", showLabel);
        }
        if (seriesIndex != null)  {
            map.put("seriesIndex", seriesIndex);
        }
        if (pieces != null)  {
            map.put("pieces", pieces);
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
        if (categories != null)  {
            map.put("categories", categories);
        }
        if (minOpen != null)  {
            map.put("minOpen", minOpen);
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
        if (itemGap != null)  {
            map.put("itemGap", itemGap);
        }
        if (padding != null)  {
            map.put("padding", padding);
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
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (formatter != null)  {
            map.put("formatter", formatter);
        }
        if (itemSymbol != null)  {
            map.put("itemSymbol", itemSymbol);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (selectedMode != null)  {
            map.put("selectedMode", selectedMode);
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
