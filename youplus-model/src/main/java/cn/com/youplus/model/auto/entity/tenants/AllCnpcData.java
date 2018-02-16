package cn.com.youplus.model.auto.entity.tenants;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("all_cnpc_data")
public class AllCnpcData extends Model<AllCnpcData> implements Serializable {

    private static final long serialVersionUID = 1L;

	private String id;
	@TableField("visit_time")
	private String visitTime;
	private String province;
	@TableField("phone_num")
	private String phoneNum;
    /**
     * 是否已经导出
     */
	@TableField("is_exported")
	private Integer isExported;


	public String getId() {
		return id;
	}

	public AllCnpcData setId(String id) {
		this.id = id;
		return this;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public AllCnpcData setVisitTime(String visitTime) {
		this.visitTime = visitTime;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public AllCnpcData setProvince(String province) {
		this.province = province;
		return this;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public AllCnpcData setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}

	public Integer getIsExported() {
		return isExported;
	}

	public AllCnpcData setIsExported(Integer isExported) {
		this.isExported = isExported;
		return this;
	}

	public static final String ID = "id";

	public static final String VISIT_TIME = "visit_time";

	public static final String PROVINCE = "province";

	public static final String PHONE_NUM = "phone_num";

	public static final String IS_EXPORTED = "is_exported";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AllCnpcData{" +
			"id=" + id +
			", visitTime=" + visitTime +
			", province=" + province +
			", phoneNum=" + phoneNum +
			", isExported=" + isExported +
			"}";
	}
}
