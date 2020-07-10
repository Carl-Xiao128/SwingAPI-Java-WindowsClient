package entity;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceProCus {
	/**
	 * 流水单号
	 */
	private String invoiceCode;
	
	/**
	 * 发货日期
	 */
	private Date deliveryTime;
	
	/**
	 * 承运人
	 */
	private String carrier;
	
	/**
	 * 追加（元）
	 */
	private BigDecimal addMoney;
	
	/**
	 * 账款（元）
	 */
	private BigDecimal creditMoney;
	
	/**
	 * 款项类型
	 */
	private String moneyType;
	/**
	 * 客户名称
	 */
	private String cname;
	/**
	 * 区域
	 */
	private String area;
	/**
	 * 业务员
	 */
	private String person;
	/**
	 * 区域经理
	 */
	private String areaCharge;
	/**
	 * 产品类别
	 */
	private String ptype;
	/**
	 * 产品代码
	 */
	private String code;
	/**
	 * 产品名称
	 */
	private String pname;
	/**
	 * 规格单位
	 */
	private String standardUnit;
	/**
	 * 规格
	 */
	private BigDecimal standard;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 规格数量
	 */
	private BigDecimal productNum;
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;
	
	/**
	 * 金额小计
	 */
	private BigDecimal totalMoney;
	/**
	 * 折扣
	 */
	private BigDecimal discount;
	/**
	 * 备注
	 */
	private String remark;

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public BigDecimal getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(BigDecimal addMoney) {
		this.addMoney = addMoney;
	}

	public BigDecimal getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(BigDecimal creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getAreaCharge() {
		return areaCharge;
	}

	public void setAreaCharge(String areaCharge) {
		this.areaCharge = areaCharge;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getStandardUnit() {
		return standardUnit;
	}

	public void setStandardUnit(String standardUnit) {
		this.standardUnit = standardUnit;
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

	public BigDecimal getProductNum() {
		return productNum;
	}

	public void setProductNum(BigDecimal productNum) {
		this.productNum = productNum;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
