package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>提示框组件。</p>
 * <hr>
 * <p><strong>提示框组件的通用介绍：</strong></p>
 * <p>提示框组件可以设置在多种地方：</p>
 * <ul>
 * <li><p>可以设置在全局，即 <a href="#tooltip">tooltip</a></p>
 * </li>
 * <li><p>可以设置在坐标系中，即 <a href="#grid.tooltip">grid.tooltip</a>、<a href="#polar.tooltip">polar.tooltip</a>、<a href="#single.tooltip">single.tooltip</a></p>
 * </li>
 * <li><p>可以设置在系列中，即 <a href="#series.tooltip">series.tooltip</a></p>
 * </li>
 * <li><p>可以设置在系列的每个数据项中，即 <a href="#series.data.tooltip">series.data.tooltip</a></p>
 * </li>
 * </ul>
 * <hr>
 * </p>
 *
 * @author 小汉语
 */
public class EChartTooltip implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean alwaysShowContent;

    private Number padding;

    private Color backgroundColor;

    private Color borderColor;

    private String triggerOn;

    private Boolean showContent;

    private Boolean show;

    private String trigger;

    private Number showDelay;

    private Boolean confine;

    private Object formatter;

    private Boolean enterable;

    private Number borderWidth;

    private String extraCssText;

    private EChartTooltipAxisPointer axisPointer;

    private Object position;

    private TextStyle textStyle;

    private Number transitionDuration;

    private Number hideDelay;


    public Boolean getAlwaysShowContent(){
        return alwaysShowContent;
    }

    public EChartTooltip setAlwaysShowContent(Boolean alwaysShowContent) {
        this.alwaysShowContent = alwaysShowContent;
        return this;
    }

    public Number getPadding(){
        return padding;
    }

    public EChartTooltip setPadding(Number padding) {
        this.padding = padding;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartTooltip setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartTooltip setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public String getTriggerOn(){
        return triggerOn;
    }

    public EChartTooltip setTriggerOn(String triggerOn) {
        this.triggerOn = triggerOn;
        return this;
    }

    public Boolean getShowContent(){
        return showContent;
    }

    public EChartTooltip setShowContent(Boolean showContent) {
        this.showContent = showContent;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartTooltip setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public String getTrigger(){
        return trigger;
    }

    public EChartTooltip setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Number getShowDelay(){
        return showDelay;
    }

    public EChartTooltip setShowDelay(Number showDelay) {
        this.showDelay = showDelay;
        return this;
    }

    public Boolean getConfine(){
        return confine;
    }

    public EChartTooltip setConfine(Boolean confine) {
        this.confine = confine;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartTooltip setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public Boolean getEnterable(){
        return enterable;
    }

    public EChartTooltip setEnterable(Boolean enterable) {
        this.enterable = enterable;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartTooltip setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public String getExtraCssText(){
        return extraCssText;
    }

    public EChartTooltip setExtraCssText(String extraCssText) {
        this.extraCssText = extraCssText;
        return this;
    }

    public EChartTooltipAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChartTooltip setAxisPointer(EChartTooltipAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public Object getPosition(){
        return position;
    }

    public EChartTooltip setPosition(Object position) {
        this.position = position;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartTooltip setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Number getTransitionDuration(){
        return transitionDuration;
    }

    public EChartTooltip setTransitionDuration(Number transitionDuration) {
        this.transitionDuration = transitionDuration;
        return this;
    }

    public Number getHideDelay(){
        return hideDelay;
    }

    public EChartTooltip setHideDelay(Number hideDelay) {
        this.hideDelay = hideDelay;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (alwaysShowContent != null)  {
            map.put("alwaysShowContent", alwaysShowContent);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (triggerOn != null)  {
            map.put("triggerOn", triggerOn);
        }
        if (showContent != null)  {
            map.put("showContent", showContent);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (trigger != null)  {
            map.put("trigger", trigger);
        }
        if (showDelay != null)  {
            map.put("showDelay", showDelay);
        }
        if (confine != null)  {
            map.put("confine", confine);
        }
        if (formatter != null)  {
            map.put("formatter", formatter);
        }
        if (enterable != null)  {
            map.put("enterable", enterable);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (extraCssText != null)  {
            map.put("extraCssText", extraCssText);
        }
        if (axisPointer != null)  {
            map.put("axisPointer", axisPointer.toMap());
        }
        if (position != null)  {
            map.put("position", position);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        if (transitionDuration != null)  {
            map.put("transitionDuration", transitionDuration);
        }
        if (hideDelay != null)  {
            map.put("hideDelay", hideDelay);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
