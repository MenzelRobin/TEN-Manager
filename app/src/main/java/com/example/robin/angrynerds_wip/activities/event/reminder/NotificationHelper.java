package com.example.robin.angrynerds_wip.activities.event.reminder;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.robin.angrynerds_wip.R;


public class NotificationHelper extends ContextWrapper {
    /* Robin Menzel
    Helps to manage Notifications.
    */
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    private NotificationManager mNotificationManager;

    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    private void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel1.setDescription("This is Channel 1");
            channel1.enableLights(true);
            channel1.enableVibration(true);

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("This is Channel 2");
            channel2.enableLights(true);
            channel2.enableVibration(true);

            getManager().createNotificationChannel(channel1);
            getManager().createNotificationChannel(channel2);
        }
    }

    public NotificationCompat.Builder getChannel1Notification(String title, String msg){
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp);
    }

    public NotificationManager getManager(){
        if(mNotificationManager==null){
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }
}