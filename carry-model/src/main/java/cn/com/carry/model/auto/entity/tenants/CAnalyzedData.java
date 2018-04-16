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
@TableName("c_analyzed_data")
public class CAnalyzedData extends Model<CAnalyzedData> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String supplier;
	private String status;
	@TableField("product_list")
	private String productList;
	private String suggest;
	@TableField("relieve_time")
	private String relieveTime;
	@TableField("add_time")
	private Date addTime;


	public Long getId() {
		return id;
	}

	public CAnalyzedData setId(Long id) {
		this.id = id;
		return this;
	}

	public String getSupplier() {
		return supplier;
	}

	public CAnalyzedData setSupplier(String supplier) {
		this.supplier = supplier;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public CAnalyzedData setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getProductList() {
		return productList;
	}

	public CAnalyzedData setProductList(String productList) {
		this.productList = productList;
		return this;
	}

	public String getSuggest() {
		return suggest;
	}

	public CAnalyzedData setSuggest(String suggest) {
		this.suggest = suggest;
		return this;
	}

	public String getRelieveTime() {
		return relieveTime;
	}

	public CAnalyzedData setRelieveTime(String relieveTime) {
		this.relieveTime = relieveTime;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public CAnalyzedData setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public static final String ID = "id";

	public static final String SUPPLIER = "supplier";

	public static final String STATUS = "status";

	public static final String PRODUCT_LIST = "product_list";

	public static final String SUGGEST = "suggest";

	public static final String RELIEVE_TIME = "relieve_time";

	public static final String ADD_TIME = "add_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CAnalyzedData{" +
			"id=" + id +
			", supplier=" + supplier +
			", status=" + status +
			", productList=" + productList +
			", suggest=" + suggest +
			", relieveTime=" + relieveTime +
			", addTime=" + addTime +
			"}";
	}
}
