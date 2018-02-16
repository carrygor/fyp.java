package cn.com.youplus.thirdparty.api.shenmiren;

import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public interface ShenmirenExcelService {
    void parseExcel(String path, String province, String dateStr) throws Exception;

    //region 从数据库中读取数据
    Map<String, Object> getParsedExcelData(String name, String dateStr) throws Exception;
}
