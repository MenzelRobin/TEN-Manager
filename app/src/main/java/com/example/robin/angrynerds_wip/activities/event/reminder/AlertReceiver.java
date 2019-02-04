package com.example.robin.angrynerds_wip.activities.event.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(
                intent.getStringExtra("TITLE"),
                intent.getStringExtra("TEXT"));
        notificationHelper.getManager().notify(1, nb.build());
    }
}
