package cn.com.carry.common.model.base;

import cn.com.carry.common.model.enums.ResponseEnum;
import cn.com.carry.common.model.enums.RouterGoTypeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public class BaseResponse<T> implements Serializable {

    private int errcode = ResponseEnum.SUCCESS.getCode();
    private String errmsg = ResponseEnum.SUCCESS.getErrMsg();
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public BaseResponse setErrcode(int errcode) {
        this.errcode = errcode;
        return this;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public BaseResponse setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public T getData() {
        if (data == null) {
            return (T) new Object();
        }
        return data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 跳转到前端的某个页面路径
     * @param type
     * @param path
     */
    public void goPath(RouterGoTypeEnum type, String path) {
        this.setErrcode(ResponseEnum.GO_PATH.getCode());
        Map<String, Object> data = new HashMap<>();
        data.put("type", type.getType());
        data.put("path", path);
        this.setData((T) data);
    }
}
