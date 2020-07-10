package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.SalesUtils;
import entity.CustomerProRel;
import entity.CustomerProRelRecord;
import entity.vo.CustomerProRelVo;

public class CustomerProductRelService {

	/**
	 * 插入客户产品关系
	 * 
	 * @param customerProRel
	 * @return
	 */
	public int insertCusProRel(CustomerProRel customerProRel) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "insertCusProRel";
			flag = session.insert(statement, customerProRel);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	/**
	 * 插入客户产品关系同时插入记录表
	 * 
	 * @param customerProRel customerProRelRecord
	 * @return
	 */
	public int insertCusProRelAndRecord(CustomerProRel customerProRel, CustomerProRelRecord customerProRelRecord) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement1 = "insertCusProRel";
			int flag1 = session.insert(statement1, customerProRel);
			String statement2 = "insertCusProRelRecord";
			int flag2 = session.insert(statement2, customerProRelRecord);
			if(flag1==1&&flag2==1){
				flag=1;
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	/**
	 * 跟新客户产品关系
	 * 
	 * @param customerProRel
	 * @return
	 */
	public int updateCusProRel(CustomerProRel customerProRel) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "updateCusProRel";
			flag = session.update(statement, customerProRel);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}
	/**
	 * 跟新客户产品关系and插入记录表
	 * 
	 * @param customerProRel
	 * @param customerProRelRecord
	 * @return
	 */
	public int updateCusProRelAndInsertRecoed(CustomerProRel customerProRel,CustomerProRelRecord customerProRelRecord) {
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "updateCusProRel";
			int flag1 = session.update(statement, customerProRel);
			String statement2 = "insertCusProRelRecord";
			int flag2 = session.insert(statement2, customerProRelRecord);
			if(flag1==1&&flag2==1){
				flag=1;
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	/**
	 * 查询单个客户产品关系
	 * 
	 * @param customerProRelVo
	 * @return
	 */
	public CustomerProRelVo selectCusProRelOne(int sid) {
		CustomerProRelVo CusP = new CustomerProRelVo();
		SqlSession session = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "selectCusProRelOne";
			CusP = session.selectOne(statement, sid);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return CusP;
	}

	/**
	 * 根据客户sid和产品sid查询价格
	 * 
	 * @param customerProRelVo
	 * @return
	 */
	public BigDecimal selectPriceByCSidAndPSid(CustomerProRelVo customerProRelVo) {
		BigDecimal price = null;
		SqlSession session = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "selectPriceByCSidAndPSid";
			price = session.selectOne(statement, customerProRelVo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return price;
	}

	/**
	 * 查询客户关系列表
	 * 
	 * @param customerProRelVo
	 * @return
	 */
	public List<CustomerProRelVo> selectCusProRelList(CustomerProRelVo customerProRelVo) {
		List<CustomerProRelVo> list = null;
		SqlSession session = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String statement = "selectCusProRelList";
			list = session.selectList(statement, customerProRelVo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
