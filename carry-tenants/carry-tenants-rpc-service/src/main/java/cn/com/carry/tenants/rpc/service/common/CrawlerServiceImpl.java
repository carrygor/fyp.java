package cn.com.carry.tenants.rpc.service.common;

import cn.com.carry.model.auto.entity.tenants.CFinalData;
import cn.com.carry.model.auto.entity.tenants.COriginData;
import cn.com.carry.model.auto.entity.tenants.COriginDataPageUrl;
import cn.com.carry.tenants.api.auto.CFinalDataService;
import cn.com.carry.tenants.api.auto.COriginDataPageUrlService;
import cn.com.carry.tenants.api.auto.COriginDataService;
import cn.com.carry.tenants.api.common.CrawlerService;
import cn.com.carry.tenants.common.model.enums.FinalDataTypeEnum;
import cn.com.carry.tenants.common.model.enums.OriginDataStatusEnum;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("crawlerService")
public class CrawlerServiceImpl implements CrawlerService{

    Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    @Autowired
    private COriginDataService cOriginDataService;

    @Autowired
    private COriginDataPageUrlService cOriginDataPageUrlService;

    @Autowired
    private CFinalDataService cFinalDataService;

    @Override
    public boolean crawPage(String url, String title) throws Exception{
        Document doc = null;
        boolean flag = false;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element timeElement = doc.select(".articleTitle_details").get(0);
        String timeStr = timeElement.text();

        Elements pElements = doc.select(".article_infor>p");
        for (Element p : pElements) {
            String text = p.text();
            text = text.trim();
            String pattern = "(、| |^)([\\u4e00-\\u7700|\\u7702-\\u9fa5]+有限公司)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(text);
            if (matcher.find()) {
                flag = true;
                String company = matcher.group(2);
                COriginData cOriginData = new COriginData();
                cOriginData.setTitle(title)
                        .setText(text)
                        .setCompany(company)
                        .setDataTime(timeStr)
                        .setUrl(url)
                        .setStatus(OriginDataStatusEnum.CREATE.getStatus());
                cOriginDataService.insert(cOriginData);
            }
        }

        return flag;

    }

