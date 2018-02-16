package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p><strong>地图。</strong></p>
 * <p>地图主要用于地理区域数据的可视化，配合 <a href="#visualMap">visualMap</a> 组件用于展示不同区域的人口分布密度等数据。</p>
 * <p>多个<a href="#series-map.map">地图类型</a>相同的系列会在同一地图上显示，这时候使用第一个系列的配置项作为地图绘制的配置。</p>
 * <p><strong>Tip: </strong>在 ECharts 3 中不再建议在地图类型的图表使用 <code>markLine</code> 和 <code>markPoint</code>。如果要实现点数据或者线数据的可视化，可以使用在<a href="#geo">地理坐标系组件</a>上的<a href="#series-scatter">散点图</a>和<a href="#series-lines">线图</a>。</p>
 * <p><strong>示例：</strong></p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=doc-example/map-example&reset=1&edit=1" width="600" height="400" ></iframe>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesMap implements Serializable {
    private static final long serialVersionUID = 1L;

    private List data;

    private EChartSeriesMapTooltip tooltip;

    private String type;

    private Boolean showLegendSymbol;

    private EChartSeriesMapMarkLine markLine;

    private List layoutCenter;

    private String mapValueCalculation;

    private Number geoIndex;

    private EChartSeriesMapMarkArea markArea;

    private List boundingCoords;

    private Object top;

    private Object layoutSize;

    private Number zlevel;

    private Object roam;

    private String map;

    private Boolean silent;

    private Object bottom;

    private List center;

    private ItemStyle itemStyle;

    private Number zoom;

    private EChartSeriesMapLabel label;

    private Object right;

    private EChartSeriesMapMarkPoint markPoint;

    private EChartSeriesMapScaleLimit scaleLimit;

    private Object nameMap;

    private Number aspectScale;

    private Object left;

    private Object selectedMode;

    private String name;

    private Number z;


    public List getData(){
        return data;
    }

    public EChartSeriesMap setData(List data) {
        this.data = data;
        return this;
    }

    public EChartSeriesMapTooltip getTooltip(){
        return tooltip;
    }

    public EChartSeriesMap setTooltip(EChartSeriesMapTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSeriesMap setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getShowLegendSymbol(){
        return showLegendSymbol;
    }

    public EChartSeriesMap setShowLegendSymbol(Boolean showLegendSymbol) {
        this.showLegendSymbol = showLegendSymbol;
        return this;
    }

    public EChartSeriesMapMarkLine getMarkLine(){
        return markLine;
    }

    public EChartSeriesMap setMarkLine(EChartSeriesMapMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    public List getLayoutCenter(){
        return layoutCenter;
    }

    public EChartSeriesMap setLayoutCenter(List layoutCenter) {
        this.layoutCenter = layoutCenter;
        return this;
    }

    public String getMapValueCalculation(){
        return mapValueCalculation;
    }

    public EChartSeriesMap setMapValueCalculation(String mapValueCalculation) {
        this.mapValueCalculation = mapValueCalculation;
        return this;
    }

    public Number getGeoIndex(){
        return geoIndex;
    }

    public EChartSeriesMap setGeoIndex(Number geoIndex) {
        this.geoIndex = geoIndex;
        return this;
    }

    public EChartSeriesMapMarkArea getMarkArea(){
        return markArea;
    }

    public EChartSeriesMap setMarkArea(EChartSeriesMapMarkArea markArea) {
        this.markArea = markArea;
        return this;
    }

    public List getBoundingCoords(){
        return boundingCoords;
    }

    public EChartSeriesMap setBoundingCoords(List boundingCoords) {
        this.boundingCoords = boundingCoords;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartSeriesMap setTop(Object top) {
        this.top = top;
        return this;
    }

    public Object getLayoutSize(){
        return layoutSize;
    }

    public EChartSeriesMap setLayoutSize(Object layoutSize) {
        this.layoutSize = layoutSize;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartSeriesMap setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Object getRoam(){
        return roam;
    }

    public EChartSeriesMap setRoam(Object roam) {
        this.roam = roam;
        return this;
    }

    public String getMap(){
        return map;
    }

    public EChartSeriesMap setMap(String map) {
        this.map = map;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartSeriesMap setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartSeriesMap setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public List getCenter(){
        return center;
    }

    public EChartSeriesMap setCenter(List center) {
        this.center = center;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartSeriesMap setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Number getZoom(){
        return zoom;
    }

    public EChartSeriesMap setZoom(Number zoom) {
        this.zoom = zoom;
        return this;
    }

    public EChartSeriesMapLabel getLabel(){
        return label;
    }

    public EChartSeriesMap setLabel(EChartSeriesMapLabel label) {
        this.label = label;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartSeriesMap setRight(Object right) {
        this.right = right;
        return this;
    }

    public EChartSeriesMapMarkPoint getMarkPoint(){
        return markPoint;
    }

    public EChartSeriesMap setMarkPoint(EChartSeriesMapMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    public EChartSeriesMapScaleLimit getScaleLimit(){
        return scaleLimit;
    }

    public EChartSeriesMap setScaleLimit(EChartSeriesMapScaleLimit scaleLimit) {
        this.scaleLimit = scaleLimit;
        return this;
    }

    public Object getNameMap(){
        return nameMap;
    }

    public EChartSeriesMap setNameMap(Object nameMap) {
        this.nameMap = nameMap;
        return this;
    }

    public Number getAspectScale(){
        return aspectScale;
    }

    public EChartSeriesMap setAspectScale(Number aspectScale) {
        this.aspectScale = aspectScale;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartSeriesMap setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getSelectedMode(){
        return selectedMode;
    }

    public EChartSeriesMap setSelectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    public String getName(){
        return name;
    }

    public EChartSeriesMap setName(String name) {
        this.name = name;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSeriesMap setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data != null)  {
            map.put("data", data);
        }
        if (tooltip != null)  {
            map.put("tooltip", tooltip.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (showLegendSymbol != null)  {
            map.put("showLegendSymbol", showLegendSymbol);
        }
        if (markLine != null)  {
            map.put("markLine", markLine.toMap());
        }
        if (layoutCenter != null)  {
            map.put("layoutCenter", layoutCenter);
        }
        if (mapValueCalculation != null)  {
            map.put("mapValueCalculation", mapValueCalculation);
        }
        if (geoIndex != null)  {
            map.put("geoIndex", geoIndex);
        }
        if (markArea != null)  {
            map.put("markArea", markArea.toMap());
        }
        if (boundingCoords != null)  {
            map.put("boundingCoords", boundingCoords);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (layoutSize != null)  {
            map.put("layoutSize", layoutSize);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (roam != null)  {
            map.put("roam", roam);
        }
        if (map != null)  {
            map.put("map", map);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (center != null)  {
            map.put("center", center);
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
        if (markPoint != null)  {
            map.put("markPoint", markPoint.toMap());
        }
        if (scaleLimit != null)  {
            map.put("scaleLimit", scaleLimit.toMap());
        }
        if (nameMap != null)  {
            map.put("nameMap", nameMap);
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
        if (name != null)  {
            map.put("name", name);
        }
        if (z != null)  {
            map.put("z", z);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
