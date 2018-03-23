package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.COriginData;
import cn.com.carry.tenants.dao.auto.COriginDataMapper;
import cn.com.carry.tenants.api.auto.COriginDataService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cOriginDataService")
public class COriginDataServiceImpl extends MybatisServiceImpl<COriginDataMapper, COriginData> implements COriginDataService {
	
}
