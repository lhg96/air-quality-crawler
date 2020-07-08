package app.service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

import com.opencsv.CSVWriter;


import app.gui.MainFrame;
import app.util.CustomUtil;
import app.util.MsgBox;
import arim.vo.PAir;
import arim.vo.Station;

/**
 * 
 * Jsoup crawling ex
 * https://stackoverflow.com/questions/35498379/using-jsoup-to-connect-to-url-but-jsoup-call-another-url-why
 * 
 * 
 * 20200428 *
 * 
 * @author arim-hyun
 *
 */
public class Service {
	Logger logger = LoggerFactory.getLogger(Service.class);	
		
	//service
	CrawlingStationInfoService 	crawlingInfo;
	SaveStationInfoService		saveStation;
	LoadStationInfoService		loadStation;
	
	//DAO 로 나중에는 데이터 형 분리하기
	public List<Station> 	stationList = new ArrayList<Station>();
	public List<PAir>		pairList	= new ArrayList<PAir>();

	
	//-------------------Station Info Service----------------------------------
	public void loadStations() throws IOException {
		if(loadStation==null) loadStation = new LoadStationInfoService();
		stationList = loadStation.loadStation();
		stationList.forEach(station->{
			System.out.println(station);
		});
		
		
	}
	/**
	 * thread 처리가 필요해서 별도의 
	 * @param url
	 */
	public void crawlingStations(String url) {
		if(crawlingInfo==null) {
			crawlingInfo = new CrawlingStationInfoService(url, this);
			crawlingInfo.start();
		}else {
			if(!crawlingInfo.isRunning()) {
				crawlingInfo = new CrawlingStationInfoService(url, this);
				crawlingInfo.start();
			}else {
				//경고 popup				
				MsgBox.info("Crawling Station Info", "Waring");
			}
		}
		
	}
	
	//public void addStations(List<Station> newStationInfo) {
	//	stationList.addAll(newStationInfo);		
	//}
	
	/**
	 * save button working 
	 * save csv file
	 * @throws IOException 
	 */
	public void saveStations() throws IOException {
		if(saveStation==null) {
			saveStation = new SaveStationInfoService();			
		}		
		saveStation.saveStation(stationList);
		
	}
	//-------------------Air Info Service----------------------------------	
	public void getRealTimeDatas(String url) {
		/*
		for(int  i=0;i<locals.size();i++) {			
			logger.info(locals.get(i)+" crawling");
			getStationInfo(url, locals.get(i));
			try {
				Thread.sleep(5000);//동시 호출시 에러발생 자동 커텍트 방지
				logger.info("delay 5000");
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
		}*/		
		String local = "대전";
		pairList = getRealTimeData(url, local);
	}
	private List<PAir> getRealTimeData(String url, String local) {
		logger.info("GetStationData");
		url = url+local;	
		MainFrame.mainUI.appendMessage("Connect", 1);
		MainFrame.mainUI.appendMessage(url, 1);		
		return null;
	}
}




