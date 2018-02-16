package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;
import cn.com.youplus.tenants.dao.auto.UpTenantsUserMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsUserService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsUserService")
public class UpTenantsUserServiceImpl extends MybatisServiceImpl<UpTenantsUserMapper, UpTenantsUser> implements UpTenantsUserService {
	
}
