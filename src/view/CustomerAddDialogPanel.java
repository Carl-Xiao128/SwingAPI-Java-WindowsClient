package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import dao.CustomerService;
import entity.Customer;
import util.SwingUtil;

/**
 *
 * @描述：新增，编辑客户弹出对话框内部JPanel模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:25:38
 */
public class CustomerAddDialogPanel extends JPanel {

	private static final long serialVersionUID = 8452897757639863500L;
	
	private ImageIcon imageIcon;
	private SwingUtil swingUtil = new SwingUtil();

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	
	/**
	 * 新增，编辑客户弹出对话框内部JPanel模型构造方法
	 */
	CustomerAddDialogPanel(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5,
			JTextField field6,JLabel exitButton, JButton ok, JButton cancel, String title, int sid) {
		initGUI(field1, field2, field3, field4, field5,field6, exitButton, ok, cancel, title, sid);
		// 给imageIcon指定默认图片
		imageIcon = swingUtil.produceImage("DialogBack.png");
	}
	
	/** 
	 * 重写画笔方法
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//设置透明度
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g2d.setComposite(alphaComposite);// 透明度
		//绘制整个JPanel背景
		g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
	}

	
	/**
	 * 初始化JPanel
	 */
	@SuppressWarnings("static-access")
	private void initGUI(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5,
			JTextField field6,JLabel exitButton, JButton ok, JButton cancel, String title, int sid) {
		setLayout(null);

		exitButton.setBounds(306, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);

		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 32, 340, 2);
		add(separator);

		label1 = swingUtil.createDialogLabel("客户名称:", 60);
		label2 = swingUtil.createDialogLabel("客户电话:", 110);
		label3 = swingUtil.createDialogLabel("负  责  人:", 160);
		label6 = swingUtil.createDialogLabel("区域经理:", 210);
		label4 = swingUtil.createDialogLabel("区       域:", 255);
		label5 = swingUtil.createDialogLabel("地       址:", 300);
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		add(label6);
		field1.setBounds(140, 70, 180, 25);
		field2.setBounds(140, 120, 180, 25);
		field3.setBounds(140, 170, 180, 25);
		field6.setBounds(140, 220, 180, 25);
		field4.setBounds(140, 260, 180, 25);
		field5.setBounds(140, 300, 180, 25);
		swingUtil.setTextFieldBorder(field1);
		swingUtil.setTextFieldBorder(field2);
		swingUtil.setTextFieldBorder(field3);
		swingUtil.setTextFieldBorder(field4);
		swingUtil.setTextFieldBorder(field5);
		swingUtil.setTextFieldBorder(field6);
		add(field1);
		add(field2);
		add(field3);
		add(field4);
		add(field5);
		add(field6);
		add(ok);
		add(cancel);
		ok.setBounds(145, 340, 85, 30);
		ok.setIcon(swingUtil.produceImage("Determine.png"));
		cancel.setBounds(235, 340, 85, 30);
		cancel.setIcon(swingUtil.produceImage("cancel.png"));

		ImageIcon icon = new ImageIcon();
		if ("新增客户".equals(title)) {
			icon = swingUtil.produceImage("addUser.png");
		} else if ("编辑客户信息".equals(title)) {
			icon = swingUtil.produceImage("editCustomer.png");
			//获取该条客户信息到每个输入框中
			CustomerService customerService = new CustomerService();
			Customer customer = new Customer();
			customer.setSid(sid);
			List<Customer> cList = customerService.getCustomerList(customer);
			customer = cList.get(0);
			field1.setText(customer.getCname());
			field2.setText(customer.getPhone());
			field3.setText(customer.getPerson());
			field4.setText(customer.getArea());
			field5.setText(customer.getAddress());
			field6.setText(customer.getAreaCharge());
		}
		JLabel lblNewLabel = new JLabel(" " + title);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(icon);
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setBounds(15, 7, 216, 24);
		add(lblNewLabel);

	}

}