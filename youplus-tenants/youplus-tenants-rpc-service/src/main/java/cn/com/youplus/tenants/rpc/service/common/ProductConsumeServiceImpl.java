package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.base.api.LogHubService;
import cn.com.youplus.common.model.log.AnswerSheetRecordLog;
import cn.com.youplus.common.model.log.AnswerSheetRequestLog;
import cn.com.youplus.common.model.log.QuestionStatLog;
import cn.com.youplus.common.model.tablestore.TsAnswerSheet;
import cn.com.youplus.common.model.tablestore.TsAnswerSheetAccessLog;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.common.ProductConsumeService;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


@Service("productConsumeService")
public class ProductConsumeServiceImpl implements ProductConsumeService {

    //region autowire

    private static Logger logger = Logger.getLogger(ProductConsumeServiceImpl.class);

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private LogHubService logHubService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private UpAppsAnswerSheetService upAppsAnswerSheetService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private TableStoreServiceImpl tableStoreService;

    @Autowired
    private SyncClient syncClient;

    //endregion

    public class Storage {

        // 仓库最大存储量
        private final int MAX_SIZE = 100000;

        private boolean finishProduce = false;

        // 仓库存储的载体
        private LinkedBlockingQueue<TsAnswerSheet> list = new LinkedBlockingQueue<>();

        private LinkedBlockingQueue<TsAnswerSheetAccessLog> accessLogList = new LinkedBlockingQueue<>();


        // 生产num个产品
        public void produce(int num) {
            // 如果仓库剩余容量不足
            while (list.size() + num > MAX_SIZE) {
                System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:"
                        + list.size() + "/t暂时不能执行生产任务!");
            }

