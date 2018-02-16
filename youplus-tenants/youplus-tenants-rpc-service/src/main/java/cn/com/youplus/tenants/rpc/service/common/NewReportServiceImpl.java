package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.TimeRangeForm;
import cn.com.youplus.common.model.base.TableCol;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.model.tablestore.TsStatRegionAll;
import cn.com.youplus.common.model.tablestore.TsStatRegionPerDay;
import cn.com.youplus.common.model.tablestore.TsStatRegionQuestionPerDay;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestion;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionItem;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsLevelService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.common.NewReportService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("newReportService")
public class NewReportServiceImpl implements NewReportService {

    //region autowire
    private static Logger logger = Logger.getLogger(NewReportServiceImpl.class);

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private UpAppsQuestionnaireAttributeService upAppsQuestionnaireAttributeService;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunProperties aliyunProperties;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private TableStoreServiceImpl tableStoreService;

//    @Autowired
//    private AliyunOssService aliyunOssService;

    //endregion

    @Override
    public Map<String, Object> getOverviewPart1(UpTenantsCompany company, Long questionnaireId) throws Exception {
        UpTenantsRegion baseRegion = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.LEVEL, 0)
        );
        if (baseRegion == null) {
            throw new AlertException("该公司不存在总部");
        }
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        if (questionnaire == null) {
            throw new AlertException("该问卷不存在");
        }
        int regionCount = upTenantsRegionService.selectCount(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );

        Map<String, Object> result = new HashMap<>();
        TsStatRegionAll tsStatRegionAll = new TsStatRegionAll();
        tsStatRegionAll.setRegionId(baseRegion.getId());
        tsStatRegionAll.setQuestionnaireId(questionnaireId);
        boolean hasAll = tableStoreService.getRow(tsStatRegionAll);
