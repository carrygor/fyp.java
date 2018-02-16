package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionItem;
import cn.com.youplus.apps.dao.auto.UpAppsQuestionItemMapper;
import cn.com.youplus.apps.api.auto.UpAppsQuestionItemService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsQuestionItemService")
public class UpAppsQuestionItemServiceImpl extends MybatisServiceImpl<UpAppsQuestionItemMapper, UpAppsQuestionItem> implements UpAppsQuestionItemService {
	
}
