package cn.com.youplus.notification.rpc.service.auto;

import cn.com.youplus.model.auto.entity.notification.UpNotificationRequestLog;
import cn.com.youplus.notification.dao.auto.UpNotificationRequestLogMapper;
import cn.com.youplus.notification.api.auto.UpNotificationRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upNotificationRequestLogService")
public class UpNotificationRequestLogServiceImpl extends MybatisServiceImpl<UpNotificationRequestLogMapper, UpNotificationRequestLog> implements UpNotificationRequestLogService {
	
}
