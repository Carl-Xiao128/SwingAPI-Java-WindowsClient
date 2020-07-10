package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.SalesUtils;
import entity.Invoice;
import entity.InvoiceProCus;
import entity.SearchParam;

public class InvoiceService {
	
	/**
	 * 插入流水单信息
	 * @param invoice
	 * @return
	 */
	public int insertInvoice(Invoice invoice){
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "insertInvoice";
			flag = session.insert(statement,invoice);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	/**
	 * @描述：统计分析列表查询
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月1日 下午3:02:05
	 * @param searchParam
	 * @return
	 */
	public List<InvoiceProCus> selectInvoiceProCus(SearchParam searchParam){
		SqlSession session = null;
		List<InvoiceProCus> list = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "selectInvoiceProCus";
			list = session.selectList(statement,searchParam);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * @描述：删除统计分析数据
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月1日 上午10:31:13
	 * @param invoiceCode
	 * @return
	 */
	public int deleteInvProByInvoiceCode(String invoiceCode){
		SqlSession session = null;
		int flag = 0;
		int flag1 = 0;
		int flag2 = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "deleteInvByInvoiceCode";
			flag1 = session.delete(statement,invoiceCode);
			String statement1 = "deleteProByInvoiceCode";
			flag2 = session.delete(statement1,invoiceCode);
			if(flag1>0 && flag2>0){
				flag=1;
			}
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
		return flag;
	}
	/**
	 * @描述：更新invoice
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月1日 下午12:41:26
	 * @param invoice
	 * @return
	 */
	public int updateInvoice(Invoice invoice) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "updateInvoice";
			flag = session.update(statement, invoice);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
}
