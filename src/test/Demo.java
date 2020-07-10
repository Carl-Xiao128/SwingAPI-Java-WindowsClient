package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Demo extends JFrame{
	 
    private Container contentPane;
 
    public Demo() {
        initSelf();
        initComponets();
    }
 
    private void initSelf() {
        setBounds(216, 70, 850, 600);
        setDefaultCloseOperation(3);
        setLayout(null);
        contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);
        try {
            setIconImage(ImageIO.read(this.getClass().getResource("/sto1.png")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
 
    }
 
    private JScrollPane scroll_main;
    private JPanel jPanel_main;
 
    private void initComponets() {
        jPanel_main = new JPanel();
        jPanel_main.setBounds(0, 0, 600, 472);
        jPanel_main.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        jPanel_main.setBackground(Color.white);
        jPanel_main.setLayout(null);
        jPanel_main.setVisible(true);
 
        scroll_main = new JScrollPane(jPanel_main);
        scroll_main.setBounds(0, 0, 600, 472);
        contentPane.add(scroll_main);
 
        JButton button = new JButton("测试");
        button.setBounds(622, 12, 160, 50);
        contentPane.add(button);
        button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                 doSomeThing();
            }
             
        });
         
    }
 
    protected void doSomeThing() {
        (new Thread(){
            public void run(){
            int tempHeight1 = 20;
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("测试按钮1-"+i);
            button.setBounds(12, tempHeight1, 350, 20);
            button.setVisible(true);
 
            jPanel_main.add(button);
            jPanel_main.repaint();
 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
 
            tempHeight1 = tempHeight1 + 25 ;
        }
 
        scroll_main.repaint();
        scroll_main.revalidate();
        scroll_main.updateUI();
 
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("测试按钮2-"+i);
            button.setBounds(12, tempHeight1, 350, 20);
            jPanel_main.add(button);
 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jPanel_main.repaint();
            tempHeight1 = tempHeight1 + 25 ;
        }
        }
    }).start();
 
    }
 
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Demo().setVisible(true);;
            }
        });
    }
 
}