package cn.com.carry.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum ErrorRegionCodeEnum {

    SUCC(0,"成功"),

    VAR_ERR(100,"变量异常"),
    NAME_NULL(101,"组织名称不能为空"),
    PARENT_ID_NULL(102,"上级组织为空或不存在"),
    LEVEL_NULL(103,"上级组织异常"),
    PROVINCE_NULL(104,"网点的省市区为必填项，请选择网点所在的省市区"),
    CITY_NULL(105,"网点的省市区为必填项，请选择网点所在的省市区"),
    DISTRICT_NULL(106,"网点的省市区为必填项，请选择网点所在的省市区"),
    REGION_NUM_NULL(107,"组织编号为空"),
    IS_STORE_NULL(108,"是否网点为必填项，请填写上是或者否"),

    NAME_DUPLICATE(201, "组织名称已经存在，不同组织的名称不能一样"),
    REGION_NUM_DUPLICATE(202, "组织编号已经存在，不同组织的编号不能一样"),
    NAME_LENGTH_ERROR(203, "组织名称的字数不能超过30字哦"),
    REGION_NUM_LENGTH_ERROR(204, "组织编号字数不能超过20字"),
    ADDRESS_LENGTH_ERROR(205, "详细地址的字数不能超过30字"),
    REGION_OUT_OF_LIMIT(206, "网点数量超过限制"),

    INSERT_ERR(300,"插入异常");


    private int code;
    private String text;

    ErrorRegionCodeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ErrorRegionCodeEnum codeOf(int code) {
        for (ErrorRegionCodeEnum errorRegionCodeEnum: ErrorRegionCodeEnum.values()) {
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
