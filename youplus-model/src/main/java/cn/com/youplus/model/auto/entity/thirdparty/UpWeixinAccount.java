package cn.com.youplus.model.auto.entity.thirdparty;

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
@TableName("up_weixin_account")
public class UpWeixinAccount extends Model<UpWeixinAccount> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
    /**
     * 微信的appid
     */
	private String name;
    /**
     * 描述
     */
	private String description;
    /**
     * 微信的appid
     */
	@TableField("authorizer_appid")
	private String authorizerAppid;
    /**
     * appid
     */
	@TableField("origin_id")
	private String originId;
    /**
     * 授权流程完成后，授权页会自动跳转进入回调URI，并在URL参数中返回授权码和过期时间(redirect_url?auth_code=xxx&expires_in=600)
     */
	@TableField("authorization_code")
	private String authorizationCode;
	@TableField("authorizer_access_token")
	private String authorizerAccessToken;
	@TableField("authorizer_refresh_token")
	private String authorizerRefreshToken;
	@TableField("expires_in")
	private String expiresIn;
	@TableField("func_info")
	private String funcInfo;
    /**
     * 授权方昵称
     */
	@TableField("nick_name")
	private String nickName;
    /**
     * 授权方头像
     */
	@TableField("head_img")
	private String headImg;
    /**
     * 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     */
	@TableField("service_type_info")
	private String serviceTypeInfo;
    /**
     * 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     */
	@TableField("verify_type_info")
	private String verifyTypeInfo;
    /**
     * 授权方公众号原始ID
     */
	@TableField("user_name")
	private String userName;
    /**
     * 公众号主体名称
     */
	@TableField("principal_name")
	private String principalName;
    /**
     * 授权方公众号所设置的微信号，可能为空
     */
	private String alias;
    /**
     * 了解功能的开通状况
     */
	@TableField("business_info")
	private String businessInfo;
    /**
     *  二维码图片的URL
     */
	@TableField("qrcode_url")
	private String qrcodeUrl;
    /**
     * 可根据这个字段判断是否为小程序类型授权
     */
	private String miniprograminfo;
    /**
     * 乐观锁版本
     */
	@Version
	private Integer version;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
    /**
     * 添加时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 是否授权
     */
	@TableField("is_auth")
	private Integer isAuth;
    /**
     * 账户的类型
     */
	@TableField("account_type")
	private String accountType;


	public Long getId() {
		return id;
	}

	public UpWeixinAccount setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpWeixinAccount setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getName() {
		return name;
	}

	public UpWeixinAccount setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpWeixinAccount setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getAuthorizerAppid() {
		return authorizerAppid;
	}

	public UpWeixinAccount setAuthorizerAppid(String authorizerAppid) {
		this.authorizerAppid = authorizerAppid;
		return this;
	}

	public String getOriginId() {
		return originId;
	}

	public UpWeixinAccount setOriginId(String originId) {
		this.originId = originId;
		return this;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public UpWeixinAccount setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
		return this;
	}

	public String getAuthorizerAccessToken() {
		return authorizerAccessToken;
	}

	public UpWeixinAccount setAuthorizerAccessToken(String authorizerAccessToken) {
		this.authorizerAccessToken = authorizerAccessToken;
		return this;
	}

	public String getAuthorizerRefreshToken() {
		return authorizerRefreshToken;
	}

	public UpWeixinAccount setAuthorizerRefreshToken(String authorizerRefreshToken) {
		this.authorizerRefreshToken = authorizerRefreshToken;
		return this;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public UpWeixinAccount setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public String getFuncInfo() {
		return funcInfo;
	}

	public UpWeixinAccount setFuncInfo(String funcInfo) {
		this.funcInfo = funcInfo;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public UpWeixinAccount setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public String getHeadImg() {
		return headImg;
	}

	public UpWeixinAccount setHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	public String getServiceTypeInfo() {
		return serviceTypeInfo;
	}

	public UpWeixinAccount setServiceTypeInfo(String serviceTypeInfo) {
		this.serviceTypeInfo = serviceTypeInfo;
		return this;
	}

	public String getVerifyTypeInfo() {
		return verifyTypeInfo;
	}

	public UpWeixinAccount setVerifyTypeInfo(String verifyTypeInfo) {
		this.verifyTypeInfo = verifyTypeInfo;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UpWeixinAccount setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public UpWeixinAccount setPrincipalName(String principalName) {
		this.principalName = principalName;
		return this;
	}

	public String getAlias() {
		return alias;
	}

	public UpWeixinAccount setAlias(String alias) {
		this.alias = alias;
		return this;
	}

	public String getBusinessInfo() {
		return businessInfo;
	}

	public UpWeixinAccount setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
		return this;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public UpWeixinAccount setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
		return this;
	}

	public String getMiniprograminfo() {
		return miniprograminfo;
	}

	public UpWeixinAccount setMiniprograminfo(String miniprograminfo) {
		this.miniprograminfo = miniprograminfo;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpWeixinAccount setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpWeixinAccount setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpWeixinAccount setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpWeixinAccount setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public UpWeixinAccount setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
		return this;
	}

	public String getAccountType() {
		return accountType;
	}

	public UpWeixinAccount setAccountType(String accountType) {
		this.accountType = accountType;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String NAME = "name";

	public static final String DESCRIPTION = "description";

	public static final String AUTHORIZER_APPID = "authorizer_appid";

	public static final String ORIGIN_ID = "origin_id";

	public static final String AUTHORIZATION_CODE = "authorization_code";

	public static final String AUTHORIZER_ACCESS_TOKEN = "authorizer_access_token";

	public static final String AUTHORIZER_REFRESH_TOKEN = "authorizer_refresh_token";

	public static final String EXPIRES_IN = "expires_in";

	public static final String FUNC_INFO = "func_info";

	public static final String NICK_NAME = "nick_name";

	public static final String HEAD_IMG = "head_img";

	public static final String SERVICE_TYPE_INFO = "service_type_info";

	public static final String VERIFY_TYPE_INFO = "verify_type_info";

	public static final String USER_NAME = "user_name";

	public static final String PRINCIPAL_NAME = "principal_name";

	public static final String ALIAS = "alias";

	public static final String BUSINESS_INFO = "business_info";

	public static final String QRCODE_URL = "qrcode_url";

	public static final String MINIPROGRAMINFO = "miniprograminfo";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String IS_AUTH = "is_auth";

	public static final String ACCOUNT_TYPE = "account_type";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpWeixinAccount{" +
			"id=" + id +
			", tenantsCompanyId=" + tenantsCompanyId +
			", name=" + name +
			", description=" + description +
			", authorizerAppid=" + authorizerAppid +
			", originId=" + originId +
			", authorizationCode=" + authorizationCode +
			", authorizerAccessToken=" + authorizerAccessToken +
			", authorizerRefreshToken=" + authorizerRefreshToken +
			", expiresIn=" + expiresIn +
			", funcInfo=" + funcInfo +
			", nickName=" + nickName +
			", headImg=" + headImg +
			", serviceTypeInfo=" + serviceTypeInfo +
			", verifyTypeInfo=" + verifyTypeInfo +
			", userName=" + userName +
			", principalName=" + principalName +
			", alias=" + alias +
			", businessInfo=" + businessInfo +
			", qrcodeUrl=" + qrcodeUrl +
			", miniprograminfo=" + miniprograminfo +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", isAuth=" + isAuth +
			", accountType=" + accountType +
			"}";
	}
}
