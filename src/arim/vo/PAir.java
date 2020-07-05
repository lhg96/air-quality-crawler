package arim.vo;

import java.util.Date;

/**
 * 공공데이터 정보
 * @author user
 *
 */
public class PAir {
	String stationName;
	Date   dateTime;
	double so2Value;
	double coValue;
	double o3Value;
	double no2Value;
	double pm10Value;
	double pm25Value;
			
	public PAir(String stationName, Date dateTime, double so2Value, double coValue, double o3Value, double no2Value,
			double pm10Value, double pm25Value) {
		super();
		this.stationName = stationName;
		this.dateTime = dateTime;
		this.so2Value = so2Value;
		this.coValue = coValue;
		this.o3Value = o3Value;
		this.no2Value = no2Value;
		this.pm10Value = pm10Value;
		this.pm25Value = pm25Value;
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public double getSo2Value() {
		return so2Value;
	}
	public void setSo2Value(double so2Value) {
		this.so2Value = so2Value;
	}
	public double getCoValue() {
		return coValue;
	}
	public void setCoValue(double coValue) {
		this.coValue = coValue;
	}
	public double getO3Value() {
		return o3Value;
	}
	public void setO3Value(double o3Value) {
		this.o3Value = o3Value;
	}
	public double getNo2Value() {
		return no2Value;
	}
	public void setNo2Value(double no2Value) {
		this.no2Value = no2Value;
	}
	public double getPm10Value() {
		return pm10Value;
	}
	public void setPm10Value(double pm10Value) {
		this.pm10Value = pm10Value;
	}
	public double getPm25Value() {
		return pm25Value;
	}
	public void setPm25Value(double pm25Value) {
		this.pm25Value = pm25Value;
	}

	@Override
	public String toString() {
		return "PAir [stationName=" + stationName + ", dateTime=" + dateTime + ", so2Value=" + so2Value + ", coValue="
				+ coValue + ", o3Value=" + o3Value + ", no2Value=" + no2Value + ", pm10Value=" + pm10Value
				+ ", pm25Value=" + pm25Value + "]";
	}
	
	
	
	
}
