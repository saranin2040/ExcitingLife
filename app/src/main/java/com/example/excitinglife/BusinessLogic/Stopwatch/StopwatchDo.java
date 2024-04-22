package com.example.excitinglife.BusinessLogic.Stopwatch;

public class StopwatchDo implements Stopwatch
{
    public StopwatchDo(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
    public long getTime()
    {
        return time;
    }

    public void start(){

    }

    public void stop(){

    }


    private String name=null;
    private long time=0;
}
