package cn.com.youplus.tenants.api.common;

import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaireAttribute;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface QuestionnaireSettingService {

    Long copyQuestionnaire(Long questionnaireId, String title, UpTenantsCompany company) throws AlertException;

    Map<String, Object> getQuestionnaireData(UpAppsQuestionnaire questionnaire);

    Map<String, UpAppsQuestionnaireAttribute> getQuestionnaireAttrMap(Long questionnaireId);

    @Transactional(rollbackFor = Exception.class)
    JSONObject editQuestionnaire(String jsonStr, UpTenantsCompany company, boolean isVip) throws AlertException;

    String UrlToQrcodeAndUpload(String url, String format, int size) throws IOException, WriterException;

    String UrlToQrcodeAndUpload(String url, String content, String format, int size) throws IOException, WriterException;

    boolean linkAllDataCheck(UpTenantsCompany company, Long questionnaireId);

    void linkAllDataDelete(UpTenantsCompany company, Long questionnaireId);

    void createBatchLinkAndQrcodeWithNewThread(UpTenantsCompany company, Long questionnaireId, int size);

    String createBatchLinkAndQrcode(UpTenantsCompany company, Long questionnaireId, int size) throws Exception;

    String uploadOneFile(InputStream is, String suffix);
}
