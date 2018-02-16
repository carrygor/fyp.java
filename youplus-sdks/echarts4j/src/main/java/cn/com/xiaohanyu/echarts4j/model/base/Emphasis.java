package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 强调样式
 * </p>
 *
 * @author 小汉语
 */
public class Emphasis implements Serializable {
    private static final long serialVersionUID = 1L;

    private String borderType;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private Number borderWidth;

    private Number opacity;


    public String getBorderType(){
        return borderType;
    }

    public Emphasis setBorderType(String borderType) {
        this.borderType = borderType;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public Emphasis setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public Emphasis setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public Emphasis setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public Emphasis setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public Emphasis setOpacity(Number opacity) {
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
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
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
