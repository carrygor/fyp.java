package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>环形布局相关配置</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGraphCircular implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean rotateLabel;


    public Boolean getRotateLabel(){
        return rotateLabel;
    }

    public EChartSeriesGraphCircular setRotateLabel(Boolean rotateLabel) {
        this.rotateLabel = rotateLabel;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (rotateLabel != null)  {
            map.put("rotateLabel", rotateLabel);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
