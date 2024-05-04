package com.example.excitinglife;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;

public class SmartLog {

    public static void logUseful(String string)
    {
        Log.d("SARANIN",string);
        Calendar calendar = Calendar.getInstance();

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        long currentTimeInMillis = calendar.getTimeInMillis();

        int milliseconds = (int) (currentTimeInMillis % 1000);
        milliseconds = milliseconds / 10;

        text.setValue(text.getValue()+"\n"+minutes+":"+seconds+":"+milliseconds+"->"+string);
    }

    public static void logDefault(String string)
    {
        Log.d("SARANIN",string);
    }

    public static void log(String string1,String string2)
    {
        Log.d(string1,string2);
    }

    public static MutableLiveData<String> text = new MutableLiveData<>();

    static {
        text.setValue("Победа! Ты нашел секретную комнату! И она не просто так..."); // Установка начального значения
    }


}
