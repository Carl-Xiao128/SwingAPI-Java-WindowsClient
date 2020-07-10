package test;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.*;

public class JTableEventTest extends MouseAdapter implements ActionListener, TableModelListener {

	JFrame frame;

	JTable table;

	MyTabled mt = null;

	JPopupMenu menu;

	JMenuItem item;

	DefaultTableModel model;

	String n[] = { "姓名", "抹毫", "性±", "花Αゑ", "出扫驳" };

	Object p[][] = { { "张三", new Integer(20), "男", new Boolean(true), "德州" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" },
			{ "李四", new Integer(21), "女", new Boolean(false), "济南" } };

	int count = 0;

	public JTableEventTest() {

		/*
		 * try{
		 * 
		 * UIManager.setLookAndFeel(
		 * "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); }
		 * 
		 * catch(Exception e){
		 * 
		 * e.printStackTrace(); }
		 */

		menu = new JPopupMenu();

		item = new JMenuItem("删除该行");

		item.addActionListener(this);

		menu.add(item);

		JComboBox box = new JComboBox();

		box.addItem("济南");

		box.addItem("迁さ");

		box.addItem("德州");

		frame = new JFrame();

		JPanel panel = new JPanel();

		JButton button = new JButton("删除");

		panel.add(button);

		button.addActionListener(this);

		mt = new MyTabled(p, n);

		mt.addTableModelListener(this);

		table = new JTable(mt);

		table.setFont(new Font("宋体", Font.BOLD, 15));

		TableColumnModel cm = table.getColumnModel();

		cm.getColumn(4).setCellEditor(new DefaultCellEditor(box));

		table.setPreferredScrollableViewportSize(new Dimension(550, 180));

		table.setCellSelectionEnabled(false);

		table.addMouseListener(this);

		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setFont(new Font("宋体", Font.BOLD, 15));

		frame.getContentPane().add(scrollPane);

		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		frame.pack();

		frame.show();

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				System.exit(0);

			}
		});
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {

			if (count == 0)

				count++;
		}

		if (e.getButton() == MouseEvent.BUTTON3) {

			if (count == 1) {

				menu.show(e.getComponent(), e.getX(), e.getY());

			}
			count = 0;

		}
	}

	public void actionPerformed(ActionEvent e) {

		if (table.getSelectedRow() == -1)
			JOptionPane.showMessageDialog(frame, "请选择栓匆境男", "川ó", JOptionPane.INFORMATION_MESSAGE);
		else {

			int result = JOptionPane.showConfirmDialog(frame, "取な狄境穑", "", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

			if (result == JOptionPane.YES_OPTION)

				mt.removeRow(table.getSelectedRow());
		}
	}

	public void tableChanged(TableModelEvent e) {// 测试ComboBox的值是·瘛冷化

		// int row=e.getFirstRow();
		System.out.println("active");

	}

	public static void main(String[] args) {
		new JTableEventTest();
	}
}

class MyTabled extends DefaultTableModel {
	public MyTabled(Object p[][], String n[]) {

		super(p, n);

	}

	public Class getColumnClass(int col) {

		return getValueAt(0, col).getClass();
	}

	public boolean isCellEditable(int rowIndex, int colIndex) {
		if (colIndex == 3)
			return true;
		return false;
	}
}