package cn.com.youplus.tenants.api.common;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.TenantsRegion;
import cn.com.youplus.common.model.base.TenantsRegionNode;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.tenants.common.form.RegionAddForm;

import java.util.List;

public interface DataStructureService {
    List<TenantsRegion> 读取组织框架(Long id);

    TenantsRegion 读取组织框架_单树结构(Long id);

    List<TenantsRegionNode> getTenantsRegionList(long companyId);

    void addNewRegion(RegionAddForm form, UpTenantsCompany company) throws AlertException;

    List<Long> getDescendantRegionIdList(Long companyId, long regionId);
}
