package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>雷达图每个指示器名称的配置项。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartRadarName implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private String borderColor;

    private Number shadowOffsetY;

    private Color color;

    private Number shadowBlur;

    private Boolean show;

    private String textShadowColor;

    private String fontFamily;

    private Number borderWidth;

    private Number textShadowOffsetX;

    private Number textShadowOffsetY;

    private String shadowColor;

    private String fontWeight;

    private Object height;

    private List padding;

    private Number textBorderWidth;

    private Object backgroundColor;

    private Rich rich;

    private String fontStyle;

    private Object formatter;

    private List borderRadius;

    private Object width;

    private Number fontSize;

    private Number lineHeight;

    private String textBorderColor;

    private Number textShadowBlur;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartRadarName setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public String getBorderColor(){
        return borderColor;
    }

    public EChartRadarName setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartRadarName setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartRadarName setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartRadarName setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartRadarName setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public String getTextShadowColor(){
        return textShadowColor;
    }

    public EChartRadarName setTextShadowColor(String textShadowColor) {
        this.textShadowColor = textShadowColor;
        return this;
    }

    public String getFontFamily(){
        return fontFamily;
    }

    public EChartRadarName setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartRadarName setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getTextShadowOffsetX(){
        return textShadowOffsetX;
    }

    public EChartRadarName setTextShadowOffsetX(Number textShadowOffsetX) {
        this.textShadowOffsetX = textShadowOffsetX;
        return this;
    }

    public Number getTextShadowOffsetY(){
        return textShadowOffsetY;
    }

    public EChartRadarName setTextShadowOffsetY(Number textShadowOffsetY) {
        this.textShadowOffsetY = textShadowOffsetY;
        return this;
    }

    public String getShadowColor(){
        return shadowColor;
    }

    public EChartRadarName setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public String getFontWeight(){
        return fontWeight;
    }

    public EChartRadarName setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartRadarName setHeight(Object height) {
        this.height = height;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public EChartRadarName setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Number getTextBorderWidth(){
        return textBorderWidth;
    }

    public EChartRadarName setTextBorderWidth(Number textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
        return this;
    }

    public Object getBackgroundColor(){
        return backgroundColor;
    }

    public EChartRadarName setBackgroundColor(Object backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Rich getRich(){
        return rich;
    }

    public EChartRadarName setRich(Rich rich) {
        this.rich = rich;
        return this;
    }

    public String getFontStyle(){
        return fontStyle;
    }

    public EChartRadarName setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartRadarName setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public List getBorderRadius(){
        return borderRadius;
    }

    public EChartRadarName setBorderRadius(List borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartRadarName setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getFontSize(){
        return fontSize;
    }

    public EChartRadarName setFontSize(Number fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Number getLineHeight(){
        return lineHeight;
    }

    public EChartRadarName setLineHeight(Number lineHeight) {
        this.lineHeight = lineHeight;
        return this;
    }

    public String getTextBorderColor(){
        return textBorderColor;
    }

    public EChartRadarName setTextBorderColor(String textBorderColor) {
        this.textBorderColor = textBorderColor;
        return this;
    }

    public Number getTextShadowBlur(){
        return textShadowBlur;
    }

    public EChartRadarName setTextShadowBlur(Number textShadowBlur) {
        this.textShadowBlur = textShadowBlur;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (textShadowColor != null)  {
            map.put("textShadowColor", textShadowColor);
        }
        if (fontFamily != null)  {
            map.put("fontFamily", fontFamily);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (textShadowOffsetX != null)  {
            map.put("textShadowOffsetX", textShadowOffsetX);
        }
        if (textShadowOffsetY != null)  {
            map.put("textShadowOffsetY", textShadowOffsetY);
        }
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        if (fontWeight != null)  {
            map.put("fontWeight", fontWeight);
        }
        if (height != null)  {
            map.put("height", height);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (textBorderWidth != null)  {
            map.put("textBorderWidth", textBorderWidth);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (rich != null)  {
            map.put("rich", rich.toMap());
        }
        if (fontStyle != null)  {
            map.put("fontStyle", fontStyle);
        }
        if (formatter != null)  {
            map.put("formatter", formatter);
        }
        if (borderRadius != null)  {
            map.put("borderRadius", borderRadius);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (fontSize != null)  {
            map.put("fontSize", fontSize);
        }
        if (lineHeight != null)  {
            map.put("lineHeight", lineHeight);
        }
        if (textBorderColor != null)  {
            map.put("textBorderColor", textBorderColor);
        }
        if (textShadowBlur != null)  {
            map.put("textShadowBlur", textShadowBlur);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
