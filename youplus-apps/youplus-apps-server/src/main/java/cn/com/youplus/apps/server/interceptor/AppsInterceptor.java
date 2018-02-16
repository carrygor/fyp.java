package cn.com.youplus.apps.server.interceptor;

import cn.com.youplus.base.api.LogHubService;
import cn.com.youplus.common.annotation.NoApiLog;
import cn.com.youplus.common.model.log.ApiAccessLog;
import cn.com.youplus.common.util.*;
import com.alibaba.fastjson.JSONObject;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
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
public class AppsInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(AppsInterceptor.class);

    //region 动态注入



    public LogHubService getLogHubService(HttpServletRequest request) {
        if (logHubService == null) {
            synchronized (this) {
                logger.info("动态加载logHubService Bean");
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                logHubService = (LogHubService) factory.getBean("logHubService");
            }
        }
        return logHubService;
    }

    private LogHubService logHubService;

    //endregion
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        //在这里尝试设置Cookies
        String cookies = CookieUtil.getCookie(request, Constants.COOKIES_QUESTIONNIRE);
        if (ValueHelper.isNone(cookies)) {
            cookies = StringHelper.getNonceStr();
            CookieUtil.setCookie(response, Constants.COOKIES_QUESTIONNIRE, cookies, Constants.COOKIE_AGE);
        }
        String IP= RequestUtil.getIpAddr(request);
        request.setAttribute(Constants.COOKIES_QUESTIONNIRE, cookies);
        request.setAttribute(Constants.IP_KEY, IP);

        logger.info("收到请求:[" + IP + "][" + cookies + "][" + request.getRequestURI() + "?" + request.getQueryString()+ "]");
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

        Object cO = request.getAttribute(Constants.COOKIES_QUESTIONNIRE);
        String cookies = "";
        if (cO != null) {
            cookies = cO.toString();
        }

        Object ipO = request.getAttribute(Constants.IP_KEY);
        String IP = "";
        if (ipO != null) {
            IP = ipO.toString();
        }

        logger.info("请求结束:[" + IP + "][" + cookies + "][" + request.getRequestURI() + "]");

        String methodName = (String)request.getAttribute(Constants.REQUEST_MODULE_KEY);
        if (methodName.equals("error")) {
            return;
        }

        HandlerMethod methodHandle = (HandlerMethod) handler;
        NoApiLog noApiLog = methodHandle.getMethodAnnotation(NoApiLog.class);
        if (noApiLog == null) {
            Integer timeConsume = (int) (new Date().getTime() - (long) request.getAttribute(Constants.REQUEST_TIME_KEY));
            ApiAccessLog apiAccessLog= new ApiAccessLog();
            apiAccessLog.setIp(RequestUtil.getIpAddr(request));
            apiAccessLog.setTimeConsume(timeConsume);
            apiAccessLog.setRequestParameter(JSONObject.toJSONString(request.getParameterMap()));
            apiAccessLog.setCookies(cookies);
            apiAccessLog.setRequestUri(request.getRequestURI());
            String device = request.getHeader("User-Agent");
            apiAccessLog.setDevice(device);
            Browser browser = UserAgent.parseUserAgentString(device).getBrowser();
            OperatingSystem operatingSystem = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getOperatingSystem();
            if (device != null) {
                apiAccessLog.setBroswer(browser.getName());
            }
            if (operatingSystem != null) {
                apiAccessLog.setSystem(operatingSystem.getName());
            }
            apiAccessLog.setModuleName(methodName);
            apiAccessLog.setErrorMsg(response.getHeader("errmsg"));
            apiAccessLog.setReturnCode(ValueHelper.tryParse(response.getHeader("errcode"), 0));
            try {
                getLogHubService(request).putLog(apiAccessLog);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

}
