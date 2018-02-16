package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>拖拽手柄，适用于触屏的环境。参见 <a href="http://echarts.baidu.com/gallery/editor.html?c=line-tooltip-touch&amp;edit=1&amp;reset=1" target="_blank">例子</a>。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartAngleAxisAxisPointerHandle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number throttle;

    private Number shadowOffsetX;

    private Number margin;

    private Number shadowOffsetY;

    private List size;

    private Color color;

    private Number shadowBlur;

    private Boolean show;

    private Object icon;

    private Color shadowColor;


    public Number getThrottle(){
        return throttle;
    }

    public EChartAngleAxisAxisPointerHandle setThrottle(Number throttle) {
        this.throttle = throttle;
        return this;
    }

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartAngleAxisAxisPointerHandle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getMargin(){
        return margin;
    }

    public EChartAngleAxisAxisPointerHandle setMargin(Number margin) {
        this.margin = margin;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartAngleAxisAxisPointerHandle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public List getSize(){
        return size;
    }

    public EChartAngleAxisAxisPointerHandle setSize(List size) {
        this.size = size;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartAngleAxisAxisPointerHandle setColor(Color color) {
        this.color = color;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartAngleAxisAxisPointerHandle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartAngleAxisAxisPointerHandle setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getIcon(){
        return icon;
    }

    public EChartAngleAxisAxisPointerHandle setIcon(Object icon) {
        this.icon = icon;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public EChartAngleAxisAxisPointerHandle setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (throttle != null)  {
            map.put("throttle", throttle);
        }
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (margin != null)  {
            map.put("margin", margin);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (size != null)  {
            map.put("size", size);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (icon != null)  {
            map.put("icon", icon);
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
