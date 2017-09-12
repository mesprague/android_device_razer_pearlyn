/*
 * Copyright (C) 2016 The CyanogenMod Project
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

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.os.SystemProperties;
import java.io.*;
import java.util.prefs.*;

public class DeviceModeFragment extends PreferenceFragment {

    private static final String KEY_PEARLYN_ACTIONS_ENABLE = "pearlyn_actions_enable";
    private static final String DEVICE_MODE_PROPERTY = "persist.sys.rzr.device_mode";

    private SwitchPreference mDeviceModePreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.device_mode);
        boolean DeviceModeEnabled = isDeviceModeEnabled();
        mDeviceModePreference =
            (SwitchPreference) findPreference(KEY_PEARLYN_ACTIONS_ENABLE);
        mDeviceModePreference.setChecked(DeviceModeEnabled);
        mDeviceModePreference.setOnPreferenceChangeListener(mDeviceModePrefListener);
    }
    
    private boolean isDeviceModeEnabled() {
	    return SystemProperties.getBoolean(DEVICE_MODE_PROPERTY, false);
	}
  
    
    private void handleDeviceMode(int value) {
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


    private Preference.OnPreferenceChangeListener mDeviceModePrefListener =
        new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
             if (newValue.toString().equals("true")) {
				handleDeviceMode(0);
				System.setProperty(DEVICE_MODE_PROPERTY, "true");
			} else {
			handleDeviceMode(1);
			System.setProperty(DEVICE_MODE_PROPERTY, "false");
			}
			return false;
        }
    };
}
