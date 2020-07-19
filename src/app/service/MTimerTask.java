package app.service;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.gui.MainFrame;

public class MTimerTask extends TimerTask{
	Logger logger = LoggerFactory.getLogger(MTimerTask.class);
	
	private Timer timer; 
	private Service service;
	final AtomicBoolean running = new AtomicBoolean(false);
	
	public MTimerTask(Service service) {
		this.service 	= service;	
	}

	@Override
	public void run() {
		logger.info("------------------timer run---------------------------------");
		MainFrame.mainUI.appendMessage("Timer Run", 1);
		String url = MainFrame.mainUI.textField2.getText();
		try {
			service.crawlingDatas(url);			
		} catch (IOException e) {
			logger.error(e.toString());
		}		
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {		
			e1.printStackTrace();
		}
		
		while(!service.crawlingData.isRunning()) {
			try {				
				service.saveData();
			} catch (IOException e) {
				logger.error(e.toString());
			}	
		}
		*/
		
		System.gc();
	}

	public boolean isRunning() {
		return running.get();
	}
	
	public void start(int period) {	
		MainFrame.mainUI.appendMessage("Timer Start:"+period+" SEC", 1);
		timer = new Timer(true);
		timer.schedule(this, 0, period*1000);
		running.set(true);
	}
	
	 public void stop() {
		 MainFrame.mainUI.appendMessage("Timer Stop", 1);
		 try {
			service.stopCrawling();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		 timer.cancel();
		 running.set(false); 
	 }

}
