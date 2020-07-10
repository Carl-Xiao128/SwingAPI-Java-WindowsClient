package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice {

	/**
	 * 流水單sid
	 */
    private String sid;
    
    /**
     * 流水单号
     */
    private String invoiceCode;
    
	/**
	 * 车牌号
	 */
	private String carNum;
	
	/**
	 * 承运人
	 */
	private String carrier;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 客户sid
	 */
	private String customerSid;


	/**
	 * 发货日期
	 */
	private Date deliveryDatetime;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 运费明细
	 */
	private String freight;


	/**
	 * 开票日期
	 */
	private Date invoiceDatetime;

	/**
	 * 开票人
	 */
	private String invoicePerson;

	/**
	 * 驾驶证号
	 */
	private String licenseNumber;
	/**
	 * 款额
	 */
	private BigDecimal money;
	/**
	 * 追加
	 */
	private BigDecimal addMoney;
	/**
	 * 账款
	 */
	private BigDecimal creditMoney;

	/**
	 * 运费类型
	 */
	private String moneyType;


	/**
	 * 操作时间
	 */
	private Date optTime;

	/**
	 * 销售电话
	 */
	private String salesTel;
	
	/**
	 * 折扣
	 */
	private BigDecimal discount;
	/**
	 * 备注
	 */
	private String remark;

	public Invoice() {
	}
	
	

	public String getInvoiceCode() {
		return invoiceCode;
	}



	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}



	public String getCarNum() {
		return this.carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
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

	public Date getDeliveryDatetime() {
		return this.deliveryDatetime;
	}

	public void setDeliveryDatetime(Date deliveryDatetime) {
		this.deliveryDatetime = deliveryDatetime;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFreight() {
		return this.freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public Date getInvoiceDatetime() {
		return this.invoiceDatetime;
	}

	public void setInvoiceDatetime(Date invoiceDatetime) {
		this.invoiceDatetime = invoiceDatetime;
	}

	public String getInvoicePerson() {
		return this.invoicePerson;
	}

	public void setInvoicePerson(String invoicePerson) {
		this.invoicePerson = invoicePerson;
	}

	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getSalesTel() {
		return this.salesTel;
	}

	public void setSalesTel(String salesTel) {
		this.salesTel = salesTel;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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