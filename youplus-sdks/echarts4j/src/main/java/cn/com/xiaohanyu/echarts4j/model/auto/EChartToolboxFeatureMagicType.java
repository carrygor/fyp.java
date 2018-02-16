package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>动态类型切换
 * <strong>示例：</strong></p>
 * <pre><code class="lang-js">feature: {
 *     magicType: {
 *         type: [&#39;line&#39;, &#39;bar&#39;, &#39;stack&#39;, &#39;tiled&#39;]
 *     }
 * }
 * </code></pre>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureMagicType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean show;

    private EChartToolboxFeatureMagicTypeIcon icon;

    private List type;

    private EChartToolboxFeatureMagicTypeTitle title;

    private IconStyle iconStyle;

    private EChartToolboxFeatureMagicTypeSeriesIndex seriesIndex;

    private EChartToolboxFeatureMagicTypeOption option;


    public Boolean getShow(){
        return show;
    }

    public EChartToolboxFeatureMagicType setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public EChartToolboxFeatureMagicTypeIcon getIcon(){
        return icon;
    }

    public EChartToolboxFeatureMagicType setIcon(EChartToolboxFeatureMagicTypeIcon icon) {
        this.icon = icon;
        return this;
    }

    public List getType(){
        return type;
    }

    public EChartToolboxFeatureMagicType setType(List type) {
        this.type = type;
        return this;
    }

    public EChartToolboxFeatureMagicTypeTitle getTitle(){
        return title;
    }

    public EChartToolboxFeatureMagicType setTitle(EChartToolboxFeatureMagicTypeTitle title) {
        this.title = title;
        return this;
    }

    public IconStyle getIconStyle(){
        return iconStyle;
    }

    public EChartToolboxFeatureMagicType setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    public EChartToolboxFeatureMagicTypeSeriesIndex getSeriesIndex(){
        return seriesIndex;
    }

    public EChartToolboxFeatureMagicType setSeriesIndex(EChartToolboxFeatureMagicTypeSeriesIndex seriesIndex) {
        this.seriesIndex = seriesIndex;
        return this;
    }

    public EChartToolboxFeatureMagicTypeOption getOption(){
        return option;
    }

    public EChartToolboxFeatureMagicType setOption(EChartToolboxFeatureMagicTypeOption option) {
        this.option = option;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (show != null)  {
            map.put("show", show);
        }
        if (icon != null)  {
            map.put("icon", icon.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (title != null)  {
            map.put("title", title.toMap());
        }
        if (iconStyle != null)  {
            map.put("iconStyle", iconStyle.toMap());
        }
        if (seriesIndex != null)  {
            map.put("seriesIndex", seriesIndex.toMap());
        }
        if (option != null)  {
            map.put("option", option.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
