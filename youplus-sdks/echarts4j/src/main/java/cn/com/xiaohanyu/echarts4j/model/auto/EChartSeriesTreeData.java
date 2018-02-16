package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><a href="#series-tree.data">series-tree.data</a> 的数据格式是树状结构的，例如：</p>
 * <pre><code class="lang-javascript">{ // 注意，最外层是一个对象，代表树的根节点
 *     name: &quot;flare&quot;,    // 节点的名称，当前节点 label 对应的文本
 *     label: {          // 此节点特殊的 label 配置（如果需要的话）。
 *         ...           // label的格式参见 series-tree.label。
 *     },
 *     itemStyle: {      // 此节点特殊的 itemStyle 配置（如果需要的话）。
 *         ...           // label的格式参见 series-tree.itemStyle。
 *     },
 *     children: [
 *         {
 *             name: &quot;flex&quot;,
 *             value: 4116,    // value 值，只在 tooltip 中显示
 *             label: {
 *                 ...
 *             },
 *             itemStyle: {
 *                 ...
 *             },
 *             collapsed: null, // 如果为 true，表示此节点默认折叠。
 *             children: [...] // 叶子节点没有 children, 可以不写
 *         },
 *         ...
 *     ]
 * };
 * </code></pre>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesTreeData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String animationEasing;

    private Object animationDelay;

    private EChartSeriesTreeDataTooltip tooltip;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesTreeDataLabel label;

    private Boolean animation;

    private Number animationDuration;

    private Number animationThreshold;

    private String animationEasingUpdate;

    private String name;

    private Number value;

    private Object animationDurationUpdate;


    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesTreeData setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesTreeData setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public EChartSeriesTreeDataTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesTreeData setTooltip(EChartSeriesTreeDataTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesTreeData setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesTreeData setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesTreeDataLabel getLabel(){
        return label;
    }

    public EChartSeriesTreeData setLabel(EChartSeriesTreeDataLabel label) {
        this.label = label;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesTreeData setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesTreeData setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesTreeData setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesTreeData setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesTreeData setName(String name) {
        this.name = name;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSeriesTreeData setValue(Number value) {
        this.value = value;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesTreeData setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (value != null)  {
            map.put("value", value);
        }
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
