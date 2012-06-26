package net.fxing.data;

//接口1: http://www.weather.com.cn/data/sk/101010100.html
public class WeatherInfoInterface1 {

	private String city;
	private String cityid;
	private String temp;
	private String WD;
	private String WS;
	private String SD;
	private String WSE;
	private String time;
	private String isRadar;
	private String Radar;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getWD() {
		return WD;
	}

	public void setWD(String wD) {
		WD = wD;
	}

	public String getWS() {
		return WS;
	}

	public void setWS(String wS) {
		WS = wS;
	}

	public String getSD() {
		return SD;
	}

	public void setSD(String sD) {
		SD = sD;
	}

	public String getWSE() {
		return WSE;
	}

	public void setWSE(String wSE) {
		WSE = wSE;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsRadar() {
		return isRadar;
	}

	public void setIsRadar(String isRadar) {
		this.isRadar = isRadar;
	}

	public String getRadar() {
		return Radar;
	}

	public void setRadar(String radar) {
		Radar = radar;
	}

}
