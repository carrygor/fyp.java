package cn.com.carry.tenants.rpc.service.common;

import cn.com.carry.common.util.ValueHelper;
import cn.com.carry.model.auto.entity.tenants.CAnalyzedData;
import cn.com.carry.model.auto.entity.tenants.CFinalData;
import cn.com.carry.tenants.api.auto.*;
import cn.com.carry.tenants.api.common.DataAnalysisService;
import cn.com.carry.tenants.common.model.enums.FinalDataTypeEnum;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("Duplicates")
@Service("dataAnalysisService")
public class DataAnalysisServiceImpl implements DataAnalysisService{

    Logger logger = LoggerFactory.getLogger(DataAnalysisServiceImpl.class);

    @Autowired
    private COriginDataService cOriginDataService;

    @Autowired
    private COriginDataPageUrlService cOriginDataPageUrlService;

    @Autowired
    private CFinalDataService cFinalDataService;

    @Autowired
    private COriginExcelDataService cOriginExcelDataService;

    @Autowired
    private COriginFileDataService cOriginFileDataService;

    @Autowired
    private CAnalyzedDataService cAnalyzedDataService;

    @Override
    public void filterTimeRange() {
        int count = cFinalDataService.selectCount(
                new EntityWrapper<>()
        );
        int index = 0;
        List<CFinalData> list = cFinalDataService.selectList(
                new EntityWrapper<>()
        );
        for (CFinalData cFinalData : list) {
            logger.info("获取TimeRange：正在处理第" + ++index + "条数据，还剩" + (count - index) + "条数据");

            //获取时间数据
            String timeRange = "";
            if (ValueHelper.isNone(cFinalData.getTimeRange())) {
                String timeRangePattern = "[0-9]{1,2}个月";
                Pattern timeRangeRegex = Pattern.compile(timeRangePattern);
                Matcher timeRangeMatcher = timeRangeRegex.matcher(cFinalData.getHandleWay());
                if (timeRangeMatcher.find()) {
                    timeRange = timeRangeMatcher.group();
                    cFinalData.setTimeRange(timeRange);
                }
            } else{
                timeRange = cFinalData.getTimeRange();
            }
            if (!ValueHelper.isNone(timeRange)) {
                String time = "";
                String timePattern = "[0-9]{1,2}";
                Pattern timeRegex = Pattern.compile(timePattern);
                Matcher timeMatcher = timeRegex.matcher(timeRange);
                if (timeMatcher.find()) {
                    time = timeMatcher.group();
                    int month = ValueHelper.tryParse(time, 0);
                    cFinalData.setTimeMonth(month);
                }
            }


            //删除无用数据
            int emptyCount = 0;
            if (ValueHelper.isNone(cFinalData.getAnnouncementPublisher())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getDataType())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getHandleReason())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getHandleWay())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getProductBuyer())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getProductName())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getPublishTime())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getSupplier())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getTimeRange())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getUrl())) {
                emptyCount++;
            }
            if (ValueHelper.isNone(cFinalData.getTimeMonth())) {
                emptyCount++;
            }
            if (emptyCount >= 2) {
                cFinalData.deleteById();
            } else {
                cFinalData.updateById();
            }

        }
    }

    @Override
    public void analyseData() {
        List<CFinalData> list = cFinalDataService.selectList(
                new EntityWrapper<>()
        );
        Map<String, List<CFinalData>> finalDataMap = new HashMap<>();
        for (CFinalData cFinalData : list) {
            String supplier = cFinalData.getSupplier();
            List<CFinalData> itemList = finalDataMap.get(supplier);
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
            itemList.add(cFinalData);
            finalDataMap.put(supplier, itemList);
        }

        int num = 0;
        for (String supplier : finalDataMap.keySet()) {
            try {
                logger.info("生成推荐数据：正在处理第" + ++num + "个商家，还剩" + (finalDataMap.keySet().size() - num) + "个");
                CAnalyzedData cAnalyzedData = new CAnalyzedData();

                List<Date> timeList = new ArrayList<>();
                List<CFinalData> cFinalDataList = finalDataMap.get(supplier);
                for (CFinalData cFinalData : cFinalDataList) {
                    FinalDataTypeEnum finalDataTypeEnum = FinalDataTypeEnum.valueOf(cFinalData.getDataType());
                    Date time = cFinalData.getStartTime();
                    switch (finalDataTypeEnum) {
                        case 处罚:
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(time);
                            cal.add(Calendar.MONTH, cFinalData.getTimeMonth());
                            timeList.add(cal.getTime());
                            break;
                        case 解除处罚:
                            timeList.add(time);
                            break;
                        default:
                            break;
                    }
                }
                Date latestTime = timeList.get(0);
                for (Date date : timeList) {
                    Date time = date;
                    if (time.getTime() > latestTime.getTime()) {
                        latestTime = time;
                    }
                }
                Date now = new Date();
                if (latestTime.getTime() > now.getTime()) {
                    cAnalyzedData.setStatus("处罚")
                            .setSuggest("该商家还在处罚时间，建议排除中标");
                } else {
                    cAnalyzedData.setStatus("处罚已解除")
                            .setSuggest("该商家有处罚历史，但现在已经解除");
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
                cAnalyzedData.setRelieveTime(formatter.format(latestTime))
                        .setSupplier(supplier);
                cAnalyzedData.insert();
            } catch (Exception e) {

            }
        }


    }


    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String str = "2018年6月13日";
        String str2 = "2018年3月15日";
        try {
            long time1 = formatter.parse(str).getTime();
            long time2 = formatter.parse(str2).getTime();
            long m = ( time1 - time2) / (1000 * 60 * 60 * 24 * 28L);
            int b = 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}




