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
@TableName("c_origin_excel_data")
public class COriginExcelData extends Model<COriginExcelData> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String html;
	private String status;
	@TableField("data_time")
	private String dataTime;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
	private String title;
	private String url;


	public Long getId() {
		return id;
	}

	public COriginExcelData setId(Long id) {
		this.id = id;
		return this;
	}

	public String getHtml() {
		return html;
	}

	public COriginExcelData setHtml(String html) {
		this.html = html;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public COriginExcelData setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getDataTime() {
		return dataTime;
	}

	public COriginExcelData setDataTime(String dataTime) {
		this.dataTime = dataTime;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public COriginExcelData setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public COriginExcelData setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public COriginExcelData setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public COriginExcelData setUrl(String url) {
		this.url = url;
		return this;
	}

	public static final String ID = "id";

	public static final String HTML = "html";

	public static final String STATUS = "status";

	public static final String DATA_TIME = "data_time";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String TITLE = "title";

	public static final String URL = "url";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "COriginExcelData{" +
			"id=" + id +
			", html=" + html +
			", status=" + status +
			", dataTime=" + dataTime +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", title=" + title +
			", url=" + url +
			"}";
	}
}
