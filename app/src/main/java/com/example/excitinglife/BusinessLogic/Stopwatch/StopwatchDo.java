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
            long currentElapsed = SystemClock.elapsedRealtime() - startTime;
            return SystemClock.elapsedRealtime() - startTime+ elapsedTime;
            //return SystemClock.elapsedRealtime() - startTime + elapsedTime;
        }

        return elapsedTime;

    }

    @Override
    public boolean isLaunch() {
        return launch;
    }

    @Override
    public boolean isStart() {return isStart;}

    public void start()
    {
        if (!launch)
        {
            if (!isStart) {isStart=true;}
            startTime = SystemClock.elapsedRealtime();
            launch = true;
        }

        Log.d("SARANIN","current time"+SystemClock.elapsedRealtime()+" | startTime="+startTime+" elapsedTime="+elapsedTime+" getElapsedTime="+(SystemClock.elapsedRealtime() - startTime + elapsedTime));
    }

    public void stop(){
        if (launch) {
            elapsedTime = getElapsedTime();
            launch = false;
            Log.d("saranin","im false");

        }
    }

    public void reset()
    {
        stop();
        isStart=false;
        startTime=0L;
        elapsedTime=0L;
    }


    private String name=null;
    private long startTime;
    private long elapsedTime=0L;

    private boolean launch=false;
    private boolean isStart=false;

    private static final long serialVersionUID = 1L;

}
