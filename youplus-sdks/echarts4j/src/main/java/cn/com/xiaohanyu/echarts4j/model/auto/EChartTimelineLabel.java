package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>轴的文本标签。有 <code>normal</code> 和 <code>emphasis</code> 两个状态，<code>normal</code> 是文本正常的样式，<code>emphasis</code> 是文本高亮的样式，比如鼠标悬浮或者图例联动高亮的时候会使用 <code>emphasis</code> 作为文本的样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartTimelineLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Normal normal;

    private Emphasis emphasis;


    public Normal getNormal(){
        return normal;
    }

    public EChartTimelineLabel setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public EChartTimelineLabel setEmphasis(Emphasis emphasis) {
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
