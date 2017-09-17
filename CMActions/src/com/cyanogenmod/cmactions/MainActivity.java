/*
 * Copyright (c) 2016 The CyanogenMod Project
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

package com.cyanogenmod.cmactions;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.os.SystemProperties;
import android.util.Log;
import java.io.*;
import java.util.prefs.*;

public class MainActivity extends Activity {

private Switch DeviceModeSwitch;

	// Write into usb_device file
    private void handleDeviceMode(String value) {
    try {
			String f = "/proc/usb_device";
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.write(value); 
			wr.close();
			bw.close();
            } catch(IOException e) {
            e.printStackTrace();
			}
    }
    
    // Get the system property
    public static String getsysprop() {
    InputStreamReader in = null;
    BufferedReader reader = null;
    try {
        Process proc = Runtime.getRuntime().exec(new String[]{"/system/bin/getprop", "persist.sys.rzr.device_mode"});
        in = new InputStreamReader(proc.getInputStream());
        reader = new BufferedReader(in);
        return reader.readLine();
    } catch (IOException e) {
        return null;
    } finally {
        closeQuietly(in);
        closeQuietly(reader);
    }
}

public static void closeQuietly(Closeable closeable) {
    if (closeable == null) return;
    try {
        closeable.close();
    } catch (IOException ignored) {
    }
}
	
	// Set the system property
	private void setsysprop(String value) {
	try {
	String[] cmd = { "/system/bin/sh", "-c", "setprop persist.sys.rzr.device_mode " + value};	
    Runtime.getRuntime().exec(cmd);
	} catch (java.io.IOException e) {
	}
	}

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

DeviceModeSwitch = (Switch) findViewById(R.id.DeviceModeSwitch);

// Initial state
String initialstate = getsysprop();
if (initialstate.equals("true")) { DeviceModeSwitch.setChecked(true); } else { DeviceModeSwitch.setChecked(false); }

// attach a listener to check for changes in state
DeviceModeSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

@Override
public void onCheckedChanged(CompoundButton buttonView,
	boolean isChecked) {

		if (isChecked) {

			handleDeviceMode("0");
			setsysprop("true");
			Log.i("Device Mode","On");

			} else {

			handleDeviceMode("1");
			setsysprop("false");
			Log.i("Device Mode","Off");
		}

		}
		});
}
}
