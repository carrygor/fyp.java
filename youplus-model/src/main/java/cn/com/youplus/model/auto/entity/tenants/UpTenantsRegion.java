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
@TableName("up_tenants_region")
public class UpTenantsRegion extends Model<UpTenantsRegion> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 租户id
     */
	@TableField("tenants_company_id")
	private Long tenantsCompanyId;
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
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
    /**
     * 乐观锁
     */
	@Version
	private Integer version;
	@TableField("update_time")
	private Date updateTime;
	@TableField("add_time")
	private Date addTime;
    /**
     * 区域编号（已不用，与region_code相同）
     */
	@TableField("region_num")
	private String regionNum;


	public Long getId() {
		return id;
	}

	public UpTenantsRegion setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getTenantsCompanyId() {
		return tenantsCompanyId;
	}

	public UpTenantsRegion setTenantsCompanyId(Long tenantsCompanyId) {
		this.tenantsCompanyId = tenantsCompanyId;
		return this;
	}

	public String getName() {
		return name;
	}

	public UpTenantsRegion setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public UpTenantsRegion setDescription(String description) {
		this.description = description;
		return this;
	}

	public Long getParentId() {
		return parentId;
	}

	public UpTenantsRegion setParentId(Long parentId) {
		this.parentId = parentId;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public UpTenantsRegion setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public String getQuickTag() {
		return quickTag;
	}

	public UpTenantsRegion setQuickTag(String quickTag) {
		this.quickTag = quickTag;
		return this;
	}

	public Integer getIsStore() {
		return isStore;
	}

	public UpTenantsRegion setIsStore(Integer isStore) {
		this.isStore = isStore;
		return this;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public UpTenantsRegion setRegionCode(String regionCode) {
		this.regionCode = regionCode;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public UpTenantsRegion setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UpTenantsRegion setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public UpTenantsRegion setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public UpTenantsRegion setAddress(String address) {
		this.address = address;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTenantsRegion setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTenantsRegion setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTenantsRegion setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTenantsRegion setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public String getRegionNum() {
		return regionNum;
	}

	public UpTenantsRegion setRegionNum(String regionNum) {
		this.regionNum = regionNum;
		return this;
	}

	public static final String ID = "id";

	public static final String TENANTS_COMPANY_ID = "tenants_company_id";

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

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String VERSION = "version";

	public static final String UPDATE_TIME = "update_time";

	public static final String ADD_TIME = "add_time";

	public static final String REGION_NUM = "region_num";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTenantsRegion{" +
			"id=" + id +
			", tenantsCompanyId=" + tenantsCompanyId +
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
			", logicDelete=" + logicDelete +
			", version=" + version +
			", updateTime=" + updateTime +
			", addTime=" + addTime +
			", regionNum=" + regionNum +
			"}";
	}
}
