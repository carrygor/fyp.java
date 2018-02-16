package cn.com.youplus.cms.api.system;

import cn.com.youplus.common.model.base.FunctionList;
import cn.com.youplus.common.model.base.ResourcesPermissionList;

import java.util.List;

public interface CmsPermissionService {
    List<String> getPermissionListByRoleId(long roleId);

    List<String> getTenantsPermissionListByRoleId(long roleId);

    List<String> getTenantsPermissionListByRoleCode(Integer roleCode);

    ResourcesPermissionList getResourcesPermissionList();

    ResourcesPermissionList getTenantsPermissionList();

    FunctionList getFunctionList();
}
