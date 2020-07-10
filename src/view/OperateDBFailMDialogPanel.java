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
 * @描述：自定义警告提示对话框内部JPanel模型
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:57:24
 */
public class OperateDBFailMDialogPanel extends JPanel {
	private static final long serialVersionUID = -1218304765817347707L;
	
	private ImageIcon imageIcon;
	private SwingUtil swingUtil = new SwingUtil();
	
	JLabel label1;
	
	public OperateDBFailMDialogPanel(JLabel exitButton,JButton ok,String message) {
		initGUI(exitButton,ok,message);
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
	private void initGUI(JLabel exitButton,JButton ok,String message) {
		setLayout(null);
		
		exitButton.setBounds(286, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);
		
		// 创建分隔条
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 32, 320, 2);
		add(separator);
		

		label1=new JLabel(message,JLabel.CENTER);
    	label1.setBounds(20, 37, 300, 40);
    	label1.setIcon(swingUtil.produceImage("Warning.png"));
    	label1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		add(label1);
		add(ok);
		ok.setBounds(120, 80, 90, 30);
		ok.setIcon(swingUtil.produceImage("Determine.png"));
		
		JLabel lblNewLabel = new JLabel(" 警告");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setIcon(swingUtil.produceImage("Message.png"));
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setBounds(15, 7, 150, 24);
		add(lblNewLabel);
		
	}
	
	
}