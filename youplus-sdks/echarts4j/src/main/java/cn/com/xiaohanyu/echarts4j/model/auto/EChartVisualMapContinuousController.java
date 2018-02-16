package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>visualMap 组件中，<code>控制器</code> 的 <code>inRange</code> <code>outOfRange</code> 设置。如果没有这个 <code>controller</code> 设置，<code>控制器</code> 会使用外层的 <code>inRange</code> <code>outOfRange</code> 设置；如果有这个 <code>controller</code> 设置，则会采用这个设置。适用于一些控制器视觉效果需要特殊定制或调整的场景。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartVisualMapContinuousController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object outOfRange;

    private Object inRange;


    public Object getOutOfRange(){
        return outOfRange;
    }

    public EChartVisualMapContinuousController setOutOfRange(Object outOfRange) {
        this.outOfRange = outOfRange;
        return this;
    }

    public Object getInRange(){
        return inRange;
    }

    public EChartVisualMapContinuousController setInRange(Object inRange) {
        this.inRange = inRange;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (outOfRange != null)  {
            map.put("outOfRange", outOfRange);
        }
        if (inRange != null)  {
            map.put("inRange", inRange);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
