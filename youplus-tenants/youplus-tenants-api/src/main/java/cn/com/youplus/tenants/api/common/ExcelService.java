package cn.com.youplus.tenants.api.common;

import cn.com.youplus.tenants.common.form.PhoneListForm;
import cn.com.youplus.tenants.common.model.ErrorRegion;
import cn.com.youplus.tenants.common.model.ErrorUser;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;

import java.util.List;

public interface ExcelService {

    List<ErrorRegion> parseStructExcel(byte[] bytes, UpTenantsCompany company) throws Exception;

    List<ErrorUser> parseUserExcel(byte[] bytes, UpTenantsCompany company) throws Exception;

    String exportPhoneList(UpTenantsCompany company, PhoneListForm form) throws Exception;
}
