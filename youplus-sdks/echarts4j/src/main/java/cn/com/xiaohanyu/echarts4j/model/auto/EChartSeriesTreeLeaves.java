package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>叶子节点的特殊配置，如上面的树图实例中，叶子节点和非叶子节点的标签位置不同。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesTreeLeaves implements Serializable {
    private static final long serialVersionUID = 1L;

    private ItemStyle itemStyle;

    private EChartSeriesTreeLeavesLabel label;


    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesTreeLeaves setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesTreeLeavesLabel getLabel(){
        return label;
    }

    public EChartSeriesTreeLeaves setLabel(EChartSeriesTreeLeavesLabel label) {
        this.label = label;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
