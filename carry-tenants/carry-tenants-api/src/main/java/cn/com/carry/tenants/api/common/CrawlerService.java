package cn.com.carry.tenants.api.common;

public interface CrawlerService {

    boolean crawPage(String url, String title) throws Exception;

    void crawPageList();

    void filterData();
}
