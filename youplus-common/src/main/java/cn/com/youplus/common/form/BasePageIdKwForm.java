package cn.com.youplus.common.form;

import cn.com.youplus.common.validation.annotation.Check;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BasePageIdKwForm extends BasePageIdForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;


    /**
     * kw
     */
    private String kw;

    public String getKw() {
        return kw;
    }

    public BasePageIdKwForm setKw(String kw) {
        this.kw = kw;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
