package cn.com.youplus.base.rpc.service.auto;

import cn.com.youplus.model.auto.entity.base.UpBaseSystemConfig;
import cn.com.youplus.base.dao.auto.UpBaseSystemConfigMapper;
import cn.com.youplus.base.api.auto.UpBaseSystemConfigService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upBaseSystemConfigService")
public class UpBaseSystemConfigServiceImpl extends MybatisServiceImpl<UpBaseSystemConfigMapper, UpBaseSystemConfig> implements UpBaseSystemConfigService {
	
}
