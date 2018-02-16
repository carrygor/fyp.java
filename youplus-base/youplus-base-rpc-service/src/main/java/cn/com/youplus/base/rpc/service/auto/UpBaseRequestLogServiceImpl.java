package cn.com.youplus.base.rpc.service.auto;

import cn.com.youplus.model.auto.entity.base.UpBaseRequestLog;
import cn.com.youplus.base.dao.auto.UpBaseRequestLogMapper;
import cn.com.youplus.base.api.auto.UpBaseRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upBaseRequestLogService")
public class UpBaseRequestLogServiceImpl extends MybatisServiceImpl<UpBaseRequestLogMapper, UpBaseRequestLog> implements UpBaseRequestLogService {
	
}
