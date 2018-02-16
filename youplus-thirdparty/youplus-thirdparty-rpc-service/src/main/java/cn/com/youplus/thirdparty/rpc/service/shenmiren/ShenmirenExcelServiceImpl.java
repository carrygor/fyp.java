package cn.com.youplus.thirdparty.rpc.service.shenmiren;

import cn.com.youplus.common.exception.data.DataErrorException;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.thirdparty.UpTpSmrExcelResult;
import cn.com.youplus.thirdparty.api.auto.UpTpSmrExcelResultService;
import cn.com.youplus.thirdparty.api.shenmiren.ShenmirenExcelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 严汉羽 on 2017/10/12.
 */

@Service("shenmirenExcelService")
public class ShenmirenExcelServiceImpl implements ShenmirenExcelService {

    private static Logger logger = Logger.getLogger(ShenmirenExcelServiceImpl.class);

    @Autowired
    private UpTpSmrExcelResultService tpTpSmrExcelResultService;

    public static void main(String [] args) {
        String str = "A.6★便利店员工服务态度33";
        String pattern = "([\\u4e00-\\u9fa5]+)";

        Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = r.matcher(str);
        logger.debug(m.find());
        logger.debug(m.group(0));
    }

    /**
     * 公式计算器
     */
    private static FormulaEvaluator evaluator;

    /**
     * 使用到的全局常亮
     * ["所属分类","所属区域","区域得分","油站代码","油站名称","油站排名","总得分","A.油站整体","B.加油服务","C.便利店","D.卫生间","外观","服务","卫生"]
     */

