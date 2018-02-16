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
public class ShadowStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Number shadowOffsetY;

    private Number shadowBlur;

    private Number opacity;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public ShadowStyle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public ShadowStyle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public ShadowStyle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Number getOpacity(){
        return opacity;
    }

    public ShadowStyle setOpacity(Number opacity) {
        this.opacity = opacity;
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

        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
