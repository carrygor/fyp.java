package cn.com.youplus.tenants.server.filter;

import cn.com.youplus.common.exception.security.OauthFailedException;
import cn.com.youplus.common.util.AESUtil;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.tenants.server.realm.StatelessToken;
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
public class StatelessAuthcFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (((HttpServletRequest)request).getRequestURI().contains("login")) {
            return true;
        }
        if (((HttpServletRequest)request).getRequestURI().contains("upload")) {
            return true;
        }
        if (((HttpServletRequest)request).getRequestURI().contains("oauth")) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.debug("get in onAccessDenied");
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter(Constants.TENANTS_PARAM_SIGN);
        //2、客户端传入的用户身份
        String username = AESUtil.AESDecodeForWeb(request.getParameter(Constants.TENANTS_PARAM_TOKEN));
        //3、客户端请求的参数列表
        Map<String, Object> params = new HashMap<>(request.getParameterMap());
        params.remove(Constants.TENANTS_PARAM_SIGN);

        //4、生成无状态Token
        StatelessToken token = new StatelessToken(username, params, clientDigest);
        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            //e.printStackTrace();
            String origin = ((HttpServletRequest)request).getHeader("Origin");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            onLoginFail(response); //6、登录失败
            return false;
        }
        request.setAttribute(Constants.TENANTS_SUBJECT_KEY, getSubject(request,response));
        return true;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException, OauthFailedException {
        logger.info("get in onLoginFail");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
