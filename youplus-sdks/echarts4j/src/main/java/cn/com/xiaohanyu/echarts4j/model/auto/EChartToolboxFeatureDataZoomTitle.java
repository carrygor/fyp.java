package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>缩放和还原的标题文本。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureDataZoomTitle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String back;

    private String zoom;


    public String getBack(){
        return back;
    }

    public EChartToolboxFeatureDataZoomTitle setBack(String back) {
        this.back = back;
        return this;
    }

    public String getZoom(){
        return zoom;
    }

    public EChartToolboxFeatureDataZoomTitle setZoom(String zoom) {
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
