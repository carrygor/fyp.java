package cn.com.carry.common.model.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/11/1.
 */
public class TenantsRegion implements Serializable{
    /**
     * 级别名称
     */
    private String label;

    private String quickTag;

    /**
     * 级别
     */
    private int level;

    private String regionCode;
    /**
     * id
     */
    private Long id;

    private Long parentId;

    private int isStore;

    private String province;

    private String city;

    private String district;

    private String address;

    List<TenantsRegion> children;

    public String getProvince() {
        return province;
    }

    public TenantsRegion setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public TenantsRegion setCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public TenantsRegion setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public TenantsRegion setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getIsStore() {
        return isStore;
    }

    public TenantsRegion setIsStore(int isStore) {
        this.isStore = isStore;
        return this;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public TenantsRegion setRegionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public TenantsRegion setLabel(String label) {
        this.label = label;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public TenantsRegion setLevel(int level) {
        this.level = level;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TenantsRegion setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public TenantsRegion setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public List<TenantsRegion> getChildren() {
        return children;
    }

    public TenantsRegion setChildren(List<TenantsRegion> children) {
        this.children = children;
        return this;
    }

    public String getQuickTag() {
        return quickTag;
    }

    public TenantsRegion setQuickTag(String quickTag) {
        this.quickTag = quickTag;
        return this;
    }
}
