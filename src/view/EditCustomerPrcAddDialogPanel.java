package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

import entity.vo.CustomerProRelVo;
import util.SwingUtil;
import web.CustomerPriceCtr;

/**
 *
 * @描述：编辑产品价格弹出对话框内部JPanel模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:25:38
 */
public class EditCustomerPrcAddDialogPanel extends JPanel {
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
	private JLabel label9;
	private JLabel label10;
	private JButton findHistoryPrc =new JButton("查看历史单价");
	
	/**
	 * 编辑产品价格弹出对话框内部JPanel模型构造方法
	 */
	EditCustomerPrcAddDialogPanel(JTextField field1,
			JLabel exitButton, JButton ok, JButton cancel, String title, int sid,String productSid,String customerSid,JFrame frame) {
		initGUI(field1, exitButton, ok, cancel, title, sid,productSid,customerSid,frame);
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
	private void initGUI(JTextField field1,JLabel exitButton, JButton ok, JButton cancel, String title, final int sid,final String productSid,
			final String customerSid,final JFrame frame) {
		setLayout(null);

		exitButton.setBounds(306, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);

		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 32, 340, 2);
		add(separator);
		
		//根据sid获取此条客户产品数据
		CustomerPriceCtr customerPriceCtr =new CustomerPriceCtr();
		CustomerProRelVo customerProRelVo=customerPriceCtr.selectCusProRelOne(sid);
		String code=customerProRelVo.getCode();
		String cName=customerProRelVo.getCname();
		String pName=customerProRelVo.getPname();
		String stardard=customerProRelVo.getStandard()+" "+customerProRelVo.getUnit()+"/"+customerProRelVo.getStandardUnit();
		String unit ="元/"+customerProRelVo.getUnit();
		String unitPrice= customerProRelVo.getUnitPrice().toString();

		label1 = swingUtil.createDialogLabel1("产品代码:", 25, 60, 65, 40, "0");
		label2 = swingUtil.createDialogLabel1(code, 95, 60, 150, 40, "1");
		label5 = swingUtil.createDialogLabel1("产品名称:", 25, 110, 65, 40, "0");
		label6 = swingUtil.createDialogLabel1(pName, 95, 110, 150, 40, "1");
		label7 = swingUtil.createDialogLabel1("产品规格:", 25, 160, 65, 40, "0");
		label8 = swingUtil.createDialogLabel1(stardard, 95, 160, 150, 40, "1");
		label3 = swingUtil.createDialogLabel1("客户名称:", 25, 210, 65, 40, "0");
		label4 = swingUtil.createDialogLabel1(cName, 95, 210, 150, 40, "1");
		label9 = swingUtil.createDialogLabel1("设定单价:", 25, 250, 65, 40, "0");
		label10 = swingUtil.createDialogLabel1(unit, 280, 250, 65, 40, "1");
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		add(label6);
		add(label7);
		add(label8);
		add(label9);
		add(label10);
		field1.setBounds(95, 260, 180, 25);
		swingUtil.setTextFieldBorder(field1);
		field1.setText(unitPrice);
		add(field1);
		ok.setBounds(165, 340, 85, 30);
		ok.setIcon(swingUtil.produceImage("Determine.png"));
		cancel.setBounds(255, 340, 85, 30);
		cancel.setIcon(swingUtil.produceImage("cancel.png"));
		//查看历史价格按钮
		findHistoryPrc.setBounds(20, 340, 130, 30);
		findHistoryPrc.setIcon(swingUtil.produceImage("findHistoryPrc.png"));
		
		findHistoryPrc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				//新建Dialog
				Dialog dlg = new HistoryPriceAddDialog(frame, true,"查看历史单价",sid,productSid,customerSid);
				NoTileDrag.setCanDraged(dlg);
        		dlg.setVisible(true);
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				findHistoryPrc.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				findHistoryPrc.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		add(ok);
		add(cancel);
		add(findHistoryPrc);

		JLabel lblNewLabel = new JLabel(" " + title);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(swingUtil.produceImage("editCustomerPrc.png"));
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setBounds(15, 7, 216, 24);
		add(lblNewLabel);

	}

}