package com.cyanogenmod.cmactions;
 
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.content.BroadcastReceiver;

public class CMActionsService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
		}

	@Override
		public void onCreate() {
		super.onCreate();
		}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_DREAMING_STARTED);
		filter.addAction(Intent.ACTION_DREAMING_STOPPED);
		final BroadcastReceiver mReceiver = new BootCompletedReceiver();
		registerReceiver(mReceiver, filter);
		return super.onStartCommand(intent, flags, startId);
		}
	public class LocalBinder extends Binder {
		CMActionsService getService() {
		return CMActionsService.this;
		}
	}
}
