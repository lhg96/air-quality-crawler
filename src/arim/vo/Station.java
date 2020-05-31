package arim.vo;

public class Station {
	String 	stationName;
	String 	addr;
	int 	year;
	String  mangName;
	String 	item;
	double  dmX;
	double  dmY;
	
	
	
	public Station(String stationName, String addr, int year, String mangName, String item,double dmX, double dmY) {
		super();
		this.stationName = stationName;
		this.addr = addr;
		this.year = year;
		this.mangName = mangName;
		this.item = item;
		this.dmX = dmX;
		this.dmY = dmY;
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMangName() {
		return mangName;
	}
	public void setMangName(String mangName) {
		this.mangName = mangName;
	}
	public double getDmX() {
		return dmX;
	}
	public void setDmX(double dmX) {
		this.dmX = dmX;
	}
	public double getDmY() {
		return dmY;
	}
	public void setDmY(double dmY) {
		this.dmY = dmY;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Station [stationName=" + stationName + ", addr=" + addr + ", year=" + year + ", mangName=" + mangName
				+ ", item=" + item + ", dmX=" + dmX + ", dmY=" + dmY + "]";
	}
	
	
	
}
