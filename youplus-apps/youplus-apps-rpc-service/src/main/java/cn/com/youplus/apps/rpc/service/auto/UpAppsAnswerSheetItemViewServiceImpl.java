package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItemView;
import cn.com.youplus.apps.dao.auto.UpAppsAnswerSheetItemViewMapper;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetItemViewService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsAnswerSheetItemViewService")
public class UpAppsAnswerSheetItemViewServiceImpl extends MybatisServiceImpl<UpAppsAnswerSheetItemViewMapper, UpAppsAnswerSheetItemView> implements UpAppsAnswerSheetItemViewService {
	
}
