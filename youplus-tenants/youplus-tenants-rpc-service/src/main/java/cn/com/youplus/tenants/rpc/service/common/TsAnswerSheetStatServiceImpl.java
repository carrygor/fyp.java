package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.UpAppsQuestionItemService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.tablestore.*;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.SnowFlake;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestion;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionItem;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsTask;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsTaskService;
import cn.com.youplus.tenants.api.common.TsAnswerSheetStatService;
import cn.com.youplus.tenants.common.model.enums.TaskStatusEnum;
import cn.com.youplus.tenants.common.model.enums.TaskTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 严汉羽 on 2018/1/11.
 */
@Service("tsAnswerSheetStatService")
public class TsAnswerSheetStatServiceImpl implements TsAnswerSheetStatService {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TsAnswerSheetStatServiceImpl.class);

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private UpTenantsTaskService upTenantsTaskService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private SyncClient syncClient;

    @Resource(name = "tableStoreService")
    private TableStoreServiceImpl tableStoreService;

    //region 问卷统计任务

    private Map<String, Object> regionMap;

    /**
     * 任务线程
     */
    public class StatJob implements Runnable {

        private Map<String, TsBaseData> dataMap;//key: questionnaireId + regionId(quickTag)
        private Map<String, TsBaseQuestionData> questionDataMap;//key: questionnaireId + regionId(quickTag) + questionId

        private Map<Long, UpAppsQuestionnaire> questionnaireMap;
        private Map<Long, UpAppsQuestion> questionMap;
        private Map<String, Long> quickTagMap;
        private Date time;

        public StatJob() {
            time = new Date();
            dataMap = new HashMap<>();
            questionDataMap = new HashMap<>();
            questionnaireMap = new HashMap<>();
            questionMap = new HashMap<>();
            quickTagMap = new HashMap<>();
            List<UpAppsQuestionnaire> questionnaireList = upAppsQuestionnaireService.selectList(
                    new EntityWrapper<UpAppsQuestionnaire>()
                            .eq(UpAppsQuestionnaire.STATUS, QuestionnaireStatusEnum.收集中.getType())
            );
            List<Long> ids = new ArrayList<>();
            for (UpAppsQuestionnaire questionnaire : questionnaireList) {
                ids.add(questionnaire.getId());
                questionnaireMap.put(questionnaire.getId(), questionnaire);
            }

            List<UpAppsQuestion> questionList = upAppsQuestionService.selectList(
                    new EntityWrapper<UpAppsQuestion>()
                            .in(UpAppsQuestion.QUESTIONNAIRE_ID, ids)
            );
            for (UpAppsQuestion question : questionList) {
                questionMap.put(question.getId(), question);
            }

            List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
                    new EntityWrapper<UpTenantsRegion>()
            );
            for (UpTenantsRegion region : regionList) {
                quickTagMap.put(region.getQuickTag(), region.getId());
            }
        }

        @Override
        public void run() {
            //Step1. 确定时间区间：
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            calendar.add(Calendar.HOUR, -1);
            calendar.add(Calendar.MINUTE, 0 - now.getMinutes());
            calendar.add(Calendar.SECOND, 0 - now.getSeconds());

            Date start = calendar.getTime();
            calendar.add(Calendar.HOUR, 1);
            Date end = calendar.getTime();

            logger.info("扫描即将开始,开始时间:" + new Date());
            StringBuilder builder = new StringBuilder();
            builder.append("扫描区间:");
            builder.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start));
            builder.append(" - ");
            builder.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end));
            logger.info(builder.toString());

            long startId = SnowFlake.nextId(start.getTime());
            long endId = SnowFlake.nextId(end.getTime());
            logger.info("Id 范围: " + startId + " - " + endId);

            UpTenantsTask task = new UpTenantsTask();
            task.setAddTime(new Date())
                    .setUpdateTime(new Date())
                    .setQuestionnaireId(0L)
                    .setQuickTag(builder.toString())
                    .setRegionFilter("")
                    .setStatus(TaskStatusEnum.进行中.getType())
                    .setTaskType(TaskTypeEnum.数据统计运算.getType());

            List<Map<String, Object>> messages = new ArrayList<>();
            addMessage(messages, "任务创建成功");
            try {
                lastHourStatHandler(startId, endId);
                addMessage(messages, "成功完成");
                task.setStatus(TaskStatusEnum.已完成.getType());
            } catch (Exception e) {
                logger.error("数据统计异常", e);
                if (e instanceof AlertException) {
                    addMessage(messages, e.getMessage());
                } else {
                    addMessage(messages, "处理异常:" + e.getMessage());
                }
                task.setStatus(TaskStatusEnum.意外终止.getType());
            }

            saveTask(task, messages);
        }

        private void lastHourStatHandler(long startId, long endId) throws CloneNotSupportedException {
            //准备开始扫描表：
            batchGetRow(startId, endId);
            if (this.dataMap.isEmpty()) {
                return;
            }
            getParentData();
            handleAndSaveData();
        }

        private void handleAndSaveData() {
            for (String key : dataMap.keySet()) {
                try {
                    TsBaseData tsBaseData = dataMap.get(key);
                    String[] strArr = key.split("\\+");
                    Long questionnaireId = Long.parseLong(strArr[0]);
                    String regionIdStr = strArr[1];
                    if (key.contains(",")) {// 有逗号说明是网点的数据（quickTag）
                        regionIdStr = quickTagMap.get(regionIdStr).toString();
                    }
                    Long regionId = Long.parseLong(regionIdStr);

                    //每小时数据
                    TsStatRegionPerHour tsStatRegionPerHour = new TsStatRegionPerHour();
                    tsStatRegionPerHour.setHours(getHours());
                    tsStatRegionPerHour.setQuestionnaireId(questionnaireId);
                    tsStatRegionPerHour.setRegionId(regionId);
                    mergeBaseData(tsBaseData, tsStatRegionPerHour);
                    tableStoreService.putRow(tsStatRegionPerHour);

                    //每天数据
                    TsStatRegionPerDay tsStatRegionPerDay = new TsStatRegionPerDay();
                    tsStatRegionPerDay.setDays(getDays());
                    tsStatRegionPerDay.setQuestionnaireId(questionnaireId);
                    tsStatRegionPerDay.setRegionId(regionId);
                    boolean hasDay = tableStoreService.getRow(tsStatRegionPerDay);
                    mergeBaseData(tsBaseData, tsStatRegionPerDay);
                    if (hasDay) {
                        tableStoreService.updateRow(tsStatRegionPerDay);
                    } else {
                        tableStoreService.putRow(tsStatRegionPerDay);
                    }

                    //每个时间段数据
                    TsStatRegionPerHourAll tsStatRegionPerHourAll = new TsStatRegionPerHourAll();
                    tsStatRegionPerHourAll.setQuestionnaireId(questionnaireId);
                    tsStatRegionPerHourAll.setRegionId(regionId);
                    tsStatRegionPerHourAll.setTime(getHourTime());
                    boolean hasHourTime = tableStoreService.getRow(tsStatRegionPerHourAll);
                    mergeBaseData(tsBaseData, tsStatRegionPerHourAll);
                    if (hasHourTime) {
                        tableStoreService.updateRow(tsStatRegionPerHourAll);
                    } else {
                        tableStoreService.putRow(tsStatRegionPerHourAll);
                    }

                    //总数据
                    TsStatRegionAll tsStatRegionAll = new TsStatRegionAll();
                    tsStatRegionAll.setQuestionnaireId(questionnaireId);
                    tsStatRegionAll.setRegionId(regionId);
                    boolean hasAll = tableStoreService.getRow(tsStatRegionAll);
                    mergeBaseData(tsBaseData, tsStatRegionAll);
                    if (hasAll) {
                        tableStoreService.updateRow(tsStatRegionAll);
                    } else {
                        tableStoreService.putRow(tsStatRegionAll);
                    }

                } catch (Exception e) {
                    logger.info("保存信息出错：" + e);
                    e.printStackTrace();
                }

            }

            for (String key : questionDataMap.keySet()) {
                try {
                    TsBaseQuestionData tsBaseQuestionData = questionDataMap.get(key);//key: questionnaireId + regionId(quickTag) + questionId
                    String[] strArr = key.split("\\+");
                    Long questionnaireId = Long.parseLong(strArr[0]);
                    String regionIdStr = strArr[1];
                    Long questionId = Long.parseLong(strArr[2]);
                    if (key.contains(",")) {// 有逗号说明是网点的数据（quickTag）
                        regionIdStr = quickTagMap.get(regionIdStr).toString();
                    }
                    Long regionId = Long.parseLong(regionIdStr);

                    //每天答案数据
                    TsStatRegionQuestionPerDay tsStatRegionQuestionPerDay = new TsStatRegionQuestionPerDay();
                    tsStatRegionQuestionPerDay.setRegionId(regionId);
                    tsStatRegionQuestionPerDay.setQuestionnaireId(questionnaireId);
                    tsStatRegionQuestionPerDay.setQuestionId(questionId);
                    tsStatRegionQuestionPerDay.setDays(getDays());
                    boolean hasDay = tableStoreService.getRow(tsStatRegionQuestionPerDay);
                    mergeBaseQuestionData(tsBaseQuestionData, tsStatRegionQuestionPerDay);
                    if (hasDay) {
                        tableStoreService.updateRow(tsStatRegionQuestionPerDay);
                    } else {
                        tableStoreService.putRow(tsStatRegionQuestionPerDay);
                    }

                    //累积所有的答案数据
                    TsStatRegionQuestionAll tsStatRegionQuestionAll = new TsStatRegionQuestionAll();
                    tsStatRegionQuestionAll.setQuestionId(questionId);
                    tsStatRegionQuestionAll.setQuestionnaireId(questionnaireId);
                    tsStatRegionQuestionAll.setRegionId(regionId);
                    boolean hasAll = tableStoreService.getRow(tsStatRegionQuestionAll);
                    mergeBaseQuestionData(tsBaseQuestionData, tsStatRegionQuestionAll);
                    if (hasAll) {
                        tableStoreService.updateRow(tsStatRegionQuestionAll);
                    } else {
                        tableStoreService.putRow(tsStatRegionQuestionAll);
                    }

                } catch (Exception e) {
                    logger.info("保存答案信息出错：" + e);
                    e.printStackTrace();
                }

            }
        }

        private void batchGetRow(long startPkValue, long endPkValue) {
            RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria("ts_answer_sheet");
            // 设置起始主键
            PrimaryKeyBuilder primaryKeyBuilderStart = PrimaryKeyBuilder.createPrimaryKeyBuilder();
            primaryKeyBuilderStart.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(startPkValue));
            primaryKeyBuilderStart.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.INF_MIN);
            rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilderStart.build());
            // 设置结束主键
            PrimaryKeyBuilder primaryKeyBuilderEnd = PrimaryKeyBuilder.createPrimaryKeyBuilder();
            primaryKeyBuilderEnd.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(endPkValue));
            primaryKeyBuilderEnd.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.INF_MAX);
            rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilderEnd.build());
            rangeRowQueryCriteria.setMaxVersions(1);
            rangeRowQueryCriteria.setDirection(Direction.FORWARD);
            rangeRowQueryCriteria.setLimit(400);

            logger.info("GetRange的结果为:");
            while (true) {
                GetRangeResponse getRangeResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
                for (Row row : getRangeResponse.getRows()) {
                    try {
                        handleOneRecord(row);
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
        }

        private void getParentData() throws CloneNotSupportedException {
            Map<String, TsBaseData> parentMap = new HashMap<>();
            for (String key : dataMap.keySet()) {
                String[] strArr = key.split("\\+");
                if (strArr.length < 2) {
                    logger.info("dataMap存在无quickTag（regionId）数据");
                    continue;
                }
                String questionnaireId = strArr[0];
                String quickTag = strArr[1];
                String[] regionIdArr = quickTag.split(",");
                for (int i = 0; i < regionIdArr.length - 1; i++) {
                    String regionId = regionIdArr[i];
                    if (!ValueHelper.isNone(regionId)) {
                        String parentKey = questionnaireId + "+" + regionId;
                        TsBaseData newTsBaseData = dataMap.get(key);
                        TsBaseData tsBaseData = parentMap.get(parentKey);
                        if (tsBaseData == null) {
                            tsBaseData = newTsBaseData.clone();
                            parentMap.put(parentKey, tsBaseData);
                        } else {
                            mergeBaseData(newTsBaseData, tsBaseData);
                            parentMap.put(parentKey, tsBaseData);
                        }
                    }
                }
            }
            dataMap.putAll(parentMap);

            Map<String, TsBaseQuestionData> parentQuestionMap = new HashMap<>();
            for (String key : questionDataMap.keySet()) { //key: questionnaireId + regionId(quickTag) + questionId
                String[] strArr = key.split("\\+");
                String questionnaireId = strArr[0];
                String quickTag = strArr[1];
                String questionId = strArr[2];
                String[] regionIdArr = quickTag.split(",");
                for (int i = 0; i < regionIdArr.length - 1; i++) {
                    String regionId = regionIdArr[i];
                    if (!ValueHelper.isNone(regionId)) {
                        String parentKey = questionnaireId + "+" + regionId + "+" + questionId;
                        TsBaseQuestionData newTsBaseQuestionData = questionDataMap.get(key);
                        TsBaseQuestionData tsBaseQuestionData = parentQuestionMap.get(parentKey);
                        if (tsBaseQuestionData == null) {
                            tsBaseQuestionData = newTsBaseQuestionData.clone();
                            parentQuestionMap.put(parentKey, tsBaseQuestionData);
                        } else {
                            mergeBaseQuestionData(newTsBaseQuestionData, tsBaseQuestionData);
                            parentQuestionMap.put(parentKey, tsBaseQuestionData);
                        }
                    }
                }
            }
            questionDataMap.putAll(parentQuestionMap);
        }

        private void handleOneRecord(Row row) throws Exception {
            //问卷主要信息
            TsAnswerSheet tsAnswerSheet = new TsAnswerSheet();
            tableStoreService.row2Data(row, tsAnswerSheet);
            Long questionnaireId = tsAnswerSheet.getQuestionnaireId();
            Long regionId = tsAnswerSheet.getTenantsRegionId();
            String quickTag = tsAnswerSheet.getQuickTag();
            if (ValueHelper.isNone(quickTag)) {
                logger.info("quickTag为空");
            }
            String key = questionnaireId + "+" + quickTag;

            TsBaseData newTsBaseData = new TsBaseData();
            Double averageTime = (tsAnswerSheet.getFinishTime().getTime() - tsAnswerSheet.getStartTime().getTime()) / 1000D;
            newTsBaseData.setAverageTime(averageTime);
            Double rate = tsAnswerSheet.getFinishQuestionNum().doubleValue() / questionnaireMap.get(questionnaireId).getQuestionNum().doubleValue();
            newTsBaseData.setFinishedRate(rate > 1 ? 1 : rate);
            Map<String, Integer> osTypeMap = new HashMap<>();
            osTypeMap.put(tsAnswerSheet.getSystem(), 1);
            newTsBaseData.setOsTypeMap(osTypeMap);
            Map<String, Integer> phoneTypeMap = new HashMap<>();
            osTypeMap.put(tsAnswerSheet.getBroswer(), 1);
            newTsBaseData.setPhoneTypeMap(phoneTypeMap);
            newTsBaseData.setTotalFinishedCnt(1);
            newTsBaseData.setTotalVisitCnt(1);

            TsBaseData tsBaseData = dataMap.get(key);

            if (tsBaseData == null) {
                tsBaseData = newTsBaseData.clone();
                dataMap.put(key, tsBaseData);
            } else {
                mergeBaseData(newTsBaseData, tsBaseData);
                dataMap.put(key, tsBaseData);
            }

            //遍历题目
            List<UpAppsAnswerSheetItem> itemList = tsAnswerSheet.getItemList();
            List<UpAppsQuestionItem> questionItemList = upAppsQuestionItemService.selectList(
                    new EntityWrapper<UpAppsQuestionItem>()
                            .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, questionnaireId)
            );
            Map<Long, UpAppsQuestionItem> questionItemMap = new HashMap<>();
            for (UpAppsQuestionItem upAppsQuestionItem : questionItemList) {
                questionItemMap.put(upAppsQuestionItem.getId(), upAppsQuestionItem);
            }
            List<Long> questionIdList = new ArrayList<>();
            for (UpAppsAnswerSheetItem item : itemList) { //同一份答卷遍历题目
                String questionKey = questionnaireId + "+" + quickTag + "+" + item.getQuestionId();
                TsBaseQuestionData newTsBaseQuestionData = new TsBaseQuestionData();
                UpAppsQuestion question = questionMap.get(item.getQuestionId());
                QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.valueOf(question.getQuestionType());
                switch (questionTypeEnum) {
                    case 分项满意度:
                    case 评分:
                        newTsBaseQuestionData.setAverage(item.getScore().doubleValue());
                    case 单选:
                    case 服务方式:
                    case 个人信息:
                    case NPS:
                    case 是非:
                    case 总体满意度:
                    case 排序:
                    case 时间:
                    case 其他:
                        Map<String, Integer> answerMap = new HashMap<>();
                        answerMap.put(item.getValue(), 1);
                        newTsBaseQuestionData.setAnswerMap(answerMap);
                        newTsBaseQuestionData.setTotalCnt(1);
                        break;
                    case 多选:
                        Map<String, Integer> answerMap1 = new HashMap<>();
                        UpAppsQuestionItem questionItem1 = questionItemMap.get(item.getQuestionItemId());
                        answerMap1.put(questionItem1.getDisplay(), 1);
                        newTsBaseQuestionData.setAnswerMap(answerMap1);
                        boolean duplicate = false;
                        for (Long id : questionIdList) {
                            if (Objects.equals(id, item.getQuestionId())) {
                                newTsBaseQuestionData.setTotalCnt(0);
                                duplicate = true;
                                break;
                            }
                        }
                        if (!duplicate) {
                            newTsBaseQuestionData.setTotalCnt(1);
                            questionIdList.add(item.getQuestionId());
                        }
                        break;
                    case 填空:
                        // TODO: 2018/2/8 需要测试
                    case 手机验证:
                        Map<String, Integer> answerMap2 = new HashMap<>();
                        UpAppsQuestionItem questionItem = questionItemMap.get(item.getQuestionItemId());
                        answerMap2.put(questionItem.getPlaceholder(), 1);
                        newTsBaseQuestionData.setAnswerMap(answerMap2);
                        boolean duplicate1 = false;
                        for (Long id : questionIdList) {
                            if (Objects.equals(id, item.getQuestionId())) {
                                newTsBaseQuestionData.setTotalCnt(0);
                                duplicate1 = true;
                                break;
                            }
                        }
                        if (!duplicate1) {
                            newTsBaseQuestionData.setTotalCnt(1);
                            questionIdList.add(item.getQuestionId());
                        }
                        break;
                    default:
                        throw new AlertException("读取问题类型出错");
                }

                TsBaseQuestionData tsBaseQuestionData = questionDataMap.get(questionKey);

                if (tsBaseQuestionData == null) {
                    tsBaseQuestionData = newTsBaseQuestionData.clone();
                    questionDataMap.put(questionKey, tsBaseQuestionData);
                } else {
                    mergeBaseQuestionData(newTsBaseQuestionData, tsBaseQuestionData);
                    questionDataMap.put(questionKey, tsBaseQuestionData);
                }
            }

            logger.info(row);
        }

        private<T extends TsBaseData> void mergeBaseData(T newBaseData, T oldBaseData){

            oldBaseData.setTotalVisitCnt(newBaseData.getTotalVisitCnt() + oldBaseData.getTotalFinishedCnt());
            oldBaseData.setTotalFinishedCnt(newBaseData.getTotalFinishedCnt() + oldBaseData.getTotalFinishedCnt());
            Map<String, Integer> oldPhoneTypeMap = oldBaseData.getPhoneTypeMap();
            Map<String, Integer> newPhoneTypeMap = newBaseData.getPhoneTypeMap();
            if (oldPhoneTypeMap == null) {
                oldBaseData.setPhoneTypeMap(newPhoneTypeMap);
            } else {
                for (String key : newPhoneTypeMap.keySet()) {
                    oldPhoneTypeMap.put(key, oldPhoneTypeMap.get(key) == null ?
                            newPhoneTypeMap.get(key) : newPhoneTypeMap.get(key) + oldPhoneTypeMap.get(key));
                }
            }
            oldBaseData.setPhoneTypeMap(oldPhoneTypeMap);
            Map<String, Integer> oldOsTypeMap = oldBaseData.getOsTypeMap();
            Map<String, Integer> newOsTypeMap = newBaseData.getOsTypeMap();
            if (oldOsTypeMap == null) {
                oldBaseData.setOsTypeMap(newOsTypeMap);
            } else {
                for (String key : newOsTypeMap.keySet()) {
                    oldOsTypeMap.put(key, oldOsTypeMap.get(key) == null ?
                            newOsTypeMap.get(key) : newOsTypeMap.get(key) + oldOsTypeMap.get(key));
                }
            }
            oldBaseData.setOsTypeMap(oldOsTypeMap);
            if (oldBaseData.getFinishedRate() == null) {
                oldBaseData.setFinishedRate(newBaseData.getFinishedRate());
            } else {
                Double rate = (newBaseData.getFinishedRate() * newBaseData.getTotalFinishedCnt() + oldBaseData.getFinishedRate() * oldBaseData.getTotalFinishedCnt())
                        / (newBaseData.getTotalFinishedCnt() + oldBaseData.getTotalFinishedCnt());

                oldBaseData.setFinishedRate(rate);
            }
            if (oldBaseData.getAverageTime() == null) {
                oldBaseData.setAverageTime(newBaseData.getAverageTime());
            } else {
                Double averageTime = (newBaseData.getAverageTime() * newBaseData.getTotalFinishedCnt() + oldBaseData.getAverageTime() * oldBaseData.getTotalFinishedCnt())
                        / (newBaseData.getTotalFinishedCnt() + oldBaseData.getTotalFinishedCnt());
                oldBaseData.setAverageTime(averageTime);
            }
        }

        private<T extends TsBaseQuestionData> void mergeBaseQuestionData(T newBaseQuestionData, T oldBaseQuestionData) {

            oldBaseQuestionData.setTotalCnt(newBaseQuestionData.getTotalCnt() + oldBaseQuestionData.getTotalCnt());
            Map<String, Integer> answerMap = newBaseQuestionData.getAnswerMap();
            if (answerMap != null) {
                Map<String, Integer> oldAnswerMap = oldBaseQuestionData.getAnswerMap();
                if (oldAnswerMap == null) {
                    oldAnswerMap = new HashMap<>();
                }
                for (String key : answerMap.keySet()) {
                    if (oldAnswerMap.get(key) != null) {
                        oldAnswerMap.put(key, oldAnswerMap.get(key) + answerMap.get(key));
                    } else {
                        oldAnswerMap.put(key, answerMap.get(key));
                    }
                }
                oldBaseQuestionData.setAnswerMap(oldAnswerMap);
            }
            if (newBaseQuestionData.getAverage() != null) {
                if (oldBaseQuestionData.getAverage() != null) {
                    Double average = (newBaseQuestionData.getAverage() * newBaseQuestionData.getTotalCnt() + oldBaseQuestionData.getTotalCnt() * oldBaseQuestionData.getTotalCnt()) /
                            (newBaseQuestionData.getTotalCnt() + oldBaseQuestionData.getTotalCnt());
                    oldBaseQuestionData.setAverage(average);
                } else {
                    oldBaseQuestionData.setAverage(newBaseQuestionData.getAverage());
                }
            }
        }

        private Long getHours() {
            return (time.getTime() / Constants.ONE_HOUR);
        }

        private int getHourTime() {
            return time.getHours();
        }

        private Long getDays() {
            return (time.getTime() / Constants.ONE_DAY);
        }
    }

    @Override
    public void lastHourStatTask() {
        //task
        taskExecutor.execute(new StatJob());
    }

    void saveTask(UpTenantsTask task, List<Map<String, Object>> messages) {
        task.setLastMessage(messages.get(messages.size() - 1).get("message").toString());
        task.setMessagesJson(JSONObject.toJSONString(messages));
        if (task.getId() != null) {
            upTenantsTaskService.updateById(task);
        } else {
            upTenantsTaskService.insert(task);
        }
    }

    void addMessage(List list, String msg) {
        Map<String, Object> message = new HashMap<>();
        message.put("time", new Date().getTime());
        message.put("message", msg);
        list.add(message);
    }
    //endregion
}
