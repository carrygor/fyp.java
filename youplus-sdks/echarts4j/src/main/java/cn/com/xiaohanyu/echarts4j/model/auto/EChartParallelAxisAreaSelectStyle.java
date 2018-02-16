package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>在坐标轴上可以进行框选，这里是一些框选的设置。</p>
 * <p><br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartParallelAxisAreaSelectStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Color borderColor;

    private Color color;

    private Number borderWidth;

    private Number width;

    private Number opacity;


    public Color getBorderColor(){
        return borderColor;
    }

    public EChartParallelAxisAreaSelectStyle setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartParallelAxisAreaSelectStyle setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartParallelAxisAreaSelectStyle setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getWidth(){
        return width;
    }

    public EChartParallelAxisAreaSelectStyle setWidth(Number width) {
        this.width = width;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public EChartParallelAxisAreaSelectStyle setOpacity(Number opacity) {
        this.opacity = opacity;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (width != null)  {
            map.put("width", width);
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
