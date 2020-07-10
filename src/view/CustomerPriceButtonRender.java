package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import util.SwingUtil;

/**
 *
 * @描述：自定义一个往JTable列里边添加按钮的单元格渲染器。
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:54:38
 */
public class CustomerPriceButtonRender implements TableCellRenderer {
    private JPanel panel;
    private JButton button;
    private SwingUtil swingUtil = new SwingUtil();

    public CustomerPriceButtonRender() {
        this.initButton();
        this.initPanel();
        // 添加按钮。  
        this.panel.add(this.button);
    }
    /**
     * 初始化按钮
     */
    private void initButton() {
        this.button = new JButton();
        // 设置按钮的大小及位置。  
        this.button.setBounds(3, 3, 60, 20);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setBackground(new Color(255, 255, 255));
    }
    /**
     * 初始化JPanel
     */
    private void initPanel() {
        this.panel = new JPanel();
        // panel使用绝对定位，这样button就不会充满整个单元格。  
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row,
            int column) {
        // 只为按钮赋值即可。也可以作其它操作，如绘背景等。  
    	Rectangle r1=new Rectangle(3, 3, 120, 30);
    	Rectangle r2=new Rectangle(3, 3, 60, 30);
        this.button.setText(column == 6 ? "查看历史单价" : "编辑");
        this.button.setBounds(column == 6 ? r1:r2);
        this.button.setIcon(column == 6 ? swingUtil.produceImage("findHistory.png") : swingUtil.produceImage("edit.png"));
        return this.panel;
    }

}
