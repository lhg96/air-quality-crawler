package app.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.gui.MainFrame;
import arim.vo.PAir;

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
	
	// 지역명
	/*String[] 		localArray = 
				{ "서울", "경기", "인천", "강원", "충남", "대전", "충북", 
						"세종", "부산", "울산", "대구", "경북", "경남", "전남", "전북","제주" };
	 */							
	String[] 		localArray = { "대전" };
	List<String> 	locals = Arrays.asList(localArray);
	
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
		//여기 작업할것 20200714
		System.out.println("----------------test1234--------------------");
		for(int  i=0;i<locals.size();i++) {			
			logger.info(locals.get(i)+" crawling");			
			try {
				//지역별 수집				
				//service.pairList.addAll(getStationInfo(url, locals.get(i)));
				MainFrame.mainUI.appendMessage(locals.get(i)+" crawling data", 1);
				Thread.sleep(5000);//동시 호출시 에러발생 자동 커텍트 방지
				//logger.info("delay 5000");
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
		}		
		running.set(false);	
	}
	
	
	/*
	private List<PAir> getPAir(String url, String local){
		
	}*/
	
}
