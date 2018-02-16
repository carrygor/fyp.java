package cn.com.youplus.cms.rpc.service.auto;

import cn.com.youplus.model.auto.entity.cms.UpCmsRole;
import cn.com.youplus.cms.dao.auto.UpCmsRoleMapper;
import cn.com.youplus.cms.api.auto.UpCmsRoleService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upCmsRoleService")
public class UpCmsRoleServiceImpl extends MybatisServiceImpl<UpCmsRoleMapper, UpCmsRole> implements UpCmsRoleService {
	
}
