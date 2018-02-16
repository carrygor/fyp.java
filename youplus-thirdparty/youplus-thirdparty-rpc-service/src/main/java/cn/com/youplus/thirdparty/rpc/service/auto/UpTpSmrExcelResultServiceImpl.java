package cn.com.youplus.thirdparty.rpc.service.auto;

import cn.com.youplus.model.auto.entity.thirdparty.UpTpSmrExcelResult;
import cn.com.youplus.thirdparty.dao.auto.UpTpSmrExcelResultMapper;
import cn.com.youplus.thirdparty.api.auto.UpTpSmrExcelResultService;
import cn.com.youplus.common.util.base.MybatisServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 严汉羽
 */
@Service("upTpSmrExcelResultService")
public class UpTpSmrExcelResultServiceImpl extends MybatisServiceImpl<UpTpSmrExcelResultMapper, UpTpSmrExcelResult> implements UpTpSmrExcelResultService {
	
}
