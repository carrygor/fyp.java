package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>本坐标系特定的 tooltip 设定。</p>
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
public class EChartPolarTooltip implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object formatter;

    private Number padding;

    private Color backgroundColor;

    private Color borderColor;

    private Number borderWidth;

    private String extraCssText;

    private Boolean show;

    private EChartPolarTooltipAxisPointer axisPointer;

    private String trigger;

    private Object position;

    private TextStyle textStyle;


    public Object getFormatter(){
        return formatter;
    }

    public EChartPolarTooltip setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public Number getPadding(){
        return padding;
    }

    public EChartPolarTooltip setPadding(Number padding) {
        this.padding = padding;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartPolarTooltip setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartPolarTooltip setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartPolarTooltip setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public String getExtraCssText(){
        return extraCssText;
    }

    public EChartPolarTooltip setExtraCssText(String extraCssText) {
        this.extraCssText = extraCssText;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartPolarTooltip setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public EChartPolarTooltipAxisPointer getAxisPointer(){
        return axisPointer;
    }

    public EChartPolarTooltip setAxisPointer(EChartPolarTooltipAxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }

    public String getTrigger(){
        return trigger;
    }

    public EChartPolarTooltip setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Object getPosition(){
        return position;
    }

    public EChartPolarTooltip setPosition(Object position) {
        this.position = position;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartPolarTooltip setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (formatter != null)  {
            map.put("formatter", formatter);
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
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (extraCssText != null)  {
            map.put("extraCssText", extraCssText);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (axisPointer != null)  {
            map.put("axisPointer", axisPointer.toMap());
        }
        if (trigger != null)  {
            map.put("trigger", trigger);
        }
        if (position != null)  {
            map.put("position", position);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
