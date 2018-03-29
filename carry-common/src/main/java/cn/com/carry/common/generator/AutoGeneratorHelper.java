package cn.com.carry.common.generator;

import cn.com.carry.common.util.AESUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/10/11.
 */
public class AutoGeneratorHelper {
    private static Logger logger = Logger.getLogger(AutoGeneratorHelper.class);
    /**
     */
    private static final String MODULE_PREFIX = "carry-";

    private static final String DB_USER = "carry";
    private static final String DB_PASS = AESUtil.AESDecode("TtWYUl3HAXPuVrkkXpB5bg==");
    private static final String DB_URL = "jdbc:mysql://139.199.4.149:3306/fyp?characterEncoding=utf8";

    /**
     * 根据需求来生成对应的DAO文件
     * @param moduleName
     * @return
     */
    private static List<FileOutConfig> getFileOutConfig(String moduleName) {
        List<FileOutConfig> focList = new ArrayList<>();
        //统一先删除文件夹：


        String mapperRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-dao/src/main/resources/" +
                "mapper/auto/" + moduleName + "/";
        deleteDir(new File(mapperRoot));
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return mapperRoot + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        //考虑到其他项目都会用到Entity
        String entityRoot = MODULE_PREFIX + "model/src/main/java/" +
                "cn/com/carry/model/auto/entity/" + moduleName + "/";
        deleteDir(new File(entityRoot));
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return entityRoot + tableInfo.getEntityName() + ".java";
            }
        });

        String mapperJavaRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-dao/src/main/java/" +
                "cn/com/carry/" + moduleName + "/dao/auto/";
        deleteDir(new File(mapperJavaRoot));
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return mapperJavaRoot + tableInfo.getEntityName() + "Mapper.java";
            }
        });

        String serviceRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-api/src/main/java/" +
                "cn/com/carry/" + moduleName + "/api/auto/";
        deleteDir(new File(serviceRoot));
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return serviceRoot + tableInfo.getEntityName() + "Service.java";
            }
        });

        String serviceImplRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-rpc-service/src/main/java/" +
                "cn/com/carry/" + moduleName + "/rpc/service/auto/";
        deleteDir(new File(serviceImplRoot));
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return serviceImplRoot + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });
        return focList;
    }

    static class Convertor {
        public String test(String string) {
            return "newString" + string;
        }
    }

    public static void generator(
            String moduleName,
            String []tblPrefix,
            String []tables

    ) {
        new AutoGenerator()
                .setCfg(new InjectionConfig() {
                            @Override
                            public void initMap() {
                                Map<String, Object> map = new HashMap<>();
                                map.put("converter", new Convertor() );
                                map.put("ddd", "ddasdasdasd" );
                                this.setMap(map);
                            }
                        }.setFileOutConfigList(getFileOutConfig(moduleName))
                ).setGlobalConfig(new GlobalConfig()
                                .setActiveRecord(true)// 开启 activeRecord 模式
                                .setEnableCache(true)// XML 二级缓存
                                .setBaseResultMap(true)// XML ResultMap
                                .setBaseColumnList(true)// XML columList
                                .setAuthor("何文浩")
                                .setFileOverride(true)
                                .setServiceName("%sService")
                                .setServiceImplName("%sServiceImpl")
                                .setOpen(false)
                ).setDataSource(new DataSourceConfig()
                                .setDbType(DbType.MYSQL)
                                .setTypeConvert(new MySqlTypeConvert())
                                .setDriverName("com.mysql.jdbc.Driver")
                                .setUsername(DB_USER)
                                .setPassword(DB_PASS)
                                .setUrl(DB_URL)
                ).setPackageInfo(new PackageConfig()
                                .setEntity("cn.com.carry.model.auto.entity." + moduleName)
                                .setMapper("cn.com.carry." + moduleName + ".dao.auto")
                                .setService("cn.com.carry." + moduleName + ".api.auto")
                                .setServiceImpl("cn.com.carry." + moduleName + ".rpc.service.auto")
                                .setModuleName(null)
                                .setParent(null)// 自定义包路径
                ).setStrategy(new StrategyConfig() //策略配置
                                .setTablePrefix(tblPrefix)
                                .setInclude(tables)
                                .setNaming(NamingStrategy.underline_to_camel)
                                .setVersionFieldName("version")
                                .setLogicDeleteFieldName("logic_delete")
                                .setEntityColumnConstant(true)
                                .setEntityBuilderModel(true)
                                .setSuperServiceClass("cn.com.carry.common.util.base.IMybatisService")
                                .setSuperServiceImplClass("cn.com.carry.common.util.base.MybatisServiceImpl")
                ).setTemplate(
                        //模板配置,这里阻止生成，然后在路径注入器修改生成的路径
                        new TemplateConfig()
                                .setController(null)
                                .setEntity(null)
                                .setMapper(null)
                                .setXml(null)
                                .setService(null)
                                .setServiceImpl(null)
                ).execute();
    }

    // 递归删除非空文件夹
    private static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i]);
            }
        }
        dir.delete();
    }

    public static final String [] TENNATS_TABLES = new String [] {
            "c_test",
            "c_user",
            "c_final_data",
            "c_origin_data",
            "c_origin_excel_data",
            "c_origin_data_page_url",
    };


    public static void main(String [] args) {
        generator(
                "tenants",
                new String [] {},
                TENNATS_TABLES
        );
    }
}
