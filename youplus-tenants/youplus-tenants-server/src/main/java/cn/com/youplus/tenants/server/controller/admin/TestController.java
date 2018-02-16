package cn.com.youplus.tenants.server.controller.admin;

import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetItemService;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.apps.api.common.AnswerSheetService;
import cn.com.youplus.base.api.LogHubService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.EmailParams;
import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.common.model.enums.SmsTypeEnum;
import cn.com.youplus.common.model.enums.UserTypeEnum;
import cn.com.youplus.common.model.log.ApiAccessLog;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.RequestUtil;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.notification.api.SmsService;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.common.ProductConsumeService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import cn.com.youplus.tenants.api.common.TsAnswerSheetStatService;
import cn.com.youplus.tenants.common.form.WeixinOauthForm;
import cn.com.youplus.tenants.server.controller.SuperController;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/test")
public class TestController extends SuperController {

    private static Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Autowired
    private UpAppsAnswerSheetService upAppsAnswerSheetService;

    @Autowired
    private UpAppsAnswerSheetItemService upAppsAnswerSheetItemService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private AnswerSheetService answerSheetService;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private TsAnswerSheetStatService tsAnswerSheetStatService;

    @Autowired
    private ProductConsumeService productConsumeService;

    @Autowired
    private LogHubService logHubService;


    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    @PostMapping("/test/sms/{phone}/{code}")
    public BaseResponse testsms(@PathVariable String phone, @PathVariable String code) throws Exception {
        BaseResponse response = new BaseResponse();

        SmsParams smsParams = new SmsParams()
                .setPhone(phone)
                .setTemplateId(SmsService.身份验证验证码)
                .setType(SmsTypeEnum.VERIFY_CODE)
                .setUserId(0L)
                .setUserType(UserTypeEnum.APPS);

        smsParams.appendData("code", StringHelper.getRandomNumber(SmsService.VERFY_CODE_LENGTH));
        messageQueueService.sendSms(smsParams, null);

        response.setData("这是成功测试");
        return response;
    }

    @PostMapping("/testEmail")
    public BaseResponse accountGetCsode(@Valid BaseForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        EmailParams emailParams = new EmailParams();
        emailParams.setContent("<h1>test message queue</h1>");
        List<String> receiverList = new ArrayList<>();
        receiverList.add("hewh@youplus.com.cn");
        emailParams.setReceiverList(receiverList);
        emailParams.setTitle("message");

        messageQueueService.sendEmail(emailParams, null);

        return response;
    }

    @GetMapping("/oauth")
    public void WeixinOauth(WeixinOauthForm form) throws Exception {
        String scope = form.getScope();
        String router = form.getRouter();
        if (ValueHelper.isNone(scope)) {
            scope = "base";
        }

        UpWeixinAccount weixinAccount = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.TENANTS_COMPANY_ID, form.getCompanyId())
                        .eq(UpWeixinAccount.IS_AUTH, 1)
        );

        if (RequestUtil.isWeixinBrowser(request)) {
            String url;
            String baseUrl = systemConfig.getAppsApiUrlWithVersion() + "/weixin/redirect?router=" + router;
            logger.info("baseUrl");
            logger.info(baseUrl);
            baseUrl = "http://thirdparty.shenmiren.com.cn/apps/v1/weixin/redirect?router=" + router;

            if (scope.equals("userInfo")) {
                url = String.format(WeixinConstants.WEIXIN_OAUTH_USERINFO_URL,
                        weixinAccount.getAuthorizerAppid(), URLEncoder.encode(baseUrl, "utf-8"), router, weixinProperties.getAppid()
                );
            } else {
                url = String.format(WeixinConstants.WEIXIN_OAUTH_BASE_URL,
                        weixinAccount.getAuthorizerAppid(), URLEncoder.encode(baseUrl, "utf-8"), router, weixinProperties.getAppid()
                );
            }

            response.sendRedirect(url);
        } else {
            throw new AlertException("请在微信浏览器上浏览");
        }

    }

    @PostMapping("/answerSheet/export/upload")
    public BaseResponse answerSheetExport() throws Exception {
        BaseResponse response = new BaseResponse();

        String result = answerSheetService.exportToDataBase();
        response.setData(result);
        return response;
    }

    @PostMapping("/transferData/upload")
    public BaseResponse transferData() throws Exception {
        BaseResponse response = new BaseResponse();

        answerSheetService.transferData();
        return response;
    }

    @PostMapping("/answerSheet/upload")
    public BaseResponse testAns() throws Exception {
        BaseResponse response = new BaseResponse();
        /*String result = answerSheetService.exportOldData();
        response.setData(result);*/
        return response;
    }

    @PostMapping("/tableStore/upload")
    public BaseResponse testTabeStore() throws Exception {
        BaseResponse response = new BaseResponse();
        tsAnswerSheetStatService.lastHourStatTask();
        return response;
    }

    @PostMapping("/batchQrcode/upload")
    public BaseResponse batchQrcode() throws Exception {
        UpTenantsCompany company = upTenantsCompanyService.selectById(926063648787984386L);
        String path = questionnaireSettingService.createBatchLinkAndQrcode(company, 938608061013319682L, 256);
        return new BaseResponse().setData(path);
    }

    @PostMapping("/apiLog/upload")
    public BaseResponse apiLog() throws Exception {
        ApiAccessLog apiAccessLog = new ApiAccessLog();
        apiAccessLog.setTimeConsume(10);
        apiAccessLog.setIp("setIp");
        apiAccessLog.setAddTime(new Date());
        apiAccessLog.setBroswer("setBroswer");
        apiAccessLog.setCookies("setCookies");
        apiAccessLog.setDevice("setDevice");
        apiAccessLog.setErrorMsg("setErrorMsg");
        apiAccessLog.setModuleName("setModuleName");
        apiAccessLog.setRequestParameter("setRequestParameter");
        apiAccessLog.setRequestUri("setRequestUri");
        apiAccessLog.setReturnCode(22);
        logHubService.putLog(apiAccessLog);
        return new BaseResponse();
    }

    @PostMapping("/logService/upload")
    public BaseResponse logService() throws Exception {
        productConsumeService.transferToLogService();
        return new BaseResponse();
    }


    @PostMapping("/requestLog/upload")
    public BaseResponse requestLog() throws Exception {
        productConsumeService.transferRequestLog();
        return new BaseResponse();
    }


