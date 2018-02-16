package cn.com.youplus.apps.server.controller;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
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
@RequestMapping("/tenants/v1")
public class TestController {

    @PostMapping("/test/succ")
    public BaseResponse testSucc() {
        BaseResponse response = new BaseResponse();
        response.setData("这是成功测试");
        return response;
    }

    @PostMapping("/test/alert")
    public BaseResponse testAlert() throws AlertException {
        throw new AlertException("这是Alert返回测试！");
    }

}
