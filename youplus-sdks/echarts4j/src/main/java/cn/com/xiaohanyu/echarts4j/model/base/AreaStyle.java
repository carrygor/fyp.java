package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <p>分隔区域的样式设置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class AreaStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Color color;

    private Number shadowBlur;

    private Number opacity;

    private Color shadowColor;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public AreaStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public AreaStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public AreaStyle setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public AreaStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public AreaStyle setOpacity(Number opacity) {
        this.opacity = opacity;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public AreaStyle setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
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
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (opacity != null)  {
            map.put("opacity", opacity);
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
