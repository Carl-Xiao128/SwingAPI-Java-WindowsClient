package test;

import java.util.ArrayList;
import java.util.List;

import entity.InvoiceProCus;
import entity.SearchParam;
import web.InvoiceCtr;

public class dg {
	public static void main(String[] args) {
		InvoiceCtr invoiceCtr =new InvoiceCtr();
		SearchParam searchParam =new SearchParam();
		List<InvoiceProCus> dataList = invoiceCtr.selectInvoiceProCusList(searchParam);
		List<String> tempList= new ArrayList<String>();  
	    for(InvoiceProCus i:dataList){  
            tempList.add(i.getCname());  
	    }  
	    List<String> list = new ArrayList<String>();
        for(int i=0; i<tempList.size(); i++){
            String str = tempList.get(i);  //获取传入集合对象的每一个元素
            if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
                list.add(str);
            }
        }
        System.out.println(list);
	}
}
