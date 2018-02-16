package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><code>label</code> 描述了每个矩形中，文本标签的样式。</p>
 * <p><br></p>
 * <blockquote>
 * <p>注：treemap中 <code>label</code> 属性可能在多处地方存在：</p>
 * <ul>
 * <li>可以存在于 <a href="#series-treemap">sereis-treemap</a> 根下，表示本系列全局的统一设置。</li>
 * </ul>
 * <ul>
 * <li><p>可以存在于 <a href="#series-treemap.levels">series-treemap.levels</a> 的每个数组元素中，表示树每个层级的统一设置。</p>
 * </li>
 * <li><p>存在于 <a href="#series-treemap.data">series-treemap.data</a> 的每个节点中，表示每个节点的特定设置。</p>
 * </li>
 * </ul>
 * </blockquote>
 * <p><br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesTreemapLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Normal normal;

    private Emphasis emphasis;


    public Normal getNormal(){
        return normal;
    }

    public EChartSeriesTreemapLabel setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public EChartSeriesTreemapLabel setEmphasis(Emphasis emphasis) {
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
