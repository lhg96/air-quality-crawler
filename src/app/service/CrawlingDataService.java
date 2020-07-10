package app.service;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Crawling Data
 * 2020-07-10 
 * 
 * @author arim-hyun
 *
 */
public class CrawlingDataService implements Runnable {
	Logger logger = LoggerFactory.getLogger(CrawlingStationInfoService.class);
	final 	AtomicBoolean running = new AtomicBoolean(false);	
	private Thread 	worker;
	private Service service;
	private String 	url;
	
	public CrawlingDataService(String url,Service service){	
		this.url 		= url;
		this.service 	= service;	
	}
	
	public boolean isRunning(){
		return running.get();
	}
	
	public void start(){
        worker = new Thread(this);
        worker.start();
    }
  
    public void stop(){    	
        running.set(false);   
    }
	
	@Override
	public void run() {	
		running.set(true);
		//여기 작업할것
		running.set(false);	
	}
	
}
