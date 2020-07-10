package util;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import entity.InvoiceProCus;
import entity.SearchParam;
import web.InvoiceCtr;

/**
 *
 * @描述：swing界面工具类
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:22:52
 */
public class SwingUtil {

	/**
	 *
	 * @描述：String不为空校验
	 * @创建人：kitxiao
	 * @创建时间：2016年8月26日下午11:08:48
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static Properties hasProperties(){
		Properties prop = new Properties();   
		File a =new File("print.properties");
		try {   
			InputStream in = new FileInputStream(a);
            prop.load(in);   
        } catch (IOException e) {   
            e.printStackTrace();   
        }
		return prop;   
	}

	/**
	 *
	 * @描述：String为空校验
	 * @创建人：kitxiao
	 * @创建时间：2016年8月26日下午11:09:43
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @描述：以空格为分隔符截取第一个字符串并转为bigDecimal
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 上午10:19:50
	 * @param str
	 * @return
	 */
	public static BigDecimal hasIntFromStr(String str,String regex){
        String strAry[] = str.split(regex);
        BigDecimal big =strTurnBig(strAry[0]);
		return big;
	}
	
	/**
	 * @描述：以“-”分隔，截取第二个字符串
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午3:28:23
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String hasStrFromStr(String str,String regex){
		String strAry[] = str.split(regex);
		String big =strAry[1];
		return big;
	}


	/**
	 *
	 * @描述：String 转Bigdecimal
	 * @创建人：kitxiao
	 * @创建时间：2016年8月27日上午12:01:39
	 * @param str
	 * @return
	 */
	public static BigDecimal strTurnBig(String str) {
		BigDecimal big = new BigDecimal(str);
		return big;
	}
	
	/**
     * @描述：inputstream转file
     * @创建人：肖龙祥
     * @创建时间：2016年10月8日 下午2:30:49
     * @param ins
     * @param file
     * @throws IOException 
     */
    public static void inputstreamtofile(InputStream ins,File file) throws IOException {  
            OutputStream os = new FileOutputStream(file);  
            int bytesRead = 0;  
            byte[] buffer = new byte[8192];  
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {  
                os.write(buffer, 0, bytesRead);  
            }  
            os.close();  
            ins.close();  
    }  
	
	/**
	 * @描述：创建文件
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月8日 下午2:28:08
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(File file) throws IOException {  
        if(! file.exists()) {  
            makeDir(file.getParentFile());  
        }  
        return file.createNewFile();  
    }  
	/**
	 * @描述：创建文件夹
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月8日 下午2:28:17
	 * @param dir
	 */
	public static void makeDir(File dir) {  
	    if(! dir.getParentFile().exists()) {  
	        makeDir(dir.getParentFile());  
	    }  
	    dir.mkdir();  
	}  
	
