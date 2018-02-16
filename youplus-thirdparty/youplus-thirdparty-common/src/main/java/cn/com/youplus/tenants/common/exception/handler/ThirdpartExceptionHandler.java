package cn.com.youplus.tenants.common.exception.handler;

import cn.com.youplus.common.exception.data.*;
import cn.com.youplus.common.exception.handler.ExceptionHandler;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.ResponseEnum;
import cn.com.youplus.common.util.ValueHelper;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.DefaultAddressedEnvelope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class ThirdpartExceptionHandler extends ExceptionHandler {
    @Override
    public BaseResponse specailHandle(Exception ex) {
        BaseResponse res = new BaseResponse();

        if (ex instanceof InvalidParameterException) {
            res.setErrcode(ResponseEnum.INVALID_PARAMTER.getCode());
            res.setErrmsg(getFisrtLine(ex.getMessage()));
        } else if (ex instanceof SignatureException) {
            res.setErrcode(ResponseEnum.SIGN_ERROR.getCode());
            res.setErrmsg(getFisrtLine(ex.getMessage()));
        } else if (ex instanceof InvalidCustomIdException) {
            res.setErrcode(ResponseEnum.INVALID_CUSTOMER_CODE.getCode());
            res.setErrmsg(getFisrtLine(ex.getMessage()));
        } else if (ex instanceof InvalidDateFormatException) {
            res.setErrcode(ResponseEnum.INVALID_DATE_FORMAT.getCode());
            res.setErrmsg(getFisrtLine(ex.getMessage()));
        } else if (ex instanceof DataErrorException) {
            res.setErrcode(ResponseEnum.DATA_ERROR.getCode());
            res.setErrmsg(getFisrtLine(ex.getMessage()));
        }
        /**
         * 权限相关
         * */
        //region 权限相关

        //endregion
        else {
            return null;
        }
        res.setData(ex.getLocalizedMessage());
        return res;
    }
}
