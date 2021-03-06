package test;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import util.SwingUtil;

public class Main extends JFrame implements ActionListener{
	private SwingUtil sd = new SwingUtil();
	private MyDialog dlg;
	private ImageIcon imageIcon;
	
	int row = 6, col = 30;
	JPanel p1 = new JPanel(), p2 = new JPanel();
	JTextArea ta = new JTextArea("文本区行数：" + row + " 列数：" + col, row, col);
	JButton exit = new JButton("退出"), dialog = new JButton("对话框");
	Main(){
		imageIcon = sd.produceImage("back0.png");
		setTitle("对话框父窗口");
		Container c = getContentPane();
		setSize(350, 200);
		c.add("Center", p1);
		c.add("South", p2);
		p1.add(ta);
		p2.add(exit);
		p2.add(dialog);
		exit.addActionListener(this);
		dialog.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == exit)
			System.exit(0);
		else
		{
			dlg = new MyDialog(this, true);
			NoTileDragTest noTileDrag = new NoTileDragTest();
			noTileDrag.setCanDraged(dlg);
			dlg.setVisible(true);
		}
	}

	public static void main(String[] args){
		new Main();
	}

	class MyDialog extends Dialog implements ActionListener{
		JLabel label1 = new JLabel("请输入行数");
		JLabel label2 = new JLabel("请输入列数");
		JTextField rows = new JTextField(50);
		JTextField columns = new JTextField(50);
		JButton ok = new JButton("确定");
		JButton cancel = new JButton("取消");
		JLabel minButton = new JLabel();
		JLabel exitButton = new JLabel();
		MyDialog(Main parent, boolean modal){
			super(parent, modal);
			setTitle("自定义对话框");
			setUndecorated(true);
			setSize(260, 140);
			setResizable(false);
			setLayout(null);
			add(label1);
			add(label2);

			exitButton.setBounds(221, 0, 39, 20);
			exitButton.setIcon(sd.produceImage("close.png"));
			add(exitButton);

			label1.setBounds(50, 30, 65, 20);
			label2.setBounds(50, 60, 65, 20);
			add(rows);
			add(columns);
			rows.setText(Integer.toString(ta.getRows()));
			columns.setText(Integer.toString(ta.getColumns()));
			rows.setBounds(120, 30, 90, 20);
			columns.setBounds(120, 60, 90, 20);
			add(ok);
			add(cancel);
			ok.setBounds(60, 100, 60, 25);
			cancel.setBounds(140, 100, 60, 25);
			ok.addActionListener(this);
			cancel.addActionListener(this);
			
			// 退出按钮事件
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
					dlg.dispose();
				}
			});
		}
		
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == ok){
				int row = Integer.parseInt(rows.getText());
				int col = Integer.parseInt(columns.getText());
				ta.setRows(row);
				ta.setColumns(col);
				ta.setText("文本区行数：" + row + " 列数：" + col);
			}
			dispose();
		}
	}

}