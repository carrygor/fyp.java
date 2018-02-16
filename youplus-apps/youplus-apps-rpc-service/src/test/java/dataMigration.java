import cn.com.youplus.apps.api.common.AnswerSheetService;
import cn.com.youplus.common.model.tablestore.*;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 严汉羽 on 2018/1/26.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class dataMigration {

    @Autowired
    private AnswerSheetService answerSheetService;

    @Resource(name = "tableStoreService")
    private TableStoreServiceImpl tableStoreService;

    @Test
    public void testCreateTable() throws Exception {
        tableStoreService.createTable(TsAnswerSheet.class);
        tableStoreService.createTable(TsAnswerSheetPhone.class);
        tableStoreService.createTable(TsAnswerSheetAccessLog.class);
        tableStoreService.createTable(TsAnswerSheetIndex.class);
        tableStoreService.createTable(TsAnswerSheetCookies.class);
        tableStoreService.createTable(TsAnswerSheetOpenid.class);
        tableStoreService.createTable(TsStatRegionAll.class);
        tableStoreService.createTable(TsStatRegionPerDay.class);
        tableStoreService.createTable(TsStatRegionPerHour.class);
        tableStoreService.createTable(TsStatRegionPerHourAll.class);
        tableStoreService.createTable(TsStatRegionQuestionAll.class);
        tableStoreService.createTable(TsStatRegionQuestionPerDay.class);
    }

    @Test
    public void transferData() throws Exception {
        answerSheetService.transferData();
    }
}
