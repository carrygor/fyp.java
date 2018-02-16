package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.apps.dao.auto.UpAppsQuestionnaireMapper;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsQuestionnaireService")
public class UpAppsQuestionnaireServiceImpl extends MybatisServiceImpl<UpAppsQuestionnaireMapper, UpAppsQuestionnaire> implements UpAppsQuestionnaireService {
	
}
