package cn.com.carry.model.auto.entity.tenants;

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
 * @author 何文浩
 */
@TableName("c_user")
public class CUser extends Model<CUser> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("user_name")
	private String userName;
	private String password;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
	private String salt;


	public Long getId() {
		return id;
	}

	public CUser setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public CUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public CUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public CUser setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public CUser setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getSalt() {
		return salt;
	}

	public CUser setSalt(String salt) {
		this.salt = salt;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_NAME = "user_name";

	public static final String PASSWORD = "password";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String SALT = "salt";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CUser{" +
			"id=" + id +
			", userName=" + userName +
			", password=" + password +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", salt=" + salt +
			"}";
	}
}
