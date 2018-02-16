package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinMessageLog;
import cn.com.youplus.thirdparty.dao.auto.UpWeixinMessageLogMapper;
import cn.com.youplus.thirdparty.api.auto.UpWeixinMessageLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信时间推送 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upWeixinMessageLogService")
public class UpWeixinMessageLogServiceImpl extends MybatisServiceImpl<UpWeixinMessageLogMapper, UpWeixinMessageLog> implements UpWeixinMessageLogService {
	
}