//        if (!hasAll) {
//            throw new AlertException("没有数据");
//        }


        result.put("visitNum", tsStatRegionAll.getTotalVisitCnt());
        result.put("submitNum", tsStatRegionAll.getTotalFinishedCnt());
        result.put("regionAverageNum", tsStatRegionAll.getTotalFinishedCnt() / regionCount);
        result.put("submitRate", tsStatRegionAll.getFinishedRate() == null ? 0 : tsStatRegionAll.getFinishedRate());
        result.put("averageTime", tsStatRegionAll.getAverageTime());

        return result;
    }

    @Override
    public Map<String, Object> getOverviewPart2(UpTenantsCompany company, Long questionnaireId, int size) throws Exception {
        UpTenantsRegion baseRegion = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.LEVEL, 0)
        );
        if (baseRegion == null) {
            throw new AlertException("该公司不存在总部");
        }
        Long regionId = baseRegion.getId();

        Date now = new Date();
        int nowDays = ValueHelper.getDays(now);
        int startDays = nowDays - size + 1;

        List<TsStatRegionPerDay> conditionList = new ArrayList<>();
        for (Integer day = startDays; day <= nowDays; day++) {
            TsStatRegionPerDay tsStatRegionPerDay = new TsStatRegionPerDay();
            tsStatRegionPerDay.setRegionId(regionId);
            tsStatRegionPerDay.setQuestionnaireId(questionnaireId);
            tsStatRegionPerDay.setDays(day.longValue());
            conditionList.add(tsStatRegionPerDay);
        }
        List<TsStatRegionPerDay> perDayList = tableStoreService.batchGetRow(conditionList);
        Map<String, Object> result = new HashMap<>();
        List<String> tempList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        for (Integer day = startDays; day <= nowDays; day++) {
            Date resultDate = calendar.getTime();
            tempList.add(simpleDateFormat.format(resultDate));
            calendar.add(Calendar.DATE, -1);
        }
        List<String> dayList = new ArrayList<>();
        for (int i = tempList.size() - 1; i >= 0; i--) {
            dayList.add(tempList.get(i));
        }

        List<Integer> visitList = new ArrayList<>();
        List<Integer> submitList = new ArrayList<>();
        for (Integer day = startDays; day <= nowDays; day++) {
            boolean hasData = false;
            for (TsStatRegionPerDay tsStatRegionPerDay : perDayList) {
                if (tsStatRegionPerDay == null) {
                    throw new AlertException("null");
                }
                if (tsStatRegionPerDay.getDays() == null) {
                    throw new AlertException("null");
                }
                if (tsStatRegionPerDay.getDays().intValue() == day) {
                    hasData = true;
                    visitList.add(tsStatRegionPerDay.getTotalVisitCnt());
                    submitList.add(tsStatRegionPerDay.getTotalFinishedCnt());
                    perDayList.remove(tsStatRegionPerDay);
                    break;
                }
            }
            if (!hasData) {
                visitList.add(0);
                submitList.add(0);
            }
        }
        List<String> legendData = new ArrayList<>();
        legendData.add("问卷浏览量");
        legendData.add("问卷提交量");
        List<List<Integer>> seriesData = new ArrayList<>();
        seriesData.add(visitList);
        seriesData.add(submitList);
        result.put("legendData", legendData);
        result.put("xAxisData", dayList);
        result.put("seriesData", seriesData);
        return result;
    }

    @Override
    public Map<String, Object> getOverviewPart3(UpTenantsCompany company, Long questionnaireId) throws Exception {
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        if (questionnaire == null) {
            throw new AlertException("该问卷不存在");
        }
        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.LEVEL, 1)
        );
        if (regionList.size() > 50) {
            throw new AlertException("数据太多无法显示，请设置区域");
        }
        Map<String, Object> result = new HashMap<>();
        List<String> regionNameList = new ArrayList<>();
        List<Integer> submitList = new ArrayList<>();
        List<TsStatRegionAll> conditionList = new ArrayList<>();
        for (UpTenantsRegion region : regionList) {
            TsStatRegionAll tsStatRegionAll = new TsStatRegionAll();
            tsStatRegionAll.setQuestionnaireId(questionnaireId);
            tsStatRegionAll.setRegionId(region.getId());
            conditionList.add(tsStatRegionAll);
        }
        List<TsStatRegionAll> resultList = tableStoreService.batchGetRow(conditionList);
        for (UpTenantsRegion region : regionList) {
            regionNameList.add(region.getName());
            boolean hasData = false;
            for (TsStatRegionAll tsStatRegionAll : resultList) {
                if (tsStatRegionAll.getRegionId().equals(region.getId())) {
                    hasData = true;
                    submitList.add(tsStatRegionAll.getTotalFinishedCnt());
                    resultList.remove(tsStatRegionAll);
                    break;
                }
            }
            if (!hasData) {
                submitList.add(0);
            }
        }

        List<List<Integer>> seriesData = new ArrayList<>();
        seriesData.add(submitList);
        List<String> legendData = new ArrayList<>();
        legendData.add("数据");
