package cn.com.youplus.tenants.server.controller.tenants;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.base.api.SystemConfigService;
import cn.com.youplus.common.annotation.Permission;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.form.*;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.base.TenantsRegion;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.tablestore.*;
import cn.com.youplus.common.mybatis.FilterWarpper;
import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.SnowFlake;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsTask;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsTaskService;
import cn.com.youplus.tenants.api.common.DataStructureService;
import cn.com.youplus.tenants.api.common.ExportTaskService;
import cn.com.youplus.tenants.api.common.NewReportService;
import cn.com.youplus.tenants.common.form.ExportDataForm;
import cn.com.youplus.tenants.common.form.ReportMapForm;
import cn.com.youplus.tenants.server.controller.SuperController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/25.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/report")
public class TenantsReportController extends SuperController{

    //region autowired
    private static Logger logger = LoggerFactory.getLogger(TenantsReportController.class);

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private UpAppsAnswerSheetItemService upAppsAnswerSheetItemService;

    @Autowired
    private UpAppsAnswerSheetService upAppsAnswerSheetService;

    @Autowired
    private UpAppsAnswerSheetItemDetailViewService upAppsAnswerSheetItemDetailViewService;

    @Autowired
    private UpAppsAnswerSheetDetailViewService upAppsAnswerSheetDetailViewService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsAnswerSheetViewService upAppsAnswerSheetViewService;

    @Autowired
    private UpAppsAnswerSheetItemViewService upAppsAnswerSheetItemViewService;

    @Autowired
    private ExportTaskService exportTaskService;

    @Autowired
    private UpTenantsTaskService upTenantsTaskService;

    @Autowired
    private NewReportService newReportService;

    @Autowired
    private DataStructureService dataStructureService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Resource(name = "tableStoreService")
    private TableStoreServiceImpl tableStoreService;

    @Autowired
    private SyncClient syncClient;

