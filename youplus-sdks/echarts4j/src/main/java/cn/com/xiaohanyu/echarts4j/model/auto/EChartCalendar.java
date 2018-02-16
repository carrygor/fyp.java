package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>日历坐标系组件。</p>
 * <p>在ECharts中，我们非常有创意地实现了日历图，是通过使用日历坐标系组件来达到日历图效果的，如下方的几个示例图所示，我们可以在热力图、散点图、关系图中使用日历坐标系。</p>
 * <p>在日历坐标系中使用热力图的示例:</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=calendar-heatmap&edit=1&reset=1" width="800" height="400" ></iframe>
 * 
 * 
 * <p>在日历坐标系中使用散点图的示例:</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=calendar-effectscatter&edit=1&reset=1" width="800" height="600" ></iframe>
 * 
 * 
 * <p>在日历坐标系中使用关系图（以及混合图表）的示例:</p>
 * <iframe data-src="http://echarts.baidu.com/gallery/view.html?c=calendar-graph&edit=1&reset=1" width="600" height="600" ></iframe>
 * 
 * 
 * <p>灵活利用 echarts 图表和坐标系的组合，以及 API，可以实现更丰富的效果。
 * <a href="http://echarts.baidu.com/gallery/editor.html?c=calendar-lunar&amp;edit=1&amp;reset=1" target="_blank">在日历中使用文字</a>、
 * <a href="http://echarts.baidu.com/gallery/editor.html?c=calendar-pie&amp;edit=1&amp;reset=1" target="_blank">在日历中放置饼图</a></p>
 * <hr>
 * <p><strong>水平和垂直放置日历</strong></p>
 * <p>在日历坐标系可以水平放置，也可以垂直放置。如上面的例子，使用热力图时，经常是水平放置的。但是如果需要格子的尺寸大些，水平放置就过于宽了，于是也可以选择垂直放置。参见 <a href="#calendar.orient">calendar.orient</a>。</p>
 * <hr>
 * <p><strong>尺寸的自适应</strong></p>
 * <p>可以设置日历坐标系使他支持不同尺寸的容器（页面）大小变化的自适应。首先，和 echarts 其他组件一样，日历坐标系可以选择使用 <a href="#calendar.left">left</a> <a href="#calendar.right">right</a> <a href="#calendar.top">top</a> <a href="bottom" target="_blank">bottom</a> <a href="#calendar.width">width</a> <a href="#calendar.height">height</a> 来描述尺寸和位置，从而将日历摆放在上下左右各种位置，并随着页面尺寸变动而改变自身尺寸。另外，也可以使用 <a href="#calendar.cellSize">cellSize</a> 来固定日历格子的长宽。</p>
 * <hr>
 * <p><strong>中西方日历习惯的支持</strong></p>
 * <p>中西方日历有所差别，西方常使用星期日作为一周的第一天，中国使用星期一为一周的第一天。日历坐标系做了这种切换的支持。参见 <a href="#calendar.dayLabel.firstDay">calendar.dayLabel.firstDay</a>。</p>
 * <p>另外，日历上的『月份』和『星期几』的文字，也可以较方便的切换中英文，甚至自定义。参见 <a href="#calendar.dayLabel.nameMap">calendar.dayLabel.nameMap</a> <a href="#calendar.monthLabel.nameMap">calendar.monthLabel.nameMap</a>。</p>
 * <hr>
 * </p>
 *
 * @author 小汉语
 */
public class EChartCalendar implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean silent;

    private String orient;

    private Object bottom;

    private EChartCalendarYearLabel yearLabel;

    private List range;

    private ItemStyle itemStyle;

    private Object right;

    private List cellSize;

    private EChartCalendarMonthLabel monthLabel;

    private EChartCalendarDayLabel dayLabel;

    private Object top;

    private Object left;

    private Object width;

    private EChartCalendarSplitLine splitLine;

    private Number zlevel;

    private Number z;

    private Object height;


    public Boolean getSilent(){
        return silent;
    }

    public EChartCalendar setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public String getOrient(){
        return orient;
    }

    public EChartCalendar setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartCalendar setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public EChartCalendarYearLabel getYearLabel(){
        return yearLabel;
    }

    public EChartCalendar setYearLabel(EChartCalendarYearLabel yearLabel) {
        this.yearLabel = yearLabel;
        return this;
    }

    public List getRange(){
        return range;
    }

    public EChartCalendar setRange(List range) {
        this.range = range;
        return this;
    }

    public ItemStyle getItemStyle(){
        return itemStyle;
    }

    public EChartCalendar setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartCalendar setRight(Object right) {
        this.right = right;
        return this;
    }

    public List getCellSize(){
        return cellSize;
    }

    public EChartCalendar setCellSize(List cellSize) {
        this.cellSize = cellSize;
        return this;
    }

    public EChartCalendarMonthLabel getMonthLabel(){
        return monthLabel;
    }

    public EChartCalendar setMonthLabel(EChartCalendarMonthLabel monthLabel) {
        this.monthLabel = monthLabel;
        return this;
    }

    public EChartCalendarDayLabel getDayLabel(){
        return dayLabel;
    }

    public EChartCalendar setDayLabel(EChartCalendarDayLabel dayLabel) {
        this.dayLabel = dayLabel;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartCalendar setTop(Object top) {
        this.top = top;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartCalendar setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getWidth(){
        return width;
    }

    public EChartCalendar setWidth(Object width) {
        this.width = width;
        return this;
    }

    public EChartCalendarSplitLine getSplitLine(){
        return splitLine;
    }

    public EChartCalendar setSplitLine(EChartCalendarSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartCalendar setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartCalendar setZ(Number z) {
        this.z = z;
        return this;
    }

    public Object getHeight(){
        return height;
    }

    public EChartCalendar setHeight(Object height) {
        this.height = height;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (orient != null)  {
            map.put("orient", orient);
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (yearLabel != null)  {
            map.put("yearLabel", yearLabel.toMap());
        }
        if (range != null)  {
            map.put("range", range);
        }
        if (itemStyle != null)  {
            map.put("itemStyle", itemStyle.toMap());
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (cellSize != null)  {
            map.put("cellSize", cellSize);
        }
        if (monthLabel != null)  {
            map.put("monthLabel", monthLabel.toMap());
        }
        if (dayLabel != null)  {
            map.put("dayLabel", dayLabel.toMap());
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (width != null)  {
            map.put("width", width);
        }
        if (splitLine != null)  {
            map.put("splitLine", splitLine.toMap());
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (height != null)  {
            map.put("height", height);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
