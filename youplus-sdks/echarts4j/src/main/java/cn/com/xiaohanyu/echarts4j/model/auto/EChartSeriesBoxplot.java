package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><a href="https://en.wikipedia.org/wiki/Box_plot" target="_blank">Boxplot</a> 中文可以称为『箱形图』、『盒须图』、『盒式图』、『盒状图』、『箱线图』，是一种用作显示一组数据分散情况资料的统计图。它能显示出一组数据的最大值、最小值、中位数、下四分位数及上四分位数。</p>
 * <p><strong>示例如下：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=boxplot-light-velocity&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * <p><br>
 * 也支持多个 <code>series</code> 在同一个坐标系中，参见 <a href="http://echarts.baidu.com/gallery/editor.html?c=boxplot-multi&amp;edit=1&amp;reset=1" target="_blank">例子</a>。</p>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesBoxplot implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object encode;

    private Boolean silent;

    private String animationEasing;

    private List data;

    private Object animationDelay;

    private Number xAxisIndex;

    private EChartSeriesBoxplotTooltip tooltip;

    private ItemStyle itemStyle;

    private String type;

    private EChartSeriesBoxplotMarkLine markLine;

    private EChartSeriesBoxplotMarkPoint markPoint;

    private Boolean legendHoverLink;

    private Boolean hoverAnimation;

    private String layout;

    private Number animationDuration;

    private EChartSeriesBoxplotMarkArea markArea;

    private String name;

    private Number zlevel;

    private Number z;

    private String coordinateSystem;

    private List boxWidth;

    private Number yAxisIndex;

    private List dimensions;


    public Object getEncode(){
        return encode;
    }

    public EChartSeriesBoxplot setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesBoxplot setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesBoxplot setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesBoxplot setData(List data) {
        this.data = data;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesBoxplot setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesBoxplot setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public EChartSeriesBoxplotTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesBoxplot setTooltip(EChartSeriesBoxplotTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesBoxplot setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesBoxplot setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesBoxplotMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesBoxplot setMarkLine(EChartSeriesBoxplotMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public EChartSeriesBoxplotMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesBoxplot setMarkPoint(EChartSeriesBoxplotMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesBoxplot setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesBoxplot setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public String getLayout(){
        return layout;
    }

    public EChartSeriesBoxplot setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesBoxplot setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public EChartSeriesBoxplotMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesBoxplot setMarkArea(EChartSeriesBoxplotMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesBoxplot setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesBoxplot setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesBoxplot setZ(Number z) {
        this.z = z;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesBoxplot setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public List getBoxWidth(){
        return boxWidth;
    }

    public EChartSeriesBoxplot setBoxWidth(List boxWidth) {
        this.boxWidth = boxWidth;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesBoxplot setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesBoxplot setDimensions(List dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (encode != null)  {
            map.put("encode", encode);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
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
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (name != null)  {
            map.put("name", name);
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
        if (boxWidth != null)  {
            map.put("boxWidth", boxWidth);
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
