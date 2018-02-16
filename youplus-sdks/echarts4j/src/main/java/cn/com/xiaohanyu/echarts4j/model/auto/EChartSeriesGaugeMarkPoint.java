package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>图表标注。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGaugeMarkPoint implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    private Boolean silent;

    private String animationEasing;

    private List data;

    private Object animationDelay;

    private Object symbolSize;

    private Object animationDelayUpdate;

    private ItemStyle itemStyle;

    private EChartSeriesGaugeMarkPointLabel label;

    private Boolean animation;

    private Number animationDuration;

    private Number animationThreshold;

    private String animationEasingUpdate;

    private Number symbolRotate;

    private List symbolOffset;

    private Object animationDurationUpdate;


    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesGaugeMarkPoint setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesGaugeMarkPoint setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesGaugeMarkPoint setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesGaugeMarkPoint setData(List data) {
        this.data = data;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesGaugeMarkPoint setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Object getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesGaugeMarkPoint setSymbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesGaugeMarkPoint setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesGaugeMarkPoint setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesGaugeMarkPointLabel getLabel(){
        return label;
    }

    public EChartSeriesGaugeMarkPoint setLabel(EChartSeriesGaugeMarkPointLabel label) {
        this.label = label;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesGaugeMarkPoint setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesGaugeMarkPoint setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesGaugeMarkPoint setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesGaugeMarkPoint setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartSeriesGaugeMarkPoint setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartSeriesGaugeMarkPoint setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesGaugeMarkPoint setAnimationDurationUpdate(Object animationDurationUpdate) {
        this.animationDurationUpdate = animationDurationUpdate;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
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
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
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
