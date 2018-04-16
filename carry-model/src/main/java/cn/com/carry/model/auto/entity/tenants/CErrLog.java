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
@TableName("c_err_log")
public class CErrLog extends Model<CErrLog> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("function_name")
	private String functionName;
	private String log;
	@TableField("add_time")
	private Date addTime;


	public Long getId() {
		return id;
	}

	public CErrLog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getFunctionName() {
		return functionName;
	}

	public CErrLog setFunctionName(String functionName) {
		this.functionName = functionName;
		return this;
	}

	public String getLog() {
		return log;
	}

	public CErrLog setLog(String log) {
		this.log = log;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public CErrLog setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public static final String ID = "id";

	public static final String FUNCTION_NAME = "function_name";

	public static final String LOG = "log";

	public static final String ADD_TIME = "add_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CErrLog{" +
			"id=" + id +
			", functionName=" + functionName +
			", log=" + log +
			", addTime=" + addTime +
			"}";
	}
}
