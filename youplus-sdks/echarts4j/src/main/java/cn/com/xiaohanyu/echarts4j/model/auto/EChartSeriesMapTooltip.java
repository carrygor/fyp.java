package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>本系列特定的 tooltip 设定。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesMapTooltip implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object formatter;

    private Number padding;

    private Color backgroundColor;

    private Color borderColor;

    private Number borderWidth;

    private String extraCssText;

    private Object position;

    private TextStyle textStyle;


    public Object getFormatter(){
        return formatter;
    }

    public EChartSeriesMapTooltip setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public Number getPadding(){
        return padding;
    }

    public EChartSeriesMapTooltip setPadding(Number padding) {
        this.padding = padding;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartSeriesMapTooltip setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartSeriesMapTooltip setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartSeriesMapTooltip setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public String getExtraCssText(){
        return extraCssText;
    }

    public EChartSeriesMapTooltip setExtraCssText(String extraCssText) {
        this.extraCssText = extraCssText;
        return this;
    }

    public Object getPosition(){
        return position;
    }

    public EChartSeriesMapTooltip setPosition(Object position) {
        this.position = position;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartSeriesMapTooltip setTextStyle(TextStyle textStyle) {
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
