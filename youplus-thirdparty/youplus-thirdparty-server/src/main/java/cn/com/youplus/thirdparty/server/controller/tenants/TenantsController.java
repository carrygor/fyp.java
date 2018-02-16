package cn.com.youplus.thirdparty.server.controller.tenants;

import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.common.exception.data.InvalidParameterException;
import cn.com.youplus.common.exception.data.SignatureException;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.util.HttpConnect;
import cn.com.youplus.common.util.RequestUtil;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Valid;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.common.form.UpEncryptForm;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import cn.com.youplus.thirdparty.api.shenmiren.ShenmirenExcelService;
import cn.com.youplus.thirdparty.server.controller.SuperController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/11.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/thirdparty/v1/tenants")
public class TenantsController extends SuperController{

    //region Autowired
    private final static Logger logger = Logger.getLogger(TenantsController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ShenmirenExcelService shenmirenExcelService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private UpWeixinAccountService upWeixinAccountService;

    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }

    //endregion

    //region
    public static void main(String [] args) {
        long t = new Date().getTime() / 1000;
        String ak = "926063648787984386";
        String sk = "M4mkxqqvHObelPh64oKVCOCI7CmacSJs7STjHIXrUTT";

        List<String> list = new ArrayList<>();
        list.add(t + "");
        list.add(ak);
        list.add(sk);
        list.sort(String::compareTo);

        StringBuilder longStr = new StringBuilder();
        for (String s : list) {
            longStr.append(s);
        }
        logger.debug(longStr.toString());
        //
        String sig = DigestUtils.sha1Hex(longStr.toString());
        System.out.println("t->" + t);
        System.out.println("t->" + sig);
        System.out.println("?api_key=" + ak + "&timestamp=" + t + "&signature=" + sig);
    }

    @PostMapping("/weixin/template/send")
    public BaseResponse weixinTemplateSend(@Valid UpEncryptForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        String jsonStr = RequestUtil.getRequestPostStr(request);

        String t = form.getTimestamp();
        String ak = form.getApi_key();
        String signature = form.getSignature();
        UpTenantsCompany company = upTenantsCompanyService.selectById(ak);
        // TODO: 2017/11/23/023 set api_sccret 
        String sk = company.getApiSecret();

        boolean isValidTimestamp = false;

        if (!ValueHelper.isNone(t)) {
            if (isValidTimestamp) {
                long time = ValueHelper.tryParse(t, -1);
                long delta = new Date().getTime() / 1000 - time;
                if (time < 0) {
                    throw new InvalidParameterException("参数错误，时间戳格式出错!");
                }

                if (delta < 0) {
                    throw new InvalidParameterException("参数错误，时间大于系统时间(" + delta + "s)!");
                }

                if (delta > 60) {
                    throw new InvalidParameterException("参数错误，时间戳不能小于系统时间60s(" + delta + "s)!");
                }
            }
        } else {
            throw new InvalidParameterException("参数错误，没有时间戳信息!");
        }

        List<String> list = new ArrayList<>();
        list.add(t);
        list.add(ak);
        list.add(sk);
        list.sort(String::compareTo);

        StringBuilder longStr = new StringBuilder();
        for (String s : list) {
            longStr.append(s);
        }

        logger.debug(longStr.toString());
        //验证：
        if (!signature.equals(DigestUtils.sha1Hex(longStr.toString()))) {
            throw new SignatureException("签名错误!");
        }

        UpWeixinAccount weixinAccount = upWeixinAccountService.selectOne(
                new EntityWrapper<UpWeixinAccount>()
                        .eq(UpWeixinAccount.IS_AUTH, 1)
                        .eq(UpWeixinAccount.TENANTS_COMPANY_ID, company.getId())
        );
        if (weixinAccount == null) {
            throw new AlertException("公众号配置异常");
        }
        String openid = form.getOpenid();
        JSONObject sendData = JSONObject.parseObject(company.getTemplateJson());
        if (sendData == null) {
            throw new AlertException("该公司模板消息配置错误");
        }
        sendData.put("touser", openid);
        // TODO: 2017/11/23/023 url
        JSONObject jsonObject = new JSONObject();
        // TODO: 2017/11/27/027 获取所需的数据
        jsonObject.put("companyId", "926063648787984386");
        jsonObject.put("openid", openid);
        jsonObject.put("regionId", "region_id");
        jsonObject.put("questionnaireId", "questionnaire_id");

        String clickUrl = "http://thirdparty.shenmiren.com.cn/users/#/welcome/" + jsonObject.toJSONString();

        sendData.put("url", clickUrl);
        HttpConnect httpConnect = HttpConnect.getInstance();
        String url = WeixinConstants.WEIXIN_TEMPLATE_SEND + "?access_token=" + weixinAccount.getAuthorizerAccessToken();
        JSONObject result = httpConnect.doPost(url, sendData);
        response.setErrcode(result.getIntValue("errcode"));
        response.setErrmsg(result.getString("errmsg"));

        return response;
    }

    //endregion

}
