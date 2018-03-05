package cn.com.carry.common.form;

/**
 * Created by 严汉羽 on 2017/11/2.
 */
public class ReportFilterForm {

    /**
     * 公司id
     */
    private Long tenantsCompanyId;

    /**
     * 问卷id
     */
    private Long questionnaireId;

    /**
     * 开始时间
     */
    private Long startDate;

    /**
     * 结束日期
     */
    private Long endDate;

    /**
     * 时间段过滤
     */
    private String period;

    private String periodMin;

    private String periodMax;

    /**
     * 服务类型
     */
    private String serviceType;

    private String keyword;

    /**
     * 网点过滤
     */
    private String siteOptions;

    private int pageSize;

    private int pageNum;

    private Long lastId;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSiteOptions() {
        return siteOptions;
    }

    public void setSiteOptions(String siteOptions) {
        this.siteOptions = siteOptions;
    }

    public String getPeriodMin() {
        return periodMin;
    }

    public void setPeriodMin(String periodMin) {
        this.periodMin = periodMin;
    }

    public String getPeriodMax() {
        return periodMax;
    }

    public void setPeriodMax(String periodMax) {
        this.periodMax = periodMax;
    }

    public Long getTenantsCompanyId() {
        return tenantsCompanyId;
    }

    public ReportFilterForm setTenantsCompanyId(Long tenantsCompanyId) {
        this.tenantsCompanyId = tenantsCompanyId;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public ReportFilterForm setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
