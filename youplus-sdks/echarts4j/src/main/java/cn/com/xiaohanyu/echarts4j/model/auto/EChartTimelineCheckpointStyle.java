package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>『当前项』（<code>checkpoint</code>）的图形样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartTimelineCheckpointStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number animationDuration;

    private String symbol;

    private Color borderColor;

    private Number symbolRotate;

    private String animationEasing;

    private Color color;

    private List symbolSize;

    private List symbolOffset;

    private Number borderWidth;

    private Boolean animation;


    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartTimelineCheckpointStyle setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public String getSymbol(){
        return symbol;
    }

    public EChartTimelineCheckpointStyle setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartTimelineCheckpointStyle setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getSymbolRotate(){
        return symbolRotate;
    }

    public EChartTimelineCheckpointStyle setSymbolRotate(Number symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartTimelineCheckpointStyle setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartTimelineCheckpointStyle setColor(Color color) {
        this.color = color;
        return this;
    }

    public List getSymbolSize(){
        return symbolSize;
    }

    public EChartTimelineCheckpointStyle setSymbolSize(List symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public List getSymbolOffset(){
        return symbolOffset;
    }

    public EChartTimelineCheckpointStyle setSymbolOffset(List symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartTimelineCheckpointStyle setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Boolean getAnimation(){
        return animation;
    }

    public EChartTimelineCheckpointStyle setAnimation(Boolean animation) {
        this.animation = animation;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (symbolRotate != null)  {
            map.put("symbolRotate", symbolRotate);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (symbolOffset != null)  {
            map.put("symbolOffset", symbolOffset);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
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
