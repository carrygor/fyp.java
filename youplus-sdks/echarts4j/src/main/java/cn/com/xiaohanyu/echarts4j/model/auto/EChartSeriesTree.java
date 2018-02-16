package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>树图</strong></p>
 * <p>树图主要用来可视化树形数据结构，是一种特殊的层次类型，具有唯一的根节点，左子树，和右子树。</p>
 * <p><strong>注意：目前不支持在单个 series 中直接绘制森林，可以通过在一个 option 中配置多个 series 实现森林</strong></p>
 * <p><strong>树图示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=tree-vertical&edit=1&reset=1" width="900" height="780" ></iframe>
 * 
 * 
 * <p><strong>多个 series 组合成森林示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=tree-legend&edit=1&reset=1" width="800" height="680" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesTree implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orient;

    private EChartSeriesTreeData data;

    private Object bottom;

    private Number initialTreeDepth;

    private EChartSeriesTreeTooltip tooltip;

    private ItemStyle itemStyle;

    private Object right;

    private EChartSeriesTreeLabel label;

    private String type;

    private Boolean expandAndCollapse;

    private String layout;

    private Object top;

    private LineStyle lineStyle;

    private EChartSeriesTreeLeaves leaves;

    private Object left;

    private String name;

    private Object width;

    private Number zlevel;

    private Number z;

    private Object height;


    public String getOrient(){
        return orient;
    }

    public EChartSeriesTree setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public EChartSeriesTreeData getData(){
        return data;
    }

    public EChartSeriesTree setData(EChartSeriesTreeData data) {
        this.data = data;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesTree setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Number getInitialTreeDepth(){
        return initialTreeDepth;
    }

    public EChartSeriesTree setInitialTreeDepth(Number initialTreeDepth) {
        this.initialTreeDepth = initialTreeDepth;
        return this;
    }

    public EChartSeriesTreeTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesTree setTooltip(EChartSeriesTreeTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesTree setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesTree setRight(Object right) {
        this.right = right;
        return this;
    }

    public EChartSeriesTreeLabel getLabel(){
        return label;
    }

    public EChartSeriesTree setLabel(EChartSeriesTreeLabel label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesTree setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getExpandAndCollapse(){
        return expandAndCollapse;
    }

    public EChartSeriesTree setExpandAndCollapse(Boolean expandAndCollapse) {
        this.expandAndCollapse = expandAndCollapse;
        return this;
    }

    public String getLayout(){
        return layout;
    }

    public EChartSeriesTree setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesTree setTop(Object top) {
        this.top = top;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesTree setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public EChartSeriesTreeLeaves getLeaves(){
        return leaves;
    }

    public EChartSeriesTree setLeaves(EChartSeriesTreeLeaves leaves) {
        this.leaves = leaves;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesTree setLeft(Object left) {
        this.left = left;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesTree setName(String name) {
        this.name = name;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartSeriesTree setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesTree setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesTree setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartSeriesTree setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (data != null)  {
            map.put("data", data.toMap());
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (initialTreeDepth != null)  {
            map.put("initialTreeDepth", initialTreeDepth);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (expandAndCollapse != null)  {
            map.put("expandAndCollapse", expandAndCollapse);
        }
        if (layout != null)  {
            map.put("layout", layout);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (leaves != null)  {
            map.put("leaves", leaves.toMap());
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (height != null)  {
            map.put("height", height);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
