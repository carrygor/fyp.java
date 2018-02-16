package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.tenants.dao.auto.UpTenantsRegionMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsRegionService")
public class UpTenantsRegionServiceImpl extends MybatisServiceImpl<UpTenantsRegionMapper, UpTenantsRegion> implements UpTenantsRegionService {
	
}
