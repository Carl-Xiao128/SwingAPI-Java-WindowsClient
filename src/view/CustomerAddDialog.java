package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import util.SwingUtil;
import web.CustomerCtr;

/**
 * 
 * @描述：新增，编辑客户弹出对话框模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:10:48
 */
public class CustomerAddDialog extends Dialog {
	private static final long serialVersionUID = -4282540346271749050L;

	JButton ok = new JButton("确定");
	JButton cancel = new JButton("取消");
	JLabel exitButton = new JLabel();
	SwingUtil sd = new SwingUtil();
	JTextField field1 = new JTextField();
	JTextField field2 = new JTextField();
	JTextField field3 = new JTextField();
	JTextField field4 = new JTextField();
	JTextField field5 = new JTextField();
	JTextField field6 = new JTextField();

	/**
	 * 新增，编辑客户弹出对话框模型构造方法
	 */
	CustomerAddDialog(final JFrame parent, boolean modal, final String title, final int sid, final JButton reloadButton) {
		super(parent, modal);
		setTitle("自定义对话框");
		setUndecorated(true);
		setSize(350, 400);
		setResizable(false);
		// 设置对话框位置为跟随JFrame中心
		Point point = parent.getLocation();
		setLocation((int) point.getX() + parent.getWidth() / 2 - 175,
				(int) point.getY() + parent.getHeight() / 2 - 200);
		// 新建JPanel
		CustomerAddDialogPanel dialogPanel = new CustomerAddDialogPanel(field1, field2, field3, field4, field5,field6,
				exitButton, ok, cancel, title, sid);
		Border border = BorderFactory.createCompoundBorder(ShadowBorder.newBuilder().shadowAlpha(0.7f).center().build(),
				BorderFactory.createLineBorder(Color.white));
		dialogPanel.setBorder(border);
		add(dialogPanel);
		// 按钮鼠标事件
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(sd.produceImage("close.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(sd.produceImage("close_active.png"));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Dialog dlg=null;
				int i=0;
				CustomerCtr customerCtr = new CustomerCtr();
				String cName=field1.getText();
				String phone=field2.getText();
				String person=field3.getText();
				String area=field4.getText();
				String address=field5.getText();
				String areaCharge=field6.getText();
				//校验数据
				if(SwingUtil.isEmpty(cName)){
					dlg=new OperateDBFailMDialog(parent,true,"客户名称不能为空！");
				}else if(SwingUtil.isEmpty(phone)){
					dlg=new OperateDBFailMDialog(parent,true,"客户电话不能为空！");
				}else if(SwingUtil.isEmpty(person)){
					dlg=new OperateDBFailMDialog(parent,true,"业务员不能为空！");
				}else if(SwingUtil.isEmpty(areaCharge)){
					dlg=new OperateDBFailMDialog(parent,true,"区域经理不能为空！");
				}else if(SwingUtil.isEmpty(area)){
					dlg=new OperateDBFailMDialog(parent,true,"区域不能为空！");
				}else if(SwingUtil.isEmpty(address)){
					dlg=new OperateDBFailMDialog(parent,true,"地址不能为空！");
				}else if(!SwingUtil.isPhone(phone) && !SwingUtil.isMobile(phone)){
					dlg=new OperateDBFailMDialog(parent,true,"客户电话格式不正确！");
				}else{
					if ("编辑客户信息".equals(title)) {
						i=customerCtr.updateOneCustomerBySid(cName,phone,person,areaCharge,area,address, sid);
					} else  if("新增客户".equals(title)){
						i=customerCtr.insertCustomer(cName,phone,person,areaCharge,area,address);
					}
					//判断是否操作数据库 成功
					if(i<1){
						dlg=new OperateDBFailMDialog(parent,true,"操作失败！");
					}else{
						dispose();
						reloadButton.doClick();
					}
				}
				if(dlg!=null){
					dlg.setVisible(true);
				}
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ok.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				ok.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				dispose();
			}

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				cancel.setBackground(UIManager.getColor("Button.shadow"));
			}

			public void mouseExited(MouseEvent e) {// 鼠标移除
				cancel.setBackground(new Color(255, 255, 255));
				setCursor(Cursor.getDefaultCursor());
			}
		});

	}

}
