package cn.com.youplus.cms.server.realm;

import cn.com.youplus.cms.api.auto.UpCmsRolePermissionService;
import cn.com.youplus.cms.api.auto.UpCmsRoleService;
import cn.com.youplus.cms.api.auto.UpCmsUserService;
import cn.com.youplus.cms.server.CmsServerApplication;
import cn.com.youplus.cms.server.codec.HmacSHA256Utils;
import cn.com.youplus.common.model.base.ResourcesPermission;
import cn.com.youplus.common.model.base.ResourcesPermissionList;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.model.auto.entity.cms.UpCmsRole;
import cn.com.youplus.model.auto.entity.cms.UpCmsRolePermission;
import cn.com.youplus.model.auto.entity.cms.UpCmsUser;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class StatelessRealm extends AuthorizingRealm {


    @Autowired
    private UpCmsUserService upCmsUserService;

    @Autowired
    private UpCmsRoleService upCmsRoleService;

    @Autowired
    private UpCmsRolePermissionService upCmsRolePermissionService;


    private static Logger logger = Logger.getLogger(StatelessRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();

        UpCmsUser cmsUser = upCmsUserService.selectOne(
                new EntityWrapper<UpCmsUser>()
                .eq(UpCmsUser.ID, Long.parseLong(username))
        );

        UpCmsRole cmsRole = upCmsRoleService.selectOne(
                new EntityWrapper<UpCmsRole>()
                .eq(UpCmsRole.ID, cmsUser.getRoleId())
        );

        UpCmsRolePermission rolePermission = upCmsRolePermissionService.selectOne(
                new EntityWrapper<UpCmsRolePermission>()
                .eq(UpCmsRolePermission.ROLE_ID, cmsUser.getRoleId())
        );

        ResourcesPermissionList resourcesPermissionList = new ResourcesPermissionList(rolePermission.getPermissionJson());


        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        authorizationInfo.addRole(cmsRole.getRoleName());
//        WildcardPermission permission = new WildcardPermission("系统设置");
//        WildcardPermission permission1 = new WildcardPermission("权限管理");

        List<StringBuilder> permissionList = resourcesPermissionList.toStringPermission();
        for (StringBuilder stringBuilder: permissionList) {
            WildcardPermission wildcardPermission = new WildcardPermission(stringBuilder.toString());
            authorizationInfo.addObjectPermission(wildcardPermission);
            logger.info(stringBuilder.toString());
        }
//        authorizationInfo.addObjectPermission(permission);
//        authorizationInfo.addObjectPermission(permission1);
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        String username = statelessToken.getUsername();
        //在服务器端生成客户端参数消息摘要
        Map<String, Object> map = statelessToken.getParams();
        map.remove(Constants.CMS_PARAM_TOKEN);
        map.remove(Constants.CMS_PARAM_SIGN);
        map.put(Constants.CMS_PARAM_TOKEN,new String[] {username});
        Set<String> set = map.keySet();
        List<String> list = new ArrayList<>();
        for (String str : set) {
            list.add(str);
        }
        list.sort(String::compareTo);
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : list) {
            String [] strings = (String[])map.get(s);
            try {
                //stringBuilder.append(URLEncoder.encode(strings[0], "utf-8"));
                stringBuilder.append(strings[0]);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

        String serverDigest = MD5Util.MD5(stringBuilder.toString()).toLowerCase();
        System.out.println(statelessToken.getClientDigest());
        System.out.println(stringBuilder.toString());
        System.out.println(serverDigest);

        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(
                username,
                serverDigest,
                getName());
    }
}
