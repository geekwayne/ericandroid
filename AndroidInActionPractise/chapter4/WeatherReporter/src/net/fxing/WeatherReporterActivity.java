package net.fxing;

import net.fxing.data.WeatherFetcher;
import net.fxing.data.WeatherInfoInterface1;
import net.fxing.service.WeatherService;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherReporterActivity extends Activity {

 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		loadWeather();
		startService(new Intent(this,WeatherService.class)); 
	}

	private void loadWeather() {
		try {
			WeatherInfoInterface1 weatherInfo = new WeatherFetcher("101010100")
					.getWeatherInfo1();
			((TextView) findViewById(R.id.temp))
					.setText(((WeatherInfoInterface1) weatherInfo).getTemp());
			((TextView) findViewById(R.id.WD))
					.setText(((WeatherInfoInterface1) weatherInfo).getWD());
			((TextView) findViewById(R.id.WS))
					.setText(((WeatherInfoInterface1) weatherInfo).getWS());
			((TextView) findViewById(R.id.time))
					.setText(((WeatherInfoInterface1) weatherInfo).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadWeather();
	}

	 
	
}