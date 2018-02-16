package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 普通样式
 * </p>
 *
 * @author 小汉语
 */
public class Normal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String borderType;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private String textPosition;

    private Number shadowBlur;

    private String textAlign;

    private Number borderWidth;

    private Number opacity;


    public String getBorderType(){
        return borderType;
    }

    public Normal setBorderType(String borderType) {
        this.borderType = borderType;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public Normal setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public Normal setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public String getTextPosition(){
        return textPosition;
    }

    public Normal setTextPosition(String textPosition) {
        this.textPosition = textPosition;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public Normal setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public String getTextAlign(){
        return textAlign;
    }

    public Normal setTextAlign(String textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public Normal setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public Normal setOpacity(Number opacity) {
        this.opacity = opacity;
        return this;
    }


    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (borderType != null)  {
            map.put("borderType", borderType);
        }
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (textPosition != null)  {
            map.put("textPosition", textPosition);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (textAlign != null)  {
            map.put("textAlign", textAlign);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (opacity != null)  {
            map.put("opacity", opacity);
        }

        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
