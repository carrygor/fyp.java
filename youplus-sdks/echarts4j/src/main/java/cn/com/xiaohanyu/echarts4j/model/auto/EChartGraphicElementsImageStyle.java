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
public class EChartGraphicElementsImageStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String image;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private Number x;

    private Number width;

    private Number y;

    private String fill;

    private String stroke;

    private Number shadowColor;

    private Number lineWidth;

    private Number height;


    public String getImage(){
        return image;
    }

    public EChartGraphicElementsImageStyle setImage(String image) {
        this.image = image;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartGraphicElementsImageStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartGraphicElementsImageStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartGraphicElementsImageStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getX(){
        return x;
    }

    public EChartGraphicElementsImageStyle setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getWidth(){
        return width;
    }

    public EChartGraphicElementsImageStyle setWidth(Number width) {
        this.width = width;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartGraphicElementsImageStyle setY(Number y) {
        this.y = y;
        return this;
    }

    public String getFill(){
        return fill;
    }

    public EChartGraphicElementsImageStyle setFill(String fill) {
        this.fill = fill;
        return this;
    }

    public String getStroke(){
        return stroke;
    }

    public EChartGraphicElementsImageStyle setStroke(String stroke) {
        this.stroke = stroke;
        return this;
    }

    public Number getShadowColor(){
        return shadowColor;
    }

    public EChartGraphicElementsImageStyle setShadowColor(Number shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Number getLineWidth(){
        return lineWidth;
    }

    public EChartGraphicElementsImageStyle setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public Number getHeight(){
        return height;
    }

    public EChartGraphicElementsImageStyle setHeight(Number height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (image != null)  {
            map.put("image", image);
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
        if (x != null)  {
            map.put("x", x);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (y != null)  {
            map.put("y", y);
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
        if (height != null)  {
            map.put("height", height);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
