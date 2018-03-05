package cn.com.carry.common.exception.interaction;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class GoPathException extends Exception {
    /**
     * 跳转到指定路径
     * @param message 传入提醒的内容
     */

    private String path;
    private String type;

    public GoPathException(String message, String path, String type) {
        super(message);
        this.path = path;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