            // 生产条件满足情况下，生产num个产品
            for (int i = 1; i <= num; ++i) {
                try {
                    list.put(new TsAnswerSheet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());
        }

        public void put(TsAnswerSheet tsAnswerSheet) {
            try {
                list.put(tsAnswerSheet);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void putAccessLog(TsAnswerSheetAccessLog tsAnswerSheetAccessLog) {
            try {
                accessLogList.put(tsAnswerSheetAccessLog);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 消费num个产品
        public void consume() {

            // 消费条件满足情况下，消费num个产品
            while (true) {
                if (finishProduce && list.size() == 0) {
                    logger.info("消费完成");
                    break;
                }
                try {
                    TsAnswerSheet tsAnswerSheet = list.take();

                    AnswerSheetRecordLog answerSheetRecordLog = new AnswerSheetRecordLog();
                    answerSheetRecordLog.setAnswerItems(JSONObject.toJSONString(tsAnswerSheet.getItemList()));
                    answerSheetRecordLog.setIp(tsAnswerSheet.getIp());
                    answerSheetRecordLog.setSystem(tsAnswerSheet.getSystem());
                    answerSheetRecordLog.setBroswer(tsAnswerSheet.getBroswer());
                    Double answerTimes = (tsAnswerSheet.getFinishTime().getTime() - tsAnswerSheet.getStartTime().getTime()) / 1000D;
                    answerSheetRecordLog.setAnswerTime(answerTimes);
                    answerSheetRecordLog.setAddTime(tsAnswerSheet.getAddTime());
                    answerSheetRecordLog.setQuestionnaireId(tsAnswerSheet.getQuestionnaireId());
                    answerSheetRecordLog.setQuickTag(tsAnswerSheet.getQuickTag());
                    answerSheetRecordLog.setRegionId(tsAnswerSheet.getTenantsRegionId());
                    answerSheetRecordLog.setCookies(tsAnswerSheet.getCookies());
                    answerSheetRecordLog.setOpenid(tsAnswerSheet.getOpenid());
                    answerSheetRecordLog.setFinishQuestionNum(tsAnswerSheet.getFinishQuestionNum());
                    answerSheetRecordLog.setAccessLogId(tsAnswerSheet.getAccessLogId());
                    answerSheetRecordLog.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
                    logHubService.putLog(answerSheetRecordLog, tsAnswerSheet.getFinishTime());

                    AnswerSheetRequestLog answerSheetRequestLog = new AnswerSheetRequestLog();
//                    answerSheetRequestLog.setQuickTag(tsAnswerSheetAccessLog);
                    answerSheetRequestLog.setCookies(tsAnswerSheet.getCookies());
                    answerSheetRequestLog.setSystem(tsAnswerSheet.getSystem());
                    answerSheetRequestLog.setBroswer(tsAnswerSheet.getBroswer());
                    answerSheetRequestLog.setQuestionnaireId(tsAnswerSheet.getQuestionnaireId());
                    answerSheetRequestLog.setRegionId(tsAnswerSheet.getTenantsRegionId());
                    answerSheetRequestLog.setIp(tsAnswerSheet.getIp());
                    answerSheetRequestLog.setAccessLogId(tsAnswerSheet.getAccessLogId());
                    answerSheetRequestLog.setAddTime(tsAnswerSheet.getStartTime());
                    logHubService.putLog(answerSheetRequestLog, tsAnswerSheet.getFinishTime());

                    List<UpAppsAnswerSheetItem> answerSheetItemList = tsAnswerSheet.getItemList();
                    for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
                        QuestionStatLog questionStatLog = new QuestionStatLog();
                        questionStatLog.setValue(answerSheetItem.getValue());
                        questionStatLog.setScore(answerSheetItem.getScore());
                        questionStatLog.setRegionId(tsAnswerSheet.getTenantsRegionId());
                        questionStatLog.setQuickTag(tsAnswerSheet.getQuickTag());
                        questionStatLog.setQuestionType(answerSheetItem.getQuestionType());
                        questionStatLog.setQuestionnaireId(tsAnswerSheet.getQuestionnaireId());
                        questionStatLog.setQuestionItemId(answerSheetItem.getQuestionItemId());
                        questionStatLog.setQuestionId(answerSheetItem.getQuestionId());
                        questionStatLog.setInputContent(answerSheetItem.getInputContent());
                        questionStatLog.setAddTime(answerSheetItem.getAddTime());
                        questionStatLog.setAnswerSheetId(answerSheetItem.getAnswerSheetId());
                        logHubService.putLog(questionStatLog, tsAnswerSheet.getFinishTime());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        public void consumeAccessLog() {

            // 消费条件满足情况下，消费num个产品
            while (true) {
                if (finishProduce && accessLogList.size() == 0) {
                    logger.info("消费完成");
                    break;
                }
                try {
                    TsAnswerSheetAccessLog tsAnswerSheetAccessLog = accessLogList.take();

                    AnswerSheetRequestLog answerSheetRequestLog = new AnswerSheetRequestLog();
//                    answerSheetRequestLog.setQuickTag(tsAnswerSheetAccessLog);
                    answerSheetRequestLog.setCookies(tsAnswerSheetAccessLog.getCookies());
                    answerSheetRequestLog.setSystem(tsAnswerSheetAccessLog.getSystem());
                    answerSheetRequestLog.setBroswer(tsAnswerSheetAccessLog.getBrowser());
                    answerSheetRequestLog.setQuestionnaireId(tsAnswerSheetAccessLog.getQuestionnaireId());
                    answerSheetRequestLog.setRegionId(tsAnswerSheetAccessLog.getRegionId());
                    answerSheetRequestLog.setIp(tsAnswerSheetAccessLog.getIp());
                    answerSheetRequestLog.setAccessLogId(tsAnswerSheetAccessLog.getAccessLogId());
                    answerSheetRequestLog.setAddTime(tsAnswerSheetAccessLog.getStartTime());
                    logHubService.putLog(answerSheetRequestLog, tsAnswerSheetAccessLog.getStartTime());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        public boolean isFinishProduce() {
            return finishProduce;
        }

        public Storage setFinishProduce(boolean finishProduce) {
            this.finishProduce = finishProduce;
            return this;
        }

        public int getMAX_SIZE() {
            return MAX_SIZE;
        }
    }

    public class Producer extends Thread {
        // 每次生产的产品数量
        private int num;

        // 所在放置的仓库
        private Storage storage;

        // 构造函数，设置仓库
        public Producer(Storage storage) {
            this.storage = storage;
        }

        // 线程run函数
        public void run() {
            produceTsAnswerSheet();
        }

        // 调用仓库Storage的生产函数
        public void produce(int num) {
            storage.produce(num);
        }

        public void produceTsAnswerSheet() {
            RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria("ts_answer_sheet");
            // 设置起始主键
            PrimaryKeyBuilder primaryKeyBuilderStart = PrimaryKeyBuilder.createPrimaryKeyBuilder();
            primaryKeyBuilderStart.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MIN);
            primaryKeyBuilderStart.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(955627332705906690L));
            rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilderStart.build());
            // 设置结束主键955627332705906690
            PrimaryKeyBuilder primaryKeyBuilderEnd = PrimaryKeyBuilder.createPrimaryKeyBuilder();
            primaryKeyBuilderEnd.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MAX);
            primaryKeyBuilderEnd.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(955627332705906690L));
            rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilderEnd.build());
            rangeRowQueryCriteria.setMaxVersions(1);
            rangeRowQueryCriteria.setDirection(Direction.FORWARD);
            rangeRowQueryCriteria.setLimit(400);

            logger.info("GetRange的结果为:");
            while (true) {
                GetRangeResponse getRangeResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
                for (Row row : getRangeResponse.getRows()) {
                    try {
                        TsAnswerSheet tsAnswerSheet = new TsAnswerSheet();
                        tableStoreService.row2Data(row, tsAnswerSheet);
                        storage.put(tsAnswerSheet);
                    } catch (Exception e) {
                        logger.debug("处理异常:", e);
                    }
                }
                // 若nextStartPrimaryKey不为null, 则继续读取.
                if (getRangeResponse.getNextStartPrimaryKey() != null) {
                    rangeRowQueryCriteria.setInclusiveStartPrimaryKey(getRangeResponse.getNextStartPrimaryKey());
                } else {
                    break;
                }
            }
            storage.setFinishProduce(true);
        }

        // get/set方法
        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Storage getStorage() {
            return storage;
        }

        public void setStorage(Storage storage) {
            this.storage = storage;
        }
    }

    public class RequestLogProducer extends Thread {
        // 每次生产的产品数量
        private int num;

        // 所在放置的仓库
        private Storage storage;

        // 构造函数，设置仓库
        public RequestLogProducer(Storage storage) {
            this.storage = storage;
        }

        // 线程run函数
        public void run() {
            produceTsRequestLog();
        }

        // 调用仓库Storage的生产函数
        public void produce(int num) {
            storage.produce(num);
        }

        public void produceTsRequestLog() {
            RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria("ts_answer_sheet_access_log");
            // 设置起始主键
            PrimaryKeyBuilder primaryKeyBuilderStart = PrimaryKeyBuilder.createPrimaryKeyBuilder();
            primaryKeyBuilderStart.addPrimaryKeyColumn("accessLogId", PrimaryKeyValue.INF_MIN);
            primaryKeyBuilderStart.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(955627332705906690L));
            rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilderStart.build());
            // 设置结束主键955627332705906690
            PrimaryKeyBuilder primaryKeyBuilderEnd = PrimaryKeyBuilder.createPrimaryKeyBuilder();
            primaryKeyBuilderEnd.addPrimaryKeyColumn("accessLogId", PrimaryKeyValue.INF_MAX);
            primaryKeyBuilderEnd.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(955627332705906690L));
            rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilderEnd.build());
            rangeRowQueryCriteria.setMaxVersions(1);
            rangeRowQueryCriteria.setDirection(Direction.FORWARD);
            rangeRowQueryCriteria.setLimit(400);

            logger.info("GetRange的结果为:");
            while (true) {
                GetRangeResponse getRangeResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
                for (Row row : getRangeResponse.getRows()) {
                    try {
                        TsAnswerSheetAccessLog tsAnswerSheetAccessLog = new TsAnswerSheetAccessLog();
                        tableStoreService.row2Data(row, tsAnswerSheetAccessLog);
                        storage.putAccessLog(tsAnswerSheetAccessLog);
                    } catch (Exception e) {
                        logger.debug("处理异常:", e);
                    }
                }
                // 若nextStartPrimaryKey不为null, 则继续读取.
                if (getRangeResponse.getNextStartPrimaryKey() != null) {
                    rangeRowQueryCriteria.setInclusiveStartPrimaryKey(getRangeResponse.getNextStartPrimaryKey());
                } else {
                    break;
                }
            }

            storage.setFinishProduce(true);
        }

        // get/set方法
        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Storage getStorage() {
            return storage;
        }

        public void setStorage(Storage storage) {
            this.storage = storage;
        }
    }

