package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum QuestionFunctionTypeEnum {
    题目(0,"题目"),
    功能(1,"功能")
    ;

    private int code;
    private String type;

    QuestionFunctionTypeEnum(int code, String type) {
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
