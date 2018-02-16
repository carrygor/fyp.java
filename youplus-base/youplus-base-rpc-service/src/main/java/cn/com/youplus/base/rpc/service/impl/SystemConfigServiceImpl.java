package cn.com.youplus.base.rpc.service.impl;


import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.base.api.auto.UpBaseSystemConfigService;
import cn.com.youplus.common.exception.data.InvalidParameterException;
import cn.com.youplus.common.model.base.SystemConfigVersionReference;
import cn.com.youplus.common.model.enums.SystemConfigParamsTypeEnum;
import cn.com.youplus.model.auto.entity.base.UpBaseSystemConfig;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/27.
 */

@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    private final static Logger logger = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

    private SystemConfigVersionReference ref = SystemConfigVersionReference.getInstance();

    //region 系统参数配置相关
    @Autowired
    private UpBaseSystemConfigService upBaseSystemConfigService;

    Map<String, String> cmsParams;
    Map<String, String> appsParams;
    Map<String, String> tenantsParams;
    Map<String, String> thirdpartyParams;

    public Map<String, String> getSystemParams(SystemConfigParamsTypeEnum typeEnum) throws Exception {
        long localRef = 0;
        long systemRef = 0;

        switch (typeEnum) {
            case 用户参数:
                localRef = ref.getAppsSystemConfigLocalRef();
                systemRef = ref.getAppsSystemConfigSystemRef();
                break;
            case 系统参数:
                localRef = ref.getCmsSystemConfigLocalRef();
                systemRef = ref.getCmsSystemConfigSystemRef();
                break;
            case 网点参数:
                localRef = ref.getTenantsSystemConfigLocalRef();
                systemRef = ref.getTenantsSystemConfigSystemRef();
                break;
            case 第三方参数:
                localRef = ref.getThirdpartySystemConfigLocalRef();
                systemRef = ref.getThirdpartySystemConfigSystemRef();
                break;
            default:
                throw new InvalidParameterException("不支持的配置类型");
        }

        if (localRef == systemRef) {
            switch (typeEnum) {
                case 用户参数:
                    return appsParams;
                case 系统参数:
                    return cmsParams;
                case 网点参数:
                    return tenantsParams;
                case 第三方参数:
                    return thirdpartyParams;
                default:
                    throw new InvalidParameterException("不支持的配置类型");
            }
        } else {
            synchronized (this) {
                List<UpBaseSystemConfig> listData = upBaseSystemConfigService.selectList(
                        new EntityWrapper<UpBaseSystemConfig>()
                                .eq(UpBaseSystemConfig.PARAMETER_TYPE, typeEnum.getType())
                                .orderBy(UpBaseSystemConfig.SORT)
                );
                Map<String, String> map = new HashMap<>();
                for (UpBaseSystemConfig config: listData) {
                    map.put(config.getAttributeKey(), config.getAttributeValue());
                }
                switch (typeEnum) {
                    case 用户参数:
                        appsParams = map;
                        ref.setAppsSystemConfigLocalRef(ref.getAppsSystemConfigSystemRef());
                        return map;
                    case 系统参数:
                        cmsParams = map;
                        ref.setCmsSystemConfigLocalRef(ref.getCmsSystemConfigSystemRef());
                        return map;
                    case 网点参数:
                        tenantsParams = map;
                        ref.setTenantsSystemConfigLocalRef(ref.getTenantsSystemConfigSystemRef());
                        return map;
                    case 第三方参数:
                        thirdpartyParams = map;
                        ref.setThirdpartySystemConfigLocalRef(ref.getThirdpartySystemConfigSystemRef());
                        return map;
                    default:
                        throw new InvalidParameterException("不支持的配置类型");
                }
            }
        }
    }

    @Override
    public String getCmsParam(String key) throws Exception {
        Map<String, String> map = getSystemParams(SystemConfigParamsTypeEnum.系统参数);
        return map.get(key);
    }

    @Override
    public String getAppsParam(String key) throws Exception {
        Map<String, String> map = getSystemParams(SystemConfigParamsTypeEnum.用户参数);
        return map.get(key);
    }

    @Override
    public String getTenantsParam(String key) throws Exception {
        Map<String, String> map = getSystemParams(SystemConfigParamsTypeEnum.网点参数);
        return map.get(key);
    }

    @Override
    public String getThirdpartyParam(String key) throws Exception {
        Map<String, String> map = getSystemParams(SystemConfigParamsTypeEnum.第三方参数);
        return map.get(key);
    }

    //endregion
}
