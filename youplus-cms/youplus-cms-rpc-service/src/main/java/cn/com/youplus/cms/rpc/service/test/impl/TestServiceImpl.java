package cn.com.youplus.cms.rpc.service.test.impl;

import cn.com.youplus.cms.api.test.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by 严汉羽 on 2017/10/15.
 */

@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    public String test(String str) {
        return "测试数据:" + str;
    }
}
