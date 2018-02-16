package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetDetailView;
import cn.com.youplus.apps.dao.auto.UpAppsAnswerSheetDetailViewMapper;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetDetailViewService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsAnswerSheetDetailViewService")
public class UpAppsAnswerSheetDetailViewServiceImpl extends MybatisServiceImpl<UpAppsAnswerSheetDetailViewMapper, UpAppsAnswerSheetDetailView> implements UpAppsAnswerSheetDetailViewService {
	
}
