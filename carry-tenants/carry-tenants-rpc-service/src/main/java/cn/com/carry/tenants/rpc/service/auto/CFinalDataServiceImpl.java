package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.CFinalData;
import cn.com.carry.tenants.dao.auto.CFinalDataMapper;
import cn.com.carry.tenants.api.auto.CFinalDataService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cFinalDataService")
public class CFinalDataServiceImpl extends MybatisServiceImpl<CFinalDataMapper, CFinalData> implements CFinalDataService {
	
}
