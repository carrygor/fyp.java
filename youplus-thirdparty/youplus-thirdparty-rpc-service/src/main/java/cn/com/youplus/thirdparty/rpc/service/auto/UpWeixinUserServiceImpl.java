package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinUser;
import cn.com.youplus.thirdparty.dao.auto.UpWeixinUserMapper;
import cn.com.youplus.thirdparty.api.auto.UpWeixinUserService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upWeixinUserService")
public class UpWeixinUserServiceImpl extends MybatisServiceImpl<UpWeixinUserMapper, UpWeixinUser> implements UpWeixinUserService {
	
}
