package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.COriginDataPageUrl;
import cn.com.carry.tenants.dao.auto.COriginDataPageUrlMapper;
import cn.com.carry.tenants.api.auto.COriginDataPageUrlService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cOriginDataPageUrlService")
public class COriginDataPageUrlServiceImpl extends MybatisServiceImpl<COriginDataPageUrlMapper, COriginDataPageUrl> implements COriginDataPageUrlService {
	
}
