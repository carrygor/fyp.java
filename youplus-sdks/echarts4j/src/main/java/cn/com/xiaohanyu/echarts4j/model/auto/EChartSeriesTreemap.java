package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><a href="https://en.wikipedia.org/wiki/Treemapping" target="_blank">Treemap</a> 是一种常见的表达『层级数据』『树状数据』的可视化形式。它主要用面积的方式，便于突出展现出『树』的各层级中重要的节点。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=treemap-obama&edit=1&reset=1" width="700" height="580" ></iframe>
 * 
 * 
 * 
 * <p><br>
 * <strong>视觉映射：</strong></p>
 * <p>treemap 首先是把数值映射到『面积』这种视觉元素上。</p>
 * <p>此外，也支持对数据的其他维度进行视觉映射，例如映射到颜色、颜色明暗度上。</p>
 * <p>关于视觉设置，详见 <a href="#series-treemap.levels">series-treemap.levels</a>。</p>
 * <p><br>
 * <strong>下钻（drill down）：</strong></p>
 * <p><code>drill down</code> 功能即点击后才展示子层级。
 * 设置了 <a href="#series-treemap.leafDepth">leafDepth</a> 后，下钻（<code>drill down</code>）功能开启。</p>
 * <p><strong>如下是 drill down 的例子：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=treemap-drill-down&edit=1&reset=1" width="800" height="500" ></iframe>
 * 
 * 
 * 
 * <p><br>
 * <br>
 * <br>
 * 注：treemap 的配置项 和 ECharts2 相比有一些变化，一些不太成熟的配置方式不再支持或不再兼容：</p>
 * <ul>
 * <li><p><code>center/size</code> 方式的定位不再支持，而是统一使用 <code>left/top/bottom/right/width/height</code> 方式定位。</p>
 * </li>
 * <li><p><code>breadcrumb</code> 的配置被移动到了 <code>itemStyle.normal/itemStyle.emphasis</code> 外部，和 <code>itemStyle</code> 平级。</p>
 * </li>
 * <li><p><code>root</code> 的设置暂时不支持。目前可以使用 <code>zoom</code> 的方式来查看树更下层次的细节，或者使用 <a href="#series-treemap.leafDepth">leafDepth</a> 开启 &quot;drill down&quot; 功能。</p>
 * </li>
 * <li><p><code>label</code> 的配置被移动到了 <code>itemStyle.normal/itemStyle.emphasis</code> 外部，和 <code>itemStyle</code> 平级。</p>
 * </li>
 * <li><p><code>itemStyle.normal.childBorderWidth</code>、<code>itemStyle.normal.childBorderColor</code>不再支持（因为这个配置方式只能定义两层的treemap）。统一使用 <a href="#series-treemap.levels">series-treemap.levels</a> 来进行各层级的定义。</p>
 * </li>
 * </ul>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesTreemap implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private Number visualMax;

    private Number childrenVisibleMin;

    private EChartSeriesTreemapTooltip tooltip;

    private String type;

    private EChartSeriesTreemapUpperLabel upperLabel;

    private List colorAlpha;

    private Object nodeClick;

    private Object top;

    private Number zlevel;

    private Object roam;

    private Number zoomToNodeRatio;

    private Number visibleMin;

    private Object height;

    private String drillDownIcon;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Object bottom;

    private ItemStyle itemStyle;

    private Object right;

    private Number leafDepth;

    private Number colorSaturation;

    private EChartSeriesTreemapLabel label;

    private Number animationDuration;

    private Number visualMin;

    private EChartSeriesTreemapBreadcrumb breadcrumb;

    private Object left;

    private Object width;

    private Number z;

    private String colorMappingBy;

    private List levels;

    private Number visualDimension;

    private Number squareRatio;


    public List getData(){
        return data;
    }

    public EChartSeriesTreemap setData(List data) {
        this.data = data;
        return this;
    }

    public Number getVisualMax(){
        return visualMax;
    }

    public EChartSeriesTreemap setVisualMax(Number visualMax) {
        this.visualMax = visualMax;
        return this;
    }

    public Number getChildrenVisibleMin(){
        return childrenVisibleMin;
    }

    public EChartSeriesTreemap setChildrenVisibleMin(Number childrenVisibleMin) {
        this.childrenVisibleMin = childrenVisibleMin;
        return this;
    }

    public EChartSeriesTreemapTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesTreemap setTooltip(EChartSeriesTreemapTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesTreemap setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesTreemapUpperLabel getUpperLabel(){
        return upperLabel;
    }

    public EChartSeriesTreemap setUpperLabel(EChartSeriesTreemapUpperLabel upperLabel) {
        this.upperLabel = upperLabel;
        return this;
    }

    public List getColorAlpha(){
        return colorAlpha;
    }

    public EChartSeriesTreemap setColorAlpha(List colorAlpha) {
        this.colorAlpha = colorAlpha;
        return this;
    }

    public Object getNodeClick(){
        return nodeClick;
    }

    public EChartSeriesTreemap setNodeClick(Object nodeClick) {
        this.nodeClick = nodeClick;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesTreemap setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesTreemap setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Object getRoam(){
        return roam;
    }

    public EChartSeriesTreemap setRoam(Object roam) {
        this.roam = roam;
        return this;
    }

    public Number getZoomToNodeRatio(){
        return zoomToNodeRatio;
    }

    public EChartSeriesTreemap setZoomToNodeRatio(Number zoomToNodeRatio) {
        this.zoomToNodeRatio = zoomToNodeRatio;
        return this;
    }

    public Number getVisibleMin(){
        return visibleMin;
    }

    public EChartSeriesTreemap setVisibleMin(Number visibleMin) {
        this.visibleMin = visibleMin;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartSeriesTreemap setHeight(Object height) {
        this.height = height;
        return this;
    }

    public String getDrillDownIcon(){
        return drillDownIcon;
    }

    public EChartSeriesTreemap setDrillDownIcon(String drillDownIcon) {
        this.drillDownIcon = drillDownIcon;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesTreemap setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesTreemap setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesTreemap setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesTreemap setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesTreemap setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesTreemap setRight(Object right) {
        this.right = right;
        return this;
    }

    public Number getLeafDepth(){
        return leafDepth;
    }

    public EChartSeriesTreemap setLeafDepth(Number leafDepth) {
        this.leafDepth = leafDepth;
        return this;
    }

    public Number getColorSaturation(){
        return colorSaturation;
    }

    public EChartSeriesTreemap setColorSaturation(Number colorSaturation) {
        this.colorSaturation = colorSaturation;
        return this;
    }

    public EChartSeriesTreemapLabel getLabel(){
        return label;
    }

    public EChartSeriesTreemap setLabel(EChartSeriesTreemapLabel label) {
        this.label = label;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesTreemap setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getVisualMin(){
        return visualMin;
    }

    public EChartSeriesTreemap setVisualMin(Number visualMin) {
        this.visualMin = visualMin;
        return this;
    }

    public EChartSeriesTreemapBreadcrumb getBreadcrumb(){
        return breadcrumb;
    }

    public EChartSeriesTreemap setBreadcrumb(EChartSeriesTreemapBreadcrumb breadcrumb) {
        this.breadcrumb = breadcrumb;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesTreemap setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartSeriesTreemap setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesTreemap setZ(Number z) {
        this.z = z;
        return this;
    }

    public String getColorMappingBy(){
        return colorMappingBy;
    }

    public EChartSeriesTreemap setColorMappingBy(String colorMappingBy) {
        this.colorMappingBy = colorMappingBy;
        return this;
    }

    public List getLevels(){
        return levels;
    }

    public EChartSeriesTreemap setLevels(List levels) {
        this.levels = levels;
        return this;
    }

    public Number getVisualDimension(){
        return visualDimension;
    }

    public EChartSeriesTreemap setVisualDimension(Number visualDimension) {
        this.visualDimension = visualDimension;
        return this;
    }

    public Number getSquareRatio(){
        return squareRatio;
    }

    public EChartSeriesTreemap setSquareRatio(Number squareRatio) {
        this.squareRatio = squareRatio;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (visualMax != null)  {
            map.put("visualMax", visualMax);
        }
        if (childrenVisibleMin != null)  {
            map.put("childrenVisibleMin", childrenVisibleMin);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (upperLabel != null)  {
            map.put("upperLabel", upperLabel.toMap());
        }
        if (colorAlpha != null)  {
            map.put("colorAlpha", colorAlpha);
        }
        if (nodeClick != null)  {
            map.put("nodeClick", nodeClick);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (roam != null)  {
            map.put("roam", roam);
        }
        if (zoomToNodeRatio != null)  {
            map.put("zoomToNodeRatio", zoomToNodeRatio);
        }
        if (visibleMin != null)  {
            map.put("visibleMin", visibleMin);
        }
        if (height != null)  {
            map.put("height", height);
        }
        if (drillDownIcon != null)  {
            map.put("drillDownIcon", drillDownIcon);
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
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (leafDepth != null)  {
            map.put("leafDepth", leafDepth);
        }
        if (colorSaturation != null)  {
            map.put("colorSaturation", colorSaturation);
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (visualMin != null)  {
            map.put("visualMin", visualMin);
        }
        if (breadcrumb != null)  {
            map.put("breadcrumb", breadcrumb.toMap());
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (colorMappingBy != null)  {
            map.put("colorMappingBy", colorMappingBy);
        }
        if (levels != null)  {
            map.put("levels", levels);
        }
        if (visualDimension != null)  {
            map.put("visualDimension", visualDimension);
        }
        if (squareRatio != null)  {
            map.put("squareRatio", squareRatio);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
