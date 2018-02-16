package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><code>upperLabel</code> 用于显示矩形的父节点的标签。当 <a href="#series-treemap.upperLabel.normal.show">upperLabel.normal.show</a> 为 <code>true</code> 的时候，『显示父节点标签』功能开启。</p>
 * <p>同 <a href="#series-treemap.label">series-treemap.label</a> 一样，<code>upperLabel</code> 可以存在于 <a href="#series-treemap">series-treemap</a> 的根节点，或者 <a href="#series-treemap.level">series-treemap.level</a> 中，或者 <a href="#series-treemap.data">series-treemap.data</a> 的每个数据项中。</p>
 * <p><a href="#series-treemap.label">series-treemap.label</a> 描述的是，当前节点为叶节点时标签的样式；<code>upperLabel</code> 描述的是，当前节点为非叶节点（即含有子节点）时标签的样式。（此时标签一般会被显示在节点的最上部）</p>
 * <p>参见：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=treemap-show-parent&edit=1&reset=1" width="700" height="500" ></iframe>
 * 
 * 
 * 
 * 
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
public class EChartSeriesTreemapUpperLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Normal normal;

    private Emphasis emphasis;


    public Normal getNormal(){
        return normal;
    }

    public EChartSeriesTreemapUpperLabel setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public EChartSeriesTreemapUpperLabel setEmphasis(Emphasis emphasis) {
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
