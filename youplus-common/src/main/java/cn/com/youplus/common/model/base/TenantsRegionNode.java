package cn.com.youplus.common.model.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/11/1.
 */
public class TenantsRegionNode implements Serializable{

    private String label;

    private int level;

    private Long id;

    private Long parentId;

    private String regionCode;

    private int isStore;

    private String province;

    private String city;

    private String district;

    private String address;

    public TenantsRegionNode() {

    }

    public TenantsRegionNode(TenantsRegion tenantsRegion) {
        if (tenantsRegion != null) {
            this.label = tenantsRegion.getLabel();
            this.level = tenantsRegion.getLevel();
            this.id = tenantsRegion.getId();
            this.parentId = tenantsRegion.getParentId();
            this.regionCode = tenantsRegion.getRegionCode();
            this.isStore = tenantsRegion.getIsStore();
            this.province = tenantsRegion.getProvince();
            this.city = tenantsRegion.getCity();
            this.district = tenantsRegion.getDistrict();
            this.address = tenantsRegion.getAddress();
        } else {
            this.label = "";
            this.level = 0;
            this.id = 0L;
            this.parentId =  0L;
            this.regionCode =  "";
            this.isStore = 0;
        }
    }

    public int getIsStore() {
        return isStore;
    }

    public void setIsStore(int isStore) {
        this.isStore = isStore;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
