package app;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
	// 지역명
	String[] localArray = { "서울", "경기", "인천", "강원", "충남", "대전", "충북", "세종", "부산", "울산", "대구", "경북", "경남", "전남", "전북",
			"제주" };
	List<String> locals = Arrays.asList(localArray);

	public void getStationList(String url) {
		/*
		locals.forEach(local -> {
			
		});*/
		
		getStationInfo(url, "대전");
	}

	private void getStationInfo(String url, String local) {
		System.out.println("GetStationInfo");
		url = url+local;	
		MainFrame.mainUI.appendMessage("Connect", 1);
		MainFrame.mainUI.appendMessage(url, 1);		
		try {
			Connection.Response response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(0).execute();
			int statusCode = response.statusCode();
			if (statusCode == 200) {
				Document dok = Jsoup.parse(response.body(), url);
				System.out.println("opened page: " + url);
				
				System.out.println(dok.text());
				MainFrame.mainUI.appendMessage("Receive", 1);
				MainFrame.mainUI.appendMessage(dok.toString(), 1);		
				

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
		
	}

	public void saveStationList() {
		// TODO Auto-generated method stub
		
	}
}
