package entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerProRelRecord{

	private Date createTime;

	private String customerSid;

	private Date optTime;

	private String productSid;

	private Integer sid;

	private BigDecimal unitPrice;

	public CustomerProRelRecord() {
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCustomerSid() {
		return this.customerSid;
	}

	public void setCustomerSid(String customerSid) {
		this.customerSid = customerSid;
	}

	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getProductSid() {
		return this.productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}


	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}