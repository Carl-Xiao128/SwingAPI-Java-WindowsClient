package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	
	/**
	 * 产品sid
	 */
	private Integer sid;
	
	/**
	 * 产品编码
	 */
	private String code;
	
	/**
	 * 产品名称
	 */
	private String pname;
	
	/**
	 * 产品类型
	 */
	private String ptype;
	
	/**
	 * 产品规格
	 */
	private BigDecimal standard;
	
	/**
	 * 产品规格单位
	 */
	private String standardUnit;	
	/**
	 * 产品单位
	 */
	private String unit;
	
	/**
	 *创建时间 
	 */
	private Date createTime;
	
	/**
	 * 操作时间
	 */
	private Date optTime;
	
	/**
	 * 是否有效 1表示无效，0表示有效
	 */
	private String ifDelete;

	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}


	public BigDecimal getStandard() {
		return standard;
	}

	public void setStandard(BigDecimal standard) {
		this.standard = standard;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(String ifDelete) {
		this.ifDelete = ifDelete;
	}

	public String getStandardUnit() {
		return standardUnit;
	}

	public void setStandardUnit(String standardUnit) {
		this.standardUnit = standardUnit;
	}
	
	
	
}
