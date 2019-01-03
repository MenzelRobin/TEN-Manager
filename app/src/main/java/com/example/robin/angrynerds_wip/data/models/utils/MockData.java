package com.example.robin.angrynerds_wip.data.models.utils;

import android.graphics.Color;
//import android.support.annotation.NonNull;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockData {

    //Nicht-Statische Liste mit 10 todos, 10 Events und 10 Notes
    //Verwendung mit MockData.tenMockData -> Bitte Nicht
    public ArrayList<TEN> tenMockData = new ArrayList<>();

    public MockData() {
        int[] bgColors = new int[]{
                Color.parseColor("#DD2C00"),
                Color.parseColor("#087f23"),
                Color.parseColor("#00766c"),
                Color.parseColor("#3d5afe"),
                Color.parseColor("#1a237e"),
                Color.parseColor("#b7008a"),
                Color.parseColor("#ffab00"),
                Color.parseColor("#f57f17"),
                Color.parseColor("#60e10f"),
                Color.parseColor("#8c234d")
        };

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", false));
        tasks.add(new Task("Task 2", true));
        tasks.add(new Task("Task 3", false));

        ArrayList<Date> reminder = new ArrayList<>();
        reminder.add(new Date());
        reminder.add(new Date(1540942029));

        ArrayList<String> tags = new ArrayList<>();
        tags.add("Tag1");
        tags.add("Tag2");
        tags.add("Tag3");

        RecurringType[] recurringTypes = new RecurringType[]{
                RecurringType.NONE,
                RecurringType.DAILY,
                RecurringType.WEEKLY,
                RecurringType.MONTHLY,
                RecurringType.YEARLY
        };
      
        for (int i = 1; i <= 10; i++) {
            tenMockData.add(new Todo("Todo " + i, bgColors[i - 1], "This is a toDo.", tasks, new Date()));
            tenMockData.add(new Event("Event " + i, bgColors[i - 1], Calendar.getInstance().getTime(), reminder,
                    new Address("Schlossallee", 1, "50674", "Köln"), recurringTypes[i % 5]));
            tenMockData.add(new Note("Note " + i, bgColors[i - 1], "This is a note.", tags));
        }
    }
}