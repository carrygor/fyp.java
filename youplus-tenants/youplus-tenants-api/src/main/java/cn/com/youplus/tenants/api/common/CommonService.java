package cn.com.youplus.tenants.api.common;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;

import java.util.List;
import java.util.Map;

public interface CommonService {

    List<Map<String, Object>> getProjectSummary(UpTenantsCompany company) throws Exception;

    List<Map<String, Object>> getRecycleBinProject(UpTenantsCompany company);

    List<Map<String, Object>> getProjects(UpTenantsCompany company);
}
