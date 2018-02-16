package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheet;
import cn.com.youplus.apps.dao.auto.UpAppsAnswerSheetMapper;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsAnswerSheetService")
public class UpAppsAnswerSheetServiceImpl extends MybatisServiceImpl<UpAppsAnswerSheetMapper, UpAppsAnswerSheet> implements UpAppsAnswerSheetService {
	
}
