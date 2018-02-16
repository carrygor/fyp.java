package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong> 主题河流 </strong></p>
 * <p>是一种特殊的流图, 它主要用来表示事件或主题等在一段时间内的变化。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=themeRiver-lastfm&edit=1&reset=1" width="700" height="580" ></iframe>
 * 
 * 
 * 
 * <p><br>
 * <strong>可视编码：</strong></p>
 * <p>主题河流中不同颜色的条带状河流分支编码了不同的事件或主题，河流分支的宽度编码了原数据集中的value值。</p>
 * <p>此外，原数据集中的时间属性，映射到单个时间轴上。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesThemeRiver implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private Object bottom;

    private EChartSeriesThemeRiverTooltip tooltip;

    private ItemStyle itemStyle;

    private Object right;

    private EChartSeriesThemeRiverLabel label;

    private String type;

    private List boundaryGap;

    private Object top;

    private Object left;

    private Object width;

    private Number zlevel;

    private Number z;

    private String coordinateSystem;

    private Number singleAxisIndex;

    private Object height;


    public List getData(){
        return data;
    }

    public EChartSeriesThemeRiver setData(List data) {
        this.data = data;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesThemeRiver setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public EChartSeriesThemeRiverTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesThemeRiver setTooltip(EChartSeriesThemeRiverTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesThemeRiver setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesThemeRiver setRight(Object right) {
        this.right = right;
        return this;
    }

    public EChartSeriesThemeRiverLabel getLabel(){
        return label;
    }

    public EChartSeriesThemeRiver setLabel(EChartSeriesThemeRiverLabel label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesThemeRiver setType(String type) {
        this.type = type;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartSeriesThemeRiver setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesThemeRiver setTop(Object top) {
        this.top = top;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesThemeRiver setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartSeriesThemeRiver setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesThemeRiver setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesThemeRiver setZ(Number z) {
        this.z = z;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesThemeRiver setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Number getSingleAxisIndex(){
        return singleAxisIndex;
    }

    public EChartSeriesThemeRiver setSingleAxisIndex(Number singleAxisIndex) {
        this.singleAxisIndex = singleAxisIndex;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartSeriesThemeRiver setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
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
        if (boundaryGap != null)  {
            map.put("boundaryGap", boundaryGap);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (left != null)  {
            map.put("left", left);
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
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (singleAxisIndex != null)  {
            map.put("singleAxisIndex", singleAxisIndex);
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
