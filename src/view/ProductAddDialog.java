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
import web.ProductCtr;

/**
 * 
 * @描述：新增，编辑产品弹出对话框模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:10:48
 */
public class ProductAddDialog extends Dialog {
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

	ProductAddDialog(final JFrame parent, boolean modal, final String title, final int sid, final JButton reloadButton) {
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
		ProductAddDialogPanel dialogPanel = new ProductAddDialogPanel(field1, field2, field3, field4, field5,field6,
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
				ProductCtr productCtr = new ProductCtr();
				String code=field1.getText();
				String pName=field2.getText();
				String pType=field3.getText();
				String standard=field4.getText();
				String standardUnit=field6.getText();
				String unit=field5.getText();
				//数据校验
				if(SwingUtil.isEmpty(code)){
					dlg=new OperateDBFailMDialog(parent,true,"产品代码不能为空！");
				}else if(SwingUtil.isEmpty(pName)){
					dlg=new OperateDBFailMDialog(parent,true,"产品名称不能为空！");
				}else if(SwingUtil.isEmpty(pType)){
					dlg=new OperateDBFailMDialog(parent,true,"产品类别不能为空！");
				}else if(SwingUtil.isEmpty(standard)){
					dlg=new OperateDBFailMDialog(parent,true,"产品规格不能为空！");
				}else if(SwingUtil.isEmpty(standardUnit)){
					dlg=new OperateDBFailMDialog(parent,true,"规格单位不能为空！");
				}else if(SwingUtil.isEmpty(unit)){
					dlg=new OperateDBFailMDialog(parent,true,"单位不能为空！");
				}else if(!SwingUtil.isDecimal(standard)){
					dlg=new OperateDBFailMDialog(parent,true,"产品规格格式不正确！");
				}else{
					if ("编辑产品信息".equals(title)) {
							i=productCtr.updateOneProductBySid(code,pName,pType,SwingUtil.strTurnBig(standard),standardUnit,unit,sid);
					} else if("新增产品".equals(title)){
							i=productCtr.insertProduct(code,pName,pType,SwingUtil.strTurnBig(standard),standardUnit,unit);
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
