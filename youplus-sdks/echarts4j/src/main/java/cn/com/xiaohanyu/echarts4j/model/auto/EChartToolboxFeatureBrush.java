package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>选框组件的控制按钮。</p>
 * <p>也可以不在这里指定，而是在 <a href="#brush.toolbox">brush.toolbox</a> 中指定。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureBrush implements Serializable {
    private static final long serialVersionUID = 1L;

    private EChartToolboxFeatureBrushIcon icon;

    private List type;

    private EChartToolboxFeatureBrushTitle title;


    public EChartToolboxFeatureBrushIcon getIcon(){
        return icon;
    }

    public EChartToolboxFeatureBrush setIcon(EChartToolboxFeatureBrushIcon icon) {
        this.icon = icon;
        return this;
    }

    public List getType(){
        return type;
    }

    public EChartToolboxFeatureBrush setType(List type) {
        this.type = type;
        return this;
    }

    public EChartToolboxFeatureBrushTitle getTitle(){
        return title;
    }

    public EChartToolboxFeatureBrush setTitle(EChartToolboxFeatureBrushTitle title) {
        this.title = title;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (icon != null)  {
            map.put("icon", icon.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (title != null)  {
            map.put("title", title.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
