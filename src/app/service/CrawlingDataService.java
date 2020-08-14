package app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.gui.MainFrame;
import app.util.CustomUtil;
import arim.vo.PAir;
import arim.vo.Station;

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
		service.pairList = new ArrayList<PAir>();//초기화
		
		for(int  i=0;i<locals.size();i++) {			
			logger.info(locals.get(i)+" crawling");			
			try {
				//지역별 수집				
				service.pairList.addAll(getPAir(url, locals.get(i)));
				MainFrame.mainUI.appendMessage(locals.get(i)+" crawling data", 1);
				Thread.sleep(5000);//동시 호출시 에러발생 자동 커텍트 방지				
			} catch (Exception e) {			
				e.printStackTrace();
			}			
		}		
		running.set(false);
		
		//add
		if(service.autoSave) {
			try {
				service.saveData();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 * 
	 */
	private List<PAir> getPAir(String url, String local){
		logger.info("Get PAir");
		url = url+local;	
		MainFrame.mainUI.appendMessage("Connect", 1);
		MainFrame.mainUI.appendMessage(url, 1);		
		List<PAir> airList = new ArrayList<PAir>();
		//add
		try {
			Connection.Response response = Jsoup
					.connect(url)					
					.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.timeout(5000).execute();
			int statusCode = response.statusCode();
			if (statusCode == 200) {
				Document dok = Jsoup.parse(response.body(), url);
				Elements stationElements = dok.select("body items item");
				
				//item item 이 읽혀지지 않음
				stationElements.forEach(stationElement->{
					String stationName	= stationElement.select("stationName").text();
					
					String dataTimeStr	= stationElement.select("dataTime").text();
					Date   dateTime		= CustomUtil.convertDate(dataTimeStr);
					//date convert
					String so2ValueStr	= stationElement.select("so2Value").text();
					double so2Value			= CustomUtil.convertDouble(so2ValueStr);
					
					String coValueStr	= stationElement.select("coValue").text();
					double coValue			= CustomUtil.convertDouble(coValueStr);
					
					String o3ValueStr	= stationElement.select("o3Value").text();
					double o3Value			= CustomUtil.convertDouble(o3ValueStr);
					
					String no2ValueStr	= stationElement.select("no2Value").text();
					double no2Value			= CustomUtil.convertDouble(no2ValueStr);
					
					String pm10ValueStr	= stationElement.select("pm10Value").text();
					double pm10Value			= CustomUtil.convertDouble(pm10ValueStr);
					
					String pm25ValueStr	= stationElement.select("pm25Value").text();
					double pm25Value			= CustomUtil.convertDouble(pm25ValueStr);
					
					PAir pair = new PAir(local,stationName,dateTime,so2Value, coValue, o3Value, no2Value, pm10Value, pm25Value);					
					if(!stationName.isEmpty()) {
						MainFrame.mainUI.appendMessage(pair.toString(), 1);
						airList.add(pair);
					}
				});				
			}
		
		} catch (NullPointerException e) {			
			e.printStackTrace();
		} catch (HttpStatusException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return airList;
	}
	
}
