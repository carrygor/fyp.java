package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureDataView implements Serializable {
    private static final long serialVersionUID = 1L;

    private String backgroundColor;

    private String buttonColor;

    private Boolean show;

    private Object icon;

    private Object contentToOption;

    private Boolean readOnly;

    private String textareaColor;

    private Boolean title;

    private Object optionToContent;

    private String textColor;

    private IconStyle iconStyle;

    private String buttonTextColor;

    private List lang;

    private String textareaBorderColor;


    public String getBackgroundColor(){
        return backgroundColor;
    }

    public EChartToolboxFeatureDataView setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getButtonColor(){
        return buttonColor;
    }

    public EChartToolboxFeatureDataView setButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartToolboxFeatureDataView setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Object getIcon(){
        return icon;
    }

    public EChartToolboxFeatureDataView setIcon(Object icon) {
        this.icon = icon;
        return this;
    }

    public Object getContentToOption(){
        return contentToOption;
    }

    public EChartToolboxFeatureDataView setContentToOption(Object contentToOption) {
        this.contentToOption = contentToOption;
        return this;
    }

    public Boolean getReadOnly(){
        return readOnly;
    }

    public EChartToolboxFeatureDataView setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public String getTextareaColor(){
        return textareaColor;
    }

    public EChartToolboxFeatureDataView setTextareaColor(String textareaColor) {
        this.textareaColor = textareaColor;
        return this;
    }

    public Boolean getTitle(){
        return title;
    }

    public EChartToolboxFeatureDataView setTitle(Boolean title) {
        this.title = title;
        return this;
    }

    public Object getOptionToContent(){
        return optionToContent;
    }

    public EChartToolboxFeatureDataView setOptionToContent(Object optionToContent) {
        this.optionToContent = optionToContent;
        return this;
    }

    public String getTextColor(){
        return textColor;
    }

    public EChartToolboxFeatureDataView setTextColor(String textColor) {
        this.textColor = textColor;
        return this;
    }

    public IconStyle getIconStyle(){
        return iconStyle;
    }

    public EChartToolboxFeatureDataView setIconStyle(IconStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    public String getButtonTextColor(){
        return buttonTextColor;
    }

    public EChartToolboxFeatureDataView setButtonTextColor(String buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
        return this;
    }

    public List getLang(){
        return lang;
    }

    public EChartToolboxFeatureDataView setLang(List lang) {
        this.lang = lang;
        return this;
    }

    public String getTextareaBorderColor(){
        return textareaBorderColor;
    }

    public EChartToolboxFeatureDataView setTextareaBorderColor(String textareaBorderColor) {
        this.textareaBorderColor = textareaBorderColor;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (buttonColor != null)  {
            map.put("buttonColor", buttonColor);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (icon != null)  {
            map.put("icon", icon);
        }
        if (contentToOption != null)  {
            map.put("contentToOption", contentToOption);
        }
        if (readOnly != null)  {
            map.put("readOnly", readOnly);
        }
        if (textareaColor != null)  {
            map.put("textareaColor", textareaColor);
        }
        if (title != null)  {
            map.put("title", title);
        }
        if (optionToContent != null)  {
            map.put("optionToContent", optionToContent);
        }
        if (textColor != null)  {
            map.put("textColor", textColor);
        }
        if (iconStyle != null)  {
            map.put("iconStyle", iconStyle.toMap());
        }
        if (buttonTextColor != null)  {
            map.put("buttonTextColor", buttonTextColor);
        }
        if (lang != null)  {
            map.put("lang", lang);
        }
        if (textareaBorderColor != null)  {
            map.put("textareaBorderColor", textareaBorderColor);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
