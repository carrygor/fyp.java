package cn.com.carry.tenants.server.interceptor;

import org.apache.log4j.Logger;
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
public class TentantsInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(TentantsInterceptor.class);

    //region 动态注入

//    public UpTenantsRequestLogService getTenantsRequestLogService(HttpServletRequest request) {
//        if (upTenantsRequestLogService == null) {
//            synchronized (this) {
//                logger.info("动态加载upTenantsRequestLogService Bean");
//                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//                upTenantsRequestLogService = (UpTenantsRequestLogService) factory.getBean("upTenantsRequestLogService");
//            }
//        }
//        return upTenantsRequestLogService;
//    }
//
//    private UpTenantsRequestLogService upTenantsRequestLogService;

    //endregion
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        logger.info("收到请求:[" + request.getRequestURI() + "]");
        logger.info("时间:[" + new Date() + "]");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        if ("OPTIONS".equals(request.getMethod())) {
            return;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex)
            throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return;
        }

        logger.info("请求结束:[" + request.getRequestURI() + "]");

//        String methodName = (String)request.getAttribute(Constants.REQUEST_MODULE_KEY);
//        if (methodName.equals("error")) {
//            return;
//        }
//
//        HandlerMethod methodHandle = (HandlerMethod) handler;
//        NoApiLog noApiLog = methodHandle.getMethodAnnotation(NoApiLog.class);
//        if (noApiLog == null) {
//            Integer timeConsume = (int) (new Date().getTime() - (long) request.getAttribute(Constants.REQUEST_TIME_KEY));
//
//            Object company = request.getAttribute(Constants.REQUEST_TENANTS_COMPANY_KEY);
//            Object user = request.getAttribute(Constants.REQUEST_TENANTS_USER_KEY);
//            Long cid = 0L, uid = 0L;
//            if (company != null && company instanceof UpTenantsCompany) {
//                cid = ((UpTenantsCompany) company).getId();
//            }
//
//            if (user != null && user instanceof UpTenantsUser) {
//                uid = ((UpTenantsUser) user).getId();
//            }
//
//            UpTenantsRequestLog UpTenantsRequestLog= new UpTenantsRequestLog();
//            UpTenantsRequestLog.setIp(RequestUtil.getIpAddr(request))
//                    .setTimeConsume(timeConsume)
//                    .setUserId(uid)
//                    .setTenantsId(cid)
//                    .setRequestUri(request.getRequestURI());
//            getTenantsRequestLogService(request).insert(UpTenantsRequestLog);
//        }
    }

}
