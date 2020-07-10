package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import entity.SearchParam;
import util.SwingUtil;
import web.InvoiceCtr;

/**
 *
 * @描述：统计分析JTable模型
 * 
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:13:20
 */
public class InvoiceJtablePaint{
	private int count =0;
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public JTable invoiceJtablePaint(JTable invoiceTable,Vector data,final JFrame frame,final JPopupMenu menu,
			final JPopupMenu menu1,final JMenu item1,final JMenu item2,final JMenu item3) {
		final Vector columnNames = new Vector();
		String [] arr = new String[]{"流水单号","发货日期","客户全称","区域","产品类别","产品代码","产品销售名称",
				"规格","件数","数量(kg)","单价","交易金额（元）","款项","追加（元）","账款（元）","折扣（元）","负责人","区域经理","承运人","备注"};
		for(int i=0;i<arr.length;i++){
			columnNames.add(arr[i]);
		}
		final DefaultTableModel myTableModel = new DefaultTableModel(data,
				columnNames) {
			public boolean isCellEditable(int row, int col) {
				if(col == 13 || col == 14){
					return true;
				}else{
					return false;
				}
			}
			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if (column == 13 || column == 14) {
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
		
		invoiceTable = new JTable(myTableModel);
		
		// 设置排序
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(myTableModel);
		invoiceTable.setRowSorter(sorter);
		TableColumn column = null;
		int[] columnWidth = new int[] { 150, 120, 150, 120, 120, 100, 180, 100, 70, 80, 120, 120, 120, 100, 100,100, 120,120,120,150};
		for (int i = 0; i < invoiceTable.getColumnCount(); i++) {
			column = invoiceTable.getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
		}
		final GroupableTableHeader tableHeader = new GroupableTableHeader();
		DefaultGroup group;
		for(int i=0;i<arr.length;i++){
			group = new DefaultGroup();
			group.setRow(0);
			group.setColumn(i);
			group.setHeaderValue(arr[i]);
			tableHeader.addGroup(group);
		}
		tableHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					List<String> list1 =SwingUtil.findDistinctList("c");
					List<String> list2 =SwingUtil.findDistinctList("p");
					List<String> list3 =SwingUtil.findDistinctList("co");
					item1.removeAll();
					item2.removeAll();
					item3.removeAll();
					for(String str : list1){
						JMenuItem submenu = new JMenuItem(str);
						item1.add(submenu);
						submenu.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								AbstractButton aButton = (AbstractButton)e.getSource();
								SearchParam searchParam =new SearchParam();
								searchParam.setCname(aButton.getText());
								InvoiceCtr invoiceCtr =new InvoiceCtr();
								Vector data = invoiceCtr.selectInvoiceProCus(searchParam);
								myTableModel.setDataVector(data, columnNames);
								int[] columnWidth = new int[] { 150, 120, 150, 120, 120, 100, 180, 100, 70, 80, 120, 120, 120, 100, 100,100, 120,120,120,150};
								for (int i = 0; i < tableHeader.getColumnCount(); i++) {
									TableColumn column = tableHeader.getColumnModel().getColumn(i);
									column.setMinWidth(columnWidth[i]);
								}
							}
						});
					}
					for(String str : list2){
						JMenuItem submenu = new JMenuItem(str);
						item2.add(submenu);
						submenu.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								AbstractButton aButton = (AbstractButton)e.getSource();
								SearchParam searchParam =new SearchParam();
								searchParam.setPname(aButton.getText());
								InvoiceCtr invoiceCtr =new InvoiceCtr();
								Vector data = invoiceCtr.selectInvoiceProCus(searchParam);
								myTableModel.setDataVector(data, columnNames);
								int[] columnWidth1 = new int[] { 150, 120, 150, 120, 120, 100, 180, 100, 70, 80, 120, 120, 120, 100, 100,100, 120,120,120,150};
								for (int i = 0; i < tableHeader.getColumnCount(); i++) {
									TableColumn column1 = tableHeader.getColumnModel().getColumn(i);
									column1.setMinWidth(columnWidth1[i]);
								}
							}
						});
					}
					for(String str : list3){
						JMenuItem submenu = new JMenuItem(str);
						item3.add(submenu);
						submenu.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								AbstractButton aButton = (AbstractButton)e.getSource();
								SearchParam searchParam =new SearchParam();
								searchParam.setCode(aButton.getText());
								InvoiceCtr invoiceCtr =new InvoiceCtr();
								Vector data = invoiceCtr.selectInvoiceProCus(searchParam);
								myTableModel.setDataVector(data, columnNames);
								int[] columnWidth2 = new int[] { 150, 120, 150, 120, 120, 100, 180, 100, 70, 80, 120, 120, 120, 100, 100,100, 120,120,120,150};
								for (int i = 0; i < tableHeader.getColumnCount(); i++) {
									TableColumn column2 = tableHeader.getColumnModel().getColumn(i);
									column2.setMinWidth(columnWidth2[i]);
								}
							}
						});
					}
					//添加右键菜单
					menu1.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		invoiceTable.setTableHeader(tableHeader);
		// 设置Table间隔色
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus, int row,
					int column) {
				if (row % 2 == 0){
					setBackground(SystemColor.scrollbar); // 设置偶数行底色
					
				}else if (row % 2 == 1){
					setBackground(Color.white); // 设置奇数行底色
				}
				if(column == 13 || column == 14){
					setBackground(Color.yellow);
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		// 设置单元格内容居中
		render.setHorizontalAlignment(SwingConstants.CENTER);
		invoiceTable.setRowHeight(30);
		invoiceTable.setDefaultRenderer(Object.class, render);
		SwingUtil.FitTableColumns(invoiceTable);
		// 设置列宽固定
		invoiceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		invoiceTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (count == 0)
						count++;
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (count == 1) {
						//添加右键菜单
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
					count = 0;
				}
			}
		});
		
		return invoiceTable;
	}
}
