package entity;

import java.util.Date;

public class InvoiceProductRel{

	/**
	 * sid
	 */
	private String sid;

	/**
	 * 流水单sid
	 */
	private String invoiceSid;

	/**
	 * 产品sid
	 */
	private String productSid;
	
	/**
	 * 产品数量
	 */
	private String productNum;
	
	/**
	 * 创建时间 
	 */
	private Date createTime;

	public InvoiceProductRel() {
	}
	
	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getInvoiceSid() {
		return this.invoiceSid;
	}

	public void setInvoiceSid(String invoiceSid) {
		this.invoiceSid = invoiceSid;
	}

	public String getProductSid() {
		return this.productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}