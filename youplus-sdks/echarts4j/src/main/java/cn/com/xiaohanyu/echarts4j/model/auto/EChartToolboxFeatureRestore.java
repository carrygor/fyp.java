package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>配置项还原。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureRestore implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean show;

    private Object icon;

    private Boolean title;

    private IconStyle iconStyle;


    public Boolean getShow(){
        return show;
    }

    public EChartToolboxFeatureRestore setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getIcon(){
        return icon;
    }

    public EChartToolboxFeatureRestore setIcon(Object icon) {
        this.icon = icon;
        return this;
    }

    public Boolean getTitle(){
        return title;
    }

    public EChartToolboxFeatureRestore setTitle(Boolean title) {
        this.title = title;
        return this;
    }

    public IconStyle getIconStyle(){
        return iconStyle;
    }

    public EChartToolboxFeatureRestore setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (show != null)  {
            map.put("show", show);
        }
        if (icon != null)  {
            map.put("icon", icon);
        }
        if (title != null)  {
            map.put("title", title);
        }
        if (iconStyle != null)  {
            map.put("iconStyle", iconStyle.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
