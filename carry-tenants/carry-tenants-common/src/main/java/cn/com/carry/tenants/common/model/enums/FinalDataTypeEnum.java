package cn.com.carry.tenants.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum FinalDataTypeEnum {

    处罚("处罚"),
    解除处罚("解除处罚"),
    其他("其他"),
    ;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    FinalDataTypeEnum(String type) {

        this.type = type;
    }
}