    @Override
    public void crawPageList() {
        List<COriginDataPageUrl> urlList = cOriginDataPageUrlService.selectList(
                new EntityWrapper<COriginDataPageUrl>()
                        .eq(COriginDataPageUrl.STATUS, OriginDataStatusEnum.CREATE.getStatus())
        );
        int i = 0;
        for (COriginDataPageUrl pageUrl : urlList) {
            logger.info("爬取网页任务：已爬取 " + i++ + " 页，剩余 " + (urlList.size() - i) + " 页");
            boolean flag = false;
            try {
                flag = crawPage(pageUrl.getUrl(), pageUrl.getFullTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag) {
                pageUrl.setStatus(OriginDataStatusEnum.SUCCESS.getStatus());
            } else {
                pageUrl.setStatus(OriginDataStatusEnum.FAIL.getStatus());
            }
            pageUrl.setUpdateTime(new Date());
            cOriginDataPageUrlService.updateById(pageUrl);
        }
    }

    @Override
    public void filterData() {
        List<COriginData> dataList = cOriginDataService.selectList(
                new EntityWrapper<COriginData>()
                        .eq(COriginData.STATUS, OriginDataStatusEnum.CREATE.getStatus())
        );

        int i = 0;
        for (COriginData originData : dataList) {
            logger.info("正在处理第" + ++i + "个数据，还剩" + (dataList.size()-i) + "个数据");
            boolean flag = false;
            try {
                String title = originData.getTitle();
                String text = originData.getText();
                CFinalData finalData = new CFinalData();

                String publisher = "";
                String publisherPattern = "[^于日月]+公司";
                Pattern publisherRegex = Pattern.compile(publisherPattern);
                Matcher publisherMatcher = publisherRegex.matcher(title);
                if (publisherMatcher.find()) {
                    publisher = publisherMatcher.group(0);
                }

                FinalDataTypeEnum dataTypeEnum;
                String dataTypePattern = "解除|撤销";
                Pattern dataTypeRegex = Pattern.compile(dataTypePattern);
                Matcher dataTypeMatcher = dataTypeRegex.matcher(title);
                if (dataTypeMatcher.find()) {
                    dataTypeEnum = FinalDataTypeEnum.解除处罚;
                } else {
                    dataTypeEnum = FinalDataTypeEnum.处罚;
                }

                String reason = "";
                String reasonPattern = "(因|经|问题描述|提供虚假)[\\s\\S]+?。|[^。，]+不合格|发生[\\s\\S]+?(?=，|。)";
                Pattern reasonRegex = Pattern.compile(reasonPattern);
                Matcher reasonMatcher = reasonRegex.matcher(text);
                if (reasonMatcher.find()) {
                    reason = reasonMatcher.group();
                }


                String handleWay = "";
                String handleWayPattern = "扣[\\s\\S]+?分|[^，。]*(限制|暂停)[\\s\\S]+?(?=，|。)";
                Pattern handleWayRegex = Pattern.compile(handleWayPattern);
                Matcher handleWayMatcher = handleWayRegex.matcher(text);
                if (handleWayMatcher.find()) {
                    handleWay = handleWayMatcher.group();
                }

                String productName = "";
                String productNamePattern = "[1-9]\\S{1,10}(器|缆|终端|母联保护|电缆附件|站|交流钢管塔|支架钢结构|不间断电源系统|保护屏|开关柜|铝包钢芯铝绞线|绝缘子)|(电能计量箱|便携型笔记本电脑|变压器|后台远动监控机|UPS电源|电能表箱|绝缘子|JP柜|BVVB电线|高压开关柜|杆塔倾斜检测装置)";
                Pattern productNameRegex = Pattern.compile(productNamePattern);
                Matcher productNameMatcher = productNameRegex.matcher(text);
                if (productNameMatcher.find()) {
                    productName = productNameMatcher.group();
                }

                String timeRange = "";
                String timeRangePattern = "[0-9]{1,2}个月";
                Pattern timeRangeRegex = Pattern.compile(timeRangePattern);
                Matcher timeRangeMatcher = timeRangeRegex.matcher(text);
                if (timeRangeMatcher.find()) {
                    timeRange = timeRangeMatcher.group();
                }

                finalData.setAddTime(new Date())
                        .setAnnouncementPublisher(publisher)
                        .setDataType(dataTypeEnum.getType())
                        .setHandleReason(reason)
                        .setHandleWay(handleWay)
                        .setProductBuyer(publisher)
                        .setProductName(productName)
                        .setPublishTime(originData.getDataTime())
                        .setSupplier(originData.getCompany())
                        .setUrl(originData.getUrl())
                        .setTimeRange(timeRange);

                cFinalDataService.insert(finalData);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (flag) {
                originData.setStatus(OriginDataStatusEnum.SUCCESS.getStatus());
            } else {
                originData.setStatus(OriginDataStatusEnum.FAIL.getStatus());
            }
            originData.setUpdateTime(new Date());
            cOriginDataService.updateById(originData);
        }
    }


    public static void main(String[] args) {
        String input = "2、江西广通电缆有限公司 在线缆协议库存中标厂家质量监督活动中，因该供应商供应的电力电缆（规格/型号：JKLYJ-1kV-1×120）在检测中发现主绝缘热延伸试验中发生断裂质量问题。依据《细则》相关规定，属于一般不良行为，给予了在相应物资类别标包中，扣减该供应商商务评审分值10分的处罚（按评分权重折算前）。现经项目单位检查验收，该公司已对上述问题完成整改,并经验收合格。";
        String dataTypePattern = "[因|经][\\S]+?。";
        Pattern dataTypeRegex = Pattern.compile(dataTypePattern);
        Matcher dataTypeMatcher = dataTypeRegex.matcher(input);
        if (dataTypeMatcher.find()) {
            input = input.replaceFirst(dataTypePattern, "");
            dataTypeMatcher = dataTypeRegex.matcher(input);
            boolean find = dataTypeMatcher.find();
            if (find) {
                String b = dataTypeMatcher.group();
                String c = dataTypeMatcher.group();
            }
        }
    }
}



