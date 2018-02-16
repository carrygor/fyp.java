package cn.com.youplus.model.auto.entity.tenants;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_tenants_company")
public class UpTenantsCompany extends Model<UpTenantsCompany> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 租户类型
     */
	@TableField("tenants_type")
	private String tenantsType;
    /**
     * 名字
     */
	private String name;
	@TableField("company_name")
	private String companyName;
	private String address;
	private String province;
	private String city;
	private String district;
	private String phone;
	private String hangye;
    /**
     * 授权到期时间
     */
	@TableField("auth_expired_time")
	private Date authExpiredTime;
    /**
     * 授权开始时间
     */
	@TableField("auth_start_time")
	private Date authStartTime;
    /**
     * 企业logo
     */
	@TableField("logo_img")
	private String logoImg;
    /**
     * 联系人姓名
     */
	@TableField("contact_name")
	private String contactName;
    /**
     * 企业域名
     */
	@TableField("domain_name")
	private String domainName;
    /**
     * 对应的用户id
     */
	@TableField("tenants_user_id")
	private Long tenantsUserId;
    /**
     * 最大网点数量
     */
	@TableField("max_site_num")
	private Integer maxSiteNum;
    /**
     * 最大用户数量
     */
	@TableField("max_user_num")
	private Integer maxUserNum;
    /**
     * 最大可收集的问卷数量
     */
	@TableField("max_report_num")
	private Integer maxReportNum;
    /**
     * 租户的功能模块jsonStr
     */
	@TableField("function_str")
	private String functionStr;
    /**
     * 乐观锁
     */
	@Version
	private Integer version;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("template_json")
	private String templateJson;
    /**
     * 调用接口使用的秘钥
     */
	@TableField("api_secret")
	private String apiSecret;


	public Long getId() {
		return id;
	}

	public UpTenantsCompany setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTenantsType() {
		return tenantsType;
	}

	public UpTenantsCompany setTenantsType(String tenantsType) {
		this.tenantsType = tenantsType;
		return this;
	}

	public String getName() {
		return name;
	}

	public UpTenantsCompany setName(String name) {
		this.name = name;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public UpTenantsCompany setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public UpTenantsCompany setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public UpTenantsCompany setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UpTenantsCompany setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public UpTenantsCompany setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UpTenantsCompany setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getHangye() {
		return hangye;
	}

	public UpTenantsCompany setHangye(String hangye) {
		this.hangye = hangye;
		return this;
	}

	public Date getAuthExpiredTime() {
		return authExpiredTime;
	}

	public UpTenantsCompany setAuthExpiredTime(Date authExpiredTime) {
		this.authExpiredTime = authExpiredTime;
		return this;
	}

	public Date getAuthStartTime() {
		return authStartTime;
	}

	public UpTenantsCompany setAuthStartTime(Date authStartTime) {
		this.authStartTime = authStartTime;
		return this;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public UpTenantsCompany setLogoImg(String logoImg) {
		this.logoImg = logoImg;
		return this;
	}

	public String getContactName() {
		return contactName;
	}

	public UpTenantsCompany setContactName(String contactName) {
		this.contactName = contactName;
		return this;
	}

	public String getDomainName() {
		return domainName;
	}

	public UpTenantsCompany setDomainName(String domainName) {
		this.domainName = domainName;
		return this;
	}

	public Long getTenantsUserId() {
		return tenantsUserId;
	}

	public UpTenantsCompany setTenantsUserId(Long tenantsUserId) {
		this.tenantsUserId = tenantsUserId;
		return this;
	}

	public Integer getMaxSiteNum() {
		return maxSiteNum;
	}

	public UpTenantsCompany setMaxSiteNum(Integer maxSiteNum) {
		this.maxSiteNum = maxSiteNum;
		return this;
	}

	public Integer getMaxUserNum() {
		return maxUserNum;
	}

	public UpTenantsCompany setMaxUserNum(Integer maxUserNum) {
		this.maxUserNum = maxUserNum;
		return this;
	}

	public Integer getMaxReportNum() {
		return maxReportNum;
	}

	public UpTenantsCompany setMaxReportNum(Integer maxReportNum) {
		this.maxReportNum = maxReportNum;
		return this;
	}

	public String getFunctionStr() {
		return functionStr;
	}

	public UpTenantsCompany setFunctionStr(String functionStr) {
		this.functionStr = functionStr;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsCompany setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsCompany setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsCompany setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsCompany setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getTemplateJson() {
		return templateJson;
	}

	public UpTenantsCompany setTemplateJson(String templateJson) {
		this.templateJson = templateJson;
		return this;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public UpTenantsCompany setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_TYPE = "tenants_type";

	public static final String NAME = "name";

	public static final String COMPANY_NAME = "company_name";

	public static final String ADDRESS = "address";

	public static final String PROVINCE = "province";

	public static final String CITY = "city";

	public static final String DISTRICT = "district";

	public static final String PHONE = "phone";

	public static final String HANGYE = "hangye";

	public static final String AUTH_EXPIRED_TIME = "auth_expired_time";

	public static final String AUTH_START_TIME = "auth_start_time";

	public static final String LOGO_IMG = "logo_img";

	public static final String CONTACT_NAME = "contact_name";

	public static final String DOMAIN_NAME = "domain_name";

	public static final String TENANTS_USER_ID = "tenants_user_id";

	public static final String MAX_SITE_NUM = "max_site_num";

	public static final String MAX_USER_NUM = "max_user_num";

	public static final String MAX_REPORT_NUM = "max_report_num";

	public static final String FUNCTION_STR = "function_str";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String TEMPLATE_JSON = "template_json";

	public static final String API_SECRET = "api_secret";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsCompany{" +
			"id=" + id +
			", tenantsType=" + tenantsType +
			", name=" + name +
			", companyName=" + companyName +
			", address=" + address +
			", province=" + province +
			", city=" + city +
			", district=" + district +
			", phone=" + phone +
			", hangye=" + hangye +
			", authExpiredTime=" + authExpiredTime +
			", authStartTime=" + authStartTime +
			", logoImg=" + logoImg +
			", contactName=" + contactName +
			", domainName=" + domainName +
			", tenantsUserId=" + tenantsUserId +
			", maxSiteNum=" + maxSiteNum +
			", maxUserNum=" + maxUserNum +
			", maxReportNum=" + maxReportNum +
			", functionStr=" + functionStr +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", templateJson=" + templateJson +
			", apiSecret=" + apiSecret +
			"}";
	}
}
