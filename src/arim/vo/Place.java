package arim.vo;

import java.util.Date;

import arim.vo.Air;

/**
 * 외부 수집 공기 센서들의 정보
 * 20200422 addition 정보
 * 
 * @author arim-hyun
 *
 */
public class Place{	
	String 	did; //device의 did 와 동일하게 적용
	String  name;//stationName
	
	String  local;		//직역명
	String 	addr; 		//주소
	String 	operator; 	//운영기관
	String 	installYear;//설치년도
	
	double 	latitude;
	double 	longitude;
	
	Date 	regDate;
	Date	lastUpdate;
	
	Air air; //last air
	
	public Place() {
		super();
	}	
	
	public Place(String did, String name,String local, String addr, String operator, String installYear, double latitude, double longitude, Date regDate, Date lastUpdate) {
		super();
		this.did = did;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.regDate = regDate;
		this.lastUpdate = lastUpdate;		
		//addition
		this.local 		= local;
		this.addr 		= addr;
		this.operator 	= operator;
		this.installYear = installYear;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInstallYear() {
		return installYear;
	}

	public void setInstallYear(String installYear) {
		this.installYear = installYear;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Air getAir() {
		return air;
	}

	public void setAir(Air air) {
		this.air = air;
	}

	@Override
	public String toString() {
		return "Place [did=" + did + ", name=" + name + ", local=" + local + ", addr=" + addr + ", operator=" + operator
				+ ", installYear=" + installYear + ", latitude=" + latitude + ", longitude=" + longitude + ", regDate="
				+ regDate + ", lastUpdate=" + lastUpdate + ", air=" + air + "]";
	}

	
	
	
}
