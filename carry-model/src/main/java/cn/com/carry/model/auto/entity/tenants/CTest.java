package cn.com.carry.model.auto.entity.tenants;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 何文浩
 */
@TableName("c_test")
public class CTest extends Model<CTest> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer age;


	public Long getId() {
		return id;
	}

	public CTest setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public CTest setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public CTest setAge(Integer age) {
		this.age = age;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String AGE = "age";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CTest{" +
			"id=" + id +
			", name=" + name +
			", age=" + age +
			"}";
	}
}
