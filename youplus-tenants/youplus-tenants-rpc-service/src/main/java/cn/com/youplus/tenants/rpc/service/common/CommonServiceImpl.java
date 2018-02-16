package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.model.tablestore.TsStatRegionAll;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheet;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.common.CommonService;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("commonService")
public class CommonServiceImpl implements CommonService {

    //region autowire

    private static Logger logger = Logger.getLogger(CommonServiceImpl.class);

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

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
    private OSSClient ossClient;

    @Autowired
    private AliyunProperties aliyunProperties;

    //endregion

    @Override
    public List<Map<String, Object>> getProjectSummary(UpTenantsCompany company) throws Exception {
        List<UpAppsQuestionnaire> questionnaireList = upAppsQuestionnaireService.selectList(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .notIn(UpAppsQuestionnaire.STATUS, QuestionnaireStatusEnum.已删除.getType())
        );

        List<Map<String, Object>> list = new ArrayList<>();
        for (UpAppsQuestionnaire questionnaire : questionnaireList) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", questionnaire.getTitle());
            map.put("projectName", questionnaire.getProjectName());
            map.put("addTime", questionnaire.getAddTime());
            map.put("status", questionnaire.getStatus());
            map.put("statusCode", QuestionnaireStatusEnum.valueOf(questionnaire.getStatus()).getCode());
            map.put("id", questionnaire.getId());
            map.put("startTime", questionnaire.getStartTime());
            map.put("expiredTime", questionnaire.getExpiredTime());

            UpTenantsRegion baseRegion = upTenantsRegionService.selectOne(
                    new EntityWrapper<UpTenantsRegion>()
                            .eq(UpTenantsRegion.LEVEL, 0)
                            .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
            );
            TsStatRegionAll tsStatRegionAll = new TsStatRegionAll();
            tsStatRegionAll.setRegionId(baseRegion.getId());
            tsStatRegionAll.setQuestionnaireId(questionnaire.getId());
            tableStoreService.getRow(tsStatRegionAll);
            Integer reviewNum = tsStatRegionAll.getTotalVisitCnt();
            Integer answerNum = tsStatRegionAll.getTotalFinishedCnt();

            map.put("reviewNum", reviewNum);
            map.put("answerNum", answerNum);
            list.add(map);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getRecycleBinProject(UpTenantsCompany company) {
        List<UpAppsQuestionnaire> questionnaireList = upAppsQuestionnaireService.selectList(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpAppsQuestionnaire.STATUS, QuestionnaireStatusEnum.已删除.getType())
        );

        List<Map<String, Object>> list = new ArrayList<>();
        for (UpAppsQuestionnaire questionnaire : questionnaireList) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", questionnaire.getTitle());
            map.put("projectName", questionnaire.getProjectName());
            map.put("addTime", questionnaire.getAddTime());
            map.put("status", questionnaire.getStatus());
            map.put("statusCode", QuestionnaireStatusEnum.valueOf(questionnaire.getStatus()).getCode());
            map.put("id", questionnaire.getId());
            map.put("startTime", questionnaire.getStartTime());
            map.put("expiredTime", questionnaire.getExpiredTime());
            map.put("deleteTime", questionnaire.getDeleteTime());

            int answerNum = upAppsAnswerSheetService.selectCount(
                    new EntityWrapper<UpAppsAnswerSheet>()
                            .eq(UpAppsAnswerSheet.QUESTIONNAIRE_ID, questionnaire.getId())
                            .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
            );

            map.put("answerNum", answerNum);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getProjects(UpTenantsCompany company) {
        List<UpAppsQuestionnaire> questionnaireList = upAppsQuestionnaireService.selectList(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company)
                        .notIn(UpAppsQuestionnaire.STATUS, QuestionnaireStatusEnum.已删除)
        );

        List<Map<String, Object>> list = new ArrayList<>();
        for (UpAppsQuestionnaire questionnaire : questionnaireList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", questionnaire.getId());
            map.put("title", questionnaire.getTitle());
            list.add(map);
        }
        return list;
    }

}