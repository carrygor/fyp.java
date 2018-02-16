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
 * VIEW
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_tenants_user_region_view")
public class UpTenantsUserRegionView extends Model<UpTenantsUserRegionView> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 真实姓名
     */
	@TableField("real_name")
	private String realName;
    /**
     * 用户邮箱
     */
	private String email;
    /**
     * 手机号码
     */
	@TableField("phone_num")
	private String phoneNum;
    /**
     * 性别：0-保密 1-男 2-女
     */
	private Integer sex;
    /**
     * 是否为负责人
     */
	@TableField("is_leader")
	private Integer isLeader;
    /**
     * 角色id
     */
	@TableField("role_id")
	private Long roleId;
    /**
     * 职称
     */
	@TableField("job_title")
	private String jobTitle;
    /**
     * 头像
     */
	@TableField("head_img")
	private String headImg;
    /**
     * 区域id
     */
	@TableField("region_id")
	private Long regionId;
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
    /**
     * 关联的企业id
     */
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
    /**
     * 角色代码
     */
	@TableField("role_code")
	private Integer roleCode;
    /**
     * 名称
     */
	private String name;
    /**
     * 描述
     */
	private String description;
    /**
     * parent_id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * level
     */
	private Integer level;
    /**
     * quick_tag
     */
	@TableField("quick_tag")
	private String quickTag;
    /**
     * 是否为网点
     */
	@TableField("is_store")
	private Integer isStore;
    /**
     * 区域代码
     */
	@TableField("region_code")
	private String regionCode;
    /**
     * 省份
     */
	private String province;
    /**
     * 城市
     */
	private String city;
    /**
     * 地区
     */
	private String district;
    /**
     * 详细地址
     */
	private String address;
    /**
     * 员工编号
     */
	@TableField("user_num")
	private String userNum;


	public Long getId() {
		return id;
	}

	public UpTenantsUserRegionView setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UpTenantsUserRegionView setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getRealName() {
		return realName;
	}

	public UpTenantsUserRegionView setRealName(String realName) {
		this.realName = realName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UpTenantsUserRegionView setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public UpTenantsUserRegionView setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}

	public Integer getSex() {
		return sex;
	}

	public UpTenantsUserRegionView setSex(Integer sex) {
		this.sex = sex;
		return this;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public UpTenantsUserRegionView setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UpTenantsUserRegionView setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public UpTenantsUserRegionView setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
		return this;
	}

	public String getHeadImg() {
		return headImg;
	}

	public UpTenantsUserRegionView setHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	public Long getRegionId() {
		return regionId;
	}

	public UpTenantsUserRegionView setRegionId(Long regionId) {
		this.regionId = regionId;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsUserRegionView setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsUserRegionView setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsUserRegionView setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsUserRegionView setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpTenantsUserRegionView setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public UpTenantsUserRegionView setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public String getName() {
		return name;
	}

	public UpTenantsUserRegionView setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpTenantsUserRegionView setDescription(String description) {
		this.description = description;
		return this;
	}

	public Long getParentId() {
		return parentId;
	}

	public UpTenantsUserRegionView setParentId(Long parentId) {
		this.parentId = parentId;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public UpTenantsUserRegionView setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public String getQuickTag() {
		return quickTag;
	}

	public UpTenantsUserRegionView setQuickTag(String quickTag) {
		this.quickTag = quickTag;
		return this;
	}

	public Integer getIsStore() {
		return isStore;
	}

	public UpTenantsUserRegionView setIsStore(Integer isStore) {
		this.isStore = isStore;
		return this;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public UpTenantsUserRegionView setRegionCode(String regionCode) {
		this.regionCode = regionCode;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public UpTenantsUserRegionView setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UpTenantsUserRegionView setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public UpTenantsUserRegionView setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public UpTenantsUserRegionView setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getUserNum() {
		return userNum;
	}

	public UpTenantsUserRegionView setUserNum(String userNum) {
		this.userNum = userNum;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_NAME = "user_name";

	public static final String REAL_NAME = "real_name";

	public static final String EMAIL = "email";

	public static final String PHONE_NUM = "phone_num";

	public static final String SEX = "sex";

	public static final String IS_LEADER = "is_leader";

	public static final String ROLE_ID = "role_id";

	public static final String JOB_TITLE = "job_title";

	public static final String HEAD_IMG = "head_img";

	public static final String REGION_ID = "region_id";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	public static final String ROLE_CODE = "role_code";

	public static final String NAME = "name";

	public static final String DESCRIPTION = "description";

	public static final String PARENT_ID = "parent_id";

	public static final String LEVEL = "level";

	public static final String QUICK_TAG = "quick_tag";

	public static final String IS_STORE = "is_store";

	public static final String REGION_CODE = "region_code";

	public static final String PROVINCE = "province";

	public static final String CITY = "city";

	public static final String DISTRICT = "district";

	public static final String ADDRESS = "address";

	public static final String USER_NUM = "user_num";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsUserRegionView{" +
			"id=" + id +
			", userName=" + userName +
			", realName=" + realName +
			", email=" + email +
			", phoneNum=" + phoneNum +
			", sex=" + sex +
			", isLeader=" + isLeader +
			", roleId=" + roleId +
			", jobTitle=" + jobTitle +
			", headImg=" + headImg +
			", regionId=" + regionId +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", tenantsCompanyId=" + tenantsCompanyId +
			", roleCode=" + roleCode +
			", name=" + name +
			", description=" + description +
			", parentId=" + parentId +
			", level=" + level +
			", quickTag=" + quickTag +
			", isStore=" + isStore +
			", regionCode=" + regionCode +
			", province=" + province +
			", city=" + city +
			", district=" + district +
			", address=" + address +
			", userNum=" + userNum +
			"}";
	}
}
