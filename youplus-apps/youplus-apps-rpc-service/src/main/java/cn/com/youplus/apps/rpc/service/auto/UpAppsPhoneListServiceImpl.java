package cn.com.youplus.apps.rpc.service.auto;

import cn.com.youplus.model.auto.entity.apps.UpAppsPhoneList;
import cn.com.youplus.apps.dao.auto.UpAppsPhoneListMapper;
import cn.com.youplus.apps.api.auto.UpAppsPhoneListService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upAppsPhoneListService")
public class UpAppsPhoneListServiceImpl extends MybatisServiceImpl<UpAppsPhoneListMapper, UpAppsPhoneList> implements UpAppsPhoneListService {
	
}