    public class Consumer extends Thread {

        // 所在放置的仓库
        private Storage storage;

        // 构造函数，设置仓库
        public Consumer(Storage storage) {
            this.storage = storage;
        }

        // 线程run函数
        public void run() {
            consume();
        }

        // 调用仓库Storage的生产函数
        public void consume() {
            storage.consume();
        }

        public Storage getStorage() {
            return storage;
        }

        public void setStorage(Storage storage) {
            this.storage = storage;
        }
    }

    public class RequestLogConsumer extends Thread {

        // 所在放置的仓库
        private Storage storage;

        // 构造函数，设置仓库
        public RequestLogConsumer(Storage storage) {
            this.storage = storage;
        }

        // 线程run函数
        public void run() {
            consume();
        }

        // 调用仓库Storage的生产函数
        public void consume() {
            storage.consumeAccessLog();
        }

        public Storage getStorage() {
            return storage;
        }

        public void setStorage(Storage storage) {
            this.storage = storage;
        }
    }

    @Override
    public void transferToLogService() {
        Storage storage = new Storage();
        Producer p1 = new Producer(storage);
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);
        Consumer c4 = new Consumer(storage);
        Consumer c5 = new Consumer(storage);
        Consumer c6 = new Consumer(storage);
        Consumer c7 = new Consumer(storage);
        Consumer c8 = new Consumer(storage);
        Consumer c9 = new Consumer(storage);
        Consumer c10 = new Consumer(storage);

        p1.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
        c9.start();
        c10.start();
    }

    @Override
    public void transferRequestLog() {
        Storage storage = new Storage();

        RequestLogProducer p = new RequestLogProducer(storage);
        p.start();
        for (int i = 0; i < 10; i++) {
            RequestLogConsumer c = new RequestLogConsumer(storage);
            c.start();
        }
    }

}