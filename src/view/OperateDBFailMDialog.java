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

/**
 *
 * @描述：自定义警告提示对话框模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:56:38
 */
public class OperateDBFailMDialog extends Dialog{
	private static final long serialVersionUID = 6356567853953658759L;
	JButton ok = new JButton("确定");
	JLabel exitButton = new JLabel();
	SwingUtil sd=new SwingUtil();
	
	/**
	 * 构造方法
	 * @param parent
	 * @param modal
	 */
	OperateDBFailMDialog(JFrame parent, boolean modal,String message){
		super(parent, modal);
		setTitle("自定义对话框");
		setUndecorated(true);
		setSize(330, 120);
		setResizable(false);
		Point point=parent.getLocation();
		setLocation((int)point.getX()+parent.getWidth()/2-125, (int)point.getY()+parent.getHeight()/2-75);
		OperateDBFailMDialogPanel dialogPanel =new OperateDBFailMDialogPanel(exitButton,ok,message);
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
    			dispose();
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
	}
	
}

