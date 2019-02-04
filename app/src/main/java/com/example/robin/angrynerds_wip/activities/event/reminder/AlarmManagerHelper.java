package com.example.robin.angrynerds_wip.activities.event.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.data.EventData;

import java.util.Date;

public class AlarmManagerHelper {
    public AlarmManagerHelper() {
    }

    public void startAlarm(AppCompatActivity pActivity, EventData pData, Date reminderTime, int requestCode) {
        AlarmManager alarmManager = (AlarmManager) pActivity.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(pActivity, AlertReceiver.class);
        String title = pActivity.getString(R.string.event_reminder_title).replace("TITLE", pData.getEvent().getTitle());
        String text = pActivity.getString(R.string.event_reminder_text).replace("DATE", pData.getFormatedDate());
        text = text.replace("TIME", pData.getFormatedTime());

        intent.putExtra("TITLE", title);
        intent.putExtra("TEXT", text);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(pActivity, requestCode, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminderTime.getTime(), pendingIntent);
    }

    public void cancelAlarm(AppCompatActivity pActivity, EventData pData, int requestCode) {
        AlarmManager alarmManager = (AlarmManager) pActivity.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(pActivity, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(pActivity, requestCode, intent, 0);

        alarmManager.cancel(pendingIntent);

    }
}
