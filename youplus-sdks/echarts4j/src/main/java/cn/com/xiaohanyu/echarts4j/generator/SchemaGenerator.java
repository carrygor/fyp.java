package cn.com.xiaohanyu.echarts4j.generator;

/**
 * Created by 严汉羽 on 2017/11/17.
 */

import cn.com.xiaohanyu.echarts4j.model.Enum.ObjectTypeEnum;
import cn.com.xiaohanyu.echarts4j.utils.StringUtil;
import cn.com.xiaohanyu.echarts4j.utils.VelocityUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据结构生成对象文件,将生成文件位于工程的model/auto下
 */
public class SchemaGenerator {

    private static Logger logger = LoggerFactory.getLogger(SchemaGenerator.class);

    /**
     * Schema文件路径，这个文件是从 echarts.baidu.com 网站上下载得到的。
     * <link>http://echarts.baidu.com/documents/cn/option.json?_v_=1510583274902</link>
     */
    private static String resSchemaPath = "/schema/option.json";

    /**
     * 自动生成的根包路径
     */
    private static String basePackage = "cn.com.xiaohanyu.echarts4j.model.auto";

    /**
     * 生成的相对目录
     */
    private static String baseDir = "/src/main/java/cn/com/xiaohanyu/echarts4j/model/auto/";

    /**
     * Velocity模板文件
     */
    public static String generatorConfigVm = "/templates/charts.java.vm";

    /**
     * 不需要 转成 Map 的基本数据类型，注意这里跟 java 的基本类型有区别
     */
    public static final String[] baseTypes = {
            "boolean",
            "int",
            "integer",
            "long",
            "double",
            "float",
            "byte",
            "char",
            "short"
    };

    /**
     * 自定义的通用类型，无需在解释，一般超过两次重复的类
     */
    public static final String[] baseDefinedTypes = {
            "Color",
            "CrossStyle",
            "Emphasis",
            "IconStyle",
            "ItemStyle",
            "Line",
            "LineStyle",
            "Normal",
            "Rich",
            "ShadowStyle",
            "TextStyle",
            "AreaStyle",
            "NameTextStyle",
    };