    public enum ShenmirenExcelTitleEnum {
        所属分类(-1,"所属分类"),
        区域编码(0,"区域编码"),
        所属区域(1,"所属区域"),
        区域得分(2,"区域得分"),
        油站编码(3,"油站编码"),
        油站名称(4,"油站名称"),
        油站排名(5,"油站排名"),
        总得分(6,"总得分"),
        油站整体(7,"油站整体"),
        加油服务(8,"加油服务"),
        便利店(9,"便利店"),
        卫生间(10,"卫生间"),
        外观(11,"外观"),
        服务(12,"服务"),
        卫生(13,"卫生");
        private int index;
        private String name;
        ShenmirenExcelTitleEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public static ShenmirenExcelTitleEnum testOf(String name) {
            name = name.replaceFirst("^[A-Z](\\.)", "");
            return ShenmirenExcelTitleEnum.valueOf(name);
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum ShenmirenNpsExcelTitleEnum {
        区域(-1,"区域"),
        油站代码(0,"油站代码"),
        油站名称(1,"油站名称"),
        油站得分(2,"油站得分"),
        加油站整体环境(3,"加油站整体环境"),
        油品质量(4,"油品质量"),
        便利店在售商品(5,"便利店在售商品"),
        加油员服务态度(6,"加油员服务态度"),
        加油员工作效率(7,"加油员工作效率"),
        便利店员工服务态度(8,"便利店员工服务态度"),
        便利店员工工作效率(9,"便利店员工工作效率"),
        洗手间卫生(10,"洗手间卫生"),
        油站秩序管理(11,"油站秩序管理"),
        加油卡使用(12,"加油卡使用");
        private int index;
        private String name;
        ShenmirenNpsExcelTitleEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public static ShenmirenNpsExcelTitleEnum testOf(String name) {
            String pattern = "([\\u4e00-\\u9fa5]+)";
            Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = r.matcher(name);
            logger.debug(m.find());
            logger.debug(m.group(0));
            return ShenmirenNpsExcelTitleEnum.valueOf(m.group(0));
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //region 从EXCEL中解析数据

    /**
     * 解析输入的文件
     * @param path
     * @throws Exception
     */
    @Override
    public void parseExcel(String path, String province, String dateStr) throws Exception {
        System.out.println("测试文件:" + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        evaluator=xssfWorkbook.getCreationHelper().createFormulaEvaluator();

        List<UpTpSmrExcelResult> smrExcelResults = parseExcel得到总体分数(xssfWorkbook, province,dateStr);
        // Read the Sheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("站点成绩表");
        if (xssfSheet == null) {
            xssfSheet = xssfWorkbook.getSheet("满意度");
        }
        if (xssfSheet == null) {
            throw new AlertException("找不到名字为[站点成绩表/满意度]的表单，请检查文档。");
        }

        //读取标题行
        List<String> titleList = new ArrayList<>();
        XSSFRow xssfRowFirst = xssfSheet.getRow(0);
        int idx = 0;
        while (true) {
            XSSFCell cell = xssfRowFirst.getCell(idx);
            if (cell == null) {
                break;
            }
            titleList.add(getValue(cell));
            idx++;
            //如果读到空的单元格
        }
        logger.debug(JSONObject.toJSONString(titleList));
        // Read the Rows
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            logger.debug("读取第" + rowNum + "行数据:");
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            //创建一个实例
            UpTpSmrExcelResult smrExcelResult = null;
            List<List<String>> npsJson = new ArrayList<>();
            for (int colNum = 0; colNum < titleList.size(); colNum++) {
                XSSFCell cell = xssfRow.getCell(colNum);
                if (cell == null) {
                    continue;
                }
                String valStr = getValue(cell);
                String title = titleList.get(colNum);
                logger.debug("读取:[" + colNum + "," + rowNum + "]: " + title + " : " + valStr);
                ShenmirenNpsExcelTitleEnum npsExcelTitleEnum = ShenmirenNpsExcelTitleEnum.testOf(titleList.get(colNum));

                //只负责找对应的code 同事记录得分
                switch (npsExcelTitleEnum) {
                    case 油站代码:
                        for (UpTpSmrExcelResult result : smrExcelResults) {
                            if (valStr.trim().equals(result.getGasCode())) {
                                smrExcelResult = result;
                                break;
                            }
                        }
                        break;
                    case 油站得分:
                        if (smrExcelResult != null) {
                            smrExcelResult.setNpsScore(getNpsFromStr(valStr));
                        }
                        break;
                    case 洗手间卫生:
                    case 油站秩序管理:
                    case 便利店在售商品:
                    case 加油员工作效率:
                    case 加油员服务态度:
                    case 加油站整体环境:
                    case 便利店员工工作效率:
                    case 便利店员工服务态度:
                    case 油品质量:
                    case 加油卡使用:
                        List<String> npsItem = new ArrayList<>(2);
                        npsItem.add(npsExcelTitleEnum.getName());
                        npsItem.add(getNpsFromStr(valStr));
                        npsJson.add(npsItem);
                        break;
                    case 油站名称:
                    case 区域:
                        break;
                }
            }
            if (smrExcelResult != null) {
                smrExcelResult.setNpsScoreJson(JSONObject.toJSONString(npsJson));
            }

        }

        //继续读取平均的
        xssfSheet = xssfWorkbook.getSheet("区域成绩表");

        if (xssfSheet == null) {
            throw new AlertException("找不到名字为[区域成绩表]的表单，请检查文档。");
        }

        titleList = new ArrayList<>();
        xssfRowFirst = xssfSheet.getRow(0);
        idx = 0;
        while (true) {
            XSSFCell cell = xssfRowFirst.getCell(idx);
            if (cell == null) {
                break;
            }
            titleList.add(getValue(cell));
            idx++;
            //如果读到空的单元格
        }
        logger.debug(JSONObject.toJSONString(titleList));
        // Read the Rows
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            logger.debug("读取第" + rowNum + "行数据:");
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            //创建一个实例
            UpTpSmrExcelResult regionRecord = null;
            List<List<String>> npsJson = new ArrayList<>();
            for (int colNum = 0; colNum < titleList.size(); colNum++) {
                XSSFCell cell = xssfRow.getCell(colNum);
                if (cell == null) {
                    continue;
                }
                String valStr = getValue(cell);
                String title = titleList.get(colNum);
                logger.debug("读取:[" + colNum + "," + rowNum + "]: " + title + " : " + valStr);
                ShenmirenNpsExcelTitleEnum npsExcelTitleEnum = ShenmirenNpsExcelTitleEnum.testOf(titleList.get(colNum));

                //只负责找对应的code 同事记录得分
                switch (npsExcelTitleEnum) {
                    case 区域:
                        if (valStr.trim().equals("总体平均值")) {
                            for (UpTpSmrExcelResult result : smrExcelResults) {
                                if (result.getLevel() == null || result.getLevel() == 0) {
                                    regionRecord = result;
                                    break;
                                }
                            }
                        } else {
                            for (UpTpSmrExcelResult result : smrExcelResults) {
                                if (result.getLevel() != null && result.getLevel() == 1 && result.getDistrict().equals(valStr.trim())) {
                                    regionRecord = result;
                                    break;
                                }
                            }
                        }
                        break;
                    case 油站得分:
                        if (regionRecord != null) {
                            regionRecord.setNpsScore(getNpsFromStr(valStr));
                        }
                        break;
                    case 洗手间卫生:
                    case 油站秩序管理:
                    case 便利店在售商品:
                    case 加油员工作效率:
                    case 加油员服务态度:
                    case 加油站整体环境:
                    case 便利店员工工作效率:
                    case 便利店员工服务态度:
                    case 油品质量:
                    case 加油卡使用:
                        List<String> npsItem = new ArrayList<>(2);
                        npsItem.add(npsExcelTitleEnum.getName());
                        npsItem.add(getNpsFromStr(valStr));
                        npsJson.add(npsItem);
                        break;
                    case 油站名称:
                    default:
                        break;
                }
            }
            if (regionRecord != null) {
                regionRecord.setNpsScoreJson(JSONObject.toJSONString(npsJson));
            }
            //继续读取平均的
        }

        tpTpSmrExcelResultService.insertBatch(smrExcelResults);

        logger.debug("最终结果:");
        logger.debug(JSONObject.toJSONString(smrExcelResults));
    }

    public List<UpTpSmrExcelResult> parseExcel得到总体分数(XSSFWorkbook xssfWorkbook, String province, String dateStr) throws Exception {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMM");
        Date date = DATE_FORMAT.parse(dateStr);
        // Read the Sheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("明细表");

        if (xssfSheet == null) {
            xssfSheet = xssfWorkbook.getSheet("暗访明细表");
        }

        if (xssfSheet == null) {
            throw new AlertException("找不到名字为[明细表/暗访明细表]的表单，请检查文档。");
        }

        //读取标题行
        List<String> titleList = new ArrayList<>();
        XSSFRow xssfRowFirst = xssfSheet.getRow(0);
        int idx = 0;
        while(true) {
            XSSFCell cell = xssfRowFirst.getCell(idx);
            if (cell ==null) {
                break;
            }
            titleList.add(getValue(cell));
            idx ++;
            //如果读到空的单元格
        }
        logger.debug(JSONObject.toJSONString(titleList));
        // Read the Rows
        //考虑到有合并单元格情况
        String last所属分类 = province;
        String last区域编码 = null;
        String last所属区域 = null;
        String last区域得分 = null;
        boolean is总平均分 = false;
        boolean is添加新的区域 = false;
        int sort = 1;

        //目前只有一个省份，全部删除就是了
        tpTpSmrExcelResultService.delete(
                new EntityWrapper<UpTpSmrExcelResult>()
                        .eq(UpTpSmrExcelResult.TIME_SLOT, date)
                        .eq(UpTpSmrExcelResult.PROVINCE, province));

        List<UpTpSmrExcelResult> records = new ArrayList<>();

        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            logger.debug("读取第" + rowNum + "行数据:");
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            //创建一个实例
            UpTpSmrExcelResult smrExcelResult = new UpTpSmrExcelResult();
            smrExcelResult.setNpsScore("18.0");    //默认值
            smrExcelResult.setTotalScore("92%");  //默认值

            for (int colNum = 0; colNum < titleList.size(); colNum++) {
                XSSFCell cell = xssfRow.getCell(colNum);
                if (cell == null) {
                    continue;
                }
                String valStr = getValue(cell);
                String title = titleList.get(colNum);
                logger.debug("读取:["+ colNum + "," + rowNum + "]: " + title + " : " + valStr);
                ShenmirenExcelTitleEnum shenmirenExcelTitleEnum = ShenmirenExcelTitleEnum.testOf(titleList.get(colNum));
                switch (shenmirenExcelTitleEnum) {
                    case 区域编码:
                        if (!ValueHelper.isNone(valStr)) {
                            last区域编码 = valStr;
                        }

                        if (!ValueHelper.isNone(valStr)) {
                            if (valStr.contains("平均")) {
                                is总平均分 = true;
                                break;
                            }
                        }

                        smrExcelResult.setDistrictCode(last区域编码);
                        break;
                    case 所属分类:
                        if (!ValueHelper.isNone(valStr)) {
                            last所属分类 = valStr;
                        }
                        smrExcelResult.setProvince(last所属分类);
                        break;
                    case 所属区域:
                        if (!ValueHelper.isNone(valStr)) {
                            //检测区域的变化，如果有变化，就需要插入一条区域代码
                            if (!valStr.equals(last所属区域)) {
                                is添加新的区域 = true;
                                last所属区域 = valStr;
                            }
                        }
                        smrExcelResult.setDistrict(last所属区域);
                        break;
                    case 区域得分:
                        if (!ValueHelper.isNone(valStr)) {
                            last区域得分 = valStr;
                        }
                        smrExcelResult.setDistrictScore(getPrecentageFromStr(last区域得分));
                        break;
                    case 油站名称:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setGasName(valStr);
                        }
                        break;
                    case 油站编码:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setGasCode(valStr);
                        }
                        break;
                    case 油站排名:
                        if (!ValueHelper.isNone(valStr)) {
                            if (valStr.contains("平均分")) {
                                is总平均分 = true;
                            } else {
                                smrExcelResult.setRank(getInt(valStr));
                            }
                        }
                        break;
                    case 总得分:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setTotalScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 油站整体:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setYzztScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 加油服务:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setJyfwScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 便利店:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setBldScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 卫生间:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setWsjScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 外观:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setWgScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 服务:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setFwScore(getPrecentageFromStr(valStr));
                        }
                        break;
                    case 卫生:
                        if (!ValueHelper.isNone(valStr)) {
                            smrExcelResult.setFwScore(getPrecentageFromStr(valStr));
                        }
                        break;
                }
            }

