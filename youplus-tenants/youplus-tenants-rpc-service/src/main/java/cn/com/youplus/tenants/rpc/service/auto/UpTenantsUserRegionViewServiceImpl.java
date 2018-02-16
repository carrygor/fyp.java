package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsUserRegionView;
import cn.com.youplus.tenants.dao.auto.UpTenantsUserRegionViewMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsUserRegionViewService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsUserRegionViewService")
public class UpTenantsUserRegionViewServiceImpl extends MybatisServiceImpl<UpTenantsUserRegionViewMapper, UpTenantsUserRegionView> implements UpTenantsUserRegionViewService {
	
}
