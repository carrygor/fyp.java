package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>标线的数据数组。每个数组项可以是一个两个值的数组，分别表示线的起点和终点，每一项是一个对象，有下面几种方式指定起点或终点的位置。</p>
 * <ol>
 * <li><p>通过 <a href="#series-effectScatter.markLine.data.0.x">x</a>, <a href="#series-effectScatter.markLine.data.0.y">y</a> 属性指定相对容器的屏幕坐标，单位像素，支持百分比。</p>
 * </li>
 * <li><p>用 <a href="#series-effectScatter.markLine.data.0.coord">coord</a> 属性指定数据在相应坐标系上的坐标位置，单个维度支持设置 <code>&#39;min&#39;</code>, <code>&#39;max&#39;</code>, <code>&#39;average&#39;</code>。</p>
 * </li>
 * <li><p>直接用 <a href="#series-effectScatter.markLine.data.0.type">type</a> 属性标注系列中的最大值，最小值。这时候可以使用 <a href="#series-effectScatter.markLine.data.0.valueIndex">valueIndex</a> 或者 <a href="#series-effectScatter.markLine.data.0.valueDim">valueDim</a> 指定是在哪个维度上的最大值、最小值、平均值。</p>
 * </li>
 * <li><p>如果是笛卡尔坐标系的话，也可以通过只指定 <code>xAxis</code> 或者 <code>yAxis</code> 来实现 X 轴或者 Y 轴为某值的标线，见示例 <a href="http://echarts.baidu.com/gallery/editor.html?c=scatter-weight" target="_blank">scatter-weight</a></p>
 * </li>
 * </ol>
 * <p>当多个属性同时存在时，优先级按上述的顺序。</p>
 * <p>也可以是直接通过 <code>type</code> 设置该标线的类型，是最大值的线还是平均线。同样的，这时候可以通过使用 <code>valueIndex</code> 指定维度。</p>
 * <pre><code>data: [
 *     {
 *         name: &#39;平均线&#39;,
 *         // 支持 &#39;average&#39;, &#39;min&#39;, &#39;max&#39;
 *         type: &#39;average&#39;
 *     },
 *     {
 *         name: &#39;Y 轴值为 100 的水平线&#39;,
 *         yAxis: 100
 *     },
 *     [
 *         {
 *             // 起点和终点的项会共用一个 name
 *             name: &#39;最小值到最大值&#39;,
 *             type: &#39;min&#39;
 *         },
 *         {
 *             type: &#39;max&#39;
 *         }
 *     ],
 *     [
 *         {
 *             name: &#39;两个坐标之间的标线&#39;,
 *             coord: [10, 20]
 *         },
 *         {
 *             coord: [20, 30]
 *         }
 *     ], [{
 *         // 固定起点的 x 像素位置，用于模拟一条指向最大值的水平线
 *         yAxis: &#39;max&#39;,
 *         x: &#39;90%&#39;
 *     }, {
 *         type: &#39;max&#39;
 *     }],
 *     [
 *         {
 *             name: &#39;两个屏幕坐标之间的标线&#39;,
 *             x: 100,
 *             y: 100
 *         },
 *         {
 *             x: 500,
 *             y: 200
 *         }
 *     ]
 * ]
 * </code></pre>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesEffectScatterMarkLineData implements Serializable {
    private static final long serialVersionUID = 1L;

    private EChartSeriesEffectScatterMarkLineDataReplace0 replace0;

    private EChartSeriesEffectScatterMarkLineDataReplace1 replace1;


    public EChartSeriesEffectScatterMarkLineDataReplace0 getReplace0(){
        return replace0;
    }

    public EChartSeriesEffectScatterMarkLineData setReplace0(EChartSeriesEffectScatterMarkLineDataReplace0 replace0) {
        this.replace0 = replace0;
        return this;
    }

    public EChartSeriesEffectScatterMarkLineDataReplace1 getReplace1(){
        return replace1;
    }

    public EChartSeriesEffectScatterMarkLineData setReplace1(EChartSeriesEffectScatterMarkLineDataReplace1 replace1) {
        this.replace1 = replace1;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (replace0 != null)  {
            map.put("replace0", replace0.toMap());
        }
        if (replace1 != null)  {
            map.put("replace1", replace1.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
