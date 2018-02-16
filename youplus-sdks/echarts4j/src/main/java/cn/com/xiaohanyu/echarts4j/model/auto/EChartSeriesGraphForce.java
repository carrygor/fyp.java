package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>力引导布局相关的配置项，力引导布局是模拟弹簧电荷模型在每两个节点之间添加一个斥力，每条边的两个节点之间添加一个引力，每次迭代节点会在各个斥力和引力的作用下移动位置，多次迭代后节点会静止在一个受力平衡的位置，达到整个模型的能量最小化。</p>
 * <p>力引导布局的结果有良好的对称性和局部聚合性，也比较美观。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartSeriesGraphForce implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object repulsion;

    private Boolean layoutAnimation;

    private Object edgeLength;

    private Number gravity;

    private String initLayout;


    public Object getRepulsion(){
        return repulsion;
    }

    public EChartSeriesGraphForce setRepulsion(Object repulsion) {
        this.repulsion = repulsion;
        return this;
    }

    public Boolean getLayoutAnimation(){
        return layoutAnimation;
    }

    public EChartSeriesGraphForce setLayoutAnimation(Boolean layoutAnimation) {
        this.layoutAnimation = layoutAnimation;
        return this;
    }

    public Object getEdgeLength(){
        return edgeLength;
    }

    public EChartSeriesGraphForce setEdgeLength(Object edgeLength) {
        this.edgeLength = edgeLength;
        return this;
    }

    public Number getGravity(){
        return gravity;
    }

    public EChartSeriesGraphForce setGravity(Number gravity) {
        this.gravity = gravity;
        return this;
    }

    public String getInitLayout(){
        return initLayout;
    }

    public EChartSeriesGraphForce setInitLayout(String initLayout) {
        this.initLayout = initLayout;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (repulsion != null)  {
            map.put("repulsion", repulsion);
        }
        if (layoutAnimation != null)  {
            map.put("layoutAnimation", layoutAnimation);
        }
        if (edgeLength != null)  {
            map.put("edgeLength", edgeLength);
        }
        if (gravity != null)  {
            map.put("gravity", gravity);
        }
        if (initLayout != null)  {
            map.put("initLayout", initLayout);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
