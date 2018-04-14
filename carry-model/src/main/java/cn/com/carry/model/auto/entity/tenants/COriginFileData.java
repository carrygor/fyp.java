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
@TableName("c_origin_file_data")
public class COriginFileData extends Model<COriginFileData> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String url;
	@TableField("file_name")
	private String fileName;
	@TableField("data_time")
	private String dataTime;
	private String publisher;
	@TableField("add_time")
	private Date addTime;


	public Long getId() {
		return id;
	}

	public COriginFileData setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public COriginFileData setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public COriginFileData setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public COriginFileData setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public String getDataTime() {
		return dataTime;
	}

	public COriginFileData setDataTime(String dataTime) {
		this.dataTime = dataTime;
		return this;
	}

	public String getPublisher() {
		return publisher;
	}

	public COriginFileData setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public COriginFileData setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public static final String ID = "id";

	public static final String TITLE = "title";

	public static final String URL = "url";

	public static final String FILE_NAME = "file_name";

	public static final String DATA_TIME = "data_time";

	public static final String PUBLISHER = "publisher";

	public static final String ADD_TIME = "add_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "COriginFileData{" +
			"id=" + id +
			", title=" + title +
			", url=" + url +
			", fileName=" + fileName +
			", dataTime=" + dataTime +
			", publisher=" + publisher +
			", addTime=" + addTime +
			"}";
	}
}
