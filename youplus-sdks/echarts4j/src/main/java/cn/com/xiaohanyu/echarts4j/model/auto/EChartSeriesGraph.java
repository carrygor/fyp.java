package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>关系图</strong></p>
 * <p>用于展现节点以及节点之间的关系数据。</p>
 * <p><strong>注意：</strong> ECharts 2.x 中 <code>force</code> 类型的图表不再在 ECharts 3 中提供支持，转为统一使用 <code>graph</code> 去展现关系数据。如果要使用力引导布局，可以将 <a href="#series-graph.layout">layout</a> 配置项设为<code>&#39;force&#39;</code>。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=graph&reset=1&edit=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    private Number nodeScaleRatio;

    private List data;

    private EChartSeriesGraphTooltip tooltip;

    private String type;

    private Number geoIndex;

    private Number animationThreshold;

    private Object edgeSymbol;

    private List links;

    private Number zlevel;

    private Boolean roam;

    private Object height;

    private Boolean focusNodeAdjacency;

    private Object bottom;

    private Number xAxisIndex;

    private Object animationDelayUpdate;

    private EChartSeriesGraphCircular circular;

    private Boolean animation;

    private String animationEasingUpdate;

    private Object left;

    private String name;

    private EChartSeriesGraphForce force;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private String cursor;

    private Object edgeSymbolSize;

    private EChartSeriesGraphMarkLine markLine;

    private Boolean legendHoverLink;

    private EChartSeriesGraphMarkArea markArea;

    private Boolean draggable;

    private LineStyle lineStyle;

    private Object top;

    private String coordinateSystem;

    private List categories;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Object symbolSize;

    private List edges;

    private ItemStyle itemStyle;

    private EChartSeriesGraphLabel label;

    private Object right;

    private EChartSeriesGraphMarkPoint markPoint;

    private Boolean hoverAnimation;

    private String layout;

    private Number animationDuration;

    private Number symbolRotate;

    private List nodes;

    private List symbolOffset;

    private Object width;

    private EChartSeriesGraphEdgeLabel edgeLabel;

    private Number z;

    private Number polarIndex;

    private Number calendarIndex;


    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesGraph setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Number getNodeScaleRatio(){
        return nodeScaleRatio;
    }

    public EChartSeriesGraph setNodeScaleRatio(Number nodeScaleRatio) {
        this.nodeScaleRatio = nodeScaleRatio;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesGraph setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesGraphTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesGraph setTooltip(EChartSeriesGraphTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesGraph setType(String type) {
        this.type = type;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesGraph setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesGraph setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Object getEdgeSymbol(){
        return edgeSymbol;
    }

    public EChartSeriesGraph setEdgeSymbol(Object edgeSymbol) {
        this.edgeSymbol = edgeSymbol;
        return this;
    }

    public List getLinks(){
        return links;
    }

    public EChartSeriesGraph setLinks(List links) {
        this.links = links;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesGraph setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Boolean getRoam(){
        return roam;
    }

    public EChartSeriesGraph setRoam(Boolean roam) {
        this.roam = roam;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartSeriesGraph setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Boolean getFocusNodeAdjacency(){
        return focusNodeAdjacency;
    }

    public EChartSeriesGraph setFocusNodeAdjacency(Boolean focusNodeAdjacency) {
        this.focusNodeAdjacency = focusNodeAdjacency;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesGraph setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesGraph setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesGraph setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public EChartSeriesGraphCircular getCircular(){
        return circular;
    }

    public EChartSeriesGraph setCircular(EChartSeriesGraphCircular circular) {
        this.circular = circular;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesGraph setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesGraph setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesGraph setLeft(Object left) {
        this.left = left;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesGraph setName(String name) {
        this.name = name;
        return this;
    }

    public EChartSeriesGraphForce getForce(){
        return force;
    }

    public EChartSeriesGraph setForce(EChartSeriesGraphForce force) {
        this.force = force;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesGraph setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesGraph setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public String getCursor(){
        return cursor;
    }

    public EChartSeriesGraph setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getEdgeSymbolSize(){
        return edgeSymbolSize;
    }

    public EChartSeriesGraph setEdgeSymbolSize(Object edgeSymbolSize) {
        this.edgeSymbolSize = edgeSymbolSize;
        return this;
    }

    public EChartSeriesGraphMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesGraph setMarkLine(EChartSeriesGraphMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesGraph setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public EChartSeriesGraphMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesGraph setMarkArea(EChartSeriesGraphMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Boolean getDraggable(){
        return draggable;
    }

    public EChartSeriesGraph setDraggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesGraph setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesGraph setTop(Object top) {
        this.top = top;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesGraph setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public List getCategories(){
        return categories;
    }

    public EChartSeriesGraph setCategories(List categories) {
        this.categories = categories;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesGraph setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesGraph setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesGraph setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesGraph setSymbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public List getEdges(){
        return edges;
    }

    public EChartSeriesGraph setEdges(List edges) {
        this.edges = edges;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesGraph setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesGraphLabel getLabel(){
        return label;
    }

    public EChartSeriesGraph setLabel(EChartSeriesGraphLabel label) {
        this.label = label;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesGraph setRight(Object right) {
        this.right = right;
        return this;
    }

    public EChartSeriesGraphMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesGraph setMarkPoint(EChartSeriesGraphMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesGraph setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public String getLayout(){
        return layout;
    }

    public EChartSeriesGraph setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesGraph setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesGraph setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public List getNodes(){
        return nodes;
    }

    public EChartSeriesGraph setNodes(List nodes) {
        this.nodes = nodes;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesGraph setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartSeriesGraph setWidth(Object width) {
        this.width = width;
        return this;
    }

    public EChartSeriesGraphEdgeLabel getEdgeLabel(){
        return edgeLabel;
    }

    public EChartSeriesGraph setEdgeLabel(EChartSeriesGraphEdgeLabel edgeLabel) {
        this.edgeLabel = edgeLabel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesGraph setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartSeriesGraph setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Number getCalendarIndex(){
        return calendarIndex;
    }

    public EChartSeriesGraph setCalendarIndex(Number calendarIndex) {
        this.calendarIndex = calendarIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (nodeScaleRatio != null)  {
            map.put("nodeScaleRatio", nodeScaleRatio);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (geoIndex != null)  {
            map.put("geoIndex", geoIndex);
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (edgeSymbol != null)  {
            map.put("edgeSymbol", edgeSymbol);
        }
        if (links != null)  {
            map.put("links", links);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (roam != null)  {
            map.put("roam", roam);
        }
        if (height != null)  {
            map.put("height", height);
        }
        if (focusNodeAdjacency != null)  {
            map.put("focusNodeAdjacency", focusNodeAdjacency);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (circular != null)  {
            map.put("circular", circular.toMap());
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (force != null)  {
            map.put("force", force.toMap());
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        if (cursor != null)  {
            map.put("cursor", cursor);
        }
        if (edgeSymbolSize != null)  {
            map.put("edgeSymbolSize", edgeSymbolSize);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (draggable != null)  {
            map.put("draggable", draggable);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (categories != null)  {
            map.put("categories", categories);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (edges != null)  {
            map.put("edges", edges);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (hoverAnimation != null)  {
            map.put("hoverAnimation", hoverAnimation);
        }
        if (layout != null)  {
            map.put("layout", layout);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (nodes != null)  {
            map.put("nodes", nodes);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (edgeLabel != null)  {
            map.put("edgeLabel", edgeLabel.toMap());
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (polarIndex != null)  {
            map.put("polarIndex", polarIndex);
        }
        if (calendarIndex != null)  {
            map.put("calendarIndex", calendarIndex);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
