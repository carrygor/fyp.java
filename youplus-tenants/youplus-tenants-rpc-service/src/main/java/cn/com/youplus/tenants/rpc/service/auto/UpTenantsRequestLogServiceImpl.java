package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsRequestLog;
import cn.com.youplus.tenants.dao.auto.UpTenantsRequestLogMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsRequestLogService")
public class UpTenantsRequestLogServiceImpl extends MybatisServiceImpl<UpTenantsRequestLogMapper, UpTenantsRequestLog> implements UpTenantsRequestLogService {
	
}
