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
 * @描述： 创建产品JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class ProductJtablePaint {
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable productJtablePaint(JTable ProductTable, Vector data, JFrame frame, JScrollPane scrollPane,
			JButton reloadButton) {
		Vector columnNames = new Vector();
		for (int i = 0; i < 8; i++) {
			columnNames.add("第" + (i + 1) + "列");

		}
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
		ProductTable = new JTable(myTableModel);
		//设置排序
		RowSorter<TableModel> sorter =new TableRowSorter<TableModel>(myTableModel);
		ProductTable.setRowSorter(sorter);
		// 设置最小列宽
		TableColumn column = null;
		int[] columnWidth = new int[] { 102, 102, 100, 180, 120, 68, 68, 0 };
		for (int i = 0; i < ProductTable.getColumnCount(); i++) {
			column = ProductTable.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}

		// 合并表头单元格
		GroupableTableHeader tableHeader = new GroupableTableHeader();
		ProductTable.setTableHeader(tableHeader);
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
		group.setHeaderValue("产品类别");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(3);
		group.setHeaderValue("规格");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(4);
		group.setHeaderValue("单位");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(5);
		group.setColumnSpan(2);
		group.setHeaderValue("操作");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(7);
		group.setHeaderValue("sid");
		tableHeader.addGroup(group);

		// 将第五列第六列加入button
		ProductTable.getColumnModel().getColumn(5)
				.setCellEditor(new ProductButtonEditor(frame, scrollPane, reloadButton));
		ProductTable.getColumnModel().getColumn(5).setCellRenderer(new CustomerButtonRender());
		ProductTable.getColumnModel().getColumn(6)
				.setCellEditor(new ProductButtonEditor(frame, scrollPane, reloadButton));
		ProductTable.getColumnModel().getColumn(6).setCellRenderer(new CustomerButtonRender());
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
		ProductTable.setDefaultRenderer(Object.class, render);
		//解决数值排序后，间隔色失效的情况
		ProductTable.getColumnModel().getColumn(0).setCellRenderer(render); 
		ProductTable.setRowHeight(30);
		// 设置单元格不可编辑
		ProductTable.setCellSelectionEnabled(false);
		// 设置列宽固定
		ProductTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 设置主键sid隐藏
		TableColumnModel columnModel = ProductTable.getColumnModel();
		int[] arr=new int[]{7};
		SwingUtil.setColumnHidden(columnModel, arr);
		SwingUtil.FitTableColumns(ProductTable);
		return ProductTable;

	}

}
