package cn.com.youplus.notification.rpc.service.auto;

import cn.com.youplus.model.auto.entity.notification.UpNotificationMessageQueueRecord;
import cn.com.youplus.notification.dao.auto.UpNotificationMessageQueueRecordMapper;
import cn.com.youplus.notification.api.auto.UpNotificationMessageQueueRecordService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upNotificationMessageQueueRecordService")
public class UpNotificationMessageQueueRecordServiceImpl extends MybatisServiceImpl<UpNotificationMessageQueueRecordMapper, UpNotificationMessageQueueRecord> implements UpNotificationMessageQueueRecordService {
	
}
