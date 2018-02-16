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
public class EChartGraphicElementsCircleShape implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number r;

    private Number cx;

    private Number cy;


    public Number getR(){
        return r;
    }

    public EChartGraphicElementsCircleShape setR(Number r) {
        this.r = r;
        return this;
    }

    public Number getCx(){
        return cx;
    }

    public EChartGraphicElementsCircleShape setCx(Number cx) {
        this.cx = cx;
        return this;
    }

    public Number getCy(){
        return cy;
    }

    public EChartGraphicElementsCircleShape setCy(Number cy) {
        this.cy = cy;
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
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
