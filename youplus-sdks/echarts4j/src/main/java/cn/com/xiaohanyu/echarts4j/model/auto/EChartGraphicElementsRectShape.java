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
public class EChartGraphicElementsRectShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number x;

    private Number width;

    private Number y;

    private Number height;


    public Number getX(){
        return x;
    }

    public EChartGraphicElementsRectShape setX(Number x) {
        this.x = x;
        return this;
    }

    public Number getWidth(){
        return width;
    }

    public EChartGraphicElementsRectShape setWidth(Number width) {
        this.width = width;
        return this;
    }

    public Number getY(){
        return y;
    }

    public EChartGraphicElementsRectShape setY(Number y) {
        this.y = y;
        return this;
    }

    public Number getHeight(){
        return height;
    }

    public EChartGraphicElementsRectShape setHeight(Number height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (x != null)  {
            map.put("x", x);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (y != null)  {
            map.put("y", y);
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
