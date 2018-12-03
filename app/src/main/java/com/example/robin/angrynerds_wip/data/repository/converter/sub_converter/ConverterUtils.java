package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ConverterUtils {


    public List<Date> castStringListToDateList(List<String> dateStrings) {
        List<Date> dates = new ArrayList<Date>();
        for (String dateString : dateStrings) {
            char[] dateCArray  = dateString.toCharArray();
            dateCArray[10] = ' ';
            dateCArray[dateCArray.length-1] = ' ';
            dateString = String.valueOf(dateCArray);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
            try {
                Date date = simpleDateFormat.parse(dateString);
                dates.add(date);
            } catch (ParseException p) {
                Log.i(TAG, "Umwandlungsfehler castStringListToDateList");
            }
        }
        return dates;
    }

    public List<Object> castListToObjectList(List<Date> list) {
        List<Object> objectList = new ArrayList<Object>();
        for (Date date : list) {
            objectList.add(date);
        }
        return objectList;
    }
}