	/**
	 *
	 * @描述：校验是否为价格类型
	 * @创建人：kitxiao
	 * @创建时间：2016年8月26日下午11:15:27
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile(
				"^(\\-|\\+)?\\d+(\\.\\d{0,3})?$");
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * @描述：校验是否为指定日期格式（yyyy-MM-dd）
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月27日 下午4:51:22
	 * @param str
	 * @return
	 */
	public static boolean isDateFormat(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile(
				"^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$");
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * @描述：手机号码校验
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月27日 下午5:25:37
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile(
				"^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * @描述：传真，电话号码验证
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月27日 下午5:25:58
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^((\\d{3,4}\\-)|)\\d{7,8}(|([-\\u8f6c]{1}\\d{1,5}))$");
		m = p1.matcher(str);
		b = m.matches();

		return b;
	}
	/**
	 * @描述：车牌号校验
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月29日 下午4:28:31
	 * @param str
	 * @return
	 */
	public static boolean isCarNum(String str) {
		Pattern p1 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
		m = p1.matcher(str);
		b = m.matches();
		
		return b;
	}
	/**
	 * @描述：身份证号，驾驶证号校验
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月29日 下午4:36:13
	 * @param str
	 * @return
	 */
	public static boolean isIdCardNum(String str) {
		Pattern p1 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
		m = p1.matcher(str);
		b = m.matches();
		
		return b;
	}

	/**
	 * @描述：获取图片
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:25:47
	 * @param name
	 * @return
	 */
	public ImageIcon produceImage(String name) {
		ImageIcon backImage = new ImageIcon(
				getClass().getClassLoader().getResource(name));
		return backImage;
	}

	/**
	 * @描述：列宽自适应
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:25:30
	 * @param myTable
	 */
	@SuppressWarnings("rawtypes")
	public static void FitTableColumns(JTable myTable) {
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();

		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel()
					.getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable,
								myTable.getValueAt(row, col), false, false, row,
								col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}

	/**
	 * 
	 * @描述：Dialog设为在JFrame中心
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:20:39
	 * @param dlg
	 * @param frame
	 */
	public static void dialogAdapterFrame(Dialog dlg, JFrame frame) {
		Dimension dialogSize = dlg.getPreferredSize();
		Dimension parentSize = frame.getSize();
		Point loc = frame.getLocation();
		dlg.setLocation((parentSize.width - dialogSize.width) / 2 + loc.x,
				(parentSize.height - dialogSize.height) / 2 + loc.y);
	}

	/**
	 * 
	 * @描述：为Dialog生成JLabel
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:20:39
	 * @param title
	 * @param Y
	 */
	public static JLabel createDialogLabel(String title, int Y) {
		JLabel label = new JLabel(title);
		label.setBounds(40, Y, 65, 40);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		return label;
	}

	/**
	 * 
	 * @描述：为Dialog生成JLabel
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:20:39
	 */
	public static JLabel createDialogLabel1(String title, int X, int Y, int W,
			int H, String font) {
		JLabel label = new JLabel(title);
		label.setBounds(X, Y, W, H);
		if ("0".equals(font)) {
			label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		} else if("1".equals(font)){
			label.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		} else if("2".equals(font)){
			label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		}
		return label;
	}

	/**
	 * 
	 * @描述：为JTextField添加焦点边框
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:20:39
	 * @param field
	 */
	public static void setTextFieldBorder(final JTextField field) {
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				field.setBorder(BorderFactory
						.createLineBorder(new Color(171, 173, 179)));
			}

			@Override
			public void focusGained(FocusEvent e) {
				field.setBorder(BorderFactory.createLineBorder(new Color(97, 164, 207)));
			}
		});
	}
	/**
	 * @描述：创建通用JTextfield
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月23日 上午11:35:25
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JTextField creatComTextField(int x,int y,int width,int height) {
		JTextField field =new JTextField();
		field.setBounds(x, y, width, height);
		setTextFieldBorder(field);
		return field;
	}

	/**
	 * 
	 * @描述：设置jtable最小列宽（自适应）
	 * @创建人：kitxiao
	 * @创建时间：2016年8月19日 下午6:20:39
	 * @param field
	 */
	public static void aotoColumnWidth(JTable table, JScrollPane scrollPane,
			Double[] columnWidth) {
		TableColumn column = null;
		for (int i = 0; i < columnWidth.length; i++) {
			column = table.getColumnModel().getColumn(i);
			column.setMinWidth((int) (columnWidth[i] * scrollPane.getWidth()));
		}
		SwingUtil.FitTableColumns(table);
	}

	/**
	 * @描述：设置Jtable某列隐藏
	 * @创建人：肖龙祥
	 * @创建时间：2016年8月24日 上午11:03:39
	 * @param columnModel
	 * @param arr
	 */
	public static void setColumnHidden(TableColumnModel columnModel,
			int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			TableColumn columnSid = columnModel.getColumn(arr[i]);
			columnSid.setMinWidth(0);
			columnSid.setMaxWidth(0);
			columnSid.setWidth(0);
			columnSid.setPreferredWidth(0);
		}
	}
	
	 /**
	 * @描述：导出excel路径选择弹出框
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月9日 下午12:47:36
	 * @param frame
	 * @return
	 */
	public static String selectSavePath(JFrame frame){  
	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月");  
	        String name = dateformat.format(new Date());  
	        name = "C:\\Users\\dsa\\Desktop\\"+"上海汇楠客户销售明细"+name + ".xls";  
	        //构造文件保存对话框  
	        JFileChooser chooser = new JFileChooser();  
	        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
	        chooser.setDialogType(JFileChooser.SAVE_DIALOG);  
	        chooser.setSelectedFile(new File(name));
	        chooser.setMultiSelectionEnabled(false);  
	        chooser.setAcceptAllFileFilterUsed(false);  
	        chooser.setDialogTitle("保存导出报表文件");  
	        chooser.addChoosableFileFilter(new FileFilter(){  
	            public boolean accept(File f) {  
	                if (f.getName().endsWith("xls") || f.getName().endsWith("xlsx") || f.isDirectory()) {  
	                     return true;   
	                }else{  
	                    return false;   
	                }  
	            }  
	            public String getDescription() {  
	                return "Excel文件(*.xls;*.xlsx)";  
	            }  
	        });  
	        //打开对话框  
	        int result = chooser.showSaveDialog(frame);//null  
	        //文件确定  
	        if(result==JFileChooser.APPROVE_OPTION) {  
	            String outPath = chooser.getSelectedFile().getAbsolutePath();  
	            if(new File(outPath).exists()){  
	            	Object[] options = {"确定","取消"};
					int response=JOptionPane.showOptionDialog(frame, "文件已经存在,是否要覆盖该文件?", "提示",JOptionPane.YES_OPTION, JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);
	                if(response == 1){  
	                    return null;  
	                }  
	            }  
	            return outPath;  
	        }  
	        return null;  
	    }  
	
	/**
	 * @描述：获取List中某个字段不重复元素
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月10日 上午9:43:13
	 * @param name
	 * @return
	 */
	public static List<String> findDistinctList(String name) {
		InvoiceCtr invoiceCtr =new InvoiceCtr();
		SearchParam searchParam =new SearchParam();
		List<InvoiceProCus> dataList = invoiceCtr.selectInvoiceProCusList(searchParam);
		List<String> tempList= new ArrayList<String>();  
	    for(InvoiceProCus i:dataList){  
	    	if("c".equals(name)){
	    		tempList.add(i.getCname());  
	    	}else if("p".equals(name)){
	    		tempList.add(i.getPname());  
	    	}else{
	    		tempList.add(i.getCode());  
	    	}
	    }  
	    List<String> list = new ArrayList<String>();
        for(int i=0; i<tempList.size(); i++){
            String str = tempList.get(i);  //获取传入集合对象的每一个元素
            if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
                list.add(str);
            }
        }
		return list;
	}
}
