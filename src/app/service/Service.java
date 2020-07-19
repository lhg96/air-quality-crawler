package app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.gui.MainFrame;
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
	
	CrawlingDataService			crawlingData;
	SaveDataService				saveData;
	LoadDataService				loadData;
	
	//DAO 로 나중에는 데이터 형 분리하기
	public List<Station> 	stationList = new ArrayList<Station>();
	public List<PAir>		pairList	= new ArrayList<PAir>();

	public boolean 	autoSave		= false;
	//-------------------Station Info Service----------------------------------
	public void loadStations() throws IOException {		
		if(loadStation==null) loadStation = new LoadStationInfoService();
		stationList = loadStation.loadStation();
		MainFrame.mainUI.appendMessage("Load Stations:"+stationList.size(), 1);		
	}
	/**
	 * thread 처리가 필요해서 별도의 
	 * @param url
	 */
	public void crawlingStations(String url) throws IOException{
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
		MainFrame.mainUI.appendMessage("Save Stations:"+stationList.size(), 1);
		
	}
	//-------------------Air Info Service----------------------------------	
	public void loadData() throws IOException{		
		if(loadData==null) loadData = new LoadDataService();
		pairList = loadData.loadData();
		MainFrame.mainUI.appendMessage("Load Airs:"+pairList.size(), 1);
	}
	
	public void saveData() throws IOException{
		if(saveData==null) {
			saveData = new SaveDataService();
		}
		saveData.saveData(pairList);
		MainFrame.mainUI.appendMessage("Save datas:"+pairList.size(), 1);
		
	}
	
	public void crawlingDatas(String url)  throws IOException{		
		if(crawlingData==null) {
			crawlingData = new CrawlingDataService(url, this);
			crawlingData.start();
		}else {
			if(!crawlingData.isRunning()) {
				crawlingData = new CrawlingDataService(url, this);
				crawlingData.start();
			}else {
				//경고 popup				
				MsgBox.info("Crawling Data Running", "Waring");
			}
		}	
		
		
	}
	public void stopCrawling()   throws IOException{
		if(crawlingData!=null) {
			crawlingData.stop();
		}
	}
	
	
	//--------------upload---------------------------------------------------
	public void uploadData() throws IOException{		
	}
	
	MTimerTask mTimer;	
	public void startTimer(int period) {
		autoSave =true;
		logger.info("Start Timer:"+period+" sec");
		if(mTimer==null) {
			mTimer = new MTimerTask(this);			
		}
		
		if(!mTimer.isRunning()) {
			mTimer.start(period);
		}else {
			//경고 popup				
			MsgBox.info("Timer Running", "Waring");
		}		
	}
	
	public void stopTimer() {
		autoSave = false;
		logger.info("Stop Timer");
		if(mTimer!=null) {
			mTimer.stop();
			mTimer = null;
			System.gc();
		}
	}
}




