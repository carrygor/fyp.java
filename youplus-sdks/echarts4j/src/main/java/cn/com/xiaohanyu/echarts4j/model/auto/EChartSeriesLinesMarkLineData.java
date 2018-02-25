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
 * <li>通过 <a href="#series-lines.markLine.data.0.x">x</a>, <a href="#series-lines.markLine.data.0.y">y</a> 属性指定相对容器的屏幕坐标，单位像素，支持百分比。</li>
 * </ol>
 * <p>当多个属性同时存在时，优先级按上述的顺序。</p>
 * <pre><code>data: [
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
public class EChartSeriesLinesMarkLineData implements Serializable {
    private static final long serialVersionUID = 1L;

    private EChartSeriesLinesMarkLineDataReplace0 replace0;

    private EChartSeriesLinesMarkLineDataReplace1 replace1;


    public EChartSeriesLinesMarkLineDataReplace0 getReplace0(){
        return replace0;
    }

    public EChartSeriesLinesMarkLineData setReplace0(EChartSeriesLinesMarkLineDataReplace0 replace0) {
        this.replace0 = replace0;
        return this;
    }

    public EChartSeriesLinesMarkLineDataReplace1 getReplace1(){
        return replace1;
    }

    public EChartSeriesLinesMarkLineData setReplace1(EChartSeriesLinesMarkLineDataReplace1 replace1) {
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