package cn.com.youplus.base.api;


import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

/**
 * 通过日志服务来获得统计数据
 * Created by admin on 2017/8/9.
 */
public interface LhAnswerSheetService {

    String ANWSER_SHEET_LOG_STORE_NAME = "answer-sheet-record-log";
    String ANWSER_SHEET_REQUEST_LOG_STORE_NAME = "answer-sheet-request-log";
    String QUESTION_STAT_LOG_STORE_NAME = "question-stat-log";

    int MAX_TIME_DELTA = 365 * 24 * 2600;

    /**
     * 获取问卷的样本总数，有一分钟的统计延时
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    int getAnswerSheetTotalCount(Long questionnaireId) throws Exception;

    /**
     * 获取问卷的当天样本总数，有一分钟的统计延时
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    int getAnswerSheetCurrentDayCount(Long questionnaireId) throws Exception;

    /**
     * 获取问卷的过去一小时的样本总数，有一分钟的统计延时
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    int getAnswerSheetLastHourCount(Long questionnaireId) throws Exception;

}
