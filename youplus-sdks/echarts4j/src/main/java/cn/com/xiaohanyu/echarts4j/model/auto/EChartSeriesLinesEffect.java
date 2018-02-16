package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>线特效的配置，见示例 <a href="http://echarts.baidu.com/gallery/editor.html?c=geo-lines" target="_blank">模拟迁徙</a> 和 <a href="http://echarts.baidu.com/gallery/editor.html?c=lines-bmap-effect" target="_blank">北京公交路线</a></p>
 * <p><strong>注意：</strong> 所有带有尾迹特效的图表需要单独放在一个层，也就是需要单独设置 <a href="#series-lines.zlevel">zlevel</a>，同时建议关闭该层的动画（<a href="#series-lines.animation">animation</a>: false）。不然位于同个层的其它系列的图形，和动画的<a href="#series-lines.label">标签</a>也会产生不必要的残影。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesLinesEffect implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    private Number period;

    private Object delay;

    private Color color;

    private Object symbolSize;

    private Boolean loop;

    private Boolean show;

    private Number constantSpeed;

    private Number trailLength;


    public String getSymbol(){
        return symbol;
    }

    public EChartSeriesLinesEffect setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Number getPeriod(){
        return period;
    }

    public EChartSeriesLinesEffect setPeriod(Number period) {
        this.period = period;
        return this;
    }

    public Object getDelay(){
        return delay;
    }

    public EChartSeriesLinesEffect setDelay(Object delay) {
        this.delay = delay;
        return this;
    }

    public Color getColor(){
        return color;
    }

    public EChartSeriesLinesEffect setColor(Color color) {
        this.color = color;
        return this;
    }

    public Object getSymbolSize(){
        return symbolSize;
    }

    public EChartSeriesLinesEffect setSymbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public Boolean getLoop(){
        return loop;
    }

    public EChartSeriesLinesEffect setLoop(Boolean loop) {
        this.loop = loop;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartSeriesLinesEffect setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Number getConstantSpeed(){
        return constantSpeed;
    }

    public EChartSeriesLinesEffect setConstantSpeed(Number constantSpeed) {
        this.constantSpeed = constantSpeed;
        return this;
    }

    public Number getTrailLength(){
        return trailLength;
    }

    public EChartSeriesLinesEffect setTrailLength(Number trailLength) {
        this.trailLength = trailLength;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (symbol != null)  {
            map.put("symbol", symbol);
        }
        if (period != null)  {
            map.put("period", period);
        }
        if (delay != null)  {
            map.put("delay", delay);
        }
        if (color != null)  {
            map.put("color", color.toMap());
        }
        if (symbolSize != null)  {
            map.put("symbolSize", symbolSize);
        }
        if (loop != null)  {
            map.put("loop", loop);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (constantSpeed != null)  {
            map.put("constantSpeed", constantSpeed);
        }
        if (trailLength != null)  {
            map.put("trailLength", trailLength);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
