package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum QuestionRequiredTypeEnum {
    非必填(0,"非必填"),
    必填(1,"必填")
    ;

    private int code;
    private String type;

    QuestionRequiredTypeEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
