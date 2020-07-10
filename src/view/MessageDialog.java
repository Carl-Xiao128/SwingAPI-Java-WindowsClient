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
import javax.swing.UIManager;
import javax.swing.border.Border;

import util.SwingUtil;
import web.CustomerCtr;
import web.ProductCtr;

/**
 *
 * @描述：删除对话框模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:47:37
 */
public class MessageDialog extends Dialog{
	private static final long serialVersionUID = 6356567853953658759L;
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("取消");
	JLabel exitButton = new JLabel();
	SwingUtil sd=new SwingUtil();
	
	/**
	 * 构造方法
	 */
	MessageDialog(final JFrame parent, boolean modal,final int sid,String cName,final JButton reloadButton,final String panleName){
		super(parent, modal);
		setTitle("自定义对话框");
		setUndecorated(true);
		setSize(330, 120);
		setResizable(false);
		Point point=parent.getLocation();
		setLocation((int)point.getX()+parent.getWidth()/2-125, (int)point.getY()+parent.getHeight()/2-75);
		//新建JPanel加入MessageDialog容器
		MessageDialogPanel dialogPanel =new MessageDialogPanel(exitButton,ok,cancel,cName,panleName);
		Border border = BorderFactory.createCompoundBorder(ShadowBorder.newBuilder().shadowAlpha(0.7f).center().build(),
				BorderFactory.createLineBorder(Color.white));
		dialogPanel.setBorder(border);
		add(dialogPanel);
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
				int i=0;
    			if("panel4".equals(panleName)){
    				ProductCtr productCtr =new ProductCtr();
    				i=productCtr.updateProductBySid("1", sid);
    			}else if("panel3".equals(panleName)){
    				CustomerCtr customerCtr=new CustomerCtr();
    				i=customerCtr.updateCustomerBySid("1", sid);
    			}
    			if(i<1){
    				System.out.println("1");
    				//操作数据库出错，新建提示对话框
    				Dialog dlg=new OperateDBFailMDialog(parent,true,"操作失败！");
    				dlg.setVisible(true);
    			}else{
    				dispose();
    				//刷新界面
    				reloadButton.doClick();
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

