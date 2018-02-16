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
public class EChartGraphicElementsRingShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number r;

    private Number cx;

    private Number cy;

    private Number r0;


    public Number getR(){
        return r;
    }

    public EChartGraphicElementsRingShape setR(Number r) {
        this.r = r;
        return this;
    }

    public Number getCx(){
        return cx;
    }

    public EChartGraphicElementsRingShape setCx(Number cx) {
        this.cx = cx;
        return this;
    }

    public Number getCy(){
        return cy;
    }

    public EChartGraphicElementsRingShape setCy(Number cy) {
        this.cy = cy;
        return this;
    }

    public Number getR0(){
        return r0;
    }

    public EChartGraphicElementsRingShape setR0(Number r0) {
        this.r0 = r0;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (r != null)  {
            map.put("r", r);
        }
        if (cx != null)  {
            map.put("cx", cx);
        }
        if (cy != null)  {
            map.put("cy", cy);
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
