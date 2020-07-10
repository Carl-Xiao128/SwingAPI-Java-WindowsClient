package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.SalesUtils;
import entity.Customer;
import entity.Product;
import entity.vo.ProductVo;

public class ProductService {
	/**
	 * 插入产品信息
	 * @param customer
	 * @return
	 */
	public int insertProduct(Product poduct){
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "insertProduct";
			flag = session.insert(statement,poduct);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	
	
	/**
	 * 查询产品列表
	 * @param poduct
	 * @return
	 */
	public List<Product> selectProductList(Product poduct){
		SqlSession session = null;
		List<Product> list = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "selectProductList";
			list = session.selectList(statement,poduct);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * 查询单个产品信息
	 * @param customer
	 * @return
	 */
	public Customer selectProductOne(Product poduct){
		SqlSession session = null;
		Customer cus = new Customer();
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "selectProductOne";
			cus = session.selectOne(statement,poduct);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return cus;
		
	}
	
	/**
	 * 更新产品信息
	 * @param customer
	 * @return
	 */
	public int updateProduct(Product poduct){
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "updateProduct";
			flag = session.update(statement,poduct);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	
	/**
	 * 根据客户sid查询产品列表包括价格
	 * @param sid
	 * @return
	 */
	public List<ProductVo> selectProductVoList(String sid){
		SqlSession session = null;
		List<ProductVo> list = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "selectProductVoList";
			list = session.selectList(statement,sid);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
}
