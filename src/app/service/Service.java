package app.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;

import app.AppMain;
import app.gui.MainFrame;
import app.util.CustomUtil;
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
	String STATION_CSV_FILE_PATH = "./station.csv";
	// 지역명	
	String[] localArray = 
		{ "서울", "경기", "인천", "강원", "충남", "대전", "충북", 
				"세종", "부산", "울산", "대구", "경북", "경남", "전남", "전북","제주" };	
	List<String> 	locals = Arrays.asList(localArray);
	List<Station> 	stationList = new ArrayList<Station>();;
	
	
	public void getStationList(String url) {
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
		stationList = getStationInfo(url, local);		
		
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
					.timeout(0).execute();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpStatusException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stationList;
		
	}

	public void saveStationList() throws IOException {
		logger.info("saveStationList:"+stationList.size());
		try(
			Writer writer = Files.newBufferedWriter(Paths.get(STATION_CSV_FILE_PATH), StandardOpenOption.CREATE_NEW);
			//FileOutputStream fos = new FileOutputStream(STATION_CSV_FILE_PATH,false);
            //OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			CSVWriter csvWriter = new CSVWriter(writer,
	                   CSVWriter.DEFAULT_SEPARATOR,
	                   CSVWriter.NO_QUOTE_CHARACTER,
	                   CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                   CSVWriter.DEFAULT_LINE_END)
		){
			String[] headerRecord = {"stationName", "addr", "year", "mangName", "dmX", "dmY"};
			csvWriter.writeNext(headerRecord);
			
			stationList.forEach(st->{
				String stationName = st.getStationName();
				String addr = st.getAddr();
				String year = st.getYear()+"";
				String mangName = st.getMangName();
				String dmX = st.getDmX()+"";
				String dmY = st.getDmY()+"";
				csvWriter.writeNext(new String[]{stationName, addr, year, mangName, dmX, dmY});
				MainFrame.mainUI.appendMessage(st.toString(), 1);
			});
		}
		
	}
}
