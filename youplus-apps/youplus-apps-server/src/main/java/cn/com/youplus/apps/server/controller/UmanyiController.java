package cn.com.youplus.apps.server.controller;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.apps.api.common.QuestionnaireService;
import cn.com.youplus.apps.common.form.CheckForm;
import cn.com.youplus.apps.common.form.QuestionnaireForm;
import cn.com.youplus.apps.common.model.Questionnaire;
import cn.com.youplus.apps.common.model.enums.EntranceEnum;
import cn.com.youplus.apps.common.model.enums.QuestionnaireAttrEnum;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.BaseIdForm;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.common.util.ComparatorUtil;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.RequestUtil;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * www.umanyi.com:8087//Survey/ServiceSurvey?r=201711291207211&T=cnpc01
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Survey")
public class UmanyiController extends SuperController{

    //region autowired
    Logger logger = LoggerFactory.getLogger(UmanyiController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsAnswerSheetService upAppsAnswerSheetService;

    @Autowired
    private UpAppsAnswerSheetItemService upAppsAnswerSheetItemService;


    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private WeixinProperties weixinProperties;

    private String regions = "{\"cnpc30\":{\"q\":\"937325886211137537\",\"r\":\"937325850010132481\",\"c\":\"937325836747743234\"},\"cnpc11\":{\"q\":\"937325886211137537\",\"r\":\"937325875339534338\",\"c\":\"937325836747743234\"},\"cnpc12\":{\"q\":\"937325886211137537\",\"r\":\"937325868959997954\",\"c\":\"937325836747743234\"},\"cnpc31\":{\"q\":\"937325886211137537\",\"r\":\"937325871438831618\",\"c\":\"937325836747743234\"},\"cnpc10\":{\"q\":\"937325886211137537\",\"r\":\"937325878070026242\",\"c\":\"937325836747743234\"},\"cnpc04\":{\"q\":\"937325886211137537\",\"r\":\"937325842506522626\",\"c\":\"937325836747743234\"},\"cnpc26\":{\"q\":\"937325886211137537\",\"r\":\"937325852635766785\",\"c\":\"937325836747743234\"},\"cnpc05\":{\"q\":\"937325886211137537\",\"r\":\"937325870159568897\",\"c\":\"937325836747743234\"},\"cnpc27\":{\"q\":\"937325886211137537\",\"r\":\"937325883078025217\",\"c\":\"937325836747743234\"},\"cnpc02\":{\"q\":\"937325886211137537\",\"r\":\"937325846214287361\",\"c\":\"937325836747743234\"},\"cnpc24\":{\"q\":\"937325886211137537\",\"r\":\"937325851184537602\",\"c\":\"937325836747743234\"},\"cnpc25\":{\"q\":\"937325886211137537\",\"r\":\"937325863385767937\",\"c\":\"937325836747743234\"},\"cnpc03\":{\"q\":\"937325886211137537\",\"r\":\"937325867554906113\",\"c\":\"937325836747743234\"},\"cnpc08\":{\"q\":\"937325886211137537\",\"r\":\"937325848839921665\",\"c\":\"937325836747743234\"},\"cnpc09\":{\"q\":\"937325886211137537\",\"r\":\"937325860806270978\",\"c\":\"937325836747743234\"},\"cnpc28\":{\"q\":\"937325886211137537\",\"r\":\"937325855303344129\",\"c\":\"937325836747743234\"},\"cnpc06\":{\"q\":\"937325886211137537\",\"r\":\"937325884265013250\",\"c\":\"937325836747743234\"},\"cnpc29\":{\"q\":\"937325886211137537\",\"r\":\"937325847573241858\",\"c\":\"937325836747743234\"},\"cnpc07\":{\"q\":\"937325886211137537\",\"r\":\"937325856611966977\",\"c\":\"937325836747743234\"},\"cnpc22\":{\"q\":\"937325886211137537\",\"r\":\"937325872831340545\",\"c\":\"937325836747743234\"},\"cnpc23\":{\"q\":\"937325886211137537\",\"r\":\"937325854003109889\",\"c\":\"937325836747743234\"},\"cnpc01\":{\"q\":\"937325886211137537\",\"r\":\"937325857966727169\",\"c\":\"937325836747743234\"},\"cnpc20\":{\"q\":\"937325886211137537\",\"r\":\"937325876585242626\",\"c\":\"937325836747743234\"},\"cnpc21\":{\"q\":\"937325886211137537\",\"r\":\"937325840765886465\",\"c\":\"937325836747743234\"},\"cnpc\":{\"q\":\"937325886211137537\",\"r\":\"937325839268519937\",\"c\":\"937325836747743234\"},\"cnpc15\":{\"q\":\"937325886211137537\",\"r\":\"937325844184244226\",\"c\":\"937325836747743234\"},\"cnpc16\":{\"q\":\"937325886211137537\",\"r\":\"937325873972191234\",\"c\":\"937325836747743234\"},\"cnpc13\":{\"q\":\"937325886211137537\",\"r\":\"937325859564756994\",\"c\":\"937325836747743234\"},\"cnpc14\":{\"q\":\"937325886211137537\",\"r\":\"937325866212728834\",\"c\":\"937325836747743234\"},\"cnpc19\":{\"q\":\"937325886211137537\",\"r\":\"937325864757305346\",\"c\":\"937325836747743234\"},\"cnpc17\":{\"q\":\"937325886211137537\",\"r\":\"937325862051979266\",\"c\":\"937325836747743234\"},\"cnpc18\":{\"q\":\"937325886211137537\",\"r\":\"937325880569831425\",\"c\":\"937325836747743234\"}}";

    private JSONObject provinces = null;

    //endregion

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    @GetMapping("/ServiceSurvey")
    public void mredirect() throws IOException {
        redirect();
    }
    public void redirect() throws IOException {
        String r=request.getParameter("r");
        String T=request.getParameter("T");


        if (!ValueHelper.isNone(r) && r.equals("201711291207211")) {
            if (ValueHelper.isNone(T)) {
                T = "cnpc";
            }
            if (provinces == null) {
                provinces = JSONObject.parseObject(regions);
            }

            String jsonStr = JSONObject.toJSONString(provinces.get(T.toLowerCase().trim()));

            if (!ValueHelper.isNone(jsonStr)) {
                String location = systemConfig.getAppUrl() + "/#/welcome/"
                        + URLEncoder.encode(jsonStr);
                logger.debug(location);
                response.sendRedirect(location);
                return;
            }
        }
        logger.debug("跳转到:");
        logger.debug(request.getRequestURL().toString());
        logger.debug(request.getServletPath());
        logger.debug(request.getContextPath());
        logger.debug(request.getQueryString());
        String loc = "http://umanyi.com:8087/Survey/ServiceSurvey?" + request.getQueryString();
        logger.debug(loc);
        response.sendRedirect(loc);

    }
}
