package cn.com.youplus.cms.server.controller;

import cn.com.youplus.base.api.auto.UpBaseSystemConfigService;
import cn.com.youplus.cms.api.auto.UpCmsRolePermissionService;
import cn.com.youplus.cms.api.auto.UpCmsRoleService;
import cn.com.youplus.cms.api.auto.UpCmsUserService;
import cn.com.youplus.cms.api.system.CmsPermissionService;
import cn.com.youplus.cms.common.form.LoginForm;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.util.AESUtil;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.cms.UpCmsRole;
import cn.com.youplus.model.auto.entity.cms.UpCmsUser;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cms/v1/common")
public class CommonController extends SuperController {

    //region 自动注入
    @Autowired
    private UpBaseSystemConfigService upBaseSystemConfigService;

    @Autowired
    private UpCmsUserService upCmsUserService;

    @Autowired
    private UpCmsRolePermissionService upCmsRolePermissionService;

    @Autowired
    private UpCmsRoleService upCmsRoleService;

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }

    //endregion

    @PostMapping("/login")
    public BaseResponse commonLogin(@Valid LoginForm form) throws AlertException, InvalidAlgorithmParameterException {
        BaseResponse response = new BaseResponse();

//        UpCmsRole upCmsRole = new UpCmsRole();
//        upCmsRole.setRoleName("管理员");
//        upCmsRole = upCmsRoleService.mInsert(upCmsRole);
//
//        UpCmsRolePermission cmsRolePermission = new UpCmsRolePermission();
//        cmsRolePermission.setRoleId(upCmsRole.getId())
//                .setPermissionJson("[{\"name\":\"系统设置111\",\"actions\":[\"显示\"],\"children\":[{\"name\":\"系统参数\",\"actions\":[\"显示\",\"列表\",\"添加\",\"修改\",\"删除\"]},{\"name\":\"前端参数\",\"actions\":[\"显示\",\"列表\",\"添加\",\"修改\",\"删除\"]}]}]");
//
//        upCmsRolePermissionService.insert(cmsRolePermission);

        UpCmsUser cmsUser = upCmsUserService.selectOne(
                new EntityWrapper<UpCmsUser>()
                        .eq(UpCmsUser.USER_NAME, form.getUsername())
        );
        if (cmsUser == null) {
            throw new AlertException("用户名或密码错误");
        }

        String salt = cmsUser.getSalt();
        String formPassword = MD5Util.MD5(form.getPassword() + salt).toLowerCase();
        if (!cmsUser.getPassword().equals(formPassword)) {
            throw new AlertException("用户名或密码错误");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("routers", cmsPermissionService.getPermissionListByRoleId(cmsUser.getRoleId()));
        result.put("token", AESUtil.AESEncodeForWeb(cmsUser.getId().toString()));
        response.setData(result);

        return response;
    }

    @PostMapping("/roleIdList")
    public BaseResponse commonRoleIdList(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        List<UpCmsRole> list = upCmsRoleService.selectList(
                new EntityWrapper<UpCmsRole>()
                .orderBy(UpCmsRole.UPDATE_TIME, false)
        );

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        response.setData(result);

        return response;
    }

}
