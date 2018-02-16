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
public class EChartGraphicElementsLineShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number y1;

    private Number x1;

    private Number y2;

    private Number x2;

    private Number percent;


    public Number getY1(){
        return y1;
    }

    public EChartGraphicElementsLineShape setY1(Number y1) {
        this.y1 = y1;
        return this;
    }

    public Number getX1(){
        return x1;
    }

    public EChartGraphicElementsLineShape setX1(Number x1) {
        this.x1 = x1;
        return this;
    }

    public Number getY2(){
        return y2;
    }

    public EChartGraphicElementsLineShape setY2(Number y2) {
        this.y2 = y2;
        return this;
    }

    public Number getX2(){
        return x2;
    }

    public EChartGraphicElementsLineShape setX2(Number x2) {
        this.x2 = x2;
        return this;
    }

    public Number getPercent(){
        return percent;
    }

    public EChartGraphicElementsLineShape setPercent(Number percent) {
        this.percent = percent;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (y1 != null)  {
            map.put("y1", y1);
        }
        if (x1 != null)  {
            map.put("x1", x1);
        }
        if (y2 != null)  {
            map.put("y2", y2);
        }
        if (x2 != null)  {
            map.put("x2", x2);
        }
        if (percent != null)  {
            map.put("percent", percent);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
