package entity;

import java.util.Date;

public class SearchParam {
	
	/**
	 * 客户名称
	 */
	private String cname;
	
	/**
	 * 产品代码
	 */
	private String code;
	
	/**
	 * 产品名称
	 */
	private String pname;
	
	/**
	 * 模糊搜索字段
	 */
	private String fieldLike;
	
	/**
	 * 发货开始时间
	 */
	private Date startTime;
	
	/**
	 * 发货结束时间
	 */
	private Date endTime;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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
