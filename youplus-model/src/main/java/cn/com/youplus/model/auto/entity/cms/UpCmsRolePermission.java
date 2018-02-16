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
@TableName("up_cms_role_permission")
public class UpCmsRolePermission extends Model<UpCmsRolePermission> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 角色id
     */
	@TableField("role_id")
	private Long roleId;
    /**
     * 权限id
     */
	@TableField("permission_id")
	private Long permissionId;
    /**
     * 权限json
     */
	@TableField("permission_json")
	private String permissionJson;
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

	public UpCmsRolePermission setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UpCmsRolePermission setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public UpCmsRolePermission setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
		return this;
	}

	public String getPermissionJson() {
		return permissionJson;
	}

	public UpCmsRolePermission setPermissionJson(String permissionJson) {
		this.permissionJson = permissionJson;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpCmsRolePermission setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpCmsRolePermission setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpCmsRolePermission setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpCmsRolePermission setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String ROLE_ID = "role_id";

	public static final String PERMISSION_ID = "permission_id";

	public static final String PERMISSION_JSON = "permission_json";

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
		return "UpCmsRolePermission{" +
			"id=" + id +
			", roleId=" + roleId +
			", permissionId=" + permissionId +
			", permissionJson=" + permissionJson +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
