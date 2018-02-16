package cn.com.youplus.thirdparty.api;

import cn.com.youplus.model.auto.entity.thirdparty.UpTpRequestLog;

/**
 * Created by 严汉羽 on 2017/10/13.
 */
public interface TestService {
    UpTpRequestLog testTrainstional(int i) throws Exception;

    UpTpRequestLog testPage(int i, int j);

    UpTpRequestLog testVersion(int i);

    UpTpRequestLog testDelete(long i);
}
