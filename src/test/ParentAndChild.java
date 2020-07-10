package test;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JLayeredPane;
 
//实现鼠标移动组件的小程序
public class ParentAndChild {
 
    static Point oldPt;
    static JLayeredPane pane = new JLayeredPane();
    static Button btn = new Button(), btn1 = new Button("嘻嘻嘻");
    public static void main(String[] args) {
        Frame fm = new Frame("哈哈哈");
        fm.setVisible(true);
        pane.setLayout(new FlowLayout());
        fm.add(pane, "Center");
        btn.setLabel("啦啦啦");
        btn.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    oldPt=e.getLocationOnScreen();
                }
                public void mouseReleased(MouseEvent e) {
                     pane.setPosition(btn, -1);
                } 
        });
         
        btn.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                     Point pt = e.getLocationOnScreen();
                     btn.setLocation(new Point(btn.getX() + pt.x-oldPt.x,
                                     btn.getY() + pt.y-oldPt.y));
                     oldPt=pt;
                }
                 
        });
             
         
        btn.setPreferredSize(new Dimension(200, 100));
        pane.add(btn);
        pane.add(btn1);
        fm.setSize(new Dimension(600, 800));
         
         
        fm.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
        });
    }
 
}
