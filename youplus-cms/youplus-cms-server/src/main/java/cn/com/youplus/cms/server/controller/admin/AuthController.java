package cn.com.youplus.cms.server.controller.admin;

import cn.com.youplus.cms.api.auto.UpCmsRolePermissionService;
import cn.com.youplus.cms.api.auto.UpCmsRoleService;
import cn.com.youplus.cms.api.auto.UpCmsUserService;
import cn.com.youplus.cms.api.system.CmsPermissionService;
import cn.com.youplus.cms.common.form.*;
import cn.com.youplus.cms.server.controller.SuperController;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.form.BasePageForm;
import cn.com.youplus.common.model.base.*;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.cms.UpCmsRole;
import cn.com.youplus.model.auto.entity.cms.UpCmsRolePermission;
import cn.com.youplus.model.auto.entity.cms.UpCmsUser;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUserRole;
import cn.com.youplus.tenants.api.auto.UpTenantsUserRoleService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cms/v1/auth")
public class AuthController extends SuperController {

    //region 自动注入
    @Autowired
    private UpCmsRoleService upCmsRoleService;

    @Autowired
    private UpTenantsUserRoleService upTenantsUserRoleService;

    @Autowired
    private UpCmsUserService upCmsUserService;

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @Autowired
    private UpCmsRolePermissionService upCmsRolePermissionService;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }

    //endregion

    @Permission(Permission.C_权限管理 + Permission.C_用户管理 + Permission.LIST)
    @PostMapping("/adminUser/list")
    public BaseResponse authAdminUserList(@Valid BasePageForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        List<UpCmsUser> list = upCmsUserService.selectList(
                new EntityWrapper<UpCmsUser>()
                .orderBy(UpCmsUser.ADD_TIME, false)
        );
        list.forEach(e -> {
            e.setPassword(null);
            e.setSalt(null);
        });

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_用户管理 + Permission.ADD)
    @PostMapping("/adminUser/add")
    public BaseResponse authAdminUserAdd(@Valid AdminUserAddForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpCmsUser checkUser = upCmsUserService.selectOne(
                new EntityWrapper<UpCmsUser>()
                .eq(UpCmsUser.USER_NAME, form.getUserName())
        );
        if (checkUser != null) {
            throw new AlertException("该用户名已存在");
        }

        String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
        String password = MD5Util.getPassword(form.getPassword(),salt).toLowerCase();

        UpCmsUser cmsUser = new UpCmsUser();
        cmsUser.setJobTitle(form.getJobTitle())
                .setUserName(form.getUserName())
                .setEmail(form.getEmail())
                .setHeadImg(form.getHeadImg())
                .setPhoneNum(form.getPhoneNum())
                .setRealName(form.getRealName())
                .setSex(form.getSex())
                .setRoleId(form.getRoleId())
                .setPassword(password)
                .setSalt(salt)
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        if (!upCmsUserService.insert(cmsUser)) {
            throw new AlertException("创建失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_用户管理 + Permission.EDIT)
    @PostMapping("/adminUser/edit")
    public BaseResponse authAdminUserEdit(@Valid AdminUserEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpCmsUser cmsUser = upCmsUserService.selectOne(
                new EntityWrapper<UpCmsUser>()
                .eq(UpCmsUser.ID, form.getId())
        );
        if (cmsUser == null) {
            throw new AlertException("该用户不存在");
        }

        if (!ValueHelper.isNone(form.getPassword())) {
            String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
            String password = MD5Util.getPassword(form.getPassword(),salt).toLowerCase();
            cmsUser.setPassword(password)
                    .setSalt(salt);
        }

        cmsUser.setJobTitle(form.getJobTitle())
                .setEmail(form.getEmail())
                .setHeadImg(form.getHeadImg())
                .setPhoneNum(form.getPhoneNum())
                .setRealName(form.getRealName())
                .setSex(form.getSex())
                .setRoleId(form.getRoleId())
                .setUpdateTime(new Date());

        if (upCmsUserService.updateById(cmsUser)) {
        } else {
            throw new AlertException("更新失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_用户管理 + Permission.DELETE)
    @PostMapping("/adminUser/delete")
    public BaseResponse authAdminUserDelete(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        if (upCmsUserService.deleteById(form.getId())) {
            response.setData("删除成功!");
        } else {
            throw new AlertException("删除失败，请稍后重试!");
        }

        return response;
    }

    @Permission(value = Permission.C_权限管理 + Permission.C_角色管理 + Permission.LIST)
    @PostMapping("/role/list")
    public BaseResponse authRoleList(@Valid BasePageForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        List<UpCmsRole> list = upCmsRoleService.selectList(
                new EntityWrapper<UpCmsRole>()
                        .orderBy(UpCmsRole.ADD_TIME, false)
        );

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_角色管理 + Permission.ADD)
    @PostMapping("/role/add")
    public BaseResponse authRoleAdd(@Valid RoleAddForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpCmsRole checkRole = upCmsRoleService.selectOne(
                new EntityWrapper<UpCmsRole>()
                        .eq(UpCmsRole.ROLE_NAME, form.getRoleName())
        );
        if (checkRole != null) {
            throw new AlertException("该角色名称已存在");
        }


        UpCmsRole cmsRole = new UpCmsRole();
        cmsRole.setRoleName(form.getRoleName())
                .setRoleCode(form.getRoleCode())
                .setRoleDescription(form.getRoleDescription())
                .setSort(form.getSort())
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        if (!upCmsRoleService.insert(cmsRole)) {
            throw new AlertException("创建失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_角色管理 + Permission.EDIT)
    @PostMapping("/role/edit")
    public BaseResponse authRoleEdit(@Valid RoleEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpCmsRole cmsRole = upCmsRoleService.selectOne(
                new EntityWrapper<UpCmsRole>()
                        .eq(UpCmsRole.ID, form.getId())
        );
        if (cmsRole == null) {
            throw new AlertException("该角色不存在");
        }

        cmsRole.setRoleDescription(form.getRoleDescription())
                .setRoleCode(form.getRoleCode())
                .setSort(form.getSort())
                .setRoleName(form.getRoleName())
                .setUpdateTime(new Date());

        if (upCmsRoleService.updateById(cmsRole)) {
            response.setData(cmsRole);
        } else {
            throw new AlertException("更新失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_角色管理 + Permission.DELETE)
    @PostMapping("/role/delete")
    public BaseResponse authRoleDelete(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        if (upCmsRoleService.deleteById(form.getId())) {
            response.setData("删除成功!");
        } else {
            throw new AlertException("删除失败，请稍后重试!");
        }

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_权限设置 + Permission.LIST)
    @PostMapping("/rolePermission/list")
    public BaseResponse rolePermissionList(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        ResourcesPermissionList resourcesPermissions = cmsPermissionService.getResourcesPermissionList();
        PermissionTreeList fullPermission = new PermissionTreeList(resourcesPermissions);

        UpCmsRolePermission rolePermission = upCmsRolePermissionService.selectOne(
                new EntityWrapper<UpCmsRolePermission>()
                .eq(UpCmsRolePermission.ROLE_ID, form.getId())
        );
        ResourcesPermissionList checkedPermissionList = new ResourcesPermissionList(rolePermission.getPermissionJson());
        PermissionTreeList checkedTreeList = new PermissionTreeList(checkedPermissionList);
        List<Integer> checkedIds = fullPermission.getCheckedIds(checkedTreeList);

        Map<String, Object> result = new HashMap<>();
        result.put("checkIds", checkedIds);
        result.put("fullPermission", fullPermission);
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_权限设置 + Permission.EDIT)
    @PostMapping("/rolePermission/edit")
    public BaseResponse rolePermissionEdit(@Valid PermissionEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpCmsRolePermission rolePermission = upCmsRolePermissionService.selectOne(
                new EntityWrapper<UpCmsRolePermission>()
                .eq(UpCmsRolePermission.ROLE_ID, form.getRoleId())
        );

        if (rolePermission == null) {
            throw new AlertException("该角色不存在");
        }

        JSONArray jsonArray = JSONArray.parseArray(form.getPermissionStr());
        if (jsonArray == null) {
            throw new AlertException("数据格式错误");
        }

        rolePermission.setPermissionJson(form.getPermissionStr())
                .setUpdateTime(new Date());

        if (upCmsRolePermissionService.updateById(rolePermission)) {
            response.setData(rolePermission);
        } else {
            throw new AlertException("修改失败，请稍后重试!");
        }

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_租户角色管理 + Permission.LIST)
    @PostMapping("/tenantsRole/list")
    public BaseResponse authTenantsRoleList(@Valid BasePageForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        List<UpTenantsUserRole> list = upTenantsUserRoleService.selectList(
                new EntityWrapper<UpTenantsUserRole>()
                        .orderBy(UpTenantsUserRole.ADD_TIME, false)
        );

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_租户角色管理 + Permission.ADD)
    @PostMapping("/tenantsRole/add")
    public BaseResponse authTenantsRoleAdd(@Valid TenantsRoleAddForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsUserRole checkRole = upTenantsUserRoleService.selectOne(
                new EntityWrapper<UpTenantsUserRole>()
                        .eq(UpTenantsUserRole.ROLE_NAME, form.getRoleName())
                        .eq(UpTenantsUserRole.ROLE_CODE, form.getRoleCode())
        );
        if (checkRole != null) {
            throw new AlertException("该角色名称已存在");
        }

        UpTenantsUserRole role = new UpTenantsUserRole();
        role.setRoleName(form.getRoleName())
                .setRoleCode(form.getRoleCode())
                .setRoleDescription(form.getRoleDescription())
                .setSort(form.getSort())
                .setPermissionJson("[]")
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        if (!upTenantsUserRoleService.insert(role)) {
            throw new AlertException("创建失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_租户角色管理 + Permission.EDIT)
    @PostMapping("/tenantsRole/edit")
    public BaseResponse authTenantsRoleEdit(@Valid TenantsRoleEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsUserRole role = upTenantsUserRoleService.selectOne(
                new EntityWrapper<UpTenantsUserRole>()
                        .eq(UpTenantsUserRole.ID, form.getId())
        );
        if (role == null) {
            throw new AlertException("该角色不存在");
        }

        role.setRoleDescription(form.getRoleDescription())
                .setRoleCode(form.getRoleCode())
                .setSort(form.getSort())
                .setRoleName(form.getRoleName())
                .setUpdateTime(new Date());

        if (upTenantsUserRoleService.updateById(role)) {
            response.setData(role);
        } else {
            throw new AlertException("更新失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_租户角色管理 + Permission.DELETE)
    @PostMapping("/tenantsRole/delete")
    public BaseResponse authTenantsRoleDelete(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        if (upTenantsUserRoleService.deleteById(form.getId())) {
            response.setData("删除成功!");
        } else {
            throw new AlertException("删除失败，请稍后重试!");
        }
        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_租户权限设置 + Permission.LIST)
    @PostMapping("/tenantsRolePermission/list")
    public BaseResponse tenantsRolePermissionList(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        ResourcesPermissionList tenantsPermissionList = cmsPermissionService.getTenantsPermissionList();
        PermissionTreeList fullPermission = new PermissionTreeList(tenantsPermissionList);

        UpTenantsUserRole userRole = upTenantsUserRoleService.selectById(form.getId());
        ResourcesPermissionList checkedPermissionList = new ResourcesPermissionList(userRole.getPermissionJson());
        PermissionTreeList checkedTreeList = new PermissionTreeList(checkedPermissionList);
        List<Integer> checkedIds = fullPermission.getCheckedIds(checkedTreeList);

        Map<String, Object> result = new HashMap<>();
        result.put("checkIds", checkedIds);
        result.put("fullPermission", fullPermission);
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_权限管理 + Permission.C_租户权限设置 + Permission.EDIT)
    @PostMapping("/tenantsRolePermission/edit")
    public BaseResponse tenantsRolePermissionEdit(@Valid PermissionEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsUserRole userRole = upTenantsUserRoleService.selectById(form.getRoleId());

        if (userRole == null) {
            throw new AlertException("该角色不存在");
        }

        JSONArray jsonArray = JSONArray.parseArray(form.getPermissionStr());
        if (jsonArray == null) {
            throw new AlertException("数据格式错误");
        }

        userRole.setPermissionJson(form.getPermissionStr())
                .setUpdateTime(new Date());

        if (upTenantsUserRoleService.updateById(userRole)) {
            response.setData(userRole);
        } else {
            throw new AlertException("修改失败，请稍后重试!");
        }

        return response;
    }
}
