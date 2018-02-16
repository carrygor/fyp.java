package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum QuestionTypeEnum {

    单选(10,"单选"),
    多选(20,"多选"),
    排序(30,"排序"),
    填空(40,"填空"),
    评分(50,"评分"),
    服务方式(60,"服务方式"),
    是非(70,"是非"),
    总体满意度(80,"总体满意度"),
    分项满意度(90,"分项满意度"),
    NPS(100,"NPS"),
    个人信息(110,"个人信息"),
    时间(120,"时间"),
    手机验证(130,"手机验证"),
    其他(999,"其他"),
    ;

    private int code;
    private String type;

    QuestionTypeEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public static QuestionTypeEnum codeOf(int code) {
        for (QuestionTypeEnum questionTypeEnum : QuestionTypeEnum.values()) {
            if (questionTypeEnum.code == code) {
                return questionTypeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