    @ExceptionHandler
    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request,response,exception);
    }
    //endregion

    //region 对象组装器

    private Map<String, Object> assembleBarChartData(String name, List<String> data, String color, int width) {
        Map<String, Object> series = new HashMap<>();
        series.put("name", name);
        series.put("type", "bar");
        series.put("barWidth", width);

        Map<String, Object> itemStyle = new HashMap<>();
        Map<String, Object> normal = new HashMap<>();
        normal.put("color", color);
        itemStyle.put("normal", normal);
        series.put("itemStyle", itemStyle);
        series.put("data", data);

        return series;
    }

    Map<String, Object> asumbleLineChartData(String name, List<String> data, String itemColor, String aeraColor) {
        Map<String, Object> res = new HashMap<>();
        res.put("name", name);
        res.put("type", "line");
        res.put("stack", "总量");

        Map<String, Object> labelNormal = new HashMap<>();
        labelNormal.put("show", false);

        Map<String, Object> label = new HashMap<>();
        label.put("normal", labelNormal);

        res.put("label", label);

        Map<String, Object> itemStyle = new HashMap<>();
        Map<String, Object> normalItemStyle = new HashMap<>();
        normalItemStyle.put("color", itemColor);
        itemStyle.put("normal", normalItemStyle);
        res.put("itemStyle", itemStyle);

        Map<String, Object> areaStyle = new HashMap<>();
        Map<String, Object> normalAeraStyle = new HashMap<>();
        normalAeraStyle.put("color", aeraColor);
        normalAeraStyle.put("opacity", 1);
        areaStyle.put("normal", normalAeraStyle);
        res.put("areaStyle", areaStyle);
        res.put("data", data);
        return res;
    }

    //endregion

    //region 刷选过滤选项

    List<TenantsRegion> 读取组织框架(Long id) {
         // 一定要注意排序
        List<UpTenantsRegion> list = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, id)
                        .orderBy(UpTenantsRegion.LEVEL)
                        .orderBy(UpTenantsRegion.PARENT_ID)
                        .orderBy(UpTenantsRegion.ID)
        );
        Map<Long, TenantsRegion> fastMap = new HashMap<>();
        TenantsRegion regions = new TenantsRegion();
        list.forEach(region -> {
            if (region.getLevel() == 0) {
                TenantsRegion regionL0 = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(0)
                        .setLabel(region.getName())
                        .setQuickTag(region.getQuickTag())
                        .setParentId(0L);
                if (regions.getChildren() == null) {
                    regions.setChildren(new ArrayList<>());
                }
                regions.getChildren().add(regionL0);
                fastMap.put(region.getId(), regionL0);
            } else {
                //get parent
                if (region.getParentId() <= 0) {
                    return;
                }
                TenantsRegion regionParent = fastMap.get(region.getParentId());
                if (regionParent == null) {
                    return;
                }
                if (regionParent.getChildren() == null) {
                    regionParent.setChildren(new ArrayList<>());
                }
                TenantsRegion regionLx = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(region.getLevel())
                        .setLabel(region.getName())
                        .setQuickTag(region.getQuickTag())
                        .setParentId(region.getParentId());
                regionParent.getChildren().add(regionLx);
                fastMap.put(region.getId(), regionLx);
            }
        });
        return regions.getChildren().get(0).getChildren();
    }

    TenantsRegion 读取组织框架_单树结构(Long id) {
        // 一定要注意排序
        List<UpTenantsRegion> list = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, id)
                        .orderBy(UpTenantsRegion.LEVEL)
                        .orderBy(UpTenantsRegion.PARENT_ID)
                        .orderBy(UpTenantsRegion.ID)
        );
        Map<Long, TenantsRegion> fastMap = new HashMap<>();
        TenantsRegion regions = new TenantsRegion();
        regions.setId(0L)
                .setLevel(0)
                .setLabel("总部")
                .setQuickTag("")
                .setChildren(new ArrayList<>())
                .setParentId(0L);
        list.forEach(region -> {
            if (region.getLevel() == 1) {
                TenantsRegion regionL0 = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(1)
                        .setLabel(region.getName())
                        .setQuickTag(region.getQuickTag())
                        .setParentId(0L);
                regions.getChildren().add(regionL0);
                fastMap.put(region.getId(), regionL0);
            } else {
                //get parent
                if (region.getParentId() <= 0) {
                    return;
                }
                TenantsRegion regionParent = fastMap.get(region.getParentId());
                if (regionParent.getChildren() == null) {
                    regionParent.setChildren(new ArrayList<>());
                }
                TenantsRegion regionLx = new TenantsRegion()
                        .setId(region.getId())
                        .setLevel(region.getLevel())
                        .setLabel(region.getName())
                        .setQuickTag(region.getQuickTag())
                        .setParentId(region.getParentId());
                regionParent.getChildren().add(regionLx);
                fastMap.put(region.getId(), regionLx);
            }
        });
        return regions;
    }

    UpAppsQuestionnaire getQuestionnaire(Long id, Long companyId) throws Exception {
        UpAppsQuestionnaire questionnaire = null;
        if (!ValueHelper.isNoneOrZero(id)) {
            questionnaire = upAppsQuestionnaireService.selectById(id);
        } else {
            throw new Exception("请指定问卷项目ID");
            /*questionnaire = upAppsQuestionnaireService.selectOne(
                    new EntityWrapper<UpAppsQuestionnaire>()
                            .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, companyId)
                            .orderBy(UpAppsQuestionnaire.IS_DEFAULT, false)
                            .orderBy(UpAppsQuestionnaire.ADD_TIME, false)
            );*/
        }
        return questionnaire;
    }

    /**
     *
     * @param form 这里的id不需要 Valid 校验
     * @return
     * @throws Exception
     */
    @Permission(Permission.T_首页 + Permission.GET)
    @PostMapping("/filters")
    public BaseResponse<Map<String, Object>> tenantsFiltersGet(BaseIdForm form) throws Exception {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        logger.debug("开始:" + new Date().toString());

        UpAppsQuestionnaire questionnaire = getQuestionnaire(form.getId(), tenantsCompany.getId());
        if (questionnaire == null) {
            throw new AlertException("问卷不存在!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        Date now = new Date();

        Map<String, Object> resData = new HashMap<>();
        resData.put("start", Constants.DATE_FORMAT.format(calendar.getTime()));
        resData.put("end", Constants.DATE_FORMAT.format(now));
        resData.put("periods", ValueHelper.tryParseJson(questionnaire.getPeriodJson(), null));
        resData.put("serviceType", ValueHelper.tryParseJson(questionnaire.getServiceTypeJson(), null));
        resData.put("siteName", questionnaire.getSiteName());
        //读取组织框架
        logger.debug("读取组织框架_单树结构:" + new Date().toString());
        resData.put("regions", dataStructureService.读取组织框架_单树结构(tenantsCompany.getId()).getChildren());
        logger.debug("完成:" + new Date().toString());
//        resData.put("regions", 读取组织框架(tenantsCompany.getId()));
        response.setData(resData);
        return response;
    }

    //endregion

    public static void main(String []args) throws IOException {
        String content = FileUtils.readFileToString(new File("velocity.log"), "utf-8");
        logger.debug(content);
    }

    @Permission(Permission.T_首页 + Permission.GET)
    @PostMapping("/test")
    public BaseResponse<Map<String, Object>> text() throws Exception {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);
        Map<String, Object> resData = new HashMap<>();

        resData.put("welcome", systemConfigService.getTenantsParam(SystemConfigService.TENANTS_WELCOME_KEY));
        resData.put("startTime", tenantsCompany.getAddTime());
        response.setData(resData);
        return response;
    }

    // TODO: 2017/11/9/009 配置功能块权限 (在permission里添加function)

    //region 首页
    @Permission(Permission.T_首页 + Permission.GET)
    @PostMapping("/home")
    public BaseResponse<Map<String, Object>> tenantsHomeGet() throws Exception {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);
        Map<String, Object> resData = new HashMap<>();
        resData.put("welcome", systemConfigService.getTenantsParam(SystemConfigService.TENANTS_WELCOME_KEY));
        resData.put("startTime", tenantsCompany.getAddTime());
        response.setData(resData);
        return response;
    }
    //endregion

    //region 总体满意度

    @Permission(value = Permission.T_报告接口 + Permission.T_总体满意度 + Permission.GET)
    @PostMapping("/zongTiManYiDu")
    public BaseResponse<Map<String, Object>> tenantsZongTiManYiDuGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        Map<String, Object> resultTotal = upAppsAnswerSheetItemService
                .selectMap(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filter(form, UpAppsAnswerSheetItem.class)
                        .setSqlSelect(String.format("SUM(%s) as score, SUM(%s) as total ", UpAppsAnswerSheetItem.SCORE, UpAppsAnswerSheetItem.MAX_SCORE))
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                );
        Map<String, Object> resData = new HashMap<>();
        if (resultTotal == null) {
            resData.put("rightStr", "0%");
            resData.put("subTitle", "0%");
            response.setData(resData);
            return response;
        }
        int score = ValueHelper.tryParse(resultTotal.get("score").toString(), 0);
        int total = ValueHelper.tryParse(resultTotal.get("total").toString(), 1);

        //针对90天前对比数据：注意 form 的内容将被修改
        Map<String, Object> resultOld = upAppsAnswerSheetItemService
                .selectMap(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filterBefore(form, UpAppsAnswerSheetItem.class, Constants.NPS_DAYS)
                        .setSqlSelect(String.format("SUM(%s) as score, SUM(%s) as total ", UpAppsAnswerSheetItem.SCORE, UpAppsAnswerSheetItem.MAX_SCORE))
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                );

        BigDecimal percentage = ValueHelper.rint((float)score * 100 / total);
        if (resultOld != null) {
            score = ValueHelper.tryParse(resultOld.get("score").toString(), 0);
            total = ValueHelper.tryParse(resultOld.get("total").toString(), 1);

            if (score > 0) {
                BigDecimal decimal = ValueHelper.rint(percentage.floatValue () -(float)score * 100 / total);
                if (decimal.floatValue() > 0) {
                    resData.put("rightStr", "+" + decimal + "%");
                } else {
                    resData.put("rightStr",decimal  + "%");
                }
            } else {
                resData.put("rightStr", "0%");
            }
        } else {
            resData.put("rightStr", "0%");
        }

        resData.put("subTitle", percentage + "%");
        response.setData(resData);
        return response;
    }

    @Permission(Permission.T_报告接口 + Permission.T_总体满意度 + Permission.GET)
    @PostMapping("/zongTiManYiDuData")
    public BaseResponse<Map<String, Object>> tenantsZongTiManYiDuDataGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        List<Map<String, Object>> resultArray = upAppsAnswerSheetItemService
                .selectMaps(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filter(form, UpAppsAnswerSheetItem.class)
                        .setSqlSelect(String.format("SUM(%s) as score, SUM(%s) as total ", UpAppsAnswerSheetItem.SCORE, UpAppsAnswerSheetItem.MAX_SCORE)  + ", DATE_FORMAT(add_time,'%Y-%m-%d') as day")
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                        //.gt(UpAppsAnswerSheetItem.ADD_TIME, new Date(new Date().getTime() - Constants.RECORD_DAYS * Constants.ONE_DAY))
                        .groupBy("day")
                );
        List<String> axis = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        resultArray.forEach( map -> {
            axis.add((String)map.get("day"));
            int s = ValueHelper.tryParse(map.get("score").toString(), 0);
            int t = ValueHelper.tryParse(map.get("total").toString(), 1);
            BigDecimal precentage = ValueHelper.rint((float)s * 100 / t);
            dataList.add(precentage.toString());
        });

        Map<String, Object> resData = new HashMap<>();

        response.setData(resData);
        return response;
    }

    //endregion

    //region NPS净推荐值

    @Permission(Permission.T_报告接口 + Permission.T_NPS净推荐值 + Permission.GET)
    @PostMapping("/npsJingTuiJianZhi")
    public BaseResponse<Map<String, Object>> tenantsNpsJingTuiJianZhiGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //NPS净推荐值
        Map<String, Object> result = upAppsAnswerSheetItemService
                .selectMap(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filter(form, UpAppsAnswerSheetItem.class)
                        .setSqlSelect(String.format("SUM(%s <= '%d') as lnum, SUM(%s >= '%s') as hnum, COUNT(id) AS total",
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_LOW_SCORE,
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_HIGHT_SCORE))
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                );

        BigDecimal allPercentage = ValueHelper.getNps(result.get("hnum"), result.get("lnum"),result.get("total"));

        //针对90天前对比数据：注意 form 的内容将被修改
        Map<String, Object> resultOld = upAppsAnswerSheetItemService
                .selectMap(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filterBefore(form, UpAppsAnswerSheetItem.class, Constants.NPS_DAYS)
                        .setSqlSelect(String.format("SUM(%s <= '%d') as lnum, SUM(%s >= '%s') as hnum, COUNT(id) AS total",
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_LOW_SCORE,
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_HIGHT_SCORE))
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                );

        Map<String, Object> resData = new HashMap<>();
        if (resultOld != null) {
            BigDecimal oldPercentage = ValueHelper.getNps(resultOld.get("hnum"), resultOld.get("lnum"),resultOld.get("total"));
            oldPercentage = ValueHelper.rint(allPercentage.floatValue() - oldPercentage.floatValue());
            if (oldPercentage.floatValue() > 0) {
                resData.put("rightStr", "+" + oldPercentage + "%");
            } else {
                resData.put("rightStr", oldPercentage + "%");
            }
        } else {
            resData.put("rightStr", "0%");
        }
        resData.put("subTitle", allPercentage + "%");
        response.setData(resData);
        return response;
    }

    @Permission(Permission.T_报告接口 + Permission.T_NPS净推荐值 + Permission.GET)
    @PostMapping("/npsJingTuiJianZhiData")
    public BaseResponse<Map<String, Object>> tenantsNpsJingTuiJianZhiDataGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        List<Map<String, Object>> resultArray = upAppsAnswerSheetItemService
                .selectMaps(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filter(form, UpAppsAnswerSheetItem.class)
                        .setSqlSelect(String.format("SUM(%s <= '%d') as lnum, SUM(%s >= '%s') as hnum, COUNT(id) AS total",
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_LOW_SCORE,
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_HIGHT_SCORE)  + ", DATE_FORMAT(add_time,'%Y-%m-%d') as day")
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                        //.gt(UpAppsAnswerSheetItem.ADD_TIME, new Date(new Date().getTime() - Constants.RECORD_DAYS * Constants.ONE_DAY))
                        .groupBy("day")
                );

        List<String> axis = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        resultArray.forEach( map -> {
            axis.add((String)map.get("day"));
            dataList.add(ValueHelper.getNps(map.get("hnum"), map.get("lnum"),map.get("total")).toString());
        });

        Map<String, Object> resData = new HashMap<>();
        resData.put("axis", axis);

        //resData.put("lineData", asumbleLineChartData("NPS净推荐值", dataList, "#20A0FF", "blue"));
        response.setData(resData);
        return response;
    }

    /**
     * NPS 分项数据
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_NPS净推荐值 + Permission.GET)
    @PostMapping("/npsFenXiangData")
    public BaseResponse<Map<String, Object>> tenantsNpsFenXiangDataGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        Map<String, Object> map = upAppsAnswerSheetItemService
                .selectMap(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filter(form, UpAppsAnswerSheetItem.class)
                        .setSqlSelect(String.format("SUM(%s <= '%d') as lnum, SUM(%s >= '%s') as hnum, COUNT(id) AS total",
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_LOW_SCORE,
                                UpAppsAnswerSheetItem.SCORE,
                                Constants.NPS_HIGHT_SCORE))
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType())
                );

        List<String> axis = new ArrayList<>();
        axis.add("NPS分项指数");

        List<String> legendData = new ArrayList<>();
        legendData.add("批评者");
        legendData.add("中立者");
        legendData.add("推荐者");

        List<String> dataListH = new ArrayList<>();
        List<String> dataListM = new ArrayList<>();
        List<String> dataListL = new ArrayList<>();

        axis.add((String)map.get("day"));
        int h = ValueHelper.tryParse(map.get("hnum"), 0);
        int l = ValueHelper.tryParse(map.get("lnum"), 0);
        int t = ValueHelper.tryParse(map.get("total"), 1);
        int m = t - h - l;
        if (m < 0) {
            m = 0;
        }

        dataListH.add(ValueHelper.rint((float)h * 100 / t).toString());
        dataListM.add(ValueHelper.rint((float)m * 100 / t).toString());
        dataListL.add(ValueHelper.rint((float)l * 100 / t).toString());

        Map<String, Object> resData = new HashMap<>();
        List<Map<String, Object>> seriesData = new ArrayList<>();
        seriesData.add(assembleBarChartData(legendData.get(0), dataListL, "red", 21));
        seriesData.add(assembleBarChartData(legendData.get(1), dataListM, "yellow", 21));
        seriesData.add(assembleBarChartData(legendData.get(2), dataListH, "green", 21));

        resData.put("legendData", legendData);
        resData.put("xAxisData", axis);
        resData.put("seriesData", seriesData);

        response.setData(resData);
        return response;
    }

    //endregion

    //region 总警报

    /**
     * 计算总的警报数量
     * @param id
     * @return
     */
    private Map<String, Object> 获取警报数量(Long id, ReportFilterForm form) {
        return upAppsAnswerSheetService
                .selectMap(new FilterWarpper<UpAppsAnswerSheet>()
                        .filter(form, UpAppsAnswerSheet.class)
                        .setSqlSelect(String.format("SUM(%s) as slove, SUM(%s) as total",
                                UpAppsAnswerSheet.IS_SLOVED,
                                UpAppsAnswerSheet.IS_DANGEROUS))
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, id)
                        .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
                );
    }

    /**
     * 得到待解决的警报数量
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_警报 + Permission.GET)
    @PostMapping("/daiJieJueJingBao")
    public BaseResponse<Map<String, Object>> tenantsDaiJieJueJingBaoGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        Map<String, Object> result = 获取警报数量(tenantsCompany.getId(), form);
        int sloveNum = ValueHelper.tryParse(result.get("slove").toString(), 0);
        int totalNum = ValueHelper.tryParse(result.get("total").toString(), 0);

        Map<String, Object> data = new HashMap<>();
        data.put("value", totalNum - sloveNum);
        response.setData(data);
        return response;
    }

    /**
     * 总警报数量
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_警报 + Permission.GET)
    @PostMapping("/zongJingBao")
    public BaseResponse<Map<String, Object>> tenantsZongJingBaoGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        Map<String, Object> result = 获取警报数量(tenantsCompany.getId(), form);
        int totalNum = ValueHelper.tryParse(result.get("total").toString(), 0);

        Map<String, Object> data = new HashMap<>();
        data.put("value", totalNum);
        response.setData(data);
        return response;
    }

    //endregion

    //region 反馈&提交

    /**
     * 获取反馈提交数
     * @param id
     * @return
     */
    private Map<String, Object> 获取反馈提交数(Long id, ReportFilterForm form) {
        return upAppsAnswerSheetService
                .selectMap(new FilterWarpper<UpAppsAnswerSheet>()
                        .filter(form, UpAppsAnswerSheet.class)
                        .setSqlSelect("COUNT(*) as count, SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum")
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, id)
                );
    }

    /**
     * 反馈提交数量,跟90天前需要对比
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_反馈提交 + Permission.GET)
    @PostMapping("/fanKuiTiJiaoDuiBi")
    public BaseResponse<Map<String, Object>> tenantsFanKuiTiJiaoDuiBiGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        Map<String, Object> result = 获取反馈提交数(tenantsCompany.getId(),form);
        Map<String, Object> resultOld = upAppsAnswerSheetService
                .selectMap(new FilterWarpper<UpAppsAnswerSheet>()
                        .filterBefore(form, UpAppsAnswerSheet.class, Constants.NPS_DAYS)
                        .setSqlSelect("COUNT(*) as count, SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum")
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, tenantsCompany.getId())
                );
        Map<String, Object> resData = new HashMap<>();
        int tjs = 0;
        int tjsDelta = 0;
        if (result != null) {
            tjs = ValueHelper.tryParse(result.get("finishNum"), 0);
        } else {
            tjs = 0;
        }

        if (resultOld != null) {
            tjsDelta = ValueHelper.tryParse(resultOld.get("finishNum"), 0);
            tjsDelta = tjs - tjsDelta;
        } else {
            tjsDelta = 0;
        }
        resData.put("subTitle", tjs);

        if (tjsDelta > 0) {
            resData.put("rightStr", "+" + tjsDelta);
        } else {
            resData.put("rightStr", "" + tjsDelta);
        }
        response.setData(resData);
        return response;
    }

    /**
     * 反馈提交数量
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_反馈提交 + Permission.GET)
    @PostMapping("/fanKuiTiJiao")
    public BaseResponse<Map<String, Object>> tenantsFanKuiTiJiaoGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        Map<String, Object> result = 获取反馈提交数(tenantsCompany.getId(),form);
        Map<String, Object> resData = new HashMap<>();
        if (result != null) {
            int tjs = ValueHelper.tryParse(result.get("count"), 0);
            resData.put("value", tjs);
        } else {
            resData.put("value", 0);
        }
        response.setData(resData);
        return response;
    }


    /**
     * 反馈提交率
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_反馈提交 + Permission.GET)
    @PostMapping("/fanKuiTiJiaoLv")
    public BaseResponse<Map<String, Object>> tenantsFanKuiTiJiaoLvGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        Map<String, Object> result = upAppsAnswerSheetService
                .selectMap(new FilterWarpper<UpAppsAnswerSheet>()
                        .filter(form, UpAppsAnswerSheet.class)
                        .setSqlSelect("COUNT(*) as count, SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum, SUM(" + UpAppsAnswerSheet.IS_COMMENTED + ") commentNum")
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, tenantsCompany.getId())
                );

        Map<String, Object> resData = new HashMap<>();
        if (result != null) {
            int 提交数 = ValueHelper.tryParse(result.get("count"), 0);
            int 完成数 = ValueHelper.tryParse(result.get("finishNum"), 0);
            int 评论数 = ValueHelper.tryParse(result.get("commentNum"), 0);

            resData.put("subTitle", 完成数);
            String value = ValueHelper.rint((float)评论数 * 100 / 完成数) + "%";
            resData.put("rightStr", value);
            resData.put("value", value);
        } else {
            resData.put("subTitle", "0");
            resData.put("rightStr", "0.00%");
            resData.put("value", "0.00%");
        }
        response.setData(resData);
        return response;
    }

    /**
     * 反馈提交率对比
     * @param form
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_反馈提交 + Permission.GET)
    @PostMapping("/fanKuiPingLunLvDuiBi")
    public BaseResponse<Map<String, Object>> tenantsFanKuiTiJiaoLvDuiBiGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        Map<String, Object> result = upAppsAnswerSheetService
                .selectMap(new FilterWarpper<UpAppsAnswerSheet>()
                        .filter(form, UpAppsAnswerSheet.class)
                        .setSqlSelect("COUNT(*) as count, SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum, SUM(" + UpAppsAnswerSheet.IS_COMMENTED + ") commentNum")
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
                );

        Map<String, Object> resData = new HashMap<>();
        int 完成数 = 0;
        int 评论数 = 0;
        if (result != null) {
            完成数 = ValueHelper.tryParse(result.get("finishNum"), 0);
            评论数 = ValueHelper.tryParse(result.get("commentNum"), 0);

            String value = ValueHelper.rint((float)评论数 * 100 / 完成数) + "%";
            resData.put("subTitle", value);
        } else {
            resData.put("subTitle", "0");
        }

        //总体满意度计算
        Map<String, Object> resultOld = upAppsAnswerSheetService
                .selectMap(new FilterWarpper<UpAppsAnswerSheet>()
                        .filterBefore(form, UpAppsAnswerSheet.class, Constants.NPS_DAYS)
                        .setSqlSelect("COUNT(*) as count, SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum, SUM(" + UpAppsAnswerSheet.IS_COMMENTED + ") commentNum")
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
                );

        if (resultOld != null) {
            int 完成数Old = ValueHelper.tryParse(resultOld.get("finishNum"), 0);
            int 评论数Old = ValueHelper.tryParse(resultOld.get("commentNum"), 0);
            BigDecimal v = ValueHelper.rint((float)(评论数 - 评论数Old) * 100 / 完成数Old);
            if (v.floatValue() > 0) {
                resData.put("rightStr", "+" + v + "%");
            } else {
                resData.put("rightStr", v + "%");
            }
        } else {
            resData.put("rightStr", "0");
        }

        response.setData(resData);
        return response;
    }

    /**
     * 反馈提交率
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_反馈提交 + Permission.GET)
    @PostMapping("/fanKuiTiJiaoData")
    public BaseResponse<Map<String, Object>> tenantsFanKuiTiJiaoDataGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        List<Map<String, Object>> resultArray = upAppsAnswerSheetService
                .selectMaps(new FilterWarpper<UpAppsAnswerSheet>()
                        .filter(form, UpAppsAnswerSheet.class)
                        .setSqlSelect(
                                "SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum, " +
                                "DATE_FORMAT(add_time,'%Y-%m-%d') as day"
                        )
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        //.gt(UpAppsAnswerSheet.ADD_TIME, new Date(new Date().getTime() - Constants.RECORD_DAYS * Constants.ONE_DAY))
                        .groupBy("day")
                );

        List<String> axis = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        resultArray.forEach( map -> {
            axis.add((String)map.get("day"));
            dataList.add(map.get("finishNum").toString());
        });

        Map<String, Object> resData = new HashMap<>();
        resData.put("axis", axis);
        response.setData(resData);
        return response;
    }

    /**
     * 反馈提交率
     * @return
     */
    @Permission(Permission.T_报告接口 + Permission.T_反馈提交 + Permission.GET)
    @PostMapping("/fanKuiPingLunLvData")
    public BaseResponse<Map<String, Object>> tenantsFanKuiPingLunLvDataGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        //总体满意度计算
        List<Map<String, Object>> resultArray = upAppsAnswerSheetService
                .selectMaps(new FilterWarpper<UpAppsAnswerSheet>()
                        .filter(form, UpAppsAnswerSheet.class)
                        .setSqlSelect(
                                "SUM(" + UpAppsAnswerSheet.IS_FINISHED + ") as finishNum, " +
                                        "SUM(" + UpAppsAnswerSheet.IS_COMMENTED + ") as commentNum" +
                                        ", DATE_FORMAT(add_time,'%Y-%m-%d') as day"
                        )
                        .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .eq(UpAppsAnswerSheet.IS_FINISHED, 1)
                        .groupBy("day")
                );

        List<String> axis = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        resultArray.forEach( map -> {
            axis.add((String)map.get("day"));

            int 完成数 = ValueHelper.tryParse(map.get("finishNum"), 0);
            int 评论数 = ValueHelper.tryParse(map.get("commentNum"), 0);
            BigDecimal v = ValueHelper.rint((float)评论数 * 100 / 完成数);
            dataList.add(v.toString());
        });

        Map<String, Object> resData = new HashMap<>();
        resData.put("axis", axis);

        response.setData(resData);
        return response;
    }

    //endregion

    //region 分项成绩

    @Permission(Permission.T_报告接口 + Permission.T_分项成绩+ Permission.GET)
    @PostMapping("/fenXiangChengJi")
    public BaseResponse<Map<String, Object>> tenantsFxcjdGet(ReportFilterForm form) {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);


        List<Map<String, Object>> resultArr = upAppsAnswerSheetItemService
                .selectMaps(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filter(form, UpAppsAnswerSheetItem.class)
                        .setSqlSelect(
                                String.format(
                                        "SUM(%s) as score, SUM(%s) as total, %s ",
                                        UpAppsAnswerSheetItem.SCORE,
                                        UpAppsAnswerSheetItem.MAX_SCORE,
                                        UpAppsAnswerSheetItem.NPS_DIMENSSION)
                        )
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .in(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType() + "," + QuestionTypeEnum.分项满意度.getType())
                        .groupBy(UpAppsAnswerSheetItem.NPS_DIMENSSION)
                );

        //总体满意度计算
        List<Map<String, Object>> oldResultArr = upAppsAnswerSheetItemService
                .selectMaps(new FilterWarpper<UpAppsAnswerSheetItem>()
                        .filterBefore(form, UpAppsAnswerSheetItem.class, Constants.NPS_DAYS)
                        .setSqlSelect(
                                String.format(
                                        "SUM(%s) as score, SUM(%s) as total, %s ",
                                        UpAppsAnswerSheetItem.SCORE,
                                        UpAppsAnswerSheetItem.MAX_SCORE,
                                        UpAppsAnswerSheetItem.NPS_DIMENSSION)
                        )
                        .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, tenantsCompany.getId())
                        .in(UpAppsAnswerSheetItem.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType() + "," + QuestionTypeEnum.分项满意度.getType())
                        //.lt(UpAppsAnswerSheetItem.ADD_TIME,  new Date(new Date().getTime() - Constants.NPS_DAYS * Constants.ONE_DAY))
                        .groupBy(UpAppsAnswerSheetItem.NPS_DIMENSSION)

                );

        Map<String, Object> resData = new HashMap<>();
        List<String> legendData = new ArrayList<>();
        List<String> xAxisData = new ArrayList<>();
        List<String> seriesData0 = new ArrayList<>();
        List<String> seriesData1 = new ArrayList<>();
        if (resultArr != null) {
            legendData.add("当前成绩");
            legendData.add("过去90天成绩");
            resultArr.sort((var1, var2) -> {
                if (var1.get(UpAppsAnswerSheetItem.NPS_DIMENSSION).toString().equals(QuestionTypeEnum.总体满意度.getType())) {
                    return -1;
                }
                return var1.get(UpAppsAnswerSheetItem.NPS_DIMENSSION).toString().compareTo(
                        var1.get(UpAppsAnswerSheetItem.NPS_DIMENSSION).toString()
                );
            });
            resultArr.forEach(map -> {
                String dimension = map.get(UpAppsAnswerSheetItem.NPS_DIMENSSION).toString();
                xAxisData.add(dimension);
                seriesData0.add(ValueHelper.rintPrecentage(map.get("score"), map.get("total")).toString());
                if (oldResultArr != null) {
                    Boolean hasOne = false;
                    for (Map<String, Object> oldMap : oldResultArr) {
                        if (oldMap.get(UpAppsAnswerSheetItem.NPS_DIMENSSION).toString()
                                .equals(dimension)) {
                            seriesData1.add(ValueHelper.rintPrecentage(oldMap.get("score"), oldMap.get("total")).toString());
                            hasOne = true;
                        }
                    }
                    if (!hasOne) {
                        seriesData1.add(BigDecimal.ZERO.toString());
                    }
                } else {
                    seriesData1.add(BigDecimal.ZERO.toString());
                }
            });
        }

        List<Map<String, Object>> seriesData = new ArrayList<>();
        seriesData.add(assembleBarChartData(legendData.get(0), seriesData0, "#7CCFFF", 30));
        seriesData.add(assembleBarChartData(legendData.get(1), seriesData1, "#1D8CE0", 30));
        resData.put("legendData", legendData);
        resData.put("xAxisData", xAxisData);
        resData.put("seriesData", seriesData);
        response.setData(resData);
        return response;
    }

    //endregion

    //region 省份数据

    @Permission(Permission.T_报告接口 + Permission.T_分项成绩 + Permission.GET)
    @PostMapping("/shengFenShuJu")
    public BaseResponse<Map<String, Object>> tenantsShengFenShuJuGet(ReportMapForm form) throws Exception {
        BaseResponse<Map<String, Object>> response = new BaseResponse<>();
        UpTenantsCompany tenantsCompany = getCompany(request);

        Wrapper<UpAppsAnswerSheetItemView> wrapper = new FilterWarpper<UpAppsAnswerSheetItemView>()
                .filter(form, UpAppsAnswerSheetItemView.class)
                .setSqlSelect(
                        String.format(
                                "SUM(%s) as value, SUM(%s) as total, %s as name",
                                UpAppsAnswerSheetItemView.SCORE,
                                UpAppsAnswerSheetItemView.MAX_SCORE,
                                UpAppsAnswerSheetItemView.PROVINCE)
                )
                .eq(UpAppsAnswerSheetItemView.TENANTS_COMPANY_ID, tenantsCompany.getId())
                .groupBy(UpAppsAnswerSheetItemView.PROVINCE);

        if (ValueHelper.isNone(form.getNpsType()) || form.getNpsType().equals("总体满意度")) {
            wrapper.eq(UpAppsAnswerSheetItemView.QUESTION_TYPE, QuestionTypeEnum.总体满意度.getType());
        } else {
            wrapper.eq(UpAppsAnswerSheetItemView.QUESTION_TYPE, QuestionTypeEnum.分项满意度.getType());
            wrapper.eq(UpAppsAnswerSheetItemView.NPS_DIMENSSION, form.getNpsType());
        }

        List<Map<String, Object>> resultArr = upAppsAnswerSheetItemViewService.selectMaps(wrapper);

        UpAppsQuestionnaire questionnaire = getQuestionnaire(0L, tenantsCompany.getId());

        if (questionnaire == null) {
            throw new AlertException("问卷不存在!");
        }

        if (resultArr != null) {
            resultArr.forEach(map -> {
                BigDecimal val = ValueHelper.rintPrecentage(map.get("value"), map.get("total"));
                map.replace("value",val);
                map.remove("total");
            });
        }
        List<String> options = new ArrayList<>();
        options.add("总体满意度");
        JSONArray jsonObject = JSONObject.parseArray(questionnaire.getNpsJson());
        if (jsonObject != null) {
            jsonObject.forEach(o -> {
                options.add(o.toString());
            });
        }

        Map<String, Object> resData = new HashMap<>();
        resData.put("myData", resultArr);
        resData.put("options", options);
        response.setData(resData);
        return response;
    }

    //endregion

    //region 原始数据报告
