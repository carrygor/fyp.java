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
public class EChartGraphicElementsCircleStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private String fill;

    private String stroke;

    private Number shadowColor;

    private Number lineWidth;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartGraphicElementsCircleStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartGraphicElementsCircleStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartGraphicElementsCircleStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public String getFill(){
        return fill;
    }

    public EChartGraphicElementsCircleStyle setFill(String fill) {
        this.fill = fill;
        return this;
    }

    public String getStroke(){
        return stroke;
    }

    public EChartGraphicElementsCircleStyle setStroke(String stroke) {
        this.stroke = stroke;
        return this;
    }

    public Number getShadowColor(){
        return shadowColor;
    }

    public EChartGraphicElementsCircleStyle setShadowColor(Number shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Number getLineWidth(){
        return lineWidth;
    }

    public EChartGraphicElementsCircleStyle setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
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
        if (fill != null)  {
            map.put("fill", fill);
        }
        if (stroke != null)  {
            map.put("stroke", stroke);
        }
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        if (lineWidth != null)  {
            map.put("lineWidth", lineWidth);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
