package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>这个组件是平行坐标系中的坐标轴。</p>
 * <p><br></p>
 * <hr>
 * <p><strong>平行坐标系介绍</strong></p>
 * <p><a href="https://en.wikipedia.org/wiki/Parallel_coordinates" target="_blank">平行坐标系（Parallel Coordinates）</a> 是一种常用的可视化高维数据的图表。</p>
 * <p>例如 <a href="#series-parallel.data">series-parallel.data</a> 中有如下数据：</p>
 * <pre><code class="lang-javascript">[
 *     [1,  55,  9,   56,  0.46,  18,  6,  &#39;良&#39;],
 *     [2,  25,  11,  21,  0.65,  34,  9,  &#39;优&#39;],
 *     [3,  56,  7,   63,  0.3,   14,  5,  &#39;良&#39;],
 *     [4,  33,  7,   29,  0.33,  16,  6,  &#39;优&#39;],
 *     { // 数据项也可以是 Object，从而里面能含有对线条的特殊设置。
 *         value: [5,  42,  24,  44,  0.76,  40,  16, &#39;优&#39;]
 *         lineStyle: {...},
 *     }
 *     ...
 * ]
 * </code></pre>
 * <p>数据中，每一行是一个『数据项』，每一列属于一个『维度』。（例如上面数据每一列的含义分别是：『日期』,『AQI指数』, 『PM2.5』, 『PM10』, 『一氧化碳值』, 『二氧化氮值』, 『二氧化硫值』）。</p>
 * <p>平行坐标系适用于对这种多维数据进行可视化分析。每一个维度（每一列）对应一个坐标轴，每一个『数据项』是一条线，贯穿多个坐标轴。在坐标轴上，可以进行数据选取等操作。如下：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/parallel-all&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * <p><br></p>
 * <hr>
 * <p><strong>配置方式概要</strong></p>
 * <p>『平行坐标系』的 <code>option</code> 基本配置如下例：</p>
 * <pre><code class="lang-javascript">option = {
 *     parallelAxis: [                     // 这是一个个『坐标轴』的定义
 *         {dim: 0, name: schema[0].text}, // 每个『坐标轴』有个 &#39;dim&#39; 属性，表示坐标轴的维度号。
 *         {dim: 1, name: schema[1].text},
 *         {dim: 2, name: schema[2].text},
 *         {dim: 3, name: schema[3].text},
 *         {dim: 4, name: schema[4].text},
 *         {dim: 5, name: schema[5].text},
 *         {dim: 6, name: schema[6].text},
 *         {dim: 7, name: schema[7].text,
 *             type: &#39;category&#39;,           // 坐标轴也可以支持类别型数据
 *             data: [&#39;优&#39;, &#39;良&#39;, &#39;轻度污染&#39;, &#39;中度污染&#39;, &#39;重度污染&#39;, &#39;严重污染&#39;]
 *         }
 *     ],
 *     parallel: {                         // 这是『坐标系』的定义
 *         left: &#39;5%&#39;,                     // 平行坐标系的位置设置
 *         right: &#39;13%&#39;,
 *         bottom: &#39;10%&#39;,
 *         top: &#39;20%&#39;,
 *         parallelAxisDefault: {          // 『坐标轴』的公有属性可以配置在这里避免重复书写
 *             type: &#39;value&#39;,
 *             nameLocation: &#39;end&#39;,
 *             nameGap: 20
 *         }
 *     },
 *     series: [                           // 这里三个系列共用一个平行坐标系
 *         {
 *             name: &#39;北京&#39;,
 *             type: &#39;parallel&#39;,           // 这个系列类型是 &#39;parallel&#39;
 *             data: [
 *                 [1,  55,  9,   56,  0.46,  18,  6,  &#39;良&#39;],
 *                 [2,  25,  11,  21,  0.65,  34,  9,  &#39;优&#39;],
 *                 ...
 *             ]
 *         },
 *         {
 *             name: &#39;上海&#39;,
 *             type: &#39;parallel&#39;,
 *             data: [
 *                 [3,  56,  7,   63,  0.3,   14,  5,  &#39;良&#39;],
 *                 [4,  33,  7,   29,  0.33,  16,  6,  &#39;优&#39;],
 *                 ...
 *             ]
 *         },
 *         {
 *             name: &#39;广州&#39;,
 *             type: &#39;parallel&#39;,
 *             data: [
 *                 [4,  33,  7,   29,  0.33,  16,  6,  &#39;优&#39;],
 *                 [5,  42,  24,  44,  0.76,  40,  16, &#39;优&#39;],
 *                 ...
 *             ]
 *         }
 *     ]
 * };
 * </code></pre>
 * <p>需要涉及到三个组件：<a href="#parallel">parallel</a>、<a href="#parallelAxis">parallelAxis</a>、<a href="#series-parallel">series-parallel</a></p>
 * <ul>
 * <li><p><a href="#parallel">parallel</a></p>
 * <p>  这个配置项是平行坐标系的『坐标系』本身。一个系列（<code>series</code>）或多个系列（如上图中的『北京』、『上海』、『广州』分别各是一个系列）可以共用这个『坐标系』。</p>
 * <p>  和其他坐标系一样，坐标系也可以创建多个。</p>
 * <p>  位置设置，也是放在这里进行。</p>
 * </li>
 * <li><p><a href="#parallelAxis">parallelAxis</a></p>
 * <p>  这个是『坐标系』中的坐标轴的配置。自然，需要有多个坐标轴。</p>
 * <p>  其中有 <a href="#parallelAxis.parallelIndex">parallelAxis.parallelIndex</a> 属性，指定这个『坐标轴』在哪个『坐标系』中。默认使用第一个『坐标系』。</p>
 * </li>
 * <li><p><a href="#series-parallel">series-parallel</a></p>
 * <p>  这个是『系列』的定义。系列被画到『坐标系』上。</p>
 * <p>  其中有 <a href="#series-parallel.parallelIndex">series-parallel.parallelIndex</a> 属性，指定使用哪个『坐标系』。默认使用第一个『坐标系』。</p>
 * </li>
 * </ul>
 * <p><br></p>
 * <hr>
 * <p><strong>配置注意和最佳实践</strong></p>
 * <p>配置多个 <a href="#parallelAxis">parallelAxis</a> 时，有些值一样的属性，如果书写多遍则比较繁琐，那么可以放置在 <a href="#parallel.parallelAxisDefault">parallel.parallelAxisDefault</a> 里。在坐标轴初始化前，<a href="#parallel.parallelAxisDefault">parallel.parallelAxisDefault</a> 里的配置项，会分别融合进 <a href="#parallelAxis">parallelAxis</a>，形成最终的坐标轴的配置。</p>
 * <p><strong>如果数据量很大并且发生卡顿</strong></p>
 * <p>建议把 <a href="#series-parallel.lineStyle.normal.width">series-parallel.lineStyle.normal.width</a> 设为 <code>0.5</code>（或更小），
 * 可能显著改善性能。</p>
 * <p><br></p>
 * <hr>
 * <p><strong>高维数据的显示</strong></p>
 * <p>维度比较多时，比如有 50+ 的维度，那么就会有 50+ 个轴。那么可能会页面显示不下。</p>
 * <p>可以通过 <a href="#parallel.axisExpandable">parallel.axisExpandable</a> 来改善显示效果，如下例子：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=map-parallel-prices&edit=1&reset=1" width="600" height="460" ></iframe>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartParallelAxis implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private EChartParallelAxisAreaSelectStyle areaSelectStyle;

    private Number dim;

    private Boolean scale;

    private String nameLocation;

    private String type;

    private Number parallelIndex;

    private EChartParallelAxisAxisLabel axisLabel;

    private Object min;

    private EChartParallelAxisAxisLine axisLine;

    private NameTextStyle nameTextStyle;

    private Number logBase;

    private Boolean inverse;

    private Boolean realtime;

    private Boolean silent;

    private Object max;

    private Number nameGap;

    private Number splitNumber;

    private Number maxInterval;

    private List boundaryGap;

    private Number minInterval;

    private Number nameRotate;

    private String name;

    private EChartParallelAxisAxisTick axisTick;

    private Number interval;

    private Boolean triggerEvent;


    public List getData(){
        return data;
    }

    public EChartParallelAxis setData(List data) {
        this.data = data;
        return this;
    }

    public EChartParallelAxisAreaSelectStyle getAreaSelectStyle(){
        return areaSelectStyle;
    }

    public EChartParallelAxis setAreaSelectStyle(EChartParallelAxisAreaSelectStyle areaSelectStyle) {
        this.areaSelectStyle = areaSelectStyle;
        return this;
    }

    public Number getDim(){
        return dim;
    }

    public EChartParallelAxis setDim(Number dim) {
        this.dim = dim;
        return this;
    }

    public Boolean getScale(){
        return scale;
    }

    public EChartParallelAxis setScale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public String getNameLocation(){
        return nameLocation;
    }

    public EChartParallelAxis setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartParallelAxis setType(String type) {
        this.type = type;
        return this;
    }

    public Number getParallelIndex(){
        return parallelIndex;
    }

    public EChartParallelAxis setParallelIndex(Number parallelIndex) {
        this.parallelIndex = parallelIndex;
        return this;
    }

    public EChartParallelAxisAxisLabel getAxisLabel(){
        return axisLabel;
    }

    public EChartParallelAxis setAxisLabel(EChartParallelAxisAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    public Object getMin(){
        return min;
    }

    public EChartParallelAxis setMin(Object min) {
        this.min = min;
        return this;
    }

    public EChartParallelAxisAxisLine getAxisLine(){
        return axisLine;
    }

    public EChartParallelAxis setAxisLine(EChartParallelAxisAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public NameTextStyle getNameTextStyle(){
        return nameTextStyle;
    }

    public EChartParallelAxis setNameTextStyle(NameTextStyle nameTextStyle) {
        this.nameTextStyle = nameTextStyle;
        return this;
    }

    public Number getLogBase(){
        return logBase;
    }

    public EChartParallelAxis setLogBase(Number logBase) {
        this.logBase = logBase;
        return this;
    }

    public Boolean getInverse(){
        return inverse;
    }

    public EChartParallelAxis setInverse(Boolean inverse) {
        this.inverse = inverse;
        return this;
    }

    public Boolean getRealtime(){
        return realtime;
    }

    public EChartParallelAxis setRealtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartParallelAxis setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Object getMax(){
        return max;
    }

    public EChartParallelAxis setMax(Object max) {
        this.max = max;
        return this;
    }

    public Number getNameGap(){
        return nameGap;
    }

    public EChartParallelAxis setNameGap(Number nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public Number getSplitNumber(){
        return splitNumber;
    }

    public EChartParallelAxis setSplitNumber(Number splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public Number getMaxInterval(){
        return maxInterval;
    }

    public EChartParallelAxis setMaxInterval(Number maxInterval) {
        this.maxInterval = maxInterval;
        return this;
    }

    public List getBoundaryGap(){
        return boundaryGap;
    }

    public EChartParallelAxis setBoundaryGap(List boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    public Number getMinInterval(){
        return minInterval;
    }

    public EChartParallelAxis setMinInterval(Number minInterval) {
        this.minInterval = minInterval;
        return this;
    }

    public Number getNameRotate(){
        return nameRotate;
    }

    public EChartParallelAxis setNameRotate(Number nameRotate) {
        this.nameRotate = nameRotate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartParallelAxis setName(String name) {
        this.name = name;
        return this;
    }

    public EChartParallelAxisAxisTick getAxisTick(){
        return axisTick;
    }

    public EChartParallelAxis setAxisTick(EChartParallelAxisAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public Number getInterval(){
        return interval;
    }

    public EChartParallelAxis setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Boolean getTriggerEvent(){
        return triggerEvent;
    }

    public EChartParallelAxis setTriggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (areaSelectStyle != null)  {
            map.put("areaSelectStyle", areaSelectStyle.toMap());
        }
        if (dim != null)  {
            map.put("dim", dim);
        }
        if (scale != null)  {
            map.put("scale", scale);
        }
        if (nameLocation != null)  {
            map.put("nameLocation", nameLocation);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (parallelIndex != null)  {
            map.put("parallelIndex", parallelIndex);
        }
        if (axisLabel != null)  {
            map.put("axisLabel", axisLabel.toMap());
        }
        if (min != null)  {
            map.put("min", min);
        }
        if (axisLine != null)  {
            map.put("axisLine", axisLine.toMap());
        }
        if (nameTextStyle != null)  {
            map.put("nameTextStyle", nameTextStyle.toMap());
        }
        if (logBase != null)  {
            map.put("logBase", logBase);
        }
        if (inverse != null)  {
            map.put("inverse", inverse);
        }
        if (realtime != null)  {
            map.put("realtime", realtime);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (max != null)  {
            map.put("max", max);
        }
        if (nameGap != null)  {
            map.put("nameGap", nameGap);
        }
        if (splitNumber != null)  {
            map.put("splitNumber", splitNumber);
        }
        if (maxInterval != null)  {
            map.put("maxInterval", maxInterval);
        }
        if (boundaryGap != null)  {
            map.put("boundaryGap", boundaryGap);
        }
        if (minInterval != null)  {
            map.put("minInterval", minInterval);
        }
        if (nameRotate != null)  {
            map.put("nameRotate", nameRotate);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (axisTick != null)  {
            map.put("axisTick", axisTick.toMap());
        }
        if (interval != null)  {
            map.put("interval", interval);
        }
        if (triggerEvent != null)  {
            map.put("triggerEvent", triggerEvent);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
