package cn.com.carry.tenants.api.common;

import cn.com.carry.common.model.base.TenantsRegion;

import java.util.List;

public interface DataStructureService {
    List<TenantsRegion> 读取组织框架(Long id);

    TenantsRegion 读取组织框架_单树结构(Long id);

}
