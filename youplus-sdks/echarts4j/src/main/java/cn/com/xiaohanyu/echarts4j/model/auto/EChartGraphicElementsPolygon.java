package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>多边形。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartGraphicElementsPolygon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cursor;

    private Object onmousemove;

    private Object onmouseup;

    private Object ondragend;

    private Object ondragover;

    private Boolean invisible;

    private String type;

    private Object onmouseover;

    private String bounding;

    private Object top;

    private Boolean draggable;

    private String $action;

    private Object ondragenter;

    private Number zlevel;

    private String id;

    private Object ondragleave;

    private Object onmousewheel;

    private Boolean silent;

    private EChartGraphicElementsPolygonShape shape;

    private Object bottom;

    private Object onclick;

    private Object ondrop;

    private Object right;

    private Object ondragstart;

    private Object ondrag;

    private Object left;

    private Object onmouseout;

    private Boolean progressive;

    private Number z;

    private EChartGraphicElementsPolygonStyle style;

    private Object onmousedown;


    public String getCursor(){
        return cursor;
    }

    public EChartGraphicElementsPolygon setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public Object getOnmousemove(){
        return onmousemove;
    }

    public EChartGraphicElementsPolygon setOnmousemove(Object onmousemove) {
        this.onmousemove = onmousemove;
        return this;
    }

    public Object getOnmouseup(){
        return onmouseup;
    }

    public EChartGraphicElementsPolygon setOnmouseup(Object onmouseup) {
        this.onmouseup = onmouseup;
        return this;
    }

    public Object getOndragend(){
        return ondragend;
    }

    public EChartGraphicElementsPolygon setOndragend(Object ondragend) {
        this.ondragend = ondragend;
        return this;
    }

    public Object getOndragover(){
        return ondragover;
    }

    public EChartGraphicElementsPolygon setOndragover(Object ondragover) {
        this.ondragover = ondragover;
        return this;
    }

    public Boolean getInvisible(){
        return invisible;
    }

    public EChartGraphicElementsPolygon setInvisible(Boolean invisible) {
        this.invisible = invisible;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartGraphicElementsPolygon setType(String type) {
        this.type = type;
        return this;
    }

    public Object getOnmouseover(){
        return onmouseover;
    }

    public EChartGraphicElementsPolygon setOnmouseover(Object onmouseover) {
        this.onmouseover = onmouseover;
        return this;
    }

    public String getBounding(){
        return bounding;
    }

    public EChartGraphicElementsPolygon setBounding(String bounding) {
        this.bounding = bounding;
        return this;
    }

    public Object getTop(){
        return top;
    }

    public EChartGraphicElementsPolygon setTop(Object top) {
        this.top = top;
        return this;
    }

    public Boolean getDraggable(){
        return draggable;
    }

    public EChartGraphicElementsPolygon setDraggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public String get$action(){
        return $action;
    }

    public EChartGraphicElementsPolygon set$action(String $action) {
        this.$action = $action;
        return this;
    }

    public Object getOndragenter(){
        return ondragenter;
    }

    public EChartGraphicElementsPolygon setOndragenter(Object ondragenter) {
        this.ondragenter = ondragenter;
        return this;
    }

    public Number getZlevel(){
        return zlevel;
    }

    public EChartGraphicElementsPolygon setZlevel(Number zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public String getId(){
        return id;
    }

    public EChartGraphicElementsPolygon setId(String id) {
        this.id = id;
        return this;
    }

    public Object getOndragleave(){
        return ondragleave;
    }

    public EChartGraphicElementsPolygon setOndragleave(Object ondragleave) {
        this.ondragleave = ondragleave;
        return this;
    }

    public Object getOnmousewheel(){
        return onmousewheel;
    }

    public EChartGraphicElementsPolygon setOnmousewheel(Object onmousewheel) {
        this.onmousewheel = onmousewheel;
        return this;
    }

    public Boolean getSilent(){
        return silent;
    }

    public EChartGraphicElementsPolygon setSilent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public EChartGraphicElementsPolygonShape getShape(){
        return shape;
    }

    public EChartGraphicElementsPolygon setShape(EChartGraphicElementsPolygonShape shape) {
        this.shape = shape;
        return this;
    }

    public Object getBottom(){
        return bottom;
    }

    public EChartGraphicElementsPolygon setBottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public Object getOnclick(){
        return onclick;
    }

    public EChartGraphicElementsPolygon setOnclick(Object onclick) {
        this.onclick = onclick;
        return this;
    }

    public Object getOndrop(){
        return ondrop;
    }

    public EChartGraphicElementsPolygon setOndrop(Object ondrop) {
        this.ondrop = ondrop;
        return this;
    }

    public Object getRight(){
        return right;
    }

    public EChartGraphicElementsPolygon setRight(Object right) {
        this.right = right;
        return this;
    }

    public Object getOndragstart(){
        return ondragstart;
    }

    public EChartGraphicElementsPolygon setOndragstart(Object ondragstart) {
        this.ondragstart = ondragstart;
        return this;
    }

    public Object getOndrag(){
        return ondrag;
    }

    public EChartGraphicElementsPolygon setOndrag(Object ondrag) {
        this.ondrag = ondrag;
        return this;
    }

    public Object getLeft(){
        return left;
    }

    public EChartGraphicElementsPolygon setLeft(Object left) {
        this.left = left;
        return this;
    }

    public Object getOnmouseout(){
        return onmouseout;
    }

    public EChartGraphicElementsPolygon setOnmouseout(Object onmouseout) {
        this.onmouseout = onmouseout;
        return this;
    }

    public Boolean getProgressive(){
        return progressive;
    }

    public EChartGraphicElementsPolygon setProgressive(Boolean progressive) {
        this.progressive = progressive;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartGraphicElementsPolygon setZ(Number z) {
        this.z = z;
        return this;
    }

    public EChartGraphicElementsPolygonStyle getStyle(){
        return style;
    }

    public EChartGraphicElementsPolygon setStyle(EChartGraphicElementsPolygonStyle style) {
        this.style = style;
        return this;
    }

    public Object getOnmousedown(){
        return onmousedown;
    }

    public EChartGraphicElementsPolygon setOnmousedown(Object onmousedown) {
        this.onmousedown = onmousedown;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cursor != null)  {
            map.put("cursor", cursor);
        }
        if (onmousemove != null)  {
            map.put("onmousemove", onmousemove);
        }
        if (onmouseup != null)  {
            map.put("onmouseup", onmouseup);
        }
        if (ondragend != null)  {
            map.put("ondragend", ondragend);
        }
        if (ondragover != null)  {
            map.put("ondragover", ondragover);
        }
        if (invisible != null)  {
            map.put("invisible", invisible);
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (onmouseover != null)  {
            map.put("onmouseover", onmouseover);
        }
        if (bounding != null)  {
            map.put("bounding", bounding);
        }
        if (top != null)  {
            map.put("top", top);
        }
        if (draggable != null)  {
            map.put("draggable", draggable);
        }
        if ($action != null)  {
            map.put("$action", $action);
        }
        if (ondragenter != null)  {
            map.put("ondragenter", ondragenter);
        }
        if (zlevel != null)  {
            map.put("zlevel", zlevel);
        }
        if (id != null)  {
            map.put("id", id);
        }
        if (ondragleave != null)  {
            map.put("ondragleave", ondragleave);
        }
        if (onmousewheel != null)  {
            map.put("onmousewheel", onmousewheel);
        }
        if (silent != null)  {
            map.put("silent", silent);
        }
        if (shape != null)  {
            map.put("shape", shape.toMap());
        }
        if (bottom != null)  {
            map.put("bottom", bottom);
        }
        if (onclick != null)  {
            map.put("onclick", onclick);
        }
        if (ondrop != null)  {
            map.put("ondrop", ondrop);
        }
        if (right != null)  {
            map.put("right", right);
        }
        if (ondragstart != null)  {
            map.put("ondragstart", ondragstart);
        }
        if (ondrag != null)  {
            map.put("ondrag", ondrag);
        }
        if (left != null)  {
            map.put("left", left);
        }
        if (onmouseout != null)  {
            map.put("onmouseout", onmouseout);
        }
        if (progressive != null)  {
            map.put("progressive", progressive);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (style != null)  {
            map.put("style", style.toMap());
        }
        if (onmousedown != null)  {
            map.put("onmousedown", onmousedown);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
