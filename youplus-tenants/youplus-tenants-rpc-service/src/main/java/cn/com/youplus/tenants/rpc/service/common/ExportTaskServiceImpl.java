package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetItemService;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.apps.common.model.Question;
import cn.com.youplus.apps.common.model.QuestionItem;
import cn.com.youplus.apps.common.model.Questionnaire;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.model.tablestore.TsAnswerSheet;
import cn.com.youplus.common.model.tablestore.TsAnswerSheetIndex;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.SnowFlake;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsTask;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsTaskService;
import cn.com.youplus.tenants.api.common.ExportTaskService;
import cn.com.youplus.tenants.common.model.enums.TaskStatusEnum;
import cn.com.youplus.tenants.common.model.enums.TaskTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**addMessage
 * Created by 严汉羽 on 2017/12/25.
 */
@Service("exportTaskService")
public class ExportTaskServiceImpl implements ExportTaskService {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExportTaskServiceImpl.class);


    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsAnswerSheetService upAppsAnswerSheetService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpAppsAnswerSheetItemService upAppsAnswerSheetItemService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpTenantsTaskService upTenantsTaskService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private SyncClient syncClient;

    @Autowired
    private TableStoreServiceImpl tableStoreService;


    /**
     * 任务线程
     */
    public class excelJob implements Runnable {

        private ExportTaskService exportDataToExcel;
        private Long questionnaireId;
        private String regionFilter;
        private String quickTag;
        private Date start;
        private Date end;

        public excelJob() {
        }

        public excelJob(ExportTaskService exportDataToExcel, Long questionnaireId, Date start, Date end, String quickTag, String regionFilter) {
            this.exportDataToExcel = exportDataToExcel;
            this.questionnaireId = questionnaireId;
            this.regionFilter = regionFilter;
            this.quickTag = quickTag;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {

            UpTenantsRegion upTenantsRegion = upTenantsRegionService.selectOne(
                    new EntityWrapper<UpTenantsRegion>()
                            .eq(UpTenantsRegion.QUICK_TAG, quickTag)
            );

            String region = upTenantsRegion == null ? "" :
                    upTenantsRegion.getIsStore() == 0 ? upTenantsRegion.getName() + "全部" : upTenantsRegion.getName();

            UpTenantsTask task = new UpTenantsTask();
            task.setAddTime(new Date())
                    .setUpdateTime(new Date())
                    .setQuestionnaireId(questionnaireId)
                    .setQuickTag(quickTag)
                    .setRegionFilter(region)
                    .setStatus(TaskStatusEnum.进行中.getType())
                    .setTaskType(TaskTypeEnum.问卷原始数据导出.getType());

            List<Map<String, Object>> messages = new ArrayList<>();
            addMessage(messages, "任务创建成功");
            try {
                UpTenantsTask oldTask = upTenantsTaskService.selectOne(
                        new EntityWrapper<UpTenantsTask>()
                                .eq(UpTenantsTask.QUESTIONNAIRE_ID, questionnaireId)
                                .eq(UpTenantsTask.STATUS, TaskStatusEnum.进行中.getType())
                );

                if (oldTask != null) {
                    throw new AlertException("已经存在一个进行中的任务了，不能同时启动两个任务.");
                }

                exportDataToExcel.exportDataToExcel(questionnaireId, start, end, task, messages, quickTag, regionFilter);
            } catch (Exception e) {
                logger.error("数据导出", e);
                if (e instanceof AlertException) {
                    addMessage(messages, e.getMessage());
                } else {
                    addMessage(messages, "处理异常:" +  e.getMessage());
                }
                task.setStatus(TaskStatusEnum.意外终止.getType());
            }

            saveTask(task, messages);
        }
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

    Questionnaire 获取问卷和题目(UpAppsQuestionnaire questionnaire) {
        List<UpAppsQuestion> questions = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaire.getId())
                        .orderBy(UpAppsQuestion.QUESTION_ORDER)
                        .orderBy(UpAppsQuestion.ADD_TIME, false)
        );

        return new Questionnaire(questionnaire, new UpAppsQuestionnaireTheme(), questions);
    }


    void addMessage(List list, String msg) {
        Map<String, Object> message = new HashMap<>();
        message.put("time", new Date().getTime());
        message.put("message", msg);
        list.add(message);
    }

    @Override
    public String exportDataToExcel(Long questionnaireId, Date start, Date end, UpTenantsTask task, List messages, String quickTag, String regionFilter) throws Exception {
        if (end == null) {
            end = new Date();
        }
        logger.info("任务开始执行:");

        //这里开启一个任务:

        //新建一个workbook
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        if (questionnaire == null) {
            throw new AlertException("问卷不存在:" + questionnaireId);
        }

        QuestionnaireStatusEnum statusEnum = QuestionnaireStatusEnum.valueOf(questionnaire.getStatus());
        switch (statusEnum) {
            case 编辑中:
            case 待收集:
            case 已删除:
                String msg = "状态错误，无法导出:" + statusEnum.getType();
                throw new AlertException(msg);
        }

        Wrapper<UpTenantsRegion> regionWrapper = new EntityWrapper<UpTenantsRegion>()
                .eq(UpTenantsRegion.TENANTS_COMPANY_ID, questionnaire.getTenantsCompanyId())
                .eq(UpTenantsRegion.IS_STORE, 1);

        if (!ValueHelper.isNone(quickTag)) {
            regionWrapper.like(UpTenantsRegion.QUICK_TAG, quickTag, SqlLike.RIGHT);
        }

        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(regionWrapper);

        if (regionList == null || regionList.size() == 0) {
            String msg = "找不到对应的网点数据，无法导出";
            throw new AlertException(msg);
        }

        Map<Long, Integer> 网点索引 = new HashMap<>();
        for (int os = 0; os < regionList.size(); os ++) {
            网点索引.put(regionList.get(os).getId(), os + 1); //这里需要 + 1 因为第0行是全部的总数据
        }

        Map<Long, String> regionMap = new HashMap<>();
        for (UpTenantsRegion region : regionList) {
            regionMap.put(region.getId(), region.getName());
        }

        Wrapper<UpAppsAnswerSheet> wrapper = new EntityWrapper<UpAppsAnswerSheet>()
                .eq(UpAppsAnswerSheet.QUESTIONNAIRE_ID, questionnaire.getId())
                .eq(UpAppsAnswerSheet.IS_FINISHED, 1);

        if (!ValueHelper.isNone(quickTag)) {
            wrapper.like(UpAppsAnswerSheet.QUICK_TAG, quickTag, SqlLike.RIGHT);
        }

        if (start != null) {
            wrapper.ge(UpAppsAnswerSheet.FINISH_TIME, start);
        }
        wrapper.le(UpAppsAnswerSheet.FINISH_TIME, end);

        Questionnaire 问卷 = 获取问卷和题目(questionnaire);

        Map<Long, QuestionTypeEnum> 题目类型快速索引 = new HashMap<>();
        for (Question question : 问卷.getQuestionList()) {
            题目类型快速索引.put(question.getId(), QuestionTypeEnum.valueOf(question.getQuestionType()));
        }


        Map<String, Integer> 满意度分数对照表 = new HashMap<>();
        满意度分数对照表.put("非常满意", 5);
        满意度分数对照表.put("满意", 4);
        满意度分数对照表.put("一般", 3);
        满意度分数对照表.put("不满意", 2);
        满意度分数对照表.put("非常不满意", 1);

        //计算得到表头
        List<String> 表头 = new ArrayList<>();
        List<String> 统计数据表头 = new ArrayList<>();
        Map<Long, Integer> 表头关系 = new HashMap<>();
        Map<Long, Integer> 统计数据表头关系 = new HashMap<>();

        表头.add("访问日期");
        表头.add("网点");
        表头.add("电话号码");
        统计数据表头.add("网点");

        for (Question question : 问卷.getQuestionList()) {
            QuestionTypeEnum questionType = QuestionTypeEnum.valueOf(question.getQuestionType());
            switch (questionType) {
                case 分项满意度:
                case 总体满意度:
                case NPS:
                case 评分:
                    if (ValueHelper.isNone(question.getSubTitle())) {
                        表头.add("题目" + question.getQuestionOrder() + "得分");
                        统计数据表头.add("题目" + question.getQuestionOrder() + "平均得分");
                    } else {
                        表头.add(question.getSubTitle() + "得分");
                        统计数据表头.add(question.getSubTitle() + "平均得分");
                    }
                    表头关系.put(question.getId(), 表头.size() - 1);
                    统计数据表头关系.put(question.getId(), 统计数据表头.size() - 1);
                    break;
                case 单选:
                case 是非:
                    if (ValueHelper.isNone(question.getSubTitle())) {
                        表头.add("题目" + question.getQuestionOrder() + "选项");
                    } else {
                        表头.add(question.getSubTitle() + "选项");
                    }
                    表头关系.put(question.getId(), 表头.size() - 1);

                    String header;
                    if (ValueHelper.isNone(question.getSubTitle())) {
                        header = "题目" + question.getQuestionOrder();
                    } else {
                        header = question.getSubTitle();
                    }
                    int offset = 1;
                    for (QuestionItem item : question.getItemList()) {
                        统计数据表头.add(header + "_" + offset + "计数");
                        统计数据表头关系.put(item.getId(), 统计数据表头.size() - 1);
                        offset++;
                    }
                    break;

                case 多选:
                case 填空:
                    // TODO: 2018/2/8 验证
                case 手机验证:
                case 个人信息:
                case 时间:
                    if (ValueHelper.isNone(question.getSubTitle())) {
                        header = "题目" + question.getQuestionOrder();
                    } else {
                        header = question.getSubTitle();
                    }
                    offset = 1;
                    for (QuestionItem item : question.getItemList()) {
                        表头.add(header + "_" + offset);
                        表头关系.put(item.getId(), 表头.size() - 1);
                        if (questionType == QuestionTypeEnum.多选) {
                            统计数据表头.add(header + "_" + offset + "计数");
                            统计数据表头关系.put(item.getId(), 统计数据表头.size() - 1);
                        }
                        offset++;
                    }
                    break;
                default:
                    throw new AlertException("不支持的问卷题目类型" + questionType.getType());
            }
        }

        //表格存储限制100一份
        int pageSize = 100;
        int excel当前行数 = 0;
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet workSheet = xssfWorkbook.createSheet("原始数据");

        XSSFRow rowTitle = workSheet.createRow(excel当前行数++);
        for (int i = 0; i < 表头.size(); i++) {
            XSSFCell newCell = rowTitle.createCell(i);
            newCell.setCellValue(表头.get(i));
        }

        //统计数据
        int [][] sumArray = new int[regionList.size() + 1][统计数据表头.size()];
        int [][] countingArray = new int[regionList.size() + 1][统计数据表头.size()];

        for (int i = 0; i < regionList.size() + 1; i++) {
            for (int j = 0; j < 统计数据表头.size(); j++) {
                sumArray[i][j] = 0;
                countingArray[i][j] = 0;
            }
        }

        addMessage(messages, "开始导出..." );
        saveTask(task, messages);

        Long startId = 0L, endId = 0L;
        //由于倒叙的关系，start end会倒过来
        if (start != null) {
            startId = SnowFlake.nextId(start.getTime());
        }

        if (end != null) {
            end = new Date(end.getTime() + (23 * 3600 + 3599) * 1000 + 999);
            endId = SnowFlake.nextId(end.getTime());
        }

        RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria("ts_answer_sheet_index");
        // 设置起始主键
        PrimaryKeyBuilder primaryKeyBuilderStart = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilderStart.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(questionnaireId));
        if (startId > 0) {
            primaryKeyBuilderStart.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(startId));
        } else {
            primaryKeyBuilderStart.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MIN);
        }
        rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilderStart.build());
        // 设置结束主键
        PrimaryKeyBuilder primaryKeyBuilderEnd = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilderEnd.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(questionnaireId));
        if (endId > 0) {
            primaryKeyBuilderEnd.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(endId));
        } else {
            primaryKeyBuilderEnd.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MAX);
        }
        rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilderEnd.build());
        rangeRowQueryCriteria.setMaxVersions(1);
        rangeRowQueryCriteria.setDirection(Direction.FORWARD);
        rangeRowQueryCriteria.setLimit(100);

        logger.info("GetRange的结果为:");
        int totalNum = 0;
        TsAnswerSheetIndex index = new TsAnswerSheetIndex();
        while (true) {
            GetRangeResponse getRangeResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
            List<TsAnswerSheet> answerSheetList = new ArrayList<>();
            for (Row row : getRangeResponse.getRows()) {
                try {
                    tableStoreService.row2Data(row, index);
                    TsAnswerSheet answerSheet = new TsAnswerSheet();
                    answerSheet.setQuestionnaireId(index.getQuestionnaireId());
                    answerSheet.setAnswerSheetId(index.getAnswerSheetId());
                    answerSheetList.add(answerSheet);
                } catch (Exception e) {
                    logger.debug("处理异常:", e);
                }
            }
            totalNum += answerSheetList.size();
            addMessage(messages, "导出进行中: 读取到需要处理条" + answerSheetList.size() + "有效数据,总共已处理:" + totalNum + "条数据");

            List<TsAnswerSheet> asList = null;
            if (answerSheetList.size() > 0) {
                asList = tableStoreService.batchGetRow(answerSheetList);
            }
            if (asList == null || asList.size() == 0) {
                continue;
            }

            Map<Long, Long> 答卷id对应的regionid索引 = new HashMap<>(asList.size());
            for (TsAnswerSheet answerSheet : asList) {
                答卷id对应的regionid索引.put(answerSheet.getAnswerSheetId(), answerSheet.getTenantsRegionId());
            }

            String [][] dataArray = new String[asList.size()][表头.size()];
            //Map<Long, Integer> answerSheet快速索引 = new HashMap<>(asList.size());

            for (int idx = 0; idx < asList.size(); idx ++) {
                TsAnswerSheet upAppsAnswerSheet = asList.get(idx);
                //answerSheet快速索引.put(upAppsAnswerSheet.getAnswerSheetId(), idx);
                dataArray[idx][0] = Constants.DATE_FORMAT.format(upAppsAnswerSheet.getFinishTime());
                dataArray[idx][1] = regionMap.get(upAppsAnswerSheet.getTenantsRegionId());
                dataArray[idx][2] = upAppsAnswerSheet.getPhoneNum();
            }

            for (int i = 0; i < asList.size(); i++) {
                TsAnswerSheet answerSheet = asList.get(i);
                for (UpAppsAnswerSheetItem item : answerSheet.getItemList()) {
                    QuestionTypeEnum typeEnum = 题目类型快速索引.get(item.getQuestionId());

                    if (typeEnum == null) {
                        logger.info(item.toString());
                        continue;
                    }

                    switch (typeEnum) {
                        case 分项满意度:
                        case 总体满意度:
                        case NPS:
                        case 评分:
                            Integer score = item.getScore();
                            int 统计索引 = 统计数据表头关系.get(item.getQuestionId());
                            if (score == null || score == 0) {
                                //对于部分数据没有添加score索引，n那么就用满意度对照表获取
                                score = 满意度分数对照表.get(item.getValue());
                            }

                            if (score == null) {
                                score = 99;
                            }

                            dataArray
                                    [i]
                                    [表头关系.get(item.getQuestionId())]
                                    = score.toString();

                            Integer 网点idx =  网点索引.get(
                                    答卷id对应的regionid索引.get(item.getAnswerSheetId()));
                            if (score != null && score > 0 && score < 11) {
                                countingArray[0][统计索引]++;
                                countingArray[网点idx][统计索引]++;
                                sumArray[0][统计索引] += item.getScore();
                                sumArray[网点idx][统计索引] += item.getScore();
                            }
                            break;
                        case 单选:
                        case 是非:
                            int i1 = i;
                            int i2 = 表头关系.get(item.getQuestionId());
                            dataArray
                                    [i1]
                                    [i2] = item.getValue();

                            统计索引 = 统计数据表头关系.get(item.getQuestionItemId());
                            网点idx =  网点索引.get(
                                    答卷id对应的regionid索引.get(item.getAnswerSheetId()));
                            countingArray[0][统计索引]++;
                            countingArray[网点idx][统计索引]++;
                            break;

                        case 多选:
                            dataArray
                                    [i]
                                    [表头关系.get(item.getQuestionItemId())] = "1";
                            统计索引 = 统计数据表头关系.get(item.getQuestionItemId());
                            网点idx =  网点索引.get(
                                    答卷id对应的regionid索引.get(item.getAnswerSheetId()));
                            countingArray[0][统计索引]++;
                            countingArray[网点idx][统计索引]++;
                            break;
                        case 填空:
                        case 手机验证:
                        case 个人信息:
                        case 时间:
                            dataArray
                                    [i]
                                    [表头关系.get(item.getQuestionItemId())] = item.getInputContent();
                            break;
                        default:
                            throw new AlertException("不支持的问卷题目类型" + typeEnum.getType());
                    }
                }

            }
            for (String [] rowData : dataArray) {
                XSSFRow row = workSheet.createRow(excel当前行数++);
                for (int j = 0; j < rowData.length; j++) {
                    XSSFCell newCell = row.createCell(j);
                    newCell.setCellValue(rowData[j] == null ? "" : rowData[j]);
                }
            }
            addMessage(messages, "导出进行中: " + answerSheetList.size() + "条数据处理完成,总共已处理:" + totalNum + "条数据");
            saveTask(task, messages);
            // 若nextStartPrimaryKey不为null, 则继续读取.
            if (getRangeResponse.getNextStartPrimaryKey() != null) {
                rangeRowQueryCriteria.setInclusiveStartPrimaryKey(getRangeResponse.getNextStartPrimaryKey());
            } else {
                break;
            }
        }
        XSSFSheet workSheetStat = xssfWorkbook.createSheet("统计数据");

        excel当前行数 = 0;
        rowTitle = workSheetStat.createRow(excel当前行数++);
        for (int i = 0; i < 统计数据表头.size(); i++) {
            XSSFCell newCell = rowTitle.createCell(i);
            newCell.setCellValue(统计数据表头.get(i));
        }

        for (int i = 0; i < regionList.size() + 1; i++) {
            rowTitle = workSheetStat.createRow(excel当前行数++);
            XSSFCell newCell = rowTitle.createCell(0);
            if (i == 0) {
                newCell.setCellValue("全部");
            } else {
                newCell.setCellValue(regionMap.get(regionList.get(i - 1).getId()));
            }
            for (int j = 1; j < 统计数据表头.size(); j ++) {
                int c = countingArray[i][j];
                int s = sumArray[i][j];
                newCell = rowTitle.createCell(j);

                if (s == 0 || c == 0) {
                    newCell.setCellValue(c);
                } else {
                    newCell.setCellValue(ValueHelper.rint((float)s / (float)c).toString());
                }
            }
        }

        addMessage(messages, "导出进行中: 数据处理完成，准备写文件");
        saveTask(task, messages);

        UpTenantsCompany company = upTenantsCompanyService.selectById(questionnaire.getTenantsCompanyId());

        String pathPreffix;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            pathPreffix = "D:/";
        } else {
            pathPreffix = "/tmp/";
        }

        String fileName = pathPreffix + company.getName() + "-生成数据-" + Constants.DATETIME_FORMAT_4_FILE.format(new Date()) +  ".xlsx";

        FileOutputStream fout = new FileOutputStream(fileName);
        xssfWorkbook.write(fout);
        fout.close();

        File f = new File(fileName);
        InputStream in = new FileInputStream(f);
        task.setDownload(uploadOneFile(in, ".xlsx"));
        task.setStatus(TaskStatusEnum.已完成.getType());
        addMessage(messages, "导出进行中: 数据处理完成，文件完成");
        saveTask(task, messages);
        return "";
    }

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunProperties aliyunProperties;

    public String uploadOneFile(InputStream is, String suffix) {
        BaseResponse<String> response = new BaseResponse<>();
        if(null == is){
            return null;
        }
        if (!suffix.startsWith(".")) {
            suffix = "." + suffix;
        }

        String key,prefix;

        prefix = StringHelper.getImgName();
        key = prefix + suffix;
        ossClient.putObject(aliyunProperties.getAliyunOssBucket(), key, is);

        return key;
    }

    @Override
    public String exportDataToExcelWithNewThread(Long questionnaireId, Date start, Date end, String quickTag, String regionFilter) throws Exception {
        taskExecutor.execute(new excelJob(this, questionnaireId, start, end,quickTag, regionFilter));
        return "";
    }

}