            if (is总平均分) {
                is总平均分 = false;
                smrExcelResult
                        .setSort(1)
                        .setProvince(last所属分类)
                        .setLevel(0)
                        .setDistrict(null)
                        .setDistrictScore(null)
                        .setGasCode(null)
                        .setGasName(null)
                        .setRank(null);
            } else {
                if (is添加新的区域) {
                    is添加新的区域 = false;
                    //插入一条记录
                    sort++;
                    UpTpSmrExcelResult smrExcelResultRegion = new UpTpSmrExcelResult();
                    smrExcelResultRegion.setSort(sort)
                            .setTimeSlot(date)
                            .setLevel(1)
                            .setProvince(last所属分类)
                            .setDistrictCode(smrExcelResult.getDistrictCode())
                            .setDistrict(smrExcelResult.getDistrict())
                            .setDistrictScore(smrExcelResult.getDistrictScore());
                    records.add(smrExcelResultRegion);
                }

                //排除空记录
                if (!ValueHelper.isNone(smrExcelResult.getTotalScore())) {
                    sort++;
                    smrExcelResult.setProvince(last所属分类).setLevel(2).setSort(sort);
                }
            }
            smrExcelResult.setTimeSlot(date);
            records.add(smrExcelResult);
        }

        return records;
    }

    /**
     * 由于getNumbericValue 都会带上 .0  所以对于整形，需要特殊处理
     * @param string
     * @return
     */
    private static Integer getInt(String string) {
        if (ValueHelper.isNone(string)) {
            return null;
        }
        try {
            return (int)Float.parseFloat(string);
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 得到百分号
     * @param string
     * @return
     */
    private static String getPrecentageFromStr(String string) {
        if (ValueHelper.isNone(string)) {
            return "";
        }
        try {
            return ValueHelper.rintPrecentage(Float.parseFloat(string));
        } catch (Exception e) {
            logger.error("", e);
            return string;
        }
    }

    /**
     * 得到NPS值，保留一位小数, 默认值是 18.0
     * @param string
     * @return
     */
    private static String getNpsFromStr(String string) {
        if (ValueHelper.isNone(string)) {
            return "";
        }
        try {
            int intVal = (int)Math.rint(Float.parseFloat(string) * 10);
            BigDecimal bigDecimal = new BigDecimal((double)intVal / 10);
            return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP) + "";
        } catch (Exception e) {
            logger.error("", e);
            return string;
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfCell) throws AlertException {
        switch (xssfCell.getCellTypeEnum()) {
            case ERROR:
                return xssfCell.getErrorCellString();
            case STRING:
                return xssfCell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(xssfCell.getBooleanCellValue());
            case FORMULA:
                CellValue value = evaluator.evaluate(xssfCell);
                switch (value.getCellTypeEnum()) {
                    case NUMERIC:
                        return String.valueOf(value.getNumberValue());
                    case STRING:
                        return value.getStringValue();
                    default:
                        throw new AlertException("不支持的公式类型");
                }
            case NUMERIC:
                return String.valueOf(xssfCell.getNumericCellValue());
            case BLANK:
            case _NONE:
            default:
                return "";
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    //endregion

    //region 从数据库中读取数据
    @Override
    public Map<String, Object> getParsedExcelData(String name, String dateStr) throws Exception {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            throw new Exception("非法日期格式:" + dateStr);
        }

        List<UpTpSmrExcelResult> results = tpTpSmrExcelResultService.selectList(new EntityWrapper<UpTpSmrExcelResult>()
                .eq(UpTpSmrExcelResult.PROVINCE, name)
                .eq(UpTpSmrExcelResult.TIME_SLOT, date)
                .orderBy(UpTpSmrExcelResult.SORT, true)
        );

        if (results.size() == 0) {
            throw new DataErrorException("没有数据");
        }
        //1. 找出第 0 层：
        UpTpSmrExcelResult parent = null;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getLevel() == 0) {
                parent = results.get(i);
                break;
            }
        }

        if (parent == null) {
            throw new DataErrorException("数据错误，请联系客服。");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("report_id", "YOUPLUS" + StringHelper.getRandomNumber(16));
        result.put("date", dateStr);
        result.put("c_id", "fjzh13572");
        result.put("c_name", "福建中化");

        result.put("total_score", parent.getTotalScore());
        result.put("total_nps_score", parent.getNpsScore());
        result.put("score_ag", getSocres(parent));
        result.put("nps_score", getNps(parent));

        List<Object> regions = new ArrayList<>();
        for(UpTpSmrExcelResult result1 : results) {
            if(result1.getLevel() == 0) {
                continue;
            }
            //找到区域记录
            if (result1.getLevel() == 1) {
                Map<String, Object> region = new HashMap<>();
                region.put("reg_name", result1.getDistrict());
                region.put("reg_code", result1.getDistrictCode());
                region.put("score", result1.getDistrictScore());
                region.put("total_nps_score", result1.getNpsScore());

                List<Object> nodes = new ArrayList<>();
                //继续找子记录
                for(UpTpSmrExcelResult result2 : results) {
                    if(result2.getLevel() == 2 && result2.getDistrict().equals(result1.getDistrict())) {
                        //网点
                        Map<String, Object> node = new HashMap<>();
                        node.put("code", result2.getGasCode());
                        node.put("node_name", result2.getGasName());
                        node.put("type", result2.getProvince());
                        node.put("order", result2.getRank());
                        node.put("total_score", result2.getTotalScore());
                        node.put("group_score", getSocres(result2));
                        node.put("total_nps_score", result2.getNpsScore());
                        node.put("nps_score", getNps(result2));
                        nodes.add(node);
                    }
                }
                region.put("node", nodes);
                regions.add(region);
            }
        }

        result.put("region", regions);
        return  result;
    }

    public JSONArray getNps(UpTpSmrExcelResult result) {
        if (ValueHelper.isNone(result.getNpsScoreJson())) {
            return new JSONArray();
        }
        return JSONObject.parseArray(result.getNpsScoreJson());
    }

    public List<Object> getSocres(UpTpSmrExcelResult result) {
        List<Object> scoreAg = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            List<String> score = new ArrayList<>();
            switch (i) {
                case 0:
                    score.add(ShenmirenExcelTitleEnum.油站整体.getName());
                    score.add(result.getYzztScore());
                    break;
                case 1:
                    score.add(ShenmirenExcelTitleEnum.加油服务.getName());
                    score.add(result.getJyfwScore());
                    break;
                case 2:
                    score.add(ShenmirenExcelTitleEnum.便利店.getName());
                    score.add(result.getBldScore());
                    break;
                case 3:
                    score.add(ShenmirenExcelTitleEnum.卫生间.getName());
                    score.add(result.getWsjScore());
                    break;
                case 4:
                    score.add(ShenmirenExcelTitleEnum.外观.getName());
                    score.add(result.getWgScore());
                    break;
                case 5:
                    score.add(ShenmirenExcelTitleEnum.服务.getName());
                    score.add(result.getFwScore());
                    break;
                case 6:
                    score.add(ShenmirenExcelTitleEnum.卫生.getName());
                    score.add(result.getWsScore());
                    break;
            }
            if (!ValueHelper.isNone(score.get(1))) {
                scoreAg.add(score);
            }
        }
        return scoreAg;
    }
    //endregion

}
