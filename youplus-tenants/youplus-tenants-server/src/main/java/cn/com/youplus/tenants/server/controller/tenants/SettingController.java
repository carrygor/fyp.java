package cn.com.youplus.tenants.server.controller.tenants;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.apps.api.common.QuestionnaireService;
import cn.com.youplus.apps.common.model.enums.QuestionnaireAttrEnum;
import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.exception.interaction.ConfirmPathException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.enums.RouterGoTypeEnum;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.*;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.notification.api.SmsService;
import cn.com.youplus.tenants.api.auto.*;
import cn.com.youplus.tenants.api.common.DataStructureService;
import cn.com.youplus.tenants.api.common.ExcelService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import cn.com.youplus.tenants.common.form.*;
import cn.com.youplus.tenants.common.model.enums.QuestionnaireAttrDataTypeEnum;
import cn.com.youplus.tenants.server.controller.SuperController;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.weixin4j.WeixinConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/25.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/setting")
public class SettingController extends SuperController{

    //region autowired
    private static Logger logger = Logger.getLogger(SettingController.class);

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

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UpAppsQuestionnaireAttributeService upAppsQuestionnaireAttributeService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsPhoneListService upAppsPhoneListService;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }
    //endregion

    @PostMapping("/weixin/getPreAuthCode")
    public BaseResponse weixinGetPreAuthCode(@Valid BaseForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("component_appid", weixinProperties.getAppid());
        HttpConnect httpConnect = HttpConnect.getInstance();
        String url = WeixinConstants.WEIXIN_FETCH_PRE_AUTH_CODE + "?component_access_token=" + systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN);
        JSONObject result = httpConnect.doPost(url, jsonObject);
        String pre_auth_code = result.getString("pre_auth_code");

        Map<String, Object> map = new HashMap<>();
        map.put("pre_auth_code", pre_auth_code);
        map.put("component_appid", weixinProperties.getAppid());
        // TODO: 2017/11/21/021 回调url /questionnaireManage/authSuccess
        String redirect_uri = "http://thirdparty.shenmiren.com.cn/thirdparty/v1/weixin/authorization/" + getCompany(request).getId() + "/feedback";
        redirect_uri = URLEncoder.encode(redirect_uri, "utf-8");
        map.put("redirect_uri", redirect_uri);

        response.setData(map);

        return response;
    }


    /**
     * 公众号授权推送
     * @param form
     * @throws Exception
     */
    @PostMapping("/authorization/feedback")
    public void authorizationFeedback(feedbackForm form){

        UpWeixinAccount weixinAccount = new UpWeixinAccount();
        String auth_code = form.getAuth_code();
        weixinAccount.setAuthorizationCode(auth_code);
        weixinAccount.setTenantsCompanyId(form.getCompanyId());

        //获取凭证
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("component_appid", weixinProperties.getAppid());
        jsonObject.put("authorization_code", auth_code);
        HttpConnect httpConnect = HttpConnect.getInstance();
        String url = null;
        try {
            url = WeixinConstants.WEIXIN_QUERY_AUTH + "?component_access_token=" + systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject result = httpConnect.doPost(url, jsonObject);
        JSONObject authorization_info = result.getJSONObject("authorization_info");
        String appid = "";
        if (authorization_info != null) {
            appid = authorization_info.getString("authorizer_appid");
            String authorizer_access_token = authorization_info.getString("authorizer_access_token");
            String authorizer_refresh_token = authorization_info.getString("authorizer_refresh_token");
            String func_info = authorization_info.getJSONArray("func_info").toJSONString();

            weixinAccount.setAuthorizerAppid(appid);
            weixinAccount.setIsAuth(1);
            weixinAccount.setAuthorizerAccessToken(authorizer_access_token);
            weixinAccount.setAuthorizerRefreshToken(authorizer_refresh_token);
            weixinAccount.setFuncInfo(func_info);

        } else {
            weixinAccount.setDescription("获取authorizer_info失败: " + result.toJSONString());
        }


        //获取授权者信息
        JSONObject infoObject = new JSONObject();
        infoObject.put("component_appid", weixinProperties.getAppid());
        infoObject.put("authorizer_appid", appid);
        try {
            url = WeixinConstants.WEIXIN_GET_AUTHORIZER_INFO + "?component_access_token=" + systemConfigService.getThirdpartyParam(SystemConfigService.THIRDPARTY_WEIXIN_COMPONENT_ACCESSTOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject info = httpConnect.doPost(url, infoObject);
        JSONObject authorizer_info = info.getJSONObject("authorizer_info");
        if (authorizer_info != null) {
            String nick_name = authorizer_info.getString("nick_name");
            String head_img = authorizer_info.getString("head_img");
            String service_type_info = authorizer_info.getJSONObject("service_type_info").toJSONString();
            String verify_type_info = authorizer_info.getJSONObject("verify_type_info").toJSONString();
            String user_name = authorizer_info.getString("user_name");
            String principal_name = authorizer_info.getString("principal_name");
            String business_info = authorizer_info.getJSONObject("business_info").toJSONString();
            String alias = authorizer_info.getString("alias");
            String qrcode_url = authorizer_info.getString("qrcode_url");

            weixinAccount.setNickName(nick_name);
            weixinAccount.setHeadImg(head_img);
            weixinAccount.setServiceTypeInfo(service_type_info);
            weixinAccount.setVerifyTypeInfo(verify_type_info);
            weixinAccount.setUserName(user_name);
            weixinAccount.setPrincipalName(principal_name);
            weixinAccount.setBusinessInfo(business_info);
            weixinAccount.setAlias(alias);
            weixinAccount.setQrcodeUrl(qrcode_url);
        } else {
            weixinAccount.setDescription(weixinAccount.getDescription() + ";  获取authorizer_info失败: " + info.toJSONString());
        }


        weixinAccount.setAddTime(new Date());
        weixinAccount.setUpdateTime(new Date());

        UpWeixinAccount checkAccount = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.AUTHORIZER_APPID, appid)
                        .orderBy(UpWeixinAccount.ADD_TIME, false)
        );
        if (checkAccount == null) {
            upWeixinAccountService.insert(weixinAccount);
        } else {
            weixinAccount.setId(checkAccount.getId());
            upWeixinAccountService.updateById(weixinAccount);
        }
    }

    @PostMapping("/weixin/info")
    public BaseResponse weixinInfo(BaseForm form) {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        UpWeixinAccount weixinAccount = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpWeixinAccount.IS_AUTH, 1)
        );

        Map<String, Object> result = new HashMap<>();
        if (weixinAccount == null) {
            result.put("isAuth", false);
        } else {
            result.put("isAuth", true);
            Map<String, Object> account = new HashMap<>();
            account.put("headImg", weixinAccount.getHeadImg());
            account.put("alias", weixinAccount.getAlias());
            account.put("nickName", weixinAccount.getNickName());
            result.put("account", account);
        }
        response.setData(result);

        return response;
    }

    @PostMapping("/weixin/questionnaire/options")
    public BaseResponse weixinQuestionnaireOptions(BaseForm form) {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        List<UpAppsQuestionnaire> questionnaireList = upAppsQuestionnaireService.selectList(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpAppsQuestionnaire.STATUS, QuestionnaireStatusEnum.收集中.getType())
        );
        List<Map<String, Object>> list = new ArrayList<>();
        for (UpAppsQuestionnaire questionnaire : questionnaireList) {
            Map<String, Object> map = new HashMap<>();
            map.put("label", questionnaire.getTitle());
            map.put("value", questionnaire.getId());
            list.add(map);
        }
        response.setData(list);
        return response;
    }

    @PostMapping("/weixin/template/get")
    public BaseResponse weixinTemplateGet(BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        JSONObject jsonObject = JSONObject.parseObject(company.getTemplateJson());
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        response.setData(jsonObject);

        return response;
    }

    @PostMapping("/weixin/template/edit")
    public BaseResponse weixinTemplateEdit(JSONForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpTenantsCompany company = getCompany(request);
        JSONObject jsonObject = JSONObject.parseObject(form.getJsonString());
        if (jsonObject == null) {
            throw new AlertException("格式错误");
        }
        company.setTemplateJson(form.getJsonString());
        if (!upTenantsCompanyService.updateById(company)) {
            throw new AlertException("插入失败,请重试");
        }

        return response;
    }

    @PostMapping("/weixinSetting/get")
    public BaseResponse weixinSettingGet(BaseForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        // TODO: 2017/12/25/025

        return response;
    }

    @PostMapping("/questionnaire/edit")
    public BaseResponse questionnaireEdit(BaseForm form) throws AlertException, IOException {
        BaseResponse response = new BaseResponse();
        String jsonStr = RequestUtil.getRequestPostStr(request);
        UpTenantsCompany company = getCompany(request);

        Object object = request.getAttribute(Constants.TENANTS_SUBJECT_KEY);
        Subject subject = (Subject) object;
        boolean isVip = true;
        try {
            //function要加F前缀
            subject.checkPermission("FVIP付费接口:短信验证码");
        } catch (AuthorizationException e) {
            isVip = false;
        }
        JSONObject data = questionnaireService.editQuestionnaire(jsonStr, company, isVip);
        response.setData(data);
        return response;
    }

    @PostMapping("/questionnaire/publish")
    public BaseResponse questionnairePublish(BaseIdForm form) throws AlertException, IOException, ConfirmPathException {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        //检查是否存在组织结构
        int count = upTenantsRegionService.selectCount(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );

        if (count == 0) {
            throw new ConfirmPathException("您还没有设置公司的组织架构,请先设置后再发布问卷。",
                    "返回",
                    "去设置",
                    "/" + form.getId() + "/systemSetting/organization/index", RouterGoTypeEnum.GO);
        }

        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(form.getId());
        if (questionnaire == null) {
            throw new AlertException("问卷不存在[" + form.getId() + "]");
        }

        questionnaire.setStatus(QuestionnaireStatusEnum.收集中.getType())
                .setUpdateTime(new Date())
                .setExpiredTime(null);
        if (questionnaire.getStartTime() == null) {
            questionnaire.setStartTime(new Date());
        }
        upAppsQuestionnaireService.updateById(questionnaire);

        response.setData("发布成功!");
        return response;
    }

    @PostMapping("/questionnaire/disable")
    public BaseResponse questionnaireDisable(BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(form.getId());
        if (questionnaire == null) {
            throw new AlertException("该问卷不存在");
        }
        if (!QuestionnaireStatusEnum.valueOf(questionnaire.getStatus()).equals(QuestionnaireStatusEnum.收集中)) {
            throw new AlertException("该问卷状态为[" + questionnaire.getStatus() + "]，禁止停用");
        }
        questionnaire.setStatus(QuestionnaireStatusEnum.已停用.getType())
                .setExpiredTime(new Date())
                .setUpdateTime(new Date());
        upAppsQuestionnaireService.updateById(questionnaire);

        return response;
    }

    @PostMapping("/questionnaire/get")
    public BaseResponse questionnaireGet(BaseIdForm form) throws AlertException {
        BaseResponse response = new BaseResponse();
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(form.getId());

        if (questionnaire == null) {
            throw new AlertException("问卷不存在[" + form.getId() + "]");
        }
        Map<String, Object> result = questionnaireSettingService.getQuestionnaireData(questionnaire);
        //JSONObject json = JSONObject.parseObject(questionnaire.getQuestionnaireJson());
        response.setData(result);
        return response;
    }

    @PostMapping("questionnaireSetting/edit")
    public BaseResponse questionnaireSettingEdit(SettingForm form) {
        BaseResponse response = new BaseResponse();
        Long questionnaireId = form.getQuestionnaireId();

        List<UpAppsQuestionnaireAttribute> attributes = upAppsQuestionnaireAttributeService.selectList(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaireId)
        );
        Map<String, UpAppsQuestionnaireAttribute> attrMap = new HashMap<>();
        for (UpAppsQuestionnaireAttribute attribute : attributes) {
            attrMap.put(attribute.getAttributeName(), attribute);
        }

        //随机题目设置
        JSONObject randomQuestion = JSONObject.parseObject(form.getRandomQuestion());
        Boolean checked = randomQuestion.getBoolean("check");
        if (checked) {
            UpAppsQuestionnaireAttribute randomQuestionAttr = attrMap.get(QuestionnaireAttrEnum.RANDOM_QUESTION.getName());
            if (randomQuestionAttr == null) {
                randomQuestionAttr = new UpAppsQuestionnaireAttribute();
                randomQuestionAttr.setAddTime(new Date())
                        .setAttributeName(QuestionnaireAttrEnum.RANDOM_QUESTION.getName())
                        .setDataType(QuestionnaireAttrDataTypeEnum.STRING.getType())
                        .setQuestionnaireId(questionnaireId);
            }
            randomQuestionAttr.setAttributeStringValue(randomQuestion.toJSONString())
                    .setUpdateTime(new Date());
            upAppsQuestionnaireAttributeService.insertOrUpdate(randomQuestionAttr);
        } else {
            UpAppsQuestionnaireAttribute randomQuestionAttr = attrMap.get(QuestionnaireAttrEnum.RANDOM_QUESTION.getName());
            if (randomQuestionAttr != null) {
                upAppsQuestionnaireAttributeService.deleteById(randomQuestionAttr.getId());
            }
        }

        //限制设置
        JSONArray limitSetting = JSONArray.parseArray(form.getLimitSetting());
        for (Object object : limitSetting) {
            JSONObject setting = (JSONObject) object;
            UpAppsQuestionnaireAttribute settingAttr = attrMap.get(setting.getString("name"));
            if (settingAttr == null) {
                settingAttr = new UpAppsQuestionnaireAttribute();
                settingAttr.setQuestionnaireId(questionnaireId)
                        .setAttributeName(setting.getString("name"))
                        .setDataType(QuestionnaireAttrDataTypeEnum.LONG.getType())
                        .setAddTime(new Date());
            }
            Boolean check =setting.getBooleanValue("check");
            settingAttr.setUpdateTime(new Date())
                    .setAttributeLongValue(check ? 1L : 0);
            String stringValue = setting.getString("stringValue");
            Long longValue = setting.getLong("longValue");
            Long timestampValue = setting.getLong("timestampValue");
            if (stringValue != null) {
                settingAttr.setAttributeStringValue(stringValue);
            }
            if (longValue != null && longValue != 0 && check) {
                settingAttr.setAttributeLongValue(longValue);
            }
            if (timestampValue != null && timestampValue != 0) {
                settingAttr.setAttributeTimestampValue(new Date(timestampValue));
            }
            upAppsQuestionnaireAttributeService.insertOrUpdate(settingAttr);
        }

        //其他设置
        JSONArray settingList = JSONArray.parseArray(form.getSettingNames());
        for (Object object : settingList) {
            JSONObject setting = (JSONObject) object;
            UpAppsQuestionnaireAttribute settingAttr = attrMap.get(setting.getString("name"));
            if (settingAttr == null) {
                settingAttr = new UpAppsQuestionnaireAttribute();
                settingAttr.setQuestionnaireId(questionnaireId)
                        .setAttributeName(setting.getString("name"))
                        .setDataType(QuestionnaireAttrDataTypeEnum.LONG.getType())
                        .setAddTime(new Date());
            }
            settingAttr.setUpdateTime(new Date())
                    .setAttributeLongValue(setting.getBooleanValue("check") ? 1L : 0);
            upAppsQuestionnaireAttributeService.insertOrUpdate(settingAttr);
        }

        return response;
    }

    @PostMapping("questionnaireSetting/get")
    public BaseResponse questionnaireSettingGet(BaseIdForm form) {
        BaseResponse response = new BaseResponse();
        Map<String, Object> result = new HashMap<>();
        Long questionnaireId = form.getId();
        UpAppsQuestionnaireAttribute randomQuestion = upAppsQuestionnaireAttributeService.selectOne(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaireId)
                        .eq(UpAppsQuestionnaireAttribute.ATTRIBUTE_NAME, QuestionnaireAttrEnum.RANDOM_QUESTION.getName())
        );
        if (randomQuestion == null) {
            result.put("randomQuestion", null);
        } else {
            result.put("randomQuestion", JSONObject.parseObject(randomQuestion.getAttributeStringValue()));
        }

        List<UpAppsQuestionnaireAttribute> list = upAppsQuestionnaireAttributeService.selectList(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaireId)
        );
        List<String> settingNames = new ArrayList<>();
        List<Map<String, Object>> limitSetting = new ArrayList<>();

        for (UpAppsQuestionnaireAttribute attribute : list) {
            if (attribute.getDataType().equals(QuestionnaireAttrDataTypeEnum.LONG.getType()) && attribute.getAttributeLongValue().equals(new Long(1L))) {
                settingNames.add(attribute.getAttributeName());
            }
            if (attribute.getAttributeName().contains("limit")) {
                Map<String, Object> setting = new HashMap<>();
                setting.put("name", attribute.getAttributeName());
                setting.put("check", attribute.getAttributeLongValue() > 0);
                String stringValue = attribute.getAttributeStringValue();
                Long longValue = attribute.getAttributeLongValue();
                Date timestampValue = attribute.getAttributeTimestampValue();
                if (stringValue != null) {
                    setting.put("stringValue", stringValue);
                }
                if (longValue != null) {
                    setting.put("longValue", longValue);
                }
                if (timestampValue != null) {
                    setting.put("timestampValue", timestampValue);
                }
                limitSetting.add(setting);
            }
        }

        UpAppsQuestion question = upAppsQuestionService.selectOne(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaireId)
                        .eq(UpAppsQuestion.QUESTION_TYPE, QuestionTypeEnum.手机验证.getType())
        );
        boolean hasSms = question != null;
        Map<String, Object> remark = new HashMap<>();
        remark.put("hasSms", hasSms);

        result.put("remark", remark);
        result.put("settingNames", settingNames);
        result.put("limitSetting", limitSetting);


        response.setData(result);

        return response;
    }

    @PostMapping("questionnaireSetting/question/get")
    public BaseResponse questionSettingQuestionGet(BaseIdForm form) {
        BaseResponse response = new BaseResponse();

        List<UpAppsQuestion> list = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, form.getId())
        );

        List<Map<String, Object>> questionList = new ArrayList<>();

        for (UpAppsQuestion question : list) {
            QuestionTypeEnum questionType = QuestionTypeEnum.valueOf(question.getQuestionType());
            //设置了显示逻辑的题目和短信验证码题目不能再加入随机题库
            if (ValueHelper.isNone(question.getDisplayRule()) && !questionType.equals(QuestionTypeEnum.手机验证)) {
                Map<String, Object> item = new HashMap<>();
                item.put("key", question.getId());
                item.put("label", question.getTitle());
                questionList.add(item);
            }
        }
        response.setData(questionList);

        return response;
    }

    @PostMapping("questionnaireSetting/link/data")
    public BaseResponse questionnaireSettingLinkData(LinkDataForm form) throws Exception{
        BaseResponse response = new BaseResponse();
        UpTenantsRegion region = upTenantsRegionService.selectById(form.getRegionId());
        if (region.getIsStore() == 0) {
            throw new AlertException("请选择网点");
        }

        JSONObject data = new JSONObject();
        data.put("q", form.getQuestionnaireId().toString());
        data.put("c", getCompany(request).getId().toString());
        data.put("r", form.getRegionId().toString());
        String prefix = systemConfig.getAppUrlWithVersion() + "/q/qrconnect";
        String suffix = form.getQueryString();
        String url = prefix + suffix;
        //url = String.format(url, RequestUtil.getDomain(request));
        String iframe = "<iframe height=\"1200\" width=\"600\" src=\"" + url + "\"></frame>";

        String format = "png";// 图像类型
        String qrcode256 = questionnaireSettingService.UrlToQrcodeAndUpload(url, format, 256);
        String qrcode512 = questionnaireSettingService.UrlToQrcodeAndUpload(url, format, 512);
        String qrcode1024 = questionnaireSettingService.UrlToQrcodeAndUpload(url,  format, 1024);

        Map<String, Object> result = new HashMap<>();
        result.put("link", url);
        result.put("iframe", iframe);
        result.put("qrcode256", qrcode256);
        result.put("qrcode512", qrcode512);
        result.put("qrcode1024", qrcode1024);
        response.setData(result);

        return response;
    }

    @PostMapping("questionnaireSetting/link/allData/create")
    public BaseResponse questionnaireSettingLinkAllDataCreate(BaseIdForm form) {
        UpTenantsCompany company = getCompany(request);
        UpAppsQuestionnaireAttribute attribute = upAppsQuestionnaireAttributeService.selectOne(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, form.getId())
                        .eq(UpAppsQuestionnaireAttribute.ATTRIBUTE_NAME, QuestionnaireAttrEnum.CREATE_ALL_LINK.getName())
        );
        if (attribute == null) {
            attribute = new UpAppsQuestionnaireAttribute();
        }
        attribute.setAttributeLongValue(1L)
                .setAttributeName(QuestionnaireAttrEnum.CREATE_ALL_LINK.getName())
                .setQuestionnaireId(form.getId())
                .setUpdateTime(new Date());
        upAppsQuestionnaireAttributeService.insertOrUpdate(attribute);

        questionnaireSettingService.createBatchLinkAndQrcodeWithNewThread(company, form.getId(), 256);
        return new BaseResponse();
    }

    @PostMapping("questionnaireSetting/link/allData/check")
    public BaseResponse questionnaireSettingLinkAllDataCheck(BaseIdForm form) throws ConfirmPathException {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        //检查
        int count = upTenantsRegionService.selectCount(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );

        if (count == 0) {
            throw new ConfirmPathException("您还没有设置公司的组织架构,请先设置后再发布问卷。",
                    "返回",
                    "去设置",
                    "/" + form.getId() + "/systemSetting/organization/index",
                    RouterGoTypeEnum.GO);
        }

        Map<String, Object> result = new HashMap<>();
        UpAppsQuestionnaireAttribute attribute = upAppsQuestionnaireAttributeService.selectOne(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, form.getId())
                        .eq(UpAppsQuestionnaireAttribute.ATTRIBUTE_NAME, QuestionnaireAttrEnum.CREATE_ALL_LINK.getName())
        );
        boolean flag = false;
        if (attribute != null) {
            flag = attribute.getAttributeLongValue() == 1L;
        }
        result.put("createAllData", flag);
        result.put("hasData", questionnaireSettingService.linkAllDataCheck(company, form.getId()));
        result.put("link", "excel/" + company.getDomainName() + "-" + form.getId() + ".zip");

        return response.setData(result);
    }

    @PostMapping("questionnaireSetting/link/allData/delete")
    public BaseResponse questionnaireSettingLinkAllDataDelete(BaseIdForm form) {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        questionnaireSettingService.linkAllDataDelete(company, form.getId());

        return response;
    }

    @PostMapping("/phoneList/upload")
    public BaseResponse regionUpload(@RequestParam("file") MultipartFile file, Long questionnaireId, String type) throws Exception {
        BaseResponse response = new BaseResponse();

        InputStream is = file.getInputStream();

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        upAppsPhoneListService.delete(
                new EntityWrapper<UpAppsPhoneList>()
                        .eq(UpAppsPhoneList.QUESTIONNAIRE_ID, questionnaireId)
        );

        List<String> errList = new ArrayList<>();
        for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            try {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                XSSFCell xssfCell = xssfRow.getCell(0);
                String phoneNum = xssfCell.getRawValue();
                if (!StringHelper.isPhoneLegal(phoneNum)) {
                    errList.add(phoneNum);
                    continue;
                }
                UpAppsPhoneList upAppsPhoneList = new UpAppsPhoneList();
                upAppsPhoneList.setPhoneNum(phoneNum)
                        .setQuestionnaireId(questionnaireId)
                        .setType(type)
                        .setAddTime(new Date())
                        .setUpdateTime(new Date());
                upAppsPhoneListService.insert(upAppsPhoneList);
            } catch (Exception e) {
                e.printStackTrace();
                errList.add(xssfSheet.getRow(rowNum).getCell(0).getStringCellValue());
            }
        }
        is.close();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : errList) {
            stringBuilder.append(str);
            stringBuilder.append("\r\n");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalNum", xssfSheet.getLastRowNum() + 1);
        result.put("successNum", xssfSheet.getLastRowNum() + 1 - errList.size());
        result.put("errorNum", errList.size());
        result.put("errorList", stringBuilder.toString());
        response.setData(result);
        return response;
    }

    @PostMapping("/phoneList/check")
    public BaseResponse phoneListCheck(PhoneListForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        List<UpAppsPhoneList> phoneList = upAppsPhoneListService.selectList(
                new EntityWrapper<UpAppsPhoneList>()
                        .eq(UpAppsPhoneList.TYPE, form.getType())
                        .eq(UpAppsPhoneList.QUESTIONNAIRE_ID, form.getQuestionnaireId())
        );
        StringBuilder stringBuilder = new StringBuilder();
        for (UpAppsPhoneList phone : phoneList) {
            stringBuilder.append(phone.getPhoneNum());
            stringBuilder.append("\r\n");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("phoneList", stringBuilder.toString());

        response.setData(result);
        return response;
    }

    @PostMapping("/phoneList/checkAvailable")
    public BaseResponse phoneListCheckAvailable(BaseIdForm form) throws Exception {
        UpAppsQuestionItem item = upAppsQuestionItemService.selectOne(
                new EntityWrapper<UpAppsQuestionItem>()
                        .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, form.getId())
                        .eq(UpAppsQuestionItem.EDITOR_TYPE, "phone")
                        .orderBy(UpAppsQuestionItem.QUESTION_ID, false)
        );
        if (item == null) {
            throw new AlertException("该问卷没有设置类型为手机号码的必填项，请到问卷设计页面进行设置。");
        }

        return new BaseResponse();
    }

    @PostMapping("/phoneList/download")
    public BaseResponse phoneListDownLoad(PhoneListForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        Map<String, Object> result = new HashMap<>();
        result.put("path", excelService.exportPhoneList(company, form));
        response.setData(result);

        return response;
    }

}


