package cn.com.youplus.tenants.server.controller.admin;

import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.form.BaseIdsForm;
import cn.com.youplus.common.form.BasePageZeroIdForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.common.model.base.TenantsRegion;
import cn.com.youplus.common.model.base.TenantsRegionNode;
import cn.com.youplus.common.model.enums.SmsTypeEnum;
import cn.com.youplus.common.model.enums.TenantsUserCodeEnum;
import cn.com.youplus.common.model.enums.UserTypeEnum;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.notification.UpNotificationSmsRecord;
import cn.com.youplus.model.auto.entity.tenants.*;
import cn.com.youplus.notification.api.SmsService;
import cn.com.youplus.tenants.api.auto.UpTenantsLevelService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserRegionViewService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserService;
import cn.com.youplus.tenants.api.common.DataStructureService;
import cn.com.youplus.tenants.api.common.ExcelService;
import cn.com.youplus.tenants.common.form.*;
import cn.com.youplus.tenants.common.model.ErrorRegion;
import cn.com.youplus.tenants.common.model.ErrorUser;
import cn.com.youplus.tenants.server.controller.SuperController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/25.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/admin")
public class AdminController extends SuperController{

    //region autowired
    private static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private UpTenantsUserService upTenantsUserService;

    @Autowired
    private UpTenantsUserRegionViewService upTenantsUserRegionViewService;

    @Autowired
    private DataStructureService dataStructureService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ExcelService excelService;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }
    //endregion

    @Permission(Permission.T_系统管理 + Permission.T_用户管理 + Permission.LIST)
    @PostMapping("/roleName/list")
    public BaseResponse roleNameList(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        List<Map<String, Object>> list = new ArrayList<>();
        for(TenantsUserCodeEnum codeEnum: TenantsUserCodeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", codeEnum.getCode());
            map.put("roleName", codeEnum.getRoleName());
            list.add(map);
        }

        response.setData(list);
        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_组织管理 + Permission.LIST)
    @PostMapping("/region/list")
    public BaseResponse regionList(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        List<TenantsRegionNode> list = dataStructureService.getTenantsRegionList(company.getId());

//        if (list.get(0).getLabel() == null) {
//            upTenantsRegionService.insert(
//                    new UpTenantsRegion().setLevel(0)
//                            .setName("总部")
//                            .setParentId(0L)
//                            .setIsStore(0)
//                            .setTenantsCompanyId(company.getId())
//                            .setAddTime(new Date())
//                            .setUpdateTime(new Date())
//            );
//            list = dataStructureService.getTenantsRegionList(company.getId());
//        }

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        response.setData(result);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_组织管理 + Permission.ADD)
    @PostMapping("/region/add")
    public BaseResponse regionAdd(@Valid RegionAddForm form) throws AlertException {
        BaseResponse response = new BaseResponse<>();

        UpTenantsCompany company = getCompany(request);
        dataStructureService.addNewRegion(form, company);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_组织管理 + Permission.EDIT)
    @PostMapping("/region/edit")
    public BaseResponse regionEdit(@Valid RegionEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        UpTenantsRegion region = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                .eq(UpTenantsRegion.ID, form.getId())
                .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
        );
        if (region == null) {
            throw new AlertException("该层级不存在");
        }
        region.setRegionCode(form.getRegionCode())
                .setName(form.getName())
                .setProvince(form.getProvince())
                .setCity(form.getCity())
                .setDistrict(form.getDistrict())
                .setAddress(form.getAddress())
                .setUpdateTime(new Date());

        if (!upTenantsRegionService.updateById(region)) {
            throw new AlertException("更新失败,请重试");
        }

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_组织管理 + Permission.DELETE)
    @PostMapping("/region/delete")
    public BaseResponse regionDelete(@Valid BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        List<Long> idList = dataStructureService.getDescendantRegionIdList(company.getId(), form.getId());
        if (!upTenantsRegionService.deleteBatchIds(idList)) {
            throw new AlertException("删除异常");
        }

        UpTenantsUser updateUser = new UpTenantsUser();
        updateUser.setRegionId(0L)
                .setUpdateTime(new Date());

        upTenantsUserService.update(updateUser,
                new EntityWrapper<UpTenantsUser>()
                        .in(UpTenantsUser.REGION_ID, idList)
        );

        UpTenantsUser upTenantsUser = new UpTenantsUser();
        upTenantsUser.setRegionId(0L)
                .setUpdateTime(new Date());
        upTenantsUserService.update(upTenantsUser,
            new EntityWrapper<UpTenantsUser>()
            .in(UpTenantsUser.REGION_ID, idList)
        );

        return response;
    }

    @PostMapping("/region/upload")
    public BaseResponse regionUpload(@RequestParam("file") MultipartFile file) throws Exception {
        BaseResponse response = new BaseResponse();

        InputStream is = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        List<ErrorRegion> errorRegionList = excelService.parseStructExcel(bytes, getCompany(request));
        is.close();
        Map<String, Object> result = new HashMap<>();
        result.put("list", errorRegionList);
        result.put("isSucc", errorRegionList.size() == 0);
        response.setData(result);
        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_组织管理 + Permission.LIST)
    @PostMapping("/layer/get")
    public BaseResponse layerGet(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);

        List<UpTenantsLevel> levels = upTenantsLevelService.selectList(
                new EntityWrapper<UpTenantsLevel>()
                .eq(UpTenantsLevel.TENANTS_COMPANY_ID, company.getId())
        );
