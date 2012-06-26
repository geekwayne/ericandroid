package net.fxing.service;

import java.util.Timer;
import java.util.TimerTask; 
import net.fxing.R;
import net.fxing.data.WeatherFetcher;
import net.fxing.data.WeatherInfoInterface1;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class WeatherService extends Service {

	private Timer timer;
	
    private NotificationManager nm;

	private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			WeatherInfoInterface1 recode=  new WeatherFetcher("101010100").getWeatherInfo1();
			setNotification(recode);
		}
	};
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			 notifyFromHandler(msg);
		}
		
	};
	
	@Override
	public void onCreate() {
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		timer = new Timer();
		timer.schedule(task, 5000, 10000);
		
	}
	
	 
	protected void notifyFromHandler(Message msg) {
		Uri uri = Uri.parse("weather://net.fxing");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
				Intent.FLAG_ACTIVITY_NEW_TASK, intent,
				PendingIntent.FLAG_ONE_SHOT);
		final Notification n = new Notification(R.drawable.logo,
				"最新天气预报", System.currentTimeMillis());
		n.setLatestEventInfo(
				this,
				"最新" + msg.getData().getString("city") + "天气预报",
				msg.getData().getString(
						"温度：" + msg.getData().getString("temp") + "时间："
								+ msg.getData().getString("time")),
				pendingIntent);
		
		 nm.notify(Integer.parseInt(msg.getData().getString("cityid")), n);
	}

	private void setNotification(WeatherInfoInterface1 recode) {
		 Message message = Message.obtain();
		 Bundle bundle = new Bundle();
		 bundle.putString("city",recode.getCity());
		 bundle.putString("time", recode.getTime());
		 bundle.putString("temp", recode.getTemp());
		 bundle.putString("cityid", recode.getCityid());
		 message.setData(bundle);
		 
		 handler.sendMessage(message);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}



	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
