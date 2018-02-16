package cn.com.youplus.model.auto.entity.base;

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
@TableName("up_base_system_config")
public class UpBaseSystemConfig extends Model<UpBaseSystemConfig> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 参数类型
     */
	@TableField("parameter_type")
	private String parameterType;
    /**
     * 参数的key
     */
	@TableField("attribute_key")
	private String attributeKey;
    /**
     * 参数的值
     */
	@TableField("attribute_value")
	private String attributeValue;
    /**
     * 说明
     */
	private String description;
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


	public Long getId() {
		return id;
	}

	public UpBaseSystemConfig setId(Long id) {
		this.id = id;
		return this;
	}

	public String getParameterType() {
		return parameterType;
	}

	public UpBaseSystemConfig setParameterType(String parameterType) {
		this.parameterType = parameterType;
		return this;
	}

	public String getAttributeKey() {
		return attributeKey;
	}

	public UpBaseSystemConfig setAttributeKey(String attributeKey) {
		this.attributeKey = attributeKey;
		return this;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public UpBaseSystemConfig setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpBaseSystemConfig setDescription(String description) {
		this.description = description;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public UpBaseSystemConfig setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpBaseSystemConfig setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpBaseSystemConfig setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpBaseSystemConfig setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpBaseSystemConfig setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String PARAMETER_TYPE = "parameter_type";

	public static final String ATTRIBUTE_KEY = "attribute_key";

	public static final String ATTRIBUTE_VALUE = "attribute_value";

	public static final String DESCRIPTION = "description";

	public static final String SORT = "sort";

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
		return "UpBaseSystemConfig{" +
			"id=" + id +
			", parameterType=" + parameterType +
			", attributeKey=" + attributeKey +
			", attributeValue=" + attributeValue +
			", description=" + description +
			", sort=" + sort +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
