package cn.com.carry.tenants.server.interceptor;

import cn.com.carry.common.annotation.Permission;
import cn.com.carry.common.exception.security.NoPermissionException;
import cn.com.carry.common.model.base.BaseResponse;
import cn.com.carry.common.util.AESUtil;
import cn.com.carry.common.util.Constants;
import cn.com.carry.common.util.ValueHelper;
import cn.com.carry.tenants.common.exception.handler.TenantsExceptionHandler;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
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
public class TenantsInterceptor4User implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(TenantsInterceptor4User.class);

//    private UpTenantsCompanyService upTenantsCompanyService;
//    private UpTenantsUserService upTenantsUserService;
//
//    public UpTenantsCompanyService getUpTenantsCompanyService(HttpServletRequest request) {
//        if (upTenantsCompanyService == null) {
//            synchronized (this) {
//                logger.info("动态加载UpTenantsCompanyService Bean");
//                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//                upTenantsCompanyService = (UpTenantsCompanyService) factory.getBean("upTenantsCompanyService");
//            }
//        }
//        return upTenantsCompanyService;
//    }
//
//    public UpTenantsUserService getUpTenantsUserService(HttpServletRequest request) {
//        if (upTenantsUserService == null) {
//            synchronized (this) {
//                logger.info("动态加载UpTenantsUserService Bean");
//                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//                upTenantsUserService = (UpTenantsUserService) factory.getBean("upTenantsUserService");
//            }
//        }
//        return upTenantsUserService;
//    }


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        try {
            HandlerMethod methodHandle = (HandlerMethod) handler;
            String handlerName = methodHandle.getMethod().getName();
            request.setAttribute(Constants.REQUEST_TIME_KEY, new Date().getTime());
            request.setAttribute(Constants.REQUEST_MODULE_KEY, handlerName);
            logger.info("收到请求:[" + request.getRequestURI() + "] 处理名:[" + handlerName + "]");
            if (handlerName.equals("error")) {
                return false;
            }
            preHandleDo(request, response, methodHandle);
        } catch (Exception ex) {
            logger.info("",ex);
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            String h = response.getHeader("Access-Control-Allow-Origin");
            if (ValueHelper.isNone(h)) {
                String origin = request.getHeader("Origin");
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Credentials", "true");
            }
            BaseResponse baseResponse = new TenantsExceptionHandler().handle(ex);
            try {
                response.getWriter().write(JSONObject.toJSONString(baseResponse));
                response.getWriter().flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }


        return true;
    }

    void preHandleDo(HttpServletRequest request, HttpServletResponse response, HandlerMethod methodHandle) throws Exception{
        Long id = 0L;
        String tokenStr = request.getParameter(Constants.CMS_PARAM_TOKEN);
        if (tokenStr != null) {
            id = ValueHelper.tryParseLong(AESUtil.AESDecodeForWeb(tokenStr), 0L);
        }

        Permission permission = methodHandle.getMethodAnnotation(Permission.class);
        if (permission != null) {

            String function = permission.function();
            Object object = request.getAttribute(Constants.TENANTS_SUBJECT_KEY);
            Subject subject = (Subject) object;

            String permissionValue = permission.value();

            if (!ValueHelper.isNone(permissionValue)) {
                if (subject != null) {
                    try {
                        subject.checkPermission(permissionValue);
                    } catch (AuthorizationException e) {
                        throw new NoPermissionException("您无权访问该资源!");
                    }
                }
            }
        }
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
