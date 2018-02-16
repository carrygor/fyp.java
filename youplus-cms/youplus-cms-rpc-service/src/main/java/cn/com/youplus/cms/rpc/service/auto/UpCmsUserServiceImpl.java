package cn.com.youplus.cms.rpc.service.auto;

import cn.com.youplus.model.auto.entity.cms.UpCmsUser;
import cn.com.youplus.cms.dao.auto.UpCmsUserMapper;
import cn.com.youplus.cms.api.auto.UpCmsUserService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upCmsUserService")
public class UpCmsUserServiceImpl extends MybatisServiceImpl<UpCmsUserMapper, UpCmsUser> implements UpCmsUserService {
	
}
