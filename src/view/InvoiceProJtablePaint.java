package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import util.SwingUtil;

/**
 *
 * @描述： 开票JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class InvoiceProJtablePaint {
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable invoiceProJtablePaint(JTable invoiceProTable,
			Vector data) {
		Vector columnNames = new Vector();
		for (int i = 0; i < 8; i++) {
			columnNames.add("第" + (i + 1) + "列");

		}
		DefaultTableModel myTableModel = new DefaultTableModel(data,
				columnNames) {
			public boolean isCellEditable(int row, int col) {
					return false;
			}
		};
		invoiceProTable = new JTable(myTableModel);
		TableColumn column = null;
		int[] columnWidth = new int[] { 117, 160, 80, 70, 50, 180, 100, 0 };
		for (int i = 0; i < invoiceProTable.getColumnCount(); i++) {
			column = invoiceProTable.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}

		// 合并表头单元格
		GroupableTableHeader tableHeader = new GroupableTableHeader();
		invoiceProTable.setTableHeader(tableHeader);
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
		group.setHeaderValue("规格");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(3);
		group.setHeaderValue("数量(kg)");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(4);
		group.setHeaderValue("件数");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(5);
		group.setHeaderValue("单价");
		tableHeader.addGroup(group);
		
		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(6);
		group.setHeaderValue("金额小计(元)");
		tableHeader.addGroup(group);

		group = new DefaultGroup();
		group.setRow(0);
		group.setColumn(7);
		group.setHeaderValue("cSid");
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
		invoiceProTable.setDefaultRenderer(Object.class, render);
		//解决数值排序后，间隔色失效的情况
		invoiceProTable.setRowHeight(30);
//		// 设置单元格不可编辑
//		invoiceProTable.setCellSelectionEnabled(false);
		// 设置列宽固定
		invoiceProTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 设置主键sid隐藏
		TableColumnModel columnModel = invoiceProTable.getColumnModel();
		int[] arr=new int[]{7};
		SwingUtil.setColumnHidden(columnModel, arr);
		SwingUtil.FitTableColumns(invoiceProTable);
		return invoiceProTable;

	}
	

}
