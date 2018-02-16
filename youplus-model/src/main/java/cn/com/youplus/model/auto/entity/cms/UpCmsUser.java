package cn.com.youplus.model.auto.entity.cms;

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
@TableName("up_cms_user")
public class UpCmsUser extends Model<UpCmsUser> implements Serializable {

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


	public Long getId() {
		return id;
	}

	public UpCmsUser setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UpCmsUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getRealName() {
		return realName;
	}

	public UpCmsUser setRealName(String realName) {
		this.realName = realName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UpCmsUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public UpCmsUser setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}

	public Integer getSex() {
		return sex;
	}

	public UpCmsUser setSex(Integer sex) {
		this.sex = sex;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UpCmsUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getSalt() {
		return salt;
	}

	public UpCmsUser setSalt(String salt) {
		this.salt = salt;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UpCmsUser setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public UpCmsUser setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
		return this;
	}

	public String getHeadImg() {
		return headImg;
	}

	public UpCmsUser setHeadImg(String headImg) {
		this.headImg = headImg;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpCmsUser setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpCmsUser setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpCmsUser setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpCmsUser setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public static final String ROLE_ID = "role_id";

	public static final String JOB_TITLE = "job_title";

	public static final String HEAD_IMG = "head_img";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpCmsUser{" +
			"id=" + id +
			", userName=" + userName +
			", realName=" + realName +
			", email=" + email +
			", phoneNum=" + phoneNum +
			", sex=" + sex +
			", password=" + password +
			", salt=" + salt +
			", roleId=" + roleId +
			", jobTitle=" + jobTitle +
			", headImg=" + headImg +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
