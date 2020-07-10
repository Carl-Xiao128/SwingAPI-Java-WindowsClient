package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		Calendar   ca   =   Calendar.getInstance();
    	ca.setTime(new Date());                            //  someDate 为你要获取的那个月的时间
    	ca.set(Calendar.DAY_OF_MONTH,1);
    	//第一天
    	Date   firstDate   =   ca.getTime();
    	ca.add(Calendar.MONTH,   1);
    	ca.add(Calendar.DAY_OF_MONTH,   -1);
    	//最后一天
    	Date   lastDate   =   ca.getTime();
    	SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
    	System.out.println(simpleFormate.format(firstDate)+":::::::::"+simpleFormate.format(lastDate));
	}
}
