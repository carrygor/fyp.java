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
@TableName("c_origin_data_page_url")
public class COriginDataPageUrl extends Model<COriginDataPageUrl> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("full_title")
	private String fullTitle;
	@TableField("sub_title")
	private String subTitle;
	@TableField("main_title")
	private String mainTitle;
	private String url;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public COriginDataPageUrl setId(Long id) {
		this.id = id;
		return this;
	}

	public String getFullTitle() {
		return fullTitle;
	}

	public COriginDataPageUrl setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public COriginDataPageUrl setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public COriginDataPageUrl setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public COriginDataPageUrl setUrl(String url) {
		this.url = url;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public COriginDataPageUrl setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public COriginDataPageUrl setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String FULL_TITLE = "full_title";

	public static final String SUB_TITLE = "sub_title";

	public static final String MAIN_TITLE = "main_title";

	public static final String URL = "url";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "COriginDataPageUrl{" +
			"id=" + id +
			", fullTitle=" + fullTitle +
			", subTitle=" + subTitle +
			", mainTitle=" + mainTitle +
			", url=" + url +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
