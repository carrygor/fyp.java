package cn.com.youplus.notification.rpc.service.auto;

import cn.com.youplus.model.auto.entity.notification.UpNotificationSmsRecord;
import cn.com.youplus.notification.dao.auto.UpNotificationSmsRecordMapper;
import cn.com.youplus.notification.api.auto.UpNotificationSmsRecordService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upNotificationSmsRecordService")
public class UpNotificationSmsRecordServiceImpl extends MybatisServiceImpl<UpNotificationSmsRecordMapper, UpNotificationSmsRecord> implements UpNotificationSmsRecordService {
	
}
