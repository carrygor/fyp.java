package cn.com.carry.common.exception.handler;

import cn.com.carry.common.util.ValueHelper;
import cn.com.carry.common.exception.interaction.AlertException;
import cn.com.carry.common.exception.interaction.ConfirmPathException;
import cn.com.carry.common.model.base.BaseResponse;
import cn.com.carry.common.model.enums.ResponseEnum;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 严汉羽 on 2017/7/6.
 */
public abstract class ExceptionHandler {

    public String getFisrtLine(String msg) {
        if(!ValueHelper.isNone(msg)) {
            Scanner scanner = new Scanner(msg);
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        }
        return "系统异常，请稍后重试!";
    }

    /**
     * 这个是针对特殊的参数需要特殊处理的结果,如果命中了就回复处理的结果
     * @param ex
     * @return
     */
    public abstract BaseResponse specailHandle(Exception ex);

    public  BaseResponse handle(Exception ex) {
        BaseResponse res = new BaseResponse();
        if (ex instanceof InvocationTargetException) {
            ex = (Exception) ((InvocationTargetException) ex).getTargetException();
        }

        BaseResponse specailResponse = specailHandle(ex);
        if (specailResponse != null) {
            return specailResponse;
        }
        if (ex instanceof DuplicateKeyException) {
            res.setErrcode(ResponseEnum.EXCEPTION.getCode());
            res.setErrmsg("数据库重复插入，已经存在相同记录了。");
        } else if (ex instanceof BindException) {
            res.setErrcode(ResponseEnum.EXCEPTION.getCode());
            res.setErrmsg("数据绑定异常，请检查提交的参数");
        }
        else if (ex instanceof ConfirmPathException) { //跳转到路径的异常
            res.setErrcode(ResponseEnum.CONFIRM_PATH.getCode());
            res.setErrmsg(ex.getMessage());
            Map<String, Object> data = new HashMap<>();
            data.put("cancel", ((ConfirmPathException) ex).getCancel());
            data.put("confirm", ((ConfirmPathException) ex).getConfirm());
            data.put("path", ((ConfirmPathException) ex).getPath());
            data.put("type", ((ConfirmPathException) ex).getType().getType());
            res.setData(data);
        }

        else if (ex instanceof AlertException) {
            res.setErrcode(ResponseEnum.AlERT.getCode());
            res.setErrmsg(getFisrtLine(ex.getMessage()));
        }

        /**
         * 权限相关
         * */
        //region 权限相关

        //endregion
        else {
            res.setErrcode(ResponseEnum.EXCEPTION.getCode());
            if (ValueHelper.isNone(getFisrtLine(ex.getMessage()))) {
                res.setErrmsg("系统异常，请稍后重试!");
            } else {
                res.setErrmsg(getFisrtLine(ex.getMessage()));
            }
        }

        if (res.getData() == null) {
            res.setData(ex.getLocalizedMessage());
        }
        return res;
    }

}
