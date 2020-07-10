package view;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
 

/**
 *
 * @描述：使窗体没有标题栏的情况下能拖动
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:56:04
 */
public class NoTileDrag {
    private static Point origin = new Point(); 
    private static boolean isDraging = false;
     
    public static void setCanDraged(final Component currentFrame){
        currentFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDraging = true;
                origin.x = e.getX();
                origin.y = e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                isDraging = false;
            }
        });
        currentFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDraging) {
                    Point p = currentFrame.getLocation();
                    currentFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
                }
            }
        });
    }
}