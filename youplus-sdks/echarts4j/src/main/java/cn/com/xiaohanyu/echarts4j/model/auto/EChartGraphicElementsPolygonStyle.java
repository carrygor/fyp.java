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
public class EChartGraphicElementsPolygonStyle implements Serializable {
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

    public EChartGraphicElementsPolygonStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartGraphicElementsPolygonStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartGraphicElementsPolygonStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public String getFill(){
        return fill;
    }

    public EChartGraphicElementsPolygonStyle setFill(String fill) {
        this.fill = fill;
        return this;
    }

    public String getStroke(){
        return stroke;
    }

    public EChartGraphicElementsPolygonStyle setStroke(String stroke) {
        this.stroke = stroke;
        return this;
    }

    public Number getShadowColor(){
        return shadowColor;
    }

    public EChartGraphicElementsPolygonStyle setShadowColor(Number shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Number getLineWidth(){
        return lineWidth;
    }

    public EChartGraphicElementsPolygonStyle setLineWidth(Number lineWidth) {
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
