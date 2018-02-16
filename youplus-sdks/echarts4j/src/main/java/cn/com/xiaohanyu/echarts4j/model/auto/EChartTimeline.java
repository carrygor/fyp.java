package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><code>timeline</code> 组件，提供了在多个 ECharts <code>option</code> 间进行切换、播放等操作的功能。</p>
 * <p>示例效果如下：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/mix-timeline-all&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * <p><code>timeline</code> 和其他组件有些不同，它需要操作『多个option』。
 * 假设，我们把 ECharts 的传统的 option 称为<em>原子option</em>，那么使用 <code>timeline</code> 时，传入 ECharts 的 option 就成为了一个集合多个原子option的<em>复合option</em>。如下示例：</p>
 * <pre><code class="lang-javascript">// 如下，baseOption 是一个 『原子option』，options 数组中的每一项也是一个 『原子option』。
 * // 每个『原子option』中就是本文档中描述的各种配置项。
 * myChart.setOption(
 *     {
 *         baseOption: {
 *             timeline: {
 *                 ...,
 *                 data: [&#39;2002-01-01&#39;, &#39;2003-01-01&#39;, &#39;2004-01-01&#39;]
 *             },
 *             title: {
 *                 subtext: &#39;数据来自国家统计局&#39;
 *             },
 *             grid: {...},
 *             xAxis: [...],
 *             yAxis: [...],
 *             series: [
 *                 { // 系列一的一些其他配置
 *                     type: &#39;bar&#39;,
 *                     ...
 *                 },
 *                 { // 系列二的一些其他配置
 *                     type: &#39;line&#39;,
 *                     ...
 *                 },
 *                 { // 系列三的一些其他配置
 *                     type: &#39;pie&#39;,
 *                     ...
 *                 }
 *             ]
 *         },
 *         options: [
 *             { // 这是&#39;2002-01-01&#39; 对应的 option
 *                 title: {
 *                     text: &#39;2002年统计值&#39;
 *                 },
 *                 series: [
 *                     {data: []}, // 系列一的数据
 *                     {data: []}, // 系列二的数据
 *                     {data: []}  // 系列三的数据
 *                 ]
 *             },
 *             { // 这是&#39;2003-01-01&#39; 对应的 option
 *                 title: {
 *                     text: &#39;2003年统计值&#39;
 *                 },
 *                 series: [
 *                     {data: []},
 *                     {data: []},
 *                     {data: []}
 *                 ]
 *             },
 *             { // 这是&#39;2004-01-01&#39; 对应的 option
 *                 title: {
 *                     text: &#39;2004年统计值&#39;
 *                 },
 *                 series: [
 *                     {data: []},
 *                     {data: []},
 *                     {data: []}
 *                 ]
 *             }
 *         ]
 *     }
 * );
 * </code></pre>
 * <p>在上例中，<code>timeline.data</code> 中的每一项，对应于 <code>options</code> 数组中的每个 <code>option</code>。</p>
 * <p><br>
 * <strong>使用注意与最佳实践：</strong></p>
 * <ul>
 * <li><p>公有的配置项，推荐配置在 <code>baseOption</code> 中。<code>timeline</code> 播放切换时，会把 <code>options</code> 数组中的对应的 <code>option</code>，与 <code>baseOption</code> 进行 merge 形成最终的 <code>option</code>。</p>
 * </li>
 * <li><p><code>options</code> 数组中，如果某一数组项中配置了某个属性，那么其他数组项中也必须配置某个属性，而不能缺省。否则这个属性的执行效果会遗留。</p>
 * </li>
 * <li><p><em>复合 option</em> 中的 <code>options</code> 不支持 merge。</p>
 * <p>  也就是说，当第二（或三、四、五 ...）次 <code>chart.setOption(rawOption)</code> 时，如果 <code>rawOption</code> 是<em>复合 option</em>（即包含 <code>options</code> 列表），那么新的 <code>rawOption.options</code> 列表不会和老的 <code>options</code> 列表进行 merge，而是简单替代。当然，<code>rawOption.baseOption</code> 仍然会正常和老的 option 进行merge。</p>
 * </li>
 * </ul>
 * <p><br>
 * <strong>与 ECharts 2 的兼容性：</strong></p>
 * <ul>
 * <li><p>ECharts 3 中不再支持 timeline.notMerge 参数，也就是不支持 notMerge 模式。如果遇到这种场景需要使用，可在外部进行option管理，并用 setOption(option, true) 这样的notMerge方式设置。</p>
 * </li>
 * <li><p>ECharts 3 和 ECharts 2 相比，timeline 属性的定义位置有所不同，移到了 <code>baseOption</code> 中，统一作为一个普通的组件看待。但是，仍然兼容 ECharts2 的 timeline 定义位置，只是不再推荐这样写。</p>
 * </li>
 * </ul>
 * </p>
 *
 * @author 小汉语
 */
public class EChartTimeline implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    private List data;

    private Boolean show;

    private EChartTimelineCheckpointStyle checkpointStyle;

    private String type;

    private Number currentIndex;

    private EChartTimelineControlStyle controlStyle;

    private Object top;

    private LineStyle lineStyle;

    private Boolean loop;

    private Number zlevel;

    private String axisType;

    private List padding;

    private Boolean inverse;

    private Boolean realtime;

    private Number playInterval;

    private String orient;

    private List symbolSize;

    private Object bottom;

    private ItemStyle itemStyle;

    private Object right;

    private EChartTimelineLabel label;

    private String controlPosition;

    private Number symbolRotate;

    private Boolean rewind;

    private Object left;

    private List symbolOffset;

    private Number z;

    private Boolean autoPlay;


    public String getSymbol(){
        return symbol;
    }

    public EChartTimeline setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartTimeline setData(List data) {
        this.data = data;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartTimeline setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public EChartTimelineCheckpointStyle getCheckpointStyle(){
        return checkpointStyle;
    }

    public EChartTimeline setCheckpointStyle(EChartTimelineCheckpointStyle checkpointStyle) {
        this.checkpointStyle = checkpointStyle;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartTimeline setType(String type) {
        this.type = type;
        return this;
    }

    public Number getCurrentIndex(){
        return currentIndex;
    }

    public EChartTimeline setCurrentIndex(Number currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    public EChartTimelineControlStyle getControlStyle(){
        return controlStyle;
    }

    public EChartTimeline setControlStyle(EChartTimelineControlStyle controlStyle) {
        this.controlStyle = controlStyle;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartTimeline setTop(Object top) {
        this.top = top;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartTimeline setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getLoop(){
        return loop;
    }

    public EChartTimeline setLoop(Boolean loop) {
        this.loop = loop;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartTimeline setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getAxisType(){
        return axisType;
    }

    public EChartTimeline setAxisType(String axisType) {
        this.axisType = axisType;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public EChartTimeline setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartTimeline setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Boolean getRealtime(){
        return realtime;
    }

    public EChartTimeline setRealtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    public Number getPlayInterval(){
        return playInterval;
    }

    public EChartTimeline setPlayInterval(Number playInterval) {
        this.playInterval = playInterval;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartTimeline setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartTimeline setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartTimeline setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartTimeline setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartTimeline setRight(Object right) {
        this.right = right;
        return this;
    }

    public EChartTimelineLabel getLabel(){
        return label;
    }

    public EChartTimeline setLabel(EChartTimelineLabel label) {
        this.label = label;
        return this;
    }

    public String getControlPosition(){
        return controlPosition;
    }

    public EChartTimeline setControlPosition(String controlPosition) {
        this.controlPosition = controlPosition;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartTimeline setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public Boolean getRewind(){
        return rewind;
    }

    public EChartTimeline setRewind(Boolean rewind) {
        this.rewind = rewind;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartTimeline setLeft(Object left) {
        this.left = left;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartTimeline setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartTimeline setZ(Number z) {
        this.z = z;
        return this;
    }

    public Boolean getAutoPlay(){
        return autoPlay;
    }

    public EChartTimeline setAutoPlay(Boolean autoPlay) {
        this.autoPlay = autoPlay;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (checkpointStyle != null)  {
            map.put("checkpointStyle", checkpointStyle.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (currentIndex != null)  {
            map.put("currentIndex", currentIndex);
        }
        if (controlStyle != null)  {
            map.put("controlStyle", controlStyle.toMap());
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (loop != null)  {
            map.put("loop", loop);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (axisType != null)  {
            map.put("axisType", axisType);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (inverse != null)  {
            map.put("inverse", inverse);
        }
        if (realtime != null)  {
            map.put("realtime", realtime);
        }
        if (playInterval != null)  {
            map.put("playInterval", playInterval);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
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
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (controlPosition != null)  {
            map.put("controlPosition", controlPosition);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (rewind != null)  {
            map.put("rewind", rewind);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (autoPlay != null)  {
            map.put("autoPlay", autoPlay);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
