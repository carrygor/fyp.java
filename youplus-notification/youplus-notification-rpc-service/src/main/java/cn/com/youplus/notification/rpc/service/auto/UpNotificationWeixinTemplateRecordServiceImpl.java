package cn.com.youplus.notification.rpc.service.auto;

import cn.com.youplus.model.auto.entity.notification.UpNotificationWeixinTemplateRecord;
import cn.com.youplus.notification.dao.auto.UpNotificationWeixinTemplateRecordMapper;
import cn.com.youplus.notification.api.auto.UpNotificationWeixinTemplateRecordService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upNotificationWeixinTemplateRecordService")
public class UpNotificationWeixinTemplateRecordServiceImpl extends MybatisServiceImpl<UpNotificationWeixinTemplateRecordMapper, UpNotificationWeixinTemplateRecord> implements UpNotificationWeixinTemplateRecordService {
	
}
