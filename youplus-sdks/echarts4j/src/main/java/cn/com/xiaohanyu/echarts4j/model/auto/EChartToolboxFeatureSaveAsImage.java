package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>保存为图片。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureSaveAsImage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Color backgroundColor;

    private List excludeComponents;

    private Number pixelRatio;

    private String name;

    private Boolean show;

    private Object icon;

    private String type;

    private Boolean title;

    private IconStyle iconStyle;


    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartToolboxFeatureSaveAsImage setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public List getExcludeComponents(){
        return excludeComponents;
    }

    public EChartToolboxFeatureSaveAsImage setExcludeComponents(List excludeComponents) {
        this.excludeComponents = excludeComponents;
        return this;
    }

    public Number getPixelRatio(){
        return pixelRatio;
    }

    public EChartToolboxFeatureSaveAsImage setPixelRatio(Number pixelRatio) {
        this.pixelRatio = pixelRatio;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartToolboxFeatureSaveAsImage setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartToolboxFeatureSaveAsImage setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getIcon(){
        return icon;
    }

    public EChartToolboxFeatureSaveAsImage setIcon(Object icon) {
        this.icon = icon;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartToolboxFeatureSaveAsImage setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getTitle(){
        return title;
    }

    public EChartToolboxFeatureSaveAsImage setTitle(Boolean title) {
        this.title = title;
        return this;
    }

    public IconStyle getIconStyle(){
        return iconStyle;
    }

    public EChartToolboxFeatureSaveAsImage setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (excludeComponents != null)  {
            map.put("excludeComponents", excludeComponents);
        }
        if (pixelRatio != null)  {
            map.put("pixelRatio", pixelRatio);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (icon != null)  {
            map.put("icon", icon);
        }
        if (type != null)  {
            map.put("type", type);
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
