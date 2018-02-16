package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>图表标域，常用于标记图表中某个范围的数据，例如标出某段时间投放了广告。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesMapMarkArea implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number animationDuration;

    private Boolean silent;

    private Number animationThreshold;

    private String animationEasingUpdate;

    private String animationEasing;

    private EChartSeriesMapMarkAreaData data;

    private Object animationDelay;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesMapMarkAreaLabel label;

    private Object animationDurationUpdate;

    private Boolean animation;


    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesMapMarkArea setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesMapMarkArea setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesMapMarkArea setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesMapMarkArea setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesMapMarkArea setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public EChartSeriesMapMarkAreaData getData(){
        return data;
    }

    public EChartSeriesMapMarkArea setData(EChartSeriesMapMarkAreaData data) {
        this.data = data;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesMapMarkArea setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesMapMarkArea setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesMapMarkArea setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesMapMarkAreaLabel getLabel(){
        return label;
    }

    public EChartSeriesMapMarkArea setLabel(EChartSeriesMapMarkAreaLabel label) {
        this.label = label;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesMapMarkArea setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesMapMarkArea setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationThreshold != null)  {
            map.put("animationThreshold", animationThreshold);
        }
        if (animationEasingUpdate != null)  {
            map.put("animationEasingUpdate", animationEasingUpdate);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (data != null)  {
            map.put("data", data.toMap());
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
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
        if (animationDurationUpdate != null)  {
            map.put("animationDurationUpdate", animationDurationUpdate);
        }
        if (animation != null)  {
            map.put("animation", animation);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
