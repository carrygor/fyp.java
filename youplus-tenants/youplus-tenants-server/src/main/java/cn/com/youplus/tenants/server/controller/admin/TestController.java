package cn.com.youplus.tenants.server.controller.admin;

import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.resources.WeixinProperties;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.server.controller.SuperController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/test")
public class TestController extends SuperController {

    private static Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;


    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    @PostMapping("/answerSheet/upload")
    public BaseResponse testAns() throws Exception {
        BaseResponse response = new BaseResponse();
        /*String result = answerSheetService.exportOldData();
        response.setData(result);*/
        return response;
    }




}
