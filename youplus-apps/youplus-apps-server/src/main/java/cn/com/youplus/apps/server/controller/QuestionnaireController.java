package cn.com.youplus.apps.server.controller;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.apps.api.common.QuestionnaireService;
import cn.com.youplus.apps.common.form.CheckForm;
import cn.com.youplus.apps.common.form.QuestionnaireForm;
import cn.com.youplus.apps.common.form.QuestionnaireGetForm;
import cn.com.youplus.apps.common.form.SmsCodeForm;
import cn.com.youplus.apps.common.model.Questionnaire;
import cn.com.youplus.apps.common.model.enums.EntranceEnum;
import cn.com.youplus.apps.common.model.enums.PhoneListTypeEnum;
import cn.com.youplus.apps.common.model.enums.QuestionnaireAttrEnum;
import cn.com.youplus.base.api.LhAnswerSheetService;
import cn.com.youplus.base.api.LogHubService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.Location;
import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.common.model.enums.*;
import cn.com.youplus.common.model.log.AnswerSheetRecordLog;
import cn.com.youplus.common.model.log.AnswerSheetRequestLog;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.model.tablestore.*;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.*;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.notification.UpNotificationSmsRecord;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinUser;
import cn.com.youplus.notification.api.LocationBaseService;
import cn.com.youplus.notification.api.SmsService;
import cn.com.youplus.notification.api.auto.UpNotificationSmsRecordService;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import cn.com.youplus.thirdparty.api.weixin.WeixinService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.SyncClient;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apps/v1/q")
public class QuestionnaireController extends SuperController{

    //region autowired
    Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpNotificationSmsRecordService upNotificationSmsRecordService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsQuestionnaireAttributeService upAppsQuestionnaireAttributeService;

    @Autowired
    private UpAppsPhoneListService upAppsPhoneListService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;

    @Autowired
    private UpAppsAnswerSheetService upAppsAnswerSheetService;

    @Autowired
    private LocationBaseService locationBaseService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private LogHubService logHubService;

    @Autowired
    private LhAnswerSheetService lhAnswerSheetService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private SyncClient syncClient;

    @Resource(name = "tableStoreService")
    private TableStoreServiceImpl tableStoreService;

    //endregion

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    String get微信授权Url(JSONObject jsonObject) throws UnsupportedEncodingException {
        String prefixPath = "/#/welcome/QUERY";
        return URLEncoder.encode(systemConfig.getAppUrl()+ prefixPath + getQueryString(jsonObject),"utf-8");
    }

    String get直接跳转Url(JSONObject jsonObject) throws UnsupportedEncodingException {
        String prefixPath = "/#/welcome/QUERY";
        return systemConfig.getAppUrl() + prefixPath + getQueryString(jsonObject);
    }

    String getQueryString(JSONObject jsonObject) {
        StringBuilder queryString = new StringBuilder("?");
        jsonObject.forEach((key, value)->{
            queryString.append(key).append("=").append(value).append("&");
        });
        String s = queryString.toString();
        return s.substring(0, s.length() - 1);
    }

    //统一入口
    @GetMapping("/qrconnect")
    public String enter(@Valid CheckForm form) throws Exception {
        Long questionnaireId = form.getQuestionnaireId();
        Long regionId = form.getRegionId();
        String rcode = form.getRoleCode();
        String rname = form.getRoleName();
        Long companyId = form.getCompanyId();
        String orderSn = form.getOrderSn();
        String entrance;
        //String appUrl = systemConfig.getAppUrl() + "/#/welcome";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("q", questionnaireId.toString());
        if (regionId != null) {
            jsonObject.put("r", regionId.toString());
        }
        if (rcode != null) {
            jsonObject.put("rcode", rcode);
        }
        if (rname != null) {
            jsonObject.put("rname", rname);
        }
        if (companyId != null) {
            jsonObject.put("c", companyId.toString());
        }
        if (orderSn != null) {
            jsonObject.put("orderSn", orderSn);
        }
        String url;
        if (RequestUtil.isWeixinBrowser(request)) {
            //region 微信逻辑
            boolean isNeedAuth = questionnaireService.getLongValueToBoolean(questionnaireId, QuestionnaireAttrEnum.USER_LOGIN.getName());

            if (isNeedAuth) {  //需要用户验证
                if (true) {
                    jsonObject.put("entrance", EntranceEnum.WEIXIN_INFO_OAUTH.getEntrance());

                    //尝试从cookies中获取微信用户
                    if (weixinService.getWeixinUserByRefreshToken(
                            CookieUtil.getCookie(request,Constants.COOKIES_YOUPLUS_ACCESS_TOKEN),
                            EntranceEnum.WEIXIN_INFO_OAUTH) != null) {
                        url = get直接跳转Url(jsonObject);
                    } else {
                        url = String.format(WeixinConstants.WEIXIN_OAUTH2授权_URL,
                                weixinProperties.getOfficialAccountsAppId(),
                                get微信授权Url(jsonObject),
                                "userinfo",
                                questionnaireId);
                    }
                } else {
                    jsonObject.put("entrance", EntranceEnum.WEIXIN_BASE_OAUTH.getEntrance());
                    if (weixinService.getWeixinUserByRefreshToken(
                            CookieUtil.getCookie(request,Constants.COOKIES_YOUPLUS_ACCESS_TOKEN),
                            EntranceEnum.WEIXIN_BASE_OAUTH) != null) {
                        url = get直接跳转Url(jsonObject);
                    } else {
                        url = String.format(WeixinConstants.WEIXIN_OAUTH2授权_URL,
                                weixinProperties.getOfficialAccountsAppId(),
                                get微信授权Url(jsonObject),
                                "base",
                                questionnaireId);
                    }
                }
//                UpWeixinAccount weixinAccount = upWeixinAccountService.selectOne(
//                        new EntityWrapper<UpWeixinAccount>()
//                        .eq(UpWeixinAccount.TENANTS_COMPANY_ID, companyId)
//                        .eq(UpWeixinAccount.IS_AUTH, 1)
//                );
//                boolean isNeedUserLogin = questionnaireService.getLongValueToBoolean(questionnaireId, QuestionnaireAttrEnum.USER_LOGIN.getName());
//                String componentAppid = weixinProperties.getAppid();
//                if (weixinAccount == null) { //没有绑定第三方平台，需要走网页应用路径
//
//                } else {
//                    if (isNeedUserLogin) {
//                        url = String.format(WeixinConstants.WEIXIN_OAUTH_USERINFO_URL,
//                                weixinAccount.getAuthorizerAppid(), URLEncoder.encode(appUrl, "utf-8"), questionnaireId, componentAppid);
//                    } else {
//                        url = String.format(WeixinConstants.WEIXIN_OAUTH_BASE_URL,
//                                weixinAccount.getAuthorizerAppid(), URLEncoder.encode(appUrl, "utf-8"), questionnaireId, componentAppid);
//                    }
//                }
            } else {
                entrance = EntranceEnum.WEIXIN.getEntrance();
                jsonObject.put("entrance", entrance);
                url = get直接跳转Url(jsonObject);
            }
            //endregion
        } else {
            //region 普通浏览器
            boolean isNeedAuth = questionnaireService.getLongValueToBoolean(questionnaireId, QuestionnaireAttrEnum.USER_LOGIN.getName());
            if (isNeedAuth) {
                jsonObject.put("entrance", EntranceEnum.LINK_OAUTH.getEntrance());
                if (weixinService.getWeixinUserByRefreshToken(
                        CookieUtil.getCookie(request,Constants.COOKIES_WEBAPP_ACCESS_TOKEN),
                        EntranceEnum.WEIXIN_BASE_OAUTH) != null) {
                    url = get直接跳转Url(jsonObject);
                } else {
                    url = String.format(WeixinConstants.WEIXIN_网页应用_OAUTH_URL,
                            weixinProperties.getWebappId(),
                            get微信授权Url(jsonObject),
                            questionnaireId);
                }
            } else {
                entrance = EntranceEnum.LINK.getEntrance();
                jsonObject.put("entrance", entrance);
                url = get直接跳转Url(jsonObject);
            }
            //endregion
        }
        response.sendRedirect(url);
        return null;
    }

