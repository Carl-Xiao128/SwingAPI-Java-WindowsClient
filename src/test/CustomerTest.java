package test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import entity.Customer;

import util.SalesUtils;

public class CustomerTest {
	public static void main(String[] args) {
		SqlSession session = null;
		try {
			session = SalesUtils.getSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Customer c = new Customer();
		Customer c1 = new Customer();
		c1.setAddress("beilanqiao");
		c1.setArea("shanghai");
		c1.setCname("ycy");
		//c1.setOptTime(new Date());
		c.setCname("ycy");
		c1.setSid(1213);
		String statement = "getCustomerList";//映射sql的标识字符串
        String s = "insertCustomer";
        List<Customer> cList = session.selectList(statement,c);
        session.insert(s,c1);
        session.commit();
        for(Customer a : cList){
        	System.out.println(a.toString());
        }
	}
}
