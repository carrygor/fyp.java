package cn.com.youplus.model.auto.entity.thirdparty;

import java.io.Serializable;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_tp_smr_excel_result")
public class UpTpSmrExcelResult extends Model<UpTpSmrExcelResult> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 级别，0：大区，1：小区域，2：油站
     */
	private Integer level;
    /**
     * 报告id
     */
	@TableField("report_id")
	private String reportId;
    /**
     * 客户编号
     */
	@TableField("c_id")
	private String cId;
    /**
     * 客户名称
     */
	@TableField("c_name")
	private String cName;
    /**
     * 时间区域，精确到月份
     */
	@TableField("time_slot")
	private Date timeSlot;
    /**
     * 大区域的名字
     */
	private String province;
    /**
     * 小区域名称
     */
	private String district;
    /**
     * 区域编码
     */
	@TableField("district_code")
	private String districtCode;
    /**
     * 该区域的区域得分
     */
	@TableField("district_score")
	private String districtScore;
    /**
     * 油站名称
     */
	@TableField("gas_name")
	private String gasName;
    /**
     * 油站代码
     */
	@TableField("gas_code")
	private String gasCode;
    /**
     * 油站排名
     */
	private Integer rank;
    /**
     * 总得分
     */
	@TableField("total_score")
	private String totalScore;
    /**
     * 油站整体得分
     */
	@TableField("yzzt_score")
	private String yzztScore;
    /**
     * 加油服务得分
     */
	@TableField("jyfw_score")
	private String jyfwScore;
    /**
     * 便利店得分情况
     */
	@TableField("bld_score")
	private String bldScore;
    /**
     * 卫生间得分情况
     */
	@TableField("wsj_score")
	private String wsjScore;
    /**
     * 外观得分
     */
	@TableField("wg_score")
	private String wgScore;
    /**
     * 服务得分
     */
	@TableField("fw_score")
	private String fwScore;
    /**
     * 卫生得分
     */
	@TableField("ws_score")
	private String wsScore;
    /**
     * 平均NPS得分
     */
	@TableField("nps_score")
	private String npsScore;
    /**
     * NPS得分的JSON数据结构
     */
	@TableField("nps_score_json")
	private String npsScoreJson;
    /**
     * 乐观锁
     */
	@Version
	private Integer version;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
    /**
     * 排序，越小拍越靠前
     */
	private Integer sort;
    /**
     * 插入时间
     */
	@TableField("add_time")
	private Date addTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public UpTpSmrExcelResult setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public UpTpSmrExcelResult setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public String getReportId() {
		return reportId;
	}

	public UpTpSmrExcelResult setReportId(String reportId) {
		this.reportId = reportId;
		return this;
	}

	public String getcId() {
		return cId;
	}

	public UpTpSmrExcelResult setcId(String cId) {
		this.cId = cId;
		return this;
	}

	public String getcName() {
		return cName;
	}

	public UpTpSmrExcelResult setcName(String cName) {
		this.cName = cName;
		return this;
	}

	public Date getTimeSlot() {
		return timeSlot;
	}

	public UpTpSmrExcelResult setTimeSlot(Date timeSlot) {
		this.timeSlot = timeSlot;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public UpTpSmrExcelResult setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public UpTpSmrExcelResult setDistrict(String district) {
		this.district = district;
		return this;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public UpTpSmrExcelResult setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
		return this;
	}

	public String getDistrictScore() {
		return districtScore;
	}

	public UpTpSmrExcelResult setDistrictScore(String districtScore) {
		this.districtScore = districtScore;
		return this;
	}

	public String getGasName() {
		return gasName;
	}

	public UpTpSmrExcelResult setGasName(String gasName) {
		this.gasName = gasName;
		return this;
	}

	public String getGasCode() {
		return gasCode;
	}

	public UpTpSmrExcelResult setGasCode(String gasCode) {
		this.gasCode = gasCode;
		return this;
	}

	public Integer getRank() {
		return rank;
	}

	public UpTpSmrExcelResult setRank(Integer rank) {
		this.rank = rank;
		return this;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public UpTpSmrExcelResult setTotalScore(String totalScore) {
		this.totalScore = totalScore;
		return this;
	}

	public String getYzztScore() {
		return yzztScore;
	}

	public UpTpSmrExcelResult setYzztScore(String yzztScore) {
		this.yzztScore = yzztScore;
		return this;
	}

	public String getJyfwScore() {
		return jyfwScore;
	}

	public UpTpSmrExcelResult setJyfwScore(String jyfwScore) {
		this.jyfwScore = jyfwScore;
		return this;
	}

	public String getBldScore() {
		return bldScore;
	}

	public UpTpSmrExcelResult setBldScore(String bldScore) {
		this.bldScore = bldScore;
		return this;
	}

	public String getWsjScore() {
		return wsjScore;
	}

	public UpTpSmrExcelResult setWsjScore(String wsjScore) {
		this.wsjScore = wsjScore;
		return this;
	}

	public String getWgScore() {
		return wgScore;
	}

	public UpTpSmrExcelResult setWgScore(String wgScore) {
		this.wgScore = wgScore;
		return this;
	}

	public String getFwScore() {
		return fwScore;
	}

	public UpTpSmrExcelResult setFwScore(String fwScore) {
		this.fwScore = fwScore;
		return this;
	}

	public String getWsScore() {
		return wsScore;
	}

	public UpTpSmrExcelResult setWsScore(String wsScore) {
		this.wsScore = wsScore;
		return this;
	}

	public String getNpsScore() {
		return npsScore;
	}

	public UpTpSmrExcelResult setNpsScore(String npsScore) {
		this.npsScore = npsScore;
		return this;
	}

	public String getNpsScoreJson() {
		return npsScoreJson;
	}

	public UpTpSmrExcelResult setNpsScoreJson(String npsScoreJson) {
		this.npsScoreJson = npsScoreJson;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpTpSmrExcelResult setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpTpSmrExcelResult setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public UpTpSmrExcelResult setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpTpSmrExcelResult setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpTpSmrExcelResult setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String LEVEL = "level";

	public static final String REPORT_ID = "report_id";

	public static final String C_ID = "c_id";

	public static final String C_NAME = "c_name";

	public static final String TIME_SLOT = "time_slot";

	public static final String PROVINCE = "province";

	public static final String DISTRICT = "district";

	public static final String DISTRICT_CODE = "district_code";

	public static final String DISTRICT_SCORE = "district_score";

	public static final String GAS_NAME = "gas_name";

	public static final String GAS_CODE = "gas_code";

	public static final String RANK = "rank";

	public static final String TOTAL_SCORE = "total_score";

	public static final String YZZT_SCORE = "yzzt_score";

	public static final String JYFW_SCORE = "jyfw_score";

	public static final String BLD_SCORE = "bld_score";

	public static final String WSJ_SCORE = "wsj_score";

	public static final String WG_SCORE = "wg_score";

	public static final String FW_SCORE = "fw_score";

	public static final String WS_SCORE = "ws_score";

	public static final String NPS_SCORE = "nps_score";

	public static final String NPS_SCORE_JSON = "nps_score_json";

	public static final String VERSION = "version";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String SORT = "sort";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpTpSmrExcelResult{" +
			"id=" + id +
			", level=" + level +
			", reportId=" + reportId +
			", cId=" + cId +
			", cName=" + cName +
			", timeSlot=" + timeSlot +
			", province=" + province +
			", district=" + district +
			", districtCode=" + districtCode +
			", districtScore=" + districtScore +
			", gasName=" + gasName +
			", gasCode=" + gasCode +
			", rank=" + rank +
			", totalScore=" + totalScore +
			", yzztScore=" + yzztScore +
			", jyfwScore=" + jyfwScore +
			", bldScore=" + bldScore +
			", wsjScore=" + wsjScore +
			", wgScore=" + wgScore +
			", fwScore=" + fwScore +
			", wsScore=" + wsScore +
			", npsScore=" + npsScore +
			", npsScoreJson=" + npsScoreJson +
			", version=" + version +
			", logicDelete=" + logicDelete +
			", sort=" + sort +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
