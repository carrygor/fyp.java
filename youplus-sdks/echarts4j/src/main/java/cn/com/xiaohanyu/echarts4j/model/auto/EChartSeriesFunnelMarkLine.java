package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>图表标线。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesFunnelMarkLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private List symbol;

    private Boolean silent;

    private String animationEasing;

    private EChartSeriesFunnelMarkLineData data;

    private Object animationDelay;

    private List symbolSize;

    private Number precision;

    private Object animationDelayUpdate;

    private EChartSeriesFunnelMarkLineLabel label;

    private Boolean animation;

    private Number animationDuration;

    private Number animationThreshold;

    private String animationEasingUpdate;

    private LineStyle lineStyle;

    private Object animationDurationUpdate;


    public List getSymbol(){
        return symbol;
    }

    public EChartSeriesFunnelMarkLine setSymbol(List symbol) {
        this.symbol = symbol;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesFunnelMarkLine setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesFunnelMarkLine setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public EChartSeriesFunnelMarkLineData getData(){
        return data;
    }

    public EChartSeriesFunnelMarkLine setData(EChartSeriesFunnelMarkLineData data) {
        this.data = data;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesFunnelMarkLine setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesFunnelMarkLine setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Number getPrecision(){
        return precision;
    }

    public EChartSeriesFunnelMarkLine setPrecision(Number precision) {
        this.precision = precision;
        return this;
    }

    public Object getAnimationDelayUpdate(){
        return animationDelayUpdate;
    }

    public EChartSeriesFunnelMarkLine setAnimationDelayUpdate(Object animationDelayUpdate) {
        this.animationDelayUpdate = animationDelayUpdate;
        return this;
    }

    public EChartSeriesFunnelMarkLineLabel getLabel(){
        return label;
    }

    public EChartSeriesFunnelMarkLine setLabel(EChartSeriesFunnelMarkLineLabel label) {
        this.label = label;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartSeriesFunnelMarkLine setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesFunnelMarkLine setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getAnimationThreshold(){
        return animationThreshold;
    }

    public EChartSeriesFunnelMarkLine setAnimationThreshold(Number animationThreshold) {
        this.animationThreshold = animationThreshold;
        return this;
    }

    public String getAnimationEasingUpdate(){
        return animationEasingUpdate;
    }

    public EChartSeriesFunnelMarkLine setAnimationEasingUpdate(String animationEasingUpdate) {
        this.animationEasingUpdate = animationEasingUpdate;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSeriesFunnelMarkLine setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Object getAnimationDurationUpdate(){
        return animationDurationUpdate;
    }

    public EChartSeriesFunnelMarkLine setAnimationDurationUpdate(Object animationDurationUpdate) {
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
            map.put("data", data.toMap());
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (precision != null)  {
            map.put("precision", precision);
        }
        if (animationDelayUpdate != null)  {
            map.put("animationDelayUpdate", animationDelayUpdate);
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
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
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
