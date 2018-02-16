package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>各个类型的标题文本，可以分别配置。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeatureMagicTypeTitle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bar;

    private String stack;

    private String tiled;

    private Line line;


    public String getBar(){
        return bar;
    }

    public EChartToolboxFeatureMagicTypeTitle setBar(String bar) {
        this.bar = bar;
        return this;
    }

    public String getStack(){
        return stack;
    }

    public EChartToolboxFeatureMagicTypeTitle setStack(String stack) {
        this.stack = stack;
        return this;
    }

    public String getTiled(){
        return tiled;
    }

    public EChartToolboxFeatureMagicTypeTitle setTiled(String tiled) {
        this.tiled = tiled;
        return this;
    }

    public Line getLine(){
        return line;
    }

    public EChartToolboxFeatureMagicTypeTitle setLine(Line line) {
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
