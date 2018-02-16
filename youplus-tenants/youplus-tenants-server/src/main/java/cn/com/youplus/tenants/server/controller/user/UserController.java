package cn.com.youplus.tenants.server.controller.user;

import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.cms.api.system.CmsPermissionService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.form.BaseIdsForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.util.AESUtil;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.RequestUtil;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;
import cn.com.youplus.notification.api.LocationBaseService;
import cn.com.youplus.notification.api.SmsService;
import cn.com.youplus.tenants.api.auto.*;
import cn.com.youplus.tenants.api.common.CommonService;
import cn.com.youplus.tenants.api.common.DataStructureService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import cn.com.youplus.tenants.common.form.CopyProjectForm;
import cn.com.youplus.tenants.common.form.LoginForm;
import cn.com.youplus.tenants.common.form.TitleForm;
import cn.com.youplus.tenants.server.controller.SuperController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/25.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/user")
public class UserController extends SuperController {

    //region autowired
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private UpTenantsUserService upTenantsUserService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpTenantsUserRegionViewService upTenantsUserRegionViewService;

    @Autowired
    private DataStructureService dataStructureService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private LocationBaseService locationBaseService;

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

    @PostMapping("/login")
    public BaseResponse commonLogin(@Valid LoginForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        String domain = RequestUtil.getDomain(request);

        EntityWrapper<UpTenantsUser> upTenantsUserEntity = new EntityWrapper<>();

        if (!domain.equals("localhost")) {
            UpTenantsCompany checkDomain = upTenantsCompanyService.selectOne(
                    new EntityWrapper<UpTenantsCompany>()
                            .eq(UpTenantsCompany.DOMAIN_NAME, domain)
            );
            if (checkDomain == null) {
                throw new AlertException("域名非法");
            }
            upTenantsUserEntity.eq(UpTenantsUser.TENANTS_COMPANY_ID, checkDomain.getId());
        }

        upTenantsUserEntity.eq(UpTenantsUser.USER_NAME, form.getUsername())
                .orderBy(UpTenantsUser.ADD_TIME, false);
        UpTenantsUser user = upTenantsUserService.selectOne(upTenantsUserEntity);

        if (user == null) {
            throw new AlertException("用户名不存在!");
        }

        if (!MD5Util.validPassword(form.getPassword(), user.getSalt(), user.getPassword())) {
            throw new AlertException("密码错误!");
        }

        UpTenantsCompany company = upTenantsCompanyService.selectById(user.getTenantsCompanyId());
        Date now = new Date();
        if (now.getTime() > company.getAuthExpiredTime().getTime()) {
            throw new AlertException("您的公司有效期限已过， 请联系优加工作人员进行续签");
        }

        user.setPassword(null)
                .setSalt(null);

        Map<String, Object> result = new HashMap<>();
        result.put("routers", cmsPermissionService.getTenantsPermissionListByRoleCode(user.getRoleCode()));
        result.put("token", AESUtil.AESEncodeForWeb(user.getId().toString()));
        result.put("userInfo", user);
        result.put("company", company);
        result.put("projectList", commonService.getProjectSummary(company));
        response.setData(result);

        return response;
    }

    @PostMapping("/login/previewData")
    public BaseResponse previewData(BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();
        String domain = RequestUtil.getDomain(request);
        UpTenantsCompany company = upTenantsCompanyService.selectOne(
                new EntityWrapper<UpTenantsCompany>()
                        .eq(UpTenantsCompany.DOMAIN_NAME, domain)
        );
        if (company == null) {
            throw new AlertException("域名错误");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("logoSrc", company.getLogoImg());
        response.setData(result);

        return response;
    }

    @PostMapping("/projectOptions")
    public BaseResponse projectOptions(BaseForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        response.setData(commonService.getProjectSummary(company));
        return response;
    }

    @PostMapping("/project/list")
    public BaseResponse projectList(BaseForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        Map<String, Object> result = new HashMap<>();

        result.put("list", commonService.getProjectSummary(company));
        result.put("recycleBin", commonService.getRecycleBinProject(company));

        response.setData(result);
        return response;
    }

    @PostMapping("project/add")
    public BaseResponse projectAdd(TitleForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        UpAppsQuestionnaire questionnaire = new UpAppsQuestionnaire();
        questionnaire.setProjectName(form.getTitle())
                .setTenantsCompanyId(company.getId())
                .setUpdateTime(new Date())
                .setAddTime(new Date());

        upAppsQuestionnaireService.insert(questionnaire);
        Map<String, Object> result = new HashMap<>();

        result.put("list", commonService.getProjectSummary(company));
        result.put("recycleBin", commonService.getRecycleBinProject(company));

        response.setData(result);

        return response;
    }

    @PostMapping("/project/copy")
    public BaseResponse projectCopy(CopyProjectForm form) throws AlertException {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        Subject subject = (Subject) request.getAttribute(Constants.TENANTS_SUBJECT_KEY);
        Long questionnaireId = questionnaireSettingService.copyQuestionnaire(form.getId(), form.getTitle(), company);
        return response;
    }

    @PostMapping("project/recycleBin/add")
    public BaseResponse projectDelete(BaseIdForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectOne(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.ID, form.getId())
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
        );
        if (questionnaire == null) {
            throw new AlertException("该问卷不存在");
        }
        questionnaire.setStatus(QuestionnaireStatusEnum.已删除.getType())
                .setDeleteTime(new Date())
                .setUpdateTime(new Date());
        upAppsQuestionnaireService.updateById(questionnaire);

        Map<String, Object> result = new HashMap<>();

        result.put("list", commonService.getProjectSummary(company));
        result.put("recycleBin", commonService.getRecycleBinProject(company));

        response.setData(result);
        return response;
    }

    @PostMapping("project/recycleBin/delete")
    public BaseResponse projectRecycleBinDelete(BaseIdsForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        List<Long> ids = new ArrayList<>();
        String[] strs = form.getIds().split(",");
        for (String str : strs) {
            ids.add(Long.parseLong(str));
        }
        UpTenantsCompany company = getCompany(request);
        upAppsQuestionnaireService.delete(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .in(UpAppsQuestionnaire.ID, ids)
        );
        Map<String, Object> result = new HashMap<>();

        result.put("list", commonService.getProjectSummary(company));
        result.put("recycleBin", commonService.getRecycleBinProject(company));

        response.setData(result);
        return response;
    }

    @PostMapping("project/recycleBin/regain")
    public BaseResponse projectRecycleBinRegain(BaseIdsForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        List<Long> ids = new ArrayList<>();
        String[] strs = form.getIds().split(",");
        for (String str : strs) {
            ids.add(Long.parseLong(str));
        }
        UpTenantsCompany company = getCompany(request);

        UpAppsQuestionnaire questionnaire = new UpAppsQuestionnaire();
        questionnaire.setStatus(QuestionnaireStatusEnum.编辑中.getType())
                .setUpdateTime(new Date());

        upAppsQuestionnaireService.update(
                questionnaire,
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpAppsQuestionnaire.STATUS, QuestionnaireStatusEnum.已删除.getType())
                        .in(UpAppsQuestionnaire.ID, ids)
        );
        Map<String, Object> result = new HashMap<>();
        result.put("list", commonService.getProjectSummary(company));
        result.put("recycleBin", commonService.getRecycleBinProject(company));

        response.setData(result);
        return response;
    }

}














