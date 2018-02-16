package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>标题组件，包含主标题和副标题。</p>
 * <p>在 ECharts 2.x 中单个 ECharts 实例最多只能拥有一个标题组件。但是在 ECharts 3 中可以存在任意多个标题组件，这在需要标题进行排版，或者单个实例中的多个图表都需要标题时会比较有用。</p>
 * <p><strong>例如下面不同缓动函数效果的示例，每一个缓动效果图都带有一个标题组件：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=line-easing&edit=1&reset=1" width="700" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartTitle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number shadowOffsetX;

    private Color borderColor;

    private Number shadowOffsetY;

    private String subtext;

    private Number shadowBlur;

    private Boolean show;

    private String link;

    private String sublink;

    private Object top;

    private Number borderWidth;

    private String subtarget;

    private Number zlevel;

    private String text;

    private Color shadowColor;

    private Number padding;

    private Number itemGap;

    private Color backgroundColor;

    private Object bottom;

    private EChartTitleSubtextStyle subtextStyle;

    private Object right;

    private String target;

    private List borderRadius;

    private Object left;

    private Number z;

    private TextStyle textStyle;


    public Number getShadowOffsetX(){
        return shadowOffsetX;
    }

    public EChartTitle setShadowOffsetX(Number shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public EChartTitle setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Number getShadowOffsetY(){
        return shadowOffsetY;
    }

    public EChartTitle setShadowOffsetY(Number shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    public String getSubtext(){
        return subtext;
    }

    public EChartTitle setSubtext(String subtext) {
        this.subtext = subtext;
        return this;
    }

    public Number getShadowBlur(){
        return shadowBlur;
    }

    public EChartTitle setShadowBlur(Number shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartTitle setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public String getLink(){
        return link;
    }

    public EChartTitle setLink(String link) {
        this.link = link;
        return this;
    }

    public String getSublink(){
        return sublink;
    }

    public EChartTitle setSublink(String sublink) {
        this.sublink = sublink;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartTitle setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getBorderWidth(){
        return borderWidth;
    }

    public EChartTitle setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public String getSubtarget(){
        return subtarget;
    }

    public EChartTitle setSubtarget(String subtarget) {
        this.subtarget = subtarget;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartTitle setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getText(){
        return text;
    }

    public EChartTitle setText(String text) {
        this.text = text;
        return this;
    }

    public Color getShadowColor(){
        return shadowColor;
    }

    public EChartTitle setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Number getPadding(){
        return padding;
    }

    public EChartTitle setPadding(Number padding) {
        this.padding = padding;
        return this;
    }

    public Number getItemGap(){
        return itemGap;
    }

    public EChartTitle setItemGap(Number itemGap) {
        this.itemGap = itemGap;
        return this;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public EChartTitle setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartTitle setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public EChartTitleSubtextStyle getSubtextStyle(){
        return subtextStyle;
    }

    public EChartTitle setSubtextStyle(EChartTitleSubtextStyle subtextStyle) {
        this.subtextStyle = subtextStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartTitle setRight(Object right) {
        this.right = right;
        return this;
    }

    public String getTarget(){
        return target;
    }

    public EChartTitle setTarget(String target) {
        this.target = target;
        return this;
    }

    public List getBorderRadius(){
        return borderRadius;
    }

    public EChartTitle setBorderRadius(List borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartTitle setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartTitle setZ(Number z) {
        this.z = z;
        return this;
    }

    public TextStyle getTextStyle(){
        return textStyle;
    }

    public EChartTitle setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (shadowOffsetX != null)  {
            map.put("shadowOffsetX", shadowOffsetX);
        }
        if (borderColor != null)  {
            map.put("borderColor", borderColor);
        }
        if (shadowOffsetY != null)  {
            map.put("shadowOffsetY", shadowOffsetY);
        }
        if (subtext != null)  {
            map.put("subtext", subtext);
        }
        if (shadowBlur != null)  {
            map.put("shadowBlur", shadowBlur);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (link != null)  {
            map.put("link", link);
        }
        if (sublink != null)  {
            map.put("sublink", sublink);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (borderWidth != null)  {
            map.put("borderWidth", borderWidth);
        }
        if (subtarget != null)  {
            map.put("subtarget", subtarget);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (text != null)  {
            map.put("text", text);
        }
        if (shadowColor != null)  {
            map.put("shadowColor", shadowColor);
        }
        if (padding != null)  {
            map.put("padding", padding);
        }
        if (itemGap != null)  {
            map.put("itemGap", itemGap);
        }
        if (backgroundColor != null)  {
            map.put("backgroundColor", backgroundColor);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (subtextStyle != null)  {
            map.put("subtextStyle", subtextStyle.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (target != null)  {
            map.put("target", target);
        }
        if (borderRadius != null)  {
            map.put("borderRadius", borderRadius);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (textStyle != null)  {
            map.put("textStyle", textStyle.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
