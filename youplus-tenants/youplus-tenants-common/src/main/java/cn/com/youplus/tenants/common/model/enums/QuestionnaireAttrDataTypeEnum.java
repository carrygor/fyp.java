package cn.com.youplus.tenants.common.model.enums;

public enum QuestionnaireAttrDataTypeEnum {

    LONG("LONG"),
    STRING("STRING"),
    DATE("DATE"),
    TIME("TIME"),
    DATETIME("DATETIME"),
    DOUBLE("DOUBLE"),
    TIMESTAMP("TIMESTAMP"),
    ;

    private String type;

    QuestionnaireAttrDataTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public QuestionnaireAttrDataTypeEnum setType(String type) {
        this.type = type;
        return this;
    }
}
