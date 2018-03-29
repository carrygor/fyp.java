package cn.com.carry.tenants.common.model.enums;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum OriginDataStatusEnum {

    CREATE("create"),
    SUCCESS("success"),
    FAIL("fail"),;

    private String status;

    OriginDataStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
