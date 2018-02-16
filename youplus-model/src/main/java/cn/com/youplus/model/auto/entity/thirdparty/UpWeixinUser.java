package cn.com.youplus.model.auto.entity.thirdparty;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_weixin_user")
public class UpWeixinUser extends Model<UpWeixinUser> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String unionid;
    /**
     * 优加公众号的openid
     */
	@TableField("youplus_openid")
	private String youplusOpenid;
    /**
     * 网页应用的openid
     */
	@TableField("webapp_openid")
	private String webappOpenid;
    /**
     * 微信公众号的appid
     */
	@TableField("thirdparty_appid")
	private String thirdpartyAppid;
    /**
     * appid
     */
	@TableField("thirdparty_openid")
	private String thirdpartyOpenid;
    /**
     * 小程序openid
     */
	@TableField("xcx_openid")
	private String xcxOpenid;
    /**
     * 优加公众号的accesstoken，也用于cookies索引
     */
	@TableField("youplus_access_token")
	private String youplusAccessToken;
    /**
     * 第三方公众号的access_token
     */
	@TableField("thirdparty_access_token")
	private String thirdpartyAccessToken;
    /**
     * 优加网页应用的access_token
     */
	@TableField("webapp_access_token")
	private String webappAccessToken;
    /**
     * 小程序的access token
     */
	@TableField("xcx_access_token")
	private String xcxAccessToken;
    /**
     * 关注时间
     */
	@TableField("subscribe_time")
	private Date subscribeTime;
    /**
     * 头像
     */
	@TableField("head_img")
	private String headImg;
    /**
     * 0:保密
1:男
2:女
     */
	private Integer sex;
    /**
     * 呢称
     */
	private String nickname;
    /**
     * 微信信息里面的城市
     */
	@TableField("profile_city")
	private String profileCity;
    /**
     * 微信信息里面的国家
     */
	@TableField("profile_country")
	private String profileCountry;
    /**
     * 微信信息里面的省份
     */
	@TableField("profile_province")
	private String profileProvince;
    /**
     * 最后时间的城市
     */
	@TableField("last_city")
	private String lastCity;
    /**
     * 最后时间的国家
     */
	@TableField("last_country")
	private String lastCountry;
    /**
     * 最后时间的省份
     */
	@TableField("last_province")
	private String lastProvince;
    /**
     * 最后时间的区
     */
	@TableField("last_district")
	private String lastDistrict;
    /**
     * 最后的街道
     */
	@TableField("last_street")
	private String lastStreet;
    /**
     * 最后的详细地址
     */
	@TableField("last_detail")
	private String lastDetail;
    /**
     * 最后更新的纬度
     */
	@TableField("last_lat")
	private Float lastLat;
    /**
     * 最后更新的经度信息
     */
	@TableField("last_lng")
	private Float lastLng;
    /**
     * 最后更新的时间
     */
	@TableField("last_time")
	private Date lastTime;
    /**
     * 微信的权限组
     */
	private String privilege;
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
     * 是否订阅微信广播消息
     */
	@TableField("is_td")
	private Integer isTd;
    /**
     * 是否关注公众号
     */
	@TableField("is_subscribe")
	private Integer isSubscribe;
    /**
     * 个人信息的json数据包
     */
	@TableField("information_json")
	private String informationJson;
    /**
     * 手机号码
     */
	private String mobile;
    /**
     * 邮箱
     */
	private String email;


	public Long getId() {
		return id;
	}

	public UpWeixinUser setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUnionid() {
		return unionid;
	}

	public UpWeixinUser setUnionid(String unionid) {
		this.unionid = unionid;
		return this;
	}

	public String getYouplusOpenid() {
		return youplusOpenid;
	}

	public UpWeixinUser setYouplusOpenid(String youplusOpenid) {
		this.youplusOpenid = youplusOpenid;
		return this;
	}

	public String getWebappOpenid() {
		return webappOpenid;
	}

	public UpWeixinUser setWebappOpenid(String webappOpenid) {
		this.webappOpenid = webappOpenid;
		return this;
	}

	public String getThirdpartyAppid() {
		return thirdpartyAppid;
	}

	public UpWeixinUser setThirdpartyAppid(String thirdpartyAppid) {
		this.thirdpartyAppid = thirdpartyAppid;
		return this;
	}

	public String getThirdpartyOpenid() {
		return thirdpartyOpenid;
	}

	public UpWeixinUser setThirdpartyOpenid(String thirdpartyOpenid) {
		this.thirdpartyOpenid = thirdpartyOpenid;
		return this;
	}

	public String getXcxOpenid() {
		return xcxOpenid;
	}

	public UpWeixinUser setXcxOpenid(String xcxOpenid) {
		this.xcxOpenid = xcxOpenid;
		return this;
	}

	public String getYouplusAccessToken() {
		return youplusAccessToken;
	}

	public UpWeixinUser setYouplusAccessToken(String youplusAccessToken) {
		this.youplusAccessToken = youplusAccessToken;
		return this;
	}

	public String getThirdpartyAccessToken() {
		return thirdpartyAccessToken;
	}

	public UpWeixinUser setThirdpartyAccessToken(String thirdpartyAccessToken) {
		this.thirdpartyAccessToken = thirdpartyAccessToken;
		return this;
	}

	public String getWebappAccessToken() {
		return webappAccessToken;
	}

	public UpWeixinUser setWebappAccessToken(String webappAccessToken) {
		this.webappAccessToken = webappAccessToken;
		return this;
	}

	public String getXcxAccessToken() {
		return xcxAccessToken;
	}

	public UpWeixinUser setXcxAccessToken(String xcxAccessToken) {
		this.xcxAccessToken = xcxAccessToken;
		return this;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public UpWeixinUser setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
		return this;
	}

	public String getHeadImg() {
		return headImg;
	}

	public UpWeixinUser setHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	public Integer getSex() {
		return sex;
	}

	public UpWeixinUser setSex(Integer sex) {
		this.sex = sex;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public UpWeixinUser setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String getProfileCity() {
		return profileCity;
	}

	public UpWeixinUser setProfileCity(String profileCity) {
		this.profileCity = profileCity;
		return this;
	}

	public String getProfileCountry() {
		return profileCountry;
	}

	public UpWeixinUser setProfileCountry(String profileCountry) {
		this.profileCountry = profileCountry;
		return this;
	}

	public String getProfileProvince() {
		return profileProvince;
	}

	public UpWeixinUser setProfileProvince(String profileProvince) {
		this.profileProvince = profileProvince;
		return this;
	}

	public String getLastCity() {
		return lastCity;
	}

	public UpWeixinUser setLastCity(String lastCity) {
		this.lastCity = lastCity;
		return this;
	}

	public String getLastCountry() {
		return lastCountry;
	}

	public UpWeixinUser setLastCountry(String lastCountry) {
		this.lastCountry = lastCountry;
		return this;
	}

	public String getLastProvince() {
		return lastProvince;
	}

	public UpWeixinUser setLastProvince(String lastProvince) {
		this.lastProvince = lastProvince;
		return this;
	}

	public String getLastDistrict() {
		return lastDistrict;
	}

	public UpWeixinUser setLastDistrict(String lastDistrict) {
		this.lastDistrict = lastDistrict;
		return this;
	}

	public String getLastStreet() {
		return lastStreet;
	}

	public UpWeixinUser setLastStreet(String lastStreet) {
		this.lastStreet = lastStreet;
		return this;
	}

	public String getLastDetail() {
		return lastDetail;
	}

	public UpWeixinUser setLastDetail(String lastDetail) {
		this.lastDetail = lastDetail;
		return this;
	}

	public Float getLastLat() {
		return lastLat;
	}

	public UpWeixinUser setLastLat(Float lastLat) {
		this.lastLat = lastLat;
		return this;
	}

	public Float getLastLng() {
		return lastLng;
	}

	public UpWeixinUser setLastLng(Float lastLng) {
		this.lastLng = lastLng;
		return this;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public UpWeixinUser setLastTime(Date lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	public String getPrivilege() {
		return privilege;
	}

	public UpWeixinUser setPrivilege(String privilege) {
		this.privilege = privilege;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpWeixinUser setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpWeixinUser setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getIsTd() {
		return isTd;
	}

	public UpWeixinUser setIsTd(Integer isTd) {
		this.isTd = isTd;
		return this;
	}

	public Integer getIsSubscribe() {
		return isSubscribe;
	}

	public UpWeixinUser setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
		return this;
	}

	public String getInformationJson() {
		return informationJson;
	}

	public UpWeixinUser setInformationJson(String informationJson) {
		this.informationJson = informationJson;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public UpWeixinUser setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UpWeixinUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public static final String ID = "id";

	public static final String UNIONID = "unionid";

	public static final String YOUPLUS_OPENID = "youplus_openid";

	public static final String WEBAPP_OPENID = "webapp_openid";

	public static final String THIRDPARTY_APPID = "thirdparty_appid";

	public static final String THIRDPARTY_OPENID = "thirdparty_openid";

	public static final String XCX_OPENID = "xcx_openid";

	public static final String YOUPLUS_ACCESS_TOKEN = "youplus_access_token";

	public static final String THIRDPARTY_ACCESS_TOKEN = "thirdparty_access_token";

	public static final String WEBAPP_ACCESS_TOKEN = "webapp_access_token";

	public static final String XCX_ACCESS_TOKEN = "xcx_access_token";

	public static final String SUBSCRIBE_TIME = "subscribe_time";

	public static final String HEAD_IMG = "head_img";

	public static final String SEX = "sex";

	public static final String NICKNAME = "nickname";

	public static final String PROFILE_CITY = "profile_city";

	public static final String PROFILE_COUNTRY = "profile_country";

	public static final String PROFILE_PROVINCE = "profile_province";

	public static final String LAST_CITY = "last_city";

	public static final String LAST_COUNTRY = "last_country";

	public static final String LAST_PROVINCE = "last_province";

	public static final String LAST_DISTRICT = "last_district";

	public static final String LAST_STREET = "last_street";

	public static final String LAST_DETAIL = "last_detail";

	public static final String LAST_LAT = "last_lat";

	public static final String LAST_LNG = "last_lng";

	public static final String LAST_TIME = "last_time";

	public static final String PRIVILEGE = "privilege";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String IS_TD = "is_td";

	public static final String IS_SUBSCRIBE = "is_subscribe";

	public static final String INFORMATION_JSON = "information_json";

	public static final String MOBILE = "mobile";

	public static final String EMAIL = "email";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpWeixinUser{" +
			"id=" + id +
			", unionid=" + unionid +
			", youplusOpenid=" + youplusOpenid +
			", webappOpenid=" + webappOpenid +
			", thirdpartyAppid=" + thirdpartyAppid +
			", thirdpartyOpenid=" + thirdpartyOpenid +
			", xcxOpenid=" + xcxOpenid +
			", youplusAccessToken=" + youplusAccessToken +
			", thirdpartyAccessToken=" + thirdpartyAccessToken +
			", webappAccessToken=" + webappAccessToken +
			", xcxAccessToken=" + xcxAccessToken +
			", subscribeTime=" + subscribeTime +
			", headImg=" + headImg +
			", sex=" + sex +
			", nickname=" + nickname +
			", profileCity=" + profileCity +
			", profileCountry=" + profileCountry +
			", profileProvince=" + profileProvince +
			", lastCity=" + lastCity +
			", lastCountry=" + lastCountry +
			", lastProvince=" + lastProvince +
			", lastDistrict=" + lastDistrict +
			", lastStreet=" + lastStreet +
			", lastDetail=" + lastDetail +
			", lastLat=" + lastLat +
			", lastLng=" + lastLng +
			", lastTime=" + lastTime +
			", privilege=" + privilege +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", isTd=" + isTd +
			", isSubscribe=" + isSubscribe +
			", informationJson=" + informationJson +
			", mobile=" + mobile +
			", email=" + email +
			"}";
	}
}
