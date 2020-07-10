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

import entity.Customer;
import entity.Product;
import util.SwingUtil;

/**
 *
 * @描述：新增，编辑客户弹出对话框内部JPanel模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:25:38
 */
public class CustomerPriceAddDialogPanel extends JPanel {
	private static final long serialVersionUID = -7941326473117007846L;

	private ImageIcon imageIcon;
	private SwingUtil swingUtil = new SwingUtil();

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;

	/**
	 * 新增，编辑客户弹出对话框内部JPanel模型构造方法
	 */
	CustomerPriceAddDialogPanel(JLabel exitButton, JButton ok, JButton cancel, DIYComboBox<String> comboBox1,
			DIYComboBox<String> comboBox2, List<Customer> cDate, List<Product> pDate, JTextField field1,
			JTextField field2, JTextField field3, JLabel labelGG, JLabel labelUN) {
		initGUI(exitButton, ok, cancel, comboBox1, comboBox2, cDate, pDate, field1, field2, field3, labelGG, labelUN);
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
	@SuppressWarnings({ "static-access" })
	private void initGUI(JLabel exitButton, JButton ok, JButton cancel, DIYComboBox<String> comboBox1,
			DIYComboBox<String> comboBox2, List<Customer> cDate, List<Product> pDate, JTextField field1,
			JTextField field2, JTextField field3, JLabel labelGG, JLabel labelUN) {
		setLayout(null);

		exitButton.setBounds(306, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);

		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 32, 340, 2);
		add(separator);

		label1 = swingUtil.createDialogLabel("产品代码:", 60);
		label2 = swingUtil.createDialogLabel("产品名称:", 110);
		label4 = swingUtil.createDialogLabel("产品规格:", 160);
		label3 = swingUtil.createDialogLabel("客户名称:", 210);
		label5 = swingUtil.createDialogLabel("设定单价:", 260);
		labelGG.setBounds(270, 160, 70, 40);
		labelUN.setBounds(270, 260, 70, 40);
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		add(labelGG);
		add(labelUN);
		comboBox1.setBounds(110, 70, 220, 25);
		comboBox2.setBounds(110, 220, 220, 25);
		add(comboBox1);
		add(comboBox2);
		field1.setBounds(110, 120, 220, 25);
		field2.setBounds(110, 170, 150, 25);
		field3.setBounds(110, 270, 150, 25);
		field1.setEditable(false);
		field2.setEditable(false);
		swingUtil.setTextFieldBorder(field3);
		add(field1);
		add(field2);
		add(field3);
		add(ok);
		add(cancel);
		ok.setBounds(145, 340, 85, 30);
		ok.setIcon(swingUtil.produceImage("Determine.png"));
		cancel.setBounds(235, 340, 85, 30);
		cancel.setIcon(swingUtil.produceImage("cancel.png"));

		JLabel lblNewLabel = new JLabel(" 新增客户-产品单价");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(swingUtil.produceImage("addHistoryPrice.png"));
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setBounds(15, 7, 216, 24);
		add(lblNewLabel);

	}

}