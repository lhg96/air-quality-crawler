package app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.service.CrawlingStationInfoService;

public class CustomUtil {
	static Logger logger = LoggerFactory.getLogger(CustomUtil.class);
	
	public static String convertMilliSecondsToFormattedDate(long milliSeconds) {
		String dateFormat = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return simpleDateFormat.format(calendar.getTime());
	}
	
	public static String currentDate() {
		//"yyyyMMdd_HHmmss"
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public static double convertDouble(String inStr) {
		double value = -1;
		if (inStr != null && inStr.trim().length() > 0) {
			try {
				value = Double.parseDouble(inStr);
			} catch (NumberFormatException e) {
				//logger.error(e.toString());
				
			}
		}
		return value;
	}
	

	public static int convertInt(String inStr) {
		int value = -1;
		if (inStr != null && inStr.trim().length() > 0) {
			try {
				//value = Double.parseDouble(inStr);
				value = Integer.parseInt(inStr);
			} catch (NumberFormatException e) {
				//logger.error(e.toString());
			}
		}
		return value;
	}



	public static Date convertDate(String dataTimeStr) {
		String dateFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dataTimeStr);
		} catch (ParseException e) {			
			//logger.error(e.toString());
		}
		return date;
	}
	
	public static String convertDateToString(Date inDate) {
		String dateFormat = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		String dateStr = null;
		try {
			dateStr = simpleDateFormat.format(inDate);			
		} catch (Exception e) {			
			logger.error(e.toString());
		}
		return dateStr;
	}
	
}
