package cn.com.youplus.cms.rpc.service.auto;

import cn.com.youplus.model.auto.entity.cms.UpCmsRolePermission;
import cn.com.youplus.cms.dao.auto.UpCmsRolePermissionMapper;
import cn.com.youplus.cms.api.auto.UpCmsRolePermissionService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upCmsRolePermissionService")
public class UpCmsRolePermissionServiceImpl extends MybatisServiceImpl<UpCmsRolePermissionMapper, UpCmsRolePermission> implements UpCmsRolePermissionService {
	
}
