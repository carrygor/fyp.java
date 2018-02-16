package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>缩放和还原的 icon path。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureDataZoomIcon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String back;

    private String zoom;


    public String getBack(){
        return back;
    }

    public EChartToolboxFeatureDataZoomIcon setBack(String back) {
        this.back = back;
        return this;
    }

    public String getZoom(){
        return zoom;
    }

    public EChartToolboxFeatureDataZoomIcon setZoom(String zoom) {
        this.zoom = zoom;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (back != null)  {
            map.put("back", back);
        }
        if (zoom != null)  {
            map.put("zoom", zoom);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
