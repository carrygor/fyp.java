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
	private String title;
	private String text;
	@TableField("data_time")
	private String dataTime;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public COriginData setId(Long id) {
		this.id = id;
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

	public static final String ID = "id";

	public static final String TITLE = "title";

	public static final String TEXT = "text";

	public static final String DATA_TIME = "data_time";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "COriginData{" +
			"id=" + id +
			", title=" + title +
			", text=" + text +
			", dataTime=" + dataTime +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
