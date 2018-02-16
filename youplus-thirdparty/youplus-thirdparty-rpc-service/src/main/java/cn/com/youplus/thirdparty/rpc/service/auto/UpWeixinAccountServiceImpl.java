package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinAccount;
import cn.com.youplus.thirdparty.dao.auto.UpWeixinAccountMapper;
import cn.com.youplus.thirdparty.api.auto.UpWeixinAccountService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upWeixinAccountService")
public class UpWeixinAccountServiceImpl extends MybatisServiceImpl<UpWeixinAccountMapper, UpWeixinAccount> implements UpWeixinAccountService {
	
}
