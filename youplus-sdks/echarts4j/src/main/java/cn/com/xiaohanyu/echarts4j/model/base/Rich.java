package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 富文本
 * </p>
 *
 * @author 小汉语
 */
public class Rich implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private String align;

    private String fontFamily;

    private Number borderWidth;

    private Number textShadowOffsetX;

    private Number textShadowOffsetY;

    private String fontWeight;

    private Object height;

    private String verticalAlign;

    private List padding;

    private Number textBorderWidth;

    private String fontStyle;

    private List borderRadius;

    private Object width;

    private Number fontSize;

    private Number lineHeight;

    private Number textShadowBlur;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public Rich setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public Rich setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public Rich setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public Rich setAlign(String align) {
        this.align = align;
        return this;
    }

    public String getFontFamily(){
        return fontFamily;
    }

    public Rich setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public Rich setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getTextShadowOffsetX(){
        return textShadowOffsetX;
    }

    public Rich setTextShadowOffsetX(Number textShadowOffsetX) {
        this.textShadowOffsetX = textShadowOffsetX;
        return this;
    }

    public Number getTextShadowOffsetY(){
        return textShadowOffsetY;
    }

    public Rich setTextShadowOffsetY(Number textShadowOffsetY) {
        this.textShadowOffsetY = textShadowOffsetY;
        return this;
    }

    public String getFontWeight(){
        return fontWeight;
    }

    public Rich setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public Rich setHeight(Object height) {
        this.height = height;
        return this;
    }

    public String getVerticalAlign(){
        return verticalAlign;
    }

    public Rich setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public Rich setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Number getTextBorderWidth(){
        return textBorderWidth;
    }

    public Rich setTextBorderWidth(Number textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
        return this;
    }

    public String getFontStyle(){
        return fontStyle;
    }

    public Rich setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public List getBorderRadius(){
        return borderRadius;
    }

    public Rich setBorderRadius(List borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public Rich setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getFontSize(){
        return fontSize;
    }

    public Rich setFontSize(Number fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Number getLineHeight(){
        return lineHeight;
    }

    public Rich setLineHeight(Number lineHeight) {
        this.lineHeight = lineHeight;
        return this;
    }

    public Number getTextShadowBlur(){
        return textShadowBlur;
    }

    public Rich setTextShadowBlur(Number textShadowBlur) {
        this.textShadowBlur = textShadowBlur;
        return this;
    }


    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (align != null)  {
            map.put("align", align);
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
        if (fontWeight != null)  {
            map.put("fontWeight", fontWeight);
        }
        if (height != null)  {
            map.put("height", height);
        }
        if (verticalAlign != null)  {
            map.put("verticalAlign", verticalAlign);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (textBorderWidth != null)  {
            map.put("textBorderWidth", textBorderWidth);
        }
        if (fontStyle != null)  {
            map.put("fontStyle", fontStyle);
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
