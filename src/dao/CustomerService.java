package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import entity.Customer;

import util.SalesUtils;

public class CustomerService {
	
	/**
	 * 插入客户信息
	 * @param customer
	 * @return
	 */
	public int insertCustomer(Customer customer){
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "insertCustomer";
			flag = session.insert(statement,customer);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	
	
	/**
	 * 查询客户列表
	 * @param customer
	 * @return
	 */
	public List<Customer> getCustomerList(Customer customer){
		SqlSession session = null;
		List<Customer> list = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "getCustomerList";
			list = session.selectList(statement,customer);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * 查询单个客户信息
	 * @param customer
	 * @return
	 */
	public Customer getCustomerOne(Customer customer){
		SqlSession session = null;
		Customer cus = new Customer();
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "getCustomerOne";
			cus = session.selectOne(statement,customer);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return cus;
		
	}
	
	/**
	 * 更新客户信息
	 * @param customer
	 * @return
	 */
	public int updateCustomer(Customer customer){
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "updateCustomer";
			flag = session.update(statement,customer);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
}
