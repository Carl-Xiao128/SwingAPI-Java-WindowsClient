package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import entity.Customer;
import entity.Invoice;
import entity.Product;
import entity.SearchParam;
import entity.vo.CustomerProRelVo;
import util.JTableToExcel;
import util.NumberToCN;
import util.SwingUtil;
import web.CustomerCtr;
import web.CustomerPriceCtr;
import web.InvoiceCtr;
import web.ProductCtr;

/**
 *
 * @描述：销售系统主界面入口
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:36:00
 */
public class MainInterface extends JFrame {
	private static final long serialVersionUID = 5019557030260918280L;
	//流水单号固定前缀
	private static final String COM_SUBNAME ="SHHN";
	private JFrame frame;
	private SwingUtil swingUtil = new SwingUtil();
	private CustomerPriceCtr CustomerPriceCtr;
	private InvoiceCtr invoiceCtr;
	//主界面panel
	private MainPanel content;
	private Point point = new Point();
	//主界面tab选项panel
	private JPanel panel0;
	private JPanel panel1;
	private JPanel panel2;
	private JPopupMenu menu;
	private JMenuItem item;
	private JPopupMenu menu1;
	private JMenu item1;
	private JMenu item2;
	private JMenu item3;
	//文本框
	private JTextField customerTextField;
	private JTextField productTextField;
	private JTextField analysisTextField;
	private JTextField customerPriceTextField;
	private JTextField lsdhTextField;
	private JTextField fhrqTextField;
	private JTextField kprqTextField;
	private JTextField kprTextField;
	private JTextField xsdhTextField;
	private JTextField bzTextField;
	private JTextField czTextField;
	private JTextField cphTextField;
	private JTextField jszhTextField;
	private JTextField cyrTextField;
	private JTextField yfmxTextField;
	private JTextField qzmTextField;
	private JTextField fieldqklx;
	private JTextField fieldCSid;
	//tabPanel
	private JTabbedPane tabbedPane1;
	private JTabbedPane tabbedPane2;
	//JScrollPane
	private JScrollPane customerScrollPane;
	private JScrollPane productScrollPane;
	private JScrollPane invoiceProScrollPane;
	private JScrollPane invoiceScrollPane;
	private JScrollPane invoicePro1ScrollPane;
	private JScrollPane customerPriceScrollPane;
	private JScrollPane sScrollPane;
	private JScrollPane pScrollPane;
	//Jtable
	private JTable customerTable;
	private JTable productTable;
	private JTable customerPriceTable;
	private JTable invoiceProTable;
	private JTable invoiceTable;
	private JTable invoicePro1Table;
	//JButton
	private JButton btnNewButton;
	private JButton btnNewButton1;
	private JButton btnNewButton2;
	private JButton btnNewButton3;
	private JButton btnNewButton4;
	private JButton btnNewButton5;
	private JButton btnNewButton6;
	private JButton btnNewButton7;
	private JButton btnNewButton8;
	private JButton btnNewInvButton;
	private JButton invReloadButton;
	private JButton invConfirmButton;
	private JButton analysisButton;
	private JButton btnExcel;
	//
	private JLabel label1;
	private JLabel label2;
	private JSeparator separator;
	//自定义下拉框以及数据
	private List<Customer> cDate;
	private List<Product> pDate;
	private DIYComboBox<String> comboBox;
	private DIYComboBox<String> comboBox1;
	//开票新增记录集合
	@SuppressWarnings("rawtypes")
	private Vector dataVector;
	//所有记录小计的总额
	private BigDecimal totalProPrice;
	private int standardTotalNum;
	private int leftCol;
	//折扣减免
	private BigDecimal zkMoney = new BigDecimal(0);;
	//开票新增产品记录开关
	private Integer conFlag = 0;
	//分页页数初始值
	private Integer page = 1;
	//分页按钮以及当前页lable
	private JLabel beforePage;
	private JLabel afterPage;
	private JLabel labelaf;
	private JLabel labelbf;
	private JLabel labelys;
	private JLabel labelkxlx;
	//款项类型为请输入时
	private JLabel labelkqzm;
	//负责人以及地址label
	private JLabel labelfzr;
	private JLabel labeldz1;
	//分页是否可用开关
	private boolean afEnable = false;
	private boolean beEnable  = false;
	//分页总页数以及最后一页数据条数
	private int totalpage;
	private int leftNum;
	//动态添加进开票的组件
	private JTextField fieldPSid;
	private DIYComboBox<String> productCode;
	private DIYComboBox<String> comboBoxProName;
	private JTextField standard;
	private JTextField standardNum;
	private JTextField unit;
	private JTextField unitPrice;
	private JTextField totalPrice;
	
	//获取打印配置
	private Integer printDirection = 
			Integer.parseInt(SwingUtil.hasProperties().getProperty("printDirection").trim());
	private Integer printLeftRange = 
			Integer.parseInt(SwingUtil.hasProperties().getProperty("printLeftRange").trim());
	private Integer printPaperWidth = 
			Integer.parseInt(SwingUtil.hasProperties().getProperty("printPaperWidth").trim());
	private Integer printPaperHeight = 
			Integer.parseInt(SwingUtil.hasProperties().getProperty("printPaperHeight").trim());

	/**
	 * 主界面构造方法
	 */
	public MainInterface() {
		initGUI();
		initListener();
		this.frame=this;
	}

	/**
	 *
	 * @描述：初始化界面
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:05:15
	 */
	private void initGUI() {
		setSize(900, 700);
		setTitle("view");
		// 去边框
		setUndecorated(true);
		// 初始化自定义panel时需要将自己带过去，因为设置大小之类的操作需要用到
		// 加入到容器
		content = new MainPanel(this);
		// 设置窗口边界阴影
		getContentPane().add(content);
		Border border = BorderFactory.createCompoundBorder(
				ShadowBorder.newBuilder().shadowAlpha(1f).shadowSize(5).center().build(),
				BorderFactory.createLineBorder(Color.white));

		// 创建复合边框，将标题边框和凸起边框组合起来
		// 设置面板的边框
		content.setBorder(border);
		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 38, 890, 2);
		content.add(separator);

