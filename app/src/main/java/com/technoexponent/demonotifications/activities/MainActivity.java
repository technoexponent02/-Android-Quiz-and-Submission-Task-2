package com.technoexponent.demonotifications.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.technoexponent.demonotifications.R;
import com.technoexponent.demonotifications.receivers.AlarmReceiver;

/**
 * Created by technoexponent on 19-Jun-19.
 */

public class MainActivity extends AppCompatActivity {

    public final static int ALARM_ID = 12345;

    public static final String MY_PREFS_NAME = "MyPrefs";
    public static final String isStart = "startKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieved SharedPreferences data for checking whether call Alarm receiver is started or not.
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString(isStart, null);
        //If not started then only below block will executed otherwise not.
        if (restoredText == null) {
            Intent intentAlarm = new Intent(this, AlarmReceiver.class);
            //Calling Alarm receiver
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            //set the notification to repeat every five minutes
            long intervalTime = 5 * 60 * 1000; // 5 min
            // set unique id to the pending item, so we can call it when needed
            PendingIntent pi = PendingIntent.getBroadcast(this, ALARM_ID, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), intervalTime, pi);

            //Save 'isStart=Yes' into SharedPreferences until and unless app will uninstall or clear data.
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(isStart, "Yes");
            editor.apply();
        }
    }
}
