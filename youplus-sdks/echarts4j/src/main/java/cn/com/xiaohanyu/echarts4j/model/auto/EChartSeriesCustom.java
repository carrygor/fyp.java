package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>自定义系列</strong></p>
 * <p>自定义系列可以自定义系列中的图形元素渲染。从而能扩展出不同的图表。</p>
 * <p>同时，echarts 会统一管理图形的创建删除、动画、与其他组件（如 <a href="#dataZoom">dataZoom</a>、<a href="#visualMap">visualMap</a>）的联动，使开发者不必纠结这些细节。</p>
 * <p><strong>例如，下面的例子使用 custom series 扩展出了 x-range 图：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=custom-profile&reset=1&edit=1" width="800" height="500" ></iframe>
 * 
 * 
 * <p><strong>更多的例子参见：<a href="http://echarts.baidu.com/examples.html#chart-type-custom" target="_blank">custom examples</a></strong></p>
 * <p><strong><a href="http://echarts.baidu.com/tutorial.html#%E8%87%AA%E5%AE%9A%E4%B9%89%E7%B3%BB%E5%88%97" target="_blank">这里是个教程</a></strong></p>
 * <p><br>
 * <strong>开发者自定义渲染逻辑（renderItem 函数）</strong></p>
 * <p>custom 系列需要开发者自己提供图形渲染的逻辑。这个渲染逻辑一般命名为 <a href="#series-custom.renderItem">renderItem</a>。例如：</p>
 * <pre><code class="lang-js">var option = {
 *     ...,
 *     series: [{
 *         type: &#39;custom&#39;,
 *         renderItem: function (params, api) {
 *             var categoryIndex = api.value(0);
 *             var start = api.coord([api.value(1), categoryIndex]);
 *             var end = api.coord([api.value(2), categoryIndex]);
 *             var height = api.size([0, 1])[1] * 0.6;
 * 
 *             return {
 *                 type: &#39;rect&#39;,
 *                 shape: echarts.graphic.clipRectByRect({
 *                     x: start[0],
 *                     y: start[1] - height / 2,
 *                     width: end[0] - start[0],
 *                     height: height
 *                 }, {
 *                     x: params.coordSys.x,
 *                     y: params.coordSys.y,
 *                     width: params.coordSys.width,
 *                     height: params.coordSys.height
 *                 }),
 *                 style: api.style()
 *             };
 *         },
 *         data: data
 *     }]
 * }
 * </code></pre>
 * <p>对于 <code>data</code> 中的每个数据项（为方便描述，这里称为 <code>dataItem</code>)，会调用此 <a href="#series-custom.renderItem">renderItem</a> 函数。</p>
 * <p><a href="#series-custom.renderItem">renderItem</a> 函数提供了两个参数：</p>
 * <ul>
 * <li><a href="#series-custom.renderItem.arguments.params">params</a>：包含了当前数据信息和坐标系的信息。</li>
 * <li><a href="#series-custom.renderItem.arguments.api">api</a>：是一些开发者可调用的方法集合。</li>
 * </ul>
 * <p><a href="#series-custom.renderItem">renderItem</a> 函数须返回根据此 <code>dataItem</code> 绘制出的图形元素的定义信息，参见 <a href="#series-custom.renderItem.return">renderItem.return</a>。</p>
 * <p>一般来说，<a href="#series-custom.renderItem">renderItem</a> 函数的主要逻辑，是将 <code>dataItem</code> 里的值映射到坐标系上的图形元素。这一般需要用到 <a href="#series-custom.renderItem.arguments.api">renderItem.arguments.api</a> 中的两个函数：</p>
 * <ul>
 * <li><a href="#series-custom.renderItem.arguments.api.value">api.value(...)</a>，意思是取出 <code>dataItem</code> 中的数值。例如 <code>api.value(0)</code> 表示取出当前 <code>dataItem</code> 中第一个维度的数值。</li>
 * <li><a href="#series-custom.renderItem.arguments.api.coord">api.coord(...)</a>，意思是进行坐标转换计算。例如 <code>var point = api.coord([api.value(0), api.value(1)])</code> 表示 <code>dataItem</code> 中的数值转换成坐标系上的点。</li>
 * </ul>
 * <p>有时候还需要用到 <a href="#series-custom.renderItem.arguments.api.size">api.size(...)</a> 函数，表示得到坐标系上一段数值范围对应的长度。</p>
 * <p>返回值中样式的设置可以使用 <a href="#series-custom.renderItem.arguments.api.style">api.style(...)</a> 函数，他能得到 <a href="#series-custom.itemStyle.normal">series.itemStyle.normal</a> 中定义的样式信息，以及视觉映射的样式信息。也可以用这种方式覆盖这些样式信息：<code>api.style({fill: &#39;green&#39;, stroke: &#39;yellow&#39;})</code>。</p>
 * <p><br>
 * <strong>维度的映射（encode 和 dimensions 属性）</strong></p>
 * <p><code>custom</code> 系列往往需要定义 <a href="#series-custom.encode">series.encode</a>，主要用于指明 <code>data</code> 的哪些维度映射到哪些数轴上。从而，echarts 能根据这些维度的值的范围，画出合适的数轴刻度。
 * 同时，encode.tooltip 和 encode.label 也可以被指定，指明默认的 tooltip 和 label 显示什么内容。<a href="#series-custom.dimensions">series.dimensions</a> 也可以被指定，指明显示在 tooltip 中的维度名称，或者维度的类型。</p>
 * <p>例如：</p>
 * <pre><code class="lang-js">series: {
 *     type: &#39;custom&#39;,
 *     renderItem: function () {
 *         ...
 *     },
 *     encode: {
 *         x: [2, 4, 3],
 *         y: 1,
 *         label: 0,
 *         tooltip: [2, 4, 3]
 *     }
 * }
 * </code></pre>
 * <p><br>
 * <strong>与 dataZoom 组件的结合</strong></p>
 * <p>与 <a href="#dataZoom">dataZoom</a> 结合使用的时候，常常使用会设置 <a href="#dataZoom.filterMode">dataZoom.filterMode</a> 为 &#39;weakFilter&#39;，从而让 <code>dataItem</code> 部分超出坐标系边界的时候，不会整体被过滤掉。</p>
 * <p><br>
 * <strong>关于 <code>dataIndex</code> 和 <code>dataIndexInside</code> 的区别</strong></p>
 * <ul>
 * <li><code>dataIndex</code> 指的 <code>dataItem</code> 在原始数据中的 index。</li>
 * <li><code>dataIndexInside</code> 指的是 <code>dataItem</code> 在当前数据窗口（参见 <a href="#dataZoom">dataZoom</a>）中的 index。</li>
 * </ul>
 * <p><a href="#series-custom.renderItem.arguments.api">renderItem.arguments.api</a> 中使用的参数都是 <code>dataIndexInside</code> 而非 <code>dataIndex</code>，因为从 <code>dataIndex</code> 转换成 <code>dataIndexInside</code> 需要时间开销。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesCustom implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object encode;

    private List data;

    private EChartSeriesCustomTooltip tooltip;

    private String type;

    private Boolean legendHoverLink;

    private Number geoIndex;

    private Number animationThreshold;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean silent;

    private String animationEasing;

    private Object animationDelay;

    private Number xAxisIndex;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private Object renderItem;

    private Boolean animation;

    private Number animationDuration;

    private String animationEasingUpdate;

    private String name;

    private Number z;

    private Number polarIndex;

    private Number calendarIndex;

    private Object animationDurationUpdate;

    private Number yAxisIndex;

    private List dimensions;


    public Object getEncode(){
        return encode;
    }

    public EChartSeriesCustom setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesCustom setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesCustomTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesCustom setTooltip(EChartSeriesCustomTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesCustom setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesCustom setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesCustom setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesCustom setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesCustom setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesCustom setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesCustom setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesCustom setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesCustom setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesCustom setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesCustom setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesCustom setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRenderItem(){
        return renderItem;
    }

    public EChartSeriesCustom setRenderItem(Object renderItem) {
        this.renderItem = renderItem;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesCustom setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesCustom setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesCustom setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesCustom setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesCustom setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getPolarIndex(){
        return polarIndex;
    }

    public EChartSeriesCustom setPolarIndex(Number polarIndex) {
        this.polarIndex = polarIndex;
        return this;
    }

    public Number getCalendarIndex(){
        return calendarIndex;
    }

    public EChartSeriesCustom setCalendarIndex(Number calendarIndex) {
        this.calendarIndex = calendarIndex;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesCustom setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesCustom setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesCustom setDimensions(List dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (encode != null)  {
            map.put("encode", encode);
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
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
        }
        if (geoIndex != null)  {
            map.put("geoIndex", geoIndex);
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
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
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (renderItem != null)  {
            map.put("renderItem", renderItem);
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
        if (name != null)  {
            map.put("name", name);
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
