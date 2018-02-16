package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestion;
import cn.com.youplus.apps.dao.auto.UpAppsQuestionMapper;
import cn.com.youplus.apps.api.auto.UpAppsQuestionService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsQuestionService")
public class UpAppsQuestionServiceImpl extends MybatisServiceImpl<UpAppsQuestionMapper, UpAppsQuestion> implements UpAppsQuestionService {
	
}
