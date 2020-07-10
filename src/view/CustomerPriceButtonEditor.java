package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import util.SwingUtil;


/**
 *
 * @描述：自定义一个往JTable列里边添加按钮的单元格编辑器。
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:49:44
 */
public class CustomerPriceButtonEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1857788426473150341L;
	
	private JPanel panel;
    private JButton button;
    private JButton reloadButton;
    private JFrame frame;
    private Dialog dlg;
    private int count;
    private SwingUtil swingUtil = new SwingUtil();
    
    /**
     * 构造方法
     */
    CustomerPriceButtonEditor(JFrame frame,JScrollPane scrollPane,JButton reloadButton) {
        // DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。  
        super(new JTextField());
        this.frame=frame;
        this.reloadButton=reloadButton;
        // 设置点击几次激活编辑。  
        this.setClickCountToStart(1);
        this.fireEditingStopped();
        this.initButton();
        this.initPanel();
        // 添加按钮。  
        this.panel.add(this.button);
    }

    private void initButton() {
        this.button = new JButton();
        // 设置按钮的大小及位置。  
        this.button.setBounds(3, 3, 60, 30);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setBackground(new Color(255, 255, 255));


    }
    private void initPanel() {
        this.panel = new JPanel();
        // panel使用绝对定位，这样button就不会充满整个单元格。  
    }

    /**
     * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
     */
    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, int row, final int column) {
    	count =0;
        // 只为按钮赋值即可。也可以作其它操作。  
    	Rectangle r1=new Rectangle(3, 3, 120, 30);
    	Rectangle r2=new Rectangle(3, 3, 60, 30);
        this.button.setText(column == 6 ? "查看历史单价" : "编辑");
        this.button.setBounds(column == 6 ? r1:r2);
        this.button.setIcon(column == 6 ? swingUtil.produceImage("findHistory.png") : swingUtil.produceImage("edit.png"));
        this.button.addActionListener(new ActionListener() {
        	//这里mouse事件不会监听，必须用action事件
            public void actionPerformed(ActionEvent e) {
            	// 触发取消编辑的事件，不会调用tableModel的setValue方法。  
            	CustomerPriceButtonEditor.this.fireEditingCanceled();
            	if(count<1){
            		// 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等。  
            		int selectRow=table.getSelectedRow();
            		String sid=table.getValueAt(selectRow, 8).toString();
            		String productSid=table.getValueAt(selectRow, 9).toString();
            		String customerSid=table.getValueAt(selectRow, 10).toString();
            		if(column==6){
            			//获取查看历史单价对话框模型
            			dlg = new HistoryPriceAddDialog(frame, true,"查看客户-产品历史单价",Integer.parseInt(sid),productSid,customerSid);
            		}else{
            			//编辑价格对话框模型
            			dlg =new EditCustomerPrcAddDialog(frame, true, "编辑客户-产品单价", Integer.parseInt(sid), reloadButton,productSid,customerSid);
            		}
            		NoTileDrag.setCanDraged(dlg);
            		dlg.setVisible(true);
            		
            	}
            	//这里存在一个bug，暂时用此方法解决（用计数来限制按钮监听多次的情况）
            	count++;
            }
        });
        return this.panel;
    }

    /**
     * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
     */
    @Override
    public Object getCellEditorValue() {
        return this.button.getText();
    }

}