//        if (ValueHelper.isNone(levels)) {
//            upTenantsLevelService.insert(
//                    new UpTenantsLevel().setName("总部")
//                            .setLevel(0)
//                            .setTenantsCompanyId(company.getId())
//                            .setAddTime(new Date())
//                            .setUpdateTime(new Date())
//            );
//            levels = upTenantsLevelService.selectList(
//                    new EntityWrapper<UpTenantsLevel>()
//                            .eq(UpTenantsLevel.TENANTS_COMPANY_ID, company.getId())
//            );
//        }

        Map<String, Object> result = new HashMap<>();
        result.put("list", levels);
        response.setData(result);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_组织管理 + Permission.EDIT)
    @PostMapping("/layer/edit")
    public BaseResponse layerEdit(@Valid LayerEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);

        UpTenantsLevel level = upTenantsLevelService.selectOne(
                new EntityWrapper<UpTenantsLevel>()
                .eq(UpTenantsLevel.TENANTS_COMPANY_ID, company.getId())
                .eq(UpTenantsLevel.LEVEL, form.getLevel())
        );

        if (level == null) {
            level = new UpTenantsLevel();
            level.setName(form.getName())
                    .setLevel(form.getLevel())
                    .setTenantsCompanyId(company.getId())
                    .setAddTime(new Date())
                    .setUpdateTime(new Date());

            if (!upTenantsLevelService.insert(level)) {
                throw new AlertException("创建失败,请重试");
            }
        } else {
            level.setName(form.getName())
                    .setUpdateTime(new Date());

            if(!upTenantsLevelService.updateById(level)) {
                throw new AlertException("保存失败,请重试");
            }
        }

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_用户管理 + Permission.LIST)
    @PostMapping("/userManage/tree")
    public BaseResponse userManageTree(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        TenantsRegion regionTree = dataStructureService.读取组织框架_单树结构(company.getId());
        response.setData(regionTree);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_用户管理 + Permission.LIST)
    @PostMapping("/userManage/get")
    public BaseResponse userManageGet(@Valid BasePageZeroIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        Page<UpTenantsUserRegionView> page = null;

        if (form.getId() == 0) {
            page = upTenantsUserRegionViewService.selectPage(
                    new Page<>(form.getPageNum(),form.getPageSize()),
                    new EntityWrapper<UpTenantsUserRegionView>()
                            .eq(UpTenantsUserRegionView.TENANTS_COMPANY_ID, company.getId())
                            .eq(UpTenantsUserRegionView.REGION_ID, form.getId())
            );
        } else {
            List<Long> regionIdList = dataStructureService.getDescendantRegionIdList(company.getId(), form.getId());
            page = upTenantsUserRegionViewService.selectPage(
                    new Page<>(form.getPageNum(),form.getPageSize()),
                    new EntityWrapper<UpTenantsUserRegionView>()
                            .eq(UpTenantsUserRegionView.TENANTS_COMPANY_ID, company.getId())
                            .in(UpTenantsUserRegionView.REGION_ID, regionIdList)
            );
        }

        Map<String, Object> result = new HashMap<>();
        result.put("list", page.getRecords());
        result.put("total", page.getTotal());
        response.setData(result);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_用户管理 + Permission.ADD)
    @PostMapping("/userManage/add")
    public BaseResponse userManageAdd(@Valid TenantsUserAddForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);

        int num = upTenantsUserService.selectCount(
                new EntityWrapper<UpTenantsUser>()
                        .eq(UpTenantsUser.TENANTS_COMPANY_ID, company)
        );
        if (num > company.getMaxUserNum()) {
            throw new AlertException("您的企业用户数目已超过" + company.getMaxUserNum() + "个用户数量限制。请联系优加工作人员进行升级。");
        }

        UpTenantsUser checkUserName = upTenantsUserService.selectOne(
                new EntityWrapper<UpTenantsUser>()
                        .eq(UpTenantsUser.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsUser.USER_NAME, form.getUserName())
        );
        if (checkUserName != null) {
            throw new AlertException("该用户已存在");
        }

        String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
        String password = MD5Util.MD5(systemConfigService.getTenantsParam(SystemConfigService.TENANTS_DEFAULT_PASSWORD)).toLowerCase();
        password = MD5Util.getPassword(password,salt).toLowerCase();

        if (form.getIsLeader() == 1) {
            UpTenantsUser checkUser = upTenantsUserService.selectOne(
                    new EntityWrapper<UpTenantsUser>()
                    .eq(UpTenantsUser.REGION_ID, form.getRegionId())
                    .eq(UpTenantsUser.IS_LEADER, 1)
            );

            if (checkUser != null) {
                throw new AlertException("该组织已存在负责人,请先取消原来的负责人");
            }

        }
        int roleCode = TenantsUserCodeEnum.默认用户.getCode();
        UpTenantsRegion region = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                .eq(UpTenantsRegion.ID, form.getRegionId())
        );
        if (region != null) {
            if (region.getIsStore() == 1) {
                if (form.getIsLeader() == 1) {
                    roleCode = TenantsUserCodeEnum.网点负责人.getCode();
                } else {
                    roleCode = TenantsUserCodeEnum.网点用户.getCode();
                }
            } else {
                if (form.getIsLeader() == 1) {
                    roleCode = TenantsUserCodeEnum.区域负责人.getCode();
                } else {
                    roleCode = TenantsUserCodeEnum.区域用户.getCode();
                }
            }
        }

        UpTenantsUser user = new UpTenantsUser();
        user.setUserName(form.getUserName())
                .setRealName(form.getRealName())
                .setUserNum(form.getUserNum())
                .setPhoneNum(form.getPhoneNum())
                .setEmail(form.getEmail())
                .setRegionId(form.getRegionId())
                .setIsLeader(form.getIsLeader())
                .setSalt(salt)
                .setPassword(password)
                .setTenantsCompanyId(company.getId())
                .setRoleCode(roleCode)
                .setAddTime(new Date())
                .setUpdateTime(new Date());

        if (!upTenantsUserService.insert(user)) {
            throw new AlertException("创建用户失败");
        }

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_用户管理 + Permission.EDIT)
    @PostMapping("/userManage/edit")
    public BaseResponse userManageEdit(@Valid TenantsUserEditForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);

        if (form.getIsLeader() == 1) {
            UpTenantsUser checkUser = upTenantsUserService.selectOne(
                    new EntityWrapper<UpTenantsUser>()
                            .eq(UpTenantsUser.REGION_ID, form.getRegionId())
                            .eq(UpTenantsUser.IS_LEADER, 1)
                            .notIn(UpTenantsUser.ID, form.getId())
            );

            if (checkUser != null) {
                throw new AlertException("该组织已存在负责人,请先取消原来的负责人");
            }

        }

        UpTenantsUser user = upTenantsUserService.selectOne(
                new EntityWrapper<UpTenantsUser>()
                .eq(UpTenantsUser.ID, form.getId())
        );
        if (user == null) {
            throw new AlertException("该用户不存在");
        }

        user.setUserName(form.getUserName())
                .setRealName(form.getRealName())
                .setUserNum(form.getUserNum())
                .setPhoneNum(form.getPhoneNum())
                .setEmail(form.getEmail())
                .setRegionId(form.getRegionId())
                .setIsLeader(form.getIsLeader())
                .setTenantsCompanyId(company.getId())
                .setAddTime(new Date());

        if (!upTenantsUserService.updateById(user)) {
            throw new AlertException("修改用户失败");
        }

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_用户管理 + Permission.DELETE)
    @PostMapping("/userManage/delete")
    public BaseResponse userManageDelete(@Valid BaseIdsForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);

        List<Long> idList = new ArrayList<>();
        String [] idStrs = form.getIds().split(",");

        for (String idStr : idStrs) {
            idList.add(Long.parseLong(idStr));
        }

        upTenantsUserService.delete(
                new EntityWrapper<UpTenantsUser>()
                .eq(UpTenantsUser.TENANTS_COMPANY_ID, company.getId())
                .in(UpTenantsUser.ID, idList)
        );

        return response;
    }

    @PostMapping("/userManage/upload")
    public BaseResponse userManageUpload(@RequestParam("file") MultipartFile file) throws Exception {
        BaseResponse response = new BaseResponse();

        InputStream is = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        List<ErrorUser> errorUserList = excelService.parseUserExcel(bytes, getCompany(request));
        is.close();
        Map<String, Object> result = new HashMap<>();
        result.put("list", errorUserList);
        result.put("isSucc", errorUserList.size() == 0);
        response.setData(result);
        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_账户管理 + Permission.GET)
    @PostMapping("/account/get")
    public BaseResponse accountGet(@Valid BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsUser user = getUser(request);
        user.setSalt(null);
        user.setPassword(null);
        response.setData(user);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_账户管理 + Permission.EDIT)
    @PostMapping("/account/base/edit")
    public BaseResponse accountBaseEdit(@Valid AccountBaseEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsUser user = getUser(request);

        user.setUserName(form.getUserName())
                .setRealName(form.getRealName())
                .setJobTitle(form.getJobTitle())
                .setEmail(form.getEmail())
                .setHeadImg(form.getHeadImg())
                .setUpdateTime(new Date());

        if (!upTenantsUserService.updateById(user)) {
            throw new AlertException("更新失败,请重试");
        }

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_账户管理 + Permission.EDIT)
    @PostMapping("/account/password/edit")
    public BaseResponse accountPasswordEdit(@Valid AccountPasswordEditForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsUser user = getUser(request);

        String oldPassword = MD5Util.getPassword(form.getOldPassword(), user.getSalt());

        if (!oldPassword.equals(user.getPassword())) {
            throw new AlertException("旧密码输入错误");
        }
        if (form.getNewPassword().equals(form.getOldPassword())) {
            throw new AlertException("新密码不能与旧密码一样");
        }

        String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
        String newPassword = MD5Util.getPassword(form.getNewPassword(),salt).toLowerCase();

        user.setSalt(salt)
                .setPassword(newPassword)
                .setUpdateTime(new Date());

        if(!upTenantsUserService.updateById(user)) {
            throw new AlertException("更新失败,请重试");
        }

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_账户管理 + Permission.GET)
    @PostMapping("/account/getCode")
    public BaseResponse accountGetCode(@Valid PhoneForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        UpTenantsUser user = getUser(request);

        if (!smsService.checkVerifyCodeOneMinute(form.getPhoneNum())) {
            throw new AlertException("一分钟之内不能重复发送");
        }

        SmsParams smsParams = new SmsParams()
                .setPhone(form.getPhoneNum())
                .setTemplateId(SmsService.身份验证验证码)
                .setType(SmsTypeEnum.VERIFY_CODE)
                .setUserId(user.getId())
                .setUserType(UserTypeEnum.TENANTS);

        smsParams.appendData("code", StringHelper.getRandomNumber(SmsService.VERFY_CODE_LENGTH));
        messageQueueService.sendSms(smsParams, null);

        return response;
    }

    @Permission(Permission.T_系统管理 + Permission.T_账户管理 + Permission.EDIT)
    @PostMapping("/account/phoneNum/edit")
    public BaseResponse accountPhoneNumEdit(@Valid ChangePhoneForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        UpNotificationSmsRecord upNotificationSmsRecord = smsService.checkVerifyCode(form.getPhoneNum(), form.getCode());
        if (upNotificationSmsRecord == null) {
            throw new AlertException("验证码不正确");
        }
        smsService.consumeVerifyCode(upNotificationSmsRecord);

        UpTenantsUser user = getUser(request);
        user.setPhoneNum(form.getPhoneNum())
                .setUpdateTime(new Date());
        if (!upTenantsUserService.updateById(user)) {
            throw new AlertException("更新失败,请重试");
        }

        return response;
    }

    @PostMapping("/region/filter")
    public BaseResponse regionFilter(@Valid BaseForm form) {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany tenantsCompany = getCompany(request);
        response.setData(dataStructureService.读取组织框架_单树结构(tenantsCompany.getId()).getChildren());
        return response;
    }

}














