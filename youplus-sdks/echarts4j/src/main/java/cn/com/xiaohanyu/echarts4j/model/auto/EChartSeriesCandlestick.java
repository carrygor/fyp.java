package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><a href="https://en.wikipedia.org/wiki/Candlestick_chart" target="_blank">Candlestick</a> 即我们常说的 <code>K线图</code>。</p>
 * <p>在 ECharts3 中，同时支持 <code>&#39;candlestick&#39;</code> 和 <code>&#39;k&#39;</code>这两种 <code>&#39;series.type&#39;</code>（<code>&#39;k&#39;</code> 会被自动转为 <code>&#39;candlestick&#39;</code>）。</p>
 * <p><strong>示例如下：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=candlestick-sh&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * 
 * <p><br>
 * <strong>关于『涨』『跌』的颜色：</strong></p>
 * <p>不同国家或地区对于 K线图 的颜色定义不一样，可能是『红涨绿跌』或『红涨蓝跌』（如大陆、台湾、日本、韩国等），可能是『绿涨红跌』（如西方国家、香港、新加坡等）。K线图也不一定要用红蓝、红绿来表示涨跌，也可以是『有色/无色』等表示方法。</p>
 * <p>默认配置项，采用的是『红涨蓝跌』。如果想更改这个颜色配置，在这些配置项中更改即可：</p>
 * <ul>
 * <li><a href="#series-candlestick.itemStyle.normal.color">series-candlestick.itemStyle.normal.color</a>：阳线填充色（即『涨』）</li>
 * <li><a href="#series-candlestick.itemStyle.normal.color0">series-candlestick.itemStyle.normal.color0</a>：阴线填充色（即『跌』）</li>
 * <li><a href="#series-candlestick.itemStyle.normal.borderColor">series-candlestick.itemStyle.normal.borderColor</a>：阳线边框色（即『涨』）</li>
 * <li><a href="series-candlestick.itemStyle.normal.borderColor0" target="_blank">series-candlestick.itemStyle.normal.borderColor0</a>：阴线边框色（即『跌』）</li>
 * </ul>
 * <p><br>
 * <br></p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesCandlestick implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number barWidth;

    private Object encode;

    private List data;

    private EChartSeriesCandlestickTooltip tooltip;

    private String type;

    private EChartSeriesCandlestickMarkLine markLine;

    private Boolean legendHoverLink;

    private EChartSeriesCandlestickMarkArea markArea;

    private Number zlevel;

    private String coordinateSystem;

    private Boolean silent;

    private String animationEasing;

    private Number barMaxWidth;

    private Object animationDelay;

    private Number xAxisIndex;

    private ItemStyle itemStyle;

    private EChartSeriesCandlestickMarkPoint markPoint;

    private Boolean hoverAnimation;

    private String layout;

    private Number animationDuration;

    private Number barMinWidth;

    private String name;

    private Number z;

    private Number yAxisIndex;

    private List dimensions;


    public Number getBarWidth(){
        return barWidth;
    }

    public EChartSeriesCandlestick setBarWidth(Number barWidth) {
        this.barWidth = barWidth;
        return this;
    }

    public Object getEncode(){
        return encode;
    }

    public EChartSeriesCandlestick setEncode(Object encode) {
        this.encode = encode;
        return this;
    }

    public List getData(){
        return data;
    }

    public EChartSeriesCandlestick setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesCandlestickTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesCandlestick setTooltip(EChartSeriesCandlestickTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesCandlestick setType(String type) {
        this.type = type;
        return this;
    }

    public EChartSeriesCandlestickMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesCandlestick setMarkLine(EChartSeriesCandlestickMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public Boolean getLegendHoverLink(){
        return legendHoverLink;
    }

    public EChartSeriesCandlestick setLegendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return this;
    }

    public EChartSeriesCandlestickMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesCandlestick setMarkArea(EChartSeriesCandlestickMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesCandlestick setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getCoordinateSystem(){
        return coordinateSystem;
    }

    public EChartSeriesCandlestick setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesCandlestick setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getAnimationEasing(){
        return animationEasing;
    }

    public EChartSeriesCandlestick setAnimationEasing(String animationEasing) {
        this.animationEasing = animationEasing;
        return this;
    }

    public Number getBarMaxWidth(){
        return barMaxWidth;
    }

    public EChartSeriesCandlestick setBarMaxWidth(Number barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
        return this;
    }

    public Object getAnimationDelay(){
        return animationDelay;
    }

    public EChartSeriesCandlestick setAnimationDelay(Object animationDelay) {
        this.animationDelay = animationDelay;
        return this;
    }

    public Number getXAxisIndex(){
        return xAxisIndex;
    }

    public EChartSeriesCandlestick setXAxisIndex(Number xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesCandlestick setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public EChartSeriesCandlestickMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesCandlestick setMarkPoint(EChartSeriesCandlestickMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public Boolean getHoverAnimation(){
        return hoverAnimation;
    }

    public EChartSeriesCandlestick setHoverAnimation(Boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
        return this;
    }

    public String getLayout(){
        return layout;
    }

    public EChartSeriesCandlestick setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Number getAnimationDuration(){
        return animationDuration;
    }

    public EChartSeriesCandlestick setAnimationDuration(Number animationDuration) {
        this.animationDuration = animationDuration;
        return this;
    }

    public Number getBarMinWidth(){
        return barMinWidth;
    }

    public EChartSeriesCandlestick setBarMinWidth(Number barMinWidth) {
        this.barMinWidth = barMinWidth;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesCandlestick setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesCandlestick setZ(Number z) {
        this.z = z;
        return this;
    }

    public Number getYAxisIndex(){
        return yAxisIndex;
    }

    public EChartSeriesCandlestick setYAxisIndex(Number yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    public List getDimensions(){
        return dimensions;
    }

    public EChartSeriesCandlestick setDimensions(List dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (barWidth != null)  {
            map.put("barWidth", barWidth);
        }
        if (encode != null)  {
            map.put("encode", encode);
        }
        if (data != null)  {
            map.put("data", data);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (legendHoverLink != null)  {
            map.put("legendHoverLink", legendHoverLink);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (coordinateSystem != null)  {
            map.put("coordinateSystem", coordinateSystem);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (animationEasing != null)  {
            map.put("animationEasing", animationEasing);
        }
        if (barMaxWidth != null)  {
            map.put("barMaxWidth", barMaxWidth);
        }
        if (animationDelay != null)  {
            map.put("animationDelay", animationDelay);
        }
        if (xAxisIndex != null)  {
            map.put("xAxisIndex", xAxisIndex);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (hoverAnimation != null)  {
            map.put("hoverAnimation", hoverAnimation);
        }
        if (layout != null)  {
            map.put("layout", layout);
        }
        if (animationDuration != null)  {
            map.put("animationDuration", animationDuration);
        }
        if (barMinWidth != null)  {
            map.put("barMinWidth", barMinWidth);
        }
        if (name != null)  {
            map.put("name", name);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (yAxisIndex != null)  {
            map.put("yAxisIndex", yAxisIndex);
        }
        if (dimensions != null)  {
            map.put("dimensions", dimensions);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
