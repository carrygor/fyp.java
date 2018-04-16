package cn.com.carry.tenants.server.controller.user;

import cn.com.carry.common.annotation.Permission;
import cn.com.carry.common.exception.interaction.AlertException;
import cn.com.carry.common.form.BasePageForm;
import cn.com.carry.common.model.base.BaseResponse;
import cn.com.carry.common.util.AESUtil;
import cn.com.carry.common.util.Constants;
import cn.com.carry.common.util.MD5Util;
import cn.com.carry.common.util.StringHelper;
import cn.com.carry.common.validation.annotation.Valid;
import cn.com.carry.model.auto.entity.tenants.CAnalyzedData;
import cn.com.carry.model.auto.entity.tenants.CFinalData;
import cn.com.carry.model.auto.entity.tenants.CUser;
import cn.com.carry.model.auto.entity.tenants.CUserRole;
import cn.com.carry.tenants.api.auto.CAnalyzedDataService;
import cn.com.carry.tenants.api.auto.CFinalDataService;
import cn.com.carry.tenants.api.auto.CUserRoleService;
import cn.com.carry.tenants.api.auto.CUserService;
import cn.com.carry.tenants.common.form.KeywordForm;
import cn.com.carry.tenants.common.form.LoginForm;
import cn.com.carry.tenants.server.controller.SuperController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CUserService cUserService;

    @Autowired
    private CFinalDataService cFinalDataService;

    @Autowired
    private CAnalyzedDataService cAnalyzedDataService;

    @Autowired
    private CUserRoleService cUserRoleService;

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

        CUser user = cUserService.selectOne(
                new EntityWrapper<CUser>()
                        .eq(CUser.USER_NAME, form.getUsername())

        );

        if (user == null) {
            throw new AlertException("用户名不存在!");
        }

        if (!MD5Util.validPassword(form.getPassword(), user.getSalt(), user.getPassword())) {
            throw new AlertException("密码错误!");
        }
        user.setPassword(null)
                .setSalt(null);

        Map<String, Object> result = new HashMap<>();
        result.put("token", AESUtil.AESEncodeForWeb(user.getId().toString()));
        result.put("userInfo", user);

        CUserRole role = cUserRoleService.selectById(user.getUserRoleId());
        if (role != null) {
            result.put("routers", role.getRoleCode());
        }
        response.setData(result);

        return response;
    }

    @PostMapping("/register")
    public BaseResponse register(@Valid LoginForm form) throws AlertException {
        BaseResponse response = new BaseResponse();

        CUser user = cUserService.selectOne(
                new EntityWrapper<CUser>()
                        .eq(CUser.USER_NAME, form.getUsername())

        );
        if (user != null) {
            throw new AlertException("该用户名已存在");
        }
        user = new CUser();
        String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
        String password = MD5Util.getPassword(form.getPassword(),salt).toLowerCase();

        user.setUserName(form.getUsername())
                .setSalt(salt)
                .setPassword(password);
        cUserService.insert(user);

        return response;
    }

    @Permission(value = "数据展示")
    @PostMapping("/finalData/get")
    public BaseResponse finalDataGet(BasePageForm form) {
        BaseResponse response = new BaseResponse();

        Page<CFinalData> page = cFinalDataService.selectPage(
                new Page<>(form.getPageNum(), form.getPageSize()),
                new EntityWrapper<CFinalData>()
                        .eq(CFinalData.DATA_TYPE, form.getDataType())
                        .like(CFinalData.SUPPLIER, form.getKeyword())
                        .orderBy(CFinalData.SORT, true)
        );

        Map<String, Object> result = new HashMap<>();

        result.put("list", page.getRecords());
        result.put("total", page.getTotal());
        response.setData(result);

        return response;
    }

    @Permission(value = "数据展示")
    @PostMapping("/analyzed/get")
    public BaseResponse analyzedGet(KeywordForm form) {
        BaseResponse response = new BaseResponse();
        CAnalyzedData cAnalyzedData = cAnalyzedDataService.selectOne(
                new EntityWrapper<CAnalyzedData>()
                        .eq(CAnalyzedData.SUPPLIER, form.getKeyword())
                        .orderBy(CAnalyzedData.ADD_TIME, false)
        );

        List<CFinalData> list = cFinalDataService.selectList(
                new EntityWrapper<CFinalData>()
                        .eq(CFinalData.SUPPLIER, form.getKeyword())
        );

        Map<String, Object> result = new HashMap<>();
        result.put("analyzedData", cAnalyzedData);
        result.put("list", list);
        response.setData(result);

        return response;
    }

}














