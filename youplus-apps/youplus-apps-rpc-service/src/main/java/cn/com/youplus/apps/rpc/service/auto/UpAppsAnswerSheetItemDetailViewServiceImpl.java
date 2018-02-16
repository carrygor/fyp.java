package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItemDetailView;
import cn.com.youplus.apps.dao.auto.UpAppsAnswerSheetItemDetailViewMapper;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetItemDetailViewService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsAnswerSheetItemDetailViewService")
public class UpAppsAnswerSheetItemDetailViewServiceImpl extends MybatisServiceImpl<UpAppsAnswerSheetItemDetailViewMapper, UpAppsAnswerSheetItemDetailView> implements UpAppsAnswerSheetItemDetailViewService {
	
}
