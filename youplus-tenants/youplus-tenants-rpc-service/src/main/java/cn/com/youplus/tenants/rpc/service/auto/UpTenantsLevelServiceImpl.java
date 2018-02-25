package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsLevel;
import cn.com.youplus.tenants.dao.auto.UpTenantsLevelMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsLevelService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsLevelService")
public class UpTenantsLevelServiceImpl extends MybatisServiceImpl<UpTenantsLevelMapper, UpTenantsLevel> implements UpTenantsLevelService {
	
}