package cn.com.youplus.cms.rpc.service.auto;

import cn.com.youplus.model.auto.entity.cms.UpCmsRequestLog;
import cn.com.youplus.cms.dao.auto.UpCmsRequestLogMapper;
import cn.com.youplus.cms.api.auto.UpCmsRequestLogService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upCmsRequestLogService")
public class UpCmsRequestLogServiceImpl extends MybatisServiceImpl<UpCmsRequestLogMapper, UpCmsRequestLog> implements UpCmsRequestLogService {
	
}
