package app.service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;

import app.gui.MainFrame;
import arim.vo.Station;

/**
 * station list save
 * 
 * @author arim-hyun
 *
 */
public class SaveStationInfoService {
	Logger logger = LoggerFactory.getLogger(SaveStationInfoService.class);
	String STATION_CSV_FILE_PATH = "./station.csv";
	
	public void saveStation(List<Station> 	stationList) throws IOException {
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
