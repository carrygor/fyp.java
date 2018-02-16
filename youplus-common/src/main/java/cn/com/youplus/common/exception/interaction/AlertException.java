package cn.com.youplus.common.exception.interaction;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class AlertException extends Exception {
    /**
     * 弹窗提醒
     * @param message 传入提醒的内容
     */
    public AlertException(String message) {
        super(message);
    }
}
