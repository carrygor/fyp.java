package cn.com.carry.tenants.server.controller.crawler;

import cn.com.carry.common.model.base.BaseResponse;
import cn.com.carry.model.auto.entity.tenants.COriginDataPageUrl;
import cn.com.carry.tenants.api.auto.COriginDataPageUrlService;
import cn.com.carry.tenants.api.auto.COriginDataService;
import cn.com.carry.tenants.api.auto.CTestService;
import cn.com.carry.tenants.api.common.CrawlerService;
import cn.com.carry.tenants.server.controller.SuperController;
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
        for (int i = 1; i <= pageNum; i++) {
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
                    COriginDataPageUrl cOriginDataPageUrl = new COriginDataPageUrl();
                    cOriginDataPageUrl.setFullTitle(title)
                            .setUrl(link);
                    cOriginDataPageUrlService.insert(cOriginDataPageUrl);
                }
            }
        }

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
//        crawlerService.filterData();
        crawlerService.filterExcelData();

        return new BaseResponse();
    }


}