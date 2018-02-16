package cn.com.youplus.tenants.test;

import cn.com.youplus.tenants.api.common.ExportTaskService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    @Autowired
    private ExportTaskService exportTaskService;


    @org.junit.Test
    public void testExport() throws Exception {
        //940130732104327169L 友谊
        //940760726052569090L 中华
        // new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-25")
        exportTaskService.exportDataToExcelWithNewThread(940130732104327169L, null,null, null, null);

        Thread.sleep(1000 * 1000);
    }

}
