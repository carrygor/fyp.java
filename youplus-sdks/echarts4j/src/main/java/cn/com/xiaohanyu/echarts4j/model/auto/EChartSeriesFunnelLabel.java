package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>漏斗图图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等，<code>label</code>选项在 ECharts 2.x 中放置于<code>itemStyle.normal</code>下，在 ECharts 3 中为了让整个配置项结构更扁平合理，<code>label</code> 被拿出来跟 <code>itemStyle</code> 平级，并且跟 <code>itemStyle</code> 一样拥有 <code>normal</code>, <code>emphasis</code> 两个状态。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesFunnelLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Normal normal;

    private Emphasis emphasis;


    public Normal getNormal(){
        return normal;
    }

    public EChartSeriesFunnelLabel setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public EChartSeriesFunnelLabel setEmphasis(Emphasis emphasis) {
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
