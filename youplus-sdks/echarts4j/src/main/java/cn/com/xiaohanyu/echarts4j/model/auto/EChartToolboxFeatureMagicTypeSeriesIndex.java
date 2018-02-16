package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>各个类型对应的系列的列表。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureMagicTypeSeriesIndex implements Serializable {
    private static final long serialVersionUID = 1L;

    private List bar;

    private List stack;

    private List tiled;

    private Line line;


    public List getBar(){
        return bar;
    }

    public EChartToolboxFeatureMagicTypeSeriesIndex setBar(List bar) {
        this.bar = bar;
        return this;
    }

    public List getStack(){
        return stack;
    }

    public EChartToolboxFeatureMagicTypeSeriesIndex setStack(List stack) {
        this.stack = stack;
        return this;
    }

    public List getTiled(){
        return tiled;
    }

    public EChartToolboxFeatureMagicTypeSeriesIndex setTiled(List tiled) {
        this.tiled = tiled;
        return this;
    }

    public Line getLine(){
        return line;
    }

    public EChartToolboxFeatureMagicTypeSeriesIndex setLine(Line line) {
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
