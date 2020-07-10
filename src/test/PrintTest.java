package test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import org.jdesktop.jdic.browser.BrowserEngineManager;
import org.jdesktop.jdic.browser.WebBrowser;

@SuppressWarnings("serial")
public class PrintTest extends JFrame implements HyperlinkListener  
{  
   public PrintTest() throws Exception  
   {  
      setSize(640, 480);      setTitle("隔叶黄莺:The Blog of Unmi");  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      JEditorPane editorPane = new JEditorPane();  
        
      //放到滚动窗格中才能滚动查看所有内容  
      JScrollPane scrollPane = new JScrollPane(editorPane);  
      //设置是显示网页 html 文件,可选项  
      //editorPane.setContentType("text/html");  
      //设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面  
      editorPane.setEditable(false);  
      //要能响应网页中的链接，则必须加上超链监听器  
      editorPane.addHyperlinkListener(this);  
      URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();  
      URL url = urlLoader.findResource("print.html");//可以用html格式文件做你的帮助系统了  
      System.out.println(url);
      editorPane.setPage(url); 
      Container container = getContentPane();  
        
      //让editorPane总是填满整个窗体  
      container.add(scrollPane, BorderLayout.CENTER);  
   }  
   //超链监听器，处理对超级链接的点击事件，但对按钮的点击还捕获不到  
   public void hyperlinkUpdate(HyperlinkEvent e)  
   {  
      if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)  
      {  
         JEditorPane pane = (JEditorPane) e.getSource();  
         if (e instanceof HTMLFrameHyperlinkEvent)  
         {  
            HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;  
            HTMLDocument doc = (HTMLDocument) pane.getDocument();  
            doc.processHTMLFrameHyperlinkEvent(evt);  
         }  
         else  
         {  
            try  
            {  
               pane.setPage(e.getURL());  
            }  
            catch (Throwable t)  
            {  
               t.printStackTrace();  
            }  
         }  
      }  
   }  
     
   public static void main(String[] args) throws Exception  
   {  
      JFrame frame = new PrintTest();  
      frame.setVisible(true);  
   }  
//	
//	  public static void main(String args[]) throws Exception
//	    {
//	        File file = new File("html/pc.html");
//	        Runtime ce=Runtime.getRuntime();
//	        System.out.println(file.getAbsolutePath());
//	        ce.exec("cmd   /c   start  "+file.getAbsolutePath());
//	    }
//}
//   static {  
//       BrowserEngineManager engineManager = BrowserEngineManager.instance();  
//       engineManager.setActiveEngine(BrowserEngineManager.IE);  
//   }  
//   public static void main(String[] args) throws Exception {  
//	   WebBrowser browser = new WebBrowser();  
//       browser.setURL(new URL("http://www.youku.com "));  
//         
//       JFrame frame = new JFrame("Browser Test");  
//       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//       frame.getContentPane().add(browser);  
//       frame.pack();  
//       frame.setSize(500,500);  
//       frame.setVisible(true);  
//   }  
}  