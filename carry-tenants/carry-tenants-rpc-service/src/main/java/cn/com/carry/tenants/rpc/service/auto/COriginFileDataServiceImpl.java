package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.COriginFileData;
import cn.com.carry.tenants.dao.auto.COriginFileDataMapper;
import cn.com.carry.tenants.api.auto.COriginFileDataService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cOriginFileDataService")
public class COriginFileDataServiceImpl extends MybatisServiceImpl<COriginFileDataMapper, COriginFileData> implements COriginFileDataService {
	
}
