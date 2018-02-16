package cn.com.youplus.common.model.tablestore;

import com.hannea.annotation.TableColumn;
import com.hannea.constant.ColumnTypeObject;

import java.util.Date;
import java.util.Map;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
public class TsBaseData extends TableStoreBaseModel implements Cloneable {

    /**
     * 访问总量
     */
    @TableColumn(name = "totalVisitCnt", type = ColumnTypeObject.INTEGER)
    private int totalVisitCnt;

    /**
     * 完成总量
     */
    @TableColumn(name = "totalFinishedCnt", type = ColumnTypeObject.INTEGER)
    private int totalFinishedCnt;

    /**
     * 提交率
     */
    @TableColumn(name = "finishedRate", type = ColumnTypeObject.DOUBLE)
    private Double finishedRate;

    /**
     * 平均提交时间(s)
     */
    @TableColumn(name = "averageTime", type = ColumnTypeObject.DOUBLE)
    private Double averageTime;

    /**
     * 手机类型统计
     */
    @TableColumn(name = "phoneTypeMap", type = ColumnTypeObject.MAP)
    private Map<String, Integer> phoneTypeMap;

    /**
     * 操作系统计数
     */
    @TableColumn(name = "osTypeMap", type = ColumnTypeObject.MAP)
    private Map<String, Integer> osTypeMap;

    private Date updateTime;

    @Override
    public TsBaseData clone() throws CloneNotSupportedException {
        return (TsBaseData)super.clone();
    }

    public int getTotalVisitCnt() {
        return totalVisitCnt;
    }

    public void setTotalVisitCnt(int totalVisitCnt) {
        this.totalVisitCnt = totalVisitCnt;
    }

    public int getTotalFinishedCnt() {
        return totalFinishedCnt;
    }

    public void setTotalFinishedCnt(int totalFinishedCnt) {
        this.totalFinishedCnt = totalFinishedCnt;
    }

    public Double getFinishedRate() {
        return finishedRate;
    }

    public void setFinishedRate(Double finishedRate) {
        this.finishedRate = finishedRate;
    }

    public Double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Double averageTime) {
        this.averageTime = averageTime;
    }

    public Map<String, Integer> getPhoneTypeMap() {
        return phoneTypeMap;
    }

    public void setPhoneTypeMap(Map<String, Integer> phoneTypeMap) {
        this.phoneTypeMap = phoneTypeMap;
    }

    public Map<String, Integer> getOsTypeMap() {
        return osTypeMap;
    }

    public void setOsTypeMap(Map<String, Integer> osTypeMap) {
        this.osTypeMap = osTypeMap;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
