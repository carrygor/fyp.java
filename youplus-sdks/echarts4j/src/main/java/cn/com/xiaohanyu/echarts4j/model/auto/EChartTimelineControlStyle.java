package cn.com.xiaohanyu.echarts4j.model.auto;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
import cn.com.xiaohanyu.echarts4j.model.base.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>『控制按钮』的样式。『控制按钮』包括：『播放按钮』、『前进按钮』、『后退按钮』。</p>
 * </p>
 *
 * @author 小汉语
 */
public class EChartTimelineControlStyle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Number itemGap;

    private Normal normal;

    private Boolean showPrevBtn;

    private Boolean show;

    private String prevIcon;

    private String stopIcon;

    private String nextIcon;

    private Boolean showNextBtn;

    private Emphasis emphasis;

    private Number itemSize;

    private String position;

    private String playIcon;

    private Boolean showPlayBtn;


    public Number getItemGap(){
        return itemGap;
    }

    public EChartTimelineControlStyle setItemGap(Number itemGap) {
        this.itemGap = itemGap;
        return this;
    }

    public Normal getNormal(){
        return normal;
    }

    public EChartTimelineControlStyle setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public Boolean getShowPrevBtn(){
        return showPrevBtn;
    }

    public EChartTimelineControlStyle setShowPrevBtn(Boolean showPrevBtn) {
        this.showPrevBtn = showPrevBtn;
        return this;
    }

    public Boolean getShow(){
        return show;
    }

    public EChartTimelineControlStyle setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public String getPrevIcon(){
        return prevIcon;
    }

    public EChartTimelineControlStyle setPrevIcon(String prevIcon) {
        this.prevIcon = prevIcon;
        return this;
    }

    public String getStopIcon(){
        return stopIcon;
    }

    public EChartTimelineControlStyle setStopIcon(String stopIcon) {
        this.stopIcon = stopIcon;
        return this;
    }

    public String getNextIcon(){
        return nextIcon;
    }

    public EChartTimelineControlStyle setNextIcon(String nextIcon) {
        this.nextIcon = nextIcon;
        return this;
    }

    public Boolean getShowNextBtn(){
        return showNextBtn;
    }

    public EChartTimelineControlStyle setShowNextBtn(Boolean showNextBtn) {
        this.showNextBtn = showNextBtn;
        return this;
    }

    public Emphasis getEmphasis(){
        return emphasis;
    }

    public EChartTimelineControlStyle setEmphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
        return this;
    }

    public Number getItemSize(){
        return itemSize;
    }

    public EChartTimelineControlStyle setItemSize(Number itemSize) {
        this.itemSize = itemSize;
        return this;
    }

    public String getPosition(){
        return position;
    }

    public EChartTimelineControlStyle setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getPlayIcon(){
        return playIcon;
    }

    public EChartTimelineControlStyle setPlayIcon(String playIcon) {
        this.playIcon = playIcon;
        return this;
    }

    public Boolean getShowPlayBtn(){
        return showPlayBtn;
    }

    public EChartTimelineControlStyle setShowPlayBtn(Boolean showPlayBtn) {
        this.showPlayBtn = showPlayBtn;
        return this;
    }

    public Object toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (itemGap != null)  {
            map.put("itemGap", itemGap);
        }
        if (normal != null)  {
            map.put("normal", normal.toMap());
        }
        if (showPrevBtn != null)  {
            map.put("showPrevBtn", showPrevBtn);
        }
        if (show != null)  {
            map.put("show", show);
        }
        if (prevIcon != null)  {
            map.put("prevIcon", prevIcon);
        }
        if (stopIcon != null)  {
            map.put("stopIcon", stopIcon);
        }
        if (nextIcon != null)  {
            map.put("nextIcon", nextIcon);
        }
        if (showNextBtn != null)  {
            map.put("showNextBtn", showNextBtn);
        }
        if (emphasis != null)  {
            map.put("emphasis", emphasis.toMap());
        }
        if (itemSize != null)  {
            map.put("itemSize", itemSize);
        }
        if (position != null)  {
            map.put("position", position);
        }
        if (playIcon != null)  {
            map.put("playIcon", playIcon);
        }
        if (showPlayBtn != null)  {
            map.put("showPlayBtn", showPlayBtn);
        }
        return map;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
