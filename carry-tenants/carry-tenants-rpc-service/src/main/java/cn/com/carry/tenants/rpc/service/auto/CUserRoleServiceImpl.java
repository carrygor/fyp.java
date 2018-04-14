package cn.com.carry.tenants.rpc.service.auto;

import cn.com.carry.model.auto.entity.tenants.CUserRole;
import cn.com.carry.tenants.dao.auto.CUserRoleMapper;
import cn.com.carry.tenants.api.auto.CUserRoleService;
import cn.com.carry.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何文浩
 */
@Service("cUserRoleService")
public class CUserRoleServiceImpl extends MybatisServiceImpl<CUserRoleMapper, CUserRole> implements CUserRoleService {
	
}
