package cn.com.youplus.tenants.api.common;

import cn.com.youplus.model.auto.entity.tenants.UpTenantsTask;

import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/12/25.
 */
public interface ExportTaskService {
    
    /**
     * 根据日期来转换导出数据
     * @param questionnaireId 问卷id
     * @param start 开始日期，可以不填
     * @param end 结束日期，可以不填
     * @return 提示语
     * @throws Exception
     */
    String exportDataToExcel(Long questionnaireId, Date start, Date end, UpTenantsTask task, List messages, String quickTag, String regionFilter) throws Exception;

    String exportDataToExcelWithNewThread(Long questionnaireId, Date start, Date end, String quickTag, String regionFilter) throws Exception;
}
