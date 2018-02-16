package cn.com.youplus.cms.server.controller;

import cn.com.youplus.cms.common.exception.handler.CmsExceptionHandler;
import cn.com.youplus.common.model.base.BaseResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 严汉羽 on 2017/10/11.
 * 这是一个虚类，每个集成的类都需要实现统一的一场处理方法。
 */
public abstract class SuperController {
    private final static Logger logger = Logger.getLogger(SuperController.class);

    /**
     * 统一异常处理接口
     * @param request
     * @param response
     * @param exception
     */
    public abstract BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException;

    /**
     * 统一异常处理
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler(value = Throwable.class)
    public BaseResponse exceptionRealHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        BaseResponse baseResponse = new CmsExceptionHandler().handle(exception);
        logger.error("统一异常处理：", exception);
        response.setHeader("errmsg", baseResponse.getErrmsg());
        response.setIntHeader("errcode", baseResponse.getErrcode());
        return baseResponse;
    }
}
