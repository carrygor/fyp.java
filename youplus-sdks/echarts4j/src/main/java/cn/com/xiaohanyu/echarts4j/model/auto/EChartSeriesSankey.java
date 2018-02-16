package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong> 桑基图 </strong></p>
 * <p>是一种特殊的流图, 它主要用来表示原材料、能量等如何从初始形式经过中间过程的加工、转化到达最终形式。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=sankey-energy&edit=1&reset=1" width="700" height="580" ></iframe>
 * 
 * 
 * 
 * <p><br>
 * <strong>可视编码：</strong></p>
 * <p>桑基图将原数据中的每个<code>node</code>编码成一个小矩形，不同的节点尽量用不同的颜色展示，小矩形旁边的<code>label</code>编码的是节点的名称。</p>
 * <p>此外，图中每两个小矩形之间的边编码的是原数据中的<code>link</code>，边的粗细编码的是<code>link</code>中的<code>value</code>。</p>
 * <p><br>
 * <strong>排序：</strong>
 * 如果想指定结果的纵向顺序，那么可以把 <a href="#series-sankey.layoutIterations">layoutIterations</a> 设为 <code>0</code>，此时纵向的顺序依照数据在 <a href="#series-sankey.links">links</a> 中出现的顺序。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesSankey implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private EChartSeriesSankeyTooltip tooltip;

    private String type;

    private Number layoutIterations;

    private Number animationThreshold;

    private Object top;

    private LineStyle lineStyle;

    private Number zlevel;

    private List links;

    private Object height;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Object bottom;

    private List edges;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private Number nodeWidth;

    private Object right;

    private EChartSeriesSankeyLabel label;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private Number nodeGap;

    private List nodes;

    private Object left;

    private Object width;

    private Number z;

    private Object animationDurationUpdate;


    public List getData(){
        return data;
    }

    public EChartSeriesSankey setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesSankeyTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesSankey setTooltip(EChartSeriesSankeyTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesSankey setType(String type) {
        this.type = type;
        return this;
    }

    public Number getLayoutIterations(){
        return layoutIterations;
    }

    public EChartSeriesSankey setLayoutIterations(Number layoutIterations) {
        this.layoutIterations = layoutIterations;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesSankey setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesSankey setTop(Object top) {
        this.top = top;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesSankey setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesSankey setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public List getLinks(){
        return links;
    }

    public EChartSeriesSankey setLinks(List links) {
        this.links = links;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartSeriesSankey setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesSankey setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesSankey setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesSankey setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesSankey setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public List getEdges(){
        return edges;
    }

    public EChartSeriesSankey setEdges(List edges) {
        this.edges = edges;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesSankey setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesSankey setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Number getNodeWidth(){
        return nodeWidth;
    }

    public EChartSeriesSankey setNodeWidth(Number nodeWidth) {
        this.nodeWidth = nodeWidth;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesSankey setRight(Object right) {
        this.right = right;
        return this;
    }

    public EChartSeriesSankeyLabel getLabel(){
        return label;
    }

    public EChartSeriesSankey setLabel(EChartSeriesSankeyLabel label) {
        this.label = label;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesSankey setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesSankey setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesSankey setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getNodeGap(){
        return nodeGap;
    }

    public EChartSeriesSankey setNodeGap(Number nodeGap) {
        this.nodeGap = nodeGap;
        return this;
    }

    public List getNodes(){
        return nodes;
    }

    public EChartSeriesSankey setNodes(List nodes) {
        this.nodes = nodes;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesSankey setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartSeriesSankey setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesSankey setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesSankey setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
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
        if (type != null)  {
            map.put("type", type);
        }
        if (layoutIterations != null)  {
            map.put("layoutIterations", layoutIterations);
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (links != null)  {
            map.put("links", links);
        }
        if (height != null)  {
            map.put("height", height);
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
        if (edges != null)  {
            map.put("edges", edges);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (nodeWidth != null)  {
            map.put("nodeWidth", nodeWidth);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (nodeGap != null)  {
            map.put("nodeGap", nodeGap);
        }
        if (nodes != null)  {
            map.put("nodes", nodes);
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