//        result.put("regionNameList", regionNameList);
//        result.put("submitList", submitList);
        result.put("legendData", legendData);
        result.put("xAxisData", regionNameList);
        result.put("seriesData", seriesData);

        return result;
    }

    @Override
    public Map<String, Object> getDataAnalysis(TimeRangeForm form, UpTenantsCompany company) throws Exception {
        Long questionnaireId = form.getQuestionnaireId();
        Date startTime;
        Date endTime;
        if (form.getEndDate() == null) {
            endTime = new Date();
        } else {
            endTime = new Date(form.getEndDate());
        }
        if (form.getStartDate() == null) {
            UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
            startTime = questionnaire.getStartTime();
        } else {
            startTime = new Date(form.getStartDate());
        }
        int startDay = ValueHelper.getDays(startTime);
        int endDay = ValueHelper.getDays(endTime);
        if (startDay < endDay - 90) {
            startDay = endDay - 90;//最大数据不能超过100
        }
        UpTenantsRegion baseRegion = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.LEVEL, 0)
        );
        if (baseRegion == null) {
            throw new AlertException("该公司不存在总部网点");
        }
        List<UpAppsQuestion> questionList = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaireId)
                        .orderBy(UpAppsQuestion.QUESTION_ORDER, true)
        );
        if (questionList == null) {
            throw new AlertException("读取问卷信息错误");
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (UpAppsQuestion question : questionList) {
            List<TsStatRegionQuestionPerDay> conditionList = new ArrayList<>();
            for (Integer day = startDay; day <= endDay; day++) {
                TsStatRegionQuestionPerDay tsStatRegionQuestionPerDay = new TsStatRegionQuestionPerDay();
                tsStatRegionQuestionPerDay.setDays(day.longValue());
                tsStatRegionQuestionPerDay.setQuestionId(question.getId());
                tsStatRegionQuestionPerDay.setQuestionnaireId(questionnaireId);
                tsStatRegionQuestionPerDay.setRegionId(baseRegion.getId());
                conditionList.add(tsStatRegionQuestionPerDay);
            }
            List<TsStatRegionQuestionPerDay> questionPerDayList = tableStoreService.batchGetRow(conditionList);

            QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.valueOf(question.getQuestionType());
            Map<String, Integer> answerMap;
            List<TableCol> answerList;
            Map<String, Object> resultItem;
            List<String> titleList;
            int totalNum = 0;
            switch (questionTypeEnum) {
                case 个人信息:
                case 服务方式:
                case 其他:
                case 时间:
                case 排序:
                case 总体满意度:
                case 分项满意度:
                    resultItem = new HashMap<>();
                    resultItem.put("questionId", question.getId());
                    resultItem.put("questionTitle", question.getTitle());
                    resultItem.put("subTitle", question.getSubTitle());
                    resultItem.put("questionType", questionTypeEnum.getCode());
                    resultList.add(resultItem);
                    break;
                case 填空:

                //// TODO: 2018/2/8  需要测试
                case 手机验证:
                case 是非:
                case 单选:
                case 多选:
                case NPS:
                    answerMap = new HashMap<>();
                    totalNum = 0;
                    for (TsStatRegionQuestionPerDay tsStatRegionQuestionPerDay : questionPerDayList) {
                        answerMap = mergeMap(answerMap, tsStatRegionQuestionPerDay.getAnswerMap());
                        totalNum += tsStatRegionQuestionPerDay.getTotalCnt();
                    }

                    List<UpAppsQuestionItem> itemList1 = upAppsQuestionItemService.selectList(
                            new EntityWrapper<UpAppsQuestionItem>()
                                    .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, questionnaireId)
                                    .eq(UpAppsQuestionItem.QUESTION_ID, question.getId())
                                    .orderBy(UpAppsQuestionItem.SORT, true)
                    );
                    answerList = mapToList(answerMap, totalNum, itemList1, questionTypeEnum);
                    titleList = new ArrayList<>();
                    titleList.add("选项");
                    titleList.add("回答量");
                    titleList.add("百分比%");
                    resultItem = new HashMap<>();
                    resultItem.put("questionId", question.getId());
                    resultItem.put("titleList", titleList);
                    resultItem.put("questionTitle", question.getTitle());
                    resultItem.put("subTitle", question.getSubTitle());
                    resultItem.put("answerList", answerList);
                    resultItem.put("questionType", questionTypeEnum.getCode());
                    resultList.add(resultItem);
                    break;
                case 评分:
                    answerMap = new HashMap<>();
                    totalNum = 0;
                    for (TsStatRegionQuestionPerDay tsStatRegionQuestionPerDay : questionPerDayList) {
                        answerMap = mergeMap(answerMap, tsStatRegionQuestionPerDay.getAnswerMap());
                        totalNum += tsStatRegionQuestionPerDay.getTotalCnt();
                    }
                    List<UpAppsQuestionItem> itemList = upAppsQuestionItemService.selectList(
                            new EntityWrapper<UpAppsQuestionItem>()
                                    .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, questionnaireId)
                                    .eq(UpAppsQuestionItem.QUESTION_ID, question.getId())
                                    .orderBy(UpAppsQuestionItem.SORT, true)
                    );
                    answerList = scoreMapToList(answerMap, totalNum, itemList);
                    for (TableCol tableCol : answerList) {
                        for (UpAppsQuestionItem item : itemList) {
                            if (tableCol.getScore().equals(item.getScore().toString())) {
                                tableCol.setName(item.getDisplay());
                                break;
                            }
                        }
                    }
                    titleList = new ArrayList<>();
                    titleList.add("选项");
                    titleList.add("分值");
                    titleList.add("回答量");
                    titleList.add("百分比%");
                    resultItem = new HashMap<>();
                    resultItem.put("questionId", question.getId());
                    resultItem.put("titleList", titleList);
                    resultItem.put("questionTitle", question.getTitle());
                    resultItem.put("subTitle", question.getSubTitle());
                    resultItem.put("answerList", answerList);
                    resultItem.put("questionType", questionTypeEnum.getCode());
                    resultList.add(resultItem);
                    break;
                default:
                    throw new AlertException("读取问卷类型信息错误");
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("resultList", resultList);
        return result;
    }

    private Map<String, Integer> mergeMap(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> newMap = new HashMap<>();
        for (String key : map1.keySet()) {
            Integer val1 = map1.get(key);
            Integer val2 = map2.get(key);
            Integer newVal = 0;
            if (val1 != null && val2 != null) {
                newVal = val1 + val2;
            } else if (val1 != null) {
                newVal = val1;
            } else if (val2 != null) {
                newVal = val2;
            }
            newMap.put(key, newVal);
        }
        for (String key : map2.keySet()) {
            Integer val = map2.get(key);
            newMap.putIfAbsent(key, val);
        }
        return newMap;
    }

    private List<TableCol> mapToList(Map<String, Integer> map, int totalNum, List<UpAppsQuestionItem> itemList, QuestionTypeEnum questionTypeEnum) throws AlertException {
        List<TableCol> list = new ArrayList<>();
        if (totalNum == 0) {
            TableCol tableCol = new TableCol();
            tableCol.setName("没有数据");
            tableCol.setRate(0D);
            tableCol.setNum(0);
            list.add(tableCol);
            return list;
        }

        switch (questionTypeEnum) {
            case 填空:
                // TODO: 2018/2/8 需要测试
            case 手机验证:
                for (UpAppsQuestionItem item : itemList) {
                    Integer num = map.get(item.getPlaceholder());
                    TableCol tableCol = new TableCol();
                    if (num != null) {
                        tableCol.setName(item.getPlaceholder());
                        tableCol.setNum(num);
                        Double rate = tableCol.getNum().doubleValue() / totalNum * 100;
                        tableCol.setRate(rate);
                    } else {
                        tableCol.setName(item.getPlaceholder());
                        tableCol.setNum(0);
                        tableCol.setRate(0D);
                    }
                    list.add(tableCol);
                }
                break;
            default:
                for (UpAppsQuestionItem item : itemList) {
                    Integer num = map.get(item.getValue());
                    TableCol tableCol = new TableCol();
                    if (num != null) {
                        tableCol.setName(item.getValue());
                        tableCol.setNum(num);
                        Double rate = tableCol.getNum().doubleValue() / totalNum * 100;
                        tableCol.setRate(rate);
                    } else {
                        tableCol.setName(item.getValue());
                        tableCol.setNum(0);
                        tableCol.setRate(0D);
                    }
                    list.add(tableCol);
                }
                break;

        }
        return list;
    }

    private List<TableCol> scoreMapToList(Map<String, Integer> map, int totalNum, List<UpAppsQuestionItem> itemList) throws AlertException {
        List<TableCol> list = new ArrayList<>();
        if (totalNum == 0) {
            TableCol tableCol = new TableCol();
            tableCol.setName("没有数据");
            tableCol.setRate(0D);
            tableCol.setScore("0");
            tableCol.setNum(0);
            list.add(tableCol);
            return list;
        }
        for (UpAppsQuestionItem item : itemList) {
            Integer num = map.get(item.getValue());
            TableCol tableCol = new TableCol();
            if (num != null) {
                tableCol.setName(item.getValue());
                tableCol.setScore(item.getValue());
                tableCol.setNum(num);
                Double rate = tableCol.getNum().doubleValue() / totalNum * 100;
                tableCol.setRate(rate);
            } else {
                tableCol.setName(item.getValue());
                tableCol.setScore(item.getValue());
                tableCol.setNum(0);
                tableCol.setRate(0D);
            }
            list.add(tableCol);
        }
        return list;
    }
}