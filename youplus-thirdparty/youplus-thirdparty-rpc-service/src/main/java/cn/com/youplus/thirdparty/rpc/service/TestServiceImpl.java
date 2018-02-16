package cn.com.youplus.thirdparty.rpc.service;

import cn.com.youplus.model.auto.entity.thirdparty.UpTpRequestLog;
import cn.com.youplus.thirdparty.api.TestService;
import cn.com.youplus.thirdparty.api.auto.UpTpRequestLogService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 严汉羽 on 2017/10/13.
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    private static Logger logger = Logger.getLogger(TestServiceImpl.class);

    @Autowired
    private UpTpRequestLogService requestLogService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UpTpRequestLog testTrainstional(int i) throws Exception {
        UpTpRequestLog requestLog = new UpTpRequestLog();
        requestLog.setCid("cid" + i).setResponseContent("111111").setRequestParameter("1111111");
        requestLogService.insert(requestLog);
        throw new Exception("haha");
    }

    @Override
    public UpTpRequestLog testPage(int i, int j) {
        UpTpRequestLog requestLog = requestLogService.selectById(98);

        Page<UpTpRequestLog> page = requestLogService.selectPage(new Page<UpTpRequestLog>(i,j),
                new EntityWrapper<UpTpRequestLog>().like(UpTpRequestLog.CID, "cid%").orderBy(UpTpRequestLog.CID));
        logger.debug(page.toString());
        logger.debug(JSONObject.toJSONString(page));
        return requestLog;
    }

    @Override
    public UpTpRequestLog testVersion(int i) {
        UpTpRequestLog requestLog = new UpTpRequestLog();
        if(requestLogService.updateById(requestLog)){
            System.out.println("Update successfully");
        }else{
            System.out.println("Update failed due to modified by others");
        }
        return requestLog;
    }

    @Override
    public UpTpRequestLog testDelete(long i) {
        UpTpRequestLog requestLog = new UpTpRequestLog();

        if(requestLogService.deleteById(i)){
            System.out.println("Update successfully");
        }else{
            System.out.println("Update failed due to modified by others");
        }
        return requestLog;
    }
}
