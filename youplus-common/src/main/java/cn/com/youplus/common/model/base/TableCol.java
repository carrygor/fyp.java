package cn.com.youplus.common.model.base;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public class TableCol implements Serializable {

    private String name;

    private String score;

    private Integer num;

    private Double rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
