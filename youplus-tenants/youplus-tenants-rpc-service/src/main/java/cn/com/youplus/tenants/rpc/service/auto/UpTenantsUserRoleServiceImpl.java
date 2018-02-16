package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsUserRole;
import cn.com.youplus.tenants.dao.auto.UpTenantsUserRoleMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsUserRoleService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsUserRoleService")
public class UpTenantsUserRoleServiceImpl extends MybatisServiceImpl<UpTenantsUserRoleMapper, UpTenantsUserRole> implements UpTenantsUserRoleService {
	
}
