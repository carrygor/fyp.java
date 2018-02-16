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
@TableName("up_tenants_user")
public class UpTenantsUser extends Model<UpTenantsUser> implements Serializable {

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
     * 密码
     */
	private String password;
    /**
     * 密码盐
     */
	private String salt;
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
     * 员工编号
     */
	@TableField("user_num")
	private String userNum;


	public Long getId() {
		return id;
	}

	public UpTenantsUser setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UpTenantsUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getRealName() {
		return realName;
	}

	public UpTenantsUser setRealName(String realName) {
		this.realName = realName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UpTenantsUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public UpTenantsUser setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}

	public Integer getSex() {
		return sex;
	}

	public UpTenantsUser setSex(Integer sex) {
		this.sex = sex;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UpTenantsUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getSalt() {
		return salt;
	}

	public UpTenantsUser setSalt(String salt) {
		this.salt = salt;
		return this;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public UpTenantsUser setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UpTenantsUser setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public UpTenantsUser setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
		return this;
	}

	public String getHeadImg() {
		return headImg;
	}

	public UpTenantsUser setHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	public Long getRegionId() {
		return regionId;
	}

	public UpTenantsUser setRegionId(Long regionId) {
		this.regionId = regionId;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsUser setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsUser setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsUser setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsUser setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpTenantsUser setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public UpTenantsUser setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public String getUserNum() {
		return userNum;
	}

	public UpTenantsUser setUserNum(String userNum) {
		this.userNum = userNum;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_NAME = "user_name";

	public static final String REAL_NAME = "real_name";

	public static final String EMAIL = "email";

	public static final String PHONE_NUM = "phone_num";

	public static final String SEX = "sex";

	public static final String PASSWORD = "password";

	public static final String SALT = "salt";

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

	public static final String USER_NUM = "user_num";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsUser{" +
			"id=" + id +
			", userName=" + userName +
			", realName=" + realName +
			", email=" + email +
			", phoneNum=" + phoneNum +
			", sex=" + sex +
			", password=" + password +
			", salt=" + salt +
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
			", userNum=" + userNum +
			"}";
	}
}
