package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.SalesUtils;
import entity.CustomerProRelRecord;
import entity.vo.CustomerProRelVo;

public class CustomerProRelRecordService {
	/**
	 * 保存客户产品关系记录表
	 * @param customerProRelRecord
	 * @return
	 */
	public int insertCusProRelRecord(CustomerProRelRecord customerProRelRecord){
		SqlSession session = null;
		int flag = 0;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "insertCusProRelRecord";
			flag = session.insert(statement,customerProRelRecord);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	
	/**
	 * 查询历史记录
	 * @param customerProRelVo
	 * @return
	 */
	public List<CustomerProRelVo> seletCusProRelRecord(CustomerProRelVo customerProRelVo){
		List<CustomerProRelVo> list = null;
		SqlSession session = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{
			String statement = "seletCusProRelRecord";
			list = session.selectList(statement,customerProRelVo);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
}
