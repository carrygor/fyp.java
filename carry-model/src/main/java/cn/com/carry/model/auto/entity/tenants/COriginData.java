package cn.com.carry.model.auto.entity.tenants;

import java.io.Serializable;
import java.util.Date;
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
 * @author 何文浩
 */
@TableName("c_origin_data")
public class COriginData extends Model<COriginData> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String url;
	private String title;
	private String text;
	private String company;
	@TableField("data_time")
	private String dataTime;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
    /**
     * 分析状态
     */
	private String status;


	public Long getId() {
		return id;
	}

	public COriginData setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public COriginData setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public COriginData setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getText() {
		return text;
	}

	public COriginData setText(String text) {
		this.text = text;
		return this;
	}

	public String getCompany() {
		return company;
	}

	public COriginData setCompany(String company) {
		this.company = company;
		return this;
	}

	public String getDataTime() {
		return dataTime;
	}

	public COriginData setDataTime(String dataTime) {
		this.dataTime = dataTime;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public COriginData setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public COriginData setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public COriginData setStatus(String status) {
		this.status = status;
		return this;
	}

	public static final String ID = "id";

	public static final String URL = "url";

	public static final String TITLE = "title";

	public static final String TEXT = "text";

	public static final String COMPANY = "company";

	public static final String DATA_TIME = "data_time";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String STATUS = "status";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "COriginData{" +
			"id=" + id +
			", url=" + url +
			", title=" + title +
			", text=" + text +
			", company=" + company +
			", dataTime=" + dataTime +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", status=" + status +
			"}";
	}
}
