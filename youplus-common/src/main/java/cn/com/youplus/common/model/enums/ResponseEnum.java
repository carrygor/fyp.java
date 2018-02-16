package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum ResponseEnum {

    SUCCESS(0,"成功"),
    EXCEPTION(1,"系统异常"),
    ERROR(-1,"系统繁忙"),

    //操作的错误
    //region 权限
    OAUTH(101,""),
    WEIXIN_ONLY(102,""),
    ROLE_REGISTER(110,""),
    ROLE_AUTH(120,""),
    ROLE_UNAUDIT(130,""),
    ROLE_UNDEPOSIT(140,""),

    //endregion

    AlERT(201,""),
    AlERT_RETURN(202,""),
    AlERT_REFRESH(203,""),
    CONFIRM(210,""),
    CONFIRM_RETURN(211,""),
    CONFIRM_PATH(212,""),
    GO_PATH(220,""),

    NO_FUNCTION(301,""),
    //系统错误
    NO_POWER(401,"权限不足"),
    NO_RESOURCE(404,"不存在的资源"),

    //一般错误  1001 ~ 2000
    INVALID_PARAMS(1001, "参数错误"),
    INVALID_INPUT_STREAM(1002,"文件流错误"),
    INVALID_FILE_FORMAT(1003,"不支持的文件类型"),
    INVALID_WEIXIN_PARAMS(1004,"微信参数错误"),
    //订单错误  2001 ~ 3000
    WEIXIN_PAY_CONFIGS_ERROR(2001, "获取微信支付参数错误"),


    //权限错误

    //第三方特殊的code
    INVALID_PARAMTER(40000, "参数错误！"),
    SIGN_ERROR(40001, "非法签名（验证失败）"),
    INVALID_CUSTOMER_CODE(40002, "非法的客户编号"),
    INVALID_DATE_FORMAT(40003, "非法的报告日期（date格式不正确）"),
    DATA_ERROR(40004, "数据错误！"),
    ;

    private int code;
    private String errMsg;

    ResponseEnum(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
