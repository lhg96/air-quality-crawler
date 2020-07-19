package app.service;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;

import app.gui.MainFrame;
import app.util.CustomUtil;
import arim.vo.PAir;

public class SaveDataService{
	Logger logger = LoggerFactory.getLogger(SaveDataService.class);
	String PAIR_CSV_FILE_PATH = "./pair";
	
	public void saveData(List<PAir> airList) throws IOException{
		logger.info("saveAirList:"+airList.size());		
		try(
				/*
			FileOutputStream fos 	=  new FileOutputStream(PAIR_CSV_FILE_PATH+"_"+CustomUtil.currentDate()+".csv");
			Writer 			 writer =  new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			CSVWriter csvWriter = new CSVWriter(writer,
		                  CSVWriter.DEFAULT_SEPARATOR,
		                  CSVWriter.NO_QUOTE_CHARACTER,
		                  CSVWriter.DEFAULT_ESCAPE_CHARACTER,
		                  CSVWriter.DEFAULT_LINE_END)*/
				CSVWriter csvWriter = new CSVWriter(new FileWriter(PAIR_CSV_FILE_PATH+"_"+CustomUtil.currentDate()+".csv", true))				
					){
					//String[] headerRecord = {"stationName", "Date", "so2", "co", "o3", "no2", "pm10", "pm25"};
					//csvWriter.writeNext(headerRecord);
					
					airList.forEach(air->{
							String local		= air.getLocal();
							String stationName 	= air.getStationName();
							String dateStr 	   	= CustomUtil.convertDateToString(air.getDateTime());
							String so2			= air.getSo2Value()+"";
							String co			= air.getCoValue()+"";
							String o3			= air.getO3Value()+"";
							String no2			= air.getNo2Value()+"";
							String pm10			= air.getPm10Value()+"";
							String pm25			= air.getPm25Value()+"";
							
							csvWriter.writeNext(new String[]{local,stationName, dateStr, so2, 
									co, o3, no2, pm10, pm25});
							MainFrame.mainUI.appendMessage(air.toString(), 1);
				});			
			}
	}
}
