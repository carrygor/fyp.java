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
@TableName("up_tenants_user_role")
public class UpTenantsUserRole extends Model<UpTenantsUserRole> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 角色名
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色代号
     */
	@TableField("role_code")
	private Integer roleCode;
    /**
     * 角色描述
     */
	@TableField("role_description")
	private String roleDescription;
    /**
     * 排序
     */
	private Integer sort;
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
     * 客户的用户角色对应的权限json
     */
	@TableField("permission_json")
	private String permissionJson;


	public Long getId() {
		return id;
	}

	public UpTenantsUserRole setId(Long id) {
		this.id = id;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public UpTenantsUserRole setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public UpTenantsUserRole setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public UpTenantsUserRole setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public UpTenantsUserRole setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsUserRole setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsUserRole setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsUserRole setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsUserRole setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getPermissionJson() {
		return permissionJson;
	}

	public UpTenantsUserRole setPermissionJson(String permissionJson) {
		this.permissionJson = permissionJson;
		return this;
	}

	public static final String ID = "id";

	public static final String ROLE_NAME = "role_name";

	public static final String ROLE_CODE = "role_code";

	public static final String ROLE_DESCRIPTION = "role_description";

	public static final String SORT = "sort";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String PERMISSION_JSON = "permission_json";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsUserRole{" +
			"id=" + id +
			", roleName=" + roleName +
			", roleCode=" + roleCode +
			", roleDescription=" + roleDescription +
			", sort=" + sort +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", permissionJson=" + permissionJson +
			"}";
	}
}
