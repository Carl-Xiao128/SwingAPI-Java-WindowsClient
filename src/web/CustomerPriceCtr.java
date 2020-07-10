package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

import dao.CustomerProRelRecordService;
import dao.CustomerProductRelService;
import dao.CustomerService;
import dao.ProductService;
import entity.Customer;
import entity.CustomerProRel;
import entity.CustomerProRelRecord;
import entity.Product;
import entity.vo.CustomerProRelVo;

/**
 *
 * @描述：客户产品关系表控制层
 * @创建人：kitxiao
 * @创建时间：2016年8月24日上午12:23:42
 */
public class CustomerPriceCtr {

	/**
	 * 查询客户产品关系表列表
	 * @param customerProRelVo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector selectCustomerPriceList(CustomerProRelVo customerProRelVo) {
		CustomerProductRelService customerProductRelService = new CustomerProductRelService();
		List<CustomerProRelVo> cPRList = customerProductRelService
				.selectCusProRelList(customerProRelVo);
		Vector data = new Vector();

		for (int i = 0; i < cPRList.size(); i++) {
			CustomerProRelVo a = cPRList.get(i);
			Vector rowData = new Vector();
			rowData.add(a.getCode());
			rowData.add(a.getPname());
			rowData.add(a.getCname());
			rowData.add(a.getUnitPrice());
			rowData.add(a.getUnit());
			rowData.add(a.getStandard() + " " + a.getUnit() + "/"
					+ a.getStandardUnit());
			rowData.add(new JButton());
			rowData.add(new JButton());
			rowData.add(a.getSid());
			rowData.add(a.getProductSid());
			rowData.add(a.getCustomerSid());
			data.add(rowData);
		}
		return data;
	}

	/**
	 * @描述：根据ProductSid和CustomerSid,以及开始结束时间返回历史单价信息
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月24日 下午2:53:31
	 * @param customerProRelVo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector selectHistoryPriceList(String productSid,String customerSid,Date startDate,Date endDate) {

		CustomerProRelRecordService customerProRelRecordService = new CustomerProRelRecordService();
		CustomerProRelVo customerProRelVo=new CustomerProRelVo();
		customerProRelVo.setProductSid(productSid);
		customerProRelVo.setCustomerSid(customerSid);
		customerProRelVo.setStartTime(startDate);
		customerProRelVo.setEndTime(endDate);
		List<CustomerProRelVo> cPRList = customerProRelRecordService
				.seletCusProRelRecord(customerProRelVo);
		Vector data = new Vector();
		// table信息
		for (int i = 0; i < cPRList.size(); i++) {
			CustomerProRelVo a = cPRList.get(i);
			Vector rowData = new Vector();
			SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			rowData.add(sp.format(a.getOptTime()));
			rowData.add(a.getUnitPrice());
			data.add(rowData);
		}
		return data;

	}
	/**
	 * @描述：根据ProductSid和CustomerSid返回历史单价  头信息
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月24日 下午2:53:31
	 * @param customerProRelVo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector selectHistoryPriceList(String productSid,String customerSid) {
		
		CustomerProRelRecordService customerProRelRecordService = new CustomerProRelRecordService();
		CustomerProRelVo customerProRelVo=new CustomerProRelVo();
		customerProRelVo.setProductSid(productSid);
		customerProRelVo.setCustomerSid(customerSid);
		List<CustomerProRelVo> cPRList = customerProRelRecordService
				.seletCusProRelRecord(customerProRelVo);
		Vector data = new Vector();
		// 头信息
		String cName = "";
		String pName = "";
		String code = "";
		if(cPRList!=null&&cPRList.size()>0){
			cName = cPRList.get(0).getCode();
			pName = cPRList.get(0).getCname();
			code = cPRList.get(0).getPname();
		}
		data.add(cName);
		data.add(pName);
		data.add(code);
		return data;
		
	}
	
	/**
     * 查询客户表信息for客户comboBox
     * @param customer
     * @return
     */
	public List<Customer> selectCustomerListForCB(){
        CustomerService customerService =new CustomerService();
        Customer customer =new Customer();
        List<Customer> cList= customerService.getCustomerList(customer);
    return cList;
    }
    
