package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.math.BigDecimal;
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
 * @描述： 开票底部JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class InvoiceProBottomJtablePaint{
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable invoiceProBottomJtablePaint(JTable invoicePro1Table) {
		Vector columnNames = new Vector();
		for (int i = 0; i < 8; i++) {
			columnNames.add("第" + (i + 1) + "列");

		}
		Vector rowDate = new Vector();
		for(int i=0;i<2;i++){
			Vector row = new Vector();
			if(i==0){
				for(int j=0;j<8;j++){
					if(j==5){
						row.add("折扣减免");
					}else{
						row.add("");
					}
				}
			}else{
				for(int j=0;j<8;j++){
					if(j==0){
						row.add("合计");
					}else{
						row.add("");
					}
				}
			}
			rowDate.add(row);
		}
		DefaultTableModel myTableModel = new DefaultTableModel(rowDate,
				columnNames) {
			public boolean isCellEditable(int row, int col) {
				if(row==0 && col == 6){
					return true;
				}else{
					return false;
				}
			}
			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if (column == 6 && row ==0) {
		            try {
		            	if(!"".equals((String) aValue)){
		            		new BigDecimal((String) aValue);
		            	}
		            } catch (NumberFormatException e) {
		            	Toolkit.getDefaultToolkit().beep();
		            	javax.swing.JOptionPane.showMessageDialog(null, "只能输入数字!");
		                return;
		            }
			        this.fireTableDataChanged();
				}
				super.setValueAt(aValue, row, column);
			}
		};
		invoicePro1Table = new JTable(myTableModel);
		invoicePro1Table.getTableHeader().setVisible(false); 
		TableColumn column = null;
		int[] columnWidth = new int[] { 117,160,80, 70, 50, 180, 100, 0};
		for (int i = 0; i < invoicePro1Table.getColumnCount(); i++) {
			column = invoicePro1Table.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}
		// 设置Table间隔色
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				if (row % 2 == 0){
					setBackground(SystemColor.scrollbar); // 设置偶数行底色
					if(column == 6){
						setBackground(Color.yellow);
					}
				}else if (row % 2 == 1){
					setBackground(SystemColor.scrollbar); // 设置奇数行底色
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		// 设置单元格内容居中
		render.setHorizontalAlignment(SwingConstants.CENTER);
		invoicePro1Table.setRowHeight(30);
		invoicePro1Table.setDefaultRenderer(Object.class, render);
		// 设置单元格不可编辑
		invoicePro1Table.setCellSelectionEnabled(false);
		// 设置列宽固定
		invoicePro1Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 设置主键sid隐藏
		TableColumnModel columnModel = invoicePro1Table.getColumnModel();
		int[] arr=new int[]{7};
		SwingUtil.setColumnHidden(columnModel, arr);
		SwingUtil.FitTableColumns(invoicePro1Table);
		return invoicePro1Table;
	}
}
