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
public class EChartGraphicElementsTextStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private String textAlign;

    private Number shadowBlur;

    private String textVerticalAlign;

    private String fill;

    private String stroke;

    private Number lineWidth;

    private Number x;

    private Number y;

    private String text;

    private Number shadowColor;

    private String font;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartGraphicElementsTextStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartGraphicElementsTextStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public String getTextAlign(){
        return textAlign;
    }

    public EChartGraphicElementsTextStyle setTextAlign(String textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartGraphicElementsTextStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public String getTextVerticalAlign(){
        return textVerticalAlign;
    }

    public EChartGraphicElementsTextStyle setTextVerticalAlign(String textVerticalAlign) {
        this.textVerticalAlign = textVerticalAlign;
        return this;
    }

    public String getFill(){
        return fill;
    }

    public EChartGraphicElementsTextStyle setFill(String fill) {
        this.fill = fill;
        return this;
    }

    public String getStroke(){
        return stroke;
    }

    public EChartGraphicElementsTextStyle setStroke(String stroke) {
        this.stroke = stroke;
        return this;
    }

    public Number getLineWidth(){
        return lineWidth;
    }

    public EChartGraphicElementsTextStyle setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public Number getX(){
        return x;
    }

    public EChartGraphicElementsTextStyle setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartGraphicElementsTextStyle setY(Number y) {
        this.y = y;
        return this;
    }

    public String getText(){
        return text;
    }

    public EChartGraphicElementsTextStyle setText(String text) {
        this.text = text;
        return this;
    }

    public Number getShadowColor(){
        return shadowColor;
    }

    public EChartGraphicElementsTextStyle setShadowColor(Number shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public String getFont(){
        return font;
    }

    public EChartGraphicElementsTextStyle setFont(String font) {
        this.font = font;
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
        if (textAlign != null)  {
            map.put("textAlign", textAlign);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (textVerticalAlign != null)  {
            map.put("textVerticalAlign", textVerticalAlign);
        }
        if (fill != null)  {
            map.put("fill", fill);
        }
        if (stroke != null)  {
            map.put("stroke", stroke);
        }
        if (lineWidth != null)  {
            map.put("lineWidth", lineWidth);
        }
        if (x != null)  {
            map.put("x", x);
        }
        if (y != null)  {
            map.put("y", y);
        }
        if (text != null)  {
            map.put("text", text);
        }
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        if (font != null)  {
            map.put("font", font);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
