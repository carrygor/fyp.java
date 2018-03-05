package cn.com.carry.common.exception.security;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class OauthFailedException extends Exception {
    /**
     * 弹窗提醒
     * @param message 传入提醒的内容
     */
    public OauthFailedException(String message) {
        super(message);
    }
}
