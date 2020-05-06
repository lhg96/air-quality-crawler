package arim.vo;

import java.util.Date;

//import com.google.appengine.repackaged.org.joda.time.DateTime;

/**
 * 공기 질 모니터 기본 값 
 * for 삼현여고 대상
 *
 * Timestamp example
 * https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/datastore/src/test/java/com/google/datastore/snippets/ConceptsTest.java
 
 * @author hyun
 *20190416
*/
public class Air{
	String aid; 		// device id pk  //반 이름 아니면 지정 이름
	// ---
	String did; 		// sub key
	double dust10; 		// dust1.0
	double dust25; 		// dust2.5
	double dust100;	 	// dust100
	double voc; 		// voc
	double co; 			// co
	double radon; 		// radon
	double hum; 		// 습도
	double temp; 		// 온도	
	
	Date regDate;
	
	public Air() {
		super();
	}
	
	public Air(String aid, String did, double dust10, double dust25, double dust100, double voc,
			double co, double radon, double hum, double temp, Date regDate) {
		super();
		this.aid 		= aid;
		this.did 		= did;
		this.dust10 	= dust10;
		this.dust25 	= dust25;
		this.dust100 	= dust100;
		this.voc 		= voc;
		this.co			= co;
		this.radon 		= radon;
		this.hum 		= hum;
		this.temp 		= temp;
		this.regDate 	= regDate;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public double getDust10() {
		return dust10;
	}

	public void setDust10(double dust10) {
		this.dust10 = dust10;
	}

	public double getDust25() {
		return dust25;
	}

	public void setDust25(double dust25) {
		this.dust25 = dust25;
	}

	public double getDust100() {
		return dust100;
	}

	public void setDust100(double dust100) {
		this.dust100 = dust100;
	}

	public double getVoc() {
		return voc;
	}

	public void setVoc(double voc) {
		this.voc = voc;
	}

	public double getRadon() {
		return radon;
	}

	public void setRadon(double radon) {
		this.radon = radon;
	}

	public double getHum() {
		return hum;
	}

	public void setHum(double hum) {
		this.hum = hum;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public double getCo() {
		return co;
	}

	public void setCo(double co) {
		this.co = co;
	}

	@Override
	public String toString() {
		return "Air [aid=" + aid + ", did=" + did + ", dust10=" + dust10 + ", dust25=" + dust25 + ", dust100=" + dust100
				+ ", voc=" + voc + ", co=" + co + ", radon=" + radon + ", hum=" + hum + ", temp=" + temp + ", regDate="
				+ regDate + "]";
	}
	
}
