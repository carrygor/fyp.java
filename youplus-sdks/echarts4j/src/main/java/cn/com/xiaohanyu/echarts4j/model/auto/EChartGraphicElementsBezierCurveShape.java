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
public class EChartGraphicElementsBezierCurveShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number y1;

    private Number cpx1;

    private Number cpy2;

    private Number x1;

    private Number y2;

    private Number cpx2;

    private Number x2;

    private Number cpy1;

    private Number percent;


    public Number getY1(){
        return y1;
    }

    public EChartGraphicElementsBezierCurveShape setY1(Number y1) {
        this.y1 = y1;
        return this;
    }

    public Number getCpx1(){
        return cpx1;
    }

    public EChartGraphicElementsBezierCurveShape setCpx1(Number cpx1) {
        this.cpx1 = cpx1;
        return this;
    }

    public Number getCpy2(){
        return cpy2;
    }

    public EChartGraphicElementsBezierCurveShape setCpy2(Number cpy2) {
        this.cpy2 = cpy2;
        return this;
    }

    public Number getX1(){
        return x1;
    }

    public EChartGraphicElementsBezierCurveShape setX1(Number x1) {
        this.x1 = x1;
        return this;
    }

    public Number getY2(){
        return y2;
    }

    public EChartGraphicElementsBezierCurveShape setY2(Number y2) {
        this.y2 = y2;
        return this;
    }

    public Number getCpx2(){
        return cpx2;
    }

    public EChartGraphicElementsBezierCurveShape setCpx2(Number cpx2) {
        this.cpx2 = cpx2;
        return this;
    }

    public Number getX2(){
        return x2;
    }

    public EChartGraphicElementsBezierCurveShape setX2(Number x2) {
        this.x2 = x2;
        return this;
    }

    public Number getCpy1(){
        return cpy1;
    }

    public EChartGraphicElementsBezierCurveShape setCpy1(Number cpy1) {
        this.cpy1 = cpy1;
        return this;
    }

    public Number getPercent(){
        return percent;
    }

    public EChartGraphicElementsBezierCurveShape setPercent(Number percent) {
        this.percent = percent;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (y1 != null)  {
            map.put("y1", y1);
        }
        if (cpx1 != null)  {
            map.put("cpx1", cpx1);
        }
        if (cpy2 != null)  {
            map.put("cpy2", cpy2);
        }
        if (x1 != null)  {
            map.put("x1", x1);
        }
        if (y2 != null)  {
            map.put("y2", y2);
        }
        if (cpx2 != null)  {
            map.put("cpx2", cpx2);
        }
        if (x2 != null)  {
            map.put("x2", x2);
        }
        if (cpy1 != null)  {
            map.put("cpy1", cpy1);
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