//    @Permission(Permission.T_报告接口 + Permission.T_导出报告 + Permission.GET)
    @PostMapping("/originData")
    public BaseResponse originData(ReportFilterForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        Long questionnaireId = form.getQuestionnaireId();

        //换用用表格存储形式，读取记录:
        RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria("ts_answer_sheet_index");

        Long startId = 0L, endId = 0L;

        //由于倒叙的关系，start end会倒过来
        if (form.getStartDate() != null && form.getStartDate() > 0) {
            endId = SnowFlake.nextId(form.getStartDate());
        }

        if (form.getEndDate() != null && form.getEndDate() > 0) {
            startId = SnowFlake.nextId(form.getEndDate() + Constants.ONE_DAY_LESS);
        }

        Long lastId = form.getLastId();
        if (lastId == null) {
            lastId = 0L;
        }

        lastId = lastId < startId ? lastId : startId;

        // 设置起始主键
        PrimaryKeyBuilder primaryKeyBuilderFrom = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilderFrom.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(form.getQuestionnaireId()));

        if (lastId > 0) {
            primaryKeyBuilderFrom.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(lastId));
        } else {
            primaryKeyBuilderFrom.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MAX);
        }
        rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilderFrom.build());

        // 设置结束主键
        PrimaryKeyBuilder primaryKeyBuilderTo = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilderTo.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(form.getQuestionnaireId()));

        if (endId > 0) {
            primaryKeyBuilderTo.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(endId));
        } else {
            primaryKeyBuilderTo.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MIN);
        }
        rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilderTo.build());
        rangeRowQueryCriteria.setMaxVersions(1);
        rangeRowQueryCriteria.setDirection(Direction.BACKWARD);  //从最新的记录开始
        rangeRowQueryCriteria.setLimit(form.getPageSize());

        List<TsAnswerSheet> answerSheetList = new ArrayList<>();

        logger.info("GetRange的结果为:");
        GetRangeResponse getRangeResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
        for (Row row : getRangeResponse.getRows()) {
            try {
                TsAnswerSheetIndex index = new TsAnswerSheetIndex();
                tableStoreService.row2Data(row, index);

                TsAnswerSheet answerSheet = new TsAnswerSheet();
                answerSheet.setQuestionnaireId(index.getQuestionnaireId());
                answerSheet.setAnswerSheetId(index.getAnswerSheetId());
                answerSheetList.add(answerSheet);
            } catch (Exception e) {
                logger.debug("处理异常:", e);
            }
        }

        List<TsAnswerSheet> asList;
        if (answerSheetList.size() > 0) {
            asList = tableStoreService.batchGetRow(answerSheetList);
        } else {
            asList = new ArrayList<>();
        }

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        Map<String, Object> questionKey = new HashMap<>();

        List<UpAppsQuestion> questionList = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, form.getQuestionnaireId())
                        .orderBy(UpAppsQuestion.QUESTION_ORDER, false)
        );

        Map<Long, UpTenantsRegion> regionsMap = new HashedMap();
        Map<Long, List<UpAppsQuestionItem>> questionItemsMap = new HashedMap();


        for (TsAnswerSheet answerSheet : asList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", answerSheet.getAnswerSheetId());

            if (answerSheet.getAnswerSheetId() < min) {
                min = answerSheet.getAnswerSheetId();
            }

            if (answerSheet.getAnswerSheetId() > max) {
                max = answerSheet.getAnswerSheetId();
            }

            UpTenantsRegion region = regionsMap.get(answerSheet.getTenantsRegionId());

            if (region == null) {
                region = upTenantsRegionService.selectById(answerSheet.getTenantsRegionId());
                regionsMap.put(answerSheet.getTenantsRegionId(), region);
            }

            map.put("regionName", region == null ? "-" : region.getName());
            map.put("startTime", answerSheet.getStartTime());
            map.put("finishTime", answerSheet.getFinishTime());
            map.put("phoneNum", answerSheet.getPhoneNum());
            map.put("browser", answerSheet.getBroswer());


            List<UpAppsAnswerSheetItem> answerSheetItemList = answerSheet.getItemList();
            int index = 0;
            int num = 0;
            for (int i = 0; i < 3; i++) {
                if (i == questionList.size()) {
                    break;
                }
                UpAppsQuestion question = questionList.get(index);

                QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.valueOf(question.getQuestionType());
                switch (questionTypeEnum) {
                    case NPS:
                    case 评分:
                    case 多选:
                    case 分项满意度:
                    case 单选:
                    case 服务方式:
                    case 个人信息:
                    case 是非:
                    case 总体满意度:
                    case 排序:
                    case 时间:
                    case 其他:
                        StringBuilder value1 = new StringBuilder();
                        int c = 0;
                        for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
                            if (answerSheetItem.getQuestionId().equals(question.getId())) {
                                if (c > 0) {
                                    value1.append("\n");
                                }
                                value1.append(answerSheetItem.getValue());
                                c++;
                            }
                        }
                        num++;
                        Map<String, Object> questionMap = new HashMap<>();
                        questionMap.put("value", value1.toString());
                        questionMap.put("key", question.getTitle());
                        questionKey.put("question" + num, question.getTitle());
                        map.put("question" + num, questionMap);
                        break;
                    case 填空:
                    case 手机验证:
                        List<UpAppsQuestionItem> questionItemList = questionItemsMap.get(question.getId());

                        if (questionItemList == null) {
                            questionItemList = upAppsQuestionItemService.selectList(
                                    new EntityWrapper<UpAppsQuestionItem>()
                                            .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, answerSheet.getQuestionnaireId())
                                            .eq(UpAppsQuestionItem.QUESTION_ID, question.getId())
                            );

                            questionItemsMap.put(question.getId(), questionItemList);
                        }

                        for (UpAppsQuestionItem questionItem : questionItemList) {
                            String value = "";
                            for (UpAppsAnswerSheetItem answerSheetItem : answerSheetItemList) {
                                if (answerSheetItem.getQuestionItemId().equals(questionItem.getId())) {
                                    value = answerSheetItem.getInputContent();
                                }
                            }
                            num++;
                            Map<String, Object> questionMap1 = new HashMap<>();
                            questionMap1.put("value", value);

                            if (questionItemList.size() == 1) {
                                questionMap1.put("key", question.getTitle());
                                questionKey.put("question" + num, question.getTitle());
                            } else {
                                questionMap1.put("key", questionItem.getPlaceholder());
                                questionKey.put("question" + num, questionItem.getPlaceholder());
                            }


                            map.put("question" + num, questionMap1);

                            if (num >= 3) {
                                break;
                            }
                        }
                        break;
                    default:
                        logger.info("题目类型出错");
                        break;
                }
                index++;
                if (num >= 3) {
                    break;
                }
            }

            map.put("comments", "N/A");
            map.put("myd", "N/A");
            map.put("nps", "N/A");




            list.add(map);
        }

        result.put("list", list);
        result.put("endId", min - 1);  //注意方向是向后的，所以越小的越靠后
        result.put("beginId", max + 1);
        result.put("questionKey", questionKey);
        response.setData(result);

        return response;
    }

    @PostMapping("/answerSheetDetail")
    public BaseResponse originDataDelete(BaseIdAndQidForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        Long answerId = form.getId();

        TsAnswerSheet tsAnswerSheet = new TsAnswerSheet();
        tsAnswerSheet.setAnswerSheetId(answerId);
        tsAnswerSheet.setQuestionnaireId(form.getQuestionnaireId());

        if (!tableStoreService.getRow(tsAnswerSheet)) {
            throw new AlertException("答卷不存在");
        }

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> answer = new HashMap<>();

        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectById(form.getQuestionnaireId());
        answer.put("questionnaireTitle", questionnaire.getTitle());
        answer.put("questionnaireId", questionnaire.getId());
        answer.put("finishTime", tsAnswerSheet.getFinishTime());
        result.put("answerSheet", answer);

        List<UpAppsQuestion> questions = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaire.getId())
                        .orderBy(UpAppsQuestion.QUESTION_ORDER)
                        .orderBy(UpAppsQuestion.ADD_TIME, false)
        );

        List<UpAppsAnswerSheetItemDetailView> itemDetailViews = new ArrayList<>();
        for (UpAppsAnswerSheetItem item : tsAnswerSheet.getItemList()) {
            UpAppsAnswerSheetItemDetailView detailView = new UpAppsAnswerSheetItemDetailView();
            detailView.setId(item.getId());
            detailView.setQuestionId(item.getQuestionId());
            detailView.setValue(item.getValue());

            UpAppsQuestion question = null;

            for (UpAppsQuestion q :questions) {
                if (item.getQuestionId().equals(q.getId())) {
                    question = q;
                    break;
                }
            }
            detailView.setTitle(question.getTitle());
            detailView.setSubTitle(question.getSubTitle());
            detailView.setInputContent(item.getInputContent());
            detailView.setScore(item.getScore());
            itemDetailViews.add(detailView);
        }

        result.put("list", itemDetailViews);
        result.put("answerSheet", answer);
        response.setData(result);

        return response;
    }

    @PostMapping("/originDataDelete")
    public BaseResponse originDataDelete(BaseIdsAndQidForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        String idsStr = form.getIds();
        String [] idArr = idsStr.split(",");
        Long questionnaireId = form.getQuestionnaireId();

        for(String id : idArr) {
            Long answerSheetId = ValueHelper.tryParseLong(id,0L);
            if (answerSheetId > 0) {
                TsAnswerSheetIndex index = new TsAnswerSheetIndex();
                index.setAnswerSheetId(answerSheetId);
                index.setQuestionnaireId(questionnaireId);
                tableStoreService.deleteRow(index);

                TsAnswerSheet answerSheet = new TsAnswerSheet();
                answerSheet.setQuestionnaireId(questionnaireId);
                answerSheet.setAnswerSheetId(answerSheetId);

                if (tableStoreService.getRow(answerSheet)) {
                    if(!ValueHelper.isNone(answerSheet.getCookies())) {
                        TsAnswerSheetCookies cookies = new TsAnswerSheetCookies();
                        cookies.setQuestionnaireIdCookies(questionnaireId + answerSheet.getCookies());
                        cookies.setRegionId(answerSheet.getTenantsRegionId());
                        tableStoreService.deleteRow(cookies);
                    }

                    if(!ValueHelper.isNone(answerSheet.getOpenid())) {
                        TsAnswerSheetOpenid openid = new TsAnswerSheetOpenid();
                        openid.setQuestionnaireIdOpenid(questionnaireId + answerSheet.getOpenid());
                        openid.setRegionId(answerSheet.getTenantsRegionId());
                        tableStoreService.deleteRow(openid);
                    }

                    if(!ValueHelper.isNone(answerSheet.getPhoneNum())) {
                        TsAnswerSheetPhone phone = new TsAnswerSheetPhone();
                        phone.setQuestionnaireIdPhone(questionnaireId + answerSheet.getPhoneNum());
                        phone.setRegionId(answerSheet.getTenantsRegionId());
                        tableStoreService.deleteRow(phone);
                    }
                }

            }
        }
        return response;
    }

    //    @Permission(Permission.T_报告接口 + Permission.T_导出报告 + Permission.GET)
    @PostMapping("/exportData")
    public BaseResponse exportData(ExportDataForm form) throws Exception {
        BaseResponse response = new BaseResponse();

        Date start = form.getStart() != 0 ? new Date(form.getStart()): null;
        Date end = form.getEnd() != 0 ? new Date(form.getEnd()) : null;

        exportTaskService.exportDataToExcelWithNewThread(form.getQuestionnaireId(), start, end, form.getQuickTag(), form.getRegionFilter());

        return response;
    }

    @PostMapping("/exportDataList")
    public BaseResponse exportDataList(BasePageIdKwForm form) {
        BaseResponse response = new BaseResponse();
        Long questionnaireId = form.getId();

        Wrapper<UpTenantsTask> wrapper = new EntityWrapper<UpTenantsTask>()
                .eq(UpTenantsTask.QUESTIONNAIRE_ID, questionnaireId)
                .orderBy(UpTenantsTask.ADD_TIME, false);

        if (!ValueHelper.isNone(form.getKw())){
            String kw = form.getKw().trim();
            wrapper.like(UpTenantsTask.REGION_FILTER, kw);
        }

        Page<UpTenantsTask> page = upTenantsTaskService.selectPage(new Page<UpTenantsTask>(form.getPageNum(), form.getPageSize()),wrapper);
        Map<String, Object> result = new HashMap<>();
        result.put("list", page.getRecords());
        result.put("size", page.getSize());
        result.put("totalNum", page.getTotal());
        response.setData(result);

        return response;
    }

    @PostMapping("/exportDataDelete")
    public BaseResponse exportDataDelete(BaseIdsForm form) {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        String idsStr = form.getIds();
        String [] idArr = idsStr.split(",");

        for(String id : idArr) {
            Long questionnaireId = ValueHelper.tryParseLong(id,0L);
            if (questionnaireId > 0) {
                upTenantsTaskService.deleteById(questionnaireId);
            }
        }
        return response;
    }

    //endregion

    //region 新概览报告
    @PostMapping("/new/overview/part1")
    public BaseResponse newOverviewPart1(BaseIdForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        Map<String, Object> result = newReportService.getOverviewPart1(company, form.getId());
        response.setData(result);
        return response;
    }

    @PostMapping("/new/overview/part2")
    public BaseResponse newOverviewPart2(BaseIdForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        Map<String, Object> result = newReportService.getOverviewPart2(company, form.getId(), 30);
        response.setData(result);
        return response;
    }

    @PostMapping("/new/overview/part3")
    public BaseResponse newOverviewPart3(BaseIdForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        Map<String, Object> result = newReportService.getOverviewPart3(company, form.getId());
        response.setData(result);
        return response;
    }

    @PostMapping("/new/analysis/tkt/list")
    public BaseResponse newAnalysisTktList(BaseIdAndQidAndRangeForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);
        Long questionnaireId = form.getQuestionnaireId();

        UpAppsQuestion question = upAppsQuestionService.selectById(form.getId());
        if (question == null) {
            throw new Exception("该填空题不存在");
        }
        QuestionTypeEnum typeEnum = QuestionTypeEnum.valueOf(question.getQuestionType());
        if (typeEnum != QuestionTypeEnum.填空) {
            throw new Exception("该题目不是填空题");
        }
        List<UpAppsQuestionItem> items = JSONObject.parseArray(question.getQuestionJson(), UpAppsQuestionItem.class);
        List<String> headers = new ArrayList<>();
        headers.add("ID");
        int cnt = 0;
        Map<Long, Integer> itemsIndex = new HashedMap();
        for (UpAppsQuestionItem item : items) {
            cnt++;
            headers.add(item.getPlaceholder() == null ? ("选项" + cnt) : item.getPlaceholder());
            itemsIndex.put(item.getId(), cnt - 2); //注意** 因为前面有个ID了
        }
        headers.add("提交时间");

        //换用用表格存储形式，读取记录:
        RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria("ts_answer_sheet_index");
        Long startId = 0L, endId = 0L;

        //由于倒叙的关系，start end会倒过来
        if (form.getStartDate() != null && form.getStartDate() > 0) {
            endId = SnowFlake.nextId(form.getStartDate());
        }

        if (form.getEndDate() != null && form.getEndDate() > 0) {
            startId = SnowFlake.nextId(form.getEndDate() + Constants.ONE_DAY_LESS);
        }

        Long lastId = form.getLastId();
        if (lastId == null) {
            lastId = 0L;
        }

        lastId = lastId < startId ? lastId : startId;

        // 设置起始主键
        PrimaryKeyBuilder primaryKeyBuilderFrom = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilderFrom.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(form.getQuestionnaireId()));

        if (lastId > 0) {
            primaryKeyBuilderFrom.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(lastId));
        } else {
            primaryKeyBuilderFrom.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MAX);
        }
        rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKeyBuilderFrom.build());

        // 设置结束主键
        PrimaryKeyBuilder primaryKeyBuilderTo = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilderTo.addPrimaryKeyColumn("questionnaireId", PrimaryKeyValue.fromLong(form.getQuestionnaireId()));

        if (endId > 0) {
            primaryKeyBuilderTo.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.fromLong(endId));
        } else {
            primaryKeyBuilderTo.addPrimaryKeyColumn("answerSheetId", PrimaryKeyValue.INF_MIN);
        }
        rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKeyBuilderTo.build());
        rangeRowQueryCriteria.setMaxVersions(1);
        rangeRowQueryCriteria.setDirection(Direction.BACKWARD);  //从最新的记录开始
        rangeRowQueryCriteria.setLimit(form.getPageSize());

        List<TsAnswerSheet> answerSheetList = new ArrayList<>();

        logger.info("GetRange的结果为:");
        GetRangeResponse getRangeResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
        for (Row row : getRangeResponse.getRows()) {
            try {
                TsAnswerSheetIndex index = new TsAnswerSheetIndex();
                tableStoreService.row2Data(row, index);

                TsAnswerSheet answerSheet = new TsAnswerSheet();
                answerSheet.setQuestionnaireId(index.getQuestionnaireId());
                answerSheet.setAnswerSheetId(index.getAnswerSheetId());
                answerSheetList.add(answerSheet);
            } catch (Exception e) {
                logger.debug("处理异常:", e);
            }
        }

        List<TsAnswerSheet> asList;
        if (answerSheetList.size() > 0) {
            asList = tableStoreService.batchGetRow(answerSheetList);
        } else {
            asList = new ArrayList<>();
        }

        Map<String, Object> result = new HashMap<>();
        List<String[]> list = new ArrayList<>();

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (TsAnswerSheet answerSheet : asList) {
            String[] map = new String[headers.size()];
            map[0] = answerSheet.getAnswerSheetId().toString();
            map[headers.size() - 1] = Constants.DATETIME_FORMAT.format(answerSheet.getFinishTime());

            for (UpAppsAnswerSheetItem item : answerSheet.getItemList()) {
                Integer integer = itemsIndex.get(item.getQuestionItemId());
                if (integer != null) {
                    map[integer] = item.getInputContent();
                }
            }
            list.add(map);
        }

        result.put("list", list);
        result.put("endId", min - 1);  //注意方向是向后的，所以越小的越靠后
        result.put("beginId", max + 1);
        response.setData(result);
        return response;
    }

    //endregion

    //region  新数据分析
    @PostMapping("/new/dataAnalysis")
    public BaseResponse newDataAnalysis(TimeRangeForm form) throws Exception {
        BaseResponse response = new BaseResponse();
        UpTenantsCompany company = getCompany(request);

        Map<String, Object> result = newReportService.getDataAnalysis(form, company);
        response.setData(result);

        return response;
    }


    //endregion
}
