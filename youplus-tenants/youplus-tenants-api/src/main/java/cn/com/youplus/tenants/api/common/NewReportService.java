package cn.com.youplus.tenants.api.common;

import cn.com.youplus.common.form.TimeRangeForm;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;

import java.util.Map;

public interface NewReportService {

    Map<String, Object> getOverviewPart1(UpTenantsCompany company, Long questionnaireId) throws Exception;

    Map<String, Object> getOverviewPart2(UpTenantsCompany company, Long questionnaireId, int size) throws Exception;

    Map<String, Object> getOverviewPart3(UpTenantsCompany company, Long questionnaireId) throws Exception;

    Map<String, Object> getDataAnalysis(TimeRangeForm form, UpTenantsCompany company) throws Exception;
}
