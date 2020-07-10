package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 *
 * @描述：自定义各种边框圆角
 * @创建人：kitxiao
 * @创建时间：2016年8月21日上午12:58:05
 */
public class RoundBorder implements Border {

	public Insets getBorderInsets(Component c) {
		return new Insets(0, 0, 0, 0);
	}

	public boolean isBorderOpaque() {
		return false;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// 使用黑色在组件的外边缘绘制一个圆角矩形
		g.setColor(Color.gray);
		g.drawRoundRect(0, 0, c.getWidth() - 2, c.getHeight() - 2, 5, 5);
	}

}
