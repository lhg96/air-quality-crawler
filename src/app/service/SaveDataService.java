package app.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arim.vo.PAir;

public class SaveDataService{
	Logger logger = LoggerFactory.getLogger(SaveDataService.class);
	String PAIR_CSV_FILE_PATH = "./pair.csv";
	
	public void saveData(List<PAir> airList) throws IOException{
		logger.info("saveAirList:"+airList.size());		
	}
}
