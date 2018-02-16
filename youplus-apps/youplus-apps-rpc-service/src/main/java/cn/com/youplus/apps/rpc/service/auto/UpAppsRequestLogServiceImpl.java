package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsRequestLog;
import cn.com.youplus.apps.dao.auto.UpAppsRequestLogMapper;
import cn.com.youplus.apps.api.auto.UpAppsRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsRequestLogService")
public class UpAppsRequestLogServiceImpl extends MybatisServiceImpl<UpAppsRequestLogMapper, UpAppsRequestLog> implements UpAppsRequestLogService {
	
}
