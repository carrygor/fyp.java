package cn.com.carry.common.form;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BaseIdAndQidAndRangeForm extends BaseIdAndQidForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    private Long startDate;

    private Long endDate;

    private Long lastId;

    private int pageSize;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
