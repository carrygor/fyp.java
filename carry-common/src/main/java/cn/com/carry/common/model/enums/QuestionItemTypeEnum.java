package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum QuestionItemTypeEnum {

    选项(1,"选项"),
    填空(2,"填空"),
    分数选择(3,"分数选择"),
    选项填空(4,"选项填空"),
    ;

    private int code;
    private String type;

    QuestionItemTypeEnum(int code, String type) {
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
