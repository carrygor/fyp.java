package cn.com.xiaohanyu.echarts4j.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 基本样式
 * </p>
 *
 * @author 小汉语
 */
public class LineStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private Number opacity;

    private Number width;

    private String type;

    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public LineStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public LineStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public LineStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public LineStyle setOpacity(Number opacity) {
        this.opacity = opacity;
        return this;
    }

    public Number getWidth() {
        return width;
    }

    public LineStyle setWidth(Number width) {
        this.width = width;
        return this;
    }

    public String getType() {
        return type;
    }

    public LineStyle setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 组装成对象
     * @return
     */
    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shadowOffsetX", shadowOffsetX);
        map.put("shadowOffsetY", shadowOffsetY);
        map.put("shadowBlur", shadowBlur);
        map.put("opacity", opacity);
        map.put("type", type);
        map.put("width", width);

        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
