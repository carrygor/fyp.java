package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>各工具配置项。</p>
 * <p>除了各个内置的工具按钮外，还可以自定义工具按钮。</p>
 * <p>注意，自定义的工具名字，只能以 <code>my</code> 开头，例如下例中的 <code>myTool1</code>，<code>myTool2</code>：</p>
 * <pre><code class="lang-javascript">{
 *     toolbox: {
 *         feature: {
 *             myTool1: {
 *                 show: true,
 *                 title: &#39;自定义扩展方法1&#39;,
 *                 icon: &#39;path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891&#39;,
 *                 onclick: function (){
 *                     alert(&#39;myToolHandler1&#39;)
 *                 }
 *             },
 *             myTool2: {
 *                 show: true,
 *                 title: &#39;自定义扩展方法&#39;,
 *                 icon: &#39;image://http://echarts.baidu.com/images/favicon.png&#39;,
 *                 onclick: function (){
 *                     alert(&#39;myToolHandler2&#39;)
 *                 }
 *             }
 *         }
 *     }
 * }
 * </code></pre>
 * </p>
 *
 * @author 小汉语
 */
public class EChartToolboxFeature implements Serializable {
    private static final long serialVersionUID = 1L;

    private EChartToolboxFeatureSaveAsImage saveAsImage;

    private EChartToolboxFeatureBrush brush;

    private EChartToolboxFeatureRestore restore;

    private EChartToolboxFeatureDataZoom dataZoom;

    private EChartToolboxFeatureMagicType magicType;

    private EChartToolboxFeatureDataView dataView;


    public EChartToolboxFeatureSaveAsImage getSaveAsImage(){
        return saveAsImage;
    }

    public EChartToolboxFeature setSaveAsImage(EChartToolboxFeatureSaveAsImage saveAsImage) {
        this.saveAsImage = saveAsImage;
        return this;
    }

    public EChartToolboxFeatureBrush getBrush(){
        return brush;
    }

    public EChartToolboxFeature setBrush(EChartToolboxFeatureBrush brush) {
        this.brush = brush;
        return this;
    }

    public EChartToolboxFeatureRestore getRestore(){
        return restore;
    }

    public EChartToolboxFeature setRestore(EChartToolboxFeatureRestore restore) {
        this.restore = restore;
        return this;
    }

    public EChartToolboxFeatureDataZoom getDataZoom(){
        return dataZoom;
    }

    public EChartToolboxFeature setDataZoom(EChartToolboxFeatureDataZoom dataZoom) {
        this.dataZoom = dataZoom;
        return this;
    }

    public EChartToolboxFeatureMagicType getMagicType(){
        return magicType;
    }

    public EChartToolboxFeature setMagicType(EChartToolboxFeatureMagicType magicType) {
        this.magicType = magicType;
        return this;
    }

    public EChartToolboxFeatureDataView getDataView(){
        return dataView;
    }

    public EChartToolboxFeature setDataView(EChartToolboxFeatureDataView dataView) {
        this.dataView = dataView;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (saveAsImage != null)  {
            map.put("saveAsImage", saveAsImage.toMap());
        }
        if (brush != null)  {
            map.put("brush", brush.toMap());
        }
        if (restore != null)  {
            map.put("restore", restore.toMap());
        }
        if (dataZoom != null)  {
            map.put("dataZoom", dataZoom.toMap());
        }
        if (magicType != null)  {
            map.put("magicType", magicType.toMap());
        }
        if (dataView != null)  {
            map.put("dataView", dataView.toMap());
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
