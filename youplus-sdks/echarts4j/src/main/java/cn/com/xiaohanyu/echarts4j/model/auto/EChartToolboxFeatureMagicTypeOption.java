package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>各个类型的专有配置项。在切换到某类型的时候会合并相应的配置项。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureMagicTypeOption implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object bar;

    private Object stack;

    private Object tiled;

    private Line line;


    public Object getBar(){
        return bar;
    }

    public EChartToolboxFeatureMagicTypeOption setBar(Object bar) {
        this.bar = bar;
        return this;
    }

    public Object getStack(){
        return stack;
    }

    public EChartToolboxFeatureMagicTypeOption setStack(Object stack) {
        this.stack = stack;
        return this;
    }

    public Object getTiled(){
        return tiled;
    }

    public EChartToolboxFeatureMagicTypeOption setTiled(Object tiled) {
        this.tiled = tiled;
        return this;
    }

    public Line getLine(){
        return line;
    }

    public EChartToolboxFeatureMagicTypeOption setLine(Line line) {
        this.line = line;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bar != null)  {
            map.put("bar", bar);
        }
        if (stack != null)  {
            map.put("stack", stack);
        }
        if (tiled != null)  {
            map.put("tiled", tiled);
        }
        if (line != null)  {
            map.put("line", line.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
