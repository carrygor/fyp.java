package cn.com.carry.tenants.api.common;

public interface CrawlerService {

    boolean crawPage(String url, String title) throws Exception;

    void crawPageList();

    void crawExcelPageList();

    void filterData();

    void filterExcelData();

    //region 获取带附件页面data
    void crawFilePageList();

    void filterFileData();
}
