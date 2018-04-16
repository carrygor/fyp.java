package cn.com.carry.tenants.server.controller.crawler;

import cn.com.carry.common.model.base.BaseResponse;
import cn.com.carry.model.auto.entity.tenants.COriginDataPageUrl;
import cn.com.carry.tenants.api.auto.CFinalDataService;
import cn.com.carry.tenants.api.auto.COriginDataPageUrlService;
import cn.com.carry.tenants.api.auto.COriginDataService;
import cn.com.carry.tenants.api.auto.CTestService;
import cn.com.carry.tenants.api.common.CrawlerService;
import cn.com.carry.tenants.api.common.DataAnalysisService;
import cn.com.carry.tenants.server.controller.SuperController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 严汉羽 on 2017/10/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tenants/v1/crawler")
public class CrawlerController extends SuperController {

    private static Logger logger = Logger.getLogger(CrawlerController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private CTestService cTestService;

    @Autowired
    private COriginDataService cOriginDataService;

    @Autowired
    private COriginDataPageUrlService cOriginDataPageUrlService;

    @Autowired
    private CFinalDataService cFinalDataService;

    @Autowired
    private DataAnalysisService dataAnalysisService;

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        return super.exceptionRealHandler(request, response, exception);
    }

    public final static String listPrefix = "http://ecp.sgcc.com.cn/html/news/global/018013001_00/list_";

    @PostMapping("/getPageList")
    public BaseResponse getPageList() {
        BaseResponse response = new BaseResponse();

        int pageNum = 49; //49页
        int linkNum = 0;
        boolean finish = false;
        for (int i = 1; i <= pageNum; i++) {
            if (finish) {
                break;
            }
            logger.info("crawler mission is running: page" + i );
            String url = listPrefix + i + ".html";
            Document doc = null;
            try {
                doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(3000).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = doc.select(".titleList ul li a");
            for (Element element : elements) {
                String title = element.attr("title");
                String onClick = element.attr("onclick");
                String patten = ", '(\\S+?)'\\);";
                Pattern regex = Pattern.compile(patten);
                Matcher matcher = regex.matcher(onClick);
                if (matcher.find()) {
                    String link =  "http://ecp.sgcc.com.cn/html/news/018013001/" + matcher.group(1) + ".html";
                    COriginDataPageUrl check = cOriginDataPageUrlService.selectOne(
                            new EntityWrapper<COriginDataPageUrl>()
                                    .eq(COriginDataPageUrl.URL, link)
                    );
                    if (check != null) {
                        finish = true;
                        break;
                    }
                    linkNum++;
                    COriginDataPageUrl cOriginDataPageUrl = new COriginDataPageUrl();
                    cOriginDataPageUrl.setFullTitle(title)
                            .setUrl(link);
                    cOriginDataPageUrlService.insert(cOriginDataPageUrl);
                }
            }
        }
        response.setData("共更新到" + linkNum + "个页面，分析数据结束请在10分钟后再进行查询");
        Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                crawlerService.newMission();
            }
        });

        return response;
    }

    @PostMapping("/crawPage")
    public BaseResponse crawPage() throws Exception {
//        crawlerService.crawExcelPageList();
//        crawlerService.crawPageList();
        crawlerService.crawFilePageList();

        return new BaseResponse();
    }

    @PostMapping("/filterData")
    public BaseResponse filterData() {
        crawlerService.filterData();
        crawlerService.filterExcelData();
        crawlerService.filterFileData();

        return new BaseResponse();
    }

    @PostMapping("/dataAnalysis")
    public BaseResponse dataAnalysis() {
//        dataAnalysisService.filterTimeRange();
        dataAnalysisService.analyseData();

        return new BaseResponse();
    }


}