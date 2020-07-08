package app.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;

import app.util.CustomUtil;
import arim.vo.Station;

public class LoadStationInfoService {
	Logger logger = LoggerFactory.getLogger(LoadStationInfoService.class);
	String STATION_CSV_FILE_PATH = "./station.csv";
	
	public List<Station> loadStation() throws IOException{
		System.out.println("load station");
		List<Station> stationList = new ArrayList<Station>();
		
		try(
			Reader reader = Files.newBufferedReader(Paths.get(STATION_CSV_FILE_PATH));
			CSVReader csvReader = new CSVReader(reader);
		){
			 // Reading Records One by One in a String array
			int index = 0;
            String[] nextRecord;            
            while ((nextRecord = csvReader.readNext()) != null) {
            	//System.out.println(Arrays.toString(nextRecord));
            	if(index>0) {
	            	String 	stationName 	= nextRecord[0];
					String 	addr	 		= nextRecord[1];
					int  	year 			= CustomUtil.convertInt(nextRecord[2]);
					String 	mangName 		= nextRecord[3];
					double 	dmX 			= CustomUtil.convertDouble(nextRecord[4]);
					double 	dmY 			= CustomUtil.convertDouble(nextRecord[5]);
					Station station = new Station(stationName, addr, year, mangName,"" , dmX, dmY);
					stationList.add(station);
            	}
            	index++;				
            }			
		}
		return stationList;
	}

}
