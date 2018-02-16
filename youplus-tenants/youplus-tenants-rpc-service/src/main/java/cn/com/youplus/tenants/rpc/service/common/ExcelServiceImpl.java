package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.UpAppsPhoneListService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.enums.*;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.model.auto.entity.apps.UpAppsPhoneList;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsLevel;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsUser;
import cn.com.youplus.tenants.api.auto.UpTenantsLevelService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserService;
import cn.com.youplus.tenants.api.common.ExcelService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import cn.com.youplus.tenants.common.form.PhoneListForm;
import cn.com.youplus.tenants.common.model.ErrorRegion;
import cn.com.youplus.tenants.common.model.ErrorUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("excelService")
public class ExcelServiceImpl implements ExcelService{


    private static Logger logger = Logger.getLogger(ExcelServiceImpl.class);

    //region 自动注入
    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private UpTenantsUserService upTenantsUserService;

    @Autowired
    private UpAppsPhoneListService upAppsPhoneListService;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;
    //endregion

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


    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfCell) throws AlertException {
        if (xssfCell == null) {
            return null;
        }
        switch (xssfCell.getCellTypeEnum()) {
            case ERROR:
                return xssfCell.getErrorCellString();
            case STRING:
                return xssfCell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(xssfCell.getBooleanCellValue());
            case NUMERIC:
                NumberFormat nf = NumberFormat.getInstance();
                nf.setGroupingUsed(false);
                String value = nf.format(xssfCell.getNumericCellValue());
                if (value.endsWith(".0")) {
                    return value.substring(0, value.length() - 2);
                } else {
                    return value;
                }
            case BLANK:
            case _NONE:
            default:
                return "";
        }
    }

    //region 导入组织结构
    private Integer getIsStoreByVal(String valStr) {

        if (valStr == null) {
            return null;
        }
        if (valStr.equals("是")) {
            return 1;
        }
        if (valStr.equals("否")) {
            return 0;
        }
        return null;
    }

    private UpTenantsRegion getParentIdAndLevel(UpTenantsRegion region, String regionName, List<UpTenantsLevel> levelList, UpTenantsCompany company) {
        if (regionName == null) {
            region.setLevel(0);
            region.setParentId(0L);
            return region;
        }
        UpTenantsRegion checkRegion = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.NAME, regionName)
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
        );
        if (checkRegion != null && checkRegion.getIsStore() == 0) {
            region.setParentId(checkRegion.getId());
            region.setQuickTag(checkRegion.getQuickTag());
            Integer newLevel = checkRegion.getLevel() + 1;
            region.setLevel(newLevel);
        }
        return region;
    }

    private int checkRegion(UpTenantsRegion region, UpTenantsCompany company, int num) {

        if (region == null) {
            return ErrorRegionCodeEnum.VAR_ERR.getCode();
        }
        if (region.getName() == null) {
            return ErrorRegionCodeEnum.NAME_NULL.getCode();
        }
        if (region.getParentId() == null) {
            try {
                //总部无上级
                if (region.getLevel() != 0) {
                    return ErrorRegionCodeEnum.PARENT_ID_NULL.getCode();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ErrorRegionCodeEnum.LEVEL_NULL.getCode();
            }
        }
        if (region.getLevel() == null) {
            return ErrorRegionCodeEnum.LEVEL_NULL.getCode();
        }
        if (region.getRegionNum() == null) {
            return ErrorRegionCodeEnum.REGION_NUM_NULL.getCode();
        }
        if (region.getIsStore() == null) {
            return ErrorRegionCodeEnum.IS_STORE_NULL.getCode();
        }
        if (region.getIsStore() == 1 && num > company.getMaxSiteNum()) {
            return ErrorRegionCodeEnum.REGION_OUT_OF_LIMIT.getCode();
        }
        //现在不用必填省市区
//        if (region.getIsStore() == 1) {
//            //网点需要省市区
//            if (region.getProvince() == null) {
//                return ErrorRegionCodeEnum.PROVINCE_NULL.getCode();
//            }
//            if (region.getCity() == null) {
//                return ErrorRegionCodeEnum.CITY_NULL.getCode();
//            }
//            if (region.getDistrict() == null) {
//                return ErrorRegionCodeEnum.DISTRICT_NULL.getCode();
//            }
//        }
        //检查名称唯一
        UpTenantsRegion check = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.NAME, region.getName())
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
        );
        if (check != null) {
            return ErrorRegionCodeEnum.NAME_DUPLICATE.getCode();
        }
        //检查编号唯一
        check = upTenantsRegionService.selectOne(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.REGION_NUM, region.getRegionNum())
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
        );
        if (check != null) {
            return ErrorRegionCodeEnum.REGION_NUM_DUPLICATE.getCode();
        }
        //名称长度
        if (region.getName().length() > 30) {
            return ErrorRegionCodeEnum.NAME_LENGTH_ERROR.getCode();
        }
        //编号长度
        if (region.getRegionNum().length() > 20) {
            return ErrorRegionCodeEnum.REGION_NUM_LENGTH_ERROR.getCode();
        }
        //详细地址长度
        if (region.getAddress() != null) {
            if (region.getAddress().length() > 30) {
                return ErrorRegionCodeEnum.ADDRESS_LENGTH_ERROR.getCode();
            }
        }

        return ErrorRegionCodeEnum.SUCC.getCode();
    }

    @Override
    public List<ErrorRegion> parseStructExcel(byte[] bytes, UpTenantsCompany company) throws Exception {
        //  准备数据
        List<UpTenantsLevel> levelList = upTenantsLevelService.selectList(
                new EntityWrapper<UpTenantsLevel>()
                .eq(UpTenantsLevel.TENANTS_COMPANY_ID, company.getId())
        );
        int storeNum = upTenantsRegionService.selectCount(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );
        List<ErrorRegion> errRegionList = new ArrayList<>();

        InputStream is = new ByteArrayInputStream(bytes);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("组织");

        if (xssfSheet == null) {
            throw new AlertException("找不到名字为[组织]的表单，请检查文档。");
        }

        //读取标题行
        List<String> titleList = new ArrayList<>();
        XSSFRow xssfRowFirst = xssfSheet.getRow(1);
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

        for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            logger.debug("读取第" + rowNum + "行数据:");
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }
            //创建一个实例
            UpTenantsRegion region = new UpTenantsRegion();
            region.setTenantsCompanyId(company.getId());
            for (int colNum = 0; colNum < titleList.size(); colNum++) {
                XSSFCell cell = xssfRow.getCell(colNum);
                String valStr = getValue(cell);
                String title = titleList.get(colNum);
                logger.debug("读取:["+ colNum + "," + rowNum + "]: " + title + " : " + valStr);
                StructureTitleEnum structureTitleEnum = StructureTitleEnum.containOf(title);
                switch (structureTitleEnum) {
                    case 组织名称:
                        region.setName(valStr);
                        break;
                    case 上级组织:
                        region = getParentIdAndLevel(region,valStr, levelList, company);
                        break;
                    case 组织编号:
                        region.setRegionCode(valStr);
                        region.setRegionNum(valStr);
                        break;
                    case 省:
                        region.setProvince(valStr);
                        break;
                    case 市:
                        region.setCity(valStr);
                        break;
                    case 区:
                        region.setDistrict(valStr);
                        break;
                    case 详细地址:
                        region.setAddress(valStr);
                        break;
                    case 是否网点:
                        region.setIsStore(getIsStoreByVal(valStr));
                    default:
                        break;
                }

            }

            int errCode = checkRegion(region, company, storeNum);
            if (errCode == ErrorRegionCodeEnum.SUCC.getCode()) {
                //网点的level要结合"上级组织"和"是否为网点"
                if (region.getLevel() == null && region.getIsStore() == 1 && region.getParentId() > 0) {
                    region.setLevel(levelList.size());
                }
                region = upTenantsRegionService.mInsert(region);
                if (region.getId() == null) {
                    errRegionList.add(new ErrorRegion(ErrorRegionCodeEnum.INSERT_ERR.getCode(), region, rowNum + 1));
                } else {
                    String prefix = region.getQuickTag() == null ? "," : region.getQuickTag();
                    String quickTag = prefix + region.getId() + ",";
                    region.setQuickTag(quickTag)
                            .setUpdateTime(new Date());
                    upTenantsRegionService.updateById(region);
                    if (region.getIsStore() == 1) {
                        storeNum++;
                    }
                }
            } else {
                errRegionList.add(new ErrorRegion(errCode, region, rowNum + 1));
            }

        }
        is.close();
        return errRegionList;
    }
    //endregion

    //region 导入用户
    @Override
    public List<ErrorUser> parseUserExcel(byte[] bytes, UpTenantsCompany company) throws Exception {

        List<ErrorUser> errorUserList = new ArrayList<>();

        InputStream is = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet("成员");
        int userNum = upTenantsUserService.selectCount(
                new EntityWrapper<UpTenantsUser>()
                        .eq(UpTenantsUser.TENANTS_COMPANY_ID, company.getId())
        );

        if (xssfSheet == null) {
            throw new AlertException("找不到名字为[成员]的表单，请检查文档。");
        }

        //读取行标题
        List<String> titleList = new ArrayList<>();
        XSSFRow xssfRowFirst = xssfSheet.getRow(1);
        int idx = 0;
        while (true) {
            XSSFCell cell = xssfRowFirst.getCell(idx);
            if (cell == null) {
                break;
            }
            titleList.add(getValue(cell));
            idx++;
        }

        for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow == null) {
                continue;
            }

            UpTenantsUser user = new UpTenantsUser();
            user.setTenantsCompanyId(company.getId());
            Boolean isStore = null;
            for (int colNum = 0; colNum < titleList.size(); colNum++) {
                XSSFCell cell = xssfRow.getCell(colNum);
                String valStr = getValue(cell);
                String title = titleList.get(colNum);

                UserTitleEnum userTitleEnum = UserTitleEnum.containOf(title);
                switch (userTitleEnum) {
                    case 员工姓名:
                        user.setRealName(valStr);
                        break;
                    case 邮箱:
                        user.setEmail(valStr);
                        break;
                    case 手机号:
                        user.setPhoneNum(valStr);
                        break;
                    case 初始密码:
                        user.setPassword(valStr);
                        break;
                    case 员工编号:
                        user.setUserNum(valStr);
                        break;
                    case 组织名称:
                        UpTenantsRegion region = upTenantsRegionService.selectOne(
                                new EntityWrapper<UpTenantsRegion>()
                                        .eq(UpTenantsRegion.NAME, valStr)
                                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        );
                        if (region != null) {
                            isStore = region.getIsStore() == 1;
                            user.setRegionId(region.getId());
                        } else {
                            user.setRegionId(0L);
                        }
                        break;
                    case 组织编号:
                        break;
                    case 系统登录名:
                        user.setUserName(valStr);
                        break;
                    case 组织负责人:
                        if (valStr != null) {
                            if (valStr.equals("是")) {
                                user.setIsLeader(1);
                            } else if (valStr.equals("否") || valStr.equals("")) {
                                user.setIsLeader(0);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            
            int errCode = checkUser(user, company, userNum);
            if (errCode == ErrorUserCodeEnum.SUCC.getCode()) {
                //set roleCode
                int roleCode = TenantsUserCodeEnum.默认用户.getCode();
                if (isStore != null) {
                    if (isStore) {
                        if (user.getIsLeader() == 1) {
                            roleCode = TenantsUserCodeEnum.网点负责人.getCode();
                        } else {
                            roleCode = TenantsUserCodeEnum.网点用户.getCode();
                        }
                    } else {
                        if (user.getIsLeader() == 1) {
                            roleCode = TenantsUserCodeEnum.区域负责人.getCode();
                        } else {
                            roleCode = TenantsUserCodeEnum.区域用户.getCode();
                        }
                    }
                }
                user.setRoleCode(roleCode);

                //password
                if (user.getPassword() == null) {
                    String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
                    String password = MD5Util.MD5(Constants.DEFAULT_PASSWORD).toLowerCase();
                    password = MD5Util.getPassword(password, salt);
                    user.setSalt(salt);
                    user.setPassword(password);
                } else {
                    String salt = StringHelper.getRandomLetterNo(Constants.PASSWORD_SALT_LENGTH);
                    String password = MD5Util.MD5(user.getPassword()).toLowerCase();
                    password = MD5Util.getPassword(password, salt);
                    user.setSalt(salt);
                    user.setPassword(password);
                }

                if (!upTenantsUserService.insert(user)) {
                    errorUserList.add(new ErrorUser(ErrorUserCodeEnum.INSERT_FAIL.getCode(), rowNum + 1, user));
                } else {
                    userNum++;
                }
            } else {
                errorUserList.add(new ErrorUser(errCode, rowNum + 1, user));
            }
        }
        return errorUserList;

    }

    private int checkUser(UpTenantsUser user, UpTenantsCompany company, int num) {

        if (num > company.getMaxUserNum()) {
            return ErrorUserCodeEnum.USER_OUT_OF_LIMIT.getCode();
        }

        if (user.getRealName() == null) {
            return ErrorUserCodeEnum.REAL_NAME_NULL.getCode();
        }
        if (user.getUserName() == null) {
            return ErrorUserCodeEnum.USER_NAME_NULL.getCode();
        }
        UpTenantsUser checkUserName = upTenantsUserService.selectOne(
                new EntityWrapper<UpTenantsUser>()
                        .eq(UpTenantsUser.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsUser.USER_NAME, user.getUserName())
        );
        if (checkUserName != null) {
            return ErrorUserCodeEnum.DUPLICATE_USER_NAME.getCode();
        }
        if (user.getEmail() == null) {
            return ErrorUserCodeEnum.EMAIL_NULL.getCode();
        }
        if (user.getIsLeader() == null) {
            return ErrorUserCodeEnum.IS_LEADER_NULL.getCode();
        }
        if (user.getRegionId() == null) {
            if (user.getIsLeader() == 1) {
                return ErrorUserCodeEnum.REGION_LEADER_CONFLICT.getCode();
            }
        }
        if (user.getPassword() != null) {
            if (user.getPassword().length() > 20 || user.getPassword().length() < 6) {
                return ErrorUserCodeEnum.PASSWORD_LENGTH_ERR.getCode();
            }
            if (!StringHelper.isFirstPasswordLegal(user.getPassword())) {
                return ErrorUserCodeEnum.ILLEGAL_PASSWORD.getCode();
            }
        }
        if (user.getPhoneNum() != null) {
            if (!StringHelper.isPhoneLegal(user.getPhoneNum())) {
                return ErrorUserCodeEnum.INCORRECT_PHONE_NUM.getCode();
            }
        }
        if (user.getEmail() != null) {
            if (!StringHelper.checkEmail(user.getEmail())) {
                return ErrorUserCodeEnum.INCORRECT_EMAIL.getCode();
            }
        }
        //该组织是否已存在负责人
        if (user.getRegionId() != null && user.getIsLeader() != null) {
            if (user.getIsLeader() == 1) {
                UpTenantsUser check = upTenantsUserService.selectOne(
                        new EntityWrapper<UpTenantsUser>()
                                .eq(UpTenantsUser.REGION_ID, user.getRegionId())
                                .eq(UpTenantsUser.TENANTS_COMPANY_ID, company.getId())
                                .eq(UpTenantsUser.IS_LEADER, 1)
                );
                if (check != null) {
                    return ErrorUserCodeEnum.LEADER_DUPLICATE.getCode();
                }
            }
        }
        //检查登录名合法
        if (!StringHelper.isUserNameLegal(user.getUserName())) {
            return ErrorUserCodeEnum.ILLEGAL_USER_NAME.getCode();
        }
        //编号合法
        if (user.getUserNum() != null) {
            UpTenantsUser checkUserNum = upTenantsUserService.selectOne(
                    new EntityWrapper<UpTenantsUser>()
                            .eq(UpTenantsUser.TENANTS_COMPANY_ID, company.getId())
                            .eq(UpTenantsUser.USER_NUM, user.getUserNum())
            );
            if (checkUserName != null) {
                return ErrorUserCodeEnum.DUPLICATE_USER_NUM.getCode();
            }

            if (!StringHelper.isUserNumLegal(user.getUserNum())) {
                return ErrorUserCodeEnum.ILLEGAL_USER_NUM.getCode();
            }
        }
        //组织名合法
        if (user.getRegionId() != null) {
            if (user.getRegionId() == 0) {
                return ErrorUserCodeEnum.ERR_REGION_NAME.getCode();
            }
        }
        //姓名长度
        if (user.getRealName().length() > 10) {
            return ErrorUserCodeEnum.REAL_NAME_LENGTH_ERR.getCode();
        }
        //登录名长度
        if (user.getRealName().length() > 30) {
            return ErrorUserCodeEnum.REAL_NAME_LENGTH_ERR.getCode();
        }
        //编号长度
        if (user.getUserNum().length() > 20) {
            return ErrorUserCodeEnum.USER_NUM_LENGTH_ERR.getCode();
        }
        return ErrorUserCodeEnum.SUCC.getCode();
    }

    //endregion

    //region 导出黑白名单excel

    @Override
    public String exportPhoneList(UpTenantsCompany company, PhoneListForm form) throws Exception{

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = workbook.createSheet("电话列表");
        List<UpAppsPhoneList> phoneList = upAppsPhoneListService.selectList(
                new EntityWrapper<UpAppsPhoneList>()
                        .eq(UpAppsPhoneList.TYPE, form.getType())
                        .eq(UpAppsPhoneList.QUESTIONNAIRE_ID, form.getQuestionnaireId())
        );
        for (int index = 0; index < phoneList.size(); index++) {
            UpAppsPhoneList phone = phoneList.get(index);
            XSSFRow row = xssfSheet.createRow(index);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(phone.getPhoneNum());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        String path = questionnaireSettingService.uploadOneFile(is, ".xlsx");
        os.close();
        is.close();
        return path;

    }

    //endregion

}
