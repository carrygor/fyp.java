package cn.com.youplus.apps.server.interceptor;

import cn.com.youplus.common.util.Constants;

import cn.com.youplus.common.util.CookieUtil;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 拦截用户信息
 * 在这里实现异常处理，用户检查，时间统计，API统计
 * @author lance
 * 2014-6-10下午9:57:20
 */
public class AppsInterceptor4User implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(AppsInterceptor4User.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        HandlerMethod methodHandle = (HandlerMethod) handler;
        String handlerName = methodHandle.getMethod().getName();
        request.setAttribute(Constants.REQUEST_TIME_KEY, new Date().getTime());
        request.setAttribute(Constants.REQUEST_MODULE_KEY, handlerName);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex)
            throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return;
        }
    }

}
