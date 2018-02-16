package cn.com.youplus.common.form;

import cn.com.youplus.common.validation.annotation.Check;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BaseForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    /**
     * 要访问的模块名称
     */
    @Check(module = true)
    private String module;

    /**
     * 个人信息
     */
    @Check(token = true)
    private String token;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
