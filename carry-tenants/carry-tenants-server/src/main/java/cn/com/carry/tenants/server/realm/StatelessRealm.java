package cn.com.carry.tenants.server.realm;

import cn.com.carry.common.util.Constants;
import cn.com.carry.common.util.MD5Util;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class StatelessRealm extends AuthorizingRealm {


//    @Autowired
//    private UpTenantsUserRoleService upTenantsUserRoleService;
//
//    @Autowired
//    private UpTenantsUserService upTenantsUserService;
//
//    @Autowired
//    private UpTenantsCompanyService upTenantsCompanyService;


    private static Logger logger = Logger.getLogger(StatelessRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String userId = (String) principals.getPrimaryPrincipal();

//        UpTenantsUser user = upTenantsUserService.selectById(userId);
//        UpTenantsCompany company = upTenantsCompanyService.selectById(user.getTenantsCompanyId());
//        UpTenantsUserRole role = upTenantsUserRoleService.selectOne(
//                new EntityWrapper<UpTenantsUserRole>()
//                        .eq(UpTenantsUserRole.ROLE_CODE, user.getRoleCode())
//        );

        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
//        if (role != null) {
//            authorizationInfo.addRole(role.getRoleName());
//        }
//
//        ResourcesPermissionList resourcesPermissionList = new ResourcesPermissionList(role.getPermissionJson());
//        List<StringBuilder> permissionList = resourcesPermissionList.toStringPermission();
//        for (StringBuilder stringBuilder: permissionList) {
//            WildcardPermission wildcardPermission = new WildcardPermission(stringBuilder.toString());
//            authorizationInfo.addObjectPermission(wildcardPermission);
//        }
//
//        FunctionList functionList = new FunctionList(company.getFunctionStr());
//        List<StringBuilder> functionStrList = functionList.toStringPermission();
//        for (StringBuilder stringBuilder1: functionStrList) {
//            WildcardPermission wildcardPermission = new WildcardPermission(Constants.TENANTS_FUNCTION_PREFIX + stringBuilder1.toString());
//            authorizationInfo.addObjectPermission(wildcardPermission);
//        }

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
