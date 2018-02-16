package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>手柄的样式配置，见 <a href="http://echarts.baidu.com/gallery/editor.html?c=area-simple" target="_blank">示例 area-simple</a></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartDataZoomSliderHandleStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String borderType;

    private Number shadowOffsetX;

    private Color borderColor;

    private Number shadowOffsetY;

    private Color color;

    private Number shadowBlur;

    private Number borderWidth;

    private Number opacity;

    private Color shadowColor;


    public String getBorderType(){
        return borderType;
    }

    public EChartDataZoomSliderHandleStyle setBorderType(String borderType) {
        this.borderType = borderType;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartDataZoomSliderHandleStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartDataZoomSliderHandleStyle setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartDataZoomSliderHandleStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartDataZoomSliderHandleStyle setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartDataZoomSliderHandleStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartDataZoomSliderHandleStyle setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public EChartDataZoomSliderHandleStyle setOpacity(Number opacity) {
        this.opacity = opacity;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public EChartDataZoomSliderHandleStyle setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
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
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
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
