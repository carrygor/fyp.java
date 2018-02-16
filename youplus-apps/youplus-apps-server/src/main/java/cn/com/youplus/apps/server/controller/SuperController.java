package cn.com.youplus.apps.server.controller;

import cn.com.youplus.apps.common.exception.handler.AppsExceptionHandler;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;
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
        BaseResponse baseResponse = new AppsExceptionHandler().handle(exception);
        logger.info("统一异常处理：");
        //logger.info(exception.getMessage() + exception.getLocalizedMessage());
        logger.info("",exception);
        response.setHeader("errmsg", baseResponse.getErrmsg());
        response.setIntHeader("errcode", baseResponse.getErrcode());
        return baseResponse;
    }

    protected UpTenantsCompany getCompany(HttpServletRequest request) {
        Object object = request.getAttribute(Constants.REQUEST_TENANTS_COMPANY_KEY);
        if (object != null && object instanceof UpTenantsCompany)  {
            return (UpTenantsCompany)object;
        }
        return null;
    }

    protected UpTenantsUser getUser(HttpServletRequest request) {
        Object object = request.getAttribute(Constants.REQUEST_TENANTS_COMPANY_KEY);
        if (object != null && object instanceof UpTenantsUser)  {
            return (UpTenantsUser)object;
        }
        return null;
    }
}
