package cn.com.youplus.common.model.log;

import cn.com.youplus.common.model.log.annotation.UpLogItem;
import cn.com.youplus.common.model.log.annotation.UpLogStore;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@UpLogStore(name = "api-access-log")
public class ApiAccessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ip地址
     */
    @UpLogItem(name="ip")
	private String ip;

    /**
     * 请求uri
     */
    @UpLogItem(name="requestUri")
	private String requestUri;
    /**
     * 模块id
     */
    @UpLogItem(name="moduleName")
	private String moduleName;
    /**
     * 处理时长
     */
    @UpLogItem(name="timeConsume")
	private int timeConsume = 0;

    /**
     * 如果超长会非截断
     */
    @UpLogItem(name="requestParameter")
	private String requestParameter;

    @UpLogItem(name="addTime")
	private Date addTime;

    @UpLogItem(name="returnCode")
	private int returnCode = 0;

    @UpLogItem(name="errorMsg")
	private String errorMsg;

    /**
     * 浏览器cookies
     */
    @UpLogItem(name="cookies")
	private String cookies;

    @UpLogItem(name="broswer")
	private String broswer;

    @UpLogItem(name="system")
	private String system;

    @UpLogItem(name="device")
	private String device;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getRequestParameter() {
		return requestParameter;
	}

	public void setRequestParameter(String requestParameter) {
		this.requestParameter = requestParameter;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public String getBroswer() {
		return broswer;
	}

	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public int getTimeConsume() {
		return timeConsume;
	}

	public void setTimeConsume(int timeConsume) {
		this.timeConsume = timeConsume;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
