package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaireTheme;
import cn.com.youplus.apps.dao.auto.UpAppsQuestionnaireThemeMapper;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireThemeService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsQuestionnaireThemeService")
public class UpAppsQuestionnaireThemeServiceImpl extends MybatisServiceImpl<UpAppsQuestionnaireThemeMapper, UpAppsQuestionnaireTheme> implements UpAppsQuestionnaireThemeService {
	
}
