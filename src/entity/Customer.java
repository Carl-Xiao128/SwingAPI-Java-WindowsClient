package entity;

import java.util.Date;

public class Customer {
	
	/**
	 * 客户sid
	 */
	private Integer sid;
	
	/**
	 * 客户名称
	 */
	private String cname;
	
	/**
	 * 负责人
	 */
	private String person;
	
	/**
	 * 省区经理
	 */
	private String areaCharge;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 区域
	 */
	private String area;
	
	/**
	 * 地址
	 */
	private String address;
	
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	public String toString(){
	    return this.address+this.cname+this.area;	
	}

	public String getAreaCharge() {
		return areaCharge;
	}

	public void setAreaCharge(String areaCharge) {
		this.areaCharge = areaCharge;
	}
	
}
