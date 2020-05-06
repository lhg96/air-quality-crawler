package app;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.opencsv.CSVReader;

import arim.vo.Place;

/**
 * 
 * 20200423 airkorea station list upload
 * 
 * 
 * 시검색 스테이션 실시간 정보 
 * http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=%EC%84%9C%EC%9A%B8&pageNo=1&numOfRows=100&ver=1.3&serviceKey=Ir8wq7VY661b0Ka0RfCj%2F7xNbzv8f%2FSOqIwKcqM3kdsdhEmBRa1TZfGgamQZsoLt4ZSGeACbVtCwn9v90lqEhQ%3D%3D
 * 
 *  
 * 
 * @author arim-hyun
 *
 */
public class AirKoreaStationListUploader {
	private final static String baseUrl = "https://arimapi.appspot.com/api/place";
	//private final static String baseUrl = "http://localhost:8080/api/place";
	private final static String csvFile = "station_list.csv";

	public static void main(String[] args) {
		AirKoreaStationListUploader main = new AirKoreaStationListUploader();
		List<List<String>> linese = main.readCSV(csvFile);
		linese.remove(0);// header
		List<Place> placeList = main.convertList(linese);
		placeList.forEach(place -> {
			System.out.println(place);
		});
		main.upload(placeList);
	}

	// readCSV to String list
	private List<List<String>> readCSV(String fileName) {
		List<List<String>> records = new ArrayList<>();
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				records.add(Arrays.asList(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;
	}

	// strings to object
	private List<Place> convertList(List<List<String>> linese) {
		List<Place> placeList = new ArrayList<Place>();
		linese.forEach(line -> {
			try {
				Place place = toPlace(line);
				placeList.add(place);
			} catch (Exception eo) {
				System.out.println("string to place error:" + eo);
			}
		});
		return placeList;
	}

	private Place toPlace(List<String> data) {
		Place place = null;
		if (data.isEmpty())
			return null;
		String did 		= null;
		String local 	= data.get(0);
		String name 	= data.get(1);
		String addr 	= data.get(2);
		double latitude = parseDouble(data.get(3));
		double longitude = parseDouble(data.get(4));
		String operator = data.get(5);
		String installYear = data.get(6);

		place = new Place(null, name, local, addr, operator, installYear, latitude, longitude, null, null);
		return place;
	}

	private Double parseDouble(String data) {
		try {
			return Double.parseDouble(data);
		} catch (Exception eo) {
			eo.printStackTrace();
		}
		return null;
	}

	private void upload(List<Place> placeList) {		
		placeList.forEach(place->{
			try {
				WebTarget target = getWebTarget(baseUrl);
				String response = 
						target.request().post(Entity.entity(place, MediaType.APPLICATION_JSON),String.class);
				System.out.println(response);
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		
	}
	
	static WebTarget getWebTarget(String url) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		return client.target(url);
	}

}
