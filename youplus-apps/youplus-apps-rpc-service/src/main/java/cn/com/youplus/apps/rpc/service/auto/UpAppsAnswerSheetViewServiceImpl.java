package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetView;
import cn.com.youplus.apps.dao.auto.UpAppsAnswerSheetViewMapper;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetViewService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsAnswerSheetViewService")
public class UpAppsAnswerSheetViewServiceImpl extends MybatisServiceImpl<UpAppsAnswerSheetViewMapper, UpAppsAnswerSheetView> implements UpAppsAnswerSheetViewService {
	
}