		// 创建左边显示tab页
		tabbedPane1 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		panel0 = createPanel("panel0");
		tabbedPane1.addTab("信息维护 ", swingUtil.produceImage("Maintenance.png"), panel0, "信息维护");
		panel1 = createPanel("panel1");
		sScrollPane= new JScrollPane(panel1);
		tabbedPane1.addTab("   开  票", swingUtil.produceImage("Invoice.png"), sScrollPane, "开票");
		panel2 = createPanel("panel2");
		pScrollPane= new JScrollPane(panel2);
		tabbedPane1.addTab("统计分析", swingUtil.produceImage("analysis.png"), pScrollPane, "统计分析");
		tabbedPane1.addChangeListener(new ChangeListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void stateChanged(ChangeEvent e) {
				int inedx =tabbedPane1.getSelectedIndex();
				//开票时刷新客户名称下拉框
				if(inedx ==1){
					frashKPCustomerCombox();
				}else if(inedx ==2){
					analysisTextField.setText("");
					analysisTextField.setOpaque(false);
					SearchParam searchParam =new SearchParam();
					invoiceCtr =new InvoiceCtr();
					Vector data = invoiceCtr.selectInvoiceProCus(searchParam);
					InvoiceJtablePaint invoiceJtablePaint = new InvoiceJtablePaint();
					invoiceTable = invoiceJtablePaint.invoiceJtablePaint(invoiceTable, data,frame,menu,menu1,item1,item2,item3);
					// 将组件以及数据模型加入scrollPane，重绘即可刷新
					invoiceScrollPane.setViewportView(invoiceTable);
					invoiceTable.getModel().addTableModelListener(new TableModelListener(){
						public void tableChanged(TableModelEvent e) {
							if(e.getType() == TableModelEvent.UPDATE && e.getColumn()==13 || e.getColumn()==14){
								String invoiceCode=invoiceTable.getValueAt(e.getLastRow(), 0).toString();
								Invoice invoice=new Invoice();
								if(e.getColumn()==13){
									String addMoney=invoiceTable.getValueAt(e.getLastRow(), e.getColumn()).toString();
									if("".equals(addMoney)){
										addMoney="0";
									}
									invoice.setAddMoney(new BigDecimal(addMoney));
								}else{
									String creditMoney=invoiceTable.getValueAt(e.getLastRow(), e.getColumn()).toString();
									if("".equals(creditMoney)){
										creditMoney="0";
									}
									invoice.setCreditMoney(new BigDecimal(creditMoney));
								}
								invoice.setInvoiceCode(invoiceCode);
								int i=invoiceCtr.updateInvoice(invoice);
								if(i<=0){
									JOptionPane.showMessageDialog(null, "更新失败！请稍后重试", "提示", JOptionPane.WARNING_MESSAGE);
								}else{
									tabbedPane1.setSelectedIndex(0);
									tabbedPane1.setSelectedIndex(2);
								}
							}
							
						}
					});
					invoiceScrollPane.validate();
					invoiceScrollPane.updateUI();
					invoiceScrollPane.repaint();
				}
			}
		});
		// 将3个tab页加入容器
		tabbedPane1.setBounds(2, 38, 892, 655);
		content.add(tabbedPane1);

		// 创建分隔条
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(new Color(255, 255, 255));
	}

	/**
	 *
	 * @描述：添加JPanel模型到每个tab页
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:04:11
	 * @param string
	 * @return
	 */
	private JPanel createPanel(final String string) {
		JPanel panel = new JPanel();
		// 设置布局
		panel.setLayout(new GridLayout(1, 1));
		// 创建一个label放到panel中
		JLabel filler = new JLabel(string);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.add(filler);

		if ("panel0".equals(string)) {
			addPanel0(panel, string);
		} else if ("panel3".equals(string)) {
			addPanel3(panel, string);
		} else if ("panel4".equals(string)) {
			addPanel4(panel, string);
		} else if ("panel5".equals(string)) {
			addPanel5(panel, string);
		} else if ("panel1".equals(string)) {
			addPanel1(panel, string);
		} else if ("panel2".equals(string)) {
			addPanel2(panel, string);
		}
		return panel;
	}
	

	/**
	 * @描述：统计分析JPanel模型
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月9日 下午4:26:15
	 * @param panel
	 * @param string
	 */
	@SuppressWarnings("rawtypes")
	private void addPanel2(final JPanel panel, final String string) {
		panel.setLayout(null);
		JTextField asd= new JTextField();
		panel.add(asd);
		// 创建analysisTextField模型
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto2.png"); 
			SwingUtil.inputstreamtofile(is,a);
			analysisTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		createTextField(analysisTextField,false);
		analysisTextField.setBounds(10, 20, 280, 30);
		panel.add(analysisTextField);
		
		final JButton btnNewButton = new JButton("搜索");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				String fieldLike = analysisTextField.getText();
				SearchParam searchParam =new SearchParam();
				if (fieldLike != null && "".equals(fieldLike)) {
					searchParam.setFieldLike(null);
				} else {
					searchParam.setFieldLike(fieldLike);
				}
				final Vector columnNames = new Vector();
				String [] arr = new String[]{"流水单号","发货日期","客户全称","区域","产品类别","产品代码","产品销售名称",
						"规格","件数","数量(kg)","单价","交易金额（元）","款项","追加（元）","账款（元）","折扣（元）","负责人","区域经理","承运人","备注"};
				for(int i=0;i<arr.length;i++){
					columnNames.add(arr[i]);
				}
				InvoiceCtr invoiceCtr =new InvoiceCtr();
				Vector data = invoiceCtr.selectInvoiceProCus(searchParam);
				((DefaultTableModel) invoiceTable.getModel()).setDataVector(data, columnNames);
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				// 设置鼠标进入变成小手
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNewButton.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				btnNewButton.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(swingUtil.produceImage("search.png"));
		btnNewButton.setBorder(new RoundBorder());
		btnNewButton.setBounds(300,20,120,30);
		
		panel.add(btnNewButton);
		analysisButton = new JButton("重置");
		analysisButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				analysisTextField.setText("");
				analysisTextField.setOpaque(false);
				tabbedPane1.setSelectedIndex(0);
				tabbedPane1.setSelectedIndex(2);
				
			}
		});
		analysisButton.setBackground(new Color(255, 255, 255));
		analysisButton.setIcon(swingUtil.produceImage("reload.png"));
		analysisButton.setBorder(new RoundBorder());
		analysisButton.setBounds(430,20,120,30);
		panel.add(analysisButton);
		
		SearchParam searchParam =new SearchParam();
		invoiceCtr =new InvoiceCtr();
		Vector data = invoiceCtr.selectInvoiceProCus(searchParam);
		InvoiceJtablePaint invoiceJtablePaint = new InvoiceJtablePaint();
		invoiceTable = invoiceJtablePaint.invoiceJtablePaint(invoiceTable, data,frame,menu,menu1,item1,item2,item3);
		invoiceScrollPane = new JScrollPane(invoiceTable);
		invoiceScrollPane.setBounds(10, 80, 760, 520);
		panel.add(invoiceScrollPane);
		
		//右键删除菜单选项
		menu = new JPopupMenu();
		item = new JMenuItem("删除该条流水单");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame, "删除后无法找回，确定删除本条数据？", "", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.YES_OPTION){
						String invoiceCode=invoiceTable.getValueAt(invoiceTable.getSelectedRow(), 0).toString();
						int i=invoiceCtr.deleteInvProByInvoiceCode(invoiceCode);
						if(i<=0){
							JOptionPane.showMessageDialog(null, "删除失败！请稍后重试", "提示", JOptionPane.WARNING_MESSAGE);
						}
						tabbedPane1.setSelectedIndex(0);
						tabbedPane1.setSelectedIndex(2);
					};
			}
		});
		menu.add(item);
		//右键筛选菜单
		menu1 = new JPopupMenu();
		item1 = new JMenu("按（客户名称）筛选");
		item2 = new JMenu("按（产品代码）筛选");
		item3 = new JMenu("按（产品名称）筛选");
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		
		btnExcel= new JButton("导出excel");
		btnExcel.setBackground(new Color(255, 255, 255));
		btnExcel.setIcon(swingUtil.produceImage("excel.png"));
		btnExcel.setBorder(new RoundBorder());
		btnExcel.setBounds(650, 610, 120, 30);
		panel.add(btnExcel);
		btnExcel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				String sa=SwingUtil.selectSavePath(frame);
				File a =new File(sa);
				if(a.exists()){
					a.delete();
				}
				JTableToExcel.export(a, "上海汇楠客户销售明细", "上海汇楠生物科技有限公司", invoiceTable);
			}
			public void mouseEntered(MouseEvent e) {// 鼠标进入
				// 设置鼠标进入变成小手
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnExcel.setBackground(UIManager.getColor("Button.shadow"));
			}
			public void mouseExited(MouseEvent e) {// 鼠标移除
				btnExcel.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		JLabel mes=new JLabel("（需要筛选时，请在表头处鼠标点击右键）");
		mes.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		mes.setForeground(Color.red);
		mes.setBounds(10, 50, 300, 30);
		panel.add(mes);
	}
	/**
	 * @描述：开票JPanel模型
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午2:24:40
	 * @param panel
	 * @param string
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	private void addPanel1(final JPanel panel, final String string) {
		panel.setLayout(null);
		label1 = new JLabel("上海汇楠生物科技有限公司");
		label1.setBounds(300, 10, 200, 40);
		label1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label2 = new JLabel("产品销售单");
		label2.setBounds(360, 35, 180, 40);
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		panel.add(label2);
		panel.add(label1);
		separator = new JSeparator();
		separator.setBounds(15, 70, 770, 2);
		panel.add(separator);
		JLabel labells =swingUtil.createDialogLabel1("流水单号：", 15, 70, 80, 40, "2");
		JLabel labellsts =swingUtil.createDialogLabel1("（生成后即锁定）", 235, 70, 100, 40, "1");
		JLabel labelfh =swingUtil.createDialogLabel1("发货日期：", 340, 70, 80, 40, "2");
		JLabel labelkh =swingUtil.createDialogLabel1("客户名称：", 15, 100, 80, 40, "2");
		JLabel labelkhts =swingUtil.createDialogLabel1("（选择后即锁定）", 235, 100, 100, 40, "1");
		labelkhts.setForeground(Color.blue);
		labellsts.setForeground(Color.blue);
		JLabel labelfz =swingUtil.createDialogLabel1("负 责 人：", 340, 100, 80, 40, "2");
		labelfzr =swingUtil.createDialogLabel1("（客户选择后自动带出）", 405, 100, 150, 40, "2");
		JLabel labeldz =swingUtil.createDialogLabel1("地      址：", 15, 130, 80, 40, "2");
		labeldz1 =swingUtil.createDialogLabel1("（客户选择后自动带出）", 80, 130, 150, 40, "2");
		panel.add(labells);
		panel.add(labellsts);
		panel.add(labelfh);
		panel.add(labelkh);
		panel.add(labelkhts);
		panel.add(labelfz);
		panel.add(labeldz);
		panel.add(labeldz1);
		panel.add(labelfzr);
		JTextField asd= new JTextField();
		panel.add(asd);
		
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto3.png"); 
			SwingUtil.inputstreamtofile(is,a);
			lsdhTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		createTextField(lsdhTextField,true);
		lsdhTextField.setBounds(80, 77, 150, 25);
		lsdhTextField.setEditable(false);
		panel.add(lsdhTextField);
		
		// 给日期Field设置背景图片
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto4.png"); 
			SwingUtil.inputstreamtofile(is,a);
			fhrqTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 设置Field透明，以看见背景图片
		fhrqTextField.setOpaque(false);
		fhrqTextField.setBounds(405, 77, 150, 25);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(fhrqTextField);
		panel.add(fhrqTextField);
		//客户名称下拉选
		kpKHMCComBox(panel);
		
		//动态添加的组件 
		//注：需要添加在Jtable的前面，必须先new出来，要在jtable之前添加进panel，否则动态添加的组件无法正常显示（搞了两天才解决,顺序很重要）
		dynamicAddKPCom(panel);
		
		//开票上部jtable
		dataVector = new Vector(); 
		InvoiceProJtablePaint invoiceProJtablePaint = new InvoiceProJtablePaint();
		invoiceProTable = invoiceProJtablePaint.invoiceProJtablePaint(invoiceProTable, dataVector);
		invoiceProScrollPane = new JScrollPane(invoiceProTable);
		invoiceProScrollPane.setBounds(15, 170, 760, 232);
		panel.add(invoiceProScrollPane, BorderLayout.CENTER);
		
		//开票下部jtable
		InvoiceProBottomJtablePaint invoiceProBottomJtablePaint =new InvoiceProBottomJtablePaint();
		invoicePro1Table =invoiceProBottomJtablePaint.invoiceProBottomJtablePaint(invoicePro1Table);
		invoicePro1ScrollPane = new JScrollPane(invoicePro1Table);
		invoicePro1ScrollPane.setBounds(15, 375, 760, 89);
		panel.add(invoicePro1ScrollPane);
		//折扣减免单元格数值更改事件
		invoicePro1Table.getModel().addTableModelListener(new TableModelListener(){
			 public void tableChanged(TableModelEvent e) {
			     if(e.getType() == TableModelEvent.UPDATE && e.getFirstRow()==0 && e.getColumn()==6 && dataVector.size() > 0){
		        	String zk = invoicePro1Table.getValueAt(e.getFirstRow(),e.getColumn()).toString();
		        	discountChange(zk);
			     }
			 }
        });
		
		//获取分页数据
		setPageDataBase();
		//开票新增产品记录按钮
		kpAddProRecordJButton(panel);
		//分页上一页按钮
		beforePageJButton(panel);
		//分页下一页按钮
		afterPageJButton(panel);
        
        labelbf =swingUtil.createDialogLabel1("第", 685, 470, 30, 30, "2");
		labelaf =swingUtil.createDialogLabel1(page.toString(), 705, 470, 30, 30, "2");
		labelys =swingUtil.createDialogLabel1("页", 725, 470, 30, 30, "2");
		panel.add(labelbf);  
		panel.add(labelaf);  
		panel.add(labelys);  
		
		labelkxlx =swingUtil.createDialogLabel1("款   项：", 590, 500, 60, 30, "2");
		labelkqzm =swingUtil.createDialogLabel1("请注明：", 590, 530, 60, 30, "2");
		qzmTextField =SwingUtil.creatComTextField(650, 530, 125, 25);
		panel.add(labelkxlx);  
		//款项类型下拉选
		kpKQLXComBox(panel);
		
		JLabel labelkprq =swingUtil.createDialogLabel1("开票日期：", 15, 505, 80, 30, "2");
		JLabel labelkpr =swingUtil.createDialogLabel1("开 票 人：", 220, 505, 80, 30, "2");
		
		JLabel labelxsdh =swingUtil.createDialogLabel1("销售电话：", 15, 595, 80, 30, "2");
		JLabel labelcz =swingUtil.createDialogLabel1("传     真：", 220, 595, 80, 30, "2");
		
		JLabel labelzph =swingUtil.createDialogLabel1("车 牌 号：", 15, 565, 80, 30, "2");
		JLabel labeljsz =swingUtil.createDialogLabel1("驾驶证号：", 220, 565, 80, 30, "2");
		
		JLabel labelcyr =swingUtil.createDialogLabel1("承 运 人：", 220, 535, 80, 30, "2");
		JLabel labelyfmx =swingUtil.createDialogLabel1("运费明细：", 15, 535, 80, 30, "2");
		
		JLabel labelqz =swingUtil.createDialogLabel1("备     注：", 15, 475, 130, 30, "0");
		labelqz.setFont(new Font("微软雅黑", 1, 14));
		panel.add(labelkprq);
		panel.add(labelkpr);
		panel.add(labelxsdh);
		panel.add(labelcz);
		panel.add(labelzph);
		panel.add(labeljsz);
		panel.add(labelcyr);
		panel.add(labelyfmx);
		panel.add(labelqz);
		// 给日期Field设置背景图片
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto5.png"); 
			SwingUtil.inputstreamtofile(is,a);
			kprqTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 设置Field透明，以看见背景图片
		kprqTextField.setOpaque(false);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(kprqTextField);
		
		kprqTextField.setBounds(80, 507, 120, 25);
		kprTextField =SwingUtil.creatComTextField(285, 507, 150, 25);
		
		xsdhTextField =SwingUtil.creatComTextField(80, 597, 120, 25);
		czTextField =SwingUtil.creatComTextField(285, 597, 150, 25);
		
		cphTextField =SwingUtil.creatComTextField(80, 567, 120, 25);
		jszhTextField =SwingUtil.creatComTextField(285, 567, 150, 25);
		
		cyrTextField =SwingUtil.creatComTextField(285, 537, 150, 25);
		yfmxTextField =SwingUtil.creatComTextField(80, 537, 120, 25);
		
		bzTextField =SwingUtil.creatComTextField(80, 477, 355, 25);
		panel.add(kprqTextField);
		panel.add(kprTextField);
		panel.add(xsdhTextField);
		panel.add(czTextField);
		panel.add(cphTextField);
		panel.add(jszhTextField);
		panel.add(cyrTextField);
		panel.add(yfmxTextField);
		panel.add(bzTextField);
		//信息重置JButton
		messageClearJButton(panel);
		//确认开票JButton
		comfirmKpJButton(panel);
	}
	
	/**
	 * @描述：添加打印html到内嵌浏览器并直接打开
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月8日 下午2:36:44
	 * @param print
	 */
	private void addHtmlAndOpenJFXBrowse(String print){
		File file = new File("print.html");
//		File fileLod = new File("LodopFuncs.js");
		
//		//写入LodopFuncs.js
//		try {
//			fileLod.createNewFile();
//			File a=new File("cache");
//			InputStream is=getClass().getClassLoader().getResourceAsStream("LodopFuncs.js"); 
//			SwingUtil.inputstreamtofile(is,a);
//			InputStream in = new FileInputStream(new File(a.getPath()));
//			OutputStream out = new FileOutputStream(fileLod);
//			byte[] buf = new byte[1024];
//			int len;
//			while ((len = in.read(buf)) > 0) {
//				out.write(buf, 0, len);
//			}
//			in.close();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//写入print.html
		OutputStreamWriter pw = null;// 定义一个流
		try {
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
			pw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			pw.write(print);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFrame localJFrame = new JFrame("开票成功");
		localJFrame.add(new JFXBrowser());
		localJFrame.setSize(330, 150);
		localJFrame.setLocationRelativeTo(null);
		localJFrame.setVisible(true);
	}
	
	/**
	 *
	 * @描述：获取打印内容
	 * @创建人：kitxiao
	 * @创建时间：2016年10月1日上午1:11:47
	 * @return
	 */
	@SuppressWarnings("static-access")
	private String hasPrintHtml(){
		String eTable="";
		int totalpageN=totalpage;
		if(leftNum == 0 && totalpage > 1){
			totalpageN=totalpage-1;
		}
		SimpleDateFormat sp1=new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sp2=new SimpleDateFormat("yyyy-MM-dd");
		String fhrq="";
		try {
			fhrq = sp1.format(sp2.parse(fhrqTextField.getText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String khmc=swingUtil.hasStrFromStr(comboBox.getSelectedItem().toString(), "-");
		for(int j=0;j<totalpageN;j++){
			eTable+="LODOP.ADD_PRINT_TEXT(6,"+(47+printLeftRange)+",100,20,'第"+(j+1)+"页');"+
				   "LODOP.ADD_PRINT_TEXT(20,"+(280+printLeftRange)+",222,30,'上海汇楠生物科技有限公司');"+
				   "LODOP.SET_PRINT_STYLEA(0,'FontSize',13);"+
				   "LODOP.SET_PRINT_STYLEA(0,'Alignment',2);"+
				   "LODOP.ADD_PRINT_TEXT(44,"+(341+printLeftRange)+",100,25,'产品销售单');"+
				   "LODOP.SET_PRINT_STYLEA(0,'FontSize',11);"+
				   "LODOP.SET_PRINT_STYLEA(0,'Alignment',2);"+
				   "LODOP.ADD_PRINT_TEXT(82,"+(46+printLeftRange)+",79,20,'流水单号：');"+
				   "LODOP.ADD_PRINT_TEXT(83,"+(106+printLeftRange)+",153,20,'"+lsdhTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT(84,"+(259+printLeftRange)+",69,20,'发货日期：');"+
				   "LODOP.ADD_PRINT_TEXT(85,"+(319+printLeftRange)+",131,20,'"+fhrq+"');"+
				   "LODOP.ADD_PRINT_TEXT(110,"+(45+printLeftRange)+",70,20,'客户名称：');"+
				   "LODOP.ADD_PRINT_TEXT(111,"+(106+printLeftRange)+",151,20,'"+khmc+"');"+
				   "LODOP.ADD_PRINT_TEXT(112,"+(257+printLeftRange)+",69,20,'负责人：');"+
				   "LODOP.ADD_PRINT_TEXT(113,"+(315+printLeftRange)+",135,20,'"+labelfzr.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT(138,"+(45+printLeftRange)+",49,20,'地址：');"+
				   "LODOP.ADD_PRINT_TEXT(139,"+(83+printLeftRange)+",365,20,'"+labeldz1.getText()+"');"+
				   "LODOP.ADD_PRINT_TABLE(165,"+(43+printLeftRange)+",836,189,'"+hasTable(j+1)+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(358-leftCol*20)+","+(44+printLeftRange)+",69,20,'备注：');"+
				   "LODOP.ADD_PRINT_TEXT("+(359-leftCol*20)+","+(80+printLeftRange)+",380,20,'"+bzTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(382-leftCol*20)+","+(44+printLeftRange)+",69,20,'开票日期：');"+
				   "LODOP.ADD_PRINT_TEXT("+(383-leftCol*20)+","+(102+printLeftRange)+",142,20,'"+kprqTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(382-leftCol*20)+","+(241+printLeftRange)+",60,20,'开票人：');"+
				   "LODOP.ADD_PRINT_TEXT("+(383-leftCol*20)+","+(291+printLeftRange)+",178,20,'"+kprTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(458-leftCol*20)+","+(44+printLeftRange)+",69,20,'销售电话：');"+
				   "LODOP.ADD_PRINT_TEXT("+(459-leftCol*20)+","+(103+printLeftRange)+",143,20,'"+xsdhTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(458-leftCol*20)+","+(240+printLeftRange)+",45,20,'传真：');"+
				   "LODOP.ADD_PRINT_TEXT("+(459-leftCol*20)+","+(277+printLeftRange)+",191,20,'"+czTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(432-leftCol*20)+","+(44+printLeftRange)+",60,20,'车牌号：');"+
				   "LODOP.ADD_PRINT_TEXT("+(433-leftCol*20)+","+(95+printLeftRange)+",151,20,'"+cphTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(432-leftCol*20)+","+(240+printLeftRange)+",73,20,'驾驶证号：');"+
				   "LODOP.ADD_PRINT_TEXT("+(433-leftCol*20)+","+(300+printLeftRange)+",170,20,'"+jszhTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(407-leftCol*20)+","+(241+printLeftRange)+",60,20,'承运人：');"+
				   "LODOP.ADD_PRINT_TEXT("+(408-leftCol*20)+","+(298+printLeftRange)+",174,20,'"+cyrTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(407-leftCol*20)+","+(43+printLeftRange)+",71,20,'运费明细：');"+
				   "LODOP.ADD_PRINT_TEXT("+(408-leftCol*20)+","+(100+printLeftRange)+",152,20,'"+yfmxTextField.getText()+"');"+
				   "LODOP.ADD_PRINT_TEXT("+(483-leftCol*20)+","+(42+printLeftRange)+",120,20,'客户签收（签字）：');"+
				   "LODOP.ADD_PRINT_TEXT("+(483-leftCol*20)+","+(241+printLeftRange)+",51,20,'日期：');"+
				   "LODOP.ADD_PRINT_TEXT("+(359-leftCol*20)+","+(587+printLeftRange)+",45,20,'款项：');"+
				   "LODOP.ADD_PRINT_TEXT("+(360-leftCol*20)+","+(630+printLeftRange)+",144,20,'"+comboBox1.getSelectedItem().toString()+"');"+
				   (comboBox1.getSelectedIndex() == 3 ? "LODOP.ADD_PRINT_TEXT("+(383-leftCol*20)+","+(587+printLeftRange)+",58,20,'请注明：');"+
							"LODOP.ADD_PRINT_TEXT("+(384-leftCol*20)+","+(635+printLeftRange)+",144,20,'"+qzmTextField.getText()+"');" : "")+
				   "LODOP.NewPage();";
		}
		String print="<!DOCTYPE><html>"+
		"<head>"+
		"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
		"<script language='javascript' src='LodopFuncs.js'></script>"+
		"<title>开票打印</title>"+
		"</head>"+
		"<body>"+
		"<div style='height:60px;text-align:center;'>"+
			"<span style='font-weight:bold;'>开票成功!流水号：<p>"+lsdhTextField.getText()+"</p></span>"+
		"</div>"+
		"<div>"+
			"<span>"+
				"<a style='margin-left:30px;' href='javascript:;' onclick='javascript:Preview()'>打印预览</a>"+
				"<a style='margin-left:80px;' href='javascript:;' onclick='JavaScript:Print()'>直接打印</a>"+
			"</span>"+
		"</div>"+
		"</body>"+
		"<script language='javascript' type='text/javascript'>"+
				"var LODOP;"+
				"function Preview() {"+		
					"CreateFullBill();"+
					"LODOP.PREVIEW();"+		
				"};"+
				"function Print() {"+
					"CreateFullBill();"+
					"LODOP.PRINT();"+
				"};"+	
				"function CreateFullBill() {"+
					"LODOP=getLodop();"+
					"LODOP.PRINT_INITA(0,0,800,533,'开票打印');"+
					"LODOP.SET_PRINT_PAGESIZE("+printDirection+","+printPaperWidth+","+printPaperHeight+",'');"+
					eTable+
				"};"+
		"</script>"+ 
		"</html>";
		return print;
	}
	
	/**
	 *
	 * @描述：获取打印js所需Table内容
	 * @创建人：kitxiao
	 * @创建时间：2016年10月1日上午1:11:58
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String hasTable(int page){
		String table="";
		String etable="";
		String zkjm=invoicePro1Table.getValueAt(0,6).toString();
		String ggslzj =invoicePro1Table.getValueAt(1,3).toString();
		String js =invoicePro1Table.getValueAt(1,4).toString();
		String totalCn =invoicePro1Table.getValueAt(1,5).toString();
		String totalM =invoicePro1Table.getValueAt(1,6).toString();
		if(totalpage == 1){
			for(int i=0;i<dataVector.size();i++){
				Vector data=(Vector)dataVector.get(i);
				etable+=hasERow(data);
			}
			leftCol = 6-dataVector.size();
		}else{
			if(page==totalpage){
				for(int i=(page-1)*7;i<dataVector.size();i++){
					Vector data=(Vector)dataVector.get(i);
					etable+=hasERow(data);
				}
				leftCol = 6-(dataVector.size()-(page-1)*7);
			}else{
				for(int i=(page-1)*7;i<page*7;i++){
					Vector data=(Vector)dataVector.get(i);
					etable+=hasERow(data);
				}
				leftCol = 0;
			}
		}
		table="<style> table,td,th {border: 1px solid black;border-style: solid;border-collapse: collapse}</style>"+
				"<table border='1'>"+
				"<tr>"+
				"<td style='width:80px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>产品代码</font></td>"+
				"<td style='width:180px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>产品名称</font></td>"+
				"<td style='width:90px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>规格</font></td>"+
				"<td style='width:60px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>数量(kg)</font></td>"+
				"<td style='width:50px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>件数</font></td>"+
				"<td style='width:170px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>单价</font></td>"+
				"<td style='width:80px;text-align:center;'><font  style='font-weight:bold;font-size:12px;'>金额小计</font></td>"+
				"</tr>"+etable+
				"<tr>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;>text-align:center;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;font-weight:bold;'>折扣减免</font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'>"+zkjm+"</font></td>"+
				"</tr>"+
				"<tr>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;font-weight:bold;'>合计</font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;>text-align:center;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;'></font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'>"+ggslzj+"</font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'>"+js+"</font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'>"+totalCn+"</font></td>"+
				"<td style='text-align:center;'><font  style='font-size:12px;text-align:center;'>"+totalM+"</font></td>"+
				"</tr>"+
				"</table>";
		table=table.replace("\'", "\"");
		return table;
	}
	
	/**
	 *
	 * @描述：获取打印table每行的值
	 * @创建人：kitxiao
	 * @创建时间：2016年10月1日上午1:12:13
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String hasERow(Vector data){
		String etable="<tr>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(0).toString()+"</font></td>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(1).toString()+"</font></td>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(2).toString()+"</font></td>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(3).toString()+"</font></td>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(4).toString()+"</font></td>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(5).toString()+"</font></td>"+
		"<td style='text-align:center;'><font  style='font-size:12px;'>"+data.get(6).toString()+"</font></td>"+
		"</tr>";
		etable=etable.replace("\'", "\"");
		return etable;
	}
	
	/**
	 *
	 * @描述：添加打印table空行
	 * @创建人：kitxiao
	 * @创建时间：2016年10月1日上午1:11:14
	 * @return
	 */
//	private String hasEmptyRow(){
//		String etable="<tr>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"<td style='text-align:center;'><font  style='font-size:12px;'>&nbsp;</font></td>"+
//				"</tr>";
//				etable=etable.replace("\'", "\"");
//		return etable;
//	}
	
	/**
	 * @描述：开票信息校验
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月30日 下午4:25:34
	 * @return
	 */
	@SuppressWarnings("static-access")
	private String kpExamMessage(){
		String message="";
		if(swingUtil.isEmpty(lsdhTextField.getText())){
			return message="流水单号不能为空，请添加！";
		}else if(swingUtil.isEmpty(fhrqTextField.getText())){
			return message="发货日期不能为空，请添加！";
		}else if(!swingUtil.isDateFormat(fhrqTextField.getText())){
			return message="发货日期格式不正确，请重新填写！";
		}else if(comboBox.getSelectedIndex()==0 || swingUtil.isEmpty(fieldCSid.getText())){
			return message="客户名称不能为空，请添加！";
		}else if(dataVector==null || dataVector.size() <= 0){
			return message="产品记录不能为空，请添加！";
		}else if(swingUtil.isEmpty(kprqTextField.getText())){
			return message="开票日期不能为空，请添加！";
		}else if(!swingUtil.isDateFormat(kprqTextField.getText())){
			return message="开票日期格式不正确，请重新填写！";
		}else if(swingUtil.isEmpty(kprTextField.getText())){
			return message="开票人不能为空，请添加！";
		}else if(swingUtil.isEmpty(xsdhTextField.getText())){
			return message="销售电话不能为空，请添加！";
		}else if(!swingUtil.isPhone(xsdhTextField.getText()) && !swingUtil.isMobile(xsdhTextField.getText())){
			return message="销售电话格式不正确，请重新填写！";
		}else if(swingUtil.isEmpty(czTextField.getText())){
			return message="传真不能为空，请添加！";
		}else if(!swingUtil.isPhone(czTextField.getText())){
			return message="传真格式不正确，请重新填写！";
		}else if(swingUtil.isEmpty(cphTextField.getText())){
			return message="车牌号不能为空，请添加！";
		}else if(swingUtil.isEmpty(jszhTextField.getText())){
			return message="驾驶证号不能为空，请添加！";
		}else if(!swingUtil.isIdCardNum(jszhTextField.getText())){
			return message="驾驶证号格式不正确，请重新填写！";
		}else if(swingUtil.isEmpty(cyrTextField.getText())){
			return message="承运人不能为空，请添加！";
		}else if(swingUtil.isEmpty(yfmxTextField.getText())){
			return message="运费明细不能为空，请添加！";
		}else if(comboBox1.getSelectedIndex()==0){
			return message="款项类型不能为空，请添加！";
		}else if(comboBox1.getSelectedIndex() == 3 && swingUtil.isEmpty(qzmTextField.getText())){
			return message="款项注明信息不能为空，请添加！";
		}
		return message;
	}
	
	/**
	 * @描述：开票信息重置
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月30日 下午3:04:36
	 */
	private void clearKPAll(){
		lsdhTextField.setText("");
		lsdhTextField.setOpaque(false);
		fhrqTextField.setText("");
		fieldCSid.setText("");
		comboBox.setEnabled(true);
		comboBox.setSelectedIndex(0);
		conFlag = 0;
		dataVector.removeAllElements();
		setPageDataBase();
		addKpProductRecord(false,true);
		invoicePro1Table.setValueAt("", 0, 6);
		invoicePro1Table.setValueAt("", 1, 6);
		invoicePro1Table.setValueAt("", 1, 5);
		invoicePro1Table.setValueAt("", 1, 4);
		invoicePro1Table.setValueAt("", 1, 3);
		kprTextField.setText("");
		xsdhTextField.setText("");
		czTextField.setText("");
		cphTextField.setText("");
		jszhTextField.setText("");
		cyrTextField.setText("");
		yfmxTextField.setText("");
		bzTextField.setText("");
		kprqTextField.setText("");
		fieldqklx.setText("");
		comboBox1.setSelectedIndex(0);
		qzmTextField.setText("");
	}
	
	/**
	 * @描述：开票panel确认开票JButton
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月8日 下午5:31:46
	 * @param panel
	 */
	private void comfirmKpJButton(JPanel panel){
		invConfirmButton = new JButton("确认开票");
		invConfirmButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Object[] options = {"确定","取消"};
				int response=JOptionPane.showOptionDialog(frame, "请确认无误后开票！", "确认开票",JOptionPane.YES_OPTION, JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);
				if(response==0){
					String message=kpExamMessage();
					if(!swingUtil.isEmpty(message)){
						JOptionPane.showMessageDialog(null, message, "提示", JOptionPane.WARNING_MESSAGE);
					}else{
						InvoiceCtr invoiceCtr=new InvoiceCtr();
						if(swingUtil.isEmpty(fieldqklx.getText())){
							fieldqklx.setText(qzmTextField.getText());
						}
						int i=invoiceCtr.insertInvoicePro(dataVector, lsdhTextField.getText(), fieldCSid.getText(), fhrqTextField.getText(), kprqTextField.getText(), 
								kprTextField.getText(),xsdhTextField.getText(), czTextField.getText(), cphTextField.getText(), jszhTextField.getText(),
								cyrTextField.getText(), yfmxTextField.getText(), fieldqklx.getText(), totalProPrice.subtract(zkMoney).toString(),zkMoney.toString(),bzTextField.getText());
						if(i>0){
							String print=hasPrintHtml();
							addHtmlAndOpenJFXBrowse(print);
							clearKPAll();
						}else{
							JOptionPane.showMessageDialog(null, "开票失败", "提示", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
			public void mouseEntered(MouseEvent e) {// 鼠标进入
				// 设置鼠标进入变成小手
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				invConfirmButton.setBackground(UIManager.getColor("Button.shadow"));
			}
			public void mouseExited(MouseEvent e) {// 鼠标移除
				invConfirmButton.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		invConfirmButton.setBackground(new Color(255, 255, 255));
		invConfirmButton.setIcon(swingUtil.produceImage("confirmInv.png"));
		invConfirmButton.setBorder(new RoundBorder());
		invConfirmButton.setBounds(660,597,120,30);
		panel.add(invConfirmButton);
	}
	
	/**
	 * @描述：开票panel信息重置JButton
	 * @创建人：肖龙祥
	 * @创建时间：2016年10月1日 下午5:29:54
	 * @param panel
	 */
	private void messageClearJButton(JPanel panel){
		invReloadButton = new JButton("信息重置");
		invReloadButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Object[] options = {"确定","取消"};
				int response=JOptionPane.showOptionDialog(frame, "确认重置信息吗？", "信息重置",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(response==0){ 
					clearKPAll();
				}
			}
			public void mouseEntered(MouseEvent e) {// 鼠标进入
				// 设置鼠标进入变成小手
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				invReloadButton.setBackground(UIManager.getColor("Button.shadow"));
			}
			public void mouseExited(MouseEvent e) {// 鼠标移除
				invReloadButton.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		invReloadButton.setBackground(new Color(255, 255, 255));
		invReloadButton.setIcon(swingUtil.produceImage("reload.png"));
		invReloadButton.setBorder(new RoundBorder());
		invReloadButton.setBounds(530,597,120,30);
		panel.add(invReloadButton);
	}
	
	/**
	 * @描述：设置分页基础数据
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午3:37:44
	 */
	private void setPageDataBase(){
		totalpage=dataVector.size()/7+1;
		leftNum = dataVector.size()%7;
		pageButtonIsEnabled();
	}
	
	/**
	 * @描述：分页下一页按钮
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午2:37:05
	 * @param panel
	 */
	private void afterPageJButton(JPanel panel){
		afterPage = new JLabel(swingUtil.produceImage("afterPage.png"));  
        afterPage.setBounds(745, 470, 30, 25);  
        afterPage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseExited(MouseEvent e) {
        		afterPage.setIcon(swingUtil.produceImage("afterPage.png"));
        		afterPage.setEnabled(true);
        		setCursor(Cursor.getDefaultCursor());
         	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
    			afterPage.setEnabled(afEnable);
        		afterPage.setDisabledIcon(swingUtil.produceImage("notEnable.png"));
        		afterPage.setIcon(swingUtil.produceImage("afterPage1.png"));
        		if(afterPage.isEnabled()){
        			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        		}
        	}
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		if(page>=totalpage){
        			afterPage.setEnabled(false);
        			setCursor(Cursor.getDefaultCursor());
        		}
        	}
        	@Override
			public void mousePressed(MouseEvent e) {
    	        if(page<totalpage){
    	        	page=page+1;
    	        	pageButtonIsEnabled();
    	        	labelaf.setText(page.toString());
    	        	labelaf.repaint();
    	        	paintInvTableByPage();
    	        }else{
    	        	afterPage.setEnabled(false);
    	        }
    	        if(page != totalpage){
    	        	setDynamicComVisiable(false);
    	        }else{
    	        	if(conFlag == 1){
        				setDynamicComVisiableNotCC(true);
        				if(comboBoxProName.getSelectedIndex()!=0){
            				standardNum.requestFocus();
            			}
        			}
    	        }
			}
        });
        panel.add(afterPage);  
	}
	
	
	/**
	 * @描述：分页上一页按钮
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午2:32:29
	 * @param panel
	 */
	private void beforePageJButton(JPanel panel){
	 	beforePage = new JLabel(swingUtil.produceImage("beforePage.png"));  
        beforePage.setBounds(645, 470, 30, 25);  
        beforePage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				beforePage.setIcon(swingUtil.produceImage("beforePage.png"));
				setCursor(Cursor.getDefaultCursor());
				beforePage.setEnabled(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				beforePage.setEnabled(beEnable);
				beforePage.setDisabledIcon(swingUtil.produceImage("notEnable.png"));
				beforePage.setIcon(swingUtil.produceImage("beforePage1.png"));
				if(beforePage.isEnabled()){
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
			@Override
        	public void mouseReleased(MouseEvent e) {
        		if(page<=1){
        			beforePage.setEnabled(false);
        			setCursor(Cursor.getDefaultCursor());
        		}
        	}
			@Override
			public void mousePressed(MouseEvent e) {
		        if(page>1){
		        	page=page-1;
		        	pageButtonIsEnabled();
		        	labelaf.setText(page.toString());
		        	labelaf.repaint();
		        	paintInvTableByPage();
		        }else{
		        	beforePage.setEnabled(false);
    	        }
		        if(page != totalpage){
        			setDynamicComVisiable(false);
        		}
			}
		});
        panel.add(beforePage);  
	}
	
	/**
	 * @描述：添加开票新增产品记录按钮
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午2:28:06
	 * @param panel
	 */
	private void kpAddProRecordJButton(JPanel panel){
		btnNewInvButton = new JButton("新增产品记录");
		btnNewInvButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Dialog dlg=null;
				int index=comboBox.getSelectedIndex();
				String lsdhText=lsdhTextField.getText();
				if("".equals(lsdhText)){
					dlg=new OperateDBFailMDialog(frame,true,"请先获取流水单号！");
				}else if(index == 0){
					dlg=new OperateDBFailMDialog(frame,true,"请先选择客户名称！");
				}else{
					if(conFlag == 0){
						addKpProductRecord(true,false);
					}else{
						dlg=new OperateDBFailMDialog(frame,true,"已有一条产品记录正在编辑！");
					}
				}
				if(dlg!=null){
					dlg.setVisible(true);
				}
			}
			public void mouseEntered(MouseEvent e) {// 鼠标进入
				// 设置鼠标进入变成小手
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNewInvButton.setBackground(UIManager.getColor("Button.shadow"));
			}
			public void mouseExited(MouseEvent e) {// 鼠标移除
				btnNewInvButton.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		btnNewInvButton.setBackground(new Color(255, 255, 255));
		btnNewInvButton.setIcon(swingUtil.produceImage("addProduct.png"));
		btnNewInvButton.setBorder(new RoundBorder());
		btnNewInvButton.setBounds(625,130,150,30);
		panel.add(btnNewInvButton);
	}
	
	/**
	 * @描述：开票款项类型下拉选
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午4:40:39
	 * @param panel
	 */
	private void kpKQLXComBox(final JPanel panel){
		fieldqklx = new JTextField();
		comboBox1 = new DIYComboBox<String>(new String[]{"-请选择款项类型"," 全部收到"," 客户欠款"," 其他（请输入）"});
		comboBox1.setBounds(650, 500, 125, 25);
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					Integer selectI = comboBox1.getSelectedIndex();
					//判断comboBox是否为第一个选项
					if(selectI == 0){
						panel.remove(labelkqzm);
						panel.remove(qzmTextField);
						fieldqklx.setText(null);
					}else if (selectI == 3) {
						panel.add(labelkqzm);
						panel.add(qzmTextField);
						fieldqklx.setText("");
					}else{
						panel.remove(labelkqzm);
						panel.remove(qzmTextField);
						fieldqklx.setText(comboBox1.getSelectedItem().toString());
					}
					panel.updateUI();
					panel.repaint();
				}
			}
		});
		panel.add(comboBox1);
	}
	/**
	 * @描述：开票客户名称下拉选
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午4:43:08
	 * @param panel
	 */
	private void kpKHMCComBox(JPanel panel){
		fieldCSid = new JTextField();
		CustomerPriceCtr = new CustomerPriceCtr();
		cDate = CustomerPriceCtr.selectCustomerListForCB();
		String[] str1 = new String[cDate.size() + 1];
		str1[0] = "-请选择客户名称 -";
		for (int i = 0; i < cDate.size(); i++) {
			str1[i + 1] = " "+cDate.get(i).getSid().toString()+"-"+cDate.get(i).getCname();
		}
		//将值添加进自定义的comboBox中
		comboBox = new DIYComboBox<String>(str1);
		comboBox.setBounds(80, 107, 150, 25);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					int selectI = comboBox.getSelectedIndex() - 1;
					//判断comboBox是否为第一个选项
					if (selectI >= 0) {
						labelfzr.setText(cDate.get(selectI).getPerson());
						labeldz1.setText(cDate.get(selectI).getAddress());
						fieldCSid.setText(cDate.get(selectI).getSid().toString());
						comboBox.setEnabled(false);
					} else {
						labelfzr.setText("（客户选择后自动带出）");
						labeldz1.setText("（客户选择后自动带出）");
						fieldCSid.setText(null);
					}
				}
			}
		});
		panel.add(comboBox);
	}
	/**
	 * @描述：开票时新增产品记录，为各个动态组件添加坐标并重绘显示
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午2:07:47
	 */
	private void addKpProductRecord(boolean x,boolean y){
		if(x||y){
			//获取最大页数以及该页的数据条数
			page =totalpage;
			//刷新到该页
			pageButtonIsEnabled();
			labelaf.setText(page.toString());
			labelaf.repaint();
			paintInvTableByPage();
		}
		//在最后一条数据后新增产品，获取坐标值
		Point inicial=new Point(15,190+leftNum*30);
		TableColumnModel invCModel=invoiceProTable.getColumnModel();
		List<Integer> widthList = new ArrayList<Integer>();
		for(int i=0;i<invCModel.getColumnCount();i++){
			int everyWidth = invCModel.getColumn(i).getWidth();
			widthList.add(everyWidth);
		}
		List<Rectangle> recList = new ArrayList<Rectangle>();
		int totalWidth=0;
		for(int i=0;i<widthList.size();i++){
			Rectangle rec =new Rectangle(inicial.x+totalWidth, inicial.y, widthList.get(i), 30);
			totalWidth+=widthList.get(i);
			recList.add(rec);
		}
		productCode.setBounds(recList.get(1));
		comboBoxProName.setBounds(recList.get(0));
		standard.setBounds(recList.get(2));
		standardNum.setBounds(recList.get(3));
		unit.setBounds(recList.get(4));
		unitPrice.setBounds(recList.get(5));
		totalPrice.setBounds(recList.get(6));
		fieldPSid.setBounds(recList.get(7));
		if(x){
			setDynamicComVisiableAndCC(true);
			conFlag =1;
		}else{
			if(conFlag == 1 && page ==totalpage){
				setDynamicComVisiableNotCC(true);
			}else{
				setDynamicComVisiableNotCC(false);
			}
		}
	}
	
	/**
	 * @描述：动态添加开票新增记录时各个组件
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午1:38:28
	 * @param panel
	 */
	private void dynamicAddKPCom(JPanel panel){
		standard=new JTextField();
		standardNum =new JTextField();
		unit =new JTextField();
		unitPrice =new JTextField();
		totalPrice =new JTextField();
		fieldPSid =new JTextField();
		// 获取下拉框的值
		pDate = CustomerPriceCtr.selectProductListForCB();
		String[] str2 = new String[pDate.size() + 1];
		str2[0] = "请选择产品代码";
		for (int i = 0; i < pDate.size(); i++) {
			str2[i + 1] = " "+pDate.get(i).getSid()+"-" + pDate.get(i).getCode();
		}
		String[] str1 = new String[pDate.size() + 1];
		str1[0] = "请选择产品名称";
		for (int i = 0; i < pDate.size(); i++) {
			str1[i + 1] = pDate.get(i).getSid()+"-" + pDate.get(i).getPname();
		}
		productCode =new DIYComboBox<String>(str1);
		comboBoxProName = new DIYComboBox<String>(str2);
		productCode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					int selectI = productCode.getSelectedIndex() - 1;
					//判断comboBox是否为第一个选项
					if (selectI >= 0) {
						BigDecimal price=CustomerPriceCtr.selectPriceByCSidAndPSid(pDate.get(selectI).getSid().toString(), fieldCSid.getText());
						if(price != null && price!=new BigDecimal(0)){
							comboBoxProName.setSelectedIndex(selectI+1);
							standard.setText(pDate.get(selectI).getStandard().toString()+" "+pDate.get(selectI).getUnit() + "/" + pDate.get(selectI).getStandardUnit());
							unitPrice.setText(price.toString()+" 元/"+pDate.get(selectI).getUnit());
							fieldPSid.setText(pDate.get(selectI).getSid().toString());
							standardNum.setEnabled(true);
							standardNum.requestFocus();
						}else{
							Dialog dlg=new OperateDBFailMDialog(frame,true,"该客户产品关系不存在，请前去添加！");
							dlg.setVisible(true);
							comboBoxProName.setSelectedIndex(0);
							productCode.setSelectedIndex(0);
						}
					} else {
						clearDynamicComWhenSelcet1();
					}
				}
			}
		});
		//将值添加进自定义的comboBox中
		//comboBox选中事件
		comboBoxProName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					int selectI = comboBoxProName.getSelectedIndex() - 1;
					//判断comboBox是否为第一个选项
					if (selectI >= 0) {
						BigDecimal price=CustomerPriceCtr.selectPriceByCSidAndPSid(pDate.get(selectI).getSid().toString(), fieldCSid.getText());
						if(price != null && price!=new BigDecimal(0)){
							productCode.setSelectedIndex(selectI+1);
							standard.setText(pDate.get(selectI).getStandard().toString()+" "+pDate.get(selectI).getUnit() + "/" + pDate.get(selectI).getStandardUnit());
							unitPrice.setText(price.toString()+" 元/"+pDate.get(selectI).getUnit());
							fieldPSid.setText(pDate.get(selectI).getSid().toString());
							standardNum.setEnabled(true);
							standardNum.requestFocus();
						}else{
							Dialog dlg=new OperateDBFailMDialog(frame,true,"该客户产品关系不存在，请前去添加！");
							dlg.setVisible(true);
							comboBoxProName.setSelectedIndex(0);
							productCode.setSelectedIndex(0);
						}
					} else {
						clearDynamicComWhenSelcet1();
					}
				}
			}
		});
		setDynamicComEnabled(false);
		standardNum.addKeyListener(new KeyAdapter(){ 
			public void keyPressed(KeyEvent e){    
			        if(e.getKeyChar()==KeyEvent.VK_ENTER ){ //回车事件
			        	standardNumChange();
			        } 
		      } 
		      //限制只能输入数字
		      public void keyTyped(KeyEvent e) {
					int key = e.getKeyChar();
					if (!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)) {
						e.consume();  //取消输入事件
						//轰鸣器响一声
						if(key != KeyEvent.VK_BACK_SPACE){
							Toolkit.getDefaultToolkit().beep();
						}
					}
			  }
	    });
		//实时监控规格数量并返回相应的总金额到totalPrice
		standardNum.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
            	hasHTotalMoney(e);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
            	hasHTotalMoney(e);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            	hasHTotalMoney(e);
            }
        });
		comboBoxProName.setBackground(Color.yellow);
		productCode.setBackground(Color.yellow);
		standardNum.setBackground(Color.yellow);
		setDynamicComVisiable(false);
		panel.add(productCode);
		panel.add(comboBoxProName);
		panel.add(standard);
		panel.add(standardNum);
		panel.add(unit);
		panel.add(unitPrice);
		panel.add(totalPrice);
		panel.add(fieldPSid);
	}
	
	/**
	 * @描述：设置动态组件不可用
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午1:57:22
	 */
	private void setDynamicComEnabled(boolean x){
		standard.setEnabled(x);
		unit.setEnabled(x);
		unitPrice.setEnabled(x);
		totalPrice.setEnabled(x);
		fieldPSid.setEnabled(x);
		standardNum.setEnabled(x);
	}
	/**
	 * @描述：当comboBoxProName选择第一项时，清空其他动态组件值
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午1:43:47
	 */
	private void clearDynamicComWhenSelcet1(){
		comboBoxProName.setSelectedIndex(0);
		productCode.setSelectedIndex(0);
		standard.setText("");
		unit.setText("");
		unitPrice.setText("");
		fieldPSid.setText("");
		totalPrice.setText("");
		standardNum.setText("");
		standardNum.setEnabled(false);
	}
	
	/**
	 * @描述：规格数量单元格回车事件
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月29日 上午11:06:34
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access"})
	private void standardNumChange(){
		if(!"".equals(standardNum.getText())){
    		Vector data =new Vector();
    		data.add(swingUtil.hasStrFromStr(comboBoxProName.getSelectedItem().toString(), "-"));
    		data.add(swingUtil.hasStrFromStr(productCode.getSelectedItem().toString(), "-"));
    		data.add(standard.getText());
    		data.add(standardNum.getText());
    		data.add(unit.getText());
    		data.add(unitPrice.getText());
    		data.add(totalPrice.getText());
    		data.add(fieldPSid.getText());
    		dataVector.add(data);
    		//设置开关
    		conFlag =0;
    		//设置动态组件不可见，清空缓存并重绘
    		setDynamicComVisiableAndCC(false);
    		//更新基础分页数据
    		setPageDataBase();
    		page =totalpage;
    		pageButtonIsEnabled();
    		labelaf.setText(page.toString());
    		labelaf.repaint();
    		paintInvTableByPage();
    		
    		//更新开票下部jtable （invoicePro1Table）的数据
    		standardTotalNum = 0;
    		BigDecimal totaljs=new BigDecimal(0);
    		totalProPrice = new BigDecimal(0);
    		for(int i=0;i<dataVector.size();i++){
    			Vector c=(Vector) dataVector.get(i);
    			BigDecimal ePrice=new BigDecimal(c.get(6).toString());
    			int sNum=Integer.parseInt(c.get(3).toString());
    			BigDecimal ejs =new BigDecimal(c.get(4).toString());
    			totalProPrice = totalProPrice.add(ePrice);
    			standardTotalNum +=sNum;
    			totaljs=totaljs.add(ejs);
    		}
    		String zk=invoicePro1Table.getValueAt(0, 6).toString();
    		invoicePro1Table.setValueAt(totaljs, 1, 4);
    		discountChange(zk);
    		
    	}else{
    		Dialog dlg=new OperateDBFailMDialog(frame,true,"规格数量不能为空，请返回添加！");
			dlg.setVisible(true);
    	}
	}
	
	/**
	 * @描述：折扣减免更改时事件
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月29日 上午11:02:52
	 */
	private void discountChange(String zk){
		if(!"".equals(zk)){
			zkMoney =new BigDecimal(zk);
		}else{
			zkMoney =new BigDecimal(0);
		}
		BigDecimal actualMoney = totalProPrice.subtract(zkMoney);
		if(actualMoney.compareTo(new BigDecimal(0)) <= 0){
			invoicePro1Table.setValueAt("", 0, 6);
			Dialog dlg=new OperateDBFailMDialog(frame,true,"折扣金额不合理，请重新填写！");
			dlg.setVisible(true);
		}else{
			String moneyCN = NumberToCN.number2CNMontrayUnit(actualMoney);
			invoicePro1Table.setValueAt(actualMoney.toString(), 1, 6);
			invoicePro1Table.setValueAt(moneyCN, 1, 5);
		}
		invoicePro1Table.setValueAt(standardTotalNum, 1, 3);
	}
	
	/**
	 * @描述：动态刷新开票panel中客户名称下拉选
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午1:28:24
	 */
	private void frashKPCustomerCombox(){
		int count =0;
		String cSid = fieldCSid.getText();
		CustomerPriceCtr CustomerPriceCtr = new CustomerPriceCtr();
		cDate = CustomerPriceCtr.selectCustomerListForCB();
		String[] str1 = new String[cDate.size() + 1];
		str1[0] = "请选择客户名称";
		for (int i = 0; i < cDate.size(); i++) {
			str1[i + 1] =cDate.get(i).getSid().toString()+"-"+cDate.get(i).getCname();
			if(cSid!="" && cDate.get(i).getSid().toString().equals(cSid)){
				count=i+1;
			}
		}
		//将值添加进自定义的comboBox中
		comboBox.removeAllItems();
		for(int i=0;i<str1.length;i++){
			comboBox.addItem(str1[i]);
		}
		if(count==0){
			comboBox.setEnabled(true);
		}
		comboBox.setSelectedIndex(count);
		comboBox.repaint();
		pDate = CustomerPriceCtr.selectProductListForCB();
		String[] str3 = new String[pDate.size() + 1];
		str3[0] = "请选择产品名称";
		for (int i = 0; i < pDate.size(); i++) {
			str3[i + 1] =pDate.get(i).getSid()+"-" + pDate.get(i).getPname();
		}
		String[] str2 = new String[pDate.size() + 1];
		str2[0] = "请选择产品代码";
		for (int i = 0; i < pDate.size(); i++) {
			str2[i + 1] = " "+pDate.get(i).getSid()+"-" + pDate.get(i).getCode();
		}
		productCode.removeAllItems(); 
		for(int i=0;i<str2.length;i++){
			productCode.addItem(str3[i]);
		}
		comboBoxProName.removeAllItems(); 
		for(int i=0;i<str2.length;i++){
			comboBoxProName.addItem(str2[i]);
		}
		comboBoxProName.repaint();
		productCode.repaint();
	}
	
	/**
	 * @描述：动态获取小计总额
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 上午10:30:41
	 * @param e
	 */
	@SuppressWarnings("static-access")
	private void hasHTotalMoney(DocumentEvent e){
		Document doc = e.getDocument();  
    	String s="";
        try {
			s = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
        if(!"".equals(s) && swingUtil.isDecimal(s)){
        	String u=unitPrice.getText();
        	String st=standard.getText();
        	BigDecimal uPrice= swingUtil.hasIntFromStr(u," ");
        	BigDecimal stand= swingUtil.hasIntFromStr(st," ");
        	BigDecimal sNum= new BigDecimal(s);
        	totalPrice.setText(uPrice.multiply(sNum).toString());
        	unit.setText(sNum.divide(stand).toString());
        }else{
        	totalPrice.setText("");
        	unit.setText("");
        }
	}
	
	
	/**
	 * @描述：设置动态组件是否可见
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午1:49:38
	 */
	private void setDynamicComVisiable(boolean x){
		comboBoxProName.setVisible(x);
		productCode.setVisible(x);
		standard.setVisible(x);
		standardNum.setVisible(x);
		unit.setVisible(x);
		unitPrice.setVisible(x);
		totalPrice.setVisible(x);
		fieldPSid.setVisible(x);
	}
	/**
	 * @描述：重绘所有动态组件
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午1:52:22
	 */
	private void repaintEveryDynamicCom(){
		productCode.repaint();
		comboBoxProName.repaint();
		standard.repaint();
		standardNum.repaint();
		unit.repaint();
		unitPrice.repaint();
		totalPrice.repaint();
		fieldPSid.repaint();
	}
	/**
	 * @描述：设置动态组件是否可见,清空缓存并重绘
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月27日 下午2:13:51
	 */
	private void setDynamicComVisiableAndCC(boolean x){
		setDynamicComVisiable(x);
		clearDynamicComWhenSelcet1();
		pDate = CustomerPriceCtr.selectProductListForCB();
		String[] str1 = new String[pDate.size() + 1];
		str1[0] = "请选择产品名称";
		for (int i = 0; i < pDate.size(); i++) {
			str1[i + 1] =pDate.get(i).getSid()+"-" + pDate.get(i).getPname();
		}
		String[] str2 = new String[pDate.size() + 1];
		str2[0] = "请选择产品代码";
		for (int i = 0; i < pDate.size(); i++) {
			str2[i + 1] = " "+pDate.get(i).getSid()+"-" + pDate.get(i).getCode();
		}
		productCode.removeAllItems(); 
		for(int i=0;i<str2.length;i++){
			productCode.addItem(str1[i]);
		}
		comboBoxProName.removeAllItems(); 
		for(int i=0;i<str2.length;i++){
			comboBoxProName.addItem(str2[i]);
		}
		repaintEveryDynamicCom();
	}
	/**
	 * @描述：设置动态组件是否可见,不清缓存并重绘
	 * @创建人：肖龙祥
	 * @创建时间：2016年9月28日 下午12:03:09
	 */
	private void setDynamicComVisiableNotCC(boolean x){
		setDynamicComVisiable(x);
		repaintEveryDynamicCom();
	}
	
	/**
	 *
	 * @描述：根据总页数和当前页数判断上一页和下一页按钮是否可用
	 * @创建人：kitxiao
	 * @创建时间：2016年9月23日下午8:58:43
	 */
	private void pageButtonIsEnabled(){
		if(totalpage-page>0 && page-1>0){
        	afEnable= true;
        	beEnable= true;
        }else if(totalpage-page>0 && page-1<=0){
        	afEnable= true;
        	beEnable =false;
        }else if(totalpage-page<=0 && page-1>0){
        	afEnable =false;
        	beEnable= true;
        }else{
        	afEnable =false;
        	beEnable= false;
        }
	}
	
	/**
	 *
	 * @描述：根据page获取相应的数据
	 * @创建人：kitxiao
	 * @创建时间：2016年9月23日下午9:38:27
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector hasPageData(){
		 Vector Vector=new Vector();
	        int startIndex=0;
	        int endIndex =0;
	        if(page==totalpage){
	        	startIndex=(page-1)*7;
	        	endIndex =dataVector.size()-1;
	        }else{
	        	startIndex=(page-1)*7;
	        	endIndex =page*7-1;
	        }
	        for(int i=0;i<dataVector.size();i++){
	        	if(i>=startIndex&&i<=endIndex){
	        		Vector.add(dataVector.elementAt(i));
	        	}
	        }
			return Vector;
	}
	
	/**
	 *
	 * @描述：根据page重绘invTable
	 * @创建人：kitxiao
	 * @创建时间：2016年9月23日下午9:40:56
	 */
	@SuppressWarnings("static-access")
	private void paintInvTableByPage(){
		InvoiceProJtablePaint invoiceProJtablePaint = new InvoiceProJtablePaint();
		invoiceProTable = invoiceProJtablePaint.invoiceProJtablePaint(invoiceProTable, hasPageData());
		invoiceProScrollPane.setViewportView(invoiceProTable);
		invoiceProScrollPane.repaint();
		invoiceProScrollPane.setBounds(15, 170, frame.getWidth() - 140, 232);
		Double[] invoiceProWidthPercent = new Double[] { 0.154,0.2114,0.1057,0.0925,0.066,0.2378,0.132,0.0};
		swingUtil.aotoColumnWidth(invoiceProTable, invoiceProScrollPane, invoiceProWidthPercent);
		invoicePro1ScrollPane.setBounds(15, 375, frame.getWidth() - 140, 89);
		Double[] invoicePro1WidthPercent = new Double[] { 0.154,0.2114,0.1057,0.0925,0.066,0.2378,0.132,0.0};
		swingUtil.aotoColumnWidth(invoicePro1Table, invoicePro1ScrollPane, invoicePro1WidthPercent);
	}

	/**
	 *
	 * @描述：信息维护JPanel模型
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:45:44
	 * @param panel
	 * @param string
	 */
	private void addPanel0(JPanel panel, final String string) {
		panel.setLayout(null);
		tabbedPane2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane2.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		JPanel panel3 = createPanel("panel3");
		tabbedPane2.addTab("客户名单", swingUtil.produceImage("card_customer_info_user.png"), panel3, "客户名单");
		JPanel panel4 = createPanel("panel4");
		tabbedPane2.addTab("产品库", swingUtil.produceImage("Product_documentation.png"), panel4, "产品库");
		JPanel panel5 = createPanel("panel5");
		tabbedPane2.addTab("客户单价表", swingUtil.produceImage("price.png"), panel5, "客户单价表");
		tabbedPane2.addChangeListener(new ChangeListener() {
			@SuppressWarnings({ "static-access" })
			@Override
			public void stateChanged(ChangeEvent e) {
				int inedx =tabbedPane2.getSelectedIndex();
				//开票时刷新客户名称下拉框
				if(inedx == 2){
					customerPriceTextField.setText("");
					customerPriceTextField.setOpaque(false);
					takeJTableModel(null, "panel5");
					// 将组件以及数据模型加入scrollPane，重绘即可刷新
					customerPriceScrollPane.setViewportView(customerPriceTable);
					customerPriceScrollPane.repaint();
					// 设置table自适应
					Double[] columWidthPercent = new Double[] { 0.122, 0.122, 0.135, 0.149, 0.108, 0.108, 0.148, 0.085, 0.0,
							0.0, 0.0 };
					swingUtil.aotoColumnWidth(customerPriceTable, customerPriceScrollPane, columWidthPercent);
				}else if(inedx == 0){
					customerTextField.setText("");
					customerTextField.setOpaque(false);
					takeJTableModel(null, "panel3");
					// 将组件以及数据模型加入scrollPane，重绘即可刷新
					customerScrollPane.setViewportView(customerTable);
					customerScrollPane.repaint();
					// 设置table自适应
					final Double[] columWidthPercent = new Double[] { 0.081, 0.135, 0.135, 0.162, 0.28, 0.092, 0.092 };
					swingUtil.aotoColumnWidth(customerTable, customerScrollPane, columWidthPercent);
				}else if(inedx == 1){
					productTextField.setText("");
					productTextField.setOpaque(false);
					takeJTableModel(null, "panel4");
					// 将组件以及数据模型加入scrollPane，重绘即可刷新
					productScrollPane.setViewportView(productTable);
					productScrollPane.repaint();
					// 设置table自适应
					Double[] columWidthPercent = new Double[] { 0.131, 0.131, 0.131, 0.24, 0.163, 0.092, 0.092, 0.0 };
					swingUtil.aotoColumnWidth(productTable, productScrollPane, columWidthPercent);
				}
			}
		});
		tabbedPane2.setBounds(5, 0, 775, 645);
		panel.add(tabbedPane2);
	}

	/**
	 *
	 * @描述：客户名单JPanel模型
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:01:41
	 * @param panel
	 * @param string
	 */
	private void addPanel3(JPanel panel, final String string) {
		panel.setLayout(null);
		// 创建customerTextField模型
		// 创建带有背景的JTextField
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto.png"); 
			SwingUtil.inputstreamtofile(is,a);
			customerTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		createTextField(customerTextField,false);
		panel.add(customerTextField);
		// 新建按钮
		Rectangle recSearch = new Rectangle(550, 5, 100, 30);
		Rectangle recReload = new Rectangle(660, 5, 100, 30);
		Rectangle recAdd = new Rectangle(10, 5, 120, 30);
		final Double[] columWidthPercent = new Double[] { 0.081, 0.135, 0.135, 0.162, 0.28, 0.092, 0.092 };
		btnNewButton2 = newJButton("重置", swingUtil.produceImage("reload.png"), recReload, customerTextField, this,
				columWidthPercent, string, btnNewButton2);
		btnNewButton1 = newJButton("搜索", swingUtil.produceImage("search.png"), recSearch, customerTextField, this,
				columWidthPercent, string, btnNewButton2);
		btnNewButton = newJButton("新增客户", swingUtil.produceImage("add_user.png"), recAdd, customerTextField, this,
				columWidthPercent, string, btnNewButton2);
		// 添加额外的action事件，为其他手动触发点击事件提供监听
		btnNewButton2.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("asd");
				customerTextField.setText("");
				customerTextField.setOpaque(false);
				takeJTableModel(null, string);
				// 将组件以及数据模型加入scrollPane，重绘即可刷新
				customerScrollPane.setViewportView(customerTable);
				customerScrollPane.repaint();
				// 设置table自适应
				swingUtil.aotoColumnWidth(customerTable, customerScrollPane, columWidthPercent);
			}
		});
		panel.add(btnNewButton2);
		panel.add(btnNewButton1);
		panel.add(btnNewButton);
		// 获取客户表信息
		String cName = customerTextField.getText();
		takeJTableModel(cName, string);
		
		customerScrollPane = new JScrollPane(customerTable);
		customerScrollPane.setBounds(5, 50, 760, 545);
		// 设置水平滚动条默认一直显示
		customerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置垂直滚动条默认一直显示
		customerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(customerScrollPane, BorderLayout.CENTER);
	}
	
	/**
	 *
	 * @描述： 产品库JPanel模型
	 * 
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午8:59:43
	 * @param panel
	 * @param string
	 */
	private void addPanel4(JPanel panel, final String string) {
		panel.setLayout(null);
		// 创建productTextField模型
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto1.png"); 
			SwingUtil.inputstreamtofile(is,a);
			productTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		createTextField(productTextField,false);
		panel.add(productTextField);

		// 新建按钮
		Rectangle recSearch = new Rectangle(550, 5, 100, 30);
		Rectangle recReload = new Rectangle(660, 5, 100, 30);
		Rectangle recAdd = new Rectangle(10, 5, 120, 30);
		// 设置最小列宽（自适应）
		final Double[] columWidthPercent = new Double[] { 0.131, 0.131, 0.131, 0.24, 0.163, 0.092, 0.092, 0.0 };
		btnNewButton5 = newJButton("重置", swingUtil.produceImage("reload.png"), recReload, productTextField, this,
				columWidthPercent, string, btnNewButton5);
		btnNewButton4 = newJButton("搜索", swingUtil.produceImage("search.png"), recSearch, productTextField, this,
				columWidthPercent, string, btnNewButton5);
		btnNewButton3 = newJButton("新增产品", swingUtil.produceImage("addProduct.png"), recAdd, productTextField, this,
				columWidthPercent, string, btnNewButton5);
		// 添加额外的action事件，为其他手动触发点击事件提供监听
		btnNewButton5.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				productTextField.setText("");
				productTextField.setOpaque(false);
				takeJTableModel(null, string);
				// 将组件以及数据模型加入scrollPane，重绘即可刷新
				productScrollPane.setViewportView(productTable);
				productScrollPane.repaint();
				// 设置table自适应
				swingUtil.aotoColumnWidth(productTable, productScrollPane, columWidthPercent);
			}
		});
		panel.add(btnNewButton5);
		panel.add(btnNewButton4);
		panel.add(btnNewButton3);
		// 获取客户表信息
		String cName = productTextField.getText();
		takeJTableModel(cName, string);

		productScrollPane = new JScrollPane(productTable);
		productScrollPane.setBounds(5, 50, 760, 545);
		// 设置水平滚动条默认一直显示
		productScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置垂直滚动条默认一直显示
		productScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(productScrollPane, BorderLayout.CENTER);
	}

	/**
	 *
	 * @描述： 客户单价表JPanel模型
	 * 
	 * @创建人：kitxiao
	 * @创建时间：2016年8月23日下午8:59:43
	 * @param panel
	 * @param string
	 */
	private void addPanel5(JPanel panel, final String string) {
		panel.setLayout(null);
		// 创建customerPriceTextField模型
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("sto2.png"); 
			SwingUtil.inputstreamtofile(is,a);
			customerPriceTextField = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		createTextField(customerPriceTextField,false);
		customerPriceTextField.setBounds(260, 5, 280, 30);
		panel.add(customerPriceTextField);

		// 新建按钮
		Rectangle recSearch = new Rectangle(550, 5, 100, 30);
		Rectangle recReload = new Rectangle(660, 5, 100, 30);
		Rectangle recAdd = new Rectangle(10, 5, 170, 30);
		// 设置最小列宽（自适应）
		final Double[] columWidthPercent = new Double[] { 0.122, 0.122, 0.135, 0.149, 0.108, 0.108, 0.148, 0.085, 0.0,
				0.0, 0.0 };
		btnNewButton8 = newJButton("重置", swingUtil.produceImage("reload.png"), recReload, customerPriceTextField, this,
				columWidthPercent, string, btnNewButton8);
		btnNewButton7 = newJButton("搜索", swingUtil.produceImage("search.png"), recSearch, customerPriceTextField, this,
				columWidthPercent, string, btnNewButton8);
		btnNewButton6 = newJButton("新增客户-产品单价", swingUtil.produceImage("addCustomerPrice.png"), recAdd,
				customerPriceTextField, this, columWidthPercent, string, btnNewButton8);
		// 添加额外的action事件，为其他手动触发点击事件提供监听
		btnNewButton8.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				customerPriceTextField.setText("");
				customerPriceTextField.setOpaque(false);
				takeJTableModel(null, string);
				// 将组件以及数据模型加入scrollPane，重绘即可刷新
				customerPriceScrollPane.setViewportView(customerPriceTable);
				customerPriceScrollPane.repaint();
				// 设置table自适应
				swingUtil.aotoColumnWidth(customerPriceTable, customerPriceScrollPane, columWidthPercent);
			}
		});
		btnNewButton7.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = customerPriceTextField.getText();
				takeJTableModel(cName, string);
				customerPriceScrollPane.setViewportView(customerPriceTable);
				customerPriceScrollPane.repaint();
				swingUtil.aotoColumnWidth(customerPriceTable, customerPriceScrollPane, columWidthPercent);
			}
		});
		panel.add(btnNewButton6);
		panel.add(btnNewButton7);
		panel.add(btnNewButton8);
		// 获取客户表信息
		String cName = customerPriceTextField.getText();
		takeJTableModel(cName, string);

		customerPriceScrollPane = new JScrollPane(customerPriceTable);
		customerPriceScrollPane.setBounds(5, 50, 760, 545);
		// 设置水平滚动条默认一直显示
		customerPriceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 设置垂直滚动条默认一直显示
		customerPriceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(customerPriceScrollPane, BorderLayout.CENTER);
	}

	/**
	 *
	 * @描述：创建TextField带有背景图片模型
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:06:02
	 * @param field
	 */
	private void createTextField(final JTextField field,final boolean isLsdh) {
		field.setBounds(320, 5, 218, 30);
		field.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// 输入框为空字符串时，设置背景透明
				if ("".equals(field.getText())) {
					// 设置文本框透明，显示背景图片
					field.setOpaque(false);
				} else {
					field.setOpaque(true);
				}
				// 设置边框
				field.setBorder(BorderFactory.createLineBorder(new Color(171, 173, 179)));
				field.repaint();
			}

			@Override
			public void focusGained(FocusEvent e) {
				if ("".equals(field.getText()) && isLsdh) {
					Date dt= new Date();
					Long time= dt.getTime();
					field.setText(COM_SUBNAME+"-"+time);
				}
				field.setBorder(BorderFactory.createLineBorder(new Color(97, 164, 207)));
				field.setOpaque(true);
				field.repaint();
			}
		});
		
	}

	/**
	 *
	 * @描述： 新建Table自定义按钮
	 * 
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午7:21:10
	 * @return
	 */
	private JButton newJButton(final String title, ImageIcon imgIcon, Rectangle rec, final JTextField textFieldOld,
			final JFrame frame, final Double[] columWidthPercent, final String panel, final JButton reloadButton) {
		final JButton btnNewButton = new JButton(title);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if ("新增产品".equals(title)) {
					ProductAddDialog dlg = new ProductAddDialog(frame, true, title, 0, reloadButton);
					NoTileDrag.setCanDraged(dlg);
					dlg.setVisible(true);
				} else if ("新增客户".equals(title)) {
					CustomerAddDialog dlg = new CustomerAddDialog(frame, true, title, 0, reloadButton);
					NoTileDrag.setCanDraged(dlg);
					dlg.setVisible(true);
				} else if ("新增客户-产品单价".equals(title)) {
					 CustomerPriceAddDialog dlg = new CustomerPriceAddDialog(frame, true,reloadButton);
					 NoTileDrag.setCanDraged(dlg);
					 dlg.setVisible(true);
				} else if ("搜索".equals(title)) {
					String cName = textFieldOld.getText();
					takeJTableModel(cName, panel);
					if ("panel3".equals(panel)) {
						customerScrollPane.setViewportView(customerTable);
						customerScrollPane.repaint();
						swingUtil.aotoColumnWidth(customerTable, customerScrollPane, columWidthPercent);
					} else if ("panel4".equals(panel)) {
						productScrollPane.setViewportView(productTable);
						productScrollPane.repaint();
						swingUtil.aotoColumnWidth(productTable, productScrollPane, columWidthPercent);
					} else if ("panel5".equals(panel)) {
						customerPriceScrollPane.setViewportView(customerPriceTable);
						customerPriceScrollPane.repaint();
						swingUtil.aotoColumnWidth(customerPriceTable, customerPriceScrollPane, columWidthPercent);
					}
				} else if ("重置".equals(title)) {
					textFieldOld.setText("");
					textFieldOld.setOpaque(false);
				}
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				// 设置鼠标进入变成小手
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNewButton.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				btnNewButton.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(imgIcon);
		btnNewButton.setBorder(new RoundBorder());
		btnNewButton.setBounds(rec);

		return btnNewButton;
	}

	/**
	 *
	 * @描述：新增Table数据模型
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:06:53
	 * @param cName
	 * @param panel
	 */
	@SuppressWarnings("rawtypes")
	private void takeJTableModel(String cName, String panel) {
		if ("panel3".equals(panel)) {
			CustomerCtr customerCtr = new CustomerCtr();
			Customer customer = new Customer();
			if (cName != null && "".equals(cName)) {
				customer.setCname(null);
			} else {
				customer.setCname(cName);
			}
			Vector data = customerCtr.selectCustomerList(customer);
			CustomerJtablePaint CustomerJtablePaint = new CustomerJtablePaint();
			customerTable = CustomerJtablePaint.customerJtablePaint(customerTable, data, this, customerScrollPane,
					btnNewButton2);
		} else if ("panel4".equals(panel)) {
			ProductCtr productCtr = new ProductCtr();
			Product product = new Product();
			if (cName != null && "".equals(cName)) {
				product.setPname(null);
			} else {
				product.setPname(cName);
			}
			Vector data = productCtr.selectProductList(product);
			ProductJtablePaint ProductJtablePaint = new ProductJtablePaint();
			productTable = ProductJtablePaint.productJtablePaint(productTable, data, this, productScrollPane,
					btnNewButton5);
		} else if ("panel5".equals(panel)) {
			CustomerPriceCtr customerPriceCtr = new CustomerPriceCtr();
			CustomerProRelVo customerProRelVo = new CustomerProRelVo();
			if (cName != null && "".equals(cName)) {
				customerProRelVo.setFieldLike(null);
			} else {
				customerProRelVo.setFieldLike(cName);
			}
			Vector data = customerPriceCtr.selectCustomerPriceList(customerProRelVo);
			CustomerPriceJtablePaint customerPriceJtablePaint = new CustomerPriceJtablePaint();
			customerPriceTable = customerPriceJtablePaint.customerPriceJtablePaint(customerPriceTable, data, this,
					customerPriceScrollPane, btnNewButton7);
		}
	}
	
	/**
	 *
	 * @描述： 所有组件自适应Jframe的大小
	 * 
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:30:52
	 * @param state
	 */
	@SuppressWarnings("static-access")
	private void autoAdaterFrame(WindowEvent state) {
		tabbedPane1.setBounds(2, 38, state.getWindow().getWidth() - 8, state.getWindow().getHeight() - 50);
		tabbedPane2.setBounds(5, 0, state.getWindow().getWidth() - 125, state.getWindow().getHeight() - 60);
		customerScrollPane.setBounds(5, 50, state.getWindow().getWidth() - 140, state.getWindow().getHeight() - 150);
		productScrollPane.setBounds(5, 50, state.getWindow().getWidth() - 140, state.getWindow().getHeight() - 150);
		customerPriceScrollPane.setBounds(5, 50, state.getWindow().getWidth() - 140, state.getWindow().getHeight() - 150);
		Double[] CColumWidthPercent = new Double[] { 0.081, 0.135, 0.135, 0.162, 0.28, 0.092, 0.092 };
		swingUtil.aotoColumnWidth(customerTable, customerScrollPane, CColumWidthPercent);
		Double[] PColumWidthPercent = new Double[] { 0.131, 0.131, 0.131, 0.24, 0.163, 0.092, 0.092, 0.0 };
		swingUtil.aotoColumnWidth(productTable, productScrollPane, PColumWidthPercent);
		Double[] cPColumWidthPercent = new Double[] { 0.122, 0.122, 0.135, 0.149, 0.108, 0.108, 0.148, 0.085, 0.0, 0.0, 0.0 };
		swingUtil.aotoColumnWidth(customerPriceTable, customerPriceScrollPane, cPColumWidthPercent);
		
		invoiceProScrollPane.setBounds(15, 170, state.getWindow().getWidth() - 140, 232);
		Double[] invoiceProWidthPercent = new Double[] { 0.154,0.2114,0.1057,0.0925,0.066,0.2378,0.132,0.0};
		invoicePro1ScrollPane.setBounds(15, 375, state.getWindow().getWidth() - 140, 89);
		swingUtil.aotoColumnWidth(invoiceProTable, invoiceProScrollPane, invoiceProWidthPercent);
		Double[] invoicePro1WidthPercent = new Double[] { 0.154,0.2114,0.1057,0.0925,0.066,0.2378,0.132,0.0};
		swingUtil.aotoColumnWidth(invoicePro1Table, invoicePro1ScrollPane, invoicePro1WidthPercent);
		
		btnExcel.setBounds(state.getWindow().getWidth()-250, state.getWindow().getHeight()-95, 120, 30);
		invoiceScrollPane.setBounds(10, 80, state.getWindow().getWidth() - 140, state.getWindow().getHeight() - 180);

		btnNewButton4.setBounds(state.getWindow().getWidth() - 360, 5, 100, 30);
		btnNewButton5.setBounds(state.getWindow().getWidth() - 250, 5, 100, 30);
		btnNewButton1.setBounds(state.getWindow().getWidth() - 360, 5, 100, 30);
		btnNewButton2.setBounds(state.getWindow().getWidth() - 250, 5, 100, 30);
		btnNewButton7.setBounds(state.getWindow().getWidth() - 360, 5, 100, 30);
		btnNewButton8.setBounds(state.getWindow().getWidth() - 250, 5, 100, 30);
		customerTextField.setBounds(state.getWindow().getWidth() - 600, 5, 218, 30);
		productTextField.setBounds(state.getWindow().getWidth() - 600, 5, 218, 30);
		customerPriceTextField.setBounds(state.getWindow().getWidth() - 660, 5, 280, 30);
		
		label1.setBounds(state.getWindow().getWidth()/2-150, 10, 200, 40);
		label2.setBounds(state.getWindow().getWidth()/2-90, 35, 180, 40);
		separator.setBounds(15, 70, state.getWindow().getWidth()-140, 2);
		btnNewInvButton.setBounds(state.getWindow().getWidth()-275, 130, 150, 30);
		beforePage.setBounds(state.getWindow().getWidth()-255, 470, 30, 25);
		labelbf.setBounds(state.getWindow().getWidth()-215, 470, 30, 30);
		labelaf.setBounds(state.getWindow().getWidth()-195, 470, 30, 30);
		labelys.setBounds(state.getWindow().getWidth()-175, 470, 30, 30);
		afterPage.setBounds(state.getWindow().getWidth()-155, 470, 30, 25);
		
		labelkxlx.setBounds(state.getWindow().getWidth()-310, 500, 60, 30);
		comboBox1.setBounds(state.getWindow().getWidth()-250, 500, 125, 25);
		labelkqzm.setBounds(state.getWindow().getWidth()-310, 530, 60, 30);
		qzmTextField.setBounds(state.getWindow().getWidth()-250, 530, 125, 25);
		
		invReloadButton.setBounds(state.getWindow().getWidth()-370, 597, 120, 30);
		invConfirmButton.setBounds(state.getWindow().getWidth()-245, 597, 120, 30);
		addKpProductRecord(false,false);
	}
	
	/**
	 *
	 * @描述：初始化鼠标事件
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:07:11
	 */
	private void initListener() {
		// 窗体事件，用于拖动窗体位置
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 记录下鼠标点击的那一刻坐标
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		// 窗体大小改变事件，各组件自适应
		this.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent state) {
				autoAdaterFrame(state);
			};
		});
		this.addMouseMotionListener(new MouseAdapter() {
			// 窗体无边框后，可拖动
			@Override
			public void mouseDragged(MouseEvent e) {
				// 右
				if (getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
					setSize(e.getX(), getHeight());
					content.fixedButtonPoint();
					// 左
				} else if (getCursor().getType() == Cursor.W_RESIZE_CURSOR) {
					setSize(getWidth() - e.getX(), getHeight());
					setLocation(getLocationOnScreen().x + e.getX(), getLocationOnScreen().y);
					content.fixedButtonPoint();
					// 下
				} else if (getCursor().getType() == Cursor.S_RESIZE_CURSOR) {
					setSize(getWidth(), e.getY());
					// 上
				} else if (getCursor().getType() == Cursor.N_RESIZE_CURSOR) {
					setSize(getWidth(), getHeight() - e.getY());
					setLocation(getLocationOnScreen().x, getLocationOnScreen().y + e.getY());
				} else {
					Point p = getLocation();
					setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
				}
			}
			// ---------------------------------------此处留着以后用---------------------------------------------
			// 窗体无边框后，大小可变
			// @Override
			// public void mouseMoved(MouseEvent e) {
			// // 根据边界和鼠标坐标的对比来决定鼠标的样式
			// if (e.getX() == 0 || e.getX() == 1 || e.getX() == 2
			// || e.getX() == 3 || e.getX() == 4 || e.getX() == 5) {
			// setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			//
			// } else if (e.getX() == getWidth() || e.getX() == getWidth() - 1
			// || e.getX() == getWidth() - 2
			// || e.getX() == getWidth() - 3 || e.getX() == getWidth() - 4 ||
			// e.getX() == getWidth() - 5) {
			// setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			//
			// } else if (e.getY() == 0 || e.getY() == 1 || e.getY() == 2
			// || e.getY() == 3 || e.getY() == 4 || e.getY() == 5) {
			// setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			//
			// } else if (e.getY() == getHeight() || e.getY() == getHeight() - 1
			// || e.getY() == getHeight() - 2
			// || e.getY() == getHeight() - 3 || e.getY() == getHeight() - 4 ||
			// e.getY() == getHeight() - 5) {
			// setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			//
			// } else {
			// setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			// }
			// }
			// ---------------------------------------此处留着以后用---------------------------------------------
		});
	}

	/**
	 *
	 * @描述： 主方法入口
	 * 
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:08:28
	 * @param args
	 */
	public static void main(String[] args) {
		// ----------------------------------------------设置整个界面为BeautyEye L&F--------------------------------------------------
		//		try {
		//			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		//		} catch (Exception e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}
		// ----------------------------------------------设置整个界面为BeautyEye L&F--------------------------------------------------
		// ----------------------------------------------设置整个界面为windows风格--------------------------------------------------
		MainInterface mainInterface = new MainInterface();
		String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(windows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(mainInterface);
		// ----------------------------------------------设置整个界面为windows风格--------------------------------------------------
		// ---------------------------------------------Substance.jar美化界面专用---------------------------------------------------
		// try {
		// // 设置外观
		// UIManager.setLookAndFeel(
		// new SubstanceBusinessBlackSteelLookAndFeel());
		// JFrame.setDefaultLookAndFeelDecorated(true);
		// // 设置主题
		// SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
		// // 设置按钮外观
		// SubstanceLookAndFeel
		// .setCurrentButtonShaper(new ClassicButtonShaper());
		// // 设置水印
		// SubstanceLookAndFeel
		// .setCurrentWatermark(new SubstanceBinaryWatermark());
		// // 设置边框
		// SubstanceLookAndFeel
		// .setCurrentBorderPainter(new StandardBorderPainter());
		// // 设置渐变渲染
		// SubstanceLookAndFeel
		// .setCurrentGradientPainter(new StandardGradientPainter());
		// // 设置标题
		// SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }
		// ---------------------------------------------Substance.jar美化界面专用---------------------------------------------------
		mainInterface.setVisible(true);
		// 设置居屏幕中心
		mainInterface.setLocationRelativeTo(null);

	}
}
