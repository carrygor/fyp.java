package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.AllCnpcData;
import cn.com.youplus.tenants.dao.auto.AllCnpcDataMapper;
import cn.com.youplus.tenants.api.auto.AllCnpcDataService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("allCnpcDataService")
public class AllCnpcDataServiceImpl extends MybatisServiceImpl<AllCnpcDataMapper, AllCnpcData> implements AllCnpcDataService {
	
}
