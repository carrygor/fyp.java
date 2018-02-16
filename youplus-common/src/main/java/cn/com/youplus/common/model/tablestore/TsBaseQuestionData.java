package cn.com.youplus.common.model.tablestore;

import com.hannea.annotation.TableColumn;
import com.hannea.constant.ColumnTypeObject;

import java.util.Date;
import java.util.Map;

/**
 * Created by 严汉羽 on 2018/1/4.
 */
public class TsBaseQuestionData extends TableStoreBaseModel implements Cloneable{

    /**
     * 总数
     */
    @TableColumn(name = "totalCnt", type = ColumnTypeObject.INTEGER)
    private int totalCnt;

    /**
     * 完成总量
     */
    @TableColumn(name = "average", type = ColumnTypeObject.DOUBLE)
    private Double average;

    /**
     * 选项详情
     */
    @TableColumn(name = "answerMap", type = ColumnTypeObject.MAP)
    private Map<String, Integer> answerMap;

    @Override
    public TsBaseQuestionData clone() throws CloneNotSupportedException {
        return (TsBaseQuestionData)super.clone();
    }

    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Map<String, Integer> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(Map<String, Integer> answerMap) {
        this.answerMap = answerMap;
    }
}
