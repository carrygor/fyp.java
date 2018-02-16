package cn.com.youplus.tenants.common.model;

import cn.com.youplus.common.model.enums.ErrorUserCodeEnum;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;

import java.io.Serializable;

public class ErrorUser implements Serializable{

    private int errCode;

    private String desc;

    private int lineNum;

    private UpTenantsUser user;

    public ErrorUser() {

    }

    public ErrorUser(int errCode, int lineNum, UpTenantsUser user) {
        this.errCode = errCode;
        this.desc = ErrorUserCodeEnum.codeOf(errCode).getText();
        this.lineNum = lineNum;
        this.user = user;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public UpTenantsUser getUser() {
        return user;
    }

    public void setUser(UpTenantsUser user) {
        this.user = user;
    }
}
