package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>滚轮缩放的极限控制，通过<code>min</code>, <code>max</code>最小和最大的缩放值，默认的缩放为<code>1</code>。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesMapScaleLimit implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number min;

    private Number max;


    public Number getMin(){
        return min;
    }

    public EChartSeriesMapScaleLimit setMin(Number min) {
        this.min = min;
        return this;
    }

    public Number getMax(){
        return max;
    }

    public EChartSeriesMapScaleLimit setMax(Number max) {
        this.max = max;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (min != null)  {
            map.put("min", min);
        }
        if (max != null)  {
            map.put("max", max);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
