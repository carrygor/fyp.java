package cn.com.carry.common.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/19.
 */
public class SystemConfigVersionReference implements Serializable {
    private static SystemConfigVersionReference systemConfigVersionReference;

    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    private SystemConfigVersionReference() {
    }

    public static SystemConfigVersionReference getInstance() {
        if (systemConfigVersionReference == null) {
            synchronized (SystemConfigVersionReference.class) {
                systemConfigVersionReference = new SystemConfigVersionReference();
            }
        }
        return systemConfigVersionReference;
    }

    //region 系统配置
    private long appsSystemConfigLocalRef = 0;

    private long appsSystemConfigSystemRef = 1;

    private long cmsSystemConfigLocalRef = 0;

    private long cmsSystemConfigSystemRef = 1;

    private long thirdpartySystemConfigLocalRef = 0;

    private long thirdpartySystemConfigSystemRef = 1;

    private long tenantsSystemConfigLocalRef = 0;

    private long tenantsSystemConfigSystemRef = 1;

    public long getAppsSystemConfigLocalRef() {
        return appsSystemConfigLocalRef;
    }

    public void setAppsSystemConfigLocalRef(long appsSystemConfigLocalRef) {
        this.appsSystemConfigLocalRef = appsSystemConfigLocalRef;
    }

    public long getAppsSystemConfigSystemRef() {
        return appsSystemConfigSystemRef;
    }

    public void setAppsSystemConfigSystemRef(long appsSystemConfigSystemRef) {
        this.appsSystemConfigSystemRef = appsSystemConfigSystemRef;
    }

    public long getCmsSystemConfigLocalRef() {
        return cmsSystemConfigLocalRef;
    }

    public void setCmsSystemConfigLocalRef(long cmsSystemConfigLocalRef) {
        this.cmsSystemConfigLocalRef = cmsSystemConfigLocalRef;
    }

    public long getCmsSystemConfigSystemRef() {
        return cmsSystemConfigSystemRef;
    }

    public void setCmsSystemConfigSystemRef(long cmsSystemConfigSystemRef) {
        this.cmsSystemConfigSystemRef = cmsSystemConfigSystemRef;
    }

    public long getThirdpartySystemConfigLocalRef() {
        return thirdpartySystemConfigLocalRef;
    }

    public void setThirdpartySystemConfigLocalRef(long thirdpartySystemConfigLocalRef) {
        this.thirdpartySystemConfigLocalRef = thirdpartySystemConfigLocalRef;
    }

    public long getThirdpartySystemConfigSystemRef() {
        return thirdpartySystemConfigSystemRef;
    }

    public void setThirdpartySystemConfigSystemRef(long thirdpartySystemConfigSystemRef) {
        this.thirdpartySystemConfigSystemRef = thirdpartySystemConfigSystemRef;
    }

    public long getTenantsSystemConfigLocalRef() {
        return tenantsSystemConfigLocalRef;
    }

    public void setTenantsSystemConfigLocalRef(long tenantsSystemConfigLocalRef) {
        this.tenantsSystemConfigLocalRef = tenantsSystemConfigLocalRef;
    }

    public long getTenantsSystemConfigSystemRef() {
        return tenantsSystemConfigSystemRef;
    }

    public void setTenantsSystemConfigSystemRef(long tenantsSystemConfigSystemRef) {
        this.tenantsSystemConfigSystemRef = tenantsSystemConfigSystemRef;
    }

    //endregion

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
