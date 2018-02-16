package cn.com.xiaohanyu.report4j.reporter;

import cn.com.xiaohanyu.report4j.datasource.DataSource;
import cn.com.xiaohanyu.report4j.datasource.DataSourceFactory;
import cn.com.xiaohanyu.report4j.enums.DataSourceFromEnum;
import cn.com.xiaohanyu.report4j.enums.DataSourceStatusEnum;
import cn.com.xiaohanyu.report4j.enums.DataSourceTypeEnum;
import cn.com.xiaohanyu.report4j.exception.DsDuplicateSourceException;
import cn.com.xiaohanyu.report4j.exception.DsNotFoundException;
import cn.com.youplus.common.form.BasePageForm;
import cn.com.youplus.common.form.ReportFilterForm;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.util.ValueHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 严汉羽 on 2017/11/28.
 */
public class Reporter {
    private static Logger logger = LoggerFactory.getLogger(Reporter.class);

    private Map<String, DataSource> dataSourceMap;

    private ApplicationContext applicationContext;

    private Object form;

    /**
     * JSON结构化的数据
     */
    private JSONObject jsonObject;

    public static Reporter loadReporter(String path, DataSourceFromEnum from) throws Exception {
        if (ValueHelper.isNone(path)) {
            return null;
        }
        Reporter reporter = new Reporter();

        StringBuilder stringBuilder = new StringBuilder();
        switch (from) {
            case OSS:
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn == null) {
                    throw new Exception("网络连接失败:" + path);
                }
                DataInputStream input = new DataInputStream(conn.getInputStream());
                int i = input.read();
                while (i != -1){
                    stringBuilder.append((char)i);
                    i=input.read();
                }
                input.close();
                break;
            case Resources:
                URL schemaUrl = DataSource.class.getResource(path);
                if (schemaUrl == null) {
                    throw new Exception("文件不存在:");
                }
                BufferedReader br = new BufferedReader(new FileReader( schemaUrl.getPath()));
                i=br.read();
                while (i != -1){
                    stringBuilder.append((char)i);
                    i = br.read();
                }
                br.close();
                break;
            case LocalStorage:
                br=new BufferedReader(new FileReader( path));
                i = br.read();
                while (i != -1){
                    stringBuilder.append((char)i);
                    i = br.read();
                }
                br.close();
                break;
            default:
                throw new DsNotFoundException("不支持的来源类型:" + from.name());
        }
        reporter.setJsonObject(JSONObject.parseObject(stringBuilder.toString()));

        reporter.scanDataSources(reporter.getJsonObject());
        reporter.loadConfig();
        return reporter;
    }

    public void loadConfig() throws Exception {
        if (dataSourceMap == null || dataSourceMap.size() == 0) {
            return;
        }

        for (DataSource dataSource : dataSourceMap.values()) {
            if (dataSource.getStatus() == DataSourceStatusEnum.INITIAL) {
                dataSource.load();
            }
        }
    }

    /**
     * 扫描数据源
     * @param bean
     * @throws Exception
     */
    public void scanDataSources(Object bean) throws Exception {
        if (bean instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject)bean;
            //尝试是否为ds
            String dataSourceType = jsonObject.getString(DataSource.DATA_SOURCE_TYPE);
            boolean isDataSource;
            try {
                DataSourceTypeEnum.valueOf(dataSourceType);
                isDataSource = true;
            } catch (Exception e) {
                isDataSource = false;
            }
            if (isDataSource) {
                if (dataSourceMap == null) {
                    dataSourceMap = new HashMap<>();
                }
                //判断是否有重名
                String name = jsonObject.getString(DataSource.NAME);
                if (dataSourceMap.containsKey(name)) {
                    throw new DsDuplicateSourceException("已经有同名的数据源了:" + name);
                }
                dataSourceMap.put(name, new DataSource(jsonObject));
            } else {
                for (Object object : jsonObject.values()) {
                    scanDataSources(object);
                }
            }
        } else if (bean instanceof JSONArray) {
            for (Object object : (JSONArray) bean) {
                scanDataSources(object);
            }
        }
    }

    public JSONObject fetchReporter() throws Exception {
        fetchReporter(this.getJsonObject());
        return jsonObject;
    }

    private void fetchReporter(Object bean) throws Exception {
        if (bean instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject)bean;

            //尝试是否为ds
            Set<String> keys = jsonObject.keySet();
            for (String key: keys) {
                if (key.contains("DataSource:")) {
                    String value = jsonObject.getString(key);
                    DataSource dataSource = dataSourceMap.get(value);
                    dataSource.setFormContext(form);
                    dataSource.setApplicationContext(applicationContext);
                    dataSource.fetchData();
                    jsonObject.remove(key);
                    jsonObject.put(key.replace("DataSource_", ""), dataSource.getData());
                } else {
                    fetchReporter(jsonObject.get(key));
                }
            }
        } else if (bean instanceof JSONArray) {
            for (Object object : (JSONArray) bean) {
                fetchReporter(object);
            }
        }
    }

    //region G & S
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public Reporter setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    public Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    public Reporter setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
        return this;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Reporter setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    public Object getForm() {
        return form;
    }

    public Reporter setForm(Object form) {
        this.form = form;
        return this;
    }

    //endregion

    public static void main(String []args) throws Exception {
        Reporter reporter = loadReporter("/reporter/templates/lineChartDemo.json", DataSourceFromEnum.Resources);

        logger.debug(JSONObject.toJSONString(reporter.getJsonObject()));
        logger.debug(JSONObject.toJSONString(reporter.getDataSourceMap()));
        ReportFilterForm reportFilterForm = new ReportFilterForm();
        reportFilterForm.setServiceType("[\"1\",\"2\",\"3\",\"4\",\"6\"]");

        reporter.setForm(
                reportFilterForm
        );
        logger.debug(JSONObject.toJSONString(reporter.fetchReporter()));
    }
}
