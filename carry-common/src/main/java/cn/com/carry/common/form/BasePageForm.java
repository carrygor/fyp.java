package cn.com.carry.common.form;

import cn.com.carry.common.validation.annotation.Check;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BasePageForm extends BaseForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    private String keyword;

    /**
     * 分页对应的大小
     */
    @Check(numeric = true, minNum = 1)
    private int pageSize = 1;

    /**
     * 页码，从第1页开始
     */
    @Check(numeric = true, minNum = 1)
    private int pageNum = 1;


    public String getKeyword() {
        return keyword == null ? null : keyword.trim();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getOffset(){
        return pageSize * (pageNum - 1);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
