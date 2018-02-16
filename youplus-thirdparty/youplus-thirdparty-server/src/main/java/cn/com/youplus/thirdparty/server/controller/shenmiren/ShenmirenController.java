package cn.com.youplus.thirdparty.server.controller.shenmiren;

import cn.com.youplus.common.exception.data.InvalidCustomIdException;
import cn.com.youplus.common.exception.data.InvalidParameterException;
import cn.com.youplus.common.exception.data.SignatureException;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.util.RequestUtil;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.thirdparty.api.shenmiren.ShenmirenExcelService;
import cn.com.youplus.thirdparty.server.controller.SuperController;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v1")
public class ShenmirenController extends SuperController{

    //region Autowired
    private final static Logger logger = Logger.getLogger(ShenmirenController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ShenmirenExcelService shenmirenExcelService;

    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }

    //endregion

    //region 中石化项目
    public static void main(String [] args) {
        long t = new Date().getTime() / 1000;
        String ak = "zh201e5b389567fcbc";
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

    @PostMapping("/getreport")
    public BaseResponse getreport() throws Exception {
        BaseResponse response = new BaseResponse();
        String jsonStr = RequestUtil.getRequestPostStr(request);

        String t = request.getParameter("timestamp");
        String ak = request.getParameter("api_key");
        String signature = request.getParameter("signature");
        String sk = "M4mkxqqvHObelPh64oKVCOCI7CmacSJs7STjHIXrUTT";

        if (ValueHelper.isNone(ak)) {
            throw new InvalidParameterException("参数错误，没有api_key!");
        }

        if (ValueHelper.isNone(signature)) {
            throw new InvalidParameterException("参数错误，没有签名信息!");
        }

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


        if (ValueHelper.isNone(jsonStr)) {
            throw new InvalidParameterException("参数错误，提交的参数为空!");
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(jsonStr);
        } catch (Exception e) {
            throw new InvalidParameterException("参数错误，提交的参数不是JSON格式!");
        }

        if (!"fjzh13572".equals(jsonObject.get("c_id"))) {
            throw new InvalidCustomIdException("客户编号错误！");
        }

        if (ValueHelper.isNone(jsonObject.getString("date"))) {
            throw new InvalidParameterException("参数错误，提交的日期不能为空!");
        }

        response.setData(shenmirenExcelService.getParsedExcelData("福建", jsonObject.getString("date")));
        return response;
    }

    //endregion

}
