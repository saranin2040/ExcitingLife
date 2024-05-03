package com.example.excitinglife;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
public class SmartLog {

    public static void logUseful(String string)
    {
        Log.d("SARANIN",string);
        text.setValue(text.getValue()+"\n"+"->"+string);
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
