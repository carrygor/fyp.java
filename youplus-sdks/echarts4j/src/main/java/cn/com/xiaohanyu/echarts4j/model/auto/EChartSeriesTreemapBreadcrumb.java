package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>面包屑，能够显示当前节点的路径。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesTreemapBreadcrumb implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number emptyItemWidth;

    private Object top;

    private Object left;

    private Object bottom;

    private Boolean show;

    private ItemStyle itemStyle;

    private Object right;

    private Number height;


    public Number getEmptyItemWidth(){
        return emptyItemWidth;
    }

    public EChartSeriesTreemapBreadcrumb setEmptyItemWidth(Number emptyItemWidth) {
        this.emptyItemWidth = emptyItemWidth;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesTreemapBreadcrumb setTop(Object top) {
        this.top = top;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesTreemapBreadcrumb setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesTreemapBreadcrumb setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartSeriesTreemapBreadcrumb setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesTreemapBreadcrumb setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesTreemapBreadcrumb setRight(Object right) {
        this.right = right;
        return this;
    }

    public Number getHeight(){
        return height;
    }

    public EChartSeriesTreemapBreadcrumb setHeight(Number height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (emptyItemWidth != null)  {
            map.put("emptyItemWidth", emptyItemWidth);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (right != null)  {
            map.put("right", right);
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
