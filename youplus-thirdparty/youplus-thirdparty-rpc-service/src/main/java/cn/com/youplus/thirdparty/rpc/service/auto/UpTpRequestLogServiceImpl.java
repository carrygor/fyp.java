package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpTpRequestLog;
import cn.com.youplus.thirdparty.dao.auto.UpTpRequestLogMapper;
import cn.com.youplus.thirdparty.api.auto.UpTpRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTpRequestLogService")
public class UpTpRequestLogServiceImpl extends MybatisServiceImpl<UpTpRequestLogMapper, UpTpRequestLog> implements UpTpRequestLogService {
	
}