    /**
     * 执行生成过程
     * 注意这个过程只能在IDE中执行，不能通过build出来的jar包，通过java指令执行
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SchemaGenerator generator = new SchemaGenerator();
        JSONObject rootJson = generator.loadSchemaJson(resSchemaPath);

        //注意:windows下才需要执行这个步骤，因为windows下的路径都是以盘符开头的
        String OS = System.getProperty("os.name");

        generatorConfigVm = SchemaGenerator.class.getResource(generatorConfigVm).getPath();
        if (generatorConfigVm.startsWith("/") && OS != null && OS.toLowerCase().contains("windows")) {
            generatorConfigVm = generatorConfigVm.substring(1);
        }
        //找到源文件的根目录:
        String exeRoot = SchemaGenerator.class.getResource("/").getPath();
        if (exeRoot.startsWith("/") && OS != null && OS.toLowerCase().contains("windows")) {
            exeRoot = exeRoot.substring(1);
        }
        if (exeRoot.endsWith("/")) {
            exeRoot = exeRoot.substring(0, exeRoot.length() - 1);
        }
        exeRoot = exeRoot.substring(0, exeRoot.lastIndexOf("/"));
        exeRoot = exeRoot.substring(0, exeRoot.lastIndexOf("/"));
        exeRoot = exeRoot + baseDir;
        logger.debug("即将删除一下文件夹下的所有文件:" + exeRoot);
        deleteDir(new File(exeRoot), false);
        generator.parseOneObject(rootJson, exeRoot, "", "EChart", "ECharts的主结构类");
    }

    /**
     * 加载json文件，并将其解释为JSONObject
     * @param schemaPath 资源对应的路径
     * @return 并将其解释为JSONObject
     * @throws Exception
     */
    public JSONObject loadSchemaJson(String schemaPath) throws Exception{
        URL schemaUrl = SchemaGenerator.class.getResource(schemaPath);
        if (schemaUrl == null) {
            throw new Exception("文件不存在:" + schemaPath);
        }
        logger.debug("Option文件的路径:" + schemaUrl.getPath());

        StringBuilder stringBuilder = new StringBuilder(20*1024*1024);
        BufferedReader br=new BufferedReader(new FileReader( schemaUrl.getPath()));
        int i=br.read();
        while (i!=-1){
            stringBuilder.append((char)i);
            i=br.read();
        }
        br.close();

        JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());
        return jsonObject == null ? null : jsonObject.getJSONObject("option").getJSONObject("properties");
    }

    /**
     * 递归解释对象
     * @param jsonObject 需要生成的json对象
     * @param currentRoot 当前的根目录
     * @param deltaPackage 在basePage基础上的增量Package
     * @param objectName 需要生成的对象名称
     * @throws Exception
     */
    public void parseOneObject(JSONObject jsonObject, String currentRoot, String deltaPackage, String objectName, String comment) throws Exception{
        if (jsonObject == null) {
            return;
        }
        String outputFile = currentRoot + StringUtil.toUpperCaseFirstOne(objectName) + ".java";

        VelocityContext context = new VelocityContext();
        List<Map<String, Object>> fields = new ArrayList<Map<String, Object>>();
        context.put("package", basePackage +deltaPackage);
        context.put("objectName", objectName);
        context.put("comments", comment.split("\n"));

        //遍历字段
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                logger.debug("属性:" + key);
                JSONObject childObject = (JSONObject)value;
                if (isBaseDefinedType(key)) { //已经定义过的类型了
                    String type = getBaseDefinedType(key);
                    Map<String, Object> arrayField = new HashMap<String, Object>();
                    arrayField.put("name", key);
                    arrayField.put("capitalName", StringUtil.toUpperCaseFirstOne(key));
                    arrayField.put("isDefalutType", isDefaultType(type));
                    arrayField.put("type", getBaseDefinedType(key));
                    fields.add(arrayField);
                } else {
                    ObjectTypeEnum [] types = getObjectType(childObject.get("type"));
                    if ((types[types.length - 1] == ObjectTypeEnum.Object ||
                            types[types.length - 1] == ObjectTypeEnum.All)) {

                        if (isInter(key)) {
                            key = "replace" + key;
                        }

                        JSONObject toParse = childObject.getJSONObject("properties");
                        if (toParse != null) {
                            String type = objectName + StringUtil.toUpperCaseFirstOne(key);
                            Map<String, Object> arrayField = new HashMap<String, Object>();
                            arrayField.put("name", key);
                            arrayField.put("isDefalutType", isDefaultType(type));
                            arrayField.put("capitalName", StringUtil.toUpperCaseFirstOne(key));
                            arrayField.put("type", type);
                            fields.add(arrayField);

                            parseOneObject(
                                    toParse,
                                    currentRoot,
                                    deltaPackage,
                                    objectName + StringUtil.toUpperCaseFirstOne(key),
                                    childObject.getString("descriptionCN")
                            );
                        } else {
                            Map<String, Object> arrayField = new HashMap<String, Object>();
                            arrayField.put("name", key);
                            arrayField.put("capitalName", StringUtil.toUpperCaseFirstOne(key));
                            arrayField.put("type", "Object");
                            arrayField.put("isDefalutType", "true");
                            fields.add(arrayField);
                        }
                    } else if (types[types.length - 1] == ObjectTypeEnum.Array) {
                        Map<String, Object> arrayField = new HashMap<String, Object>();
                        arrayField.put("name", key);
                        arrayField.put("capitalName", StringUtil.toUpperCaseFirstOne(key));
                        arrayField.put("isDefalutType", "true");
                        arrayField.put("type", "List");
                        fields.add(arrayField);

                        JSONObject items = childObject.getJSONObject("items");
                        if (items != null) {
                            JSONArray anyOf = items.getJSONArray("anyOf");
                            if (anyOf != null) {
                                for (Object anyObject : anyOf) {
                                    JSONObject anyJson = (JSONObject) anyObject;
                                    ObjectTypeEnum[] subTypes = getObjectType(anyJson.get("type"));
                                    if (subTypes[subTypes.length - 1] == ObjectTypeEnum.Object ||
                                            subTypes[subTypes.length - 1] == ObjectTypeEnum.All) {

                                        String nameMore = anyJson.getJSONObject("properties").getJSONObject("type").getString("default");
                                        nameMore = nameMore.replace("'", "");

                                        parseOneObject(
                                                anyJson.getJSONObject("properties"),
                                                currentRoot,
                                                deltaPackage,
                                                objectName + StringUtil.toUpperCaseFirstOne(key) + StringUtil.toUpperCaseFirstOne(nameMore),
                                                anyJson.getString("descriptionCN")
                                        );
                                    }

                                }
                            } else {
                                //先不管
                            }
                        }
                    } else {
                        if (types.length == 1) {
                            String type;
                            switch (types[0]) {
                                case Color:
                                    type = "Color";
                                    break;
                                case Number:
                                    type = "Number";
                                    break;
                                case Boolean:
                                    type = "Boolean";
                                    break;
                                case Prefix:
                                case String:
                                case Date:
                                    type = "String";
                                    break;
                                case Function:
                                case All:
                                case Object:
                                case Array:
                                default:
                                    type = "Object";
                            }
                            Map<String, Object> arrayField = new HashMap<String, Object>();
                            arrayField.put("name", key);
                            arrayField.put("capitalName", StringUtil.toUpperCaseFirstOne(key));
                            arrayField.put("type", type);
                            arrayField.put("isDefalutType", "true");
                            fields.add(arrayField);
                        } else { //如果有多重对象时，用Object代替
                            Map<String, Object> arrayField = new HashMap<String, Object>();
                            arrayField.put("name", key);
                            arrayField.put("capitalName", StringUtil.toUpperCaseFirstOne(key));
                            arrayField.put("type", "Object");
                            arrayField.put("isDefalutType", "true");
                            fields.add(arrayField);
                        }
                    }
                }

            } else {
                logger.error("不认识的对象");
                throw new Exception("呼叫120，有不认识的对象");
            }

        }

        context.put("fields", fields);
        context.put("package", basePackage);
        logger.info("输出文件:" + outputFile);
        VelocityUtil.generate(generatorConfigVm, outputFile, context);


    }

    /**
     * 判断是否为自定义类型
     * @param type
     * @return
     */
    private boolean isBaseDefinedType(String type) {
        String firstUpper = StringUtil.toUpperCaseFirstOne(type);
        for (String baseType : baseDefinedTypes) {
            if (baseType.equals(firstUpper)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInter(String key) {
        try {
            Integer.parseInt(key);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 获取自定义类型的名称
     * @param type
     * @return
     */
    private String getBaseDefinedType(String type) {
        String firstUpper = StringUtil.toUpperCaseFirstOne(type);
        for (String baseType : baseDefinedTypes) {
            if (baseType.equals(firstUpper)) {
                return baseType;
            }
        }
        return null;
    }

    /**
     * 判断类型是不是基本的类型，无需 toMap 转换
     * @param type
     * @return
     */
    private boolean isDefaultType(String type) {
        String typeLower = type.toLowerCase();
        for (String baseType : baseTypes) {
            if (baseType.equals(typeLower)) {
                return true;
            }
        }
        return false;
    }

    private ObjectTypeEnum [] getObjectType(Object typeObject) throws Exception {
        if (typeObject instanceof String) {
            return new ObjectTypeEnum [] {ObjectTypeEnum.valueOf(StringUtil.toUpperCaseFirstOne(String.valueOf(typeObject).replace("*", "All")))};
        } else if (typeObject instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray)typeObject;
            ObjectTypeEnum [] typeEnums = new ObjectTypeEnum[jsonArray.size()];
            int i = 0;
            for (Object name : (JSONArray)typeObject) {
                typeEnums[i] = ObjectTypeEnum.valueOf(StringUtil.toUpperCaseFirstOne(String.valueOf(name).replace("*", "All")));
                i++;
            }
            return typeEnums;
        } else {
            throw new Exception("不认识的类型:" + JSONObject.toJSONString(typeObject));
        }
    }

    /**
     * 递归删除文件夹
     * @param rootFile 需要删除的文件夹或者文件
     */
    public static void deleteDir(File rootFile, boolean isDeleteSelf) {
        if (rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i], true);
            }
        }
        if (isDeleteSelf && !rootFile.getName().contains("readme")) {
            rootFile.delete();
        }
    }


}
