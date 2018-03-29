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
@TableName("c_final_data")
public class CFinalData extends Model<CFinalData> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("data_type")
	private String dataType;
	private String supplier;
	@TableField("product_name")
	private String productName;
	@TableField("product_buyer")
	private String productBuyer;
	@TableField("announcement_publisher")
	private String announcementPublisher;
	@TableField("publish_time")
	private String publishTime;
	@TableField("handle_way")
	private String handleWay;
	@TableField("time_range")
	private String timeRange;
	@TableField("handle_reason")
	private String handleReason;
	private String url;
	@TableField("add_time")
	private Date addTime;


	public Long getId() {
		return id;
	}

	public CFinalData setId(Long id) {
		this.id = id;
		return this;
	}

	public String getDataType() {
		return dataType;
	}

	public CFinalData setDataType(String dataType) {
		this.dataType = dataType;
		return this;
	}

	public String getSupplier() {
		return supplier;
	}

	public CFinalData setSupplier(String supplier) {
		this.supplier = supplier;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public CFinalData setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public String getProductBuyer() {
		return productBuyer;
	}

	public CFinalData setProductBuyer(String productBuyer) {
		this.productBuyer = productBuyer;
		return this;
	}

	public String getAnnouncementPublisher() {
		return announcementPublisher;
	}

	public CFinalData setAnnouncementPublisher(String announcementPublisher) {
		this.announcementPublisher = announcementPublisher;
		return this;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public CFinalData setPublishTime(String publishTime) {
		this.publishTime = publishTime;
		return this;
	}

	public String getHandleWay() {
		return handleWay;
	}

	public CFinalData setHandleWay(String handleWay) {
		this.handleWay = handleWay;
		return this;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public CFinalData setTimeRange(String timeRange) {
		this.timeRange = timeRange;
		return this;
	}

	public String getHandleReason() {
		return handleReason;
	}

	public CFinalData setHandleReason(String handleReason) {
		this.handleReason = handleReason;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public CFinalData setUrl(String url) {
		this.url = url;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public CFinalData setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public static final String ID = "id";

	public static final String DATA_TYPE = "data_type";

	public static final String SUPPLIER = "supplier";

	public static final String PRODUCT_NAME = "product_name";

	public static final String PRODUCT_BUYER = "product_buyer";

	public static final String ANNOUNCEMENT_PUBLISHER = "announcement_publisher";

	public static final String PUBLISH_TIME = "publish_time";

	public static final String HANDLE_WAY = "handle_way";

	public static final String TIME_RANGE = "time_range";

	public static final String HANDLE_REASON = "handle_reason";

	public static final String URL = "url";

	public static final String ADD_TIME = "add_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CFinalData{" +
			"id=" + id +
			", dataType=" + dataType +
			", supplier=" + supplier +
			", productName=" + productName +
			", productBuyer=" + productBuyer +
			", announcementPublisher=" + announcementPublisher +
			", publishTime=" + publishTime +
			", handleWay=" + handleWay +
			", timeRange=" + timeRange +
			", handleReason=" + handleReason +
			", url=" + url +
			", addTime=" + addTime +
			"}";
	}
}