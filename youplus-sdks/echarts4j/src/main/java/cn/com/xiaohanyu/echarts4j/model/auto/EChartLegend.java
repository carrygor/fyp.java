package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>图例组件。</p>
 * <p>图例组件展现了不同系列的标记(symbol)，颜色和名字。可以通过点击图例控制哪些系列不显示。</p>
 * <p>ECharts 3 中单个 echarts 实例中可以存在多个图例组件，会方便多个图例的布局。</p>
 * <p>当图例数量过多时，可以使用 <a href="http://echarts.baidu.com/gallery/editor.html?c=pie-legend&amp;edit=1&amp;reset=1" target="_blank">滚动图例（垂直）</a> 或 <a href="http://echarts.baidu.com/gallery/editor.html?c=radar2&amp;edit=1&amp;reset=1" target="_blank">滚动图例（水平）</a>，参见：<a href="#legend.type">legend.type</a></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartLegend implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Color borderColor;

    private Number shadowOffsetY;

    private List pageIconSize;

    private List data;

    private Number shadowBlur;

    private Number scrollDataIndex;

    private Number itemHeight;

    private Boolean show;

    private Object tooltip;

    private String type;

    private String align;

    private Object top;

    private Number borderWidth;

    private Number itemWidth;

    private Number zlevel;

    private Color shadowColor;

    private Object selected;

    private Object height;

    private Number padding;

    private Number itemGap;

    private Color backgroundColor;

    private Color inactiveColor;

    private String orient;

    private EChartLegendPageTextStyle pageTextStyle;

    private Number pageButtonItemGap;

    private Object bottom;

    private String pageButtonPosition;

    private Object right;

    private String pageIconInactiveColor;

    private Boolean animation;

    private Object formatter;

    private List borderRadius;

    private Object left;

    private Object selectedMode;

    private Number pageButtonGap;

    private Object pageFormatter;

    private Object width;

    private Number z;

    private EChartLegendPageIcons pageIcons;

    private TextStyle textStyle;

    private String pageIconColor;

    private Number animationDurationUpdate;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartLegend setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartLegend setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartLegend setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public List getPageIconSize(){
        return pageIconSize;
    }

    public EChartLegend setPageIconSize(List pageIconSize) {
        this.pageIconSize = pageIconSize;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartLegend setData(List data) {
        this.data = data;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartLegend setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getScrollDataIndex(){
        return scrollDataIndex;
    }

    public EChartLegend setScrollDataIndex(Number scrollDataIndex) {
        this.scrollDataIndex = scrollDataIndex;
        return this;
    }

    public Number getItemHeight(){
        return itemHeight;
    }

    public EChartLegend setItemHeight(Number itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartLegend setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getTooltip(){
        return tooltip;
    }

    public EChartLegend setTooltip(Object tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartLegend setType(String type) {
        this.type = type;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public EChartLegend setAlign(String align) {
        this.align = align;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartLegend setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartLegend setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getItemWidth(){
        return itemWidth;
    }

    public EChartLegend setItemWidth(Number itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartLegend setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public EChartLegend setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Object getSelected(){
        return selected;
    }

    public EChartLegend setSelected(Object selected) {
        this.selected = selected;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartLegend setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Number getPadding(){
        return padding;
    }

    public EChartLegend setPadding(Number padding) {
        this.padding = padding;
        return this;
    }

    public Number getItemGap(){
        return itemGap;
    }

    public EChartLegend setItemGap(Number itemGap) {
        this.itemGap = itemGap;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartLegend setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Color getInactiveColor(){
        return inactiveColor;
    }

    public EChartLegend setInactiveColor(Color inactiveColor) {
        this.inactiveColor = inactiveColor;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartLegend setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public EChartLegendPageTextStyle getPageTextStyle(){
        return pageTextStyle;
    }

    public EChartLegend setPageTextStyle(EChartLegendPageTextStyle pageTextStyle) {
        this.pageTextStyle = pageTextStyle;
        return this;
    }

    public Number getPageButtonItemGap(){
        return pageButtonItemGap;
    }

    public EChartLegend setPageButtonItemGap(Number pageButtonItemGap) {
        this.pageButtonItemGap = pageButtonItemGap;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartLegend setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public String getPageButtonPosition(){
        return pageButtonPosition;
    }

    public EChartLegend setPageButtonPosition(String pageButtonPosition) {
        this.pageButtonPosition = pageButtonPosition;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartLegend setRight(Object right) {
        this.right = right;
        return this;
    }

    public String getPageIconInactiveColor(){
        return pageIconInactiveColor;
    }

    public EChartLegend setPageIconInactiveColor(String pageIconInactiveColor) {
        this.pageIconInactiveColor = pageIconInactiveColor;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartLegend setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartLegend setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public List getBorderRadius(){
        return borderRadius;
    }

    public EChartLegend setBorderRadius(List borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartLegend setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getSelectedMode(){
        return selectedMode;
    }

    public EChartLegend setSelectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    public Number getPageButtonGap(){
        return pageButtonGap;
    }

    public EChartLegend setPageButtonGap(Number pageButtonGap) {
        this.pageButtonGap = pageButtonGap;
        return this;
    }

    public Object getPageFormatter(){
        return pageFormatter;
    }

    public EChartLegend setPageFormatter(Object pageFormatter) {
        this.pageFormatter = pageFormatter;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartLegend setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartLegend setZ(Number z) {
        this.z = z;
        return this;
    }

    public EChartLegendPageIcons getPageIcons(){
        return pageIcons;
    }

    public EChartLegend setPageIcons(EChartLegendPageIcons pageIcons) {
        this.pageIcons = pageIcons;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartLegend setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public String getPageIconColor(){
        return pageIconColor;
    }

    public EChartLegend setPageIconColor(String pageIconColor) {
        this.pageIconColor = pageIconColor;
        return this;
    }

    public Number getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartLegend setAnimationDurationUpdate(Number animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (pageIconSize != null)  {
            map.put("pageIconSize", pageIconSize);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (scrollDataIndex != null)  {
            map.put("scrollDataIndex", scrollDataIndex);
        }
        if (itemHeight != null)  {
            map.put("itemHeight", itemHeight);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (align != null)  {
            map.put("align", align);
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
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        if (selected != null)  {
            map.put("selected", selected);
        }
        if (height != null)  {
            map.put("height", height);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (itemGap != null)  {
            map.put("itemGap", itemGap);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (inactiveColor != null)  {
            map.put("inactiveColor", inactiveColor);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (pageTextStyle != null)  {
            map.put("pageTextStyle", pageTextStyle.toMap());
        }
        if (pageButtonItemGap != null)  {
            map.put("pageButtonItemGap", pageButtonItemGap);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (pageButtonPosition != null)  {
            map.put("pageButtonPosition", pageButtonPosition);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (pageIconInactiveColor != null)  {
            map.put("pageIconInactiveColor", pageIconInactiveColor);
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (formatter != null)  {
            map.put("formatter", formatter);
        }
        if (borderRadius != null)  {
            map.put("borderRadius", borderRadius);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (selectedMode != null)  {
            map.put("selectedMode", selectedMode);
        }
        if (pageButtonGap != null)  {
            map.put("pageButtonGap", pageButtonGap);
        }
        if (pageFormatter != null)  {
            map.put("pageFormatter", pageFormatter);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (pageIcons != null)  {
            map.put("pageIcons", pageIcons.toMap());
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        if (pageIconColor != null)  {
            map.put("pageIconColor", pageIconColor);
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
