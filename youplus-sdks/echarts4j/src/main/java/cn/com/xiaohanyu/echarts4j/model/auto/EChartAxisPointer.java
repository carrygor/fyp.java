package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>这是坐标轴指示器（axisPointer）的全局公用设置。</p>
 * <hr>
 * <p>坐标轴指示器是指示坐标轴当前刻度的工具。</p>
 * <p>如下例，鼠标悬浮到图上，可以出现标线和刻度文本。</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/candlestick-axisPointer&edit=1&reset=1" width="600" height="450" ></iframe>
 * 
 * 
 * <p>上例中，使用了 <a href="#axisPointer.link">axisPointer.link</a> 来关联不同的坐标系中的 axisPointer。</p>
 * <p>坐标轴指示器也有适合触屏的交互方式，如下：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=line-tooltip-touch&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * <p>坐标轴指示器在多轴的场景能起到辅助作用：</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=multiple-y-axis&edit=1&reset=1" width="600" height="300" ></iframe>
 * 
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=multiple-x-axis&edit=1&reset=1" width="600" height="300" ></iframe>
 * 
 * 
 * 
 * 
 * <hr>
 * <blockquote>
 * <p><strong>注意：</strong>
 * 一般来说，axisPointer 的具体配置项会配置在各个轴中（如 <a href="#xAxis.axisPointer">xAxis.axisPointer</a>）或者 <code>tooltip</code> 中（如 <a href="#tooltip.axisPointer">tooltip.axisPointer</a>）。</p>
 * <p>但是这几个选项只能配置在全局的 axisPointer 中：<a href="#axisPointer.triggerOn">axisPointer.triggerOn</a>、<a href="#axisPointer.link">axisPointer.link</a>。</p>
 * </blockquote>
 * <hr>
 * <p><strong>如何显示 axisPointer：</strong></p>
 * <p>直角坐标系 <a href="#grid">grid</a>、极坐标系 <a href="#polar">polar</a>、单轴坐标系 <a href="#single">single</a> 中的每个轴都自己的 axisPointer。</p>
 * <p>他们的 axisPointer 默认不显示。有两种方法可以让他们显示：</p>
 * <ul>
 * <li><p>设置轴上的 <code>axisPointer.show</code>（例如 <a href="#xAxis.axisPointer.show">xAxis.axisPointer.show</a>）为 <code>true</code>，则显示此轴的 axisPointer。</p>
 * </li>
 * <li><p>设置 <a href="#tooltip.trigger">tooltip.trigger</a> 设置为 <code>&#39;axis&#39;</code> 或者 <a href="#tooltip.axisPointer.type">tooltip.axisPointer.type</a> 设置为 <code>&#39;cross&#39;</code>，则此时坐标系会自动选择显示哪个轴的 axisPointer，也可以使用 <a href="#tooltip.axisPointer.axis">tooltip.axisPointer.axis</a> 改变这种选择。注意，轴上如果设置了 axisPointer，会覆盖此设置。</p>
 * </li>
 * </ul>
 * <hr>
 * <p><strong>如何显示 axisPointer 的 label：</strong></p>
 * <p>axisPointer 的 label 默认不显示（也就是默认只显示指示线），除非：</p>
 * <ul>
 * <li><p>设置轴上的 <code>axisPointer.label.show</code>（例如 <a href="#xAxis.axisPointer.show">xAxis.axisPointer.label.show</a>）为 <code>true</code>，则显示此轴的 axisPointer 的 label。</p>
 * </li>
 * <li><p>设置 <a href="#tooltip.axisPointer.type">tooltip.axisPointer.type</a> 为 <code>&#39;cross&#39;</code> 时会自动显示 axisPointer 的 label。</p>
 * </li>
 * </ul>
 * <hr>
 * <p><strong>关于触屏的 axisPointer 的设置</strong></p>
 * <p>设置轴上的 <code>axisPointer.handle.show</code>（例如 <a href="#xAxis.axisPointer.handle.show">xAxis.axisPointer.handle.show</a> 为 <code>true</code> 则会显示出此 axisPointer 的拖拽按钮。（polar 坐标系暂不支持此功能）。</p>
 * <p><strong>注意：</strong>
 * 如果发现此时 tooltip 效果不良好，可设置 <a href="#tooltip.triggerOn">tooltip.triggerOn</a> 为 <code>&#39;none&#39;</code>（于是效果为：手指按住按钮则显示 tooltip，松开按钮则隐藏 tooltip），或者 <a href="#tooltip.alwaysShowContent">tooltip.alwaysShowContent</a> 为 <code>true</code>（效果为 tooltip 一直显示）。</p>
 * <p>参见<a href="http://echarts.baidu.com/gallery/editor.html?c=line-tooltip-touch&amp;edit=1&amp;reset=1" target="_blank">例子</a>。</p>
 * <hr>
 * <p><strong>自动吸附到数据（snap）</strong></p>
 * <p>对于数值轴、时间轴，如果开启了 <a href="#xAxis.axisPointer.snap">snap</a>，则 axisPointer 会自动吸附到最近的点上。</p>
 * <hr>
 * <hr>
 * </p>
 *
 * @author 小汉语
 */
public class EChartAxisPointer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String triggerOn;

    private Boolean triggerTooltip;

    private Boolean show;

    private List link;

    private EChartAxisPointerHandle handle;

    private EChartAxisPointerLabel label;

    private String type;

    private ShadowStyle shadowStyle;

    private LineStyle lineStyle;

    private Number z;

    private Number value;

    private Boolean snap;

    private Boolean status;


    public String getTriggerOn(){
        return triggerOn;
    }

    public EChartAxisPointer setTriggerOn(String triggerOn) {
        this.triggerOn = triggerOn;
        return this;
    }

    public Boolean getTriggerTooltip(){
        return triggerTooltip;
    }

    public EChartAxisPointer setTriggerTooltip(Boolean triggerTooltip) {
        this.triggerTooltip = triggerTooltip;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartAxisPointer setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public List getLink(){
        return link;
    }

    public EChartAxisPointer setLink(List link) {
        this.link = link;
        return this;
    }

    public EChartAxisPointerHandle getHandle(){
        return handle;
    }

    public EChartAxisPointer setHandle(EChartAxisPointerHandle handle) {
        this.handle = handle;
        return this;
    }

    public EChartAxisPointerLabel getLabel(){
        return label;
    }

    public EChartAxisPointer setLabel(EChartAxisPointerLabel label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartAxisPointer setType(String type) {
        this.type = type;
        return this;
    }

    public ShadowStyle getShadowStyle(){
        return shadowStyle;
    }

    public EChartAxisPointer setShadowStyle(ShadowStyle shadowStyle) {
        this.shadowStyle = shadowStyle;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartAxisPointer setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartAxisPointer setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartAxisPointer setValue(Number value) {
        this.value = value;
        return this;
    }

    public Boolean getSnap(){
        return snap;
    }

    public EChartAxisPointer setSnap(Boolean snap) {
        this.snap = snap;
        return this;
    }

    public Boolean getStatus(){
        return status;
    }

    public EChartAxisPointer setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (triggerOn != null)  {
            map.put("triggerOn", triggerOn);
        }
        if (triggerTooltip != null)  {
            map.put("triggerTooltip", triggerTooltip);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (link != null)  {
            map.put("link", link);
        }
        if (handle != null)  {
            map.put("handle", handle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (shadowStyle != null)  {
            map.put("shadowStyle", shadowStyle.toMap());
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (value != null)  {
            map.put("value", value);
        }
        if (snap != null)  {
            map.put("snap", snap);
        }
        if (status != null)  {
            map.put("status", status);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
