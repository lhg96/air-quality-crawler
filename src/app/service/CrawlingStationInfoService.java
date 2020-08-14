package app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import arim.vo.Station;

/**
 * 지역 스테이션 정보 가져오기
 * @author hyun
 *
 */
public class CrawlingStationInfoService implements Runnable{
	Logger logger = LoggerFactory.getLogger(CrawlingStationInfoService.class);	
	private Thread worker;
	final AtomicBoolean running = new AtomicBoolean(false);
	private Service service;
	private String url;
	
	// 지역명	
	
	String[] localArray = 
			{ "서울", "경기", "인천", "강원", "충남", "대전", "충북", 
					"세종", "부산", "울산", "대구", "경북", "경남", "전남", "전북","제주" }; 
					
						
	//String[] localArray = { "대전" };	
	List<String> 	locals = Arrays.asList(localArray);

	public CrawlingStationInfoService(String url,Service service) {
		this.url 		= url;
		this.service 	= service;		
	}
	
	public boolean isRunning() {
		return running.get();
	}
	
	public void start() {
        worker = new Thread(this);
        worker.start();
    }
  
    public void stop() {    	
        running.set(false);   
    }
        	
	public void run() {
		running.set(true);
		service.stationList = new ArrayList<Station>();		
		
		for(int  i=0;i<locals.size();i++) {			
			logger.info(locals.get(i)+" crawling");			
			try {
				//지역별 수집
				//service.addStations(getStationInfo(url, locals.get(i)));
				service.stationList.addAll(getStationInfo(url, locals.get(i)));
				MainFrame.mainUI.appendMessage(locals.get(i)+" crawling", 1);
				Thread.sleep(5000);//동시 호출시 에러발생 자동 커텍트 방지
				//logger.info("delay 5000");
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
		}
		running.set(false);		
		//for only 대전
		//String local = "대전";
		//stationList = getStationInfo(url, local);		
	}	
	
	private List<Station> getStationInfo(String url, String local) {
		logger.info("GetStationInfo");
		url = url+local;	
		MainFrame.mainUI.appendMessage("Connect", 1);
		MainFrame.mainUI.appendMessage(url, 1);		
		
		List<Station> stationList = new ArrayList<Station>();
		try {
			Connection.Response response = Jsoup
					.connect(url)
					//.userAgent("Mozilla/5.0")
					.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.timeout(5000).execute();
			int statusCode = response.statusCode();
			if (statusCode == 200) {
				Document dok = Jsoup.parse(response.body(), url);
				System.out.println("opened page: " + url);
				
				MainFrame.mainUI.appendMessage("Receive", 1);
				MainFrame.mainUI.appendMessage(dok.toString(), 1);		
				
				Elements stationElements = dok.select("body items item");
				
				//item item 이 읽혀지지 않음
				stationElements.forEach(stationElement->{
					String stationName	= stationElement.select("stationName").text();
					String addr			= stationElement.select("addr").text();
					String yearStr 		= stationElement.select("year").text();					
					int year			= CustomUtil.convertInt(yearStr);
					String oper			= stationElement.select("oper").text();
					String mangName		= stationElement.select("mangName").text();		
					
					String dmXStr 		= stationElement.select("dmX").text();					
					double dmX			= CustomUtil.convertDouble(dmXStr);
					String dmYStr		= stationElement.select("dmY").text();
					double dmY			= CustomUtil.convertDouble(dmYStr);
					Station newSt = new Station(stationName, addr, year, mangName,"", dmX, dmY);
					if(!stationName.isEmpty()) {
						MainFrame.mainUI.appendMessage(newSt.toString(), 1);
						stationList.add(newSt);
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
		return stationList;		
	}
	
}
