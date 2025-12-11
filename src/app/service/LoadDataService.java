package app.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import app.util.CustomUtil;
import arim.vo.PAir;
import arim.vo.Station;

public class LoadDataService{
	Logger logger = LoggerFactory.getLogger(LoadDataService.class);
	String PAIR_CSV_FILE_PATH = "./pair";
	
	public List<PAir> loadData() throws IOException{
		System.out.println("load air");
		List<PAir> airList = new ArrayList<PAir>();
		try(
				Reader reader = Files.newBufferedReader(Paths.get(
						PAIR_CSV_FILE_PATH+"_"+CustomUtil.currentDate()+".csv"));
				CSVReader csvReader = new CSVReader(reader);
			){
				 // Reading Records One by One in a String array
				int index = 0;
	            String[] nextRecord;            
	            while ((nextRecord = csvReader.readNext()) != null) {
	            	//System.out.println(Arrays.toString(nextRecord));
	            	//if(index>0) {//header
	            		String 	local 			= nextRecord[0];
		            	String 	stationName 	= nextRecord[1];
						
		            	String 	dateStr	 		= nextRecord[2];
						Date	dateTime		= CustomUtil.convertDate(dateStr);
						
						String  so2ValueStr		= nextRecord[3];
						double so2Value			= CustomUtil.convertDouble(so2ValueStr);
						
						String coValueStr		= nextRecord[4];
						double coValue			= CustomUtil.convertDouble(coValueStr);
						
						String o3ValueStr		= nextRecord[5];
						double o3Value			= CustomUtil.convertDouble(o3ValueStr);
						
						String no2ValueStr		= nextRecord[6];
						double no2Value			= CustomUtil.convertDouble(no2ValueStr);
						
						String pm10ValueStr		= nextRecord[7];
						double pm10Value		= CustomUtil.convertDouble(pm10ValueStr);
						
						String pm25ValueStr		= nextRecord[8];
						double pm25Value		= CustomUtil.convertDouble(pm25ValueStr);
						
						PAir pair = new PAir(local,stationName,dateTime,so2Value, coValue, o3Value, no2Value, pm10Value, pm25Value);
						
						airList.add(pair);
	            	//}
	            	index++;				
	            }			
		} catch (CsvValidationException e) {
			logger.error("CSV validation error: " + e.getMessage(), e);
			throw new IOException("Failed to validate CSV data", e);
		}
	
	return airList;
	}
}