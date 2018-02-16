package cn.com.youplus.common.generator;

import cn.com.youplus.common.util.AESUtil;
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
    private static final String BASE_PACKAGE = "cn.com.youplus";
    private static final String MODULE_PREFIX = "youplus-";

    private static final String DB_USER = "admin";
    private static final String DB_PASS = AESUtil.AESDecode("PjhVRC58IHZecJQtuC8W2w==");
    private static final String DB_URL = "jdbc:mysql://120.78.211.19:3306/youplus_test_v100?characterEncoding=utf8";

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
                "cn/com/youplus/model/auto/entity/" + moduleName + "/";
        deleteDir(new File(entityRoot));
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return entityRoot + tableInfo.getEntityName() + ".java";
            }
        });

        String mapperJavaRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-dao/src/main/java/" +
                "cn/com/youplus/" + moduleName + "/dao/auto/";
        deleteDir(new File(mapperJavaRoot));
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return mapperJavaRoot + tableInfo.getEntityName() + "Mapper.java";
            }
        });

        String serviceRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-api/src/main/java/" +
                "cn/com/youplus/" + moduleName + "/api/auto/";
        deleteDir(new File(serviceRoot));
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return serviceRoot + tableInfo.getEntityName() + "Service.java";
            }
        });

        String serviceImplRoot = MODULE_PREFIX + moduleName + "/" +
                MODULE_PREFIX + moduleName + "-rpc-service/src/main/java/" +
                "cn/com/youplus/" + moduleName + "/rpc/service/auto/";
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
                                .setAuthor("严汉羽")
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
                                .setEntity("cn.com.youplus.model.auto.entity." + moduleName)
                                .setMapper("cn.com.youplus." + moduleName + ".dao.auto")
                                .setService("cn.com.youplus." + moduleName + ".api.auto")
                                .setServiceImpl("cn.com.youplus." + moduleName + ".rpc.service.auto")
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
                                .setSuperServiceClass("cn.com.youplus.common.util.base.IMybatisService")
                                .setSuperServiceImplClass("cn.com.youplus.common.util.base.MybatisServiceImpl")
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

    public static final String [] APPS_TABLES = new String [] {
            "up_apps_answer_sheet",
            "up_apps_answer_sheet_item",
            "up_apps_question",
            "up_apps_phone_list",
            "up_apps_question_item",
            "up_apps_questionnaire",
            "up_apps_questionnaire_attribute",
            "up_apps_questionnaire_theme",
            "up_apps_request_log",
            "up_apps_answer_sheet_view",
            "up_apps_answer_sheet_item_view",
            "up_apps_answer_sheet_detail_view",
            "up_apps_answer_sheet_item_detail_view",
/*            "all_cnpc_result",
            "cnpc_export_data",*/
    };

    public static final String [] BASE_TABLES = new String [] {
            "up_base_request_log",
            "up_base_system_config"
    };

    public static final String [] NOTIFICATION_TABLES = new String [] {
            "up_notification_email_record",
            "up_notification_message_queue_record",
            "up_notification_request_log",
            "up_notification_sms_record",
            "up_notification_weixin_template_record"
    };

    public static final String [] TENNATS_TABLES = new String [] {
            "up_tenants_company",
            "up_tenants_region",
            "up_tenants_level",
            "up_tenants_request_log",
            "up_tenants_user",
            "up_tenants_user_role",
            "up_tenants_user_region_view",
            "tb_qm_effectiveresultdetail",
            "tb_qm_effectiveresult",
            "tb_qm_submitresult",
            "tb_qm_answerresult",
            "all_cnpc_data",
            "up_tenants_task"
    };

    public static final String [] THIRDPARTY_TABLES = new String [] {
            "up_tp_request_log",
            "up_tp_smr_excel_result",
            "up_weixin_user",
            "up_weixin_response_rule",
            "up_weixin_request_log",
            "up_weixin_message_log",
            "up_weixin_account"
    };

    public static final String [] CMS_TABLES = new String [] {
            "up_cms_request_log",
            "up_cms_role",
            "up_cms_role_permission",
            "up_cms_user",
    };


    public static void main(String [] args) {
        generator(
                "base",
                new String [] {},
                BASE_TABLES
        );

        generator(
                "notification",
                new String [] {},
                NOTIFICATION_TABLES

        );

        generator(
                "apps",
                new String [] {},
                APPS_TABLES
        );

        generator(
                "tenants",
                new String [] {},
                TENNATS_TABLES
        );

        generator(
                "cms",
                new String [] {},
                CMS_TABLES
        );

        generator(
                "thirdparty",
                new String [] {},
                THIRDPARTY_TABLES
        );
    }
}
