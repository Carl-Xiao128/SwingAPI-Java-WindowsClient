package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import util.SwingUtil;

/**
 *
 * @描述：删除对话框内部JPanel模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:49:09
 */
public class MessageDialogPanel extends JPanel {
	private static final long serialVersionUID = -1218304765817347707L;
	
	private ImageIcon imageIcon;
	private SwingUtil swingUtil = new SwingUtil();
	
	JLabel label1;
	JButton ok;
	JButton cancel;
	
	public MessageDialogPanel(JLabel exitButton,JButton ok,JButton cancel,String cName,String panelName) {
		initGUI(exitButton,ok,cancel,cName,panelName);
		// 给imageIcon指定默认图片
		imageIcon = swingUtil.produceImage("MessageDialogBack.png");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	    g2d.setComposite(alphaComposite);//透明度
		g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * 初始化界面
	 */
	private void initGUI(JLabel exitButton,JButton ok,JButton cancel,String cName,String panelName) {
		setLayout(null);
		
		exitButton.setBounds(286, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);
		
		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 32, 320, 2);
		add(separator);
		
		if("panel3".equals(panelName)){
			label1=new JLabel("确定删除客户：（"+cName+"）？");
		}else if("panel4".equals(panelName)){
			label1=new JLabel("确定删除产品：（"+cName+"）？");
		}
    	label1.setBounds(50, 37, 250, 40);
    	label1.setIcon(swingUtil.produceImage("delete.png"));
    	label1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		add(label1);
		add(ok);
		add(cancel);
		ok.setBounds(60, 80, 90, 30);
		ok.setIcon(swingUtil.produceImage("Determine.png"));
		cancel.setBounds(180, 80, 90, 30);
		cancel.setIcon(swingUtil.produceImage("cancel.png"));
		
		JLabel lblNewLabel = new JLabel(" 删除提示");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(swingUtil.produceImage("Message.png"));
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setBounds(15, 7, 150, 24);
		add(lblNewLabel);
		
	}
	
	
}