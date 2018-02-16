package cn.com.youplus.apps.api.common;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

public interface QuestionnaireService {

    String getStringValue(Long questionnaireId, String name);

    Long getLongValue(Long questionnaireId, String name);

    Boolean getLongValueToBoolean(Long questionnaireId, String name);

    Double getDoubleValue(Long questionnaireId, String name);

    Date getDateValue(Long questionnaireId, String name);

    @Transactional(rollbackFor = Exception.class)
    JSONObject editQuestionnaire(String jsonStr, UpTenantsCompany company, boolean isVip) throws AlertException;
}
