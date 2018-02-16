package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小汉语
 */
public class EChartTitleSubtextStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String verticalAlign;

    private Number textBorderWidth;

    private Color color;

    private String textShadowColor;

    private Rich rich;

    private String fontStyle;

    private String align;

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


    public String getVerticalAlign(){
        return verticalAlign;
    }

    public EChartTitleSubtextStyle setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
        return this;
    }

    public Number getTextBorderWidth(){
        return textBorderWidth;
    }

    public EChartTitleSubtextStyle setTextBorderWidth(Number textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartTitleSubtextStyle setColor(Color color) {
        this.color = color;
        return this;
    }

    public String getTextShadowColor(){
        return textShadowColor;
    }

    public EChartTitleSubtextStyle setTextShadowColor(String textShadowColor) {
        this.textShadowColor = textShadowColor;
        return this;
    }

    public Rich getRich(){
        return rich;
    }

    public EChartTitleSubtextStyle setRich(Rich rich) {
        this.rich = rich;
        return this;
    }

    public String getFontStyle(){
        return fontStyle;
    }

    public EChartTitleSubtextStyle setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public EChartTitleSubtextStyle setAlign(String align) {
        this.align = align;
        return this;
    }

    public String getFontFamily(){
        return fontFamily;
    }

    public EChartTitleSubtextStyle setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartTitleSubtextStyle setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getFontSize(){
        return fontSize;
    }

    public EChartTitleSubtextStyle setFontSize(Number fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Number getLineHeight(){
        return lineHeight;
    }

    public EChartTitleSubtextStyle setLineHeight(Number lineHeight) {
        this.lineHeight = lineHeight;
        return this;
    }

    public Number getTextShadowOffsetX(){
        return textShadowOffsetX;
    }

    public EChartTitleSubtextStyle setTextShadowOffsetX(Number textShadowOffsetX) {
        this.textShadowOffsetX = textShadowOffsetX;
        return this;
    }

    public Number getTextShadowOffsetY(){
        return textShadowOffsetY;
    }

    public EChartTitleSubtextStyle setTextShadowOffsetY(Number textShadowOffsetY) {
        this.textShadowOffsetY = textShadowOffsetY;
        return this;
    }

    public String getFontWeight(){
        return fontWeight;
    }

    public EChartTitleSubtextStyle setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartTitleSubtextStyle setHeight(Object height) {
        this.height = height;
        return this;
    }

    public String getTextBorderColor(){
        return textBorderColor;
    }

    public EChartTitleSubtextStyle setTextBorderColor(String textBorderColor) {
        this.textBorderColor = textBorderColor;
        return this;
    }

    public Number getTextShadowBlur(){
        return textShadowBlur;
    }

    public EChartTitleSubtextStyle setTextShadowBlur(Number textShadowBlur) {
        this.textShadowBlur = textShadowBlur;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (verticalAlign != null)  {
            map.put("verticalAlign", verticalAlign);
        }
        if (textBorderWidth != null)  {
            map.put("textBorderWidth", textBorderWidth);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (textShadowColor != null)  {
            map.put("textShadowColor", textShadowColor);
        }
        if (rich != null)  {
            map.put("rich", rich.toMap());
        }
        if (fontStyle != null)  {
            map.put("fontStyle", fontStyle);
        }
        if (align != null)  {
            map.put("align", align);
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
