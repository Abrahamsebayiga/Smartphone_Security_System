package com.example.pdsafe256;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, Register.class));
            finish();
        }, 2000);

        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        startForegroundService(serviceIntent);

        foregroundServiceRunning();
    }

    public void foregroundServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

            for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (MyForegroundService.class.getName().equals(service.service.getClassName())) {
                    return;
                }
            }
        }
    }

//Build was configured to prefer settings repositories over project repositories but repository 'Google' was added by build file 'build.gradle'
