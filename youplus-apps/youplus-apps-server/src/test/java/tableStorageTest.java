
import cn.com.youplus.common.model.tablestore.*;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/applicationContext.xml"})
public class tableStorageTest {

    private static Logger logger = Logger.getLogger(tableStorageTest.class);

    @Resource(name = "tableStoreService")
    private TableStoreServiceImpl tableStoreService;

    @Before
    public void before() {

    }


    @Test
    public void testCreateTable() throws Exception {
//        tableStoreService.createTable(TsAnswerSheet.class);
//        tableStoreService.createTable(TsAnswerSheetPhone.class);
//        tableStoreService.createTable(TsAnswerSheetCookies.class);
//        tableStoreService.createTable(TsAnswerSheetOpenid.class);
//        tableStoreService.createTable(TsStatRegionAll.class);
//        tableStoreService.createTable(TsStatRegionPerDay.class);
//        tableStoreService.createTable(TsStatRegionPerHour.class);
//        tableStoreService.createTable(TsStatRegionPerHourAll.class);
        tableStoreService.createTable(TsStatRegionQuestionAll.class);
        tableStoreService.createTable(TsStatRegionQuestionPerDay.class);
    }

    @Test
    public void putRow() throws Exception {
        UserAction action = new UserAction();
        action.setActionType(100);
        action.setHashUserId(new Date().getTime() + "");
        action.setUserId(200);
        action.setTimes(100);
        tableStoreService.putRow(action);

        UserAction action1 = new UserAction();
        action1.setHashUserId(action.getHashUserId());
        action1.setUserId(200);

        tableStoreService.getRow(action1);
        logger.info(action1.toString());

        action.setTimes(1000);
        action.setActionType(1001);
        tableStoreService.updateRow(action);

        tableStoreService.getRow(action1);
        logger.info(action1.toString());

        tableStoreService.deleteRow(action1);

        boolean re = tableStoreService.getRow(action1);
        logger.info(re);
        logger.info(action1.toString());

        logger.info(action1.toString());

    }



    @Test
    public void getRow() throws Exception {
        UserAction action = new UserAction();
        action.setHashUserId(new Date().getTime() + "");
        action.setUserId(200);
        tableStoreService.getRow(action);
        logger.info(action.toString());
    }

    @Test
    public void batchGetRow() {

    }

    @Test
    public void updateRow() {

    }


}
