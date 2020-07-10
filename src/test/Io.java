package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

public class Io {
	public static void main(String[] args) throws IOException {

		File file = new File("D:\\saleInvoice\\print.html");
		File fileLod = new File("D:\\saleInvoice\\LodopFuncs.js");
		
		//写入LodopFuncs.js到D盘
		try {
			createFile(fileLod);
			File a=new File("cache");
			InputStream is=Io.class.getClassLoader().getResourceAsStream("LodopFuncs.js"); 
			inputstreamtofile(is,a);
			InputStream in = new FileInputStream(new File(a.getPath()));
			OutputStream out = new FileOutputStream(fileLod);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//写入print.html到D盘
		OutputStreamWriter pw = null;// 定义一个流
		try {
			createFile(file);
			pw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			pw.write("x");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	  public static void inputstreamtofile(InputStream ins,File file) {  
	        try {  
	            OutputStream os = new FileOutputStream(file);  
	            int bytesRead = 0;  
	            byte[] buffer = new byte[8192];  
	            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {  
	                os.write(buffer, 0, bytesRead);  
	            }  
	            os.close();  
	            ins.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  public static boolean createFile(File file) throws IOException {  
	        if(! file.exists()) {  
	            makeDir(file.getParentFile());  
	        }  
	        return file.createNewFile();  
	    }  
	    public static void makeDir(File dir) {  
	        if(! dir.getParentFile().exists()) {  
	            makeDir(dir.getParentFile());  
	        }  
	        dir.mkdir();  
	    }  
}
