package com.technoexponent.demonotifications.receivers;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.technoexponent.demonotifications.R;

/**
 * Created by technoexponent on 19-Jun-19.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public static final String titleMessage = "Dummy title message";
    public static final String contentMessage = "Dummy content message";

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Whenever call this method notification will appear.
        displayNotification(context, titleMessage, contentMessage);
    }

    /**
     * Display notification.
     *
     * @param context
     */
    private void displayNotification(Context context, String titleText, String contentText) {
        // notification sound
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // Gets an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //set notification channel id
        String NOTIFICATION_CHANNEL_ID = "101";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);
            //Configure Notification Channel
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)// notification icon
                .setContentTitle(titleText)// notification title text
                .setAutoCancel(true)
                .setSound(defaultSound)// notification sound
                .setContentText(contentText)// notification content text
                .setWhen(System.currentTimeMillis()) //notification time
                .setPriority(Notification.PRIORITY_MAX); //notification priority
        notificationManager.notify(1, notificationBuilder.build());
    }
}
