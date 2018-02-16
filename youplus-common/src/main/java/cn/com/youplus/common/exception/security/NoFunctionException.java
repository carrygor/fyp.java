package cn.com.youplus.common.exception.security;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class NoFunctionException extends Exception {
    /**
     * 没有开通功能
     * @param message 传入提醒的内容
     */
    public NoFunctionException(String message) {
        super(message);
    }

}
