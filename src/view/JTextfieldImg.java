package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

/**
 *
 * @描述：设置JTextField背景图
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:33:41
 */
public class JTextfieldImg extends JTextField {
    private static final long serialVersionUID = 1L;
    private BufferedImage img;
    private TexturePaint texture;

    /**
     * 构造函数通过传递一张图作为其背景图
     * 
     * @param file：图像文件
     * @throws IOException
     */
    public JTextfieldImg(File file) throws IOException {
        // 通过文件的方式获取BufferedImage  
        img = ImageIO.read(file);
        // 根据指定图像的大小创建一个基准矩形,此矩形和图像大小相等（如果想矩形小一点，，也可绘制部分）  
        Rectangle rect = new Rectangle(0, 0, img.getWidth(null),
                img.getHeight(null));
        /** TexturePaint 类提供一种用于被指定为 BufferedImage 的纹理填充 Shape 的方式 **/
        texture = new TexturePaint(img, rect);
        // 使此文本框透明  
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        /** 在调用父类的绘图方法前绘制好背景 **/
        Graphics2D g2 = (Graphics2D) g;
        // 为 Graphics2D 设置 TexturePaint 属性  
        g2.setPaint(texture);
        // 获取文本框的的大小，并将其全部填充  
        g.fillRect(0, 0, getWidth(), getHeight());

        // 然后重用父类的绘图方法，比如绘制文本字符信息等  
        super.paintComponent(g);
    }
}
