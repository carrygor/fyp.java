package cn.com.youplus.cms.server.controller.admin;

import cn.com.youplus.cms.server.controller.SuperController;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
import com.google.zxing.WriterException;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cms/v1/test")
public class TestController extends SuperController {

    @Autowired
    private HttpServletRequest request;

    private final static Logger logger = Logger.getLogger(TestController.class);

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }

    @Permission("role:create")
    @PostMapping("/succ")
    public BaseResponse testSucc() throws IOException, WriterException {
        BaseResponse response = new BaseResponse();

        return response;
    }

    @RequiresRoles("admin")
    @PostMapping("/role")
    public BaseResponse testRole() {
        BaseResponse response = new BaseResponse();
        response.setData("这是成功测试");
        return response;
    }

    @PostMapping("/jump")
    public BaseResponse testJump() {
        BaseResponse response = new BaseResponse();
        String data = this.getData();
        response.setData(data);
        return response;
    }

    @RequiresRoles("superAdmin")
    public String getData() {
        return "superAdmin";
    }

    @PostMapping("/none")
    public BaseResponse testNone() {
        BaseResponse response = new BaseResponse();
        response.setData("这是成功测试");
        return response;
    }

    @PostMapping("/superAdmin")
    public BaseResponse superAdmin() {
        BaseResponse response = new BaseResponse();
        response.setData("这是成功测试");
        return response;
    }

    @Permission("role:add")
    @PostMapping("/alert")
    public BaseResponse testAlert() throws AlertException {
        throw new AlertException("这是Alert返回测试！");
    }

}
