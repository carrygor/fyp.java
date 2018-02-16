package cn.com.youplus.common.exception.interaction;

import cn.com.youplus.common.model.enums.RouterGoTypeEnum;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class ConfirmPathException extends Exception {
    /**
     * 弹窗提醒，点击确定跳转到指定路径
     * @param message 传入提醒的内容
     */

    private String path;
    private String cancel;
    private String confirm;
    private RouterGoTypeEnum type;

    public ConfirmPathException(String message,String cancel, String confirm, String path, RouterGoTypeEnum type) {
        super(message);
        this.path = path;
        this.cancel = cancel;
        this.confirm = confirm;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public RouterGoTypeEnum getType() {
        return type;
    }

    public void setType(RouterGoTypeEnum type) {
        this.type = type;
    }
}
