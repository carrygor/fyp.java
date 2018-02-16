package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>象形柱图</strong></p>
 * <p>象形柱图是可以设置各种具象图形元素（如图片、<a href="http://www.w3.org/TR/SVG/paths.html#PathData" target="_blank">SVG PathData</a> 等）的柱状图。往往用在信息图中。用于有至少一个类目轴或时间轴的<a href="#grid">直角坐标系</a>上。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=pictorialBar-hill&reset=1&edit=1" width="800" height="400" ></iframe>
 * 
 * 
 * 
 * <p><strong>布局</strong></p>
 * <p>象形柱图可以被想象为：它首先是个柱状图，但是柱状图的柱子并不显示。这些柱子我们称为『基准柱（reference bar）』，根据基准柱来定位和显示各种象形图形（包括图片）。</p>
 * <p>每个象形图形根据基准柱的定位，是通过 <a href="#series-pictorialBar.symbolPosition">symbolPosition</a>、<a href="#series-pictorialBar.symbolOffset">symbolOffset</a> 来调整其于基准柱的相对位置。</p>
 * <p>参见例子：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/pictorialBar-position&reset=1&edit=1" width="800" height="600" ></iframe>
 * 
 * 
 * <p>可以使用 <a href="#series-pictorialBar.symbolSize">symbolSize</a> 调整大小，从而形成各种视图效果。</p>
 * <p>参见例子：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/pictorialBar-symbolSize&reset=1&edit=1" width="800" height="600" ></iframe>
 * 
 * 
 * 
 * <p><strong>象形图形类型</strong></p>
 * <p>每个图形可以配置成『单独』和『重复』两种类型，即通过 <a href="#series-pictorialBar.symbolRepeat">symbolRepeat</a> 来设置。</p>
 * <ul>
 * <li>设置为 <code>false</code>（默认），则一个图形来代表一个数据项。</li>
 * <li>设置为 <code>true</code>，则一组重复的图形来代表一个数据项。</li>
 * </ul>
 * <p>参见例子：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/pictorialBar-repeat&reset=1&edit=1" width="800" height="400" ></iframe>
 * 
 * 
 * <p>每个象形图形可以是基本图形（如 <code>&#39;circle&#39;</code>, <code>&#39;rect&#39;</code>, ...）、<a href="http://www.w3.org/TR/SVG/paths.html#PathData" target="_blank">SVG PathData</a>、图片，参见：<a href="#series-pictorialBar.symbolType">symbolType</a>。</p>
 * <p>参见例子：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/pictorialBar-graphicType&reset=1&edit=1" width="800" height="400" ></iframe>
 * 
 * 
 * <p>可以使用 <a href="#series-pictorialBar.symbolClip">symbolClip</a> 对图形进行剪裁。</p>
 * <p>参见例子：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/pictorialBar-clip&reset=1&edit=1" width="800" height="600" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesPictorialBar implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Object barWidth;

    private Object encode;

    private String symbol;

    private String symbolPosition;

    private Object symbolRepeat;

    private List data;

    private String barGap;

    private Number symbolPatternSize;

    private EChartSeriesPictorialBarTooltip tooltip;

    private String type;

    private EChartSeriesPictorialBarMarkLine markLine;

    private Boolean legendHoverLink;

    private String symbolRepeatDirection;

    private EChartSeriesPictorialBarMarkArea markArea;

    private Number animationThreshold;

    private List symbolBoundingData;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean symbolClip;

    private Number barMinHeight;

    private String barCategoryGap;

    private Boolean silent;

    private String animationEasing;

    private Object barMaxWidth;

    private List symbolSize;

    private Number xAxisIndex;

    private ItemStyle itemStyle;

    private EChartSeriesPictorialBarLabel label;

    private EChartSeriesPictorialBarMarkPoint markPoint;

    private Boolean animation;

    private Boolean hoverAnimation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private Number symbolRotate;

    private Object symbolMargin;

    private List symbolOffset;

    private String name;

    private Number z;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private List dimensions;


    public String getCursor(){
        return cursor;
    }

    public EChartSeriesPictorialBar setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getBarWidth(){
        return barWidth;
    }

    public EChartSeriesPictorialBar setBarWidth(Object barWidth) {
        this.barWidth = barWidth;
        return this;
    }

    public Object getEncode(){
        return encode;
    }

    public EChartSeriesPictorialBar setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesPictorialBar setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getSymbolPosition(){
        return symbolPosition;
    }

    public EChartSeriesPictorialBar setSymbolPosition(String symbolPosition) {
        this.symbolPosition = symbolPosition;
        return this;
    }

    public Object getSymbolRepeat(){
        return symbolRepeat;
    }

    public EChartSeriesPictorialBar setSymbolRepeat(Object symbolRepeat) {
        this.symbolRepeat = symbolRepeat;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesPictorialBar setData(List data) {
        this.data = data;
        return this;
    }

    public String getBarGap(){
        return barGap;
    }

    public EChartSeriesPictorialBar setBarGap(String barGap) {
        this.barGap = barGap;
        return this;
    }

    public Number getSymbolPatternSize(){
        return symbolPatternSize;
    }

    public EChartSeriesPictorialBar setSymbolPatternSize(Number symbolPatternSize) {
        this.symbolPatternSize = symbolPatternSize;
        return this;
    }

    public EChartSeriesPictorialBarTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesPictorialBar setTooltip(EChartSeriesPictorialBarTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesPictorialBar setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesPictorialBarMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesPictorialBar setMarkLine(EChartSeriesPictorialBarMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesPictorialBar setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public String getSymbolRepeatDirection(){
        return symbolRepeatDirection;
    }

    public EChartSeriesPictorialBar setSymbolRepeatDirection(String symbolRepeatDirection) {
        this.symbolRepeatDirection = symbolRepeatDirection;
        return this;
    }

    public EChartSeriesPictorialBarMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesPictorialBar setMarkArea(EChartSeriesPictorialBarMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesPictorialBar setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public List getSymbolBoundingData(){
        return symbolBoundingData;
    }

    public EChartSeriesPictorialBar setSymbolBoundingData(List symbolBoundingData) {
        this.symbolBoundingData = symbolBoundingData;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesPictorialBar setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesPictorialBar setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSymbolClip(){
        return symbolClip;
    }

    public EChartSeriesPictorialBar setSymbolClip(Boolean symbolClip) {
        this.symbolClip = symbolClip;
        return this;
    }

    public Number getBarMinHeight(){
        return barMinHeight;
    }

    public EChartSeriesPictorialBar setBarMinHeight(Number barMinHeight) {
        this.barMinHeight = barMinHeight;
        return this;
    }

    public String getBarCategoryGap(){
        return barCategoryGap;
    }

    public EChartSeriesPictorialBar setBarCategoryGap(String barCategoryGap) {
        this.barCategoryGap = barCategoryGap;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesPictorialBar setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesPictorialBar setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getBarMaxWidth(){
        return barMaxWidth;
    }

    public EChartSeriesPictorialBar setBarMaxWidth(Object barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesPictorialBar setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesPictorialBar setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesPictorialBar setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesPictorialBarLabel getLabel(){
        return label;
    }

    public EChartSeriesPictorialBar setLabel(EChartSeriesPictorialBarLabel label) {
        this.label = label;
        return this;
    }

    public EChartSeriesPictorialBarMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesPictorialBar setMarkPoint(EChartSeriesPictorialBarMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesPictorialBar setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesPictorialBar setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesPictorialBar setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesPictorialBar setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesPictorialBar setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public Object getSymbolMargin(){
        return symbolMargin;
    }

    public EChartSeriesPictorialBar setSymbolMargin(Object symbolMargin) {
        this.symbolMargin = symbolMargin;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesPictorialBar setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesPictorialBar setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesPictorialBar setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesPictorialBar setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesPictorialBar setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesPictorialBar setDimensions(List dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cursor != null)  {
            map.put("cursor", cursor);
        }
        if (barWidth != null)  {
            map.put("barWidth", barWidth);
        }
        if (encode != null)  {
            map.put("encode", encode);
        }
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (symbolPosition != null)  {
            map.put("symbolPosition", symbolPosition);
        }
        if (symbolRepeat != null)  {
            map.put("symbolRepeat", symbolRepeat);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (barGap != null)  {
            map.put("barGap", barGap);
        }
        if (symbolPatternSize != null)  {
            map.put("symbolPatternSize", symbolPatternSize);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
        }
        if (symbolRepeatDirection != null)  {
            map.put("symbolRepeatDirection", symbolRepeatDirection);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (symbolBoundingData != null)  {
            map.put("symbolBoundingData", symbolBoundingData);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (symbolClip != null)  {
            map.put("symbolClip", symbolClip);
        }
        if (barMinHeight != null)  {
            map.put("barMinHeight", barMinHeight);
        }
        if (barCategoryGap != null)  {
            map.put("barCategoryGap", barCategoryGap);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (barMaxWidth != null)  {
            map.put("barMaxWidth", barMaxWidth);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (hoverAnimation != null)  {
            map.put("hoverAnimation", hoverAnimation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (symbolMargin != null)  {
            map.put("symbolMargin", symbolMargin);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        if (dimensions != null)  {
            map.put("dimensions", dimensions);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
