package app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log 저장 방법
 * https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=221341280123&proxyReferer=https%3A%2F%2Fwww.google.com%2F
 * 
 * 
 * serial
 * https://eclipsesource.com/blogs/2012/10/17/serial-communication-in-java-with-raspberry-pi-and-rxtx/
 *
 * 2020
 * @author user
 *
 */
public class App {
	public static void main(String[] args) {
		App main = new App();
		
		//main.logTest();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException ex) {
						ex.printStackTrace();
					}

					MainFrame frame = new MainFrame("AirKorea crawler");
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
		try {
			// consoleLogger.info("Contents : {}", "Logger Test");
			MLog.console("Logger testing");
			double d = 1 / 0; // 0으로 나누기 -> Exception 발생!
		} catch (Exception e) {
			// invalidFileLogger.warn("INVALID : {}", e);
			// MLog.write(e.toString());
			MLog.write("INVALID : {}", e);

		}
	}

	// util
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

}
