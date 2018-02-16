package cn.com.youplus.tenants.common.model;

import cn.com.youplus.common.model.enums.ErrorRegionCodeEnum;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public class ErrorRegion implements Serializable {

    private int errCode;

    private String desc;

    private UpTenantsRegion region;

    private int lineNum;

    public ErrorRegion(){

    }

    public ErrorRegion(int errCode, UpTenantsRegion region, int lineNum) {
        this.errCode = errCode;
        this.region = region;
        this.lineNum = lineNum;
        this.desc = ErrorRegionCodeEnum.codeOf(errCode).getText();
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

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
        this.desc = ErrorRegionCodeEnum.codeOf(errCode).getText();
    }

    public UpTenantsRegion getRegion() {
        return region;
    }

    public void setRegion(UpTenantsRegion region) {
        this.region = region;
    }
}
