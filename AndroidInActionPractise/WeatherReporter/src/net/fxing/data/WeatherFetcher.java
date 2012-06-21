package net.fxing.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
 
public class WeatherFetcher {

    private static final String CLASSTAG = WeatherFetcher.class.getSimpleName();
    private static final String QBASE = "http://www.weather.com.cn/data/sk/";
    private String citycode;
    private String queryURL;
    
    public WeatherFetcher(String citycode){
    	this.citycode = citycode;
    	this.queryURL  = QBASE+this.citycode+".html";
    }
    
    public WeatherInfoInterface1 getWeatherInfo1(){
    	WeatherInfoInterface1 info = null;
    	try{
    		InputStream inputStream = getStream();
    		StringBuilder sBuilder = new StringBuilder();
    		BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
    		for(String s = bReader.readLine(); s!=null ; s = bReader.readLine()){
    			sBuilder.append(s);
    		}
    		  JSONObject o = new JSONObject(sBuilder.toString()); 
    		  JSONObject jsonObject = o.getJSONObject("weatherinfo");
    		  info = new WeatherInfoInterface1(); 
    		  info.setCity(jsonObject.getString("city"));
    		  info.setCityid(jsonObject.getString("cityid"));
    		  info.setTemp(jsonObject.getString("temp"));
    		  info.setWD(jsonObject.getString("WD"));
    		  info.setWS(jsonObject.getString("WS"));
    		  info.setSD(jsonObject.getString("SD")); 
    		  info.setWSE(jsonObject.getString("WSE")); 
    		  info.setTime(jsonObject.getString("time")); 
    		  info.setIsRadar(jsonObject.getString("isRadar")); 
    		  info.setRadar(jsonObject.getString("Radar")); 
    		   
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return info;
    }

	private InputStream getStream() throws Exception{
		InputStream stream = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(queryURL);
		HttpResponse response =  client.execute(get);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			HttpEntity entity =  response.getEntity();
			stream = entity.getContent();
		}
		return stream;
	}
}
