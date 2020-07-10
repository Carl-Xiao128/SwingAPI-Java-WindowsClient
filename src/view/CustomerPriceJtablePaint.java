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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import util.SwingUtil;

/**
 *
 * @描述： 创建客户产品关系JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class CustomerPriceJtablePaint {
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable customerPriceJtablePaint(JTable customerPriceTable,
			Vector data, JFrame frame, JScrollPane scrollPane,
			JButton reloadButton) {
		Vector columnNames = new Vector();
		for (int i = 0; i < 11; i++) {
			columnNames.add("第" + (i + 1) + "列");

		}
		DefaultTableModel myTableModel = new DefaultTableModel(data,
				columnNames) {
			public boolean isCellEditable(int row, int col) {
				if (col == 6 || col == 7) {
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
		customerPriceTable = new JTable(myTableModel);
		// 设置排序
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				myTableModel);
		customerPriceTable.setRowSorter(sorter);
		// 设置最小列宽
		TableColumn column = null;
		int[] columnWidth = new int[] { 92, 90, 100, 110, 80, 80, 120, 68, 0, 0, 0 };
		for (int i = 0; i < customerPriceTable.getColumnCount(); i++) {
			column = customerPriceTable.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}

		// 合并表头单元格
		GroupableTableHeader tableHeader = new GroupableTableHeader();
		customerPriceTable.setTableHeader(tableHeader);
		DefaultGroup group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(0);
		group.setHeaderValue("产品代码ID");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(1);
		group.setHeaderValue("产品名称");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(2);
		group.setHeaderValue("客户名称");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(3);
		group.setHeaderValue("最新客户单价/元");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(4);
		group.setHeaderValue("单位");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(5);
		group.setHeaderValue("规格");
		tableHeader.addGroup(group);
		
		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(6);
		group.setColumnSpan(2);
		group.setHeaderValue("操作");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(8);
		group.setHeaderValue("sid");
		tableHeader.addGroup(group);
		
		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(9);
		group.setHeaderValue("productSid");
		tableHeader.addGroup(group);
		
		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(10);
		group.setHeaderValue("customerSid");
		tableHeader.addGroup(group);

		// 将第7列第8列加入button
		customerPriceTable.getColumnModel().getColumn(6).setCellEditor(
				new CustomerPriceButtonEditor(frame, scrollPane, reloadButton));
		customerPriceTable.getColumnModel().getColumn(6)
				.setCellRenderer(new CustomerPriceButtonRender());
		customerPriceTable.getColumnModel().getColumn(7).setCellEditor(
				new CustomerPriceButtonEditor(frame, scrollPane, reloadButton));
		customerPriceTable.getColumnModel().getColumn(7)
				.setCellRenderer(new CustomerPriceButtonRender());
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
		customerPriceTable.setDefaultRenderer(Object.class, render);
		//解决数值排序后，间隔色失效的情况
		customerPriceTable.getColumnModel().getColumn(3).setCellRenderer(render); 
		customerPriceTable.setRowHeight(30);
		// 设置列宽固定
		customerPriceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 设置主键sid隐藏
		TableColumnModel columnModel = customerPriceTable.getColumnModel();
		int[] arr=new int[]{8,9,10};
		SwingUtil.setColumnHidden(columnModel, arr);
		SwingUtil.FitTableColumns(customerPriceTable);
		return customerPriceTable;

	}
	

}
