package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>工具栏。内置有<a href="#toolbox.feature.saveAsImage">导出图片</a>，<a href="#toolbox.feature.dataView">数据视图</a>，<a href="#toolbox.feature.magicType">动态类型切换</a>，<a href="#toolbox.feature.dataZoom">数据区域缩放</a>，<a href="#toolbox.feature.reset">重置</a>五个工具。</p>
 * <p><strong>如下示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=line-marker&reset=1&edit=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolbox implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number itemGap;

    private String orient;

    private Object bottom;

    private Boolean show;

    private Object right;

    private IconStyle iconStyle;

    private EChartToolboxFeature feature;

    private Object top;

    private Object left;

    private Boolean showTitle;

    private Object width;

    private Number zlevel;

    private Number z;

    private Number itemSize;

    private Object height;


    public Number getItemGap(){
        return itemGap;
    }

    public EChartToolbox setItemGap(Number itemGap) {
        this.itemGap = itemGap;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartToolbox setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartToolbox setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartToolbox setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartToolbox setRight(Object right) {
        this.right = right;
        return this;
    }

    public IconStyle getIconStyle(){
        return iconStyle;
    }

    public EChartToolbox setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    public EChartToolboxFeature getFeature(){
        return feature;
    }

    public EChartToolbox setFeature(EChartToolboxFeature feature) {
        this.feature = feature;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartToolbox setTop(Object top) {
        this.top = top;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartToolbox setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Boolean getShowTitle(){
        return showTitle;
    }

    public EChartToolbox setShowTitle(Boolean showTitle) {
        this.showTitle = showTitle;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartToolbox setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartToolbox setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartToolbox setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getItemSize(){
        return itemSize;
    }

    public EChartToolbox setItemSize(Number itemSize) {
        this.itemSize = itemSize;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartToolbox setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (itemGap != null)  {
            map.put("itemGap", itemGap);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (iconStyle != null)  {
            map.put("iconStyle", iconStyle.toMap());
        }
        if (feature != null)  {
            map.put("feature", feature.toMap());
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (showTitle != null)  {
            map.put("showTitle", showTitle);
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
        if (itemSize != null)  {
            map.put("itemSize", itemSize);
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
