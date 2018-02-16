package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaireAttribute;
import cn.com.youplus.apps.dao.auto.UpAppsQuestionnaireAttributeMapper;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireAttributeService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问卷属性 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsQuestionnaireAttributeService")
public class UpAppsQuestionnaireAttributeServiceImpl extends MybatisServiceImpl<UpAppsQuestionnaireAttributeMapper, UpAppsQuestionnaireAttribute> implements UpAppsQuestionnaireAttributeService {
	
}
