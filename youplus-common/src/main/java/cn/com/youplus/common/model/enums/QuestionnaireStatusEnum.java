package cn.com.youplus.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum QuestionnaireStatusEnum {

    编辑中(100,"编辑中"),
    待收集(150,"待收集"),
    收集中(200,"收集中"),
    已停用(300,"已停用"),
    已过期(400,"已过期"),
    已删除(500,"已删除")
    ;
    //ENUM('编辑中', '收集中', '已停用', '已过期')

    private int code;
    private String type;

    QuestionnaireStatusEnum(int code, String type) {
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
