package cn.com.carry.tenants.rpc.service.common;

import cn.com.carry.model.auto.entity.tenants.CFinalData;
import cn.com.carry.model.auto.entity.tenants.COriginData;
import cn.com.carry.model.auto.entity.tenants.COriginDataPageUrl;
import cn.com.carry.model.auto.entity.tenants.COriginExcelData;
import cn.com.carry.tenants.api.auto.CFinalDataService;
import cn.com.carry.tenants.api.auto.COriginDataPageUrlService;
import cn.com.carry.tenants.api.auto.COriginDataService;
import cn.com.carry.tenants.api.auto.COriginExcelDataService;
import cn.com.carry.tenants.api.common.CrawlerService;
import cn.com.carry.tenants.common.model.enums.FinalDataTypeEnum;
import cn.com.carry.tenants.common.model.enums.OriginDataStatusEnum;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("Duplicates")
@Service("crawlerService")
public class CrawlerServiceImpl implements CrawlerService{

    Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    @Autowired
    private COriginDataService cOriginDataService;

    @Autowired
    private COriginDataPageUrlService cOriginDataPageUrlService;

    @Autowired
    private CFinalDataService cFinalDataService;

    @Autowired
    private COriginExcelDataService cOriginExcelDataService;

    //region 获取普通页面

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

        Elements pElements = doc.select(".article_infor p");
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
                        .eq(COriginDataPageUrl.STATUS, OriginDataStatusEnum.FAIL.getStatus())
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

    //  过滤普通data
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

    //endregion

    //region 获取table页面data

