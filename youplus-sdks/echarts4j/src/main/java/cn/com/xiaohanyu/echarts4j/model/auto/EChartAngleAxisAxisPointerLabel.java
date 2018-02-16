package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>坐标轴指示器的文本标签。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartAngleAxisAxisPointerLabel implements Serializable {
    private static final long serialVersionUID = 1L;

    private List padding;

    private Number shadowOffsetX;

    private Boolean margin;

    private String backgroundColor;

    private String borderColor;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private Object precision;

    private Boolean show;

    private Object formatter;

    private String borderWidth;

    private TextStyle textStyle;

    private Color shadowColor;


    public List getPadding(){
        return padding;
    }

    public EChartAngleAxisAxisPointerLabel setPadding(List padding) {
        this.padding = padding;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartAngleAxisAxisPointerLabel setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Boolean getMargin(){
        return margin;
    }

    public EChartAngleAxisAxisPointerLabel setMargin(Boolean margin) {
        this.margin = margin;
        return this;
    }

    public String getBackgroundColor(){
        return backgroundColor;
    }

    public EChartAngleAxisAxisPointerLabel setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getBorderColor(){
        return borderColor;
    }

    public EChartAngleAxisAxisPointerLabel setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartAngleAxisAxisPointerLabel setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartAngleAxisAxisPointerLabel setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Object getPrecision(){
        return precision;
    }

    public EChartAngleAxisAxisPointerLabel setPrecision(Object precision) {
        this.precision = precision;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartAngleAxisAxisPointerLabel setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getFormatter(){
        return formatter;
    }

    public EChartAngleAxisAxisPointerLabel setFormatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    public String getBorderWidth(){
        return borderWidth;
    }

    public EChartAngleAxisAxisPointerLabel setBorderWidth(String borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartAngleAxisAxisPointerLabel setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public EChartAngleAxisAxisPointerLabel setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (margin != null)  {
            map.put("margin", margin);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (precision != null)  {
            map.put("precision", precision);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (formatter != null)  {
            map.put("formatter", formatter);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
