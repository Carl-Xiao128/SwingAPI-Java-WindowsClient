package entity.vo;

import java.util.Date;

import entity.CustomerProRel;

public class CustomerProRelVo extends CustomerProRel{
	
	/**
	 * 产品编码
	 */
	private String code;
	/**
	 * 产品名称
	 */
	private String pname;
	/**
	 * 客户名称
	 */
	private String cname;
	/**
	 * 产品规格
	 */
	private String standard;
	
	/**
	 * 产品规格单位
	 */
	private String standardUnit;
	
	/**
	 * 产品单位
	 */
	private String unit;
	
	/**
	 * 模糊查询
	 */
	private String fieldLike;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	

	public String getStandardUnit() {
		return standardUnit;
	}

	public void setStandardUnit(String standardUnit) {
		this.standardUnit = standardUnit;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFieldLike() {
		return fieldLike;
	}

	public void setFieldLike(String fieldLike) {
		this.fieldLike = fieldLike;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
