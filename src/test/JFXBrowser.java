package test;

import com.sun.javafx.application.PlatformImpl;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;

public class JFXBrowser extends JPanel {
	private static final String RUN_JAVASCRIPT = "Run JavaScript...";
	private static final String CLOSE_JAVASCRIPT = "Close JavaScript Console";
	private static final String DEFAULT_URL = "print.html";
	private JButton backwardButton;
	private JButton forwardButton;
	private JButton refreshButton;
	private JButton stopButton;
	private JMenuItem consoleMenuItem;
	private JTextField addressBar;
	private WebView view;
	private JFXPanel jfxPanel;
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public JFXBrowser() {

		PlatformImpl.startup(new Runnable() {

			@Override
			public void run() {
				jfxPanel = new JFXPanel();
				view = new WebView();
				String url = getClass().getClassLoader().getResource(DEFAULT_URL)
						.toString();
				
				final StackPane layout = new StackPane();

				Scene scene = new Scene(layout);

				layout.getChildren().addAll(view);

				jfxPanel.setScene(scene);
				jfxPanel.setVerifyInputWhenFocusTarget(true);
				view.getEngine().load(url);

			}
		});
		String url = getClass().getClassLoader().getResource(DEFAULT_URL)
				.toString();
		addressBar = new JTextField(url);
		this.backwardButton = createBackwardButton(view);
		this.forwardButton = createForwardButton(view);
		this.refreshButton = createRefreshButton(view);
		this.stopButton = createStopButton(view);

		setLayout(new BorderLayout());
		JToolBar toolbar = new JToolBar();
		toolbar.setBorderPainted(false);
		toolbar.setFloatable(false);
		toolbar.add(backwardButton);
		toolbar.add(refreshButton);
		toolbar.add(forwardButton);
		toolbar.addSeparator();
		
		add(toolbar, BorderLayout.NORTH);
		add(jfxPanel);

	}

	private static JButton createBackwardButton(final WebView view) {
		return createButton("back", new AbstractAction() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
				PlatformImpl.startup(new Runnable() {

					@Override
					public void run() {
						int currentIndex = view.getEngine().getHistory()
								.getCurrentIndex();
						if (currentIndex > 0) {
							view.getEngine().getHistory().go(currentIndex - 1);
						}

					}
				});

			}
		});
	}

	private static JButton createForwardButton(final WebView view) {
		return createButton("forward", new AbstractAction() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
				int currentIndex = view.getEngine().getHistory()
						.getCurrentIndex();
			}
		});
	}

	private static JButton createRefreshButton(final WebView view) {
		return createButton("refresh", new AbstractAction() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
				PlatformImpl.startup(new Runnable() {

					@Override
					public void run() {
						view.getEngine().reload();

					}
				});

			}
		});
	}

	private static JButton createStopButton(final WebView view) {
		return createButton("stop", new AbstractAction() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {

			}
		});
	}

	private static JButton createButton(String paramString,
			Action paramAction) {
		ActionButton localActionButton = new ActionButton(paramString,
				paramAction);
		String str = paramString.toLowerCase();
		// localActionButton.setIcon(getIcon(str + ".png"));
		// localActionButton.setRolloverIcon(getIcon(str + "-selected.png"));
		return localActionButton;
	}

	public static ImageIcon getIcon(String paramString) {
		return new ImageIcon(JFXBrowser.class.getResource("/" + paramString));
	}

	private static class ActionButton extends JButton {
		private ActionButton(String paramString, Action paramAction) {
			super();
			setContentAreaFilled(false);
			setBorder(BorderFactory.createEmptyBorder());
			setBorderPainted(false);
			setRolloverEnabled(true);
			setToolTipText(paramString);
			setText(null);
			setFocusable(false);
			setDefaultCapable(false);
		}
	}

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		initEnvironment();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame localJFrame = new JFrame("JavaFx Browser Demo");
				localJFrame.setDefaultCloseOperation(2);
				localJFrame.addWindowListener(new WindowAdapter() {
					public void windowOpened(
							WindowEvent paramAnonymousWindowEvent) {

					}
				});

				localJFrame.add(new JFXBrowser());
				localJFrame.setSize(300, 300);
				localJFrame.setLocationRelativeTo(null);
				/// localJFrame.setIconImage(getIcon("jxbrowser16x16.png").getImage());
				localJFrame.setVisible(true);
			}
		});

	}

	private static void initEnvironment()

	{
		// System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode",
		// "false");
		// System.setProperty("apple.laf.useScreenMenuBar", "true");
		// System.setProperty("com.apple.mrj.application.apple.menu.about.name",
		// "JxBrowser Demo");
		// System.setProperty("jxbrowser.ie.compatibility-disabled", "true");
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
	}
}
