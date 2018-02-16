package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinRequestLog;
import cn.com.youplus.thirdparty.dao.auto.UpWeixinRequestLogMapper;
import cn.com.youplus.thirdparty.api.auto.UpWeixinRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upWeixinRequestLogService")
public class UpWeixinRequestLogServiceImpl extends MybatisServiceImpl<UpWeixinRequestLogMapper, UpWeixinRequestLog> implements UpWeixinRequestLogService {
	
}
