package cn.com.youplus.cms.server.realm;

import cn.com.youplus.model.auto.entity.cms.UpCmsUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CmsRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据用户ID查询权限（permission），放入到Authorization里。
        Set<String> permissions = new HashSet<>();
        permissions.add("permission:show");
        permissions.add("permission:delete");
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if(!"hwh".equals(token.getUsername()) || !"888888".equals(token.getPassword())){
            throw new AccountException("帐号或密码不正确！");
        }

        UpCmsUser cmsUser = new UpCmsUser();
        cmsUser.setUserName("hwh")
                .setId(1L)
                .setJobTitle("zheshiJobtitle");
        return new SimpleAuthenticationInfo(cmsUser,"888888", "hwh");
    }
}