    @Override
    public void crawExcelPageList() {
        List<COriginDataPageUrl> urlList = cOriginDataPageUrlService.selectList(
                new EntityWrapper<COriginDataPageUrl>()
                        .eq(COriginDataPageUrl.STATUS, OriginDataStatusEnum.FAIL.getStatus())
        );
        int i = 0;
        for (COriginDataPageUrl pageUrl : urlList) {
            logger.info("爬取Excel网页任务：已爬取 " + i++ + " 页，剩余 " + (urlList.size() - i) + " 页");
            boolean flag = false;
            try {
                flag = crawExcelPage(pageUrl.getUrl(), pageUrl.getFullTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag) {
                pageUrl.setStatus(OriginDataStatusEnum.SUCCESS.getStatus());
                pageUrl.setUpdateTime(new Date());
                cOriginDataPageUrlService.updateById(pageUrl);
            }
        }
    }

    private boolean crawExcelPage(String url, String title) {
        Document doc = null;
        boolean flag = false;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element table = doc.select(".article_infor table").first();
        if (table != null) {
            Element timeElement = doc.select(".articleTitle_details").get(0);
            String timeStr = timeElement.text();

            COriginExcelData cOriginExcelData = new COriginExcelData();
            cOriginExcelData.setTitle(title)
                    .setHtml(table.outerHtml())
                    .setDataTime(timeStr)
                    .setUrl(url)
                    .setStatus(OriginDataStatusEnum.CREATE.getStatus());
            cOriginExcelDataService.insert(cOriginExcelData);
            flag = true;
        }

        return flag;
    }

    //  过滤table data
    @Override
    public void filterExcelData() {
        int count = cOriginExcelDataService.selectCount(
                new EntityWrapper<COriginExcelData>()
                        .eq(COriginExcelData.STATUS, OriginDataStatusEnum.CREATE.getStatus())
        );
        int pageSize = 10;
        int totalPageNum = count / pageSize;
        int num = 0;

        for (int pageNum = 0; pageNum <= totalPageNum; pageNum++) {
            Page<COriginExcelData> page = cOriginExcelDataService.selectPage(
                new Page<>(pageNum, pageSize),
                new EntityWrapper<COriginExcelData>()
                        .eq(COriginExcelData.STATUS, OriginDataStatusEnum.CREATE.getStatus())
            );
            List<COriginExcelData> list = page.getRecords();

            for (COriginExcelData cOriginExcelData : list) {
                logger.info("正在处理第" + ++num + "条table数据，还剩" + (count - num) + "条");

                boolean flag = true;
                try {
                    FinalDataTypeEnum dataTypeEnum;
                    String dataTypePattern = "解除|撤销";
                    Pattern dataTypeRegex = Pattern.compile(dataTypePattern);
                    Matcher dataTypeMatcher = dataTypeRegex.matcher(cOriginExcelData.getTitle());
                    if (dataTypeMatcher.find()) {
                        dataTypeEnum = FinalDataTypeEnum.解除处罚;
                    } else {
                        dataTypeEnum = FinalDataTypeEnum.处罚;
                    }

                    Document document = Jsoup.parse(cOriginExcelData.getHtml());
                    Element table = document.select("table").first();
                    Elements trs = table.select("tr");
                    int lineIndex = 0;

                    String publisher = "";
                    String publisherPattern = "[^于日月]+公司";
                    Pattern publisherRegex = Pattern.compile(publisherPattern);
                    Matcher publisherMatcher = publisherRegex.matcher(cOriginExcelData.getTitle());
                    if (publisherMatcher.find()) {
                        publisher = publisherMatcher.group(0);
                    }

                    int supplierIndex=-1,handleReasonIndex=-1,handleWayIndex=-1,productNameIndex=-1;
                    while (supplierIndex + handleReasonIndex + handleWayIndex + productNameIndex == -4 && lineIndex < trs.size()) {
                        Element titleLine = trs.get(lineIndex);
                        lineIndex++;
                        Elements titles = titleLine.select("td");
                        for (int i = 0; i < titles.size(); i++) {
                            String titleStr = titles.get(i).text();
                            if (titleStr.contains("供应商") || titleStr.contains("服务商")) {
                                supplierIndex = i;
                            } else if (titleStr.contains("不良行为")) {
                                handleReasonIndex = i;
                            } else if (titleStr.contains("措施") || titleStr.contains("处理建议")) {
                                handleWayIndex = i;
                            } else if (titleStr.contains("范围") || titleStr.contains("物资")) {
                                productNameIndex = i;
                            }
                        }
                    }

                    for (int i = lineIndex; i < trs.size(); i++) {
                        Elements tds = trs.get(i).select("td");
                        CFinalData cFinalData = new CFinalData();
                        String supplier="",handleReason="",handleWay="",productName="";
                        if (supplierIndex >= 0) {
                            supplier = tds.get(supplierIndex).text();
                        }
                        if (handleReasonIndex >= 0) {
                            handleReason = tds.get(handleReasonIndex).text();
                        }
                        if (handleWayIndex >= 0) {
                            handleWay = tds.get(handleWayIndex).text();
                        }
                        if (productNameIndex >= 0) {
                            productName = tds.get(productNameIndex).text();
                        }

                        cFinalData.setUrl(cOriginExcelData.getUrl())
                                .setSupplier(supplier)
                                .setPublishTime(cOriginExcelData.getDataTime())
                                .setProductName(productName)
                                .setProductBuyer(publisher)
                                .setHandleWay(handleWay)
                                .setHandleReason(handleReason)
                                .setDataType(dataTypeEnum.getType())
                                .setAnnouncementPublisher(publisher)
                                .setSort(3)
                                .setSource("TablePage")
                                .setAddTime(new Date());

                        cFinalDataService.insert(cFinalData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                }

                if (flag) {
                    cOriginExcelData.setStatus(OriginDataStatusEnum.SUCCESS.getStatus());
                } else {
                    cOriginExcelData.setStatus(OriginDataStatusEnum.FAIL.getStatus());
                }
                cOriginExcelData.setUpdateTime(new Date());
                cOriginExcelDataService.updateById(cOriginExcelData);

            }
        }

    }

    //endregion

    //region 获取带附件页面data
    @Override
    public void crawFilePageList() {
        List<COriginDataPageUrl> urlList = cOriginDataPageUrlService.selectList(
                new EntityWrapper<COriginDataPageUrl>()
                        .eq(COriginDataPageUrl.STATUS, OriginDataStatusEnum.FAIL.getStatus())
        );
        int i = 0;
        for (COriginDataPageUrl pageUrl : urlList) {
            logger.info("爬取带附件网页任务：已爬取 " + i++ + " 页，剩余 " + (urlList.size() - i) + " 页");
            boolean flag = false;
            try {
                flag = crawFilePage(pageUrl.getUrl(), pageUrl.getFullTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag) {
                pageUrl.setStatus(OriginDataStatusEnum.SUCCESS.getStatus());
                pageUrl.setUpdateTime(new Date());
                cOriginDataPageUrlService.updateById(pageUrl);
            }
        }
    }

    private boolean crawFilePage(String url, String title) {
        Document doc = null;
        boolean flag = false;
        String prefix = "http://ecp.sgcc.com.cn";
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select(".bot_list a");
        for (Element link : links) {
            try {
                String downloadUrl = link.attr("href");
                String pattern = "[^/]+$";
                Pattern Regex = Pattern.compile(pattern);
                Matcher matcher = Regex.matcher(downloadUrl);
                String name = "noName";
                if (matcher.find()) {
                    name = matcher.group(0);
                }
                downloadUrl = prefix + downloadUrl;
                downLoadFromUrl(downloadUrl, name, "D:/FYP/file");
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return flag;
    }


    //endregion

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success");

    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public static void main(String[] args) {
//        crawFilePage("http://ecp.sgcc.com.cn/html/news/018013001/52986.html", "国网青海省电力公司2017年11月供应商不良行为处理情况公示");
    }
}




