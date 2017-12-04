/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyanogenmod.pearlynsetup;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.*;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ActivityNotFoundException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.google.android.gms",PackageManager.GET_ACTIVITIES);
            setContentView(R.layout.main);
			/* Enable Bluetooth onCreate */
			BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter(); 
			if (! mBtAdapter.isEnabled()) {
			mBtAdapter.enable(); 
			}
			/* Call AtvRemoteService */
			Intent intent = new Intent(Intent.ACTION_MAIN); 
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			intent.setClassName("com.google.android.tv.remote.service", "com.google.android.tv.remote.service.DiscoveryService"); 
			startActivity(intent);
			}	
			catch (PackageManager.NameNotFoundException e) {
				finish();
			}

    }
    
    /* Kill the app pressing "Back" button */
    @Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}
	
	@Override
	public void onDestroy()
	{
		try {
			/* Call Google's Setup Wizard */
            Intent intent = new Intent(Intent.ACTION_MAIN); 
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			intent.setClassName("com.google.android.tungsten.setupwraith", "com.google.android.tungsten.setupwraith.MainActivity"); 
			startActivity(intent);
			}	
			catch (ActivityNotFoundException e) {
			/* Call Default Launcher */
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
			}
			
		try{ 
			/* Disable Package */
			PackageManager localPackageManager = getPackageManager();
			localPackageManager.setComponentEnabledSetting(new ComponentName("org.cyanogenmod.pearlynsetup", "org.cyanogenmod.pearlynsetup.MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 1);
			} catch (Exception e) {}
			
			/* Kill app */	
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();
	}

}
