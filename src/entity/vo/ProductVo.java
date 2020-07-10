package entity.vo;

import java.math.BigDecimal;

import entity.Product;

public class ProductVo extends Product{
	private BigDecimal unitPrice;
	
	private String cName;
	
	

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}	
}
