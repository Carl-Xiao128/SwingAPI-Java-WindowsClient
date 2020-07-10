package entity.vo;

import java.math.BigDecimal;

import entity.Invoice;

public class InvoiceProductVo extends Invoice{
	
	private String productSid;
	
	private String code;
	
	private String pname;
	
	/**
	 * 产品规格
	 */
	private String standard;
	
	/**
	 * 产品规格单位
	 */
	private String standardUnit;
	
	/**
	 * 产品数量
	 */
	private String productNum;
	
	/**
	 *单价
	 */
	private BigDecimal unitPrice;
	
	

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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getStandardUnit() {
		return standardUnit;
	}

	public void setStandardUnit(String standardUnit) {
		this.standardUnit = standardUnit;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	
}
