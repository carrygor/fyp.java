package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>仪表盘指针。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGaugePointer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean show;

    private Object length;

    private Number width;


    public Boolean getShow(){
        return show;
    }

    public EChartSeriesGaugePointer setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getLength(){
        return length;
    }

    public EChartSeriesGaugePointer setLength(Object length) {
        this.length = length;
        return this;
    }

    public Number getWidth(){
        return width;
    }

    public EChartSeriesGaugePointer setWidth(Number width) {
        this.width = width;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (show != null)  {
            map.put("show", show);
        }
        if (length != null)  {
            map.put("length", length);
        }
        if (width != null)  {
            map.put("width", width);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
