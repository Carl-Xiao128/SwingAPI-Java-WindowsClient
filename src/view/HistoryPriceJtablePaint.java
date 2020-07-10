package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.util.Vector;

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
 * @描述： 创建历史价格JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class HistoryPriceJtablePaint {
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable historyPriceJtablePaint(JTable Table,
			Vector data) {
		Vector columnNames = new Vector();
		columnNames.add("日期");
		columnNames.add("单价/元");
		DefaultTableModel myTableModel = new DefaultTableModel(data,
				columnNames) {
			public boolean isCellEditable(int row, int col) {
					return false;
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
		Table = new JTable(myTableModel);
		// 设置排序
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				myTableModel);
		Table.setRowSorter(sorter);
		// 设置最小列宽
		TableColumn column = null;
		int[] columnWidth = new int[] {145,145};
		for (int i = 0; i < Table.getColumnCount(); i++) {
			column = Table.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}
		GroupableTableHeader tableHeader = new GroupableTableHeader();
		Table.setTableHeader(tableHeader);
		DefaultGroup group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(0);
		group.setHeaderValue("日期");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(1);
		group.setHeaderValue("单价/元");
		tableHeader.addGroup(group);

		// 设置Table间隔色
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				if (row % 2 == 0)
					setBackground(Color.white); // 设置奇数行底色
				else if (row % 2 == 1)
					setBackground(SystemColor.scrollbar); // 设置偶数行底色
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		// 设置单元格内容居中
		render.setHorizontalAlignment(SwingConstants.CENTER);
		Table.setDefaultRenderer(Object.class, render);
		//解决数值排序后，间隔色失效的情况
		Table.getColumnModel().getColumn(1).setCellRenderer(render); 
		Table.setRowHeight(30);
		// 设置列宽固定
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		SwingUtil.FitTableColumns(Table);
		return Table;

	}
	

}
