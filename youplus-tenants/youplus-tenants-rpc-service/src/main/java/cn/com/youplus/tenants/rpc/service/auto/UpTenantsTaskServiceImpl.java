package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsTask;
import cn.com.youplus.tenants.dao.auto.UpTenantsTaskMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsTaskService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务进度管理 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsTaskService")
public class UpTenantsTaskServiceImpl extends MybatisServiceImpl<UpTenantsTaskMapper, UpTenantsTask> implements UpTenantsTaskService {
	
}
