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


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Enable Bluetooth onCreate */
			BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter(); 
			if (! mBtAdapter.isEnabled()) {
			mBtAdapter.enable(); 
			}
		/* Call IOSBeamService */
			Intent i = new Intent();		 
 			String pkg = "com.razerzone.pearlyn.beam.iosservice";
 			String cls = "com.razerzone.pearlyn.beam.iosservice.BeamService"; 
 			i.setComponent(new ComponentName(pkg, cls));
 			startService(i);
 		/* Draw the layout */	
 			setContentView(R.layout.main);	
        
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.google.android.gsf",PackageManager.GET_ACTIVITIES);
            managepackage ("com.google.android.gsf","com.google.android.gsf.settings.ConfirmLgaaylActivity",0);
			}	
			catch (PackageManager.NameNotFoundException e) {}

    }
    
    /* Kill the app pressing "Back" button */
    @Override
	public void onBackPressed() {
		disableandkill();
	}
	
	private void managepackage(String pkg, String act, int sta) {
		try{ 
			PackageManager localPackageManager = getPackageManager();
			if (sta==0) {localPackageManager.setComponentEnabledSetting(new ComponentName(pkg, act), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 1);}
			else {localPackageManager.setComponentEnabledSetting(new ComponentName(pkg, act), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 1);}
			} catch (Exception e) {}
		}
	
	private void disableandkill()
	{			
			/* Disable Package */
			managepackage ("com.cyanogenmod.pearlynsetup","com.cyanogenmod.pearlynsetup.MainActivity",0);
			/* Re-enable Provision */
			managepackage ("com.google.android.gsf","com.google.android.gsf.settings.ConfirmLgaaylActivity",1);
			/* Kill app */	
			finish();
	}

}
