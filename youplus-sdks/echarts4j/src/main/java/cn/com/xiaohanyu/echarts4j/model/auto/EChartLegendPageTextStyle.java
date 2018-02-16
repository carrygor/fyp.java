package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><a href="#legend.type">legend.type</a> 为 <code>&#39;scroll&#39;</code> 时有效。</p>
 * <p>图例页信息的文字样式。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartLegendPageTextStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number textBorderWidth;

    private Color color;

    private String textShadowColor;

    private String fontStyle;

    private String fontFamily;

    private Object width;

    private Number fontSize;

    private Number lineHeight;

    private Number textShadowOffsetX;

    private Number textShadowOffsetY;

    private String fontWeight;

    private Object height;

    private String textBorderColor;

    private Number textShadowBlur;


    public Number getTextBorderWidth(){
        return textBorderWidth;
    }

    public EChartLegendPageTextStyle setTextBorderWidth(Number textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartLegendPageTextStyle setColor(Color color) {
        this.color = color;
        return this;
    }

    public String getTextShadowColor(){
        return textShadowColor;
    }

    public EChartLegendPageTextStyle setTextShadowColor(String textShadowColor) {
        this.textShadowColor = textShadowColor;
        return this;
    }

    public String getFontStyle(){
        return fontStyle;
    }

    public EChartLegendPageTextStyle setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public String getFontFamily(){
        return fontFamily;
    }

    public EChartLegendPageTextStyle setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartLegendPageTextStyle setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getFontSize(){
        return fontSize;
    }

    public EChartLegendPageTextStyle setFontSize(Number fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Number getLineHeight(){
        return lineHeight;
    }

    public EChartLegendPageTextStyle setLineHeight(Number lineHeight) {
        this.lineHeight = lineHeight;
        return this;
    }

    public Number getTextShadowOffsetX(){
        return textShadowOffsetX;
    }

    public EChartLegendPageTextStyle setTextShadowOffsetX(Number textShadowOffsetX) {
        this.textShadowOffsetX = textShadowOffsetX;
        return this;
    }

    public Number getTextShadowOffsetY(){
        return textShadowOffsetY;
    }

    public EChartLegendPageTextStyle setTextShadowOffsetY(Number textShadowOffsetY) {
        this.textShadowOffsetY = textShadowOffsetY;
        return this;
    }

    public String getFontWeight(){
        return fontWeight;
    }

    public EChartLegendPageTextStyle setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartLegendPageTextStyle setHeight(Object height) {
        this.height = height;
        return this;
    }

    public String getTextBorderColor(){
        return textBorderColor;
    }

    public EChartLegendPageTextStyle setTextBorderColor(String textBorderColor) {
        this.textBorderColor = textBorderColor;
        return this;
    }

    public Number getTextShadowBlur(){
        return textShadowBlur;
    }

    public EChartLegendPageTextStyle setTextShadowBlur(Number textShadowBlur) {
        this.textShadowBlur = textShadowBlur;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (textBorderWidth != null)  {
            map.put("textBorderWidth", textBorderWidth);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (textShadowColor != null)  {
            map.put("textShadowColor", textShadowColor);
        }
        if (fontStyle != null)  {
            map.put("fontStyle", fontStyle);
        }
        if (fontFamily != null)  {
            map.put("fontFamily", fontFamily);
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
        if (textShadowOffsetX != null)  {
            map.put("textShadowOffsetX", textShadowOffsetX);
        }
        if (textShadowOffsetY != null)  {
            map.put("textShadowOffsetY", textShadowOffsetY);
        }
        if (fontWeight != null)  {
            map.put("fontWeight", fontWeight);
        }
        if (height != null)  {
            map.put("height", height);
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
