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
@TableName("c_user_role")
public class CUserRole extends Model<CUserRole> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	@TableField("role_code")
	private String roleCode;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public CUserRole setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public CUserRole setName(String name) {
		this.name = name;
		return this;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public CUserRole setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public CUserRole setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public CUserRole setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String ROLE_CODE = "role_code";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CUserRole{" +
			"id=" + id +
			", name=" + name +
			", roleCode=" + roleCode +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
