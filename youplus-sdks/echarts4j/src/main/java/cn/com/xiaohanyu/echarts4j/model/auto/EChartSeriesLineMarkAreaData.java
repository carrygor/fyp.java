package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>标域的数据数组。每个数组项是一个两个项的数组，分别表示标域左上角和右下角的位置，每个项支持通过下面几种方式指定自己的位置</p>
 * <ol>
 * <li><p>通过 <a href="#series-line.markArea.data.0.x">x</a>, <a href="#series-line.markArea.data.0.y">y</a> 属性指定相对容器的屏幕坐标，单位像素，支持百分比。</p>
 * </li>
 * <li><p>用 <a href="#series-line.markArea.data.0.coord">coord</a> 属性指定数据在相应坐标系上的坐标位置，单个维度支持设置 <code>&#39;min&#39;</code>, <code>&#39;max&#39;</code>, <code>&#39;average&#39;</code>。</p>
 * </li>
 * <li><p>直接用 <a href="#series-line.markArea.data.0.type">type</a> 属性标注系列中的最大值，最小值。这时候可以使用 <a href="#series-line.markArea.data.0.valueIndex">valueIndex</a> 或者 <a href="#series-line.markPoint.data.0.valueDim">valueDim</a> 指定是在哪个维度上的最大值、最小值、平均值。</p>
 * </li>
 * <li><p>如果是笛卡尔坐标系的话，也可以通过只指定 <code>xAxis</code> 或者 <code>yAxis</code> 来实现 X 轴或者 Y 轴为某值的标域，见示例 <a href="http://echarts.baidu.com/gallery/editor.html?c=scatter-weight" target="_blank">scatter-weight</a></p>
 * </li>
 * </ol>
 * <p>当多个属性同时存在时，优先级按上述的顺序。</p>
 * <pre><code>data: [
 * 
 *     [
 *         {
 *             name: &#39;平均值到最大值&#39;,
 *             type: &#39;average&#39;
 *         },
 *         {
 *             type: &#39;max&#39;
 *         }
 *     ],
 * 
 *     [
 *         {
 *             name: &#39;两个坐标之间的标域&#39;,
 *             coord: [10, 20]
 *         },
 *         {
 *             coord: [20, 30]
 *         }
 *     ], [
 *         {
 *             name: &#39;60分到80分&#39;,
 *             yAxis: 60
 *         },
 *         {
 *             yAxis: 80
 *         }
 *     ], [
 *         {
 *             name: &#39;所有数据范围区间&#39;
 *             coord: [&#39;min&#39;, &#39;min&#39;]
 *         },
 *         {
 *             coord: [&#39;max&#39;, &#39;max&#39;]
 *         }
 *     ],
 *     [
 *         {
 *             name: &#39;两个屏幕坐标之间的标域&#39;,
 *             x: 100,
 *             y: 100
 *         }, {
 *             x: &#39;90%&#39;,
 *             y: &#39;10%&#39;
 *         }
 *     ]
 * ]
 * </code></pre>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesLineMarkAreaData implements Serializable {
    private static final long serialVersionUID = 1L;

    private EChartSeriesLineMarkAreaDataReplace0 replace0;

    private EChartSeriesLineMarkAreaDataReplace1 replace1;


    public EChartSeriesLineMarkAreaDataReplace0 getReplace0(){
        return replace0;
    }

    public EChartSeriesLineMarkAreaData setReplace0(EChartSeriesLineMarkAreaDataReplace0 replace0) {
        this.replace0 = replace0;
        return this;
    }

    public EChartSeriesLineMarkAreaDataReplace1 getReplace1(){
        return replace1;
    }

    public EChartSeriesLineMarkAreaData setReplace1(EChartSeriesLineMarkAreaDataReplace1 replace1) {
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
