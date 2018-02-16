package cn.com.youplus.apps.api.common;

import java.util.Date;

public interface AnswerSheetService {

    /**
     * 根据日期来转换导出数据
     * @param questionnaireId 问卷id
     * @param start 开始日期，可以不填
     * @param end 结束日期，可以不填
     * @return 提示语
     * @throws Exception
     */
    String exportDataToExcel(Long questionnaireId, Date start, Date end) throws Exception;

    String exportDataToExcelWithNewThread(Long questionnaireId, Date start, Date end) throws Exception;

    String exportData(byte[] bytes) throws Exception;

    void transferData() throws Exception;

    String exportToDataBase() throws Exception;

    String exportToDataMultiTask(int startNum, int total) throws Exception;
}
