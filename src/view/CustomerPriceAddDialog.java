package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import entity.Customer;
import entity.Product;
import util.SwingUtil;
import web.CustomerPriceCtr;

/**
 * 
 * @描述：新增客户-产品单价弹出对话框模型
 * @创建人：kitxiao
 * @创建时间：2016年8月25日上午12:10:48
 */
public class CustomerPriceAddDialog extends Dialog {
	private static final long serialVersionUID = -4282540346271749050L;

	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");
	private JLabel exitButton = new JLabel();
	private SwingUtil sd = new SwingUtil();
	private DIYComboBox<String> comboBox1;
	private DIYComboBox<String> comboBox2;
	private JTextField field1 = new JTextField();
	private JTextField field2 = new JTextField();
	private JTextField field3 = new JTextField();
	private JTextField fieldCSid = new JTextField();
	private JTextField fieldPSid = new JTextField();
	private JLabel labelGG = new JLabel();
	private JLabel labelUN = new JLabel();
	private List<Product> pDate;
	private List<Customer> cDate;

	/**
	 * 新增客户-产品单价弹出对话框模型构造方法
	 */
	CustomerPriceAddDialog(final JFrame parent, boolean modal, final JButton reloadButton) {
		super(parent, modal);
		setTitle("自定义对话框");
		setUndecorated(true);
		setSize(350, 400);
		setResizable(false);
		// 设置对话框位置为跟随JFrame中心
		Point point = parent.getLocation();
		setLocation((int) point.getX() + parent.getWidth() / 2 - 175,
				(int) point.getY() + parent.getHeight() / 2 - 200);
		// 获取下拉框的值
		CustomerPriceCtr CustomerPriceCtr = new CustomerPriceCtr();
		cDate = CustomerPriceCtr.selectCustomerListForCB();
		pDate = CustomerPriceCtr.selectProductListForCB();
		String[] str1 = new String[cDate.size() + 1];
		str1[0] = " -请选择客户名称- ";
		for (int i = 0; i < cDate.size(); i++) {
			str1[i + 1] = " "+cDate.get(i).getSid()+"-" + cDate.get(i).getCname() + " ";
		}
		String[] str2 = new String[pDate.size() + 1];
		str2[0] = " -请选择产品代码- ";
		for (int i = 0; i < pDate.size(); i++) {
			str2[i + 1] = " "+pDate.get(i).getSid()+"-"+ pDate.get(i).getCode() + "-" + pDate.get(i).getPname()+" ";
		}
		//将值添加进自定义的comboBox中
		comboBox1 = new DIYComboBox<String>(str2);
		comboBox2 = new DIYComboBox<String>(str1);
		// 新建JPanel
		CustomerPriceAddDialogPanel dialogPanel = new CustomerPriceAddDialogPanel(exitButton, ok, cancel, comboBox1,
				comboBox2, cDate, pDate, field1, field2, field3, labelGG, labelUN);
		Border border = BorderFactory.createCompoundBorder(ShadowBorder.newBuilder().shadowAlpha(0.7f).center().build(),
				BorderFactory.createLineBorder(Color.white));
		dialogPanel.setBorder(border);
		add(dialogPanel);
		//comboBox选中事件
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					int selectI = comboBox1.getSelectedIndex() - 1;
					//判断comboBox是否为第一个选项
					if (selectI >= 0) {
						field1.setText(pDate.get(selectI).getPname());
						field2.setText(pDate.get(selectI).getStandard().toString());
						labelGG.setText(pDate.get(selectI).getUnit() + "/" + pDate.get(selectI).getStandardUnit());
						labelUN.setText("元/"+pDate.get(selectI).getUnit());
						fieldPSid.setText(pDate.get(selectI).getSid().toString());
					} else {
						field1.setText(null);
						field2.setText(null);
						labelGG.setText(null);
						labelUN.setText(null);
						fieldPSid.setText(null);
					}
				}
			}
		});
		comboBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					int selectI = comboBox2.getSelectedIndex() - 1;
					if (selectI >= 0) {
						fieldCSid.setText(cDate.get(selectI).getSid().toString());
					} else {
						fieldCSid.setText(null);
					}
				}
			}
		});
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
				String productSid=fieldPSid.getText();
				String customerSid=fieldCSid.getText();
				String unitPrice =field3.getText();
				//非空,数字校验
				if(SwingUtil.isEmpty(productSid)){
					dlg=new OperateDBFailMDialog(parent,true,"产品代码不能为空！");
				}else if(SwingUtil.isEmpty(customerSid)){
					dlg=new OperateDBFailMDialog(parent,true,"客户名称不能为空！");
				}else if(SwingUtil.isEmpty(unitPrice)){
					dlg=new OperateDBFailMDialog(parent,true,"产品单价不能为空！");
				}else if(!SwingUtil.isDecimal(unitPrice)){
					dlg=new OperateDBFailMDialog(parent,true,"产品单价格式错误！");
				}else{
					//进行数据库操作，出错会进行回滚
					CustomerPriceCtr customerCtr = new CustomerPriceCtr();
					i=customerCtr.selectCountCusProByPCid(productSid, customerSid);
					if(i>0){
						dlg=new OperateDBFailMDialog(parent,true,"该客户-产品关系已存在！");
						comboBox1.setSelectedIndex(0);
						comboBox2.setSelectedIndex(0);
						field3.setText(null);
					}else{
						int j=0;
						j=customerCtr.insertCusProRelAndRecord(productSid, customerSid, SwingUtil.strTurnBig(unitPrice));
						//判断是否操作数据库 成功
						if(j<1){
							dlg=new OperateDBFailMDialog(parent,true,"操作失败！");
						}else{
							dispose();
							reloadButton.doClick();
						}
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
