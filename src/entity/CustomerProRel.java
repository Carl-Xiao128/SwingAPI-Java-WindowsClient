package entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerProRel {
	
	/**
	 *sid 
	 */
	private Integer sid;
	
	/**
	 *客户sid
	 */
	private String customerSid;
	
	/**
	 *单价
	 */
	private BigDecimal unitPrice;
	
	/**
	 *产品sid
	 */
	private String productSid;
	
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

	public String getCustomerSid() {
		return customerSid;
	}

	public void setCustomerSid(String customerSid) {
		this.customerSid = customerSid;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
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
	
	

}
