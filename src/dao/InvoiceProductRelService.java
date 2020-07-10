package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;

import util.SalesUtils;
import entity.Invoice;
import entity.InvoiceProductRel;
import entity.vo.CustomerProRelVo;
import entity.vo.InvoiceProductVo;

public class InvoiceProductRelService {
	/**
	 * 插入流水单对应关系
	 * @param invoiceProductRel
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int insertInvoiceProductRel(Vector data,Invoice invoice){
		SqlSession session = null;
		int flag2 = 0;
		int flag1 = 0;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement1 = "insertInvoice";
			flag1 = session.insert(statement1,invoice);
			String statement = "insertInvoiceProductRel";
			InvoiceProductRel invoiceProductRel =new InvoiceProductRel();
			Vector everyData=null;
			for(int i=0;i<data.size();i++){
				everyData=(Vector)data.get(i);
				invoiceProductRel.setInvoiceSid(invoice.getInvoiceCode());
				invoiceProductRel.setProductSid(everyData.get(7).toString());
				invoiceProductRel.setProductNum(everyData.get(3).toString());
				flag2 = session.insert(statement,invoiceProductRel);
			}
			if(flag1==1&&flag2==1){
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
	 * 查询流水单列表
	 * @param InvoiceProductVo
	 * @return
	 */
	public List<InvoiceProductVo> selectInvoiceList(InvoiceProductVo InvoiceProductVo){
		SqlSession session = null;
		List<InvoiceProductVo> list = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "selectInvoiceList";
			list = session.selectList(statement,InvoiceProductVo);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		CustomerProductRelService service = new CustomerProductRelService();
		if(list != null && list.size()>0){			
			for(InvoiceProductVo i : list){
				CustomerProRelVo c = new CustomerProRelVo();
				c.setCustomerSid(i.getCustomerSid());
				c.setProductSid(i.getProductSid());
				BigDecimal price = service.selectPriceByCSidAndPSid(c);
				i.setUnitPrice(price);
			}
		}
		return list;
	}
}
