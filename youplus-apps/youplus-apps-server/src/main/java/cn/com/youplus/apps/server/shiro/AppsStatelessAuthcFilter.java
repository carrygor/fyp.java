package cn.com.youplus.apps.server.shiro;

import cn.com.youplus.common.exception.security.OauthFailedException;
import cn.com.youplus.common.util.AESUtil;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.RequestUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class AppsStatelessAuthcFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(AppsStatelessAuthcFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //TODO 目前只有q下面的接口需要，未来需要重新配置
        HttpServletRequest r = (HttpServletRequest)request;
        if (r.getRequestURI().contains("/q/") &&
                !r.getRequestURI().contains("qrconnect")) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.debug("get in onAccessDenied");
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter(Constants.APPS_PARAM_SIGN);
        //2、客户端传入的用户身份
        String timestamp = AESUtil.AESDecodeForWeb(request.getParameter(Constants.APPS_PARAM_TOKEN));
        String noise = request.getParameter(Constants.APPS_PARAM_NOISE);
        //3、客户端请求的参数列表
        Map<String, Object> params = new HashMap<>(request.getParameterMap());
        params.remove(Constants.APPS_PARAM_SIGN);

        //4、生成无状态Token
        AppsStatelessToken token = new AppsStatelessToken(timestamp, clientDigest, noise);
        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            String IP= RequestUtil.getIpAddr(httpServletRequest);

            logger.info("请求签名校验失败:[" + IP + "][" + httpServletRequest.getRequestURI() + "?" + httpServletRequest.getQueryString()+ "]");
            String origin = httpServletRequest.getHeader("Origin");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            onLoginFail(response); //6、登录失败
            return false;
        }
        request.setAttribute(Constants.APPS_SUBJECT_KEY, getSubject(request,response));
        return true;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException, OauthFailedException {
        logger.info("收到非法访问");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
