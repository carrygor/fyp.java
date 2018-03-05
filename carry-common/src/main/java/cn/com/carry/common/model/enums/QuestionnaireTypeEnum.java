package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum QuestionnaireTypeEnum {

    普通(100,"普通"),
    考试(200,"考试"),
    其他(300,"其他"),
    ;

    private int code;
    private String type;

    QuestionnaireTypeEnum(int code, String type) {
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
