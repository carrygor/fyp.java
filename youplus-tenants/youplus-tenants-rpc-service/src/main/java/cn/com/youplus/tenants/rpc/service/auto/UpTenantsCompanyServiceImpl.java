package cn.com.youplus.tenants.rpc.service.auto;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.tenants.dao.auto.UpTenantsCompanyMapper;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTenantsCompanyService")
public class UpTenantsCompanyServiceImpl extends MybatisServiceImpl<UpTenantsCompanyMapper, UpTenantsCompany> implements UpTenantsCompanyService {
	
}
