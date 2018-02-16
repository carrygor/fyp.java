package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum ErrorUserCodeEnum {

    SUCC(0,"成功"),

    REAL_NAME_NULL(101, "员工姓名为必填项，不能为空"),
    USER_NAME_NULL(102, "登陆账户为必填项，不能为空"),
    EMAIL_NULL(103, "邮箱为必填项，请输入正确的员工邮箱地址"),
    IS_LEADER_NULL(104, "组织负责人栏只能填写是或者否"),

    LEADER_DUPLICATE(201, "该组织已经存在负责人，一个具体组织只能有一个负责人"),
    REGION_LEADER_CONFLICT(202, "没有所属组织不能为负责人"),
    INCORRECT_PHONE_NUM(203, "手机号格式不正确，请输入正确的手机号"),
    INCORRECT_EMAIL(204, "邮箱格式不正确，请输入正确的员工邮箱地址"),
    DUPLICATE_USER_NAME(205, "登陆账户已经存在，不能使用相同的登陆账户"),
    DUPLICATE_USER_NUM(206, "员工编号已经使用，不同员工不能使用相同的员工编号"),
    ILLEGAL_USER_NAME(207, "登陆账户含有非法字符，只支持英文字母，数字，和符号@，_的组合"),
    ILLEGAL_USER_NUM(208, "员工编号包含非法字符，只支持英文字母和数字的组合"),
    ILLEGAL_PASSWORD(209, "初始密码包含非法字符，只支持英文字母和数字的组合"),
    ERR_REGION_NAME(210, "组织名称不存在，需保证和系统中设置的组织名称完全一致"),
    REAL_NAME_LENGTH_ERR(211, "员工姓名字数不能超过10字"),
    USER_NAME_LENGTH_ERR(212, "登陆账户长度不能超过30个字符"),
    USER_NUM_LENGTH_ERR(213, "员工编号长度不能超过20个字符"),
    PASSWORD_LENGTH_ERR(214, "初始密码的长度不正确，长度应该为6-20个字符之间"),
    USER_OUT_OF_LIMIT(215, "用户数量超过限制"),

    INSERT_FAIL(300, "插入失败");

    private int code;
    private String text;

    ErrorUserCodeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ErrorUserCodeEnum codeOf(int code) {
        for (ErrorUserCodeEnum errorRegionCodeEnum: ErrorUserCodeEnum.values()) {
            if (code == errorRegionCodeEnum.getCode()) {
                return errorRegionCodeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
