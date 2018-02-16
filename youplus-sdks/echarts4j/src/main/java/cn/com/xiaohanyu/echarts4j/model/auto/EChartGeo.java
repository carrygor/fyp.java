package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>地理坐标系组件。</p>
 * <p>地理坐标系组件用于地图的绘制，支持在地理坐标系上绘制<a href="#series-scatter">散点图</a>，<a href="#series-lines">线集</a>。</p>
 * <p><strong>在地理坐标系中使用散点图的示例:</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=scatter-map&edit=1&reset=1" width="600" height="400" ></iframe>
 * 
 * 
 * 
 * 
 * <p><code>3.1.10</code> 开始 geo 组件也支持鼠标事件。事件参数为</p>
 * <pre><code class="lang-js">{
 *     componentType: &#39;geo&#39;,
 *     // Geo 组件在 option 中的 index
 *     geoIndex: number,
 *     // 点击区域的名称，比如&quot;上海&quot;
 *     name: string,
 *     // 传入的点击区域的 region 对象，见 geo.regions
 *     region: Object
 * }
 * </code></pre>
 * <p><strong>Tip:</strong>
 * geo 区域的颜色也可以被 map series 所控制，参见 <a href="#series-map.geoIndex">series-map.geoIndex</a>。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartGeo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean silent;

    private List regions;

    private Object bottom;

    private List center;

    private Boolean show;

    private ItemStyle itemStyle;

    private Number zoom;

    private EChartGeoLabel label;

    private Object right;

    private List layoutCenter;

    private EChartGeoScaleLimit scaleLimit;

    private Object nameMap;

    private List boundingCoords;

    private Object top;

    private Number aspectScale;

    private Object left;

    private Object selectedMode;

    private Object layoutSize;

    private Number zlevel;

    private Number z;

    private Boolean roam;

    private String map;


    public Boolean getSilent(){
        return silent;
    }

    public EChartGeo setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public List getRegions(){
        return regions;
    }

    public EChartGeo setRegions(List regions) {
        this.regions = regions;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartGeo setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public List getCenter(){
        return center;
    }

    public EChartGeo setCenter(List center) {
        this.center = center;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartGeo setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartGeo setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Number getZoom(){
        return zoom;
    }

    public EChartGeo setZoom(Number zoom) {
        this.zoom = zoom;
        return this;
    }

    public EChartGeoLabel getLabel(){
        return label;
    }

    public EChartGeo setLabel(EChartGeoLabel label) {
        this.label = label;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartGeo setRight(Object right) {
        this.right = right;
        return this;
    }

    public List getLayoutCenter(){
        return layoutCenter;
    }

    public EChartGeo setLayoutCenter(List layoutCenter) {
        this.layoutCenter = layoutCenter;
        return this;
    }

    public EChartGeoScaleLimit getScaleLimit(){
        return scaleLimit;
    }

    public EChartGeo setScaleLimit(EChartGeoScaleLimit scaleLimit) {
        this.scaleLimit = scaleLimit;
        return this;
    }

    public Object getNameMap(){
        return nameMap;
    }

    public EChartGeo setNameMap(Object nameMap) {
        this.nameMap = nameMap;
        return this;
    }

    public List getBoundingCoords(){
        return boundingCoords;
    }

    public EChartGeo setBoundingCoords(List boundingCoords) {
        this.boundingCoords = boundingCoords;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartGeo setTop(Object top) {
        this.top = top;
        return this;
    }

    public Number getAspectScale(){
        return aspectScale;
    }

    public EChartGeo setAspectScale(Number aspectScale) {
        this.aspectScale = aspectScale;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartGeo setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getSelectedMode(){
        return selectedMode;
    }

    public EChartGeo setSelectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    public Object getLayoutSize(){
        return layoutSize;
    }

    public EChartGeo setLayoutSize(Object layoutSize) {
        this.layoutSize = layoutSize;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartGeo setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartGeo setZ(Number z) {
        this.z = z;
        return this;
    }

    public Boolean getRoam(){
        return roam;
    }

    public EChartGeo setRoam(Boolean roam) {
        this.roam = roam;
        return this;
    }

    public String getMap(){
        return map;
    }

    public EChartGeo setMap(String map) {
        this.map = map;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (regions != null)  {
            map.put("regions", regions);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (center != null)  {
            map.put("center", center);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (zoom != null)  {
            map.put("zoom", zoom);
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (layoutCenter != null)  {
            map.put("layoutCenter", layoutCenter);
        }
        if (scaleLimit != null)  {
            map.put("scaleLimit", scaleLimit.toMap());
        }
        if (nameMap != null)  {
            map.put("nameMap", nameMap);
        }
        if (boundingCoords != null)  {
            map.put("boundingCoords", boundingCoords);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (aspectScale != null)  {
            map.put("aspectScale", aspectScale);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (selectedMode != null)  {
            map.put("selectedMode", selectedMode);
        }
        if (layoutSize != null)  {
            map.put("layoutSize", layoutSize);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (roam != null)  {
            map.put("roam", roam);
        }
        if (map != null)  {
            map.put("map", map);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
