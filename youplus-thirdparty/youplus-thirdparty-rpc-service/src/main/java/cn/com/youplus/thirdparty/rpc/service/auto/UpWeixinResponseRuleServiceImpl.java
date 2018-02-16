package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinResponseRule;
import cn.com.youplus.thirdparty.dao.auto.UpWeixinResponseRuleMapper;
import cn.com.youplus.thirdparty.api.auto.UpWeixinResponseRuleService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upWeixinResponseRuleService")
public class UpWeixinResponseRuleServiceImpl extends MybatisServiceImpl<UpWeixinResponseRuleMapper, UpWeixinResponseRule> implements UpWeixinResponseRuleService {
	
}
