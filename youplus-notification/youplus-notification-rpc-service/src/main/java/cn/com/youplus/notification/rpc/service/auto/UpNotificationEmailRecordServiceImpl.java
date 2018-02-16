package cn.com.youplus.notification.rpc.service.auto;

import cn.com.youplus.model.auto.entity.notification.UpNotificationEmailRecord;
import cn.com.youplus.notification.dao.auto.UpNotificationEmailRecordMapper;
import cn.com.youplus.notification.api.auto.UpNotificationEmailRecordService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upNotificationEmailRecordService")
public class UpNotificationEmailRecordServiceImpl extends MybatisServiceImpl<UpNotificationEmailRecordMapper, UpNotificationEmailRecord> implements UpNotificationEmailRecordService {
	
}
