package cn.com.youplus.cms.rpc.service.system.impl;

import cn.com.youplus.cms.api.auto.UpCmsRolePermissionService;
import cn.com.youplus.cms.api.system.CmsPermissionService;
import cn.com.youplus.common.model.base.FunctionList;
import cn.com.youplus.common.model.base.ResourcesPermission;
import cn.com.youplus.common.model.base.ResourcesPermissionList;
import cn.com.youplus.model.auto.entity.cms.UpCmsRolePermission;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUserRole;
import cn.com.youplus.tenants.api.auto.UpTenantsUserRoleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cmsPermissionService")
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    private ResourcesPermissionList resourcesPermissionList;

    @Autowired
    private ResourcesPermissionList tenantsPermissionList;

    @Autowired
    private FunctionList functionList;

    @Autowired
    private UpCmsRolePermissionService upCmsRolePermissionService;

    @Autowired
    private UpTenantsUserRoleService upTenantsUserRoleService;

    @Override
    public List<String> getPermissionListByRoleId(long roleId) {

        UpCmsRolePermission upCmsRolePermission = upCmsRolePermissionService.selectOne(
                new EntityWrapper<UpCmsRolePermission>().eq(UpCmsRolePermission.ROLE_ID, roleId)
        );
        JSONArray jsonArray = JSONArray.parseArray(upCmsRolePermission.getPermissionJson());

        List<String> list = getAllowResourcesName(jsonArray);

        return list;
    }

    @Override
    public List<String> getTenantsPermissionListByRoleId(long roleId) {

        UpTenantsUserRole userRole = upTenantsUserRoleService.selectById(roleId);

        JSONArray jsonArray = JSONArray.parseArray(userRole.getPermissionJson());

        List<String> list = getAllowResourcesName(jsonArray);

        return list;
    }

    @Override
    public List<String> getTenantsPermissionListByRoleCode(Integer roleCode) {

        UpTenantsUserRole userRole = upTenantsUserRoleService.selectOne(
                new EntityWrapper<UpTenantsUserRole>()
                        .eq(UpTenantsUserRole.ROLE_CODE, roleCode)
        );
        if (userRole == null) {
            return null;
        }

        JSONArray jsonArray = JSONArray.parseArray(userRole.getPermissionJson());

        List<String> list = getAllowResourcesName(jsonArray);

        return list;
    }

    @Override
    public ResourcesPermissionList getResourcesPermissionList() {
        return resourcesPermissionList;
        //return new ResourcesPermissionList();
    }

    @Override
    public ResourcesPermissionList getTenantsPermissionList() {
        return tenantsPermissionList;
    }

    @Override
    public FunctionList getFunctionList() {
        return functionList;
    }

    private static List<String> getAllowResourcesName(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();

        for (Object object: jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject.getString("actions").contains("显示")) {
                list.add(jsonObject.getString("name"));
            }
            if (jsonObject.get("children") != null) {
                list.addAll(getAllowResourcesName(jsonObject.getJSONArray("children")));
            }
        }

        return list;
    }

    private static List<String> getAllowResourcesName(List<ResourcesPermission> resourcesPermissionList) {

        List<String> list = new ArrayList<>();

        for (ResourcesPermission resourcesPermission: resourcesPermissionList) {
            list.add(resourcesPermission.getName());
            if (resourcesPermission.getChildren() != null) {
                list.addAll(getAllowResourcesName(resourcesPermission.getChildren()));
            }
        }

        return list;
    }

}
