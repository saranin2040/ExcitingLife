package com.example.excitinglife.BusinessLogic.Stopwatch;

import android.os.SystemClock;
import android.util.Log;

import java.io.Serializable;

public class StopwatchDo implements Stopwatch, Serializable
{
    public StopwatchDo(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
    public long getElapsedTime()
    {
        if (launch)
        {
            return SystemClock.elapsedRealtime() - startTime + elapsedTime;
        }

        return elapsedTime;

    }

    @Override
    public boolean isLaunch() {
        return launch;
    }

    public void start(){
        Log.d("SARANIN","start stop watch");
        if (!launch) {
            startTime = SystemClock.elapsedRealtime();
            launch = true;
        }
    }

    public void stop(){
        if (launch) {
            launch = false;
            Log.d("saranin","im false");
            elapsedTime = SystemClock.elapsedRealtime() - startTime + elapsedTime;
        }
    }

    private String name=null;
    private long startTime;
    private long elapsedTime=0;

    private boolean launch=false;

    private static final long serialVersionUID = 1L;

}
