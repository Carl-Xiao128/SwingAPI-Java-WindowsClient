package view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import util.SwingUtil;
import web.CustomerPriceCtr;

/**
 *
 * @描述：查看历史价格弹出对话框内部JPanel模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:25:38
 */
public class HistoryPriceAddDialogPanel extends JPanel {
	private static final long serialVersionUID = -7941326473117007846L;

	private ImageIcon imageIcon;
	private SwingUtil swingUtil = new SwingUtil();

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JTextField field1;
	private JTextField field2;
	private HistoryPriceJtablePaint historyPriceJtablePaint;
	private Date startDate;
	private Date endDate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private CustomerPriceCtr customerPriceCtr;
	private JTable historyPriceTable;
	private JScrollPane historyPriceScrollPane;
	private JButton reload = new JButton("重置");
	private JButton search = new JButton("搜索");

	/**
	 * 查看历史价格弹出对话框内部JPanel模型构造方法
	 */
	HistoryPriceAddDialogPanel(JFrame frame, JLabel exitButton, String title, int sid, String productSid,
			String customerSid) {
		initGUI(frame, exitButton, title, sid, productSid, customerSid);
		// 给imageIcon指定默认图片
		imageIcon = swingUtil.produceImage("DialogBack.png");
	}

	/**
	 * 重写画笔方法
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// 设置透明度
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g2d.setComposite(alphaComposite);// 透明度
		// 绘制整个JPanel背景
		g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
	}

	/**
	 * 初始化JPanel
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	private void initGUI(final JFrame frame, JLabel exitButton, String title, int sid, final String productSid,
			final String customerSid) {
		setLayout(null);

		exitButton.setBounds(306, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);

		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 32, 340, 2);
		add(separator);

		// 获取该条客户产品信息到每个label中
		customerPriceCtr = new CustomerPriceCtr();
		Vector allData = customerPriceCtr.selectHistoryPriceList(productSid, customerSid);
		String code = allData.get(0).toString();
		String cName = allData.get(1).toString();
		String pName = allData.get(2).toString();

		label1 = swingUtil.createDialogLabel1("产品代码:", 25, 40, 65, 40, "0");
		label2 = swingUtil.createDialogLabel1(code, 95, 40, 80, 40, "1");
		label3 = swingUtil.createDialogLabel1("客户名称:", 180, 40, 65, 40, "0");
		label4 = swingUtil.createDialogLabel1(cName, 250, 40, 80, 40, "1");
		label5 = swingUtil.createDialogLabel1("产品名称:", 25, 70, 65, 40, "0");
		label6 = swingUtil.createDialogLabel1(pName, 95, 70, 150, 40, "1");
		label7 = swingUtil.createDialogLabel1("起始日期:", 25, 100, 65, 40, "0");
		label8 = swingUtil.createDialogLabel1("结束日期:", 185, 100, 65, 40, "0");
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		add(label6);
		add(label7);
		add(label8);
		// 给日期Field设置背景图片
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("dateBackground.png"); 
			SwingUtil.inputstreamtofile(is,a);
			field1 = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			File a=new File("cache");
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("dateBackground.png"); 
			SwingUtil.inputstreamtofile(is,a);
			field2 = new JTextfieldImg(a);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 设置Field透明，以看见背景图片
		field1.setOpaque(false);
		field2.setOpaque(false);
		field1.setBounds(95, 110, 80, 25);
		field2.setBounds(255, 110, 80, 25);
		// 添加日期控件
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(field1);
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser2.register(field2);

		add(field1, BorderLayout.NORTH);
		add(field2, BorderLayout.NORTH);

		reload.setBounds(25, 360, 100, 30);
		reload.setIcon(swingUtil.produceImage("reload.png"));
		search.setBounds(235, 360, 100, 30);
		search.setIcon(swingUtil.produceImage("search.png"));
		reload.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				field1.setText(null);
				field2.setText(null);
				paintTable(productSid, customerSid, null, null);
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				reload.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				reload.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int i = timeCheck(frame);
				if (i < 1) {
					paintTable(productSid, customerSid, startDate, endDate);
				}
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				search.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				search.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		add(reload);
		add(search);

		ImageIcon icon = swingUtil.produceImage("findHistoryPrice.png");
		JLabel lblNewLabel = new JLabel(" " + title);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(icon);
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setBounds(15, 7, 216, 24);
		add(lblNewLabel);

		// 绘制产品价格历史table
		historyPriceJtablePaint = new HistoryPriceJtablePaint();
		Vector date = customerPriceCtr.selectHistoryPriceList(productSid, customerSid, null, null);
		historyPriceTable = historyPriceJtablePaint.historyPriceJtablePaint(historyPriceTable, date);

		historyPriceScrollPane = new JScrollPane(historyPriceTable);
		historyPriceScrollPane.setBounds(25, 140, 310, 210);
		// 设置垂直滚动条默认一直显示
		historyPriceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		historyPriceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(historyPriceScrollPane, BorderLayout.CENTER);

	}

	/**
	 *
	 * @描述：检查起始结束时间的科学性
	 * @创建人：kitxiao
	 * @创建时间：2016年8月24日下午8:03:21
	 * @param frame
	 */
	private int timeCheck(JFrame frame) {
		Dialog dlg = null;
		startDate = null;
		endDate = null;
		String start = field1.getText();
		String end = field2.getText();

		// 校验日期格式
		if (!"".equals(start) && !SwingUtil.isDateFormat(start) || !"".equals(end) && !SwingUtil.isDateFormat(end)) {
			dlg = new OperateDBFailMDialog(frame, true, "日期格式不正确！");
			dlg.setVisible(true);
			return 1;
		}
		// 结束日期加一天，也就是包括结束日期
		try {
			Calendar c = Calendar.getInstance();
			if (!"".equals(start)) {
				startDate = sdf.parse(start);
			}
			if (!"".equals(end)) {
				endDate = sdf.parse(end);
				c.setTime(endDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				endDate = c.getTime();
			}

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int i = -1;
		// 判断起始结束日期的科学性
		if (startDate != null && endDate != null) {
			i = startDate.compareTo(endDate);
		}
		if (i >= 0) {
			dlg = new OperateDBFailMDialog(frame, true, "时间不科学！请重新输入");
			dlg.setVisible(true);
			return 1;
		}
		return 0;
	}

	/**
	 *
	 * @描述：重绘historyTable
	 * @创建人：kitxiao
	 * @创建时间：2016年8月24日下午10:34:16
	 */
	@SuppressWarnings("rawtypes")
	private void paintTable(String productSid, String customerSid, Date startDate, Date endDate) {
		Vector date = customerPriceCtr.selectHistoryPriceList(productSid, customerSid, startDate, endDate);
		historyPriceTable = historyPriceJtablePaint.historyPriceJtablePaint(historyPriceTable, date);
		historyPriceScrollPane.setViewportView(historyPriceTable);
		historyPriceScrollPane.repaint();
	}

}