package cn.com.carry.tenants.server.controller.user;

import cn.com.carry.common.annotation.Permission;
import cn.com.carry.common.exception.interaction.AlertException;
import cn.com.carry.common.form.BaseIdForm;
import cn.com.carry.common.form.BasePageForm;
import cn.com.carry.common.model.base.BaseResponse;
import cn.com.carry.common.util.Constants;
import cn.com.carry.common.util.MD5Util;
import cn.com.carry.common.util.StringHelper;
import cn.com.carry.common.util.ValueHelper;
import cn.com.carry.model.auto.entity.tenants.CUser;
import cn.com.carry.model.auto.entity.tenants.CUserRole;
import cn.com.carry.tenants.api.auto.CAnalyzedDataService;
import cn.com.carry.tenants.api.auto.CFinalDataService;
import cn.com.carry.tenants.api.auto.CUserRoleService;
import cn.com.carry.tenants.api.auto.CUserService;
import cn.com.carry.tenants.common.form.LoginForm;
import cn.com.carry.tenants.common.form.RoleEditForm;
import cn.com.carry.tenants.server.controller.SuperController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/25.
 */
@SuppressWarnings("Duplicates")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/admin")
public class AdminController extends SuperController {

    //region autowired
    private static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private CUserService cUserService;

    @Autowired
    private CFinalDataService cFinalDataService;

    @Autowired
    private CUserRoleService cUserRoleService;

    @Autowired
    private CAnalyzedDataService cAnalyzedDataService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }
    //endregion

    @Permission(value = "系统管理")
    @PostMapping("/user/save")
    public BaseResponse userSave(LoginForm form) throws AlertException {
        BaseResponse response = new BaseResponse();


        if (ValueHelper.isNoneOrZero(form.getId())) {
            CUser user = cUserService.selectOne(
                    new EntityWrapper<CUser>()
                            .eq(CUser.USER_NAME, form.getUsername())

            );
            if (user != null) {
                throw new AlertException("该用户名已存在");
            }
            user = new CUser();
            if (!ValueHelper.isNone(form.getPassword())) {

                String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
                String password = MD5Util.getPassword(form.getPassword(), salt).toLowerCase();
                user.setSalt(salt)
                        .setPassword(password);
            }

            user.setUserName(form.getUsername())
                    .setUserRoleId(form.getUserRoleId());
            cUserService.insert(user);
        } else {
            CUser user = cUserService.selectById(form.getId());
            if (user == null) {
                throw new AlertException("该用户名不存在");
            }

            if (!ValueHelper.isNone(form.getPassword())) {

                String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
                String password = MD5Util.getPassword(form.getPassword(), salt).toLowerCase();
                user.setSalt(salt)
                        .setPassword(password);
            }
            user.setUserName(form.getUsername())
                    .setUserRoleId(form.getUserRoleId());
            cUserService.updateById(user);
        }

        return response;
    }

    @Permission(value = "系统管理")
    @PostMapping("/user/list")
    public BaseResponse userSave(BasePageForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        Page<CUser> page = cUserService.selectPage(
                new Page<>(form.getPageNum(), form.getPageSize()),
                new EntityWrapper<CUser>()
        );
        List<CUser> list = page.getRecords();
        for (CUser cUser : list) {
            cUser.setPassword(null)
                    .setSalt(null);
        }

        List<CUserRole> roleList = cUserRoleService.selectList(
                new EntityWrapper<>()
        );

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", page.getTotal());
        result.put("roleList", roleList);
        response.setData(result);

        return response;
    }

    @Permission(value = "系统管理")
    @PostMapping("/user/delete")
    public BaseResponse userDelete(BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        cUserService.deleteById(form.getId());

        return response;
    }

    @Permission(value = "系统管理")
    @PostMapping("/role/save")
    public BaseResponse userSave(RoleEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        if (ValueHelper.isNoneOrZero(form.getId())) {
            CUserRole role = new CUserRole();
            role.setName(form.getName())
                    .setRoleCode(form.getRoleCode());
            cUserRoleService.insert(role);
        } else {
            CUserRole role = cUserRoleService.selectById(form.getId());
            if (role == null) {
                throw new AlertException("改角色不存在");
            }
            role.setName(form.getName())
                    .setRoleCode(form.getRoleCode());
            cUserRoleService.updateById(role);
        }

        return response;
    }

    @Permission(value = "系统管理")
    @PostMapping("/role/list")
    public BaseResponse roleSave(BasePageForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        Page<CUserRole> page = cUserRoleService.selectPage(
                new Page<>(form.getPageNum(), form.getPageSize()),
                new EntityWrapper<CUserRole>()
        );
        response.setData(page);

        return response;
    }

    @Permission(value = "系统管理")
    @PostMapping("/role/delete")
    public BaseResponse roleDelete(BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        cUserRoleService.deleteById(form.getId());
        cUserService.delete(
                new EntityWrapper<CUser>()
                        .eq(CUser.USER_ROLE_ID, form.getId())
        );

        return response;
    }

}














