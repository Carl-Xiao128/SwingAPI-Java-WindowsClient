package web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import dao.InvoiceProductRelService;
import dao.InvoiceService;
import entity.Invoice;
import entity.InvoiceProCus;
import entity.SearchParam;

/**
 *
 * @描述：开票控制层
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:23:42
 */
public class InvoiceCtr {
    
    /**
     * @描述：插入
     * @创建人：肖龙祥
     * @创建时间：2016年10月1日 下午4:19:34
     * @param data
     * @param invoiceCode
     * @param customerSid
     * @param deliveryDatetime
     * @param invoiceDatetime
     * @param invoicePerson
     * @param salesTel
     * @param fax
     * @param carNum
     * @param licenseNumber
     * @param carrier
     * @param freight
     * @param moneyType
     * @param money
     * @return
     */
    @SuppressWarnings("rawtypes")
	public int  insertInvoicePro(
    		Vector data,String invoiceCode,String customerSid,String deliveryDatetime,String invoiceDatetime,
    		String invoicePerson,String salesTel,String fax,String carNum,String licenseNumber,
    		String carrier,String freight,String moneyType,String money,String discount,String remark){
    	InvoiceProductRelService invoiceProductRelService =new InvoiceProductRelService();
    	SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
    	Invoice invoice= new Invoice();
    	invoice.setInvoiceCode(invoiceCode);
    	invoice.setCustomerSid(customerSid);
    	try {
			invoice.setDeliveryDatetime(sp.parse(deliveryDatetime));
			invoice.setInvoiceDatetime(sp.parse(invoiceDatetime));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
    	invoice.setInvoicePerson(invoicePerson);
    	invoice.setSalesTel(salesTel);
    	invoice.setFax(fax);
    	invoice.setCarNum(carNum);
    	invoice.setLicenseNumber(licenseNumber);
    	invoice.setCarrier(carrier);
    	invoice.setFreight(freight);
    	invoice.setMoneyType(moneyType);
    	invoice.setMoney(new BigDecimal(money));
    	invoice.setRemark(remark);
    	invoice.setDiscount(new BigDecimal(discount));
		return invoiceProductRelService.insertInvoiceProductRel(data,invoice);
    }
    
    /**
     * @描述：根据条件查询当月统计分析数据
     * @创建人：肖龙祥
     * @创建时间：2016年10月1日 下午4:19:24
     * @param searchParam
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector selectInvoiceProCus(SearchParam searchParam){
    	InvoiceService invoiceService =new InvoiceService();
    	SimpleDateFormat sp=new SimpleDateFormat("yyyy年MM月dd日");
    	Calendar   ca   =   Calendar.getInstance();
    	ca.setTime(new Date());
    	ca.set(Calendar.DAY_OF_MONTH,1);
    	//第一天
    	Date   firstDate   =   ca.getTime();
    	ca.add(Calendar.MONTH,   1);
    	ca.add(Calendar.DAY_OF_MONTH,   -1);
    	//最后一天
    	Date   lastDate   =   ca.getTime();
    	searchParam.setStartTime(firstDate);
    	searchParam.setEndTime(lastDate);
    	List<InvoiceProCus> list=invoiceService.selectInvoiceProCus(searchParam);
    	Vector data = new Vector();

        for (int i = 0; i < list.size(); i++) {
            InvoiceProCus a = list.get(i);
            Vector rowData = new Vector();
            rowData.add(a.getInvoiceCode());
            rowData.add(sp.format(a.getDeliveryTime()));
            rowData.add(a.getCname());
            rowData.add(a.getArea());
            rowData.add(a.getPtype());
            rowData.add(a.getCode());
            rowData.add(a.getPname());
            rowData.add(a.getStandard()+" "+a.getUnit()+"/"+a.getStandardUnit());
            rowData.add(a.getProductNum().divide(a.getStandard()));
            rowData.add(a.getProductNum());
            rowData.add(a.getUnitPrice()+" 元/"+a.getUnit());
            rowData.add(a.getTotalMoney());
        	rowData.add(a.getMoneyType());
            rowData.add(a.getAddMoney());
            rowData.add(a.getCreditMoney());
            rowData.add(a.getDiscount());
            rowData.add(a.getPerson());
            rowData.add(a.getAreaCharge());
            rowData.add(a.getCarrier());
        	rowData.add(a.getRemark());
            data.add(rowData);
        }
    	return data;
    }
    /**
     * @描述：获取统计分析List列表
     * @创建人：肖龙祥
     * @创建时间：2016年10月18日 上午9:21:27
     * @param searchParam
     * @return
     */
    public List<InvoiceProCus> selectInvoiceProCusList(SearchParam searchParam){
    	InvoiceService invoiceService =new InvoiceService();
    	Calendar   ca   =   Calendar.getInstance();
    	ca.setTime(new Date());
    	ca.set(Calendar.DAY_OF_MONTH,1);
    	//第一天
    	Date   firstDate   =   ca.getTime();
    	ca.add(Calendar.MONTH,   1);
    	ca.add(Calendar.DAY_OF_MONTH,   -1);
    	//最后一天
    	Date   lastDate   =   ca.getTime();
    	searchParam.setStartTime(firstDate);
    	searchParam.setEndTime(lastDate);
    	List<InvoiceProCus> list=invoiceService.selectInvoiceProCus(searchParam);
    	return list;
    }
    
    /**
     * @描述：删除统计分析数据
     * @创建人：肖龙祥
     * @创建时间：2016年10月1日 上午10:35:17
     * @param invoiceCode
     * @return
     */
    public int deleteInvProByInvoiceCode(String invoiceCode){
    	InvoiceService invoiceService =new InvoiceService();
    	return invoiceService.deleteInvProByInvoiceCode(invoiceCode);
    }
    
    /**
     * @描述：更新invoice
     * @创建人：肖龙祥
     * @创建时间：2016年10月1日 下午12:43:00
     * @param invoice
     * @return
     */
    public int updateInvoice(Invoice invoice){
    	InvoiceService invoiceService =new InvoiceService();
		return invoiceService.updateInvoice(invoice);
    }
}
