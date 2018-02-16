package cn.com.youplus.tenants.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum TaskTypeEnum {
    问卷原始数据导出(1,"问卷原始数据导出"),
    数据统计运算(2,"数据统计运算"),
    待添加(99,"待添加")


    //ENUM('问卷原始数据导出', '待添加')
    ;

    private int code;
    private String type;

    TaskTypeEnum(int code, String type) {
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
