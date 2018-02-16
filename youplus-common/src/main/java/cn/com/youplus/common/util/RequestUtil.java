package cn.com.youplus.common.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * request工具类
 * Created by 严汉羽 on 2016/12/12.
 */
public class RequestUtil {
	private static Logger logger = Logger.getLogger(RequestUtil.class);
	/**
	 * 判断是否为微信浏览器
	 * @param request
	 * @return
	 */
	public static boolean isWeixinBrowser(HttpServletRequest request) {
		String agent =  request.getHeader("user-agent").toLowerCase();
		if (agent.contains("micromess")) {
			return true;
		}

		logger.error("agent:" + agent);
		return false;
	}

	/**
	 * 移除request指定参数
	 * @param request
	 * @param paramName
	 * @return
	 */
	public String removeParam(HttpServletRequest request, String paramName) {
		String queryString = "";
		Enumeration keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (key.equals(paramName)) {
				continue;
			}
			if (queryString.equals("")) {
				queryString = key + "=" + request.getParameter(key);
			} else {
				queryString += "&" + key + "=" + request.getParameter(key);
			}
		}
		return queryString;
	}

	/**
	 * 获取请求basePath
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		StringBuffer basePath = new StringBuffer();
		String scheme = request.getScheme();
		String domain = request.getServerName();
		int port = request.getServerPort();
		basePath.append(scheme);
		basePath.append("://");
		basePath.append(domain);
		if("http".equalsIgnoreCase(scheme) && 80 != port) {
			basePath.append(":").append(String.valueOf(port));
		} else if("https".equalsIgnoreCase(scheme) && port != 443) {
			basePath.append(":").append(String.valueOf(port));
		}
		return basePath.toString();
	}

	public static String getDomain(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (referer.contains("localhost")) {
			return "localhost";
		}
		String regex = "(//)(\\S+?)(\\.)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(referer);
		matcher.find();
		String domain = matcher.group(2);
		return domain;
	}

	/**
	 * 获取ip工具类，除了getRemoteAddr，其他ip均可伪造
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("Cdn-Src-Ip");    // 网宿cdn的真实ip
		if (ip == null || ip.length() == 0 || "".equals(ip.trim()) || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");  // 获取代理ip
		}

		if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");   // 蓝讯cdn的真实ip
		}
		if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");  // 获取代理ip
		}
		if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP"); // 获取代理ip
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 获取代理ip
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr(); // 获取真实ip
		}
		return ip;
	}

	/***
	 * 获取 request 中 json 字符串的内容
	 *
	 * @param request
	 * @return : <code>byte[]</code>
	 * @throws IOException
	 */
	public static String getRequestJsonString(HttpServletRequest request)
			throws IOException {
		String submitMehtod = request.getMethod();
		// GET
		if (submitMehtod.equals("GET")) {
			return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
			// POST
		} else {
			return getRequestPostStr(request);
		}
	}

	/**
	 * 描述:获取 post 请求的 byte[] 数组
	 * <pre>
	 * 举例：
	 * </pre>
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static byte[] getRequestPostBytes(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		if(contentLength<0){
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i,
					contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	/**
	 * 描述:获取 post 请求内容
	 * <pre>
	 * 举例：
	 * </pre>
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestPostStr(HttpServletRequest request)
			throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}



}
