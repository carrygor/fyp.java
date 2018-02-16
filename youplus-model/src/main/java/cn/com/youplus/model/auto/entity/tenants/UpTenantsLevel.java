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
@TableName("up_tenants_level")
public class UpTenantsLevel extends Model<UpTenantsLevel> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * 层级名称
     */
	private String name;
    /**
     * 层级
     */
	private Integer level;
    /**
     * 描述
     */
	private String description;
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
     * 企业id
     */
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;


	public Long getId() {
		return id;
	}

	public UpTenantsLevel setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public UpTenantsLevel setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public UpTenantsLevel setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpTenantsLevel setDescription(String description) {
		this.description = description;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsLevel setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsLevel setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsLevel setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpTenantsLevel setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String LEVEL = "level";

	public static final String DESCRIPTION = "description";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsLevel{" +
			"id=" + id +
			", name=" + name +
			", level=" + level +
			", description=" + description +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", tenantsCompanyId=" + tenantsCompanyId +
			"}";
	}
}