    @Permission
    @PostMapping("/questionnaire")
    public BaseResponse<Map<String, Object>> getQuestionnaire(@Valid QuestionnaireGetForm form) throws Exception {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(form.getId());

        boolean isPreViewMode = !ValueHelper.isNone(request.getParameter("preview_mode"));
        if (!isPreViewMode) { //只要带了这个参数就算是预览模式
            BaseResponse r = commonQuestionnaireCheck(questionnaire);
            if (r != null) {
                return r;
            }
        }

        long accessLogId = form.getAccessLogId() == null ? 0 : form.getAccessLogId();
        if (!isPreViewMode && accessLogId <= 0) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("答卷异常，请退出后重试!");

            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("访问ID: ").append(form.getAccessLogId());
            return response;
        }

        TsAnswerSheetAccessLog accessLog = new TsAnswerSheetAccessLog();
        accessLog.setQuestionnaireId(form.getId());
        accessLog.setAccessLogId(form.getAccessLogId());

        boolean hasAccessLog = tableStoreService.getRow(accessLog);
        if (!isPreViewMode && !hasAccessLog) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("答卷异常，请退出后重试!");

            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("访问ID: ").append(form.getAccessLogId());
            return response;
        }

        UpAppsQuestionnaireTheme theme = upAppsQuestionnaireThemeService.selectOne(
                new EntityWrapper<UpAppsQuestionnaireTheme>()
                        .eq(UpAppsQuestionnaireTheme.ID, questionnaire.getThemeId())
        );

        List<UpAppsQuestion> questions = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaire.getId())
                        .orderBy(UpAppsQuestion.QUESTION_ORDER)
                        .orderBy(UpAppsQuestion.ADD_TIME, false)
        );

        List<UpAppsQuestionnaireAttribute> attributes = upAppsQuestionnaireAttributeService.selectList(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                       .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaire.getId())
        );
        Map<String, UpAppsQuestionnaireAttribute> attributeMap = new HashMap<>();
        for (UpAppsQuestionnaireAttribute attribute : attributes) {
            attributeMap.put(attribute.getAttributeName(), attribute);
        }
        UpAppsQuestionnaireAttribute randomQuestionnaireAttribute = attributeMap.get(QuestionnaireAttrEnum.RANDOM_QUESTION.getName());
        UpAppsQuestionnaireAttribute verificationCodeAttribute = attributeMap.get(QuestionnaireAttrEnum.VERIFICATION_CODE.getName());
        UpAppsQuestionnaireAttribute eachQuestionAttribute = attributeMap.get(QuestionnaireAttrEnum.EACH_QUESTION.getName());
        boolean verificationCode = false;
        if (verificationCodeAttribute != null) {
            verificationCode = verificationCodeAttribute.getAttributeLongValue() == 1L;
        }

        Map<String, Object> resData = new HashMap<>();
        if (randomQuestionnaireAttribute != null) {
            JSONObject object = JSONObject.parseObject(randomQuestionnaireAttribute.getAttributeStringValue());
            resData.put("randomQuestion", object);
        }
        resData.put("verificationCode", verificationCode);
        resData.put("eachQuestion", eachQuestionAttribute != null && eachQuestionAttribute.getAttributeLongValue() > 0);
        resData.put("questionnaire", new Questionnaire(questionnaire, theme, questions));

        if (!isPreViewMode) {
            accessLog.setQuestionnaireId(form.getId());
            accessLog.setAccessLogId(form.getAccessLogId());
            accessLog.setUri("getQuestionnaire");
            accessLog.setStartTime(new Date());
            accessLog.setIp(RequestUtil.getIpAddr(request));
            String cookies = request.getAttribute(Constants.COOKIES_QUESTIONNIRE).toString();
            accessLog.setCookies(cookies);
            try {
                tableStoreService.updateRow(accessLog);
            } catch (Exception e) {
                //
            }
        }

        response.setData(resData);
        return response;
    }

    private BaseResponse commonQuestionnaireCheck(UpAppsQuestionnaire questionnaire) {
        BaseResponse response = new BaseResponse();
        if (questionnaire == null) {
            response.setErrcode(ResponseEnum.AlERT.getCode())
                    .setErrmsg("答卷异常");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("答卷异常:答卷不存在！");
            logger.info(stringBuilder.toString());
        }

        QuestionnaireStatusEnum statusEnum = null;
        try {
            statusEnum = QuestionnaireStatusEnum.valueOf(questionnaire.getStatus());
        } catch (Exception e) {
            response.setErrcode(ResponseEnum.AlERT.getCode())
                    .setErrmsg("答卷状态错误！");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("答卷状态错误:")
                    .append(questionnaire.getStatus());
            logger.info(stringBuilder.toString());
        }

        switch (statusEnum) {
            case 编辑中:
            case 待收集:
                response.setErrcode(ResponseEnum.AlERT.getCode())
                        .setErrmsg("该问卷还没有开始启用！");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("答卷状态错误:")
                        .append(questionnaire.getStatus());
                logger.info(stringBuilder.toString());
                break;
            case 已删除:
            case 已停用:
            case 已过期:
                response.setErrcode(ResponseEnum.AlERT.getCode())
                        .setErrmsg("问卷已过期！");
                stringBuilder = new StringBuilder();
                stringBuilder.append("问卷已过期:")
                        .append(questionnaire.getStatus());
                logger.info(stringBuilder.toString());
                break;
        }

        if (questionnaire.getExpiredTime() != null &&questionnaire.getExpiredTime().getTime() < new Date().getTime()) {
            response.setErrcode(ResponseEnum.AlERT.getCode())
                    .setErrmsg("问卷已过期！");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("问卷已过期:")
                    .append(questionnaire.getStatus());
            logger.info(stringBuilder.toString());
        }
        return response.getErrcode() == 0 ? null : response;
    }

    @Permission
    @PostMapping("/answer/post")
    public BaseResponse<Map<String, Object>> answer(@Valid QuestionnaireForm form) throws Exception {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        Long questionnaireId = form.getQuestionnaireId();
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        UpTenantsCompany company = upTenantsCompanyService.selectById(questionnaire.getTenantsCompanyId());
        //数据准备
        String phoneNum = form.getPhoneNum();
        Long regionId = form.getRegionId();
        Map<String, UpAppsQuestionnaireAttribute> attributeMap = questionnaireSettingService.getQuestionnaireAttrMap(questionnaireId);

        //验证码校验
        String verifyCode = form.getVerifyCode();
        if (!ValueHelper.isNone(verifyCode)) {
            UpNotificationSmsRecord upNotificationSmsRecord = smsService.checkVerifyCode(phoneNum, verifyCode);
            if (upNotificationSmsRecord == null) {
                response.setErrcode(ResponseEnum.AlERT.getCode());
                response.setErrmsg("验证码不正确");
                return response;
            }
            smsService.consumeVerifyCode(upNotificationSmsRecord);

        }

        Long accessLogId = form.getAnswerSheetId(); //历史原因，使用了answerSheerId
        if (accessLogId == null) {
            accessLogId = 0L;
        }

        if (accessLogId <= 0) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("答卷异常，请退出后重试!");

            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("手机号:[")
                    .append(form.getPhoneNum())
                    .append("] 答卷ID: ").append(form.getAnswerSheetId());
            return response;
        }

        String ip = RequestUtil.getIpAddr(request);

        TsAnswerSheetAccessLog accessLog = new TsAnswerSheetAccessLog();
        accessLog.setQuestionnaireId(form.getQuestionnaireId());
        accessLog.setAccessLogId(accessLogId);
        boolean hasAccessRecord = tableStoreService.getRow(accessLog);

        if (!hasAccessRecord) {
            response.setErrcode(ResponseEnum.AlERT.getCode())
                    .setErrmsg("答卷异常");
            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("手机号:[")
                    .append(form.getPhoneNum())
                    .append("] IP:[")
                    .append(ip)
                    .append("]  答卷ID: ").append(form.getAnswerSheetId());
            logger.info(stringBuilder.toString());
            return response;
        }

        long t = new Date().getTime() - accessLog.getStartTime().getTime();

        String entrance = accessLog.getEntrance();

        EntranceEnum entranceEnum = null;
        try {
            entranceEnum = EntranceEnum.valueOf(entrance);
        } catch (Exception e) {
            //nothing
        }

        boolean isAuth = false;
        String cookies = null;
        UpWeixinUser user = null;
        if (entranceEnum != null) {
            switch(entranceEnum) {
                case LINK:
                case WEIXIN:
                    isAuth = true;
                    break;
                case LINK_OAUTH:
                    cookies = CookieUtil.getCookie(request,Constants.COOKIES_WEBAPP_ACCESS_TOKEN);
                    break;
                case WEIXIN_INFO_OAUTH:
                case WEIXIN_BASE_OAUTH:
                    cookies = CookieUtil.getCookie(request,Constants.COOKIES_YOUPLUS_ACCESS_TOKEN);
                    break;
                case WEIXIN_COMPHONENT_BASE_OAUTH:
                case WEIXIN_COMPHONENT_INFO_OAUTH:
                    cookies = CookieUtil.getCookie(request,Constants.COOKIES_TIHRDPARTY_ACCESS_TOKEN);
                    break;
            }

            if (!isAuth && cookies != null) {
                user = weixinService.getWeixinUserByRefreshToken(cookies, entranceEnum);
                if (user != null) {
                    if (user.getId().equals(form.getWeixinUserId())) {
                        isAuth = true;
                    }
                }
            }

            if(!isAuth) {
                response.setErrcode(ResponseEnum.AlERT.getCode());
                response.setErrmsg("微信授权校验失败!");

                logger.info(response.getErrmsg());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("微信授权校验失败:")
                       .append(JSON.toJSONString(form));
                return response;
            }
        }

        //重复提交
        if (accessLog.getAnswerSheetId() != null && accessLog.getAnswerSheetId() > 0) {
            response.setErrmsg("您已经提交过该问卷了，无需要重复提交！");  //已经提交过的，直接跳转到提交页面
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("手机号:[")
                    .append(form.getPhoneNum())
                    .append("] IP:[")
                    .append(ip)
                    .append("] 填写用时: ")
                    .append(t / 1000).append("秒 已经提交的实例: ").append(JSONObject.toJSONString(accessLog));
            logger.info(response.getErrmsg());
            logger.info(stringBuilder.toString());
                    return response;
        }

        //反馈总数限制
        int maxNum = company.getMaxReportNum();
        if (maxNum > 0) {
            int totalSize = lhAnswerSheetService.getAnswerSheetTotalCount(questionnaireId);
            if (totalSize >= maxNum) {
                response.setErrcode(ResponseEnum.AlERT.getCode());
                response.goPath(RouterGoTypeEnum.REPLACE, getErrorPagePath("该问卷已过超过最大样本数量: " + maxNum + "。"));
                logger.info(response.getErrmsg());
                return response;
            }
        }

        //时间段问卷数限制
        UpAppsQuestionnaireAttribute timesLimit = attributeMap.get(QuestionnaireAttrEnum.LIMIT_EACH_TIMES_ANSWER_SHEET.getName());
        if (timesLimit != null) {
            Long totalNum = timesLimit.getAttributeLongValue();
            String errMsg;
            if (totalNum > 0) {
                String type = timesLimit.getAttributeStringValue();
                int size = 0;
                switch (type) {
                    case "hour":
                        size = lhAnswerSheetService.getAnswerSheetLastHourCount(questionnaireId);
                        errMsg = "已超过问卷数量，请稍后重试。";
                        break;
                    case "day":
                        size = lhAnswerSheetService.getAnswerSheetCurrentDayCount(questionnaireId);
                        errMsg = "今天已超过问卷数量，请明天再来。";
                        break;
                    default:
                        throw new AlertException("问卷属性配置错误");
                }
                if (size >= totalNum) {
                    response.goPath(RouterGoTypeEnum.REPLACE, getErrorPagePath(errMsg));
                    logger.info(response.getErrmsg());
                    return response;
                }
            }
        }

        //限定结束时间
        UpAppsQuestionnaireAttribute limitEndTime = attributeMap.get(QuestionnaireAttrEnum.LIMIT_END_TIME.getName());
        if (limitEndTime != null && limitEndTime.getAttributeLongValue() > 0 && limitEndTime.getAttributeTimestampValue() != null) {
            Long now = new Date().getTime();
            Long endTime = limitEndTime.getAttributeTimestampValue().getTime();
            if (now > endTime) {
                response.setErrcode(ResponseEnum.AlERT.getCode());
                response.setErrmsg("该问卷已结束");
                logger.info(response.getErrmsg());
                return response;
            }
        }

        //黑白名单
        UpAppsQuestionnaireAttribute phoneLimit = attributeMap.get(QuestionnaireAttrEnum.LIMIT_PHONE_NUM.getName());
        if (phoneLimit != null && phoneLimit.getAttributeLongValue() > 0) {
            PhoneListTypeEnum limitType = PhoneListTypeEnum.valueOf(phoneLimit.getAttributeStringValue());
            switch (limitType) {
                case whiteList:
                    UpAppsPhoneList white = upAppsPhoneListService.selectOne(
                            new EntityWrapper<UpAppsPhoneList>()
                                    .eq(UpAppsPhoneList.QUESTIONNAIRE_ID, questionnaireId)
                                    .eq(UpAppsPhoneList.PHONE_NUM, phoneNum)
                                    .eq(UpAppsPhoneList.TYPE, limitType.getType())
                    );
                    if (white == null) {
                        throw new AlertException("非常抱歉，您不是问卷的目标回答者！");
                    }
                    break;
                case blackList:
                    UpAppsPhoneList black = upAppsPhoneListService.selectOne(
                            new EntityWrapper<UpAppsPhoneList>()
                                    .eq(UpAppsPhoneList.QUESTIONNAIRE_ID, questionnaireId)
                                    .eq(UpAppsPhoneList.PHONE_NUM, phoneNum)
                                    .eq(UpAppsPhoneList.TYPE, limitType.getType())
                    );
                    if (black != null) {
                        throw new AlertException("非常抱歉，您不是问卷的目标回答者！");
                    }
                    break;
                default:
                    throw new AlertException("系统错误：黑白名单设置错误");
            }
        }

        //检查是否超时
        if (EntranceEnum.valueOf(form.getEntrance()).equals(EntranceEnum.WEIXIN)) {
            Long dayLimit = questionnaireService.getLongValue(questionnaireId, QuestionnaireAttrEnum.WEIXIN_TIME_LIMIT.getName());
            if (dayLimit > 0) {
                Date now = new Date();
                //这部分逻辑需要重新处理
                Date lastTime = new Date(accessLog.getUpdateTime().getTime() + questionnaire.getDayLimit() * Constants.ONE_DAY);
                if (ComparatorUtil.isTime1AfterTime2(now, lastTime)) {
                    response.setErrcode(ResponseEnum.AlERT.getCode());
                    response.setErrmsg("该问卷已超时");
                    logger.info(response.getErrmsg());
                    return response;
                }
            }
        }

        //检查答题时间
        if (t < 10 * 1000) {
            accessLog.setUri("postQuestionnaire");
            accessLog.setLog("答题时间不足20秒");
            tableStoreService.updateRow(accessLog);

            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("请认真回答问题！！！");

            logger.info(response.getErrmsg() );
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("手机号:[")
                    .append(form.getPhoneNum())
                    .append("] IP:[")
                    .append(ip)
                    .append("] 填写用时: ")
                    .append(t / 1000).append("秒");
            logger.info(stringBuilder.toString());

            return response;
        }

        //电话号码去重
        if (!ValueHelper.isNone(phoneNum)) {
            if (!StringHelper.isPhoneLegal(phoneNum)) {
                response.setErrcode(ResponseEnum.AlERT.getCode());
                    response.setErrmsg("请填写正确的手机号码");
                logger.info(response.getErrmsg());

                accessLog.setUri("postQuestionnaire");
                accessLog.setLog("手机号码不正确:" + phoneNum);
                tableStoreService.updateRow(accessLog);
                return response;
            } else {
                TsAnswerSheetPhone tsAnswerSheetPhone = new TsAnswerSheetPhone();
                String questionnaireIdPhone = questionnaireId + phoneNum;
                tsAnswerSheetPhone.setQuestionnaireIdPhone(questionnaireIdPhone);
                tsAnswerSheetPhone.setRegionId(regionId);
                boolean checkPhoneNum = tableStoreService.getRow(tsAnswerSheetPhone);

                if (checkPhoneNum) {
                    accessLog.setUri("postQuestionnaire");
                    accessLog.setLog("该手机号码已经提交过了:" + phoneNum);
                    tableStoreService.updateRow(accessLog);

                    response.setErrcode(ResponseEnum.AlERT.getCode());
                    response.setErrmsg("该手机号码已经提交过了，请不要重复提交!");
                    logger.info(response.getErrmsg());
                    return response;
                }
            }
        }

        UpTenantsRegion region = upTenantsRegionService.selectById(accessLog.getRegionId());
        if (region == null) {
            accessLog.setUri("postQuestionnaire");
            accessLog.setLog("网点信息错误:" + accessLog.getRegionId());
            tableStoreService.updateRow(accessLog);

            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("网点信息错误!");
            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("无效网点ID: ").append(form.getRegionId());
            return response;
        }

        //表格存储
        TsAnswerSheet tsAnswerSheet = new TsAnswerSheet();
        tsAnswerSheet.setAnswerSheetId(SnowFlake.nextId(null));
        tsAnswerSheet.setOpenid(accessLog.getOpenid());
        tsAnswerSheet.setCookies(request.getAttribute(Constants.COOKIES_QUESTIONNIRE).toString()); //注意，这里cookies不要跟上面的cookies搞混了，这个是问卷对应的cookies
        tsAnswerSheet.setTenantsRegionId(regionId);
        tsAnswerSheet.setQuickTag(region.getQuickTag());
        tsAnswerSheet.setQuestionnaireId(questionnaireId);
        tsAnswerSheet.setAddTime(new Date());
        tsAnswerSheet.setStartTime(accessLog.getUpdateTime());
        tsAnswerSheet.setBroswer(accessLog.getBrowser());
        tsAnswerSheet.setSystem(accessLog.getSystem());

        tsAnswerSheet.setFinished(true);
        tsAnswerSheet.setIp(ip);
        tsAnswerSheet.setFinishTime(new Date());
        tsAnswerSheet.setUpdateTime(new Date());

        //日志服务
        AnswerSheetRecordLog answerSheetRecordLog = new AnswerSheetRecordLog();
        answerSheetRecordLog.setOpenid(accessLog.getOpenid());
        answerSheetRecordLog.setCookies(request.getAttribute(Constants.COOKIES_QUESTIONNIRE).toString());
        answerSheetRecordLog.setRegionId(regionId);
        answerSheetRecordLog.setQuickTag(region.getQuickTag());
        answerSheetRecordLog.setQuestionnaireId(questionnaireId);
        answerSheetRecordLog.setAddTime(new Date());
        Double answerTimes = (new Date().getTime() - accessLog.getUpdateTime().getTime()) / 1000D;
        answerSheetRecordLog.setAnswerTime(answerTimes);
        answerSheetRecordLog.setBroswer(accessLog.getBrowser());
        answerSheetRecordLog.setSystem(accessLog.getSystem());
        answerSheetRecordLog.setIp(ip);


        //保存问卷
        String jsonStr = RequestUtil.getRequestPostStr(request);
        JSONObject json = JSONObject.parseObject(jsonStr);
        JSONArray questionList = json.getJSONArray("questionList");
        int finishQuestionNum = 0;
        int finishRequiredQuestionNum = 0;
        List<UpAppsAnswerSheetItem> answerSheetItemList = new ArrayList<>();
        for (Object object : questionList) {
            JSONObject question = (JSONObject)object;
            Boolean checked = question.getBoolean("checked");
            Integer visible = question.getIntValue("isVisible");

            checked = checked && visible > 0;
            if (checked) {
                finishQuestionNum++;
                if (question.getIntValue("isRequired") == 1) {
                    finishRequiredQuestionNum++;
                }

                String questionType = question.getString("questionType");
                QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.valueOf(questionType);

                for (Object object1 : question.getJSONArray("itemList")) {
                    JSONObject item = (JSONObject) object1;
                    if (item.getBoolean("checked")) {
                        UpAppsAnswerSheetItem answerSheetItem = new UpAppsAnswerSheetItem();
                        answerSheetItem.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId())
                                .setQuestionId(question.getLong("id"))
                                .setQuestionType(questionType)
                                .setAddTime(new Date())

                                .setUpdateTime(new Date());

                        answerSheetItem.setQuestionItemId(item.getLong("id"));
                        String editor = item.getString("editorValue");
                        if (editor != null) {
                            answerSheetItem.setInputContent(editor);
                        }
                        switch (questionTypeEnum) {
                            case 总体满意度:
                            case 分项满意度:
                            case NPS:
                            case 评分:
                                int val = ValueHelper.tryParse(item.get("value"), -1);

                                if (val == -1) {
                                    val = ValueHelper.tryParse(item.get("key"), 0);
                                }
                                answerSheetItem.setScore(val);
                                answerSheetItem.setValue(item.getString("value"));
                                break;
                            case 填空:
                                // TODO: 2018/2/8 验证 
                            case 手机验证:
                            case 个人信息:
                            case 服务方式:
                                answerSheetItem.setInputContent(item.getString("value"));
                                break;
                            case 多选:
                            case 单选:
                            case 是非:
                                answerSheetItem.setValue(item.getString("value"));
                                break;
                            default:
                                break;
                        }

                        answerSheetItemList.add(answerSheetItem);
                    }
                }
            }
        }

        //表格存储
        tsAnswerSheet.setItemList(answerSheetItemList);
        tsAnswerSheet.setFinishQuestionNum(finishQuestionNum);
        tsAnswerSheet.setFinishRequiredQuestionNum(finishRequiredQuestionNum);

        //日志服务
        answerSheetRecordLog.setAnswerItems(JSONObject.toJSONString(answerSheetItemList));
        answerSheetRecordLog.setFinishQuestionNum(finishQuestionNum);
        logHubService.putLog(answerSheetRecordLog);
        logHubService.putAnswerItemsLog(answerSheetItemList);

        boolean updateResult = tableStoreService.putRow(tsAnswerSheet);
        if (!updateResult) {
            logger.error(tsAnswerSheet.toString());
            throw new AlertException("系统异常");
        }

        //保存cookie
        if (tsAnswerSheet.getCookies() != null) {
            TsAnswerSheetCookies tsAnswerSheetCookies = new TsAnswerSheetCookies();
            tsAnswerSheetCookies.setQuestionnaireIdCookies(questionnaireId + tsAnswerSheet.getCookies());
            tsAnswerSheetCookies.setRegionId(regionId);
            tsAnswerSheetCookies.setAccessLogId(accessLog.getAccessLogId());
            tsAnswerSheetCookies.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
            if (!tableStoreService.updateRow(tsAnswerSheetCookies)) {
                tableStoreService.putRow(tsAnswerSheetCookies);
            }
        }

        //保存openid
        if (tsAnswerSheet.getOpenid() != null) {
            TsAnswerSheetOpenid tsAnswerSheetOpenid = new TsAnswerSheetOpenid();
            tsAnswerSheetOpenid.setQuestionnaireIdOpenid(questionnaireId.toString() + tsAnswerSheet.getOpenid());
            tsAnswerSheetOpenid.setRegionId(regionId);
            tsAnswerSheetOpenid.setAccessLogId(accessLog.getAccessLogId());
            tsAnswerSheetOpenid.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
            if (!tableStoreService.updateRow(tsAnswerSheetOpenid)) {
                tableStoreService.putRow(tsAnswerSheetOpenid);
            }
        }

        //保存电话号码
        if (phoneNum != null) {
            TsAnswerSheetPhone tsAnswerSheetPhone = new TsAnswerSheetPhone();
            tsAnswerSheetPhone.setRegionId(regionId);
            tsAnswerSheetPhone.setQuestionnaireIdPhone(questionnaireId + phoneNum);
            tsAnswerSheetPhone.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
            if (!tableStoreService.updateRow(tsAnswerSheetPhone)) {
                tableStoreService.putRow(tsAnswerSheetPhone);
            }
        }

        Map<String, Object> resData = new HashMap<>();
        resData.put("questionnaire", jsonStr);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("提交成功了:")
                .append("手机号:[")
                .append(form.getPhoneNum())
                .append("] IP:[")
                .append(ip)
                .append("] 填写用时: ")
                .append(t / 1000).append("秒");

        logger.info(stringBuilder.toString());

        accessLog.setUri("postQuestionnaire");
        accessLog.setLog(stringBuilder.toString());
        accessLog.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
        tableStoreService.updateRow(accessLog);

        //插入索引列
        TsAnswerSheetIndex index = new TsAnswerSheetIndex();
        index.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
        index.setQuestionnaireId(tsAnswerSheet.getQuestionnaireId());
        tableStoreService.putRow(index);

        response.setData(resData);
        return response;
    }

    private String getErrorPagePath(String msg) throws UnsupportedEncodingException {
        return "/errPage/" + URLEncoder.encode(msg, "utf8");
    }

    @PostMapping("/checkQuestionnaire")
    public BaseResponse checkQuestionnaire(CheckForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        Long regionId = form.getRegionId();
        Long questionnaireId = form.getQuestionnaireId();
        String entrance = form.getEntrance();
        String cookies = request.getAttribute(Constants.COOKIES_QUESTIONNIRE).toString();
        String ip = RequestUtil.getIpAddr(request);

        if (form.getQuestionnaireId() == null ||
                form.getQuestionnaireId() < 1 ||
                form.getRegionId() == null ||
                form.getRegionId() < 1
                ) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("问卷信息不能为空");
            return response;
        }

        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        if (questionnaire == null) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("答卷异常，请退出后重试!");
            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("无效答卷ID: ").append(form.getQuestionnaireId());
            return response;
        }
        UpAppsQuestionnaireTheme theme = upAppsQuestionnaireThemeService.selectById(questionnaire.getThemeId());

        Map<String, Object> result = new HashMap<>();
        result.put("title", questionnaire.getTitle());
        result.put("theme", theme);

        if (!ValueHelper.isNone(request.getParameter("preview_mode"))) { //只要带了这个参数就算是预览模式
            result.put("answerSheetId", 0);
            result.put("allowAccess", true);
            response.setData(result);
            return response;
        }

        BaseResponse r = commonQuestionnaireCheck(questionnaire);
        if (r != null) {
            return r;
        }

        //检查问卷的配置:
        String code = request.getParameter("code");

        EntranceEnum entranceEnum = null;
        try {
            entranceEnum = EntranceEnum.valueOf(entrance);
        } catch (Exception e) {
            //do nothing
        }

        if (entranceEnum == null) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("非法请求来源");
            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("无效答卷来源: ").append(form.getEntrance());
            return response;
        }

        UpWeixinUser weixinUser = null;

        switch(entranceEnum) {
            case LINK:
            case WEIXIN:
                break;
            case WEIXIN_BASE_OAUTH:
            case WEIXIN_INFO_OAUTH:
                weixinUser = weixinService.getWeixinUserByCode(code,
                        CookieUtil.getCookie(request, Constants.COOKIES_YOUPLUS_ACCESS_TOKEN),
                        entranceEnum);
                if (weixinUser != null) { //写入cookies
                    CookieUtil.setCookie(this.response,
                            Constants.COOKIES_YOUPLUS_ACCESS_TOKEN,
                            URLEncoder.encode(AESUtil.AESEncodeForWeb(weixinUser.getYouplusAccessToken()), "utf-8"),
                            Constants.COOKIE_AGE);
                }
                break;
            case LINK_OAUTH:
                weixinUser = weixinService.getWeixinUserByCode(code,
                        CookieUtil.getCookie(request, Constants.COOKIES_WEBAPP_ACCESS_TOKEN),
                        entranceEnum);
                if (weixinUser != null) { //写入cookies
                    CookieUtil.setCookie(this.response,
                            Constants.COOKIES_WEBAPP_ACCESS_TOKEN,
                            URLEncoder.encode(AESUtil.AESEncodeForWeb(weixinUser.getWebappAccessToken()), "utf-8"),
                            Constants.COOKIE_AGE);
                }
                break;
        }

        Map<String, UpAppsQuestionnaireAttribute> attributeMap = questionnaireSettingService.getQuestionnaireAttrMap(questionnaireId);
        boolean isNeedAuth = false;
        if (attributeMap.get(QuestionnaireAttrEnum.USER_LOGIN.getName()) != null) {
            isNeedAuth = attributeMap.get(QuestionnaireAttrEnum.USER_LOGIN.getName()).getAttributeLongValue() == 1L;
        }
        boolean isNeedCheckDevice = false;
        if (attributeMap.get(QuestionnaireAttrEnum.DEVICE_CHECK.getName()) != null) {
            isNeedCheckDevice = attributeMap.get(QuestionnaireAttrEnum.DEVICE_CHECK.getName()).getAttributeLongValue() == 1L;
        }
        boolean ipAreaCheck = false;
        if (attributeMap.get(QuestionnaireAttrEnum.IP_AREA_CHECK.getName()) != null) {
            ipAreaCheck = attributeMap.get(QuestionnaireAttrEnum.IP_AREA_CHECK.getName()).getAttributeLongValue() == 1L;
        }

        if (ipAreaCheck) {
            Location location = locationBaseService.getLocationByIp(ip);
            UpTenantsRegion region = upTenantsRegionService.selectById(regionId);
            if (region == null) {
                throw new AlertException("找不到ID为" + regionId + "的region");
            }
            String province = region.getProvince();
            if (!ValueHelper.isNone(province)) {
                if (!location.getProvince().equals(province)) {
                    response.setErrmsg("该问卷所属地区与当前地区不符(当前：" + location.getProvince() + "，问卷所属地区：" + province + ")");
                    response.setErrcode(ResponseEnum.AlERT.getCode());
                    logger.info(response.getErrmsg());
                    return response;
                }
            }
        }

        if (weixinUser != null) {
            result.put("weixinUserId", weixinUser.getId());
        } else {
            if (isNeedAuth) { //如果需要授权，但是获取微信信息失败！
                //path
                response.goPath(RouterGoTypeEnum.REPLACE,getErrorPagePath("微信授权失败"));
                logger.info(response.getErrmsg());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("微信授权失败:")
                        .append("微信授权失败: ").append(form.getCode())
                        .append("->").append(JSONObject.toJSONString(form));
                return response;
            }
        }

        //答卷只有在真正提交的时候生成
        boolean allowAccess = true;
        boolean hasCookiesRecord = false;
        boolean hasOpenidRecord = false;
        long accessLogId = 0;

        TsAnswerSheetOpenid tsAnswerSheetOpenid = new TsAnswerSheetOpenid();
        if (isNeedAuth) {
            String questionnaireIdOpenid = questionnaireId.toString() + weixinUser.getUnionid();
            tsAnswerSheetOpenid.setQuestionnaireIdOpenid(questionnaireIdOpenid);
            tsAnswerSheetOpenid.setRegionId(regionId);
            hasOpenidRecord = tableStoreService.getRow(tsAnswerSheetOpenid);
            if (hasOpenidRecord) { //存在记录了
                if (tsAnswerSheetOpenid.getAnswerSheetId() != null &&
                        tsAnswerSheetOpenid.getAnswerSheetId() > 0) {
                    allowAccess = false;
                }

                if (tsAnswerSheetOpenid.getAccessLogId() != null &&
                        tsAnswerSheetOpenid.getAccessLogId() > 0) {
                    accessLogId = tsAnswerSheetOpenid.getAccessLogId();
                }
            }
        }

        TsAnswerSheetCookies tsAnswerSheetCookies = new TsAnswerSheetCookies();
        String questionnaireIdCookies = questionnaireId + cookies;
        tsAnswerSheetCookies.setQuestionnaireIdCookies(questionnaireIdCookies);
        tsAnswerSheetCookies.setRegionId(regionId);
        hasCookiesRecord = tableStoreService.getRow(tsAnswerSheetCookies);
        if (hasCookiesRecord) {
            if (isNeedCheckDevice &&  //需要检查设备
                    tsAnswerSheetCookies.getAnswerSheetId() != null &&
                    tsAnswerSheetCookies.getAnswerSheetId() > 0) {
                allowAccess = false;
            }

            if (accessLogId == 0 &&
                    tsAnswerSheetCookies.getAccessLogId() != null &&
                    tsAnswerSheetCookies.getAccessLogId() > 0) {
                accessLogId = tsAnswerSheetCookies.getAccessLogId();
            }
        }

        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        OperatingSystem operatingSystem = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getOperatingSystem();
        logger.debug("浏览器信息:" + JSONObject.toJSONString(browser));
        if (accessLogId == 0) {
            accessLogId = SnowFlake.nextId(null);
            TsAnswerSheetAccessLog log = new TsAnswerSheetAccessLog();
            log.setAccessLogId(accessLogId);
            log.setQuestionnaireId(questionnaireId);
            log.setRegionId(regionId);
            log.setAnswerSheetId(null);
            log.setBrowser(browser == null ? "其他" : browser.getName());
            log.setSystem(operatingSystem == null ? "其他" : operatingSystem.getName());
            log.setIp(RequestUtil.getIpAddr(request));
            log.setCookies(cookies);
            log.setEntrance(entrance);
            log.setOpenid(weixinUser == null ? null : weixinUser.getUnionid());
            log.setUri("checkQuestionnaire");
            tableStoreService.putRow(log);
        } else {
            TsAnswerSheetAccessLog log = new TsAnswerSheetAccessLog();
            log.setAccessLogId(accessLogId);
            log.setRegionId(regionId);
            log.setQuestionnaireId(questionnaireId);
            log.setAnswerSheetId(null);
            log.setBrowser(browser == null ? null : browser.getName());
            log.setSystem(operatingSystem == null ? null : operatingSystem.getName());
            log.setIp(RequestUtil.getIpAddr(request));
            log.setCookies(cookies);
            log.setEntrance(entrance);
            log.setOpenid(weixinUser == null ? null : weixinUser.getUnionid());
            log.setUri("checkQuestionnaire");
            tableStoreService.updateRow(log);
        }
        //日志服务-request-log
        AnswerSheetRequestLog answerSheetRequestLog = new AnswerSheetRequestLog();
        answerSheetRequestLog.setIp(ip);
        answerSheetRequestLog.setRegionId(regionId);
        answerSheetRequestLog.setQuestionnaireId(questionnaireId);
        answerSheetRequestLog.setBroswer(browser == null ? null : browser.getName());
        answerSheetRequestLog.setSystem(operatingSystem == null ? null : operatingSystem.getName());
        answerSheetRequestLog.setCookies(cookies);
        UpTenantsRegion region = upTenantsRegionService.selectById(regionId);
        answerSheetRequestLog.setQuickTag(region.getQuickTag());
        logHubService.putLog(answerSheetRequestLog);

        if (isNeedAuth && !hasOpenidRecord) {
            tsAnswerSheetOpenid.setAccessLogId(accessLogId);
            tableStoreService.putRow(tsAnswerSheetOpenid);
        }
        if (!hasCookiesRecord) {
            tsAnswerSheetCookies.setAccessLogId(accessLogId);
            tableStoreService.putRow(tsAnswerSheetCookies);
        }

        result.put("answerSheetId", accessLogId);
        result.put("allowAccess", allowAccess);
        response.setData(result);
        return response;
    }

    @PostMapping("/oil/checkQuestionnaire")
    public BaseResponse oilCheckQuestionnaire(CheckForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        Long regionId = form.getRegionId();
        Long companyId = form.getCompanyId();
        Long questionnaireId = form.getQuestionnaireId();
        String cookies = request.getAttribute(Constants.COOKIES_QUESTIONNIRE).toString();

        if (form.getQuestionnaireId() == null ||
                form.getQuestionnaireId() < 1 ||
                form.getRegionId() == null ||
                form.getRegionId() < 1
                ) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("问卷信息不能为空");
            return response;
        }

        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        if (questionnaire == null) {
            response.setErrcode(ResponseEnum.AlERT.getCode());
            response.setErrmsg("答卷异常，请退出后重试!");
            logger.info(response.getErrmsg());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("提交失败:")
                    .append("无效答卷ID: ").append(form.getQuestionnaireId());
            return response;
        }

        UpAppsQuestionnaireTheme theme = upAppsQuestionnaireThemeService.selectById(questionnaire.getThemeId());

        UpAppsAnswerSheet upAppsAnswerSheet = upAppsAnswerSheetService.selectOne(
                new EntityWrapper<UpAppsAnswerSheet>()
                        .eq(UpAppsAnswerSheet.COOKIES, cookies)
                        .eq(UpAppsAnswerSheet.TENANTS_REGION_ID, regionId)
                        .eq(UpAppsAnswerSheet.QUESTIONNAIRE_ID, questionnaireId)
        );
        Map<String, Object> result = new HashMap<>();

        boolean allowAccess = true;
        if (upAppsAnswerSheet != null) {
            allowAccess = upAppsAnswerSheet.getIsFinished() == 0;
            result.put("answerSheetId", upAppsAnswerSheet.getId());
        } else {
            upAppsAnswerSheet = new UpAppsAnswerSheet();
            upAppsAnswerSheet.setCookies(cookies)
                    .setTenantsRegionId(regionId)
                    .setTenantsCompanyId(companyId)
                    .setQuestionnaireId(questionnaireId)
                    .setAddTime(new Date())
                    .setStartTime(new Date())
                    .setUpdateTime(new Date());
            upAppsAnswerSheet = upAppsAnswerSheetService.mInsert(upAppsAnswerSheet);
            result.put("answerSheetId", upAppsAnswerSheet.getId());
        }
        result.put("allowAccess", allowAccess);
        result.put("title", questionnaire.getTitle());
        result.put("theme", theme);
        response.setData(result);

        return response;
    }

    @PostMapping("/getCode")
    public BaseResponse getCode(SmsCodeForm form) throws Exception {
        BaseResponse <String> response = new BaseResponse();
        String phoneNum = form.getPhoneNum();
        Long questionnaireId = form.getQuestionnaireId();
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        Long companyId = questionnaire.getTenantsCompanyId();

        EntranceEnum entranceEnum = null;
        try {
            entranceEnum = EntranceEnum.valueOf(form.getEntrance());
        } catch (Exception e) {
            //nothing
        }
        boolean isAuth = false;
        String authCookies = null;
        String cookies = request.getAttribute(Constants.COOKIES_QUESTIONNIRE).toString();
        String unionId = null;
        if (entranceEnum != null) {
            switch(entranceEnum) {
                case LINK:
                case WEIXIN:
                    isAuth = true;
                    break;
                case LINK_OAUTH:
                    authCookies = CookieUtil.getCookie(request,Constants.COOKIES_WEBAPP_ACCESS_TOKEN);
                    break;
                case WEIXIN_INFO_OAUTH:
                case WEIXIN_BASE_OAUTH:
                    authCookies = CookieUtil.getCookie(request,Constants.COOKIES_YOUPLUS_ACCESS_TOKEN);
                    break;
                case WEIXIN_COMPHONENT_BASE_OAUTH:
                case WEIXIN_COMPHONENT_INFO_OAUTH:
                    authCookies = CookieUtil.getCookie(request,Constants.COOKIES_TIHRDPARTY_ACCESS_TOKEN);
                    break;
            }

            if (!isAuth && authCookies != null) {
                UpWeixinUser weixinUser = weixinService.getWeixinUserByRefreshToken(authCookies, entranceEnum);

                if (weixinUser != null) {
                    unionId = weixinUser.getUnionid();
                    isAuth = true;
                }

            }

            if(!isAuth) {
                response.setErrcode(ResponseEnum.AlERT.getCode());
                response.setErrmsg("微信授权校验失败!");

                logger.info(response.getErrmsg());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("微信授权校验失败:")
                        .append(JSON.toJSONString(form));
                return response;
            }
        }

        if (!smsService.checkVerifyCodeOneMinute(phoneNum)) {
            response.setErrmsg("一分钟之内不能重复发送");
            response.setErrcode(ResponseEnum.AlERT.getCode());
            return response;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        String day = String.valueOf(calendar.getTime().getTime());
        //同一openid一天5条
        if (unionId != null) {
            int openidSize = upNotificationSmsRecordService.selectCount(
                    new EntityWrapper<UpNotificationSmsRecord>()
                            .eq(UpNotificationSmsRecord.DAY, day)
                            .eq(UpNotificationSmsRecord.OPENID, unionId)
                            .eq(UpNotificationSmsRecord.COMPANY_ID, companyId)
                            .eq(UpNotificationSmsRecord.QUESTIONNAIRE_ID, questionnaireId)
            );
            if (openidSize >= 5) {
                response.setErrmsg("同一微信每天只能发送5条短信");
                response.setErrcode(ResponseEnum.AlERT.getCode());
                return response;
            }
        }

        //同一cookie一天5条
        if (cookies != null) {
            int cookieSize = upNotificationSmsRecordService.selectCount(
                    new EntityWrapper<UpNotificationSmsRecord>()
                            .eq(UpNotificationSmsRecord.DAY, day)
                            .eq(UpNotificationSmsRecord.COOKIE, cookies)
                            .eq(UpNotificationSmsRecord.COMPANY_ID, companyId)
                            .eq(UpNotificationSmsRecord.QUESTIONNAIRE_ID, questionnaireId)
            );
            if (cookieSize >= 5) {
                response.setErrmsg("同一设备每天只能发送5条短信");
                response.setErrcode(ResponseEnum.AlERT.getCode());
                return response;
            }
        }

        //同一ip一天100条
        String ip = RequestUtil.getIpAddr(request);
        if (ip != null) {
            int ipSize = upNotificationSmsRecordService.selectCount(
                    new EntityWrapper<UpNotificationSmsRecord>()
                            .eq(UpNotificationSmsRecord.DAY, day)
                            .eq(UpNotificationSmsRecord.IP, ip)
                            .eq(UpNotificationSmsRecord.COMPANY_ID, companyId)
                            .eq(UpNotificationSmsRecord.QUESTIONNAIRE_ID, questionnaireId)
            );
            if (ipSize >= 100) {
                response.setErrmsg("同一ip每天只能发送100条短信");
                response.setErrcode(ResponseEnum.AlERT.getCode());
                return response;
            }
        }

        //根据公司配置
        UpAppsQuestionnaireAttribute smsLimit = upAppsQuestionnaireAttributeService.selectOne(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.ATTRIBUTE_NAME, QuestionnaireAttrEnum.LIMIT_SMS_NUM.getName())
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaireId)
        );
        if (smsLimit != null) {
            Long totalNum = smsLimit.getAttributeLongValue();
            if (totalNum > 0) {
                String type = smsLimit.getAttributeStringValue();
                int smsSize = 0;
                switch (type) {
                    case "all":
                        smsSize = upNotificationSmsRecordService.selectCount(
                                new EntityWrapper<UpNotificationSmsRecord>()
                                        .eq(UpNotificationSmsRecord.COMPANY_ID, companyId)
                                        .eq(UpNotificationSmsRecord.QUESTIONNAIRE_ID, questionnaireId)
                        );
                        break;
                    case "day":
                        smsSize = upNotificationSmsRecordService.selectCount(
                                new EntityWrapper<UpNotificationSmsRecord>()
                                        .eq(UpNotificationSmsRecord.DAY, day)
                                        .eq(UpNotificationSmsRecord.COMPANY_ID, companyId)
                                        .eq(UpNotificationSmsRecord.QUESTIONNAIRE_ID, questionnaireId)
                        );
                        break;
                    default:
                        throw new AlertException("问卷属性配置错误");
                }
                if (smsSize >= totalNum) {
                    response.setErrmsg("已超过短信验证码发送数目");
                    response.setErrcode(ResponseEnum.AlERT.getCode());
                    logger.info(response.getErrmsg());
                    return response;
                }
            }
        }

        // TODO: 2018/2/7/007 短信模板id
        SmsParams smsParams = new SmsParams()
                .setPhone(form.getPhoneNum())
                .setTemplateId(SmsService.问卷验证码)
                .setType(SmsTypeEnum.VERIFY_CODE)
                .setUserType(UserTypeEnum.APPS)
//                .setUserId(0L)
                .setCookie(cookies)
                .setOpenid(unionId)
                .setIp(ip)
                .setCompanyId(companyId)
                .setQuestionnaireId(questionnaireId)
                .setDay(day);

        smsParams.appendData("code", StringHelper.getRandomNumber(SmsService.VERFY_CODE_LENGTH));

        String result = smsService.sendSms(smsParams);

        if ("OK".equals(result)) {
            result = "发送成功！";
        }

        response.setData(result);
        //messageQueueService.sendSms(smsParams, null);
        return response;
    }
}
