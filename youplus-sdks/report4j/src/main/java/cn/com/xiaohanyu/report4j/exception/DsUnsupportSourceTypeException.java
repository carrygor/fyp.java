package cn.com.xiaohanyu.report4j.exception;

/**
 * Created by 严汉羽 on 2017/11/27.
 */
public class DsUnsupportSourceTypeException extends Exception {

    /**
     * 不支持的数据源
     */
    public DsUnsupportSourceTypeException() {
    }

    public DsUnsupportSourceTypeException(String msg) {
        super(msg);
    }
}