//    HSSFCell cell = row1.createCell(0);
//        cell.setCellValue("访问日期");
//        cell.setCellStyle(style);
//    cell = row1.createCell(1);
//        cell.setCellValue("省公司");
//        cell.setCellStyle(style);
//    cell = row1.createCell(2);
//        cell.setCellValue("电话号码");
//        cell.setCellStyle(style);
//    cell = row1.createCell(3);
//        cell.setCellValue("是否相关行业工作");
//        cell.setCellStyle(style);
//    cell = row1.createCell(4);
//        cell.setCellValue("服务态度");
//        cell.setCellStyle(style);
//    cell = row1.createCell(5);
//        cell.setCellValue("服务效率");
//        cell.setCellStyle(style);
//    cell = row1.createCell(6);
//        cell.setCellValue("油品质量");
//        cell.setCellStyle(style);
//    cell = row1.createCell(7);
//        cell.setCellValue("环境卫生");
//        cell.setCellStyle(style);
//    cell = row1.createCell(8);
//        cell.setCellValue("促销优惠");
//        cell.setCellStyle(style);
//    cell = row1.createCell(9);
//        cell.setCellValue("现场秩序");
//        cell.setCellStyle(style);
//    cell = row1.createCell(10);
//        cell.setCellValue("油品标号");
//        cell.setCellStyle(style);
//    cell = row1.createCell(11);
//        cell.setCellValue("优惠活动多");
//        cell.setCellStyle(style);
//    cell = row1.createCell(12);
//        cell.setCellValue("自助加油方便");
//        cell.setCellStyle(style);
//    cell = row1.createCell(13);
//        cell.setCellValue("油品数量充足");
//        cell.setCellStyle(style);
//    cell = row1.createCell(14);
//        cell.setCellValue("服务态度好");
//        cell.setCellStyle(style);
//    cell = row1.createCell(15);
//        cell.setCellValue("加油站位置方便");
//        cell.setCellStyle(style);
//    cell = row1.createCell(16);
//        cell.setCellValue("加油站口碑好");
//        cell.setCellStyle(style);
//    cell = row1.createCell(17);
//        cell.setCellValue("服务效率快速");
//        cell.setCellStyle(style);
//    cell = row1.createCell(18);
//        cell.setCellValue("加油站分布多");
//        cell.setCellStyle(style);
//    cell = row1.createCell(19);
//        cell.setCellValue("加油卡使用方便");
//        cell.setCellStyle(style);
//    cell = row1.createCell(20);
//        cell.setCellValue("油品质量好");
//        cell.setCellStyle(style);
//    cell = row1.createCell(21);
//        cell.setCellValue("业务操作不规范");
//        cell.setCellStyle(style);
//    cell = row1.createCell(22);
//        cell.setCellValue("服务效率低");
//        cell.setCellStyle(style);
//    cell = row1.createCell(23);
//        cell.setCellValue("服务态度差");
//        cell.setCellStyle(style);
//    cell = row1.createCell(24);
//        cell.setCellValue("便利店商品单一");
//        cell.setCellStyle(style);
//    cell = row1.createCell(25);
//        cell.setCellValue("便利店商品摆放混乱");
//        cell.setCellStyle(style);
//    cell = row1.createCell(26);
//        cell.setCellValue("加油卡业务办理繁琐");
//        cell.setCellStyle(style);
//    cell = row1.createCell(27);
//        cell.setCellValue("自助加油机少");
//        cell.setCellStyle(style);
//    cell = row1.createCell(28);
//        cell.setCellValue("优惠活动少");
//        cell.setCellStyle(style);
//    cell = row1.createCell(29);
//        cell.setCellValue("活动开展形式单一");
//        cell.setCellStyle(style);
//    cell = row1.createCell(30);
//        cell.setCellValue("便民设施少");
//        cell.setCellStyle(style);
//    cell = row1.createCell(31);
//        cell.setCellValue("整体站内环境差");
//        cell.setCellStyle(style);
//    cell = row1.createCell(32);
//        cell.setCellValue("油品质量差");
//        cell.setCellStyle(style);
//    cell = row1.createCell(33);
//        cell.setCellValue("油品数量不足");
//        cell.setCellStyle(style);
//    cell = row1.createCell(34);
//        cell.setCellValue("加油站位置不方便");
//        cell.setCellStyle(style);
//    cell = row1.createCell(35);
//        cell.setCellValue("加油站口碑差");
//        cell.setCellStyle(style);
//    cell = row1.createCell(36);
//        cell.setCellValue("以上均无");
//        cell.setCellStyle(style);
}
