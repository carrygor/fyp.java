package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>直角坐标系内绘图网格，单个 grid 内最多可以放置上下两个 X 轴，左右两个 Y 轴。可以在网格上绘制<a href="#series-line">折线图</a>，<a href="#series-bar">柱状图</a>，<a href="#series-scatter">散点图（气泡图）</a>。</p>
 * <p>在 ECharts 2.x 里单个 echarts 实例中最多只能存在一个 grid 组件，在 ECharts 3 中可以存在任意个 grid 组件。</p>
 * <p><strong>例如下面这个 Anscombe Quartet 的示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-anscombe-quartet&edit=1&reset=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartGrid implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Color backgroundColor;

    private Color borderColor;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private Object bottom;

    private Boolean show;

    private EChartGridTooltip tooltip;

    private Object right;

    private Object top;

    private Object left;

    private Number borderWidth;

    private Object width;

    private Number zlevel;

    private Number z;

    private Color shadowColor;

    private Object height;

    private Boolean containLabel;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartGrid setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartGrid setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartGrid setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartGrid setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartGrid setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartGrid setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartGrid setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public EChartGridTooltip getTooltip(){
        return tooltip;
    }

    public EChartGrid setTooltip(EChartGridTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartGrid setRight(Object right) {
        this.right = right;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartGrid setTop(Object top) {
        this.top = top;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartGrid setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartGrid setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartGrid setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartGrid setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartGrid setZ(Number z) {
        this.z = z;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public EChartGrid setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartGrid setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Boolean getContainLabel(){
        return containLabel;
    }

    public EChartGrid setContainLabel(Boolean containLabel) {
        this.containLabel = containLabel;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
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
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        if (height != null)  {
            map.put("height", height);
        }
        if (containLabel != null)  {
            map.put("containLabel", containLabel);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
