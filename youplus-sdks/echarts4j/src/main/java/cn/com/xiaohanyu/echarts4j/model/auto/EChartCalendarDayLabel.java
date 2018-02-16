package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>设置日历坐标中 星期轴的样式</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartCalendarDayLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private String borderColor;

    private Number shadowOffsetY;

    private Color color;

    private Number shadowBlur;

    private Boolean show;

    private String textShadowColor;

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

    private List nameMap;

    private Number firstDay;

    private List borderRadius;

    private Object width;

    private Number fontSize;

    private Number lineHeight;

    private String position;

    private String textBorderColor;

    private Number textShadowBlur;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartCalendarDayLabel setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public String getBorderColor(){
        return borderColor;
    }

    public EChartCalendarDayLabel setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartCalendarDayLabel setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartCalendarDayLabel setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartCalendarDayLabel setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartCalendarDayLabel setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public String getTextShadowColor(){
        return textShadowColor;
    }

    public EChartCalendarDayLabel setTextShadowColor(String textShadowColor) {
        this.textShadowColor = textShadowColor;
        return this;
    }

    public String getAlign(){
        return align;
    }

    public EChartCalendarDayLabel setAlign(String align) {
        this.align = align;
        return this;
    }

    public String getFontFamily(){
        return fontFamily;
    }

    public EChartCalendarDayLabel setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartCalendarDayLabel setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getTextShadowOffsetX(){
        return textShadowOffsetX;
    }

    public EChartCalendarDayLabel setTextShadowOffsetX(Number textShadowOffsetX) {
        this.textShadowOffsetX = textShadowOffsetX;
        return this;
    }

    public Number getTextShadowOffsetY(){
        return textShadowOffsetY;
    }

    public EChartCalendarDayLabel setTextShadowOffsetY(Number textShadowOffsetY) {
        this.textShadowOffsetY = textShadowOffsetY;
        return this;
    }

    public String getShadowColor(){
        return shadowColor;
    }

    public EChartCalendarDayLabel setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public String getFontWeight(){
        return fontWeight;
    }

    public EChartCalendarDayLabel setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartCalendarDayLabel setHeight(Object height) {
        this.height = height;
        return this;
    }

    public String getVerticalAlign(){
        return verticalAlign;
    }

    public EChartCalendarDayLabel setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
        return this;
    }

    public List getPadding(){
        return padding;
    }

    public EChartCalendarDayLabel setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Number getTextBorderWidth(){
        return textBorderWidth;
    }

    public EChartCalendarDayLabel setTextBorderWidth(Number textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
        return this;
    }

    public Number getMargin(){
        return margin;
    }

    public EChartCalendarDayLabel setMargin(Number margin) {
        this.margin = margin;
        return this;
    }

    public Object getBackgroundColor(){
        return backgroundColor;
    }

    public EChartCalendarDayLabel setBackgroundColor(Object backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Rich getRich(){
        return rich;
    }

    public EChartCalendarDayLabel setRich(Rich rich) {
        this.rich = rich;
        return this;
    }

    public String getFontStyle(){
        return fontStyle;
    }

    public EChartCalendarDayLabel setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public List getNameMap(){
        return nameMap;
    }

    public EChartCalendarDayLabel setNameMap(List nameMap) {
        this.nameMap = nameMap;
        return this;
    }

    public Number getFirstDay(){
        return firstDay;
    }

    public EChartCalendarDayLabel setFirstDay(Number firstDay) {
        this.firstDay = firstDay;
        return this;
    }

    public List getBorderRadius(){
        return borderRadius;
    }

    public EChartCalendarDayLabel setBorderRadius(List borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartCalendarDayLabel setWidth(Object width) {
        this.width = width;
        return this;
    }

    public Number getFontSize(){
        return fontSize;
    }

    public EChartCalendarDayLabel setFontSize(Number fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Number getLineHeight(){
        return lineHeight;
    }

    public EChartCalendarDayLabel setLineHeight(Number lineHeight) {
        this.lineHeight = lineHeight;
        return this;
    }

    public String getPosition(){
        return position;
    }

    public EChartCalendarDayLabel setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getTextBorderColor(){
        return textBorderColor;
    }

    public EChartCalendarDayLabel setTextBorderColor(String textBorderColor) {
        this.textBorderColor = textBorderColor;
        return this;
    }

    public Number getTextShadowBlur(){
        return textShadowBlur;
    }

    public EChartCalendarDayLabel setTextShadowBlur(Number textShadowBlur) {
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
        if (nameMap != null)  {
            map.put("nameMap", nameMap);
        }
        if (firstDay != null)  {
            map.put("firstDay", firstDay);
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
        if (position != null)  {
            map.put("position", position);
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
