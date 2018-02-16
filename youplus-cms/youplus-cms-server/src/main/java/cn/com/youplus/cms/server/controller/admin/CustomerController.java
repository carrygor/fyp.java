package cn.com.youplus.cms.server.controller.admin;

import cn.com.youplus.cms.api.system.CmsPermissionService;
import cn.com.youplus.cms.common.form.TenantsCompanyAddForm;
import cn.com.youplus.cms.common.form.TenantsCompanyEditForm;
import cn.com.youplus.cms.server.controller.SuperController;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.form.BasePageForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.FunctionList;
import cn.com.youplus.common.model.enums.TenantsUserCodeEnum;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsLevel;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsLevelService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cms/v1/cus")
public class CustomerController extends SuperController {

    //region 自动注入
    @Autowired
    private CmsPermissionService cmsPermissionService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpTenantsUserService upTenantsUserService;

    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }
    //endregion

    @PostMapping("/fullFunction")
    public BaseResponse fullFunction(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        FunctionList fullFunction = cmsPermissionService.getFunctionList();
        Map<String, Object> result = new HashMap<>();
        result.put("fullFunction", fullFunction);

        response.setData(result);

        return response;
    }

    @Permission(Permission.C_客户管理 + Permission.C_客户列表 + Permission.LIST)
    @PostMapping("/customer/list")
    public BaseResponse customerList(@Valid BasePageForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        Page<UpTenantsCompany> list = upTenantsCompanyService.selectPage(
                new Page<>(form.getPageNum(),form.getPageSize()),
                new EntityWrapper<UpTenantsCompany>()
                .orderBy(UpTenantsCompany.ADD_TIME, false)
        );

        Map<String, Object> result = new HashMap<>();

        result.put("list", list.getRecords());
        result.put("total", list.getTotal());
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_客户管理 + Permission.C_客户列表 + Permission.GET)
    @PostMapping("/customer/get")
    public BaseResponse customerOne(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany tenantsCompany = upTenantsCompanyService.selectOne(
                new EntityWrapper<UpTenantsCompany>()
                        .eq(UpTenantsCompany.ID, form.getId())
        );

        UpTenantsUser tenantsUser = upTenantsUserService.selectOne(
                new EntityWrapper<UpTenantsUser>()
                        .eq(UpTenantsUser.ID, tenantsCompany.getTenantsUserId())
        );
        if (tenantsUser == null) {
            throw new AlertException("该租户管理员不存在");
        }
        Map<String, Object> user = new HashMap<>();
        user.put("user4userName",tenantsUser.getUserName());
        user.put("user4email",tenantsUser.getEmail());
        user.put("user4phoneNum",tenantsUser.getPhoneNum());
        user.put("user4password","");

        FunctionList functionList = new FunctionList(tenantsCompany.getFunctionStr());
        List<Integer> checkedIds = functionList.takeCheckedIds();

        FunctionList fullFunction = cmsPermissionService.getFunctionList();

        Map<String, Object> result = new HashMap<>();

        result.put("company", tenantsCompany);
        result.put("user", user);
        result.put("checkedIds", checkedIds);
        result.put("fullFunction", fullFunction);
        response.setData(result);

        return response;
    }

    @Permission(Permission.C_客户管理 + Permission.C_客户列表 + Permission.ADD)
    @PostMapping("/customer/add")
    public BaseResponse customerAdd(@Valid TenantsCompanyAddForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        switch (form.getDomainName()) {
            case "www":
            case "q":
            case "youplus":
            case "cms":
            case "apps":
            case "tenants":
            case "admin":
            case "api":
            case "test":
            case "thirdparty":
                throw new AlertException("该域名为系统保留域名");
            default:
                break;
        }
        UpTenantsCompany checkDomain = upTenantsCompanyService.selectOne(
                new EntityWrapper<UpTenantsCompany>()
                        .eq(UpTenantsCompany.DOMAIN_NAME, form.getDomainName())
        );
        if (checkDomain != null) {
            throw new AlertException("该域名已存在");
        }

//        UpTenantsUser user = upTenantsUserService.selectOne(
//                new EntityWrapper<UpTenantsUser>()
//                .eq(UpTenantsUser.USER_NAME, form.getUser4userName())
//        );
//        if (user != null) {
//            throw new AlertException("该用户名已存在");
//        }
        UpTenantsUser user = new UpTenantsUser();
        String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
        String password = MD5Util.getPassword(form.getUser4password(),salt).toLowerCase();

        user.setUserName(form.getUser4userName())
                .setRoleCode(TenantsUserCodeEnum.管理员.getCode())
                .setEmail(form.getUser4email())
                .setPhoneNum(form.getUser4phoneNum())
                .setSalt(salt)
                .setPassword(password)
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        user = upTenantsUserService.mInsert(user);
        if (!(user.getId() > 0)) {
            throw new AlertException("创建超级管理员用户失败,请重试");
        }

        UpTenantsCompany company = new UpTenantsCompany();
        company.setTenantsType(form.getTenantsType())
                .setName(form.getName())
                .setCompanyName(form.getCompanyName())
                .setAddress(form.getAddress())
                .setPhone(form.getPhone())
                .setProvince(form.getProvince())
                .setCity(form.getCity())
                .setDistrict(form.getDistrict())
                .setHangye(form.getHangye())
                .setAuthStartTime(new Date(form.getAuthStartTime()))
                .setAuthExpiredTime(new Date(form.getAuthExpiredTime()))
                .setLogoImg(form.getLogoImg())
                .setContactName(form.getContactName())
                .setDomainName(form.getDomainName())
                .setMaxReportNum(form.getMaxReportNum())
                .setMaxSiteNum(form.getMaxSiteNum())
                .setMaxUserNum(form.getMaxUserNum())
                .setTenantsUserId(user.getId())
                .setFunctionStr(form.getFunctionStr())
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        company = upTenantsCompanyService.mInsert(company);
        if (!(company.getId() > 0)) {
            throw new AlertException("创建失败,请重试");
        }
        //总部region
        UpTenantsRegion base = new UpTenantsRegion();
        base.setLevel(0)
            .setName("总部")
            .setParentId(0L)
            .setIsStore(0)
            .setTenantsCompanyId(company.getId())
            .setAddTime(new Date())
            .setUpdateTime(new Date());
        base = upTenantsRegionService.mInsert(base);
        base.setQuickTag("," + base.getId() + ",");
        upTenantsRegionService.updateById(base);

        //总部层级
        upTenantsLevelService.insert(
                new UpTenantsLevel().setName("总部")
                        .setLevel(0)
                        .setTenantsCompanyId(company.getId())
                        .setAddTime(new Date())
                        .setUpdateTime(new Date())
        );
        user.setTenantsCompanyId(company.getId());
        if (!upTenantsUserService.updateById(user)) {
            throw new AlertException("更新失败,请重试");
        }
        return response;
    }

    @Permission(Permission.C_客户管理 + Permission.C_客户列表 + Permission.EDIT)
    @PostMapping("/customer/edit")
    public BaseResponse customerEdit(@Valid TenantsCompanyEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = upTenantsCompanyService.selectOne(
                new EntityWrapper<UpTenantsCompany>()
                .eq(UpTenantsCompany.ID, form.getId())
        );

        UpTenantsUser user = upTenantsUserService.selectOne(
                new EntityWrapper<UpTenantsUser>()
                .eq(UpTenantsUser.ID, company.getTenantsUserId())
        );
        if (user == null) {
            throw new AlertException("数据不存在");
        }
        if (!ValueHelper.isNone(form.getUser4password())) {
            String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
            String password = MD5Util.getPassword(form.getUser4password(),salt).toLowerCase();
            user.setPassword(password)
                    .setSalt(salt);
        }
        user.setPhoneNum(form.getUser4phoneNum())
                .setEmail(form.getUser4email())
                .setUserName(form.getUser4userName())
                .setUpdateTime(new Date());

        if (upTenantsUserService.updateById(user)) {
        } else {
            throw new AlertException("更新失败,请重试");
        }

        company.setTenantsType(form.getTenantsType())
                .setName(form.getName())
                .setCompanyName(form.getCompanyName())
                .setAddress(form.getAddress())
                .setPhone(form.getPhone())
                .setProvince(form.getProvince())
                .setCity(form.getCity())
                .setDistrict(form.getDistrict())
                .setHangye(form.getHangye())
                .setAuthStartTime(new Date(form.getAuthStartTime()))
                .setAuthExpiredTime(new Date(form.getAuthExpiredTime()))
                .setLogoImg(form.getLogoImg())
                .setContactName(form.getContactName())
                .setMaxReportNum(form.getMaxReportNum())
                .setMaxSiteNum(form.getMaxSiteNum())
                .setMaxUserNum(form.getMaxUserNum())
                .setFunctionStr(form.getFunctionStr())
                .setUpdateTime(new Date());

        if (upTenantsCompanyService.updateById(company)) {
        } else {
            throw new AlertException("更新失败,请重试");
        }

        return response;
    }

    @Permission(Permission.C_客户管理 + Permission.C_客户列表 + Permission.DELETE)
    @PostMapping("/customer/delete")
    public BaseResponse customerDelete(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        if (upTenantsCompanyService.deleteById(form.getId())) {
            response.setData("删除成功!");
        } else {
            throw new AlertException("删除失败，请稍后重试!");
        }

        return response;
    }


}
