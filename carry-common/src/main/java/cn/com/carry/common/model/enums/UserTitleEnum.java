package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum UserTitleEnum {

    DEFAULT(0, "DEFAULT"),
    员工姓名(1,"员工姓名"),
    系统登录名(2,"系统登录名"),
    员工编号(3,"员工编号"),
    组织名称(4,"组织名称"),
    组织编号(5,"组织编号"),
    组织负责人(6,"组织负责人"),
    手机号(7,"手机号"),
    邮箱(8,"邮箱"),
    初始密码(9,"初始密码");

    private int index;
    private String name;

    UserTitleEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static UserTitleEnum containOf(String name) {
        for (UserTitleEnum structureTitleEnum: UserTitleEnum.values()) {
            if (name.contains(structureTitleEnum.name)) {
                return structureTitleEnum;
            }
        }
        return null;
    }

}
