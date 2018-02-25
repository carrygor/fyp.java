package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>axisPointer settings on axis.</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSingleAxisAxisPointer implements Serializable {
    private static final long serialVersionUID = 1L;

    private ShadowStyle shadowStyle;

    private LineStyle lineStyle;

    private Boolean triggerTooltip;

    private Boolean show;

    private Number z;

    private EChartSingleAxisAxisPointerHandle handle;

    private EChartSingleAxisAxisPointerLabel label;

    private String type;

    private Number value;

    private Boolean snap;

    private Boolean status;


    public ShadowStyle getShadowStyle(){
        return shadowStyle;
    }

    public EChartSingleAxisAxisPointer setShadowStyle(ShadowStyle shadowStyle) {
        this.shadowStyle = shadowStyle;
        return this;
    }

    public LineStyle getLineStyle(){
        return lineStyle;
    }

    public EChartSingleAxisAxisPointer setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    public Boolean getTriggerTooltip(){
        return triggerTooltip;
    }

    public EChartSingleAxisAxisPointer setTriggerTooltip(Boolean triggerTooltip) {
        this.triggerTooltip = triggerTooltip;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartSingleAxisAxisPointer setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Number getZ(){
        return z;
    }

    public EChartSingleAxisAxisPointer setZ(Number z) {
        this.z = z;
        return this;
    }

    public EChartSingleAxisAxisPointerHandle getHandle(){
        return handle;
    }

    public EChartSingleAxisAxisPointer setHandle(EChartSingleAxisAxisPointerHandle handle) {
        this.handle = handle;
        return this;
    }

    public EChartSingleAxisAxisPointerLabel getLabel(){
        return label;
    }

    public EChartSingleAxisAxisPointer setLabel(EChartSingleAxisAxisPointerLabel label) {
        this.label = label;
        return this;
    }

    public String getType(){
        return type;
    }

    public EChartSingleAxisAxisPointer setType(String type) {
        this.type = type;
        return this;
    }

    public Number getValue(){
        return value;
    }

    public EChartSingleAxisAxisPointer setValue(Number value) {
        this.value = value;
        return this;
    }

    public Boolean getSnap(){
        return snap;
    }

    public EChartSingleAxisAxisPointer setSnap(Boolean snap) {
        this.snap = snap;
        return this;
    }

    public Boolean getStatus(){
        return status;
    }

    public EChartSingleAxisAxisPointer setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (shadowStyle != null)  {
            map.put("shadowStyle", shadowStyle.toMap());
        }
        if (lineStyle != null)  {
            map.put("lineStyle", lineStyle.toMap());
        }
        if (triggerTooltip != null)  {
            map.put("triggerTooltip", triggerTooltip);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (z != null)  {
            map.put("z", z);
        }
        if (handle != null)  {
            map.put("handle", handle.toMap());
        }
        if (label != null)  {
            map.put("label", label.toMap());
        }
        if (type != null)  {
            map.put("type", type);
        }
        if (value != null)  {
            map.put("value", value);
        }
        if (snap != null)  {
            map.put("snap", snap);
        }
        if (status != null)  {
            map.put("status", status);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}