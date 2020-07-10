package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.SwingUtil;

/**
 *
 * @描述：主界面Jpanel模型
 * 为了动态调用其paintComponent方法
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:43:23
 */
public class MainPanel extends JPanel {
	private static final long serialVersionUID = -8006156901710150560L;
	
	private JLabel minButton;
	private JLabel maxButton;
	private JLabel exitButton;
	private JFrame frame;
	private SwingUtil swingUtil =new SwingUtil();
	
	private ImageIcon imageIcon;
	
	/**
	 * 构造方法
	 * @param frame
	 */
	public MainPanel(JFrame frame) {
		this.frame = frame;
		initGUI();
		initListener();
		// 给imageIcon指定默认图片
		imageIcon = swingUtil.produceImage("back0.png");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	    g2d.setComposite(alphaComposite);//透明度
	    //绘制背景
		g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 *
	 * @描述：初始化界面
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:22:21
	 */
	private void initGUI() {
		setLayout(null);
		//最小化按钮
		minButton = new JLabel();
		minButton.setBounds(800, 5, 31, 20);
		minButton.setIcon(swingUtil.produceImage("min.png"));
		add(minButton);
		//最大化按钮
		maxButton = new JLabel();
		maxButton.setBounds(828, 5, 31, 20);
		maxButton.setIcon(swingUtil.produceImage("max.png"));
		add(maxButton);
		//退出按钮
		exitButton = new JLabel();
		exitButton.setBounds(856, 5, 39, 20);
		exitButton.setIcon(swingUtil.produceImage("close.png"));
		add(exitButton);
		
		//主页头信息
		JLabel lblNewLabel = new JLabel("销售开票系统v1.0");
		lblNewLabel.setIcon(swingUtil.produceImage("home.png"));
		lblNewLabel.setForeground(new Color(204, 255, 51));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(15, 10, 216, 23);
		add(lblNewLabel);
		
	}
	/**
	 *
	 * @描述：初始化监听
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:22:06
	 */
	private void initListener() {
		// 最小化按钮事件
		minButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				minButton.setIcon(swingUtil.produceImage("min.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				minButton.setIcon(swingUtil.produceImage("min_active.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.setExtendedState(Frame.ICONIFIED);
			}
		});
		// 最大化按钮事件
		maxButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (frame.getExtendedState() != Frame.MAXIMIZED_BOTH) {
					maxButton.setIcon(swingUtil.produceImage("max.png"));
				} else {
					maxButton.setIcon(swingUtil.produceImage("restore.png"));
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (frame.getExtendedState() != Frame.MAXIMIZED_BOTH) {
					maxButton.setIcon(swingUtil.produceImage("max_active.png"));
				} else {
					maxButton.setIcon(swingUtil.produceImage("restore_active.png"));
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// 若是最大化过了，就直接还原大小
				if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) {
					frame.setSize(900, 700);// 还原大小
					frame.setLocationRelativeTo(null);// 这里需要重新设置坐标
					maxButton.setIcon(swingUtil.produceImage("max.png"));
				} else {
					frame.setExtendedState(Frame.MAXIMIZED_BOTH);
					maxButton.setIcon(swingUtil.produceImage("restore.png"));
				}
				// 按比例放置按钮
				fixedButtonPoint();
			}
		});
		// 退出按钮事件
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(swingUtil.produceImage("close.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(swingUtil.produceImage("close_active.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//退出整个系统
				System.exit(0);
			}
		});
		
	}
	/**
	 *
	 * @描述：按比例放置按钮，不管放大还是缩小窗体，按钮永远在右上角
	 * @创建人：kitxiao
	 * @创建时间：2016年8月21日下午9:21:51
	 */
	public void fixedButtonPoint() {
		minButton.setBounds(frame.getWidth()-39-28-28-5, 5, 31, 20);
		maxButton.setBounds(frame.getWidth()-39-28-5, 5, 31, 20);
		exitButton.setBounds(frame.getWidth()-39-5, 5, 39, 20);
	}
	
}
