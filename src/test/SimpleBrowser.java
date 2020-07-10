package test;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.net.MalformedURLException;
import org.jdesktop.jdic.browser.*;

/**
 * JDIC API demo main class.
 * <p>
 * SimpleBrowser demonstrate the usage of JDIC API package org.jdesktop.jdic.browser
 * (Browser component).
 */

public class SimpleBrowser {
    // Below method reads, changes javascript variables and attributes of DOM elements.
    private static void testDOMAPI(WebBrowser webBrowser) {           
        System.out.println("===========================================================");
        System.out.println("=== Test setContent()/getContent()/executeScript() APIs ===");
        System.out.println("===========================================================");
                       
        // Add Chinese/Korean/Japan characters to the content to test unicode 
        // support.
        String HTML_CONTENT =
            "<html>" +
            "<head>" +
              "<script>" +
                "var counter = 100;" +
                "function scriptMethod() { " +
                    "alert('scriptMethod() within the loaded page'); " +
                "}" +
              "</script>" +
            "</head>" +
            "<title>Test Page for JDIC Browser Component</title>" +
            "" +
            "<body>" +
            "<div id='theDiv'>This page content is set using setContent() API</div>" +
            "</body>" +
            "</html>";

        System.out.println("===============================");
        System.out.println("=== To test executeScript() ===");
        System.out.println("===============================");
        String jscript = "alert(\"alert 'statement' test\");" +
            "document.bgColor='blue';";        
        String result = webBrowser.executeScript(jscript);
        System.out.println("Execution of: " + jscript + " returns: " + result);

        System.out.println("============================");
        System.out.println("=== To test getContent() ===");
        System.out.println("============================");
        String content = webBrowser.getContent();
        System.out.println("getContent() returns: " + content);           

        System.out.println("============================");
        System.out.println("=== To test setContent() ===");   
        System.out.println("============================");    
        System.out.println("To setContent(): " + HTML_CONTENT);
        webBrowser.setContent(HTML_CONTENT);

        jscript = "scriptMethod();";
        System.out.println("Execute JavaScript method within the current page ...");        
        result = webBrowser.executeScript(jscript);        
        
        String retContent = webBrowser.getContent();
        System.out.println("getContent() returns: " + retContent);        
        
        // getContent may return the same content in different upper/lower 
        // case or single/double quotation mark.
        // Here we assume the content is equal if the content lengh is equal.        
        if (retContent != null
            && retContent.length() == HTML_CONTENT.length()) {
            System.out.println("=== SUCCEED: getContent() correctly returns "
                + "the content set by setContent() !!!");
        } else {
            System.out.println("=== ERROR: getContent() doesn't return the "
                + "content set by setContent() ???");
        }       

    }
        
    public static void main(String[] args) {
        JFrame frame = new JFrame("JDIC API Demo - SimpleBrowser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final WebBrowser webBrowser = new WebBrowser();
        
        //Use below code to check the status of the navigation process,
        //or register a listener for the notification events.
        webBrowser.addWebBrowserListener(
            new WebBrowserListener() {
            boolean isFirstPage = true;
						public void initializationCompleted(WebBrowserEvent event){;}            
            public void downloadStarted(WebBrowserEvent event) {;}
            public void downloadCompleted(WebBrowserEvent event) {;}
            public void downloadProgress(WebBrowserEvent event) {;}
            public void downloadError(WebBrowserEvent event) {;}
            public void documentCompleted(WebBrowserEvent event) {
                // Uncomment below code to test getContent()/setContent()/
                // executeScript() APIs.
                // As the setContent() call will invoke this event, which falls
                // into a loop, so check if this event is fired by the first
                // loaded page.
                /*
                if (isFirstPage) {
                    testDOMAPI(webBrowser);
                    isFirstPage = false;
                }
                */
            }
            public void titleChange(WebBrowserEvent event) {;}  
            public void statusTextChange(WebBrowserEvent event) {;}        
        });

        try {
            webBrowser.setURL(new URL("http://java.net"));
            // Below Chinese website tests unicode support.
            //webBrowser.setURL(new URL("http://www.google.com/intl/zh-CN/"));
            
            // Print out debug messages in the command line.
            //webBrowser.setDebug(true);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return;
        }               
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(700, 500));
        panel.add(webBrowser, BorderLayout.CENTER);
        
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);        
    }
}
