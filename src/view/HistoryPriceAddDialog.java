package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import util.SwingUtil;

/**
 * 
 * @描述：查看历史单价弹出对话框模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:10:48
 */
public class HistoryPriceAddDialog extends Dialog {
	private static final long serialVersionUID = -4282540346271749050L;

	private JLabel exitButton = new JLabel();
	private SwingUtil sd = new SwingUtil();

	/**
	 * 查看历史单价弹出对话框模型构造方法
	 */
	HistoryPriceAddDialog(final JFrame parent, boolean modal, final String title,int sid,String productSid,String customerSid) {
		super(parent, modal);
		setTitle("自定义对话框");
		setUndecorated(true);
		setSize(350, 400);
		setResizable(false);
		// 设置对话框位置为跟随JFrame中心
		Point point = parent.getLocation();
		if("查看历史单价".equals(title)){
			//客户产品关系编辑时，不能在frame的中心
			setLocation((int) point.getX() + parent.getWidth() / 2 - 120,
					(int) point.getY() + parent.getHeight() / 2 - 225);
		}else{
			setLocation((int) point.getX() + parent.getWidth() / 2 - 175,
					(int) point.getY() + parent.getHeight() / 2 - 200);
		}
		// 新建JPanel
		HistoryPriceAddDialogPanel dialogPanel = new HistoryPriceAddDialogPanel(parent,exitButton,title, sid,productSid,customerSid);
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
	}

}
