package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.COriginExcelData;
import cn.com.carry.tenants.dao.auto.COriginExcelDataMapper;
import cn.com.carry.tenants.api.auto.COriginExcelDataService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cOriginExcelDataService")
public class COriginExcelDataServiceImpl extends MybatisServiceImpl<COriginExcelDataMapper, COriginExcelData> implements COriginExcelDataService {
	
}
