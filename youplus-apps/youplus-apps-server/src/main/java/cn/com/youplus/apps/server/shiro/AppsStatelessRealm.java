package cn.com.youplus.apps.server.shiro;

import cn.com.youplus.common.model.base.FunctionList;
import cn.com.youplus.common.model.base.ResourcesPermissionList;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.RedisUtil;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUserRole;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserRoleService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class AppsStatelessRealm extends AuthorizingRealm {


    @Autowired
    private UpTenantsUserRoleService upTenantsUserRoleService;

    @Autowired
    private UpTenantsUserService upTenantsUserService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;


    private static Logger logger = Logger.getLogger(AppsStatelessRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof AppsStatelessToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //TODO 暂时不需要权限控制
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AppsStatelessToken statelessToken = (AppsStatelessToken) token;
        String timestamp = statelessToken.getTimestamp();
        String sign = statelessToken.getSign();
        String noise = statelessToken.getNoise();

        String serverDigest = "";
        if (!ValueHelper.isNone(noise)) {
            try {
                Long t = ValueHelper.tryParseLong(timestamp, 0L);
                if (Math.abs(t - new Date().getTime()) > Constants.ONE_DAY) { //一天以外
                    logger.info("Err请求时间超出范围的");
                    serverDigest = "fuck";
                } else {
                    boolean isExists = false;
                    try {
                        if ("Y".equals(RedisUtil.get(Constants.APPS_NOISE_PRIFIX + noise))) {
                            isExists = true;
                        }
                    } catch (Exception ex) {
                        //如果有异常，当初就先不管，因为不能保证Redis一定正常工作
                    }
                    if (!isExists) {
                        serverDigest = MD5Util.MD5(timestamp + noise).toLowerCase();
                        RedisUtil.set(Constants.APPS_NOISE_PRIFIX + noise, "Y", RedisUtil.EXRP_HOUR);
                    } else {
                        logger.info("无效noise");
                        serverDigest = "fuck";
                    }
                }
            } catch (Exception e) {
                serverDigest = "fuck";
                logger.info("有异常");
            }
        } else {
            logger.info("空noise");
            serverDigest = "fuck";
        }

        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(
                noise,
                serverDigest,
                getName());
    }
}
