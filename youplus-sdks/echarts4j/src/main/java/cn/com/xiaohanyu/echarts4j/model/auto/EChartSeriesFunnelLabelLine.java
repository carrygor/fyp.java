package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>标签的视觉引导线样式，在 <a href="#series-funnel.label.normal.position">label 位置</a> 设置为<code>&#39;left&#39;</code>或者<code>&#39;right&#39;</code>的时候会显示视觉引导线。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesFunnelLabelLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private Normal normal;

    private Emphasis emphasis;


    public Normal getNormal(){
        return normal;
    }

    public EChartSeriesFunnelLabelLine setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public EChartSeriesFunnelLabelLine setEmphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (normal != null)  {
            map.put("normal", normal.toMap());
        }
        if (emphasis != null)  {
            map.put("emphasis", emphasis.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
