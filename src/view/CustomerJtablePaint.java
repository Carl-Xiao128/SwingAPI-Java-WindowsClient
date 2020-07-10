package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import util.SwingUtil;

/**
 *
 * @描述： 创建客户JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class CustomerJtablePaint {
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable customerJtablePaint(JTable CustomerTable, Vector data, JFrame frame, JScrollPane scrollPane,
			JButton reloadButton) {
		Vector columnNames = new Vector();
		columnNames.add("客户ID");
		columnNames.add("客户名称");
		columnNames.add("客户电话");
		columnNames.add("区域");
		columnNames.add("地址");
		columnNames.add("操作");
		columnNames.add("操作");

		DefaultTableModel myTableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int col) {
				if (col == 5 || col == 6) {
					return true;
				} else {
					return false;
				}
			}
			public Class getColumnClass(int column) {
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}

		};
		CustomerTable = new JTable(myTableModel);
		// 设置排序
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(myTableModel);
		CustomerTable.setRowSorter(sorter);
		// 设置最小列宽
		TableColumn column = null;
		int[] columnWidth = new int[] { 60, 100, 100, 120, 220, 68, 68 };
		for (int i = 0; i < CustomerTable.getColumnCount(); i++) {
			column = CustomerTable.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}

		// 合并表头单元格
		GroupableTableHeader tableHeader = new GroupableTableHeader();
		CustomerTable.setTableHeader(tableHeader);
		DefaultGroup group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(0);
		group.setHeaderValue("客户ID");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(1);
		group.setHeaderValue("客户名称");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(2);
		group.setHeaderValue("客户电话");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(3);
		group.setHeaderValue("区域");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(4);
		group.setHeaderValue("地址");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(5);
		group.setColumnSpan(2);
		group.setHeaderValue("操作");
		tableHeader.addGroup(group);

		// 将第五列第六列加入button
		CustomerTable.getColumnModel().getColumn(5)
				.setCellEditor(new CustomerButtonEditor(frame, scrollPane, reloadButton));
		CustomerTable.getColumnModel().getColumn(5).setCellRenderer(new CustomerButtonRender());
		CustomerTable.getColumnModel().getColumn(6)
				.setCellEditor(new CustomerButtonEditor(frame, scrollPane, reloadButton));
		CustomerTable.getColumnModel().getColumn(6).setCellRenderer(new CustomerButtonRender());
		// 设置Table间隔色
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.white); // 设置奇数行底色
				else if (row % 2 == 1)
					setBackground(SystemColor.scrollbar); // 设置偶数行底色
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
			
		};
		// 设置单元格内容居中
		render.setHorizontalAlignment(SwingConstants.CENTER);
		CustomerTable.setDefaultRenderer(Object.class, render);
		
		//解决数值排序后，间隔色失效的情况
		CustomerTable.getColumnModel().getColumn(0).setCellRenderer(render); 
		CustomerTable.setRowHeight(30);
		// 设置列宽固定
		CustomerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		SwingUtil.FitTableColumns(CustomerTable);
		return CustomerTable;

	}

}
