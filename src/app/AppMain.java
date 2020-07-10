package app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.gui.MainFrame;

/**
 * Log 저장 방법
 * https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=221341280123&proxyReferer=https%3A%2F%2Fwww.google.com%2F
 * 
 * serial
 * https://eclipsesource.com/blogs/2012/10/17/serial-communication-in-java-with-raspberry-pi-and-rxtx/
 *
 * 2020
 * @author user
 * 
 * 20200531
 * log 저장 기능
 * 
 * 20200707
 * property 읽기 기능
 *
 */
public class AppMain {
	private static Logger 	logger = LoggerFactory.getLogger(AppMain.class);
	private static String 	title = "AirKorea  Crawler 대전";

	public static void main(String[] args) {
		AppMain main = new AppMain();
		//properties
		try {
			FileReader reader=new FileReader("application.properties");
		    Properties p=new Properties();  
		    p.load(reader);
		    p.list(System.out);
		    //System.out.println(p.getProperty("user"));  
		    //System.out.println(p.getProperty("password"));  
		} catch (IOException e) {	
			logger.error(e.toString());
		}
		//main.logTest();
		logger.info("start app");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException ex) {
						ex.printStackTrace();
					}

					MainFrame frame = new MainFrame(title);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void logTest() {
		logger.info("test1");
		logger.trace("Hello world.");
	    logger.debug("Hello world."); //debug level로 해당 메시지의 로그를 찍겠다.
	    logger.info("Hello world.");
	    logger.warn("Hello world.");
	    logger.error("Hello world.");
	}

	// util
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

}
