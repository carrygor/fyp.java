package cn.com.youplus.common.form;

import cn.com.youplus.common.validation.annotation.Check;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public class BaseIdsForm extends BaseForm implements Serializable {
    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Check(notNull = true)
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
