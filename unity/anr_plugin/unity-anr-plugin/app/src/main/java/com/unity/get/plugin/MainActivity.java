/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unity.get.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.unity.plugin.Memory;
import com.google.unity.plugin.UnityDebugHelper;


public class MainActivity extends AppCompatActivity {

    private Memory memory;
    private UnityDebugHelper plugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plugin = new UnityDebugHelper();
        plugin.enableLogging(true);
        plugin.init(this, null);
        plugin.checkForExitInfo();

        memory = new Memory(this, false);

//
//        addOnTrimMemoryListener(memory.consumer);
//
//        PlatformChecker platformChecker = new PlatformChecker(this);
//        Log.d(App.Tag, String.format("Is Chrome: %s", platformChecker.isChromebook()));
//        Log.d(App.Tag, String.format("Is HPE: %s", platformChecker.isPlayGamesPC()));
//
//
//        PackageInfo info = WebViewCompat.getCurrentWebViewPackage(this);
//
//        Log.d(App.Tag, "version name: " + info.versionName);

    }

    public void forceANR(View view) {
        memory.getMemoryInfo(true);
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //am.appNotResponding("Forced ANR");
        }
    }
}