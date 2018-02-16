package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><code>brush</code> 是区域选择组件，用户可以选择图中一部分数据，从而便于向用户展示被选中数据，或者他们的一些统计计算结果。</p>
 * <p>示例如下：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-map-brush&edit=1&reset=1" width="800" height="600" ></iframe>
 * 
 * 
 * 
 * <p><br></p>
 * <hr>
 * <p><strong>刷子的类型和启动按钮</strong></p>
 * <p>目前 <code>brush</code> 组件支持的图表类型：<code>scatter</code>、<code>bar</code>、<code>candlestick</code>（<code>parallel</code> 本身自带刷选功能，但并非由 brush 组件来提供）。</p>
 * <p>点击 <code>toolbox</code> 中的按钮，能够进行『区域选择』、『清除选择』等操作。</p>
 * <p><br>
 * <code>横向刷子</code> 的示例如下（点击 <code>toolbox</code> 中的按钮启动刷选）：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=candlestick-brush&edit=1&reset=1" width="800" height="500" ></iframe>
 * 
 * 
 * <p><br>
 * <code>bar</code> 图中的 <code>brush</code>（点击 <code>toolbox</code> 中的按钮启动刷选）：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=bar-brush&edit=1&reset=1" width="800" height="400" ></iframe>
 * 
 * 
 * 
 * <p>启动 <code>brush</code> 的按钮既可以在 <code>toolbox</code> 中指定（参见 <a href="#toolbox.feature.brush.type">toolbox.feature.brush.type</a>），也可以在 <code>brush</code> 组件的配置中指定（参见 <a href="#brush.toolbox">brush.toolbox</a>）。</p>
 * <p>支持这几种选框：<code>矩形刷子</code>，<code>任意形状刷子</code>，<code>横向刷子</code>，<code>纵向刷子</code>。参见 <a href="#brush.toolbox">brush.toolbox</a>。</p>
 * <p>可以使用 <code>保持选择</code> 按钮，切换单选和多选模式。</p>
 * <ul>
 * <li>单选即同时只能存在一个选框，可单击空白区域消除选框。</li>
 * <li>多选即同时可存在多个选框，单击空白区域不能消除选框，需要点击『清除按钮』消除线框。</li>
 * </ul>
 * <p><br></p>
 * <hr>
 * <p><strong>刷选和坐标系的关系</strong></p>
 * <p>可以设置 <code>brush</code> 是『全局的』还是『属于坐标系的』。</p>
 * <p><strong>全局 brush</strong></p>
 * <p>在 echarts 实例中任意地方刷选。这是默认情况。如果没有指定为『坐标系 brush』，就是『全局 brush』。</p>
 * <p><strong>坐标系 brush</strong></p>
 * <p>在 指定的坐标系中刷选。选框可以跟随坐标系的缩放和平移（roam 和 dataZoom）而移动。</p>
 * <p>坐标系 brush 实际更为常用，尤其是在 geo 中。</p>
 * <p>通过指定 <a href="#brush.geoIndex">brush.geoIndex</a> 或 <a href="#brush.xAxisIndex">brush.xAxisIndex</a> 或 <a href="#brush.yAxisIndex">brush.yAxisIndex</a> 来规定可以在哪些坐标系中进行刷选。</p>
 * <p>这几个配置项的取值可以是：</p>
 * <ul>
 * <li><code>&#39;all&#39;</code>，表示所有</li>
 * <li><code>number</code>，如 <code>0</code>，表示这个 index 所对应的坐标系。</li>
 * <li><code>Array</code>，如 <code>[0, 4, 2]</code>，表示指定这些 index 所对应的坐标系。</li>
 * <li><code>&#39;none&#39;</code> 或 <code>null</code> 或 <code>undefined</code>，表示不指定。</li>
 * </ul>
 * <p>例如：</p>
 * <pre><code class="lang-javascript">option = {
 *     geo: {
 *         ...
 *     },
 *     brush: {
 *         geoIndex: &#39;all&#39;, // 只可以在所有 geo 坐标系中刷选，也就是上面定义的 geo 组件中。
 *         ...
 *     }
 * };
 * </code></pre>
 * <p>例如：</p>
 * <pre><code class="lang-javascript">option = {
 *     grid: [
 *         {...}, // grid 0
 *         {...}  // grid 1
 *     ],
 *     xAxis: [
 *         {gridIndex: 1, ...}, // xAxis 0，属于 grid 1。
 *         {gridIndex: 0, ...}  // xAxis 1，属于 grid 0。
 *     ],
 *     yAxis: [
 *         {gridIndex: 1, ...}, // yAxis 0，属于 grid 1。
 *         {gridIndex: 0, ...}  // yAxis 1，属于 grid 0。
 *     ],
 *     brush: {
 *         xAxisIndex: [0, 1], // 只可以在 xAxisIndex 为 `0` 和 `1` 的 xAxis 所在的直角坐标系中刷选。
 *         ...
 *     }
 * };
 * </code></pre>
 * <p><br></p>
 * <hr>
 * <p><strong> 使用 API 控制选框 </strong></p>
 * <p>可以通过调用 <code>dispatchAction</code> 来用程序主动渲染选框，例如：</p>
 * <pre><code class="lang-javascript">myChart.dispatchAction({
 *     type: &#39;brush&#39;,
 *     areas: [
 *         {
 *             geoIndex: 0,
 *             // 指定选框的类型。
 *             brushType: &#39;polygon&#39;,
 *             // 指定选框的形状。
 *             coordRange: [[119.72,34.85],[119.68,34.85],[119.5,34.84],[119.19,34.77]]
 *         }
 *     ]
 * });
 * </code></pre>
 * <p>详情参见 <a href="api.html#action.brush" target="_blank">action.brush</a></p>
 * <p><br></p>
 * <hr>
 * <p><strong> brushLink </strong></p>
 * <p>不同系列间，选中的项可以联动。</p>
 * <p>参见如下效果（刷选一个 <code>scatter</code>，其他 <code>scatter</code> 以及 <code>parallel</code> 图都会有选中效果）：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-matrix&edit=1&reset=1" width="800" height="550" ></iframe>
 * 
 * 
 * <p><code>brushLink</code> 配置项是一个数组，内容是 seriesIndex，指定了哪些 series 可以被联动。例如可以是：</p>
 * <ul>
 * <li><code>[3, 4, 5]</code> 表示 seriesIndex 为 <code>3</code>, <code>4</code>, <code>5</code> 的 series 可以被联动。</li>
 * <li><code>&#39;all&#39;</code> 表示所有 series 都进行 brushLink。</li>
 * <li><code>&#39;none&#39;</code> 或 <code>null</code> 或 <code>undefined</code> 表示不启用 brushLink 功能。</li>
 * </ul>
 * <p><strong>注意</strong></p>
 * <p>brushLink 是通过 dataIndex 进行映射，所以需要保证，<strong>联动的每个系列的 <code>data</code> 都是 <code>index</code> 对应的</strong>。*</p>
 * <p>例如：</p>
 * <pre><code class="lang-javascript">option = {
 *     brush: {
 *         brushLink: [0, 1]
 *     },
 *     series: [
 *         {
 *             type: &#39;bar&#39;
 *             data: [232,    4434,    545,      654]     // data 有四个项
 *         },
 *         {
 *             type: &#39;parallel&#39;,
 *             data: [[4, 5], [3, 5], [66, 33], [99, 66]] // data 同样有四个项，两个系列的 data 是对应的。
 *         }
 *     ]
 * };
 * </code></pre>
 * <p>参见 <a href="#brush.brushLink">brush.brushLink</a>。</p>
 * <p><br></p>
 * <hr>
 * <p><strong> throttle / debounce / 事件延迟 </strong></p>
 * <p>默认情况，刷选或者移动选区的时候，会不断得发 <code>brushSelected</code> 事件，从而告诉外界选中的内容。</p>
 * <p>但是频繁的事件可能导致性能问题，或者动画效果很差。所以 brush 组件提供了 <a href="#brush.throttleType">brush.throttleType</a>，<a href="#brush.throttleDelay">brush.throttleDelay</a> 来解决这个问题。</p>
 * <p>throttleType 取值可以是：</p>
 * <ul>
 * <li><code>&#39;debounce&#39;</code>：表示只有停止动作了（即一段时间没有操作了），才会触发事件。时间阈值由 <a href="#brush.throttleDelay">brush.throttleDelay</a> 指定。</li>
 * <li><code>&#39;fixRate&#39;</code>：表示按照一定的频率触发时间，时间间隔由 <a href="#brush.throttleDelay">brush.throttleDelay</a> 指定。</li>
 * </ul>
 * <p>例如这个 <a href="http://echarts.baidu.com/gallery/view.html?c=scatter-map-brush&amp;edit=1&amp;reset=1" target="_blank">例子</a>，就是使用了 <code>debounce</code>的效果：只有用户停止动作了，柱状图才更新。不然的话，如果柱状图的频繁更新，那么动画效果很差。</p>
 * <p><br></p>
 * <hr>
 * <p><strong> 被选中项和未被选中项的视觉设置 </strong></p>
 * <p>参见 <a href="#brush.inBrush">brush.inBrush</a> 和 <a href="#brush.outOfBrush">brush.outOfBrush</a>。</p>
 * <p><br></p>
 * <hr>
 * <p>下面是详细配置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartBrush implements Serializable {
    private static final long serialVersionUID = 1L;

    private String throttleType;

    private String brushType;

    private Object xAxisIndex;

    private Number removeOnClick;

    private Object outOfBrush;

    private Object seriesIndex;

    private Object inBrush;

    private String brushMode;

    private Object geoIndex;

    private Number throttleDelay;

    private Boolean transformable;

    private List toolbox;

    private Object brushLink;

    private Object brushStyle;

    private Number z;

    private Object yAxisIndex;


    public String getThrottleType(){
        return throttleType;
    }

    public EChartBrush setThrottleType(String throttleType) {
        this.throttleType = throttleType;
        return this;
    }

    public String getBrushType(){
        return brushType;
    }

    public EChartBrush setBrushType(String brushType) {
        this.brushType = brushType;
        return this;
    }

    public Object getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartBrush setXAxisIndex(Object xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public Number getRemoveOnClick(){
        return removeOnClick;
    }

    public EChartBrush setRemoveOnClick(Number removeOnClick) {
        this.removeOnClick = removeOnClick;
        return this;
    }

    public Object getOutOfBrush(){
        return outOfBrush;
    }

    public EChartBrush setOutOfBrush(Object outOfBrush) {
        this.outOfBrush = outOfBrush;
        return this;
    }

    public Object getSeriesIndex(){
        return seriesIndex;
    }

    public EChartBrush setSeriesIndex(Object seriesIndex) {
        this.seriesIndex = seriesIndex;
        return this;
    }

    public Object getInBrush(){
        return inBrush;
    }

    public EChartBrush setInBrush(Object inBrush) {
        this.inBrush = inBrush;
        return this;
    }

    public String getBrushMode(){
        return brushMode;
    }

    public EChartBrush setBrushMode(String brushMode) {
        this.brushMode = brushMode;
        return this;
    }

    public Object getGeoIndex(){
        return geoIndex;
    }

    public EChartBrush setGeoIndex(Object geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public Number getThrottleDelay(){
        return throttleDelay;
    }

    public EChartBrush setThrottleDelay(Number throttleDelay) {
        this.throttleDelay = throttleDelay;
        return this;
    }

    public Boolean getTransformable(){
        return transformable;
    }

    public EChartBrush setTransformable(Boolean transformable) {
        this.transformable = transformable;
        return this;
    }

    public List getToolbox(){
        return toolbox;
    }

    public EChartBrush setToolbox(List toolbox) {
        this.toolbox = toolbox;
        return this;
    }

    public Object getBrushLink(){
        return brushLink;
    }

    public EChartBrush setBrushLink(Object brushLink) {
        this.brushLink = brushLink;
        return this;
    }

    public Object getBrushStyle(){
        return brushStyle;
    }

    public EChartBrush setBrushStyle(Object brushStyle) {
        this.brushStyle = brushStyle;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartBrush setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartBrush setYAxisIndex(Object yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (throttleType != null)  {
            map.put("throttleType", throttleType);
        }
        if (brushType != null)  {
            map.put("brushType", brushType);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (removeOnClick != null)  {
            map.put("removeOnClick", removeOnClick);
        }
        if (outOfBrush != null)  {
            map.put("outOfBrush", outOfBrush);
        }
        if (seriesIndex != null)  {
            map.put("seriesIndex", seriesIndex);
        }
        if (inBrush != null)  {
            map.put("inBrush", inBrush);
        }
        if (brushMode != null)  {
            map.put("brushMode", brushMode);
        }
        if (geoIndex != null)  {
            map.put("geoIndex", geoIndex);
        }
        if (throttleDelay != null)  {
            map.put("throttleDelay", throttleDelay);
        }
        if (transformable != null)  {
            map.put("transformable", transformable);
        }
        if (toolbox != null)  {
            map.put("toolbox", toolbox);
        }
        if (brushLink != null)  {
            map.put("brushLink", brushLink);
        }
        if (brushStyle != null)  {
            map.put("brushStyle", brushStyle);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
