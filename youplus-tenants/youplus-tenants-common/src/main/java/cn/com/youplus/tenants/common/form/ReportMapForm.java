package cn.com.youplus.tenants.common.form;

import cn.com.youplus.common.form.ReportFilterForm;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/3.
 */
public class ReportMapForm extends ReportFilterForm implements Serializable {
    /**
     * NPS分项名称
     */
    private String npsType;

    public String getNpsType() {
        return npsType;
    }

    public void setNpsType(String npsType) {
        this.npsType = npsType;
    }
}
