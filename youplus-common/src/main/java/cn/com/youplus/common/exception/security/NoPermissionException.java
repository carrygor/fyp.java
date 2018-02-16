package cn.com.youplus.common.exception.security;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class NoPermissionException extends Exception {
    /**
     * 弹窗提醒
     * @param message 传入提醒的内容
     */
    public NoPermissionException(String message) {
        super(message);
    }
}
