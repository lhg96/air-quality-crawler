package app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arim.vo.PAir;
import arim.vo.Station;

public class LoadDataService{
	Logger logger = LoggerFactory.getLogger(LoadDataService.class);
	String PAIR_CSV_FILE_PATH = "./pair.csv";
	
	public List<PAir> loadData() throws IOException{
		System.out.println("load air");
		List<PAir> airList = new ArrayList<PAir>();
		
		return airList;
	}
}
