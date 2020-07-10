package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SalesUtils {
	public static SqlSession getSession() throws IOException{
		
		File a =new File("config.xml");
		InputStream in = new FileInputStream(a);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
   
        SqlSession session = sessionFactory.openSession();
		
        return session;
	}
}
