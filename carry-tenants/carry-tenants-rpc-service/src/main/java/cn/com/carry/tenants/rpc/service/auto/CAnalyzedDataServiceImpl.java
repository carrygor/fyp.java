package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.CAnalyzedData;
import cn.com.carry.tenants.dao.auto.CAnalyzedDataMapper;
import cn.com.carry.tenants.api.auto.CAnalyzedDataService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cAnalyzedDataService")
public class CAnalyzedDataServiceImpl extends MybatisServiceImpl<CAnalyzedDataMapper, CAnalyzedData> implements CAnalyzedDataService {
	
}
