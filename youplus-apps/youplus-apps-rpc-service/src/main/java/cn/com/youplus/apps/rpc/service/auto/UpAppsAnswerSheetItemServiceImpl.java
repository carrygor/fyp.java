package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;
import cn.com.youplus.apps.dao.auto.UpAppsAnswerSheetItemMapper;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetItemService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsAnswerSheetItemService")
public class UpAppsAnswerSheetItemServiceImpl extends MybatisServiceImpl<UpAppsAnswerSheetItemMapper, UpAppsAnswerSheetItem> implements UpAppsAnswerSheetItemService {
	
}
