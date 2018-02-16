package cn.com.youplus.apps.rpc.service.common;

import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetItemService;
import cn.com.youplus.apps.api.auto.UpAppsAnswerSheetService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionService;
import cn.com.youplus.apps.api.auto.UpAppsQuestionnaireService;
import cn.com.youplus.apps.api.common.AnswerSheetService;
import cn.com.youplus.apps.common.model.Question;
import cn.com.youplus.apps.common.model.QuestionItem;
import cn.com.youplus.apps.common.model.Questionnaire;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.tablestore.*;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/6/29.
 */
@Service("answerSheetService")
public class AnswerSheetServiceImpl implements AnswerSheetService {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AnswerSheetServiceImpl.class);


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
    private TaskExecutor taskExecutor;
    
    @Resource(name = "tableStoreService")
    private TableStoreServiceImpl tableStoreService;


    public class excelJob implements Runnable {

        private AnswerSheetService answerSheetService;
        private Long questionnaireId;
        private Date start;
        private Date end;

        public excelJob() {
        }

        public excelJob(AnswerSheetService answerSheetService, Long questionnaireId, Date start, Date end) {
            this.answerSheetService = answerSheetService;
            this.questionnaireId = questionnaireId;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                answerSheetService.exportDataToExcel(questionnaireId, start, end);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public class MultiThreadDemo implements Runnable {

        private AnswerSheetService answerSheetService;
        private int startNum;
        private int total;

        public MultiThreadDemo() {
        }

        public MultiThreadDemo(AnswerSheetService answerSheetService, int startNum, int total) {
            this.answerSheetService = answerSheetService;
            this.startNum = startNum;
            this.total = total;
        }

        @Override
        public void run() {
            try {
                answerSheetService.exportToDataMultiTask(startNum, total);
            } catch (Exception e) {
                e.printStackTrace();
            }
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


    @Override
    public String exportDataToExcel(Long questionnaireId, Date start, Date end) throws Exception {
        if (end == null) {
            end = new Date();
        }

        logger.info("任务开始执行:");

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
                throw new AlertException("状态错误，无法导出:" + statusEnum.getType());
        }

        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, questionnaire.getTenantsCompanyId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );

        if (regionList == null || regionList.size() == 0) {
            throw new AlertException("找不到对应的网点数据，无法导出");
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

        if (start != null) {
            wrapper.ge(UpAppsAnswerSheet.FINISH_TIME, start);
        }

        if (end != null) {
            wrapper.le(UpAppsAnswerSheet.FINISH_TIME, end);
        }

        int count = upAppsAnswerSheetService.selectCount(wrapper);

        if (count > 20000) {
            throw new AlertException("导出数量超过1万条，导出时间段"
                    + start
                    + "-" +
                    end
                    + "，强烈建议分时间导出");
        }

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
                    throw new AlertException("不支持的问卷题目类型");
            }
        }

        int pageSize = 1000;
        int pageNum = count / pageSize;
        if (count % pageSize > 0) {
            pageNum++;
        }

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

        logger.info("总共:");
        //注意是从第一个开始的
        for (int num = 0; num < pageNum; num++) {
            Page<UpAppsAnswerSheet> page = upAppsAnswerSheetService.selectPage(
                    new Page<>(num + 1, pageSize),
                    wrapper
            );

            if (page.getRecords().size() == 0) {
                break;
            }

            List<Long> answerSheetIds = new ArrayList<>(page.getRecords().size());
            Map<Long, Long> 答卷id对应的regionid索引 = new HashMap<>(page.getRecords().size());
            for (UpAppsAnswerSheet answerSheet : page.getRecords()) {
                answerSheetIds.add(answerSheet.getId());
                答卷id对应的regionid索引.put(answerSheet.getId(), answerSheet.getTenantsRegionId());
            }

            List<UpAppsAnswerSheetItem> answerSheetItemList = upAppsAnswerSheetItemService.selectList(
                    new EntityWrapper<UpAppsAnswerSheetItem>()
                            .in(UpAppsAnswerSheetItem.ANSWER_SHEET_ID, answerSheetIds)
            );

            String [][] dataArray = new String[page.getRecords().size()][表头.size()];
            Map<Long, Integer> answerSheet快速索引 = new HashMap<>(page.getRecords().size());
            for (int idx = 0; idx < page.getRecords().size(); idx ++) {
                UpAppsAnswerSheet upAppsAnswerSheet = page.getRecords().get(idx);
                answerSheet快速索引.put(upAppsAnswerSheet.getId(), idx);
                dataArray[idx][0] = Constants.DATE_FORMAT.format(upAppsAnswerSheet.getFinishTime());
                dataArray[idx][1] = regionMap.get(upAppsAnswerSheet.getTenantsRegionId());
                dataArray[idx][2] = upAppsAnswerSheet.getPhoneNum();
            }

            for (UpAppsAnswerSheetItem item : answerSheetItemList) {
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
                                [answerSheet快速索引.get(item.getAnswerSheetId())]
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
                        int i1 = answerSheet快速索引.get(item.getAnswerSheetId());
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
                                [answerSheet快速索引.get(item.getAnswerSheetId())]
                                [表头关系.get(item.getQuestionItemId())] = "1";
                        统计索引 = 统计数据表头关系.get(item.getQuestionItemId());
                        网点idx =  网点索引.get(
                                答卷id对应的regionid索引.get(item.getAnswerSheetId()));
                        countingArray[0][统计索引]++;
                        countingArray[网点idx][统计索引]++;
                        break;
                    case 填空:
                        // TODO: 2018/2/8 验证
                    case 手机验证:
                    case 个人信息:
                    case 时间:
                        dataArray
                                [answerSheet快速索引.get(item.getAnswerSheetId())]
                                [表头关系.get(item.getQuestionItemId())] = item.getInputContent();
                        break;
                    default:
                        throw new AlertException("不支持的问卷题目类型");
                }
            }

            for (String [] rowData : dataArray) {
                XSSFRow row = workSheet.createRow(excel当前行数++);
                for (int i = 0; i < rowData.length; i++) {
                    XSSFCell newCell = row.createCell(i);
                    newCell.setCellValue(rowData[i] == null ? "" : rowData[i]);
                }
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

        UpTenantsCompany company = upTenantsCompanyService.selectById(questionnaire.getTenantsCompanyId());

        FileOutputStream fout = new FileOutputStream("D:/" + company.getName() + "-生成数据-" + Constants.DATETIME_FORMAT_4_FILE.format(new Date()) +  ".xlsx");
        xssfWorkbook.write(fout);
        fout.close();
        return "";
    }

    @Override
    public String exportDataToExcelWithNewThread(Long questionnaireId, Date start, Date end) throws Exception {
        taskExecutor.execute(new excelJob(this, questionnaireId, start, end));
        return "";
    }

    @Override
    public String exportData(byte[] bytes) throws Exception {
//        Date start = new Date();
//        InputStream is = new ByteArrayInputStream(bytes);
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
//        XSSFSheet xssfSheet = xssfWorkbook.getSheet("原始数据");
//        XSSFRow rowSubTitle = xssfSheet.getRow(0);
//        XSSFRow rowTitle = xssfSheet.getRow(1);
//        Long questionnaireId = 937325886211137537L; //true
//        Long companyId = 937325836747743234L; //true
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("中石油数据");
//
//        XSSFRow row0 = sheet.createRow(0);
//        for (Iterator cellIt = rowSubTitle.cellIterator(); cellIt.hasNext(); ) {
//            XSSFCell tmpCell = (XSSFCell) cellIt.next();
//            XSSFCell newCell = row0.createCell(tmpCell.getColumnIndex());
//            newCell.setCellValue(tmpCell.getStringCellValue());
//            XSSFCellStyle style = workbook.createCellStyle();
//            copyCellStyle(tmpCell.getCellStyle(), style);
//            newCell.setCellStyle(style);
//        }
//
//        XSSFRow row1 = sheet.createRow(1);
//        Map<String, Integer> valueMap = new HashMap<>();
//        for (Iterator cellIt = rowTitle.cellIterator(); cellIt.hasNext(); ) {
//            XSSFCell tmpCell = (XSSFCell) cellIt.next();
//            XSSFCell newCell = row1.createCell(tmpCell.getColumnIndex());
//            newCell.setCellValue(tmpCell.getStringCellValue());
//            XSSFCellStyle style = workbook.createCellStyle();
//            copyCellStyle(tmpCell.getCellStyle(), style);
//            newCell.setCellStyle(style);
//            valueMap.put(tmpCell.getStringCellValue(), tmpCell.getColumnIndex());
//        }
//        valueMap.remove("加油站口碑好");
//        valueMap.put("中国石油口碑好", 16);
//
//        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
//                new EntityWrapper<UpTenantsRegion>()
//                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, companyId)
//        );
//        Map<Long, String> regionMap = new HashMap<>();
//        for (UpTenantsRegion region : regionList) {
//            regionMap.put(region.getId(), region.getName());
//        }
//
//        int size = 585000;
//        int pageSize = 1000;
//        int pageNum = size / pageSize;
//        int lineNum = 2;
//
//        for (int num = 0; num < pageNum; num++) {
//            Page<AllCnpcData> page = allCnpcDataService.selectPage(new Page<>(num, pageSize));
//            List<AllCnpcData> cnpcDataList = page.getRecords();
//            List<String> phoneList = new ArrayList<>();
//            for (AllCnpcData cnpcData : cnpcDataList) {
//                phoneList.add(cnpcData.getPhoneNum());
//            }
//
//            List<UpAppsAnswerSheet> answerSheetList = upAppsAnswerSheetService.selectList(
//                    new EntityWrapper<UpAppsAnswerSheet>()
//                            .eq(UpAppsAnswerSheet.QUESTIONNAIRE_ID, questionnaireId)
//                            .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
//                            .in(UpAppsAnswerSheet.PHONE_NUM, phoneList)
//                            .orderBy(UpAppsAnswerSheet.FINISH_TIME, true)
//                            .groupBy(UpAppsAnswerSheet.PHONE_NUM)
//            );
//            List<Long> answerSheetIds = new ArrayList<>();
//            for (UpAppsAnswerSheet answerSheet : answerSheetList) {
//                answerSheetIds.add(answerSheet.getId());
//            }
//            List<UpAppsAnswerSheetItem> answerSheetItemList = upAppsAnswerSheetItemService.selectList(
//                    new EntityWrapper<UpAppsAnswerSheetItem>()
//                            .in(UpAppsAnswerSheetItem.ANSWER_SHEET_ID, answerSheetIds)
//            );
//
//            Map<Long, List<UpAppsAnswerSheetItem>> answerSheetItemListMap = new HashMap<>();
//            for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
//                List<UpAppsAnswerSheetItem> answerSheetItemList1 = answerSheetItemListMap.get(answerSheetItem.getAnswerSheetId());
//                if (answerSheetItemList1 == null) {
//                    answerSheetItemList1 = new ArrayList<>();
//                }
//                answerSheetItemList1.add(answerSheetItem);
//                answerSheetItemListMap.put(answerSheetItem.getAnswerSheetId(), answerSheetItemList1);
//            }
//
//            logger.info("页码： " + num + "/" + pageNum);
//            logger.info("answerSheetList大小： " + answerSheetList.size());
//            for (int i = 0; i < answerSheetList.size(); i++) {
//                try {
//                    XSSFRow rowContent = sheet.createRow(i + lineNum);
//                    UpAppsAnswerSheet answerSheet = answerSheetList.get(i);
//
//                    XSSFCell dataCell = rowContent.createCell(0);
//                    dataCell.setCellValue(Constants.DATE_FORMAT.format(answerSheet.getFinishTime()));
//                    XSSFCell companyCell = rowContent.createCell(1);
//                    String regionName = regionMap.get(answerSheet.getTenantsRegionId());
//                    if (regionName != null) {
//                        companyCell.setCellValue(regionName);
//                    }
//                    XSSFCell phoneCell = rowContent.createCell(2);
//                    phoneCell.setCellValue(answerSheet.getPhoneNum());
//
//                    List<UpAppsAnswerSheetItem> itemList = answerSheetItemListMap.get(answerSheet.getId());
//                    for (UpAppsAnswerSheetItem item : itemList) {
//                        QuestionTypeEnum questionType = QuestionTypeEnum.valueOf(item.getQuestionType());
//                        String questionId = item.getQuestionId().toString();
//                        switch (questionType) {
//                            case 单选:
//                                switch (questionId) {
//                                    case "937325886479572994": //是否相关行业工作
//                                        XSSFCell range4 = rowContent.createCell(3);
//                                        range4.setCellValue(item.getValue());
//                                        break;
//                                    case "937325905710456833": //油品标号
//                                        XSSFCell range5 = rowContent.createCell(10);
//                                        range5.setCellValue(item.getValue());
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                break;
//                            case 多选:
//                                String value = item.getValue();
//                                Integer index = null;
//                                if (value != null) {
//                                    index = valueMap.get(item.getValue());
//                                }
//                                if (index != null) {
//                                    XSSFCell cell = rowContent.createCell(index);
//                                    cell.setCellValue("1");
//                                }
//                                break;
//                            case 分项满意度:
//                                switch (questionId) {
//                                    case "937325888111157250": //服务态度
//                                        XSSFCell range4 = rowContent.createCell(4);
//                                        range4.setCellValue(item.getValue());
//                                        break;
//                                    case "937325889893736449": //服务效率
//                                        XSSFCell range5 = rowContent.createCell(5);
//                                        range5.setCellValue(item.getValue());
//                                        break;
//                                    case "937325891747618818": //油品质量
//                                        XSSFCell range6 = rowContent.createCell(6);
//                                        range6.setCellValue(item.getValue());
//                                        break;
//                                    case "937325893362425857": //卫生环境
//                                        XSSFCell range7 = rowContent.createCell(7);
//                                        range7.setCellValue(item.getValue());
//                                        break;
//                                    case "937325895157587970": //促销优惠
//                                        XSSFCell range8 = rowContent.createCell(8);
//                                        range8.setCellValue(item.getValue());
//                                        break;
//                                    case "937325896847892481": //现场秩序
//                                        XSSFCell range9 = rowContent.createCell(9);
//                                        range9.setCellValue(item.getValue());
//                                        break;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } catch (Exception e) {
//                    logger.error("", e);
//                    e.printStackTrace();
//                }
//            }
//            lineNum = lineNum + answerSheetList.size();
//        }
//
//        FileOutputStream fout = new FileOutputStream("D:/中石油.xlsx");
//        workbook.write(fout);
//        fout.close();
//
//        Date end = new Date();
//        long total = (end.getTime() - start.getTime()) / 1000;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("总耗时：" + total + "秒  ");
//        return stringBuilder.toString();
        return "";
    }


    public void multiTask(int threadNum) {

        int size = 585000;
        int s = 585000 / threadNum;
        for (int i = 0; i < threadNum; i++) {
            taskExecutor.execute(new MultiThreadDemo(this, i * s,  s));
        }
    }


    @Override
    public String exportToDataBase() throws Exception {
        multiTask(39);
        return "";
//        Date start = new Date();
//        Long questionnaireId = 937325886211137537L; //true
//        Long companyId = 937325836747743234L; //true
//
//        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
//                new EntityWrapper<UpTenantsRegion>()
//                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, companyId)
//        );
//        Map<Long, String> regionMap = new HashMap<>();
//        for (UpTenantsRegion region : regionList) {
//            regionMap.put(region.getId(), region.getName());
//        }
//
//        int size = 585000;
//        int pageSize = 1000;
//        int pageNum = size / pageSize;
//
//        for (int num = 0; num < pageNum; num++) {
//            Date searchStart = new Date();
//            Page<AllCnpcData> page = allCnpcDataService.selectPage(new Page<>(num, pageSize));
//            List<AllCnpcData> cnpcDataList = page.getRecords();
//            List<String> phoneList = new ArrayList<>();
//            for (AllCnpcData cnpcData : cnpcDataList) {
//                phoneList.add(cnpcData.getPhoneNum());
//            }
//
//            List<UpAppsAnswerSheet> answerSheetList = upAppsAnswerSheetService.selectList(
//                    new EntityWrapper<UpAppsAnswerSheet>()
//                            .eq(UpAppsAnswerSheet.QUESTIONNAIRE_ID, questionnaireId)
//                            .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
//                            .in(UpAppsAnswerSheet.PHONE_NUM, phoneList)
//                            .orderBy(UpAppsAnswerSheet.FINISH_TIME, true)
//                            .groupBy(UpAppsAnswerSheet.PHONE_NUM)
//            );
//            List<Long> answerSheetIds = new ArrayList<>();
//            for (UpAppsAnswerSheet answerSheet : answerSheetList) {
//                answerSheetIds.add(answerSheet.getId());
//            }
//            List<UpAppsAnswerSheetItem> answerSheetItemList = upAppsAnswerSheetItemService.selectList(
//                    new EntityWrapper<UpAppsAnswerSheetItem>()
//                            .in(UpAppsAnswerSheetItem.ANSWER_SHEET_ID, answerSheetIds)
//            );
//            Date searchEnd = new Date();
//
//            Map<Long, List<UpAppsAnswerSheetItem>> answerSheetItemListMap = new HashMap<>();
//            for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
//                List<UpAppsAnswerSheetItem> answerSheetItemList1 = answerSheetItemListMap.get(answerSheetItem.getAnswerSheetId());
//                if (answerSheetItemList1 == null) {
//                    answerSheetItemList1 = new ArrayList<>();
//                }
//                answerSheetItemList1.add(answerSheetItem);
//                answerSheetItemListMap.put(answerSheetItem.getAnswerSheetId(), answerSheetItemList1);
//            }
//
//            Date insertStart = new Date();
//            logger.info("页码： " + num + "/" + pageNum);
//            logger.info("answerSheetList大小： " + answerSheetList.size());
//            for (int i = 0; i < answerSheetList.size(); i++) {
//                try {
//                    CnpcExportData cnpcExportData = new CnpcExportData();
//                    UpAppsAnswerSheet answerSheet = answerSheetList.get(i);
//
//                    String regionName = regionMap.get(answerSheet.getTenantsRegionId());
//                    cnpcExportData.setFinishTime(answerSheet.getFinishTime())
//                            .setProvince(regionName)
//                            .setPhoneNum(answerSheet.getPhoneNum());
//
//                    List<UpAppsAnswerSheetItem> itemList = answerSheetItemListMap.get(answerSheet.getId());
//                    for (UpAppsAnswerSheetItem item : itemList) {
//                        String questionId = item.getQuestionId().toString();
//                        String value = item.getValue();
//                        Integer score = item.getScore();
//                        switch (questionId) {
//                            case "937325886479572994": //是否相关行业工作
//                                cnpcExportData.setSo(value);
//                                break;
//                            case "937325905710456833": //油品标号
//                                cnpcExportData.setA4(value);
//                                break;
//                            case "937325888111157250": //服务态度
//                                cnpcExportData.setA11(score.toString());
//                                break;
//                            case "937325889893736449": //服务效率
//                                cnpcExportData.setA12(score.toString());
//                                break;
//                            case "937325891747618818": //油品质量
//                                cnpcExportData.setA13(score.toString());
//                                break;
//                            case "937325893362425857": //卫生环境
//                                cnpcExportData.setA14(score.toString());
//                                break;
//                            case "937325895157587970": //促销优惠
//                                cnpcExportData.setA15(score.toString());
//                                break;
//                            case "937325896847892481": //现场秩序
//                                cnpcExportData.setA16(score.toString());
//                                break;
//                            case "937325898634665986": //优点
//                                switch (value) {
//                                    case "优惠活动多" :
//                                        cnpcExportData.setA21("1");
//                                        break;
//                                    case "自助加油方便" :
//                                        cnpcExportData.setA22("1");
//                                        break;
//                                    case "油品数量充足" :
//                                        cnpcExportData.setA23("1");
//                                        break;
//                                    case "服务态度好" :
//                                        cnpcExportData.setA24("1");
//                                        break;
//                                    case "加油站位置方便" :
//                                        cnpcExportData.setA25("1");
//                                        break;
//                                    case "中国石油口碑好" :
//                                        cnpcExportData.setA26("1");
//                                        break;
//                                    case "服务效率快速" :
//                                        cnpcExportData.setA27("1");
//                                        break;
//                                    case "加油站分布多" :
//                                        cnpcExportData.setA28("1");
//                                        break;
//                                    case "加油卡使用方便" :
//                                        cnpcExportData.setA29("1");
//                                        break;
//                                    case "油品质量好" :
//                                        cnpcExportData.setA210("1");
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                break;
//                            case "937325901520347137": //缺点
//                                switch (value) {
//                                    case "业务操作不规范" :
//                                        cnpcExportData.setA31("1");
//                                        break;
//                                    case "服务效率低" :
//                                        cnpcExportData.setA32("1");
//                                        break;
//                                    case "服务态度差" :
//                                        cnpcExportData.setA33("1");
//                                        break;
//                                    case "便利店商品单一" :
//                                        cnpcExportData.setA34("1");
//                                        break;
//                                    case "便利店商品摆放混乱" :
//                                        cnpcExportData.setA35("1");
//                                        break;
//                                    case "加油卡业务办理繁琐" :
//                                        cnpcExportData.setA36("1");
//                                        break;
//                                    case "自助加油机少" :
//                                        cnpcExportData.setA37("1");
//                                        break;
//                                    case "优惠活动少" :
//                                        cnpcExportData.setA38("1");
//                                        break;
//                                    case "活动开展形式单一" :
//                                        cnpcExportData.setA39("1");
//                                        break;
//                                    case "便民设施少" :
//                                        cnpcExportData.setA310("1");
//                                        break;
//                                    case "整体站内环境差" :
//                                        cnpcExportData.setA311("1");
//                                        break;
//                                    case "油品质量差" :
//                                        cnpcExportData.setA312("1");
//                                        break;
//                                    case "油品数量不足" :
//                                        cnpcExportData.setA313("1");
//                                        break;
//                                    case "加油站位置不方便" :
//                                        cnpcExportData.setA314("1");
//                                        break;
//                                    case "加油站口碑差" :
//                                        cnpcExportData.setA315("1");
//                                        break;
//                                    case "以上均无" :
//                                        cnpcExportData.setA31(null);
//                                        cnpcExportData.setA32(null);
//                                        cnpcExportData.setA33(null);
//                                        cnpcExportData.setA34(null);
//                                        cnpcExportData.setA35(null);
//                                        cnpcExportData.setA36(null);
//                                        cnpcExportData.setA37(null);
//                                        cnpcExportData.setA38(null);
//                                        cnpcExportData.setA39(null);
//                                        cnpcExportData.setA310(null);
//                                        cnpcExportData.setA311(null);
//                                        cnpcExportData.setA312(null);
//                                        cnpcExportData.setA313(null);
//                                        cnpcExportData.setA314(null);
//                                        cnpcExportData.setA315(null);
//                                        cnpcExportData.setA316("1");
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//
//                    cnpcExportDataService.insert(cnpcExportData);
//                } catch (Exception e) {
//                    logger.error("", e);
//                    e.printStackTrace();
//                }
//            }
//            Date insertEnd = new Date();
//            long searchTime = (searchEnd.getTime() - searchStart.getTime()) / 1000;
//            long insertTime = (insertEnd.getTime() - insertStart.getTime()) / 1000;
//            logger.info("查询时间: " + searchTime + "秒，  插入时间：" + insertTime + "秒");
//        }
//
//        Date end = new Date();
//        long total = (end.getTime() - start.getTime()) / 1000;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("总耗时：" + total + "秒  ");
//        return stringBuilder.toString();
    }

    @Override
    public String exportToDataMultiTask(int startNum, int total) throws Exception {
//        Date start = new Date();
//        Long questionnaireId = 937325886211137537L; //true
//        Long companyId = 937325836747743234L; //true
//
//        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
//                new EntityWrapper<UpTenantsRegion>()
//                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, companyId)
//        );
//        Map<Long, String> regionMap = new HashMap<>();
//        for (UpTenantsRegion region : regionList) {
//            regionMap.put(region.getId(), region.getName());
//        }
//
//        int pageSize = 1000;
//        int pageNum = (startNum + total) / pageSize;
//        int offset = startNum / pageSize;
//
//        for (int num = offset; num < pageNum; num++) {
//            Date searchStart = new Date();
//            Page<AllCnpcData> page = allCnpcDataService.selectPage(new Page<>(num, pageSize));
//            List<AllCnpcData> cnpcDataList = page.getRecords();
//            List<String> phoneList = new ArrayList<>();
//            for (AllCnpcData cnpcData : cnpcDataList) {
//                phoneList.add(cnpcData.getPhoneNum());
//            }
//
//            List<UpAppsAnswerSheet> answerSheetList = upAppsAnswerSheetService.selectList(
//                    new EntityWrapper<UpAppsAnswerSheet>()
//                            .eq(UpAppsAnswerSheet.QUESTIONNAIRE_ID, questionnaireId)
//                            .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
//                            .in(UpAppsAnswerSheet.PHONE_NUM, phoneList)
//                            .orderBy(UpAppsAnswerSheet.FINISH_TIME, true)
//                            .groupBy(UpAppsAnswerSheet.PHONE_NUM)
//            );
//            List<Long> answerSheetIds = new ArrayList<>();
//            for (UpAppsAnswerSheet answerSheet : answerSheetList) {
//                answerSheetIds.add(answerSheet.getId());
//            }
//            List<UpAppsAnswerSheetItem> answerSheetItemList = upAppsAnswerSheetItemService.selectList(
//                    new EntityWrapper<UpAppsAnswerSheetItem>()
//                            .in(UpAppsAnswerSheetItem.ANSWER_SHEET_ID, answerSheetIds)
//            );
//            Date searchEnd = new Date();
//
//            Map<Long, List<UpAppsAnswerSheetItem>> answerSheetItemListMap = new HashMap<>();
//            for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
//                List<UpAppsAnswerSheetItem> answerSheetItemList1 = answerSheetItemListMap.get(answerSheetItem.getAnswerSheetId());
//                if (answerSheetItemList1 == null) {
//                    answerSheetItemList1 = new ArrayList<>();
//                }
//                answerSheetItemList1.add(answerSheetItem);
//                answerSheetItemListMap.put(answerSheetItem.getAnswerSheetId(), answerSheetItemList1);
//            }
//
//            Date insertStart = new Date();
//            logger.info(Thread.currentThread().getName());
//            logger.info("answerSheetList大小： " + answerSheetList.size());
//            logger.info("answerSheetList大小： " + answerSheetList.size());
//            for (int i = 0; i < answerSheetList.size(); i++) {
//                try {
//                    CnpcExportData cnpcExportData = new CnpcExportData();
//                    UpAppsAnswerSheet answerSheet = answerSheetList.get(i);
//
//                    String regionName = regionMap.get(answerSheet.getTenantsRegionId());
//                    cnpcExportData.setFinishTime(answerSheet.getFinishTime())
//                            .setProvince(regionName)
//                            .setPhoneNum(answerSheet.getPhoneNum());
//
//                    List<UpAppsAnswerSheetItem> itemList = answerSheetItemListMap.get(answerSheet.getId());
//                    for (UpAppsAnswerSheetItem item : itemList) {
//                        String questionId = item.getQuestionId().toString();
//                        String value = item.getValue();
//                        Integer score = item.getScore();
//                        switch (questionId) {
//                            case "937325886479572994": //是否相关行业工作
//                                cnpcExportData.setSo(value);
//                                break;
//                            case "937325905710456833": //油品标号
//                                cnpcExportData.setA4(value);
//                                break;
//                            case "937325888111157250": //服务态度
//                                cnpcExportData.setA11(score.toString());
//                                break;
//                            case "937325889893736449": //服务效率
//                                cnpcExportData.setA12(score.toString());
//                                break;
//                            case "937325891747618818": //油品质量
//                                cnpcExportData.setA13(score.toString());
//                                break;
//                            case "937325893362425857": //卫生环境
//                                cnpcExportData.setA14(score.toString());
//                                break;
//                            case "937325895157587970": //促销优惠
//                                cnpcExportData.setA15(score.toString());
//                                break;
//                            case "937325896847892481": //现场秩序
//                                cnpcExportData.setA16(score.toString());
//                                break;
//                            case "937325898634665986": //优点
//                                switch (value) {
//                                    case "优惠活动多" :
//                                        cnpcExportData.setA21("1");
//                                        break;
//                                    case "自助加油方便" :
//                                        cnpcExportData.setA22("1");
//                                        break;
//                                    case "油品数量充足" :
//                                        cnpcExportData.setA23("1");
//                                        break;
//                                    case "服务态度好" :
//                                        cnpcExportData.setA24("1");
//                                        break;
//                                    case "加油站位置方便" :
//                                        cnpcExportData.setA25("1");
//                                        break;
//                                    case "中国石油口碑好" :
//                                        cnpcExportData.setA26("1");
//                                        break;
//                                    case "服务效率快速" :
//                                        cnpcExportData.setA27("1");
//                                        break;
//                                    case "加油站分布多" :
//                                        cnpcExportData.setA28("1");
//                                        break;
//                                    case "加油卡使用方便" :
//                                        cnpcExportData.setA29("1");
//                                        break;
//                                    case "油品质量好" :
//                                        cnpcExportData.setA210("1");
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                break;
//                            case "937325901520347137": //缺点
//                                switch (value) {
//                                    case "业务操作不规范" :
//                                        cnpcExportData.setA31("1");
//                                        break;
//                                    case "服务效率低" :
//                                        cnpcExportData.setA32("1");
//                                        break;
//                                    case "服务态度差" :
//                                        cnpcExportData.setA33("1");
//                                        break;
//                                    case "便利店商品单一" :
//                                        cnpcExportData.setA34("1");
//                                        break;
//                                    case "便利店商品摆放混乱" :
//                                        cnpcExportData.setA35("1");
//                                        break;
//                                    case "加油卡业务办理繁琐" :
//                                        cnpcExportData.setA36("1");
//                                        break;
//                                    case "自助加油机少" :
//                                        cnpcExportData.setA37("1");
//                                        break;
//                                    case "优惠活动少" :
//                                        cnpcExportData.setA38("1");
//                                        break;
//                                    case "活动开展形式单一" :
//                                        cnpcExportData.setA39("1");
//                                        break;
//                                    case "便民设施少" :
//                                        cnpcExportData.setA310("1");
//                                        break;
//                                    case "整体站内环境差" :
//                                        cnpcExportData.setA311("1");
//                                        break;
//                                    case "油品质量差" :
//                                        cnpcExportData.setA312("1");
//                                        break;
//                                    case "油品数量不足" :
//                                        cnpcExportData.setA313("1");
//                                        break;
//                                    case "加油站位置不方便" :
//                                        cnpcExportData.setA314("1");
//                                        break;
//                                    case "加油站口碑差" :
//                                        cnpcExportData.setA315("1");
//                                        break;
//                                    case "以上均无" :
//                                        cnpcExportData.setA31(null);
//                                        cnpcExportData.setA32(null);
//                                        cnpcExportData.setA33(null);
//                                        cnpcExportData.setA34(null);
//                                        cnpcExportData.setA35(null);
//                                        cnpcExportData.setA36(null);
//                                        cnpcExportData.setA37(null);
//                                        cnpcExportData.setA38(null);
//                                        cnpcExportData.setA39(null);
//                                        cnpcExportData.setA310(null);
//                                        cnpcExportData.setA311(null);
//                                        cnpcExportData.setA312(null);
//                                        cnpcExportData.setA313(null);
//                                        cnpcExportData.setA314(null);
//                                        cnpcExportData.setA315(null);
//                                        cnpcExportData.setA316("1");
//                                        break;
//                                    default:
//                                        break;
//                                }
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//
//                    cnpcExportDataService.insert(cnpcExportData);
//                } catch (Exception e) {
//                    logger.error("", e);
//                    e.printStackTrace();
//                }
//            }
//            Date insertEnd = new Date();
//            long searchTime = (searchEnd.getTime() - searchStart.getTime()) / 1000;
//            long insertTime = (insertEnd.getTime() - insertStart.getTime()) / 1000;
//            logger.info("查询时间: " + searchTime + "秒，  插入时间：" + insertTime + "秒");
//        }
//
//        Date end = new Date();
//        long totalTime = (end.getTime() - start.getTime()) / 1000;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(Thread.currentThread().getName());
//        stringBuilder.append("总耗时：" + totalTime + "秒  ");
//        return stringBuilder.toString();
        return "";
    }

    @Override
    public void transferData(){
        Map<Long, String> regionMap = new HashMap<>();

        Wrapper<UpAppsAnswerSheet> wrapper = new EntityWrapper<UpAppsAnswerSheet>()
                .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
                .where("(finish_time - start_time) >= 20")
                .orderBy(UpAppsAnswerSheet.FINISH_TIME, true);

        int count = upAppsAnswerSheetService.selectCount(wrapper);
        int pageSize = 200;
        int pageCount = (int)((double)count / pageSize) + (((count % pageSize) > 0) ? 1 : 0);

        List<Long> failedIdList = new ArrayList<>();
        for (int num = 0; num < pageCount; num++) {
            Page<UpAppsAnswerSheet> page = upAppsAnswerSheetService.selectPage(
                    new Page<>(num, pageSize),
                    wrapper
            );

            List<UpAppsAnswerSheet> answerSheetList = page.getRecords();
            List<Long> answerSheetIds = new ArrayList<>();

            for (UpAppsAnswerSheet answerSheet : answerSheetList) {
                answerSheetIds.add(answerSheet.getId());
            }

            List<UpAppsAnswerSheetItem> answerSheetItemList = upAppsAnswerSheetItemService.selectList(
                    new EntityWrapper<UpAppsAnswerSheetItem>()
                            .in(UpAppsAnswerSheetItem.ANSWER_SHEET_ID, answerSheetIds)
                            .orderBy(UpAppsAnswerSheetItem.ANSWER_SHEET_ID, true)
                            .orderBy(UpAppsAnswerSheetItem.ADD_TIME, true)
            );

            Map<Long, List<UpAppsAnswerSheetItem>> answerSheetItemListMap = new HashMap<>();
            for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
                List<UpAppsAnswerSheetItem> answerSheetItemList1 = answerSheetItemListMap.get(answerSheetItem.getAnswerSheetId());
                if (answerSheetItemList1 == null) {
                    answerSheetItemList1 = new ArrayList<>();
                }
                answerSheetItemList1.add(answerSheetItem);
                answerSheetItemListMap.put(answerSheetItem.getAnswerSheetId(), answerSheetItemList1);
            }

            logger.info(Thread.currentThread().getName());
            logger.info("answerSheetList大小： " + answerSheetList.size());
            logger.info("answerSheetList大小： " + answerSheetList.size());

            for (UpAppsAnswerSheet upAppsAnswerSheet: answerSheetList) {
                List<UpAppsAnswerSheetItem> upAppsAnswerSheetItemList = answerSheetItemListMap.get(upAppsAnswerSheet.getId());
                TsAnswerSheet tsAnswerSheet = new TsAnswerSheet();
                tsAnswerSheet.setAnswerSheetId(upAppsAnswerSheet.getId());
                tsAnswerSheet.setQuestionnaireId(upAppsAnswerSheet.getQuestionnaireId());
                tsAnswerSheet.setOpenid(upAppsAnswerSheet.getOpenid());
                tsAnswerSheet.setTenantsRegionId(upAppsAnswerSheet.getTenantsRegionId());
                tsAnswerSheet.setAutoSubmit(upAppsAnswerSheet.getIsAutoSubmit() > 0);
                tsAnswerSheet.setStartTime(upAppsAnswerSheet.getStartTime());
                tsAnswerSheet.setFinished(upAppsAnswerSheet.getIsFinished() == 1);
                tsAnswerSheet.setFinishTime(upAppsAnswerSheet.getFinishTime());
                tsAnswerSheet.setFinishQuestionNum(upAppsAnswerSheet.getFinishQuestionNum());
                tsAnswerSheet.setFinishRequiredQuestionNum(upAppsAnswerSheet.getFinishRequiredQuestionNum());
                tsAnswerSheet.setFinishPrecentage(upAppsAnswerSheet.getFinishPrecentage());
                tsAnswerSheet.setQuickTag(upAppsAnswerSheet.getQuickTag());
                tsAnswerSheet.setOrderSn(upAppsAnswerSheet.getOrderSn());
                tsAnswerSheet.setCookies(upAppsAnswerSheet.getCookies());
                tsAnswerSheet.setIp(upAppsAnswerSheet.getIp());
                tsAnswerSheet.setAddTime(upAppsAnswerSheet.getAddTime());
                tsAnswerSheet.setUpdateTime(upAppsAnswerSheet.getUpdateTime());
                tsAnswerSheet.setPhoneNum(upAppsAnswerSheet.getPhoneNum());
                tsAnswerSheet.setEntrance(upAppsAnswerSheet.getEntrance() == null ? "LINK" : upAppsAnswerSheet.getEntrance());
                tsAnswerSheet.setBroswer(upAppsAnswerSheet.getBroswer() == null ? "其他" : upAppsAnswerSheet.getBroswer());
                tsAnswerSheet.setBroswerType(upAppsAnswerSheet.getBroswer() == null ? "其他" : upAppsAnswerSheet.getBroswer());
                tsAnswerSheet.setSystem("其他");
                tsAnswerSheet.setAccessLogId(0L);
                tsAnswerSheet.setItemList(upAppsAnswerSheetItemList);

                //保存电话号码
                TsAnswerSheetPhone tsAnswerSheetPhone = null;
                if (!ValueHelper.isNone(tsAnswerSheet.getPhoneNum())) {
                    tsAnswerSheetPhone = new TsAnswerSheetPhone();
                    tsAnswerSheetPhone.setRegionId(tsAnswerSheet.getTenantsRegionId());
                    tsAnswerSheetPhone.setQuestionnaireIdPhone(tsAnswerSheet.getQuestionnaireId() + tsAnswerSheet.getPhoneNum());
                    tsAnswerSheetPhone.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
                }

                try {
                    tableStoreService.putRow(tsAnswerSheet);
                    logger.error("LAST SUCC ID:" + tsAnswerSheet.getAnswerSheetId());
                    if (tsAnswerSheetPhone != null) {
                        tableStoreService.putRow(tsAnswerSheetPhone);
                    }

                    TsAnswerSheetIndex index = new TsAnswerSheetIndex();
                    index.setAnswerSheetId(tsAnswerSheet.getAnswerSheetId());
                    index.setQuestionnaireId(tsAnswerSheet.getQuestionnaireId());
                    tableStoreService.putRow(index);

                } catch (Exception e) {
                    failedIdList.add(tsAnswerSheet.getAnswerSheetId());
                    logger.error("LAST FAIL ID:" + tsAnswerSheet.getAnswerSheetId());
                }
            }

            logger.error("FAILED ID LIST:");
            logger.error(JSONObject.toJSONString(failedIdList));
        }

        logger.error("ALL FAILED ID LIST:");
        logger.error(JSONObject.toJSONString(failedIdList));
    }

    public static void copyCellStyle(XSSFCellStyle fromStyle,
                                     XSSFCellStyle toStyle) {
        toStyle.setAlignment(HorizontalAlignment.CENTER);
        //边框和边框颜色
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        //背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
//      toStyle.setFont(fromStyle.getFont(null));
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());//首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());//旋转
        toStyle.setWrapText(fromStyle.getWrapText());

    }


}
