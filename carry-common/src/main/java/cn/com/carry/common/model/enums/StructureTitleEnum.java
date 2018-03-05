package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum StructureTitleEnum {

    组织名称(1,"组织名称"),
    上级组织(2,"上级组织"),
    组织编号(3,"组织编号"),
    上级组织编号(4,"上级组织编号"),
    管理层级(5,"管理层级"),
    是否网点(6,"是否网点"),
    省(7,"省"),
    市(8,"市"),
    区(9,"区"),
    详细地址(10,"详细地址");

    private int index;
    private String name;

    StructureTitleEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static StructureTitleEnum containOf(String name) {
        for (StructureTitleEnum structureTitleEnum: StructureTitleEnum.values()) {
            if (name.contains(structureTitleEnum.name)) {
                return structureTitleEnum;
            }
        }
        return null;
    }

}
