package cn.com.youplus.cms.common.form;

import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.validation.annotation.Check;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class TenantsCompanyAddForm extends BaseForm implements Serializable{

    private String tenantsType;

    @Check(notNull = true)
    private String name;

    @Check(notNull = true)
    private String companyName;

    private String address;

    private String province;

    private String city;

    private String district;

    private String phone;

    private String hangye;

    private long authExpiredTime;

    private long authStartTime;

    @Check(notNull = true)
    private String logoImg;

    private String contactName;

    @Check(notNull = true)
    private String domainName;

    private int maxSiteNum;

    private int maxUserNum;

    private int maxReportNum;

    @Check(notNull = true)
    private String user4userName;

    private String user4email;

    private String user4phoneNum;

    @Check(notNull = true)
    private String user4password;

    private String functionStr;

    public String getFunctionStr() {
        return functionStr;
    }

    public void setFunctionStr(String functionStr) {
        this.functionStr = functionStr;
    }

    public String getUser4userName() {
        return user4userName;
    }

    public void setUser4userName(String user4userName) {
        this.user4userName = user4userName;
    }

    public String getUser4email() {
        return user4email;
    }

    public void setUser4email(String user4email) {
        this.user4email = user4email;
    }

    public String getUser4phoneNum() {
        return user4phoneNum;
    }

    public void setUser4phoneNum(String user4phoneNum) {
        this.user4phoneNum = user4phoneNum;
    }

    public String getUser4password() {
        return user4password;
    }

    public void setUser4password(String password) {
        this.user4password = password;
    }

    public String getTenantsType() {
        return tenantsType;
    }

    public void setTenantsType(String tenantsType) {
        this.tenantsType = tenantsType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHangye() {
        return hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    public long getAuthExpiredTime() {
        return authExpiredTime;
    }

    public void setAuthExpiredTime(long authExpiredTime) {
        this.authExpiredTime = authExpiredTime;
    }

    public long getAuthStartTime() {
        return authStartTime;
    }

    public void setAuthStartTime(long authStartTime) {
        this.authStartTime = authStartTime;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public int getMaxSiteNum() {
        return maxSiteNum;
    }

    public void setMaxSiteNum(int maxSiteNum) {
        this.maxSiteNum = maxSiteNum;
    }

    public int getMaxUserNum() {
        return maxUserNum;
    }

    public void setMaxUserNum(int maxUserNum) {
        this.maxUserNum = maxUserNum;
    }

    public int getMaxReportNum() {
        return maxReportNum;
    }

    public void setMaxReportNum(int maxReportNum) {
        this.maxReportNum = maxReportNum;
    }


}
