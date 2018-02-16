package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>每个按钮的 icon path。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureBrushIcon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rect;

    private String polygon;

    private String lineY;

    private String lineX;

    private String keep;

    private String clear;


    public String getRect(){
        return rect;
    }

    public EChartToolboxFeatureBrushIcon setRect(String rect) {
        this.rect = rect;
        return this;
    }

    public String getPolygon(){
        return polygon;
    }

    public EChartToolboxFeatureBrushIcon setPolygon(String polygon) {
        this.polygon = polygon;
        return this;
    }

    public String getLineY(){
        return lineY;
    }

    public EChartToolboxFeatureBrushIcon setLineY(String lineY) {
        this.lineY = lineY;
        return this;
    }

    public String getLineX(){
        return lineX;
    }

    public EChartToolboxFeatureBrushIcon setLineX(String lineX) {
        this.lineX = lineX;
        return this;
    }

    public String getKeep(){
        return keep;
    }

    public EChartToolboxFeatureBrushIcon setKeep(String keep) {
        this.keep = keep;
        return this;
    }

    public String getClear(){
        return clear;
    }

    public EChartToolboxFeatureBrushIcon setClear(String clear) {
        this.clear = clear;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (rect != null)  {
            map.put("rect", rect);
        }
        if (polygon != null)  {
            map.put("polygon", polygon);
        }
        if (lineY != null)  {
            map.put("lineY", lineY);
        }
        if (lineX != null)  {
            map.put("lineX", lineX);
        }
        if (keep != null)  {
            map.put("keep", keep);
        }
        if (clear != null)  {
            map.put("clear", clear);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
