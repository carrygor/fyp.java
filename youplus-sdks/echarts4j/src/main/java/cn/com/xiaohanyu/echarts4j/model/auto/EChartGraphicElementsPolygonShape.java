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
public class EChartGraphicElementsPolygonShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean smoothConstraint;

    private List points;

    private Object smooth;


    public Boolean getSmoothConstraint(){
        return smoothConstraint;
    }

    public EChartGraphicElementsPolygonShape setSmoothConstraint(Boolean smoothConstraint) {
        this.smoothConstraint = smoothConstraint;
        return this;
    }

    public List getPoints(){
        return points;
    }

    public EChartGraphicElementsPolygonShape setPoints(List points) {
        this.points = points;
        return this;
    }

    public Object getSmooth(){
        return smooth;
    }

    public EChartGraphicElementsPolygonShape setSmooth(Object smooth) {
        this.smooth = smooth;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (smoothConstraint != null)  {
            map.put("smoothConstraint", smoothConstraint);
        }
        if (points != null)  {
            map.put("points", points);
        }
        if (smooth != null)  {
            map.put("smooth", smooth);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