    /**
     * 查询产品表信息for产品comboBox
     * @param product
     * @return
     */
	public List<Product> selectProductListForCB(){
    	ProductService productService =new ProductService();
    	Product product=new Product();
        List<Product> pList= productService.selectProductList(product);
    return pList;
    }
	
	/**
	 *
	 * @描述：新增客户-产品价格时，根据ProductSid和CustomerSid查询是否已存在数据
	 * @创建人：kitxiao
	 * @创建时间：2016年8月26日下午10:48:11
	 * @param productSid
	 * @param customerSid
	 * @return
	 */
	public int selectCountCusProByPCid(String productSid,String customerSid){
		CustomerProductRelService customerProductRelService = new CustomerProductRelService();
		CustomerProRelVo customerProRelVo=new CustomerProRelVo();
		customerProRelVo.setProductSid(productSid);
		customerProRelVo.setCustomerSid(customerSid);
		List<CustomerProRelVo> cPRList = customerProductRelService.selectCusProRelList(customerProRelVo);
		if(cPRList!=null&&cPRList.size()>0){
			return 1;
		}
		return 0;
	}
	
	/**
	 * @描述：新增客户产品关系同时插入记录表
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月27日 上午9:28:11
	 * @param productSid
	 * @param customerSid
	 * @param unitPrice
	 * @return
	 */
	public int insertCusProRelAndRecord(String productSid,String customerSid,BigDecimal unitPrice){
		CustomerProductRelService customerProductRelService = new CustomerProductRelService();
		CustomerProRel customerProRel=new CustomerProRel();
		CustomerProRelRecord customerProRelRecord =new CustomerProRelRecord();
		customerProRel.setProductSid(productSid);
		customerProRel.setCustomerSid(customerSid);
		customerProRel.setUnitPrice(unitPrice);
		customerProRel.setIfDelete("0");
		customerProRelRecord.setProductSid(productSid);
		customerProRelRecord.setCustomerSid(customerSid);
		customerProRelRecord.setUnitPrice(unitPrice);
		int i=customerProductRelService.insertCusProRelAndRecord(customerProRel, customerProRelRecord);
		return i;
	}

	/**
	 * @描述：根据sid查询一条产品关系记录
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月27日 上午9:33:36
	 * @param sid
	 * @return
	 */
	public CustomerProRelVo selectCusProRelOne(int sid){
		CustomerProductRelService customerProductRelService = new CustomerProductRelService();
		CustomerProRelVo customerProRelVo=customerProductRelService.selectCusProRelOne(sid);
		return customerProRelVo;
	}
	
	/**
	 * @描述：编辑客户产品价格时，编辑CusProRel价同时，插入记录表一条记录
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月27日 上午11:41:23
	 * @param sid
	 * @param productSid
	 * @param customerSid
	 * @param unitPrice
	 * @return
	 */
	public int updateCusProRelAndInsertRecord(int sid,String productSid,String customerSid,BigDecimal unitPrice){
		CustomerProductRelService customerProductRelService = new CustomerProductRelService();
		CustomerProRel customerProRel=new CustomerProRel();
		customerProRel.setSid(sid);
		customerProRel.setUnitPrice(unitPrice);
		CustomerProRelRecord customerProRelRecord =new CustomerProRelRecord();
		customerProRelRecord.setProductSid(productSid);
		customerProRelRecord.setCustomerSid(customerSid);
		customerProRelRecord.setUnitPrice(unitPrice);
		int i=customerProductRelService.updateCusProRelAndInsertRecoed(customerProRel, customerProRelRecord);
		return i;
	}
	
	/**
	 * @描述：根据psid和csid查询该条关系的现在价格
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月27日 下午3:46:43
	 * @param productSid
	 * @param customerSid
	 * @return
	 */
	public BigDecimal selectPriceByCSidAndPSid(String productSid,String customerSid){
		CustomerProRelVo customerProRelVo=new CustomerProRelVo();
		CustomerProductRelService customerProductRelService = new CustomerProductRelService();
		customerProRelVo.setCustomerSid(customerSid);
		customerProRelVo.setProductSid(productSid);
		return customerProductRelService.selectPriceByCSidAndPSid(customerProRelVo);
	}
	

}
