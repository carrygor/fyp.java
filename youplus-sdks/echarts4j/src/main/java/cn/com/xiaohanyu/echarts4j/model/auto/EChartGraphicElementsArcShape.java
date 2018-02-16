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
public class EChartGraphicElementsArcShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number r;

    private Number startAngle;

    private Number cx;

    private Number cy;

    private Boolean clockwise;

    private Number endAngle;

    private Number r0;


    public Number getR(){
        return r;
    }

    public EChartGraphicElementsArcShape setR(Number r) {
        this.r = r;
        return this;
    }

    public Number getStartAngle(){
        return startAngle;
    }

    public EChartGraphicElementsArcShape setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public Number getCx(){
        return cx;
    }

    public EChartGraphicElementsArcShape setCx(Number cx) {
        this.cx = cx;
        return this;
    }

    public Number getCy(){
        return cy;
    }

    public EChartGraphicElementsArcShape setCy(Number cy) {
        this.cy = cy;
        return this;
    }

    public Boolean getClockwise(){
        return clockwise;
    }

    public EChartGraphicElementsArcShape setClockwise(Boolean clockwise) {
        this.clockwise = clockwise;
        return this;
    }

    public Number getEndAngle(){
        return endAngle;
    }

    public EChartGraphicElementsArcShape setEndAngle(Number endAngle) {
        this.endAngle = endAngle;
        return this;
    }

    public Number getR0(){
        return r0;
    }

    public EChartGraphicElementsArcShape setR0(Number r0) {
        this.r0 = r0;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (r != null)  {
            map.put("r", r);
        }
        if (startAngle != null)  {
            map.put("startAngle", startAngle);
        }
        if (cx != null)  {
            map.put("cx", cx);
        }
        if (cy != null)  {
            map.put("cy", cy);
        }
        if (clockwise != null)  {
            map.put("clockwise", clockwise);
        }
        if (endAngle != null)  {
            map.put("endAngle", endAngle);
        }
        if (r0 != null)  {
            map.put("r0", r0);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
