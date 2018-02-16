package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>坐标轴刻度标签的相关设置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartRadiusAxisAxisLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number rotate;

    private Number shadowOffsetX;

    private String borderColor;

    private Number shadowOffsetY;

    private Color color;

    private Number shadowBlur;

    private Boolean show;

    private Boolean showMinLabel;

    private String textShadowColor;

    private Boolean inside;

    private String align;

    private String fontFamily;

    private Number borderWidth;

    private Number textShadowOffsetX;

    private Number textShadowOffsetY;

    private String shadowColor;

    private String fontWeight;

    private Object height;

    private String verticalAlign;

    private List padding;

    private Number textBorderWidth;

    private Number margin;

    private Object backgroundColor;

    private Rich rich;

    private String fontStyle;

    private Object formatter;

    private List borderRadius;

    private Object width;

    private Object interval;

    private Number fontSize;

    private Number lineHeight;

    private Boolean showMaxLabel;

    private String textBorderColor;

    private Number textShadowBlur;


    public Number getRotate(){
        return rotate;
    }

    public EChartRadiusAxisAxisLabel setRotate(Number rotate) {
        this.rotate = rotate;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartRadiusAxisAxisLabel setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public String getBorderColor(){
        return borderColor;
    }

    public EChartRadiusAxisAxisLabel setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartRadiusAxisAxisLabel setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartRadiusAxisAxisLabel setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartRadiusAxisAxisLabel setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartRadiusAxisAxisLabel setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Boolean getShowMinLabel(){
        return showMinLabel;
    }

    public EChartRadiusAxisAxisLabel setShowMinLabel(Boolean showMinLabel) {
        this.showMinLabel = showMinLabel;
        return this;
    }

    public String getTextShadowColor(){
        return textShadowColor;
    }

    public EChartRadiusAxisAxisLabel setTextShadowColor(String textShadowColor) {
        this.textShadowColor = textShadowColor;
        return this;
    }

    public Boolean getInside(){
        return inside;
    }

    public EChartRadiusAxisAxisLabel setInside(Boolean inside) {
        this.inside = inside;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public EChartRadiusAxisAxisLabel setAlign(String align) {
        this.align = align;
        return this;
    }

    public String getFontFamily(){
        return fontFamily;
    }

    public EChartRadiusAxisAxisLabel setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartRadiusAxisAxisLabel setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getTextShadowOffsetX(){
        return textShadowOffsetX;
    }

    public EChartRadiusAxisAxisLabel setTextShadowOffsetX(Number textShadowOffsetX) {
        this.textShadowOffsetX = textShadowOffsetX;
        return this;
    }

    public Number getTextShadowOffsetY(){
        return textShadowOffsetY;
    }

    public EChartRadiusAxisAxisLabel setTextShadowOffsetY(Number textShadowOffsetY) {
        this.textShadowOffsetY = textShadowOffsetY;
        return this;
    }

    public String getShadowColor(){
        return shadowColor;
    }

    public EChartRadiusAxisAxisLabel setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public String getFontWeight(){
        return fontWeight;
    }

    public EChartRadiusAxisAxisLabel setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartRadiusAxisAxisLabel setHeight(Object height) {
        this.height = height;
        return this;
    }

    public String getVerticalAlign(){
        return verticalAlign;
    }

    public EChartRadiusAxisAxisLabel setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public EChartRadiusAxisAxisLabel setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Number getTextBorderWidth(){
        return textBorderWidth;
    }

    public EChartRadiusAxisAxisLabel setTextBorderWidth(Number textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
        return this;
    }

    public Number getMargin(){
        return margin;
    }

    public EChartRadiusAxisAxisLabel setMargin(Number margin) {
        this.margin = margin;
        return this;
    }

    public Object getBackgroundColor(){
        return backgroundColor;
    }

    public EChartRadiusAxisAxisLabel setBackgroundColor(Object backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Rich getRich(){
        return rich;
    }

    public EChartRadiusAxisAxisLabel setRich(Rich rich) {
        this.rich = rich;
        return this;
    }

    public String getFontStyle(){
        return fontStyle;
    }

    public EChartRadiusAxisAxisLabel setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartRadiusAxisAxisLabel setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public List getBorderRadius(){
        return borderRadius;
    }

    public EChartRadiusAxisAxisLabel setBorderRadius(List borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartRadiusAxisAxisLabel setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Object getInterval(){
        return interval;
    }

    public EChartRadiusAxisAxisLabel setInterval(Object interval) {
        this.interval = interval;
        return this;
    }

    public Number getFontSize(){
        return fontSize;
    }

    public EChartRadiusAxisAxisLabel setFontSize(Number fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Number getLineHeight(){
        return lineHeight;
    }

    public EChartRadiusAxisAxisLabel setLineHeight(Number lineHeight) {
        this.lineHeight = lineHeight;
        return this;
    }

    public Boolean getShowMaxLabel(){
        return showMaxLabel;
    }

    public EChartRadiusAxisAxisLabel setShowMaxLabel(Boolean showMaxLabel) {
        this.showMaxLabel = showMaxLabel;
        return this;
    }

    public String getTextBorderColor(){
        return textBorderColor;
    }

    public EChartRadiusAxisAxisLabel setTextBorderColor(String textBorderColor) {
        this.textBorderColor = textBorderColor;
        return this;
    }

    public Number getTextShadowBlur(){
        return textShadowBlur;
    }

    public EChartRadiusAxisAxisLabel setTextShadowBlur(Number textShadowBlur) {
        this.textShadowBlur = textShadowBlur;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (rotate != null)  {
            map.put("rotate", rotate);
        }
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
        if (showMinLabel != null)  {
            map.put("showMinLabel", showMinLabel);
        }
        if (textShadowColor != null)  {
            map.put("textShadowColor", textShadowColor);
        }
        if (inside != null)  {
            map.put("inside", inside);
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
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
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
        if (margin != null)  {
            map.put("margin", margin);
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
        if (interval != null)  {
            map.put("interval", interval);
        }
        if (fontSize != null)  {
            map.put("fontSize", fontSize);
        }
        if (lineHeight != null)  {
            map.put("lineHeight", lineHeight);
        }
        if (showMaxLabel != null)  {
            map.put("showMaxLabel", showMaxLabel);
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
