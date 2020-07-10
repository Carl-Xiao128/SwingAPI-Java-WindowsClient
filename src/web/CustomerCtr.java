package web;

import java.util.List;
import java.util.Vector;

import javax.swing.JButton;


import dao.CustomerService;
import entity.Customer;


/**
 *
 * @描述：客户表控制层
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:23:42
 */
public class CustomerCtr {
	
    /**
     * 查询客户表信息
     * @param customer
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector selectCustomerList(Customer customer){
        CustomerService customerService =new CustomerService();
        List<Customer> cList= customerService.getCustomerList(customer);
        Vector data = new Vector();

        for (int i = 0; i < cList.size(); i++) {
            Customer a = cList.get(i);
            Vector rowData = new Vector();
            rowData.add(a.getSid());
            rowData.add(a.getCname());
            rowData.add(a.getPhone());
            rowData.add(a.getArea());
            rowData.add(a.getAddress());
            rowData.add(new JButton());
            rowData.add(new JButton());
            data.add(rowData);
        }
    return data;
    }
    /**
     * 删除一条记录，更新ifDelete字段为1
     * @param ifDelete
     * @param sid
     * @return
     */
    public int  updateCustomerBySid(String ifDelete,int sid){
    	CustomerService customerService=new CustomerService();
		Customer customer=new Customer();
		customer.setIfDelete(ifDelete);
		customer.setSid(sid);
		return customerService.updateCustomer(customer);
    }
    /**
     * 根据sid更新一条客户记录
     * @param cName
     * @param phone
     * @param person
     * @param area
     * @param address
     * @param sid
     * @return
     */
    public int  updateOneCustomerBySid(String cName,String phone,String person,String areaCharge,String area,String address,int sid){
    	CustomerService customerService=new CustomerService();
    	Customer customer=new Customer();
    	customer.setSid(sid);
    	customer.setCname(cName);
		customer.setPhone(phone);
		customer.setPerson(person);
		customer.setAreaCharge(areaCharge);
		customer.setArea(area);
		customer.setAddress(address);
    	return customerService.updateCustomer(customer);
    }
    /**
     * 插入一条客户记录
     * @param cName
     * @param phone
     * @param person
     * @param area
     * @param address
     * @return
     */
    public int  insertCustomer(String cName,String phone,String person,String areaCharge,String area,String address){
    	CustomerService customerService=new CustomerService();
    	Customer customer=new Customer();
    	customer.setCname(cName);
    	customer.setPhone(phone);
    	customer.setPerson(person);
    	customer.setAreaCharge(areaCharge);
    	customer.setArea(area);
    	customer.setAddress(address);
    	customer.setIfDelete("0");
    	return customerService.insertCustomer(customer);
    }
}
