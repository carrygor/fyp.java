package cn.com.carry.common.form;

import cn.com.carry.common.validation.annotation.Check;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BasePageIdForm extends BasePageForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Check(minNum = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
