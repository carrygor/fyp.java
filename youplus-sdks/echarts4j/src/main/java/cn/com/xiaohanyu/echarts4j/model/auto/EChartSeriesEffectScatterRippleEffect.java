package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>涟漪特效相关配置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesEffectScatterRippleEffect implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number period;

    private String brushType;

    private Number scale;


    public Number getPeriod(){
        return period;
    }

    public EChartSeriesEffectScatterRippleEffect setPeriod(Number period) {
        this.period = period;
        return this;
    }

    public String getBrushType(){
        return brushType;
    }

    public EChartSeriesEffectScatterRippleEffect setBrushType(String brushType) {
        this.brushType = brushType;
        return this;
    }

    public Number getScale(){
        return scale;
    }

    public EChartSeriesEffectScatterRippleEffect setScale(Number scale) {
        this.scale = scale;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (period != null)  {
            map.put("period", period);
        }
        if (brushType != null)  {
            map.put("brushType", brushType);
        }
        if (scale != null)  {
            map.put("scale", scale);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
